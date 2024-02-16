# LongBox

Your digital solution for archiving and preserving comic books. Developed by passionate fans, this open-source desktop app offers concise documentation for out-of-print, independent, and mainstream comics. Addressing the decline in sales, LongBox provides a user-friendly platform for organizing and archiving collections. With web application plans, it aims to be a central hub for enthusiasts. Users can create profiles, submit comics, and enjoy a recommendation system. Built from scratch, LongBox prioritizes simplicity without relying on existing projects.

## System 

This project uses a 3-layer software architechture. A sketch of it is:

![architectural-diagram.png](architectural-diagram.png)

## Getting Started

### Prerequisites

- The project is built on Java 19
- The project uses Gradle (Kotlin) as the build system

### Installing

1. The project needs to be cloned into Eclipse.
2. Once the project has been cloned, gradle nature needs to be added to the project. [How to configure grade in eclipse](https://www.vogella.com/tutorials/EclipseGradle/article.html#add-gradle-support-to-existing-eclipse-project).
3. This project also uses [Project Lombok](https://www.baeldung.com/intro-to-project-lombok). Project Lombok can be configured in Eclipse by following [this](https://stackoverflow.com/a/65949629).

### Testing

The project also consists of a number of test cases, they can be run in Eclipse as follows: 
> right click on src/test/java > RunAs > JUnit Test.

These tests make sure that the business logic that drives our system is working correctly and as expected.We have done a thorough testing such that all the possible edge cases are covered in the test.

## Deployment

To launch the project:
1. Go the the presentation package in source.
> LongBox / src / main / java / org / longbox / presntation
2. Go the the authentication package inside presentaion.
> LongBox / src / main / java / org / longbox / presntation / authentication
3. Run the _AuthenticationPage.java_ file

Note:
* This is the first frame of our project, rest features can be accessed through this page.
* Inside the persistence pacakge, there is UserStubDB, which can be used to login into the system.

### Build System

[Gradle](https://kotlinlang.org/docs/gradle.html) - Dependency Management

## Features

### Login Page

This is the first page that the user of the app sees. 

![LoginPage](https://github.com/Hashir-Jamil/LongBox/assets/90640849/94401b5a-5f2c-4d81-9f5d-d589ad11110d)

### Registeration Page

New users can sign up and use our app.

![RegisterationPage](https://github.com/Hashir-Jamil/LongBox/assets/90640849/11a86db2-66d6-4859-886a-b7606b660fec)

### Home Page

This is the first page that the user lands on after logging in the system. The current users username is displayed near the logout button.
The default view isComic Collection page where the user can view all the comic books in the app's collection.

![HomePage](https://github.com/Hashir-Jamil/LongBox/assets/90640849/c063eaf4-0cde-4391-b8f7-1596911e065d)

### Add Comic

Using this page, a user can add a comic book to the system.

![AddComic](https://github.com/Hashir-Jamil/LongBox/assets/90640849/213fa1c6-a094-4452-ba64-68dfd3fc5b57)

### Search Comics

Users can search a comic book and if the comic is in the reposisotry, results can be viewed.

![Search](https://github.com/Hashir-Jamil/LongBox/assets/90640849/e789b6c6-2fc4-49c0-b3bc-f68449c71919)

![SearchResult](https://github.com/Hashir-Jamil/LongBox/assets/90640849/3862fcc3-c012-4bb8-8344-8738d351bbd1)

### View Profile

Users can view their details on the profile page.

![ViewProfile](https://github.com/Hashir-Jamil/LongBox/assets/90640849/fad928eb-62cc-4983-9fed-c6845c5c25f0)

### Logout

After usign the system, the user can logout and will be redirected to the login page.

![Logout](https://github.com/Hashir-Jamil/LongBox/assets/90640849/524cf536-87cb-45e4-baa7-42e1e5f3523b)
