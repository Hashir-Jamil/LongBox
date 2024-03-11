-- Schema Definition
DROP SCHEMA IF EXISTS longbox_schema CASCADE;
CREATE SCHEMA IF NOT EXISTS longbox_schema;

-- longbox_schema is scope for all following statements
SET SEARCH_PATH = longbox_schema;

-- Table Definitions
CREATE TABLE IF NOT EXISTS "user" (
    "id" integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "user_name" text UNIQUE,
    "first_name" text,
    "last_name" text,
    "dob" date,
    "email" text UNIQUE,
    "password" text,
    "country" text,
    "join_date" date,
    "comics_reading" integer,
    "comics_finished" integer,
    "about_me" text
);

CREATE TABLE IF NOT EXISTS "comic_book" (
    "id" integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "series_title" text,
    "author" text,
    "artist" text,
    "genres" text,
    "description" text,
    "number_of_issues" integer,
    "publisher" text,
    "year_published" integer,
    "date_added" date,
    "favorites_count" integer DEFAULT 0
);

CREATE TABLE IF NOT EXISTS "comic_book_favorites_list" (
    "user_id" integer NOT NULL,
    "comic_book_id" integer NOT NULL,
    "date_added_user_list" date,
    PRIMARY KEY ("user_id","comic_book_id")
);

CREATE TABLE IF NOT EXISTS "comments" (
    "id" integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "message" text,
    "comment_date" date,
    "comic_book_id" integer,
    "user_id" integer,
    "user_name" text
);

CREATE TABLE IF NOT EXISTS "comic_book_finished_list" (
    "user_id" integer NOT NULL,
    "comic_book_id" integer NOT NULL,
    "date_finished" date,
    PRIMARY KEY ("user_id","comic_book_id")
);

CREATE TABLE IF NOT EXISTS "comic_book_reading_list" (
   "user_id" integer NOT NULL,
   "comic_book_id" integer NOT NULL,
   "date_started" date,
   PRIMARY KEY ("user_id","comic_book_id")
);

-- Foreign Key Constraints
ALTER TABLE "comic_book_favorites_list" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comic_book_favorites_list" ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "comments" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comments" ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "comic_book_finished_list" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comic_book_finished_list" ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "comic_book_reading_list" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comic_book_reading_list" ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

-- Functions & Triggers

-- Create a function to update user.comics_finished column
CREATE OR REPLACE FUNCTION update_comics_finished_count()
RETURNS TRIGGER AS $$
BEGIN
    -- Update comics_finished count for the affected user
    SET SEARCH_PATH = longbox_schema;
	IF TG_OP = 'INSERT' THEN
        -- Increment comics_finished count
        UPDATE "user" u
        SET comics_finished = (
            SELECT COUNT(*)
            FROM comic_book_finished_list f
            WHERE f.user_id = NEW.user_id
        )
        WHERE u.id = NEW.user_id;
    ELSEIF TG_OP = 'DELETE' THEN
        -- Decrement comics_finished count
        UPDATE "user" u
        SET comics_finished = (
            SELECT COUNT(*)
            FROM comic_book_finished_list f
            WHERE f.user_id = OLD.user_id
        )
        WHERE u.id = OLD.user_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to execute the function after insert or delete on comic_book_finished_list table
CREATE TRIGGER update_comics_finished_trigger
    AFTER INSERT OR DELETE
ON comic_book_finished_list
FOR EACH ROW
EXECUTE FUNCTION update_comics_finished_count();

-- Create a function to update user.comics_reading column
CREATE OR REPLACE FUNCTION update_comics_reading_count()
    RETURNS TRIGGER AS $$
BEGIN
    -- Update comics_reading count for the affected user
    SET SEARCH_PATH = longbox_schema;
    IF TG_OP = 'INSERT' THEN
        -- Increment comics_reading count
        UPDATE "user" u
        SET comics_reading = (
            SELECT COUNT(*)
            FROM comic_book_reading_list r
            WHERE r.user_id = NEW.user_id
        )
        WHERE u.id = NEW.user_id;
    ELSEIF TG_OP = 'DELETE' THEN
        -- Decrement comics_reading count
        UPDATE "user" u
        SET comics_reading = (
            SELECT COUNT(*)
            FROM comic_book_reading_list r
            WHERE r.user_id = OLD.user_id
        )
        WHERE u.id = OLD.user_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to execute the function after insert or delete on comic_book_reading_list table
