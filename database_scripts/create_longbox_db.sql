-- Schema Definition
DROP SCHEMA IF EXISTS longbox_schema CASCADE;
CREATE SCHEMA IF NOT EXISTS longbox_schema;

-- longbox_schema is scope for all following statements
SET SEARCH_PATH = longbox_schema;

-- Table Definitions
CREATE TABLE IF NOT EXISTS "user"
(
    "id"              bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "user_name"       text UNIQUE,
    "first_name"      text,
    "last_name"       text,
    "dob"             date,
    "email"           text UNIQUE,
    "password"        text,
    "country"         text,
    "continent"       text,
    "join_date"       date,
    "comics_reading"  integer DEFAULT 0,
    "comics_finished" integer DEFAULT 0,
    "about_me"        text,
    "preferred_genre" text
);

CREATE TABLE IF NOT EXISTS "comic_book"
(
    "id"                             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "series_title"                   text,
    "author"                         text,
    "artist"                         text,
    "genres"                         text,
    "description"                    text,
    "number_of_issues"               integer DEFAULT 0,
    "publisher"                      text,
    "year_published"                 integer DEFAULT 0,
    "date_added"                     date,
    "favourites_count"               integer DEFAULT 0,
    "north_america_favourites_count" integer DEFAULT 0,
    "south_america_favourites_count" integer DEFAULT 0,
    "europe_favourites_count"        integer DEFAULT 0,
    "asia_favourites_count"          integer DEFAULT 0,
    "africa_favourites_count"        integer DEFAULT 0,
    "oceania_favourites_count"       integer DEFAULT 0,
    "antarctica_favourites_count"    integer DEFAULT 0
);

CREATE TABLE IF NOT EXISTS "comic_book_favourites_list"
(
    "user_id"              bigint NOT NULL,
    "comic_book_id"        bigint NOT NULL,
    "date_added_user_list" date,
    PRIMARY KEY ("user_id", "comic_book_id")
);

CREATE TABLE IF NOT EXISTS "comments"
(
    "id"            bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "message"       text,
    "comment_date"  date,
    "comic_book_id" bigint,
    "user_id"       bigint,
    "user_name"     text
);

CREATE TABLE IF NOT EXISTS "star_ratings" (
    "comic_book_id" bigint,
    "user_id" bigint,
    "rating" integer,
    PRIMARY KEY ("user_id","comic_book_id")
);

CREATE TABLE IF NOT EXISTS "comic_book_finished_list"
(
    "user_id"       bigint NOT NULL,
    "comic_book_id" bigint NOT NULL,
    "date_finished" date,
    PRIMARY KEY ("user_id", "comic_book_id")
);

CREATE TABLE IF NOT EXISTS "comic_book_reading_list"
(
    "user_id"       bigint NOT NULL,
    "comic_book_id" bigint NOT NULL,
    "date_started"  date,
    PRIMARY KEY ("user_id", "comic_book_id")
);

-- Foreign Key Constraints
ALTER TABLE "comic_book_favourites_list"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comic_book_favourites_list"
    ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "comments"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comments"
    ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "star_ratings"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "star_ratings"
    ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "comic_book_finished_list"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comic_book_finished_list"
    ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "comic_book_reading_list"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "comic_book_reading_list"
    ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

-- Functions & Triggers

-- Create a function to update comic_book.favourites_count and comic_book.*(continent)_favourites_count column
CREATE OR REPLACE FUNCTION update_comic_favourites_count()
    RETURNS TRIGGER AS
$$
BEGIN
    SET SEARCH_PATH = longbox_schema;
    IF TG_OP = 'INSERT' THEN
        DECLARE
            user_continent text;
        BEGIN
            SELECT continent INTO user_continent FROM "user" AS u WHERE u.id = NEW.user_id;

            IF user_continent = 'North_America' THEN
                UPDATE comic_book AS c
                SET north_america_favourites_count = north_america_favourites_count + 1,
                    favourites_count               = favourites_count + 1
                WHERE c.id = NEW.comic_book_id;
            ELSIF user_continent = 'South_America' THEN
                UPDATE comic_book AS c
                SET south_america_favourites_count = south_america_favourites_count + 1,
                    favourites_count               = favourites_count + 1
                WHERE c.id = NEW.comic_book_id;
            ELSIF user_continent = 'Europe' THEN
                UPDATE comic_book AS c
                SET europe_favourites_count = europe_favourites_count + 1,
                    favourites_count        = favourites_count + 1
                WHERE c.id = NEW.comic_book_id;
            ELSIF user_continent = 'Asia' THEN
                UPDATE comic_book AS c
                SET asia_favourites_count = asia_favourites_count + 1,
                    favourites_count      = favourites_count + 1
                WHERE c.id = NEW.comic_book_id;
            ELSIF user_continent = 'Africa' THEN
                UPDATE comic_book AS c
                SET africa_favourites_count = africa_favourites_count + 1,
                    favourites_count        = favourites_count + 1
                WHERE c.id = NEW.comic_book_id;
            ELSIF user_continent = 'Oceania' THEN
                UPDATE comic_book AS c
                SET oceania_favourites_count = oceania_favourites_count + 1,
                    favourites_count         = favourites_count + 1
                WHERE c.id = NEW.comic_book_id;
            ELSIF user_continent = 'Antarctica' THEN
                UPDATE comic_book AS c
                SET antarctica_favourites_count = antarctica_favourites_count + 1,
                    favourites_count            = favourites_count + 1
                WHERE c.id = NEW.comic_book_id;
            END IF;
        END;
    ELSEIF TG_OP = 'DELETE' THEN
        DECLARE
            user_continent text;
        BEGIN
            SELECT continent INTO user_continent FROM "user" AS u WHERE u.id = OLD.user_id;

            IF user_continent = 'North_America' THEN
                UPDATE comic_book AS c
                SET north_america_favourites_count = north_america_favourites_count - 1,
                    favourites_count               = favourites_count - 1
                WHERE c.id = OLD.comic_book_id;
            ELSIF user_continent = 'South_America' THEN
                UPDATE comic_book AS c
                SET south_america_favourites_count = south_america_favourites_count - 1,
                    favourites_count               = favourites_count - 1
                WHERE c.id = OLD.comic_book_id;
            ELSIF user_continent = 'Europe' THEN
                UPDATE comic_book AS c
                SET europe_favourites_count = europe_favourites_count - 1,
                    favourites_count        = favourites_count - 1
                WHERE c.id = OLD.comic_book_id;
            ELSIF user_continent = 'Asia' THEN
                UPDATE comic_book AS c
                SET asia_favourites_count = asia_favourites_count - 1,
                    favourites_count      = favourites_count - 1
                WHERE c.id = OLD.comic_book_id;
            ELSIF user_continent = 'Africa' THEN
                UPDATE comic_book AS c
                SET africa_favourites_count = africa_favourites_count - 1,
                    favourites_count        = favourites_count - 1
                WHERE c.id = OLD.comic_book_id;
            ELSIF user_continent = 'Oceania' THEN
                UPDATE comic_book AS c
                SET oceania_favourites_count = oceania_favourites_count - 1,
                    favourites_count         = favourites_count - 1
                WHERE c.id = OLD.comic_book_id;
            ELSIF user_continent = 'Antarctica' THEN
                UPDATE comic_book AS c
                SET antarctica_favourites_count = antarctica_favourites_count - 1,
                    favourites_count            = favourites_count - 1
                WHERE c.id = OLD.comic_book_id;
            END IF;
        END;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to execute the function after insert or delete on comic_book_favourites_list table
