# LongBox ITR02 Build

Your digital solution for archiving and preserving comic books. Developed by passionate fans, this open-source desktop app offers concise documentation for out-of-print, independent, and mainstream comics. Addressing the decline in sales, LongBox provides a user-friendly platform for organizing and archiving collections. With web application plans, it aims to be a central hub for enthusiasts. Users can create profiles, submit comics, and enjoy a recommendation system. Built from scratch, LongBox prioritizes simplicity without relying on existing projects.

## System 

This project uses a 3-layer software architecture. The system is built around the Java Swing framework and behaves as a model view controller application in conformance to the oracle documentation for Swing applications. A sketch of it is:

![SystemDiagram](design_documents/diagrams/architecture_diagram_dark_mode.png)

## Getting Started

### Prerequisites

- The project is built on Java 19.
- The project uses Gradle (Kotlin) as the build system.
- The project used [PostgreSQL 16](https://www.postgresql.org/) as the database.

### Installing

1. The project needs to be cloned into Eclipse from the [GitHub Repository](https://github.com/Hashir-Jamil/LongBox). In terms of ITR01 release, the branch is named iteration_one and in terms of deliverable 1, the branch is main.
2. Once the project has been cloned, gradle nature needs to be added to the project. [How to configure gradle in eclipse](https://www.vogella.com/tutorials/EclipseGradle/article.html#add-gradle-support-to-existing-eclipse-project).
3. To use the database, we need to install [pgAdmin4](https://www.pgadmin.org/download/). In pgAdmin, [create a new database](https://www.tutorialsteacher.com/postgresql/create-database#:~:text=Create%20Database%20using%20pgAdmin&text=Open%20pgAdmin%20and%20right%2Dclick,Database…%20%2C%20as%20shown%20below.&text=This%20will%20open%20Create%20–%20Database,be%20the%20owner%20by%20default). The name of databse should be ***longbox_db***.
4. After creating the database, [run](https://support.spiresystems.com/support/solutions/articles/13000015301-executing-a-sql-query-using-pgadmin) the [*create_longbox_db*](database_scripts/create_longbox_db.sql) in the database_scripts package. This will setup the tables and populate them with the data required for this project.
5. To ensure the database server communicates with the project, go to the file [hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml) and ensure the connection.url property matches the one for your personal machine's postresql installation.

### Testing

The project also consists of a number of test cases, they can be run in Eclipse as follows: 
> right click on src/test/java > RunAs > JUnit Test.

These tests make sure that the business logic that drives our system is working correctly and as expected.We have done a thorough testing such that all the possible edge cases are covered in the test.

## Deployment

To launch the project:
1. Go the presentation package in source.
> LongBox / src / main / java / org / longbox / presntation
2. Go the authentication package inside presentaion.
> LongBox / src / main / java / org / longbox / presntation / authentication
3. Run the _AuthenticationPage.java_ file. This is the primary entry point into the project.

Note:
* This is the first frame of our project, rest of the features can be accessed through this page.
* Inside the persistence package, there is UserStubDB, which can be used to login into the system.

### Build System

[Gradle](https://kotlinlang.org/docs/gradle.html) - Dependency Management

## Features

### Login Page

This is the first page that the user of the app sees.

![Login](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f301d4e4-9725-477c-82cd-8dc03ea25d7d)

### Registration Page

New users can sign up and use our app.

![Sign Up](https://github.com/Hashir-Jamil/LongBox/assets/90640849/29155eea-7b16-47bc-97b3-60d623c3d12a)


### Home Page

This is the first page that the user lands on after logging in the system. The current user's username is displayed near the logout button.
The default view is Comic Repository page where the user can view all the comic books in the app's collection.

![Comic Repo Page](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f939fdd5-1ed5-4527-8234-1fcff95d23ab)

### Advanced Search

Users can use the advanced search and search the comics based on Title, Artist, Author, Genre, Publisher and Year Published. The search results are viewed in a different window.

![Search Results](https://github.com/Hashir-Jamil/LongBox/assets/90640849/a2aaeb1a-05c1-422f-b358-5b685564afe7)

### View Detailed information and Add Comments

After selecting a comic book, users can viewe detailed information about the comic book. In this window user also has an option to add a new comment, view existing comments in order of newest to oldest, 
favorite and unfavorite a book, add and remove a book from a reading and finished list. Comics added to reading and finished list can be viewed in the profile page.

![Comic Info Page](https://github.com/Hashir-Jamil/LongBox/assets/90640849/4b78f565-6018-4b12-aadb-3af7b6e3723e)

### Add Comic

Using this page, a user can add a comic book to the system. A user can favorite a comic while adding to the system.

![Add Comic](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f9afc51d-567c-424d-92df-bd0f889a1208)

### Favorites Page

All the comics favorited are visible in this page, a user can remove a comic from favorites.

![Favorites Page](https://github.com/Hashir-Jamil/LongBox/assets/90640849/4953cd07-9ed9-41ae-bac3-83f0dd2ed367)

Confirmation to remove from favorites:

![Unfavorite](https://github.com/Hashir-Jamil/LongBox/assets/90640849/f0523c0a-70de-45d1-b8af-58c217cf10a8)

### View Profile

Users can view their details on the profile page, including comics read and finished. Users also have an option to edit their about me.
This page will show all user profile information except for the password.

![Profile Page](https://github.com/Hashir-Jamil/LongBox/assets/90640849/52dfa3ec-768f-443d-82fa-fdac6d95a62f)

### Logout

After using the system, the user can logout and will be redirected to the login page.

![Logout](https://github.com/Hashir-Jamil/LongBox/assets/90640849/7bb2e520-4505-487d-8f5c-9fbaade7d7f6)

## Authors
    Hashir Jamil
    Ahan Bhargava
    Mher Eric Gyuluman
    Ali Sina
    Oscar Ye
