CREATE SCHEMA IF NOT EXISTS longbox_schema;
SET SEARCH_PATH = longbox_schema;

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
    "comics_finished" integer
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
    "date_added" date
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

ALTER TABLE "comic_book_favorites_list" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "comic_book_favorites_list" ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");

ALTER TABLE "comments" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "comments" ADD FOREIGN KEY ("comic_book_id") REFERENCES "comic_book" ("id");