CREATE TRIGGER update_comic_favourites_trigger
    AFTER INSERT OR DELETE
    ON comic_book_favourites_list
    FOR EACH ROW
EXECUTE FUNCTION update_comic_favourites_count();

-- Create a function to update user.comics_finished column
CREATE OR REPLACE FUNCTION update_comics_finished_count()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Update comics_finished count for the affected user
    SET SEARCH_PATH = longbox_schema;
    IF TG_OP = 'INSERT' THEN
        -- Increment comics_finished count
        UPDATE "user" u
        SET comics_finished = (SELECT COUNT(*)
                               FROM comic_book_finished_list f
                               WHERE f.user_id = NEW.user_id)
        WHERE u.id = NEW.user_id;
    ELSEIF TG_OP = 'DELETE' THEN
        -- Decrement comics_finished count
        UPDATE "user" u
        SET comics_finished = (SELECT COUNT(*)
                               FROM comic_book_finished_list f
                               WHERE f.user_id = OLD.user_id)
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
    RETURNS TRIGGER AS
$$
BEGIN
    -- Update comics_reading count for the affected user
    SET SEARCH_PATH = longbox_schema;
    IF TG_OP = 'INSERT' THEN
        -- Increment comics_reading count
        UPDATE "user" u
        SET comics_reading = (SELECT COUNT(*)
                              FROM comic_book_reading_list r
                              WHERE r.user_id = NEW.user_id)
        WHERE u.id = NEW.user_id;
    ELSEIF TG_OP = 'DELETE' THEN
        -- Decrement comics_reading count
        UPDATE "user" u
        SET comics_reading = (SELECT COUNT(*)
                              FROM comic_book_reading_list r
                              WHERE r.user_id = OLD.user_id)
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
INSERT INTO longbox_schema."user"(user_name, first_name, last_name, dob, email, password, country, continent, join_date,
                                  comics_reading, comics_finished, about_me, preferred_genre)