CREATE TRIGGER update_comics_reading_trigger
    AFTER INSERT OR DELETE
    ON comic_book_reading_list
    FOR EACH ROW
EXECUTE FUNCTION update_comics_reading_count();

-- Add data to the tables

-- First we add user objects
INSERT INTO longbox_schema."user"(
    user_name, first_name, last_name, dob, email, password, country, join_date, comics_reading, comics_finished, about_me)
VALUES
    ('Always_Scheming', 'John', 'Smith', '1990-12-1', 'email@domain.com', 'Always_Scheming', 'Canada', '2024-02-21', 0, 0, 'Imaginations ally and inks confidante, I craft worlds within the panels, inviting you to escape reality through the lens of my storytelling pen.'),
    ('Always_Throwing', 'Neo', 'Anderson', '3829-02-01', 'address@provider.ca', 'Always_Throwing', 'Indonesia', '2024-02-14 12:28:42', 0, 0,'An animated soul exploring both pixels and plot twists, I am your guide in the comic cosmos, steering you through adventures that leap off the screen.'),
    ('Phoenix', 'Stan', 'Lee', '3900-05-31', '123fake@nowhere.org', 'Phoenix', 'United Kingdom', '2024-02-14 12:42:43', 0, 0, 'Code-wielding superhero by day, rhythm-following vigilante by night, I bridge the gap between tech and tunes on this epic quest through the digital comic realm.'),
    ('ahan', 'Ahan', 'Bhargava', '2003-02-10', 'ahan@email.com', 'Password!1', 'India', '2024-02-15 15:09:10', 0, 0, 'A pixel pioneer on the quest for knowledge, I dive into the virtual inkwell, emerging with stories that captivate and characters that resonate.'),
    ('naha', 'Ahan', 'Bhargava', '2003-02-10', 'naha@email.com', 'naha', 'India', '2024-02-15 15:09:10', 0, 0, 'Juggling dumbbells and donuts in equal measure, I bring the perfect balance of action and humor to the comic book universe, one swipe at a time.'),
    ('123', 'Quick', 'Access', '2003-02-10', '123@email.com', '123', 'India', '2024-02-15 15:09:10', 0, 0, 'Roaming the digital landscapes with a camera lens for justice, I capture the essence of heroes and villains alike, freezing epic moments in the frames of your favorite comic book app.');

-- Next we add comic book objects
INSERT INTO longbox_schema.comic_book(
    series_title, author, artist, genres, description, number_of_issues, publisher, year_published, date_added)
