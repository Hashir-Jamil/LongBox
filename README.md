# LongBox ITR03 Build

Your digital solution for archiving and preserving comic books. Developed by passionate fans, this open-source desktop app offers concise documentation for out-of-print, independent, and mainstream comics. Addressing the decline in sales, LongBox provides a user-friendly platform for organizing and archiving collections. With web application plans, it aims to be a central hub for enthusiasts. Users can create profiles, submit comics, and enjoy a recommendation system. Built from scratch, LongBox prioritizes simplicity without relying on existing projects.

## System 

This project uses a 3-layer software architecture. The system is built around the Java Swing framework and behaves as a model view controller application in conformance to the oracle documentation for Swing applications. A sketch of it is:

![SystemDiagram](design_documents/diagrams/architecture_diagram_dark_mode.png)

## Getting Started

### Prerequisites

- The project is built on Java 19.
- The project uses Gradle as the build system.
- The project uses [PostgreSQL 16](https://www.postgresql.org/) as the database.

### Installing

1. Clone the [GitHub Repository](https://github.com/Hashir-Jamil/LongBox) delivery-2 branch or download the source for Delivery 2 release.
2. If using eclipse, once the project has been cloned, gradle nature needs to be added to the project. [How to configure gradle in eclipse](https://www.vogella.com/tutorials/EclipseGradle/article.html#add-gradle-support-to-existing-eclipse-project).
3. To use the database, we need to install [pgAdmin4](https://www.pgadmin.org/download/). In pgAdmin, [create a new database](https://www.tutorialsteacher.com/postgresql/create-database#:~:text=Create%20Database%20using%20pgAdmin&text=Open%20pgAdmin%20and%20right%2Dclick,Database…%20%2C%20as%20shown%20below.&text=This%20will%20open%20Create%20–%20Database,be%20the%20owner%20by%20default). The name of database should be ***longbox_db***.
4. After creating the database, [run](https://support.spiresystems.com/support/solutions/articles/13000015301-executing-a-sql-query-using-pgadmin) the [*create_longbox_db*](database_scripts/create_longbox_db.sql) in the database_scripts directory. This will set up the tables and populate them with the data required for this project.
5. To ensure the database server communicates with the project, go to the file [hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml) configuration file and ensure the connection.url property matches the one for your personal machine's PostgreSQL installation and the database made above.

### Build

To build the project run the command:
> gradle clean build

To run the application run the command:
> gradle run 

### Testing

The project uses JUnit and gradle integration for unit testing and integration testing of system code. E2E testing is described in the [Code Review Document](design_documents/code_review/EECS2311-Z-Team3-TakeHomeAssignment.pdf) and organized by feature within the document. 

To execute the full JUnit test suite run the command:
> gradle test

To view the coverage of the tests navigate to the [JaCoCo coverage report](build/jacoco-reports/index.html). This must be done after running the test suite.

If using Eclipse, they can all be run as follows: 
> right click on src/test/java > RunAs > JUnit Test.

These tests make sure that the business logic that drives our system is working correctly and as expected.We have done a thorough testing such that all the possible edge cases are covered in the test.

## Launching in Eclipse IDE

To launch the project:
>Run the **Main.java** in *src/main/java*.

### Build System

[Gradle](https://kotlinlang.org/docs/gradle.html) - Dependency Management

## Features

### Login

This is the first page that the user of the app sees.

![Login](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f301d4e4-9725-477c-82cd-8dc03ea25d7d)

### Registration

New users can sign up and use our app.

![Sign Up](https://github.com/Hashir-Jamil/LongBox/assets/90640849/29155eea-7b16-47bc-97b3-60d623c3d12a)

### Home

This is the first page that the user lands on after logging in the system. The current user's username is displayed near the logout button.
The default view is Comic Repository page where the user can view all the comic books in the app's collection.

![Comic Repo Page](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f939fdd5-1ed5-4527-8234-1fcff95d23ab)

### Advanced Search

Users can use the advanced search and search the comics based on Title, Artist, Author, Genre, Publisher and Year Published. The search results are viewed in a different window.

![Search Results](https://github.com/Hashir-Jamil/LongBox/assets/90640849/a2aaeb1a-05c1-422f-b358-5b685564afe7)

### View Detailed information and Add Comments

After selecting a comic book, users can view detailed information about the comic book. In this window user also has an option to add a new comment, view existing comments in order of newest to oldest, 
favourite and unfavourite a book, add and remove a book from a reading and finished list. Comics added to reading and finished list can be viewed in the profile page.

![Comic Info](https://github.com/Hashir-Jamil/LongBox/assets/90640849/4b78f565-6018-4b12-aadb-3af7b6e3723e)

### Add Comic

Using this page, a user can add a comic book to the system. A user can favourite a comic while adding to the system.

![Add Comic](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f9afc51d-567c-424d-92df-bd0f889a1208)

### Favourite a comic

All the comics favourited are visible in this page, a user can remove a comic from favourites.

![Favourites Page](https://github.com/Hashir-Jamil/LongBox/assets/90640849/4953cd07-9ed9-41ae-bac3-83f0dd2ed367)

Confirmation to remove from favourites:

![Unfavourite](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f0523c0a-70de-45d1-b8af-58c217cf10a8)

### View Profile

Users can view their details on the profile page, including comics read and finished. Users also have an option to edit their about me.
This page will show all user profile information except for the password.

![Profile Page](https://github.com/Hashir-Jamil/LongBox/assets/90640849/52dfa3ec-768f-443d-82fa-fdac6d95a62f)

### Reading & Finished Lists

Users can add and remove books from their personal reading and finished lists of comic books. Add and remove is accomplished when viewing a comic book in its individual page. The lists can be viewed in the profile view and from here the comic books can be selected again to edit their list membership.

![Editing Lists](https://github.com/Hashir-Jamil/LongBox/assets/77168895/07c4fc74-c3f4-4d67-98d8-732a8036e7af)

![Viewing Lists](https://github.com/Hashir-Jamil/LongBox/assets/77168895/f7d02b3f-8d3e-4340-b6c5-ee6f36be5b49)

### Recommendations

Upon login users land on a page of recommendations that are tailored to for them. The entries in this list can be opened to explore more about them.

![Recommendations](https://github.com/Hashir-Jamil/LongBox/assets/77168895/6e7aefda-cefb-4668-9aae-e80d0c90942d)

### Trending

### About Me

### Star Ratings

### Leaderboards

### User Lookup

### Logout

After using the system, the user can log out and will be redirected to the login page.

![Logout](https://github.com/Hashir-Jamil/LongBox/assets/90640849/7bb2e520-4505-487d-8f5c-9fbaade7d7f6)

## Authors
    Hashir Jamil
    Ahan Bhargava
    Mher Eric Gyuluman
    Ali Sina
    Oscar Ye