VALUES ('Always_Scheming', 'John', 'Smith', '1990-12-1', 'email@domain.com', 'Always_Scheming', 'Canada', 'Antarctica',
        '2024-02-21', 0, 0,
        'Imaginations ally and inks confidante, I craft worlds within the panels, inviting you to escape reality through the lens of my storytelling pen.',
        'Action, Manga, Fantasy'),
       ('Always_Throwing', 'Neo', 'Anderson', '3829-02-01', 'address@provider.ca', 'Always_Throwing', 'Indonesia',
        'South_America', '2024-02-14 12:28:42', 0, 0,
        'An animated soul exploring both pixels and plot twists, I am your guide in the comic cosmos, steering you through adventures that leap off the screen.',
        'Action, Comedy, Anthology, Fantasy'),
       ('Phoenix', 'Stan', 'Lee', '3900-05-31', '123fake@nowhere.org', 'Phoenix', 'United Kingdom', 'Europe',
        '2024-02-14 12:42:43', 0, 0,
        'Code-wielding superhero by day, rhythm-following vigilante by night, I bridge the gap between tech and tunes on this epic quest through the digital comic realm.',
        'Science Fiction, Manga, Superhero'),
       ('ahan', 'Ahan', 'Bhargava', '2003-02-10', 'ahan@email.com', 'Password!1', 'India', 'Africa',
        '2024-02-15 15:09:10', 0, 0,
        'A pixel pioneer on the quest for knowledge, I dive into the virtual inkwell, emerging with stories that captivate and characters that resonate.',
        ''),
       ('naha', 'Ahan', 'Bhargava', '2003-02-10', 'naha@email.com', 'naha', 'India', 'Asia', '2024-02-15 15:09:10', 0,
        0,
        'Juggling dumbbells and donuts in equal measure, I bring the perfect balance of action and humor to the comic book universe, one swipe at a time.',
        'Thriller'),
       ('123', 'Quick', 'Access', '2003-02-10', '123@email.com', '123', 'India', 'North_America', '2024-02-15 15:09:10',
        0, 0,
        'Roaming the digital landscapes with a camera lens for justice, I capture the essence of heroes and villains alike, freezing epic moments in the frames of your favourite comic book app.',
        'Action, Science Fiction, Anthology, Fantasy, Superhero'),
       ('ComicExplorer', 'Alice', 'Wong', '1985-07-15', 'alice@example.com', 'ExploringComics', 'United States',
        'North America', '2024-03-10', 0, 0,
        'Lost in the maze of panels, I wander through the realms of imagination, seeking treasures of art and story to share with fellow travelers.',
        'Adventure, Fantasy, Science Fiction'),
       ('InkMaster', 'Emily', 'Johnson', '1992-04-28', 'emily@email.com', 'InkMeister', 'United Kingdom', 'Europe',
        '2024-03-05', 0, 0,
        'With every stroke of the pen, I weave tales that dance on the canvas of your mind, inviting you to explore the depths of imagination.',
        'Fantasy, Adventure, Anthology'),
       ('PixelPilot', 'David', 'Lee', '1988-11-03', 'david@domain.com', 'PixelPilot123', 'Australia', 'Australia',
        '2024-03-18', 0, 0,
        'Navigating the digital cosmos, I guide you through pixels and code, unlocking doors to worlds where heroes rise and villains fall.',
        'Science Fiction, Superhero, Thriller'),
       ('LaughingGal', 'Sophia', 'Martinez', '1995-09-22', 'sophia@example.com', 'SmilingSophie', 'Mexico',
        'North America', '2024-03-12', 0, 0,
        'With a laugh as infectious as the stories I share, I journey through the comedic landscape, sprinkling humor into every panel.',
        'Comedy, Anthology'),
       ('MysticScribe', 'Elijah', 'Chang', '1980-01-08', 'elijah@email.com', 'MagicInk', 'Canada', 'North America',
        '2024-03-20', 0, 0,
        'With ancient words and arcane ink, I conjure tales of magic and mystery, inviting you to explore realms beyond imagination.',
        'Fantasy, Adventure, Thriller'),
       ('GalacticHero', 'Luke', 'Williams', '1982-06-30', 'luke@example.com', 'HeroicLuke', 'United States',
        'North America', '2024-03-15', 0, 0,
        'In the vast expanse of the cosmos, I soar as a beacon of hope, defending the innocent and vanquishing the forces of darkness.',
        'Science Fiction, Superhero, Adventure'),
       ('EternalWanderer', 'Sofia', 'Kumar', '1998-03-17', 'sofia@email.com', 'WanderingSoul', 'India', 'Asia',
        '2024-03-08', 0, 0,
        'Boundless and free, I journey through realms unknown, seeking stories that stir the soul and ignite the imagination.',
        'Fantasy, Adventure, Thriller'),
       ('TechnoTales', 'Michael', 'Nguyen', '1991-08-19', 'michael@example.com', 'TechWizard', 'Canada',
        'North America', '2024-03-21', 0, 0,
        'Amidst the whir of machines and the glow of screens, I craft tales where technology blurs the line between reality and fiction.',
        'Science Fiction, Thriller, Adventure'),
       ('WhimsicalWizard', 'Olivia', 'Garcia', '1987-12-12', 'olivia@email.com', 'WhimsyWiz', 'Spain', 'Europe',
        '2024-03-16', 0, 0,
        'With a flick of my wand and a sprinkle of imagination, I conjure stories that shimmer with magic and wonder, inviting you to believe in the impossible.',
        'Fantasy, Adventure'),
       ('SpaceScribbler', 'Grace', 'Adams', '1993-05-25', 'grace@example.com', 'ScribbleSpace', 'Canada',
        'North America', '2024-03-10', 0, 0,
        'Lost in the vastness of the cosmos, I write tales that traverse galaxies, weaving together science and fiction in an endless tapestry of exploration.',
        'Science Fiction, Adventure, Thriller'),
       ('LaughingLeopard', 'Max', 'Thompson', '1984-09-18', 'max@email.com', 'LeopardLaughs', 'Australia', 'Australia',
        '2024-03-05', 0, 0,
        'With a roar of laughter, I prowl through the jungle of humor, hunting down jokes and gags to share with the world.',
        'Comedy, Anthology'),
       ('PixelPioneer', 'Liam', 'Roberts', '1990-02-14', 'liam@example.com', 'PixelPioneer', 'United States',
        'North America', '2024-03-18', 0, 0,
        'Charting new frontiers in the digital realm, I blaze trails through pixels and code, creating worlds where imagination knows no bounds.',
        'Science Fiction, Fantasy, Superhero'),
       ('MysticDreamer', 'Isabella', 'Brown', '1997-07-30', 'isabella@email.com', 'Dreamer123', 'United Kingdom',
        'Europe', '2024-03-12', 0, 0,
        'With eyes closed and mind open, I drift through realms of magic and mystery, painting visions of wonder with the brush of imagination.',
        'Fantasy, Adventure, Thriller'),
       ('InfiniteImaginator', 'William', 'Davis', '1989-11-05', 'william@example.com', 'Imaginator', 'United States',
        'North America', '2024-03-20', 0, 0,
        'Infinite possibilities unfold before me as I traverse the landscape of imagination, crafting stories that defy reality and ignite the soul.',
        'Fantasy, Science Fiction, Adventure'),
       ('GalacticGuardian1', 'Emma', 'Wilson', '1994-03-17', 'emma@email.com', 'GuardianEmma', 'Canada',
        'North America', '2024-03-15', 0, 0,
        'With courage as my shield and justice as my sword, I stand as a sentinel against the darkness that threatens the cosmos, defending all that is good and true.',
        'Science Fiction, Superhero, Adventure'),
       ('EtherealEnchanter', 'Daniel', 'Martinez', '1983-06-22', 'daniel@example.com', 'EnchanterDan', 'Spain',
        'Europe', '2024-03-08', 0, 0,
        'In the realm where dreams and reality intertwine, I wield the power of enchantment, crafting stories that shimmer with magic and mystery.',
        'Fantasy, Adventure, Thriller'),
       ('TechnoTinkerer', 'Sophie', 'Brown', '1996-08-19', 'sophie@email.com', 'TechTinker', 'United States',
        'North America', '2024-03-21', 0, 0,
        'With gears and gadgets at my fingertips, I tinker with the fabric of reality, weaving tales where technology blurs the line between the possible and the impossible.',
        'Science Fiction, Thriller, Superhero'),
       ('WhimsicalWanderer', 'Ethan', 'Liu', '1986-12-09', 'ethan@example.com', 'WhimsyEthan', 'Australia', 'Australia',
        '2024-03-16', 0, 0,
        'With a skip in my step and a twinkle in my eye, I wander through whimsical worlds of wonder, inviting you to join me on adventures beyond imagination.',
        'Fantasy, Adventure'),
       ('EpicExplorer', 'Ava', 'Chen', '1991-04-03', 'ava@email.com', 'EpicAva', 'Canada', 'North America',
        '2024-03-21', 0, 0,
        'Venturing into the unknown, I seek stories that transcend time and space, weaving epic tales of heroism and adventure that echo through the ages.',
        'Fantasy, Science Fiction, Adventure'),
       ('CyberNinja', 'Kenji', 'Tanaka', '1993-05-20', 'kenji@example.com', 'NinjaMaster', 'Japan', 'Asia',
        '2024-03-19', 0, 0,
        'In the neon-lit streets of the cyber city, I move like a shadow, blending ancient artistry with futuristic tech, cutting through the digital darkness.',
        'Science Fiction, Thriller, Adventure'),
       ('LaughingLass', 'Hannah', 'Baker', '1984-09-08', 'hannah@email.com', 'JokerHannah', 'United States',
        'North America', '2024-03-14', 0, 0,
        'With a joke and a smile, I journey through the comedic landscape, bringing laughter to even the darkest corners of the comic world.',
        'Comedy, Anthology, Adventure'),
       ('GalacticGazer', 'Max', 'Chen', '1990-02-12', 'max@example.com', 'GalaxyExplorer', 'Canada', 'North America',
        '2024-03-17', 0, 0,
        'Lost among the stars, I chart courses through the cosmic expanse, exploring worlds unknown and unraveling the mysteries of the universe.',
        'Science Fiction, Adventure, Fantasy'),
       ('InkSorcerer', 'Liam', 'OConnor', '1986-06-25', 'liam@email.com', 'SorcererInk', 'Ireland', 'Europe',
        '2024-03-13', 0, 0,
        'With spells woven in ink and imagination, I conjure stories that dance on the edge of reality, inviting you to explore the realms of magic and mystery.',
        'Fantasy, Adventure, Thriller'),
       ('PixelPirate', 'Emma', 'Davis', '1994-11-10', 'emma@example.com', 'PixelPlunderer', 'Australia', 'Australia',
        '2024-03-11', 0, 0,
        'Sailing the digital seas, I plunder pixels and loot code, uncovering treasures of art and story hidden in the vast expanse of cyberspace.',
        'Science Fiction, Adventure, Thriller'),
       ('MangaMaster', 'Takashi', 'Nakamura', '1989-03-03', 'takashi@email.com', 'MangaSensei', 'Japan', 'Asia',
        '2024-03-09', 0, 0,
        'With pen in hand, I journey through the vibrant world of manga, crafting stories that blur the line between reality and fantasy, inviting you to explore the depths of imagination.',
        'Manga, Fantasy, Adventure'),
       ('CyberScribe', 'Elena', 'Vasiliev', '1997-07-28', 'elena@email.com', 'DigitalScribe', 'Russia', 'Europe',
        '2024-03-16', 0, 0,
        'In the digital archives of tomorrow, I write tales of bytes and pixels, where reality bends and cyberspace becomes a canvas for boundless imagination.',
        'Science Fiction, Thriller, Superhero'),
       ('ComedicChronicler', 'Charlie', 'Johnson', '1983-04-15', 'charlie@email.com', 'ChuckleWriter', 'United States',
        'North America', '2024-03-18', 0, 0,
        'With wit as sharp as my pen, I spin tales that tickle the funny bone and warm the heart, inviting you to laugh along on the journey of life.',
        'Comedy, Anthology, Adventure'),
       ('FantasyNavigator', 'Isabella', 'Romano', '1991-01-02', 'isabela@email.com', 'DreamWeaver', 'Italy', 'Europe',
        '2024-03-20', 0, 0,
        'Guided by dreams and fueled by imagination, I navigate the realms of fantasy, charting courses through lands of magic and wonder.',
        'Fantasy, Adventure, Anthology'),
       ('GalacticGuardian', 'Alex', 'Garcia', '1981-08-07', 'alex@example.com', 'GuardianAlex', 'United States',
        'North America', '2024-03-21', 0, 0,
        'A shield against the darkness of the cosmos, I stand as a guardian of justice, defending the innocent and upholding the light of hope across the stars.',
        'Science Fiction, Superhero, Adventure'),
       ('InkNinja', 'Ryu', 'Tanaka', '1996-03-12', 'ryu@example.com', 'NinjaInk', 'Japan', 'Asia', '2024-03-19', 0, 0,
        'Silent as the night, I wield my pen like a blade, carving tales of honor and betrayal in the shadows of the ink-stained page.',
        'Fantasy, Adventure, Thriller'),
       ('JovialJester', 'Freya', 'Olsen', '1987-11-25', 'freya@email.com', 'LaughingFreya', 'Norway', 'Europe',
        '2024-03-14', 0, 0,
        'With a jest and a grin, I traverse the comedic landscape, spinning yarns that tickle the funny bone and warm the heart.',
        'Comedy, Anthology, Adventure'),
       ('AstroAdventurer', 'Leo', 'Choi', '1993-09-08', 'leo@example.com', 'StellarExplorer', 'South Korea', 'Asia',
        '2024-03-17', 0, 0,
        'Bound for the stars, I embark on cosmic journeys, exploring the wonders of the universe and unraveling the mysteries of deep space.',
        'Science Fiction, Adventure, Fantasy'),
       ('ArcaneScribe', 'Amara', 'Martinez', '1988-05-20', 'amara@email.com', 'MysticWriter', 'Mexico', 'North America',
        '2024-03-13', 0, 0,
        'With ancient quill and enchanted parchment, I weave spells of storytelling magic, conjuring worlds of wonder and mystery.',
        'Fantasy, Adventure, Thriller'),
       ('CyberCaptain', 'Ethan', 'Davis', '1995-01-30', 'ethn@example.com', 'DigitalHero', 'United States',
        'North America', '2024-03-11', 0, 0,
        'Navigating the digital seas, I commandeer the virtual ship, charting courses through the cyberspace frontier.',
        'Science Fiction, Superhero, Thriller'),
       ('MangaMaestro', 'Yuki', 'Sato', '1989-07-03', 'yuki@email.com', 'MaestroYuki', 'Japan', 'Asia', '2024-03-09', 0,
        0,
        'With ink and imagination, I orchestrate tales of manga mastery, guiding readers through worlds where dreams and reality collide.',
        'Manga, Fantasy, Adventure'),
       ('TechnoTeller', 'Nikolai', 'Ivanov', '1998-12-28', 'nikolai@email.com', 'CodeScribe', 'Russia', 'Europe',
        '2024-03-16', 0, 0,
        'In the realm of ones and zeros, I spin tales of digital intrigue, where algorithms dance and virtual worlds come to life.',
        'Science Fiction, Thriller, Superhero'),
       ('WhimsyWordsmith', 'Aurora', 'Moreno', '1983-10-15', 'aurora@email.com', 'EnchantedInk', 'Spain', 'Europe',
        '2024-03-18', 0, 0,
        'With a touch of whimsy and a sprinkle of magic, I craft stories that sparkle with wonder and delight, inviting you to dream with eyes wide open.',
        'Fantasy, Adventure, Anthology'),
       ('DreamWeaver', 'Luca', 'Rossi', '1991-04-02', 'luca@email.com', 'DreamerLuca', 'Italy', 'Europe', '2024-03-20',
        0, 0,
        'In the tapestry of dreams, I am the weaver, spinning threads of imagination into tales that transport you to realms of enchantment and wonder.',
        'Fantasy, Adventure, Thriller'),
       ('GalacticGuard', 'Aiden', 'Gordon', '1980-08-25', 'aiden@example.com', 'GuardianAiden', 'United States',
        'North America', '2024-03-21', 0, 0,
        'A sentinel of the stars, I stand watch over the celestial expanse, defending the galaxy from threats beyond imagination.',
        'Science Fiction, Superhero, Adventure'),
       ('NeonNebula', 'Luna', 'Hernandez', '1994-06-18', 'luna@email.com', 'StellarLuna', 'Mexico', 'North America',
        '2024-03-22', 0, 0,
        'Amidst the neon glow of the cyber city, I navigate the digital cosmos, exploring realms of light and shadow where dreams become reality.',
        'Science Fiction, Cyberpunk, Thriller'),
       ('WhimsicalWordsmith', 'Eva', 'Andersen', '1986-09-03', 'eva@example.com', 'EnchantedWriter', 'Denmark',
        'Europe', '2024-03-23', 0, 0,
        'With whimsy in my heart and magic in my pen, I craft tales that dance on the edge of imagination, inviting you to journey into worlds of wonder.',
        'Fantasy, Adventure, Anthology'),
       ('PixelPioneer1', 'Oliver', 'Smith', '1990-02-28', 'oliver@email.com', 'DigitalTrailblazer', 'Canada',
        'North America', '2024-03-24', 0, 0,
        'In the frontier of pixels and code, I blaze trails through the digital wilderness, uncovering hidden treasures of art and narrative.',
        'Science Fiction, Adventure, Thriller'),
       ('LaughingLyricist', 'Nora', 'Kumar', '1997-12-10', 'nora@example.com', 'JollyNora', 'India', 'Asia',
        '2024-03-25', 0, 0,
        'With laughter as my muse and humor as my ink, I compose stories that sing with joy and resonate with the rhythm of life.',
        'Comedy, Anthology, Adventure'),
       ('ComicCrusader', 'Maxwell', 'Chang', '1983-04-30', 'maxwell@example.com', 'HeroicMax', 'United States',
        'North America', '2024-03-26', 0, 0,
        'Clad in the armor of righteousness, I stand as a guardian of truth and justice, fighting for the innocent and vanquishing evil wherever it lurks.',
        'Superhero, Action, Adventure');