VALUES
    ('Zot!', 'Scott McCloud', 'Scott McCloud',
     'Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama',
     'Empty', 36, 'Eclipse', 1984, '2024-02-15'),

    ('Sanctuary', 'Sho Fumimura', 'Ryoichi Ikegami',
     'Polital, Crime, Thriller, Manga',
     'Empty', 108, 'Viz', 1990, '2024-02-15'),

    ('Nexus (1981)', 'Mike Baron', 'Steve Rude',
     'Superhero, Planetary Romance, Superpower, Science Fiction, Adventure, Fantasy',
     'Empty', 3, 'Capital', 1981, '2024-02-15'),

    ('The Maxx', 'Sam Keith', 'Sam Keith',
     'Fantasy, Drama, Comedy, Superhero',
     'Empty', 35, 'Image', 1993, '2024-02-15'),

    ('Winter Wolrd', 'Chuck Dixon', 'Jorge Zaffino',
     'Adventure, Post Apocalyptic, Gunslinger',
     'Empty', 3, 'Eclipse', 1987, '2024-02-15'),

    ('Hellhounds Panzer Cops', 'Mamoru Oshii', 'Kamui Fujiwara',
     'Military, Police, Adventure, Dystopian, Manga',
     'Empty', 6, 'Dark Horse', 1994, '2024-02-15'),

    ('Jon Sable Freelance (1983)', 'Mike Grell', 'Mike Grell',
     'Action, Adventure, Crime, Vigilantes',
     'Empty', 56, 'First Comics', 1983, '2024-02-15'),

    ('Chronicles of Corum', 'Mike Baron', 'Mike Mignola',
     'Action, Adventure, Sword and Socery',
     'Empty', 12, 'First Comics', 1987, '2024-02-15'),

    ('Drakuun', 'Johji Manabe', 'Jonji Manabe',
     'Adventure, Sword and Socery, Comedy, Manga',
     'Empty', 24, 'Dark Horse', 1997, '2024-02-15'),

    ('Elementals (1984)', 'Bill Willingham', 'Bill Willingham',
     'Action, Superhero, Supernatural',
     'Empty', 29, 'Comico', 1984, '2024-02-25'),

    ('Airboy (1986)', 'Chuck Dixon', 'Timothy Truman',
     'Adventure, War',
     'Empty', 50, 'Eclipse', 1986, '2024-02-25'),

    ('Scout (1985)', 'Timothy Truman', 'Timothy Truman',
     'Adventure, War, Post Apocalyptic',
     'Empty', 24, 'Eclipse', 1985, '2024-02-25'),

    ('Warlock (1986)', 'Gordon Derry', 'Denis Beauvais',
     'Adventure, Action, Post Apocalyptic, Fantasy, Futuristic, Cyber Punk',
     'Empty', 22, 'Aircel', 1986, '2024-02-25'),

    ('Dalgoda', 'Jan Strnad', 'Dennis Fujitake',
     'Science Fiction',
     'Empty', 8, 'Fantagraphics', 1984, '2024-02-25'),

    ('Outlanders', 'Johji Manabe', 'Johji Manabe',
     'Science Fiction, Adventure, Sword and Planet, Manga',
     'Empty', 33, 'Dark Horse', 1988, '2024-02-25'),

    ('Evangeline (1984)', 'Chuck Dixon', 'Judith Hunt',
     'Adventure, Action, Science Fiction',
     'Empty', 2, 'Comico', 1984, '2024-02-25'),

    ('Evangeline (1987)', 'Chuck Dixon', 'Judith Hunt',
     'Adventure, Action, Science Fiction',
     'Empty', 12, 'First', 1987, '2024-02-25'),

    ('Badger (1983)', 'Mike Baron', 'Jeffrey Butler',
     'Adventure, Action, Superhero, Science Fiction',
     'Empty', 70, 'First', 1983, '2024-02-25'),

    ('Fightin Marines', 'Gene Colan', 'Gene Colan',
     'Adventure, Action, War, Anthology',
     'Empty', 151, 'Charlton', 1951, '2024-02-25'),

    ('Grimjack (1984)', 'John Ostrander', 'Timothy Truman',
     'Adventure, Action, Science Fiction, Vigilantes, Fantasy',
     'Empty', 81, 'First', 1984, '2024-02-25'),

    ('Caravan Kidd', 'Johji Manabe', 'Johji Manabe',
     'Adventure, Action, Science Fiction, Fantasy, Comedy, Manga',
     'Empty', 28, 'Dark Horse', 1992, '2024-02-25'),

    ('Boris the Bear', 'Mike Richardson', 'James Dean Smith',
     'Adventure, Action, Satire, Fantasy, Comedy',
     'Empty', 34, 'Dark Horse', 1986, '2024-02-25'),

    ('Destroyer Duck', 'Steve Gerber', 'Jack Kirby',
     'Adventure, Action, Satire, Fantasy, Comedy',
     'Empty', 7, 'Eclipse', 1982, '2024-02-25'),

    ('Merchants of Death', 'Grassi', 'Enrique Breccia',
     'Adventure, Action, Anthology',
     'Empty', 4, 'Eclipse', 1988, '2024-02-25'),

    ('Mr Monster', 'Michael T Gilbert', 'Michael T Gilbert',
     'Action, Supernatural, Superhero, Comedy',
     'Empty', 10, 'Eclipse', 1985, '2024-02-25'),

    ('Meridian', 'Barbara Kesel', 'Joshua Middleton',
     'Action, Supernatural, Superpower, Fantasy',
     'Empty', 44, 'Crossgen', 2000, '2024-02-25'),

    ('Mystic', 'Ron Marz', 'Brandon Peterson',
     'Action, Supernatural, Superpower, Fantasy',
     'Empty', 43, 'Crossgen', 2000, '2024-02-25'),

    ('Scion', 'Ron Marz', 'Jim Cheung',
     'Action, Supernatural, Superpower, Fantasy',
     'Empty', 43, 'Crossgen', 2000, '2024-02-25'),

    ('Sigil', 'Barbara Kesel', 'Wil Quintana',
     'Action, Supernatural, Superpower, Fantasy',
     'Empty', 42, 'Crossgen', 2000, '2024-02-25'),

    ('The Uncensored Mouse', 'Disney', 'Disney',
     'Bootleg',
     'Empty', 2, 'Eternity', 1989, '2024-02-25'),

    ('Maelstorm', 'Jim Somerville', 'Jim Somerville',
     'Dark Fantasy, Supernatural, Superpower, Action',
     'Empty', 11, 'Aircel', 1987, '2024-02-25'),

    ('Sojourn', 'Ron Marz', 'Greg Land',
     'Action, Adventure, Sword and Sorcery',
     'Empty', 34, 'Crossgen', 2001, '2024-02-15'),

    ('Absolute Zero', 'David Hahn', 'David Hahn',
     'Action, Superhero, Adventure',
     'Empty', 6, 'Antarctic Press', 1995, '2024-02-25'),

    ('Addam Omega', 'Bill Hughes', 'Bill Hughes',
     'Horror, Science Fiction, Superhero',
     'Empty', 4, 'Antarctic Press', 1997, '2024-02-25'),

    ('Actionopolis', 'Shannon Denton', 'Shannon Denton',
     'Action, Adventure',
     'Empty', 1, 'Antarctic Press', 2001, '2024-02-25'),

    ('Alien Worlds', 'Bruce Jones', 'Al Williamson',
     'Anthology, Science Fiction, Adventure',
     'Empty', 9, 'Pacific Comics', 1982, '2024-02-25'),

    ('Captain Victory and the Galactic Rangers', 'Jack Kirby', 'Jack Kirby',
     'Science Fiction, Superhero',
     'Empty', 13, 'Pacific Comics', 1981, '2024-02-25'),

    ('Groo the Wanderer', 'Sergio Aragones', 'Sergio Aragones',
     'Fantasy, Comedy',
     'Empty', 8, 'Pacific Comics', 1982, '2024-02-25');

