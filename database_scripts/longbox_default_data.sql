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
     'Adventure, Post-Apocalyptic, Gunslinger',
     'Empty', 3, 'Eclipse', 1987, '2024-02-15'),

    ('Hellhounds Panzer Cops', 'Mamoru Oshii', 'Kamui Fujiwara',
     'Military, Police, Adventure, Dystopian,Manga',
     'Empty', 6, 'Dark Horse', 1994, '2024-02-15'),

    ('Jon Sable Freelance (1983)', 'Mike Grell', 'Mike Grell',
     'Action, Adventure, Crime, Vigilantes',
     'Empty', 56, 'First Comics', 1983, '2024-02-15'),

    ('Chronicles of Corum', 'Mike Baron', 'Mike Mignola',
     'Action, Adventure, Sword and Socery',
     'Empty', 12, 'First Comics', 1987, '2024-02-15'),

    ('Drakuun', 'Johji Manabe', 'Johnji Manabe',
     'Adventure, Sword and Socery, Comedy, Manga',
     'Empty', 24, 'Dark Horse', 1997, '2024-02-15'),

    ('Sojourn', 'Ron Marz', 'Greg Land',
     'Action, Adventure, Sword and Sorcery',
     'Empty', 34, 'Crossgen', 2001, '2024-02-15');

--Template to batchload more comic book objects

/*  ('', '', '',
    ',,,,,,'
    'Empty', , '', , '2024-02-15')	*/