-- Next we add comic book objects
INSERT INTO longbox_schema.comic_book(series_title, author, artist, genres, description, number_of_issues, publisher,
                                      year_published, date_added)
VALUES ('Zot!', 'Scott McCloud', 'Scott McCloud',
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

       ('Vampire Doll', 'Erika Kari', 'Erika Kari',
        'Vampire, Comedy, Manga',
        'Empty', 32, 'TokyoPop', 2006, '2024-03-24'),


       ('Full Metal Panic!', 'Shouji Gatou', 'Retsu Tateo',
        'Science Fiction, Military, School Life, Comedy, Manga',
        'Empty', 13, 'ADV', 2004, '2024-03-24'),


       ('American Flagg!', 'Howard Chaykin', 'Howard Chaykin',
        'Science Fiction, Political, Action, Satire',
        'Empty', 50, 'First Comics', 1983, '2024-03-24'),


       ('Gunsmith Cats', 'Kenichi Sonada', 'Kenichi Sonada',
        'Gunslinger, Girls With Guns, Action, Comedy, Manga',
        'Empty', 32, 'Dark Horse', 1998, '2024-03-24'),


       ('Bastard!! Heavy Metal Dark Fantasy', 'Kazushi Hagiwara', 'Kazushi Hagiwara',
        'Dark Fantasy, Fantasy, Action, Manga',
        'Empty', 26, 'Viz', 1999, '2024-03-24'),


       ('Robotech The Macross Saga', 'Carl Macek', 'Neil Vokes',
        'Science Fiction, Mecha, Space Opera',
        'Empty', 52, 'Comico', 1982, '2024-03-24'),


       ('Slayers', 'Hajime Kanzaka', 'Rui Araizumi',
        'Fantasy, Adventure, Comedy, Satire, Manga',
        'Empty', 5, 'CPM', 1995, '2024-03-24'),


       ('Marmalade Bot', 'Wataru Yoshizumi', 'Wataru Yoshizumi',
        'Slice of Life, Manga, Drama',
        'Empty', 5, 'TokyoPop', 2003, '2024-03-24'),


       ('Daragon Knights', 'Mineko Ohkami', 'Mineko Ohkami',
        'Fantasy, Drama, Manga',
        'Empty', 6, 'TokyoPop', 2002, '2024-03-24'),


       ('Cyber 7 (Vol 1)', 'Shuho Itashi', 'Shuho Itashi',
        'Fantasy, Manga, Science Fiction',
        'Empty', 7, 'Eclipse', 1988, '2024-03-24'),


       ('Cyber 7 (Vol 2)', 'Shuho Itashi', 'Shuho Itashi',
        'Fantasy, Manga, Science Fiction',
        'Empty', 6, 'Eclipse', 1989, '2024-03-24'),


       ('Record of Lodoss War The Lady Pharis', 'Ryo Mizuno', 'Akihiro Yamada',
        'Fantasy, Adventure, Manga',
        'Empty', 8, 'CPM', 1999, '2024-03-24'),


       ('Record of Lodoss War The Grey Witch', 'Ryo Mizuno', 'Akihiro Yamada',
        'Fantasy, Adventure, Manga',
        'Empty', 7, 'CPM', 1999, '2024-03-24'),


       ('Record of Lodoess War Deeedlits Tale', 'Ryo Mizuno', 'Akihiro Yamada',
        'Fantasy, Adventure, Manga',
        'Empty', 8, 'CPM', 1999, '2024-03-24'),


       ('DNAgents', 'Mark Evanier', 'Mark Evanier',
        'Science Fiction, Superhero',
        'Empty', 34, 'Eclipse', 1983, '2024-03-24'),


       ('Poison Elves', 'Drew Hayes', 'Drew Hayes',
        'Science Fiction, Drama, Violent',
        'Empty', 34, 'Sirius', 1995, '2024-03-24'),


       ('The Spirit', 'Will Eisner', 'Will Eisner',
        'Superhero, Comedy',
        'Empty', 32, 'Kitchen Sink', 1981, '2024-03-24'),


       ('Black Kiss', 'Howard Chaykin', 'Howard Chaykin',
        'Drama',
        'Empty', 19, 'Vortex', 1998, '2024-03-24'),


       ('Total War', 'Wally Wood', 'Wally Wood',
        'Science Fiction, Military, War',
        'Empty', 8, 'Gold Key', 1965, '2024-03-24'),


       ('Troll Lords', 'Scott Beaderstadt', 'Paul Fricke',
        'Science Fiction, Comedy',
        'Empty', 19, 'Tru Studios', 1995, '2024-03-24'),


       ('Spy Boy', 'Peter David', 'Pop Mhan',
        'Science Fiction, Adventure, Comedy',
        'Empty', 13, 'Dark Horse', 2006, '2024-03-24'),


       ('Crimson Skies', 'Microsoft', 'Howard Chaykin',
        'Action, ADventure',
        'Empty', 1, 'Top Cow', 2004, '2024-03-24'),


       ('Initial D', 'Shuichi Shigeno', 'Shuichi Shigeno',
        'Racing, Drama, Manga',
        'Empty', 105, 'Kodansha', 1995, '2024-03-24'),


       ('Groo the Wanderer', 'Sergio Aragones', 'Sergio Aragones',
        'Fantasy, Comedy',
        'Empty', 8, 'Pacific Comics', 1982, '2024-02-25');

-- Next we add some comments
INSERT INTO longbox_schema."comments"(message, comment_date, comic_book_id, user_id, user_name)
VALUES ('Wow, the art in this comic is absolutely breathtaking! The attention to detail and vibrant colors bring the characters to life in a way thats truly mesmerizing.',
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
       ('Just started comic #14, and its already becoming one of my favourites. The characters are so relatable!',
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
        '2024-02-20', 21, 4, 'ahan'),
       ('This comic has such a whimsical charm to it! Each page feels like a journey through a fairy tale.',
        '2024-03-14', 43, 43, 'WhimsyWordsmith'),
       ('This comic is a masterpiece! The storyline is gripping and the artwork is stunning.',
        '2024-03-15', 25, 10, 'LaughingGal'),
       ('I love the way this comic combines humor with thrilling action! It''s a rollercoaster ride from start to finish.',
        '2024-03-18', 16, 22, 'EtherealEnchanter'),
       ('The characters in this comic are so relatable, I feel like I know them personally!',
        '2024-03-10', 53, 24, 'WhimsicalWanderer'),
       ('The world-building in this comic is phenomenal. It feels like stepping into a whole new universe!',
        '2024-03-20', 11, 3, 'Phoenix'),
       ('Im blown away by the creativity in this comic! Each panel is like a work of art.',
        '2024-03-16', 34, 43, 'WhimsyWordsmith'),
       ('The action scenes in this comic are intense! I can''t wait to see what happens next.',
        '2024-03-22', 29, 26, 'CyberNinja'),
       ('This comic has me on the edge of my seat with its suspenseful plot twists!',
        '2024-03-25', 6, 5, 'naha'),
       ('Im loving the humor in this comic! It never fails to make me laugh out loud.',
        '2024-03-19', 18, 33, 'ComedicChronicler'),
       ('The art style in this comic is breathtaking! It adds so much depth to the storytelling.',
        '2024-03-24', 42, 48, 'PixelPioneer1'),
       ('This comic is a thrilling ride from start to finish! The characters are compelling and the action is non-stop.',
        '2024-03-21', 4, 50, 'ComicCrusader'),
       ('The art style of this comic is truly unique! It adds so much depth to the story.',
        '2024-03-15', 22, 11, 'MysticScribe'),
       ('I can''t get enough of the humor in this comic! It brightens my day every time I read it.',
        '2024-03-16', 12, 36, 'InkNinja'),
       ('This comic is a masterpiece! The storytelling is so captivating and emotionally resonant.',
        '2024-03-17', 38, 26, 'CyberNinja'),
       ('The fantasy world in this comic is breathtaking! It''s like stepping into a dream.',
        '2024-03-18', 13, 1, 'Always_Scheming'),
       ('I admire the creativity of this comic! It''s unlike anything I''ve ever read before.',
        '2024-03-19', 25, 2, 'Always_Throwing'),
       ('The characters in this comic feel like old friends! I can''t wait to see what adventures they go on next.',
        '2024-03-20', 38, 9, 'PixelPilot'),
       ('This comic is a rollercoaster of emotions! It keeps me on the edge of my seat with every twist and turn.',
        '2024-03-21', 11, 34, 'FantasyNavigator'),
       ('I''m fascinated by the technology in this comic! It feels so ahead of its time.',
        '2024-03-22', 36, 16, 'SpaceScribbler'),
       ('The action sequences in this comic are so thrilling! It''s like watching an epic movie.',
        '2024-03-23', 14, 29, 'InkSorcerer'),
       ('I''m captivated by the world-building in this comic! It feels so rich and immersive.',
        '2024-03-24', 41, 41, 'MangaMaestro'),
       ('The artwork in this comic is stunning! Each panel feels like a work of art.',
        '2024-03-25', 19, 31, 'MangaMaster'),
       ('I''m hooked on the storyline of this comic! It''s full of unexpected twists and turns.',
        '2024-03-26', 5, 27, 'LaughingLass'),
       ('This comic never fails to make me laugh! The humor is top-notch.',
        '2024-03-13', 16, 39, 'ArcaneScribe'),
       ('The character development in this comic is superb! Each character feels so real and relatable.',
        '2024-03-15', 32, 30, 'PixelPirate'),
       ('I''m blown away by the world-building in this comic! It''s so intricate and well thought out.',
        '2024-04-24', 20, 13, 'EternalWanderer'),
       ('This comic is a true epic! The scale of the story is massive and awe-inspiring.',
        '2024-02-29', 45, 25, 'EpicExplorer'),
       ('I''m captivated by the characters in this comic! Each one has such a distinct personality.',
        '2024-03-01', 10, 38, 'AstroAdventurer'),
       ('The action scenes in this comic are adrenaline-pumping! It''s like being in the middle of a blockbuster movie.',
        '2024-03-02', 27, 20, 'InfiniteImaginator'),
       ('I''m intrigued by the mystery in this comic! It keeps me guessing with every page.',
        '2024-03-03', 55, 33, 'ComedicChronicler'),
       ('This comic is a work of art! The attention to detail is incredible.',
        '2024-03-04', 8, 12, 'GalacticHero'),
       ('This comic has such a unique art style! It really stands out from the crowd.',
        '2024-03-05', 14, 21, 'GalacticGuardian1'),
       ('The characters in this comic are so well-written! Each one feels fleshed out and three-dimensional.',
        '2024-03-06', 28, 14, 'TechnoTales'),
       ('I love the dialogue in this comic! It''s witty and clever.',
        '2024-03-07', 36, 10, 'LaughingGal'),
       ('The action scenes in this comic are intense! It keeps me on the edge of my seat.',
        '2024-03-08', 19, 22, 'EtherealEnchanter'),
       ('This comic is a masterpiece! It''s beautifully crafted in every aspect.',
        '2024-03-09', 35, 8, 'InkMaster'),
       ('I''m addicted to this comic! I can''t get enough of the story.',
        '2024-03-10', 9, 19, 'MysticDreamer'),
       ('The world-building in this comic is phenomenal! It feels so immersive.',
        '2024-03-11', 2, 32, 'CyberScribe'),
       ('I''m amazed by the attention to detail in this comic! Every little thing is accounted for.',
        '2024-03-12', 41, 17, 'LaughingLeopard'),
       ('This comic is a rollercoaster of emotions! It takes me on a journey with every issue.',
        '2024-03-13', 11, 42, 'TechnoTeller'),
       ('I''m blown away by the artwork in this comic! It''s like nothing I''ve ever seen before.',
        '2024-03-14', 6, 5, 'naha');

-- Adding data into finished list

INSERT INTO longbox_schema."comic_book_finished_list"(user_id, comic_book_id, date_finished)
VALUES (6, 1, '2024-03-26 17:13:23.198'),
       (6, 14, '2024-03-26 17:13:32.996'),
       (6, 19, '2024-03-26 17:13:36.119'),
       (6, 26, '2024-03-26 17:14:48.968'),
       (6, 29, '2024-03-26 17:14:51.684'),
       (6, 33, '2024-03-26 17:14:54.962'),
       (6, 39, '2024-03-26 17:14:59.139'),
       (6, 44, '2024-03-26 17:15:02.896'),
       (6, 53, '2024-03-26 17:15:14.821'),
       (6, 56, '2024-03-26 17:15:18.326'),
       (6, 58, '2024-03-26 17:15:21.833'),
       (6, 11, '2024-03-26 17:15:29.387'),
       (1, 2, '2024-03-26 17:16:00.624'),
       (1, 31, '2024-03-26 17:16:08.208'),
       (1, 33, '2024-03-26 17:16:23.441'),
       (1, 46, '2024-03-26 17:16:33.996'),
       (1, 50, '2024-03-26 17:16:37.743'),
       (1, 53, '2024-03-26 17:16:40.875'),
       (1, 60, '2024-03-26 17:16:52.277'),
       (2, 12, '2024-03-26 17:17:38.11'),
       (2, 27, '2024-03-26 17:17:41.582'),
       (2, 54, '2024-03-26 17:18:08.921'),
       (2, 38, '2024-03-26 17:18:11.64'),
       (2, 30, '2024-03-26 17:18:26.478'),
       (2, 41, '2024-03-26 17:18:29.969'),
       (7, 6, '2024-03-26 14:00:00'),
       (15, 22, '2024-03-26 15:30:00'),
       (30, 43, '2024-03-26 16:45:00'),
       (42, 8, '2024-03-26 17:15:00'),
       (20, 14, '2024-03-26 18:00:00'),
       (3, 32, '2024-03-26 19:20:00'),
       (12, 55, '2024-03-26 20:10:00'),
       (25, 11, '2024-03-26 21:00:00'),
       (36, 2, '2024-03-26 22:30:00'),
       (48, 49, '2024-03-26 23:45:00'),
       (14, 7, '2024-03-27 18:00:00'),
       (26, 34, '2024-03-27 19:15:00'),
       (38, 52, '2024-03-27 20:30:00'),
       (5, 16, '2024-03-27 21:45:00'),
       (11, 25, '2024-03-27 22:30:00'),
       (23, 37, '2024-03-27 23:20:00'),
       (36, 6, '2024-03-28 00:45:00'),
       (48, 20, '2024-03-28 01:55:00'),
       (3, 31, '2024-03-28 02:10:00'),
       (19, 58, '2024-03-28 03:25:00'),
       (11, 16, '2024-03-29 18:00:00'),
       (23, 30, '2024-03-29 19:15:00'),
       (36, 46, '2024-03-29 20:30:00'),
       (8, 8, '2024-03-29 21:45:00'),
       (16, 25, '2024-03-29 22:30:00'),
       (29, 39, '2024-03-29 23:20:00'),
       (41, 2, '2024-03-30 00:45:00'),
       (4, 18, '2024-03-30 01:55:00'),
       (18, 53, '2024-03-30 02:10:00'),
       (31, 6, '2024-03-30 03:25:00'),
       (14, 28, '2024-03-30 08:00:00'),
       (26, 36, '2024-03-30 09:15:00'),
       (39, 51, '2024-03-30 10:30:00'),
       (7, 10, '2024-03-30 11:45:00'),
       (17, 22, '2024-03-30 12:30:00'),
       (30, 39, '2024-03-30 13:20:00'),
       (42, 5, '2024-03-30 14:45:00'),
       (5, 21, '2024-03-30 15:55:00'),
       (12, 17, '2024-03-31 08:00:00'),
       (24, 31, '2024-03-31 09:15:00'),
       (37, 48, '2024-03-31 10:30:00'),
       (6, 8, '2024-03-31 11:45:00'),
       (15, 23, '2024-03-31 12:30:00'),
       (28, 37, '2024-03-31 13:20:00'),
       (7, 20, '2024-03-31 15:55:00'),
       (19, 54, '2024-03-31 16:10:00'),
       (32, 5, '2024-03-31 17:25:00'),
       (8, 16, '2024-04-01 08:00:00'),
       (21, 29, '2024-04-01 09:15:00'),
       (34, 45, '2024-04-01 10:30:00'),
       (19, 57, '2024-04-01 16:10:00'),
       (32, 7, '2024-04-01 17:25:00'),
       (14, 27, '2024-04-01 08:00:00'),
       (7, 9, '2024-04-01 11:45:00'),
       (17, 21, '2024-04-01 12:30:00'),
       (30, 38, '2024-04-01 13:20:00'),
       (42, 4, '2024-04-01 14:45:00'),
       (5, 20, '2024-04-01 15:55:00'),
       (19, 56, '2024-04-01 16:10:00'),
       (32, 6, '2024-04-01 17:25:00'),
       (10, 18, '2024-04-01 08:00:00'),
       (22, 29, '2024-04-01 09:15:00'),
       (35, 47, '2024-04-01 10:30:00'),
       (8, 12, '2024-04-01 11:45:00'),
       (17, 26, '2024-04-01 12:30:00'),
       (28, 38, '2024-04-01 13:20:00'),
       (40, 4, '2024-04-01 14:45:00'),
       (6, 21, '2024-04-01 15:55:00'),
       (19, 50, '2024-04-01 16:10:00'),
       (32, 9, '2024-04-01 17:25:00'),
       (9, 15, '2024-04-01 08:00:00'),
       (21, 28, '2024-04-01 09:15:00'),
       (34, 47, '2024-04-01 10:30:00'),
       (7, 11, '2024-04-01 11:45:00'),
       (17, 25, '2024-04-01 12:30:00'),
       (42, 3, '2024-04-01 14:45:00'),
       (5, 19, '2024-04-01 15:55:00');

-- Adding data into reading list

INSERT INTO longbox_schema."comic_book_reading_list"(user_id, comic_book_id, date_started)
VALUES (6, 1, '2024-03-26 17:13:24.579'),
       (6, 5, '2024-03-26 17:13:28.52'),
       (6, 19, '2024-03-26 17:13:35.468'),
       (6, 22, '2024-03-26 17:13:38.689'),
       (6, 23, '2024-03-26 17:14:45.435'),
       (6, 26, '2024-03-26 17:14:48.763'),
       (6, 29, '2024-03-26 17:14:51.905'),
       (6, 33, '2024-03-26 17:14:55.173'),
       (6, 39, '2024-03-26 17:14:59.323'),
       (6, 51, '2024-03-26 17:15:05.969'),
       (6, 46, '2024-03-26 17:15:12.047'),
       (6, 56, '2024-03-26 17:15:18.01'),
       (6, 58, '2024-03-26 17:15:21.185'),
       (6, 60, '2024-03-26 17:15:24.839'),
       (1, 2, '2024-03-26 17:16:00.871'),
       (1, 14, '2024-03-26 17:16:04.187'),
       (1, 35, '2024-03-26 17:16:26.749'),
       (1, 42, '2024-03-26 17:16:30.393'),
       (1, 56, '2024-03-26 17:16:43.758'),
       (1, 58, '2024-03-26 17:16:48.941'),
       (1, 38, '2024-03-26 17:16:56.238'),
       (1, 18, '2024-03-26 17:17:01.362'),
       (1, 7, '2024-03-26 17:17:05.644'),
       (1, 43, '2024-03-26 17:17:09.952'),
       (2, 2, '2024-03-26 17:17:28.782'),
       (2, 17, '2024-03-26 17:17:35.286'),
       (2, 20, '2024-03-26 17:17:44.219'),
       (2, 21, '2024-03-26 17:17:47.497'),
       (2, 35, '2024-03-26 17:17:50.767'),
       (2, 42, '2024-03-26 17:17:53.46'),
       (2, 48, '2024-03-26 17:17:57.782'),
       (2, 51, '2024-03-26 17:18:05.068'),
       (2, 46, '2024-03-26 17:18:14.894'),
       (2, 19, '2024-03-26 17:18:18.818'),
       (2, 8, '2024-03-26 17:18:22.522'),
       (2, 41, '2024-03-26 17:18:29.634'),
       (9, 15, '2024-03-28 08:00:00'),
       (21, 28, '2024-03-28 09:15:00'),
       (34, 45, '2024-03-28 10:30:00'),
       (7, 11, '2024-03-28 11:45:00'),
       (16, 24, '2024-03-28 12:30:00'),
       (29, 40, '2024-03-28 13:20:00'),
       (41, 3, '2024-03-28 14:45:00'),
       (4, 19, '2024-03-28 15:55:00'),
       (18, 54, '2024-03-28 16:10:00'),
       (31, 7, '2024-03-28 17:25:00'),
       (24, 32, '2024-03-28 19:15:00'),
       (37, 49, '2024-03-28 20:30:00'),
       (6, 10, '2024-03-28 21:45:00'),
       (27, 36, '2024-03-28 23:20:00'),
       (39, 5, '2024-03-29 00:45:00'),
       (8, 22, '2024-03-29 01:55:00'),
       (20, 57, '2024-03-29 02:10:00'),
       (13, 27, '2024-03-29 08:00:00'),
       (25, 35, '2024-03-29 09:15:00'),
       (38, 50, '2024-03-29 10:30:00'),
       (7, 9, '2024-03-29 11:45:00'),
       (17, 21, '2024-03-29 12:30:00'),
       (30, 38, '2024-03-29 13:20:00'),
       (42, 4, '2024-03-29 14:45:00'),
       (5, 20, '2024-03-29 15:55:00'),
       (19, 56, '2024-03-29 16:10:00'),
       (32, 6, '2024-03-29 17:25:00'),
       (13, 26, '2024-03-30 18:00:00'),
       (25, 34, '2024-03-30 19:15:00'),
       (38, 49, '2024-03-30 20:30:00'),
       (6, 9, '2024-03-30 21:45:00'),
       (15, 24, '2024-03-30 22:30:00'),
       (28, 38, '2024-03-30 23:20:00'),
       (40, 3, '2024-03-31 00:45:00'),
       (8, 19, '2024-03-31 01:55:00'),
       (20, 55, '2024-03-31 02:10:00'),
       (33, 8, '2024-03-31 03:25:00'),
       (12, 17, '2024-03-31 08:00:00'),
       (24, 31, '2024-03-31 09:15:00'),
       (37, 48, '2024-03-31 10:30:00'),
       (6, 8, '2024-03-31 11:45:00'),
       (15, 23, '2024-03-31 12:30:00'),
       (28, 37, '2024-03-31 13:20:00'),
       (40, 4, '2024-03-31 14:45:00'),
       (7, 20, '2024-03-31 15:55:00'),
       (19, 54, '2024-03-31 16:10:00'),
       (32, 5, '2024-03-31 17:25:00'),
       (8, 16, '2024-04-01 08:00:00'),
       (21, 29, '2024-04-01 09:15:00'),
       (19, 57, '2024-04-01 16:10:00'),
       (32, 7, '2024-04-01 17:25:00'),
       (14, 27, '2024-04-01 08:00:00'),
       (10, 18, '2024-04-01 08:00:00'),
       (22, 29, '2024-04-01 09:15:00'),
       (35, 47, '2024-04-01 10:30:00'),
       (8, 12, '2024-04-01 11:45:00'),
       (17, 26, '2024-04-01 12:30:00'),
       (6, 21, '2024-04-01 15:55:00'),
       (19, 50, '2024-04-01 16:10:00'),
       (32, 9, '2024-04-01 17:25:00'),
       (17, 25, '2024-04-01 12:30:00'),
       (42, 3, '2024-04-01 14:45:00'),
       (5, 19, '2024-04-01 15:55:00');

-- Adding data into favourites list

INSERT INTO longbox_schema."comic_book_favourites_list"(user_id, comic_book_id, date_added_user_list)
VALUES (6, 1, '2024-03-26 17:13:24.579'),
       (6, 5, '2024-03-26 17:13:28.52'),
       (6, 19, '2024-03-26 17:13:35.468'),
       (6, 23, '2024-03-26 17:14:45.435'),
       (6, 26, '2024-03-26 17:14:48.763'),
       (6, 39, '2024-03-26 17:14:59.323'),
       (6, 51, '2024-03-26 17:15:05.969'),
       (1, 2, '2024-03-26 17:16:00.871'),
       (1, 14, '2024-03-26 17:16:04.187'),
       (1, 35, '2024-03-26 17:16:26.749'),
       (1, 42, '2024-03-26 17:16:30.393'),
       (1, 56, '2024-03-26 17:16:43.758'),
       (1, 7, '2024-03-26 17:17:05.644'),
       (1, 43, '2024-03-26 17:17:09.952'),
       (2, 2, '2024-03-26 17:17:28.782'),
       (2, 17, '2024-03-26 17:17:35.286'),
       (2, 20, '2024-03-26 17:17:44.219'),
       (2, 8, '2024-03-26 17:18:22.522'),
       (2, 41, '2024-03-26 17:18:29.634'),
       (9, 15, '2024-03-28 08:00:00'),
       (21, 28, '2024-03-28 09:15:00'),
       (34, 45, '2024-03-28 10:30:00'),
       (7, 11, '2024-03-28 11:45:00'),
       (16, 24, '2024-03-28 12:30:00'),
       (29, 40, '2024-03-28 13:20:00'),
       (41, 3, '2024-03-28 14:45:00'),
       (4, 19, '2024-03-28 15:55:00'),
       (18, 54, '2024-03-28 16:10:00'),
       (31, 7, '2024-03-28 17:25:00'),
       (24, 32, '2024-03-28 19:15:00'),
       (37, 49, '2024-03-28 20:30:00'),
       (6, 10, '2024-03-28 21:45:00'),
       (27, 36, '2024-03-28 23:20:00'),
       (39, 5, '2024-03-29 00:45:00'),
       (8, 22, '2024-03-29 01:55:00'),
       (20, 57, '2024-03-29 02:10:00'),
       (13, 27, '2024-03-29 08:00:00'),
       (25, 35, '2024-03-29 09:15:00'),
       (38, 50, '2024-03-29 10:30:00'),
       (7, 9, '2024-03-29 11:45:00'),
       (17, 21, '2024-03-29 12:30:00'),
       (30, 38, '2024-03-29 13:20:00'),
       (42, 4, '2024-03-29 14:45:00'),
       (5, 20, '2024-03-29 15:55:00'),
       (19, 56, '2024-03-29 16:10:00'),
       (32, 6, '2024-03-29 17:25:00'),
       (13, 26, '2024-03-30 18:00:00'),
       (25, 34, '2024-03-30 19:15:00'),
       (38, 49, '2024-03-30 20:30:00'),
       (6, 9, '2024-03-30 21:45:00'),
       (15, 24, '2024-03-30 22:30:00'),
       (28, 38, '2024-03-30 23:20:00'),
       (40, 3, '2024-03-31 00:45:00'),
       (8, 19, '2024-03-31 01:55:00'),
       (20, 55, '2024-03-31 02:10:00'),
       (33, 8, '2024-03-31 03:25:00'),
       (12, 17, '2024-03-31 08:00:00'),
       (24, 31, '2024-03-31 09:15:00'),
       (37, 48, '2024-03-31 10:30:00'),
       (6, 8, '2024-03-31 11:45:00'),
       (15, 23, '2024-03-31 12:30:00'),
       (28, 37, '2024-03-31 13:20:00'),
       (40, 4, '2024-03-31 14:45:00'),
       (7, 20, '2024-03-31 15:55:00'),
       (19, 54, '2024-03-31 16:10:00'),
       (32, 5, '2024-03-31 17:25:00'),
       (8, 16, '2024-04-01 08:00:00'),
       (21, 29, '2024-04-01 09:15:00'),
       (19, 57, '2024-04-01 16:10:00'),
       (32, 7, '2024-04-01 17:25:00'),
       (14, 27, '2024-04-01 08:00:00'),
       (10, 18, '2024-04-01 08:00:00'),
       (22, 29, '2024-04-01 09:15:00'),
       (35, 47, '2024-04-01 10:30:00'),
       (8, 12, '2024-04-01 11:45:00'),
       (17, 26, '2024-04-01 12:30:00'),
       (6, 21, '2024-04-01 15:55:00'),
       (19, 50, '2024-04-01 16:10:00'),
       (32, 9, '2024-04-01 17:25:00'),
       (17, 25, '2024-04-01 12:30:00'),
       (42, 3, '2024-04-01 14:45:00'),
       (5, 19, '2024-04-01 15:55:00');