-- Next we add some comments
INSERT INTO longbox_schema."comments"(
    message, comment_date, comic_book_id, user_id, user_name)
VALUES
    ('Wow, the art in this comic is absolutely breathtaking! The attention to detail and vibrant colors bring the characters to life in a way thats truly mesmerizing.',
     '2024-03-02', 1, 1, 'Always_Scheming'),
    ('This comics storyline is a rollercoaster of emotions. From intense action sequences to heartwarming moments, it keeps readers hooked with its perfect blend of drama and humor.',
     '2024-03-01', 3, 2, 'Always_Throwing'),
    ('Short and sweet, this comics humor is on point! The witty dialogue and clever punchlines had me chuckling from start to finish.',
     '2024-02-29', 2, 3, 'Phoenix'),
    ('I appreciate how this comic tackles relevant social issues without being preachy. The writers skillfully weave important themes into the narrative, making it both entertaining and thought-provoking.',
     '2024-02-29', 3, 5, 'naha'),
    ('The character development in this comic is phenomenal. Each character has a unique personality and backstory, adding depth to the overall narrative.',
     '2024-02-28', 10, 4, 'ahan'),
    ('The world-building in this comic is exceptional. The creators have crafted a rich and immersive universe that sparks the imagination and leaves readers craving more.',
     '2024-03-02', 34, 1, 'Always_Scheming'),
    ('This comics artwork is a true work of art. The use of unconventional panel layouts and innovative visual storytelling techniques adds a dynamic layer to the overall reading experience.',
     '2024-03-01', 21, 2, 'Always_Throwing'),
    ('Simple yet powerful, this comics message resonates deeply. Its amazing how a few carefully chosen words and poignant illustrations can leave a lasting impact.',
     '2024-02-29', 18, 3, 'Phoenix'),
    ('The pacing in this comic is spot-on. It keeps the story moving at a brisk pace, ensuring that readers are always engaged and eager to see what happens next.',
     '2024-02-29', 31, 5, 'naha'),
    ('This comic is a nostalgia trip! It expertly pays homage to classic comic book tropes while infusing a fresh and modern twist, making it a delightful read for both longtime fans and newcomers alike.',
     '2024-03-01', 7, 1, 'Always_Scheming'),
    ('I can''t get enough of the storyline in comic #5! It keeps me on the edge of my seat with every turn of the page.',
     '2024-03-02', 5, 1, 'Always_Scheming'),
    ('The character development in comic #12 is outstanding. Kudos to the writers for creating such a compelling narrative.',
     '2024-02-28', 12, 1, 'Always_Scheming'),
    ('Just finished reading comic #20. The plot twists are mind-blowing! Can''t wait for the next issue.',
     '2024-02-25', 20, 1, 'Always_Scheming'),
    ('The artwork in comic #8 is simply stunning. The artists talent shines through every panel.',
     '2024-03-01', 8, 2, 'Always_Throwing'),
    ('Comic #15 has a unique storyline that keeps me guessing. Excited to see where it goes next!',
     '2024-02-28', 15, 2, 'Always_Throwing'),
    ('Kudos to the creators of comic #25! The world-building is fantastic, and the characters are so well-developed.',
     '2024-02-24', 25, 2, 'Always_Throwing'),
    ('The nostalgia in comic #3 is overwhelming. Stan Lee truly knew how to create timeless characters.',
     '2024-02-29', 3, 3, 'Phoenix'),
    ('Just started comic #30, and it already has me hooked! Stan Lees legacy lives on.',
     '2024-02-27', 30, 3, 'Phoenix'),
    ('Comic #38 is a masterpiece! Thank you, Stan Lee, for your incredible contributions to the comic world.',
     '2024-02-22', 38, 3, 'Phoenix'),
    ('The plot twists in comic #2 are mind-boggling! A must-read for any comic enthusiast.',
     '2024-02-28', 2, 4, 'ahan'),
    ('Comic #10 is a great blend of action and drama. The character dynamics are well-executed.',
     '2024-02-26', 10, 4, 'ahan'),
    ('Just finished comic #18, and Im speechless. The storytelling is top-notch!',
     '2024-02-23', 18, 4, 'ahan'),
    ('Comic #7 is a masterpiece! The storytelling and art are on another level. Cant wait for the next issue.',
     '2024-02-27', 7, 1, 'Always_Scheming'),
    ('Just started comic #14, and its already becoming one of my favorites. The characters are so relatable!',
     '2024-02-25', 14, 1, 'Always_Scheming'),
    ('Comic #22 took me on an emotional rollercoaster. Well done to the creative team!',
     '2024-02-22', 22, 1, 'Always_Scheming'),
    ('Comic #9 has some jaw-dropping action scenes. The fight choreography is phenomenal!',
     '2024-02-26', 9, 2, 'Always_Throwing'),
    ('The humor in comic #16 is spot on! I found myself laughing out loud multiple times.',
     '2024-02-24', 16, 2, 'Always_Throwing'),
    ('Comic #28 has an unexpected twist that left me speechless. Bravo to the writers!',
     '2024-02-21', 28, 2, 'Always_Throwing'),
    ('Comic #4 is a classic. The timeless storytelling and iconic characters never get old.',
     '2024-02-28', 4, 3, 'Phoenix'),
    ('Just finished comic #11, and it exceeded my expectations. Stan Lees legacy lives on in every page.',
     '2024-02-27', 11, 3, 'Phoenix'),
    ('Comic #27 has some breathtaking visuals. Stan Lees influence on the industry is unparalleled.',
     '2024-02-23', 27, 3, 'Phoenix'),
    ('Comic #1 is a great introduction to the series. The world-building is intriguing!',
     '2024-02-25', 1, 4, 'ahan'),
    ('Just caught up with comic #13, and it left me with so many questions. Cant wait for the next chapter!',
     '2024-02-23', 13, 4, 'ahan'),
    ('The character development in comic #21 is exceptional. Ahan approves!',
     '2024-02-20', 21, 4, 'ahan');
