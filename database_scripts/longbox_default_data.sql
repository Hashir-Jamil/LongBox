/*
* This script can be used to insert user test data to batch load a database for this project
* You can comment out individual lines if that row was already added manually by you in the process of developing and testing your stories
*/


-- First we add user objects

INSERT INTO longbox_schema."user"(
    user_name, first_name, last_name, dob, email, password, country, join_date, comics_reading, comics_finished)
VALUES
    ('Always_Scheming', 'John', 'Smith', '1990-12-1', 'email@domain.com', 'Always_Scheming', 'Canada', '2024-02-21', 0, 0),
    ('Always_Throwing', 'Neo', 'Anderson', '3829-02-01', 'address@provider.ca', 'Always_Throwing', 'Indonesia', '2024-02-14 12:28:42', 0, 0),
    ('Phoenix', 'Stan', 'Lee', '3900-05-31', '123fake@nowhere.org', 'Phoenix', 'United Kingdom', '2024-02-14 12:42:43', 0, 0),
    ('ahan', 'Ahan', 'Bhargava', '2003-02-10', 'ahan@email.com', 'Password!1', 'India', '2024-02-15 15:09:10', 0, 0);

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
     'Empty', 34, 'Crossgen', 2001, '2024-02-15')
    
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
    'Empty', 8, 'Pacific Comics', 1982, '2024-02-25);
    

--Template to batchload more comic book objects

/*  ('', '', '',
    ',,,,,,'
    'Empty', , '', , '2024-02-15')	*/


