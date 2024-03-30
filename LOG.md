# EECS 2311Z Winter 2024 Team 3 Log of Activities  

## Wednesday, January 24th, 2024

	Eric: Interviewed Rocco and recorded the meeting. Uploaded mp3 to Google Drive.
	Oscar: Interviewed Rocco with Eric.

## Wednesday, January 31st, 2024

    Group (Dexter absent due to illness): Meeting with professor on how to organize with project and divide work.
    Hashir: Created GitHub Repository, JIRA workspace for project & starter gradle project.
    Ahan: Added working GUI of Registration page.

## Thursday, February 1st, 2024
	
	Ahan: Added working GUI of Login Page.

## Friday, February 2nd, 2024

    Hashir: Setup up entity classes, database schema in postgresql and ORM configuration for database communication.

## Saturday, February 3rd, 2024

    Group (all members present): team meeting with division of user stories & timelines for finishing ITR1
 
## Sunday, February 4th, 2024
	
	Ahan: Added functionality to registration page, making it work only if all fields are not null and entered, also added the email and password validation feature.

## Monday, February 5th, 2024

	Ahan: Added sign up button to login page, added sign in button to registration page to switch between the two pages and made it working.

## Tuesday, February 6th, 2024

    Dexter: Added empty home page, with logout button in top left, logout button creates pop up which, if confirmed, leads back to login page.
    Ali: Added empty GUI class for the Add Comic Page. 

## Wednesday, February 7th, 2024

    Group (all members present): Spoke with professor about refactoring packages, and making sure everyone implements tests in each of the packages.
    Ahan: Refactored LoginPage and Registration page as JPanels and added a new JFrame AuthenticationPage that houses these two JPanels. Added functionality to switch between LoginPage and RegistrationPage. Cleaned up the code.
    Hashir: Started working on the add comic book to repository user story and refactored the packages to match architecture shown in class slides + created stub database entries as JSON files with gson serialization/deserialization classes.
    Eric: Added basic ComicCollectionPage with lite features
    Ali: Started working on graphical user interface (GUI) for the Add Comic Page. This implementation focuses solely on the presentation layer without incorporating any business logic functionalities.
    
## Thursday, February 8th, 2024

	Ahan: Added user login and registration functionality to the authentication page using the StubDB.
    Ahan + Eric + Hashir: worked together to create proper card layout with panels for proper scene transitions without bugs.
    Hashir: Started working on user stories for adding comic book, searching comic book, viewing tabular view of comic books, viewing profile (these are anticipated to take 2 story points each but this may change)
    
## Friday, February 9th, 2024
	
    Eric: Fully refactored ComicCollectionPage to now include a search box and ScrollPane with an empty JTable. Old version of ComicCollectionPage was deleted
    
## Saturday, February 10th, 2024
	
	Ahan: Completed the user logout functionality started by Dexter. Also integrated the active user session into the design, which helps in validating the user session once logged in. Added the functionality to view the user name on the home page once logged in. Completed All the user stories for this sprint.
    Hashir: created comparators and sorting methods for comic book lists to be sorted by intended business rules/user story features.

## Sunday, February 11th, 2024

    Hashir: Refactoring & Reorganizing packages & business logic classes to correct the architecture in accordance to that presented by the professor in the latest lecture. Making DAO and Repository Interfaces for clean data access.

## Monday, February 12th, 2024
    
    Group meeting: ended/concluded ITR01-associated sprint on JIRA and assigned new user stories to move forward to next sprint for ITR2/Deliverable1
    Hashir: finished story for user to add a comic book to the stub database (this took 3 user story points due to the other tasks needed to be done to correct the architecture and to build up the project's other pieces anticipated for deliverable 2 (e.g. database api, gradle build system, hibernate ORM framework, postgresql connection).

## Wednesday, February 14th, 2024

    Hashir: Implemented user story for personal profile view and tabular view of all repository items (both stories took 2 points each).
    Eric: Fully refactored comic book contents. Includes not genres, artists, and issue number. Refactored other files dependent on this change.
    Oscar: Edited mp4 of interview with rocco (cut it down to 5 minutes, fixed eric's volume, fixed overall audio).
    
## Thursday, February 15th, 2024

    Hashir: Implemented search for individual comic book story (this took 2 points). In addition many refactors of the code were performed and reviewed to ensure everything works smoothly.
    Ahan: Refactored Authentication page for better redability. My initial estimate to complete each of my user stories was off a bit as I decided it would be better to focus on login and registration page simultaneously. I was also learning about concepts on the go, and that made the estimate time to go from 3 days to approximately 5 days. After the coding was done, I started with white box testing and then went ahead and refactored the code, changed a library and changed how the system was interacting with the stubDB, did minor bug fixes too.
    Eric: Added basic test cases for ComicCollectionPanel and uploaded more comics to the repository. Also fully finished implementing sorting for JTable in ComicCollection
    Oscar: Edited and created everyone's user stories cards on Google Slides.

## Friday, February 16th, 2024

	Eric: Added searchability to ComicCollectionPanel, user can now navigate to a comic page either through the search bar or clicking on a series' title name from the JTable
    Hashir: Reviewed and editted documentation to be submitted for ITR01 and branched the main trunk into an ITRO1 trunk.
    Ahan: Wrote the README.md.
    Ali: Created and added architectural-diagram.png to README.md

## Monday, February 19th, 2024

    Group Meeting (Ahan, Ali, Oscar, Eric): TO-DO by members who attended

## Tuesday, Februrary 20th, 2024

    Eric: Advanced searching via publisher, results display in a seperate frame/window
    
    
## Wednesday, February 21st, 2024

    Hashir & Ahan: setup hibernate to postgresql connection & created database connection + basic queries to test if it works
    Group Meeting (Ahan, Ali, Hashir, Oscar, Eric): Spoke with Prof. Hemmati about imbalanced github contribution problem and followed up with group meeting to reorganize teamwork.
	Oscar: Reorganized layout of profile page with future features in mind and added comics reading and comics finished stat.
    Ali: Initiated development on the Favourite Collection Comic Book feature, laying the groundwork for its implementation. 
    Eric: Added advanced search for title and year, advanced search logic in switch satement

## Thursday, February 22nd, 2024

    Hashir: refined database attributes & worked on creating user and comic book data to load into postgresql database. Created queries for database integration and tests.
    Ahan: Changed Login page and Registeration page from Stub DB to PostgreSQL DB. Also updated the README.md file. Wrote some tests to determine if the UserDaoImpl is working as expected.
    Eric: Results from advanced search now open in a new results fram, can click on them to open the comic page. Made test box for the results window title dynamic to the search input

## Friday, February 23rd, 2024

    Group Meeting (Ahan, Ali, Hashir, Oscar, Eric): Discussed integration and final documents for D1 + Presentation, discussed bugs to be corrected and talked about future user stories and features
    Hashir: finished database integration for ITR01 stories + fixed errors discovered after previous days integration efforts
    Oscar: Changed searches by publisher and series title to be case insensitive. Created method for searching comic book by artist and author.
    Eric: Comic Repository now uses postgres data rather than stub data

## Saturday, February 24th, 2024

    Group Meeting (Ahan, Ali, Hashir, Oscar, Eric): Discussed documentation & presentation for D1 coming up. Divided slides for presentation and sections for planning document
        '-> Ahan: write big user stories on ppt., write personal user stories for itr1,2 on itr2 planning document and ppt.
        '-> Ali: wire frame sketches on ppt., high level architectural diagram on ppt (that he made).
        '-> Hashir: write personal user stories for itr1,2 on planning doc and ppt., code review slide, other architecture diagrams
        '-> Oscar: edit interview video & talk about user stories on itr1,2 on plann ing document and ppt.
        '-> Eric: problem statement, customer video highlights, talk about user stories on itr1,2 on planning document and ppt., vision & future of project
    Ali: Finalized the Favourite Panel functionality without reliance on a database, utilizing a list-based approach for storage and management.
    Eric: Added advanced search for genres

## Sunday, February 25th, 2024

    Group Meeting (Ahan, Ali, Hashir, Oscar, Eric): Polished submission and reviewed it to ensure it has all required components, discussed presentation.
    Hashir: Refactored project to not use lombok and just use regular generated boiler plate code, added extra tests to check if anything was wrong.
    Eric: Added more comic entries to the repo, added test cases for ComicRepositoryPanel, and refactored ComicCollectionPanel to ComicRepositoryPanel
    Oscar: Added test cases for case insensitive searches in ComicBookSearchTest.java. Also added more comic books into the database.
    
## Tuesday, February 27th, 2024

    Group Meeting (Ahan, Ali, Hashir, Oscar, Eric): Practiced presentation several times.

## Wednesday, February 28th, 2024

    Eric: added repository refresh button for the case of a user adding comic books and wanting to view them in the list without loggin out and back in

## Thursday, February 29th, 2024

    Group Meeting (Ahan, Ali, Hashir, Oscar, Eric): discussed tasks/user stories needed for conclusion of iteration 2
        '-> Ahan: add comment to comic book, view comment on comic book
        '-> Ali: user can add a comic book to favourites and user can remove a comic book from favourites
        '-> Eric: user can login with email or username (may need modification for username's valid characters upon registration)
        '-> Hashir: user can add a comic book to their current reading list and add comic books to their finished list
        '-> Oscar: fix fonts, about me box for profile view1
    Ahan: Made emails unique while registration.

## Friday, March 1st, 2024
    
    Ahan: Refactored the Search Results page. Added viewing comments besides viewing search result.

## Saturday, March 2nd, 2024

    Eric & Ahan: Passed user session into the search comic repo and the favouruties panel.
    Ahan: Added user can comment on a comic functionality, and the add comment appears in the list of comments.

## Sunday, March 3rd, 2024

    Hashir: Finished hibernate entity class relationship cardinalities

## Monday, March 4th, 2024

    Hashir & Ahan: Debugged database integration issues. Table definitions and key constraints were not handled properly. Also, data transfer was not being done correctly. New objects being created were leading to new keys/rows being made which were supposed to be for existing records in the database.
    
## Tuesday, March 5th, 2024

	Oscar: Changed default font to Calibri.
 	Eric: Added ability to login using email and password rather than just username and password. New user registration no longer allows '@' or blank usernames
    Ali & Ahan: Went over the error in FavouritesPanel and ComicBookFavouritesDaoImpl.

## Wednesday, March 6th, 2024
    
    Ahan: Fixed the error in FavouritesPanel and ComicBookFavouritesDaoImpl due to incorrect initializing of objects. Also refactored the rpofile page.
    Group (All members): Meeting with professor and weekly update in lecture time.
    Group (Ahan, Hashir, Eric, Oscar): Evening work session to finish iteration 2 planning document, discussing any tasks left to complete & discussion of a design decision for common interface of stub data and postgresql data.
    
## Thursday, March 7th, 2024
    
    Eric: Added advanced search to favourites panel, moved advanced favourites logic to ComicBookSearch. Added table model for reading and read comics, now displays in scroll pane / list in profile
	Oscar: Reorganized "about me" section in profile page and added JTextField and most of the functionality for the update button.
    Hashir: General refactoring, bug fixing, database testing, database API completion for finished and reading lists, improved previous stories CRUD APIs to include more use cases, database triggers for computed columns.

## Friday, March 8th, 2024
    
    Hashir: Competed user stories for user add/remove comic books to/from personal comic book finished and currently reading lists. GUI integration of user comic book list stories: table views, add/remove buttons. General refactoring. Stub common interface cleanup started.

## Saturday, March 9th, 2024
    
    Hashir: General refactoring + service layer creation for preparation of future design refinement & ITR3 refactoring to create further separation of concern. Service layer implementation. Stub database API development. Unit & Integration tests.

## Sunday, March 10th, 2024
    
    Ahan: Converted postgresql data to json files, finished CommentStubDB.java implementation and added more tests for CommentDaoImpl.java. Also updated ReadMe/ Wiki with updated feature list and latest screenshots.
    Eric: Added ability for user to navigate to comic book pgae by clicking on comic book in their reading / read table in profile
    Oscar: Finished implementing cancel button for "About Me" section and created test case for updating the field in database.
    Hashir: Final touches for submission criteria for ITR2 + adding more tests for stories and integration + refactoring

## Monday, March 11th, 2024
    
    Group (All Members): Discussed the ITR2 submission, also started with the ITR3 planning document and divided user stories and refactoring task amongst the group members.
    Ahan and Hashir: Refatored controller of authentication frame to a seperate class, also incorporated UserService class to the refactored code.
    Eric: added table models, panel, and logic for all time trending. Refactored trending page.
    Hashir: began working on service layer of business logic for better abstraction, improved stub database tests & fixed json stub database format --> refactoring of code is overall goal here
    

## Tuesday, March 12th, 2024

    Ahan and Hashir: Moved controller for ComicBookInfoPanel to another package, and switched form CommentDao to CommentService
    Ali, Hashir and Ahan: Moved controller for FavouritesPanel to another package.
    Hashir: More refactoring by cleaning up coding style, unused fields/imports, development of controller to separate gui from its control, finished service layer api for cleaner abstraction of backend services, made database table id fields bigint and all java types Long wrapper object for corresponding fields/params/returns

## Wednesday, March 13th, 2024

    Group Meeting (All members): went over recent refactorings for everyone to be up to date, met with professor to discuss final phase of course and our plan/what we are working on/have worked on
    Hashir: more refactoring of coding style & project structure, consistent mapping/contracts of dto vs entity at service layer.
    
## Thursday, March 14th, 2024

    Eric: Refactored all Comic Repo Panel logic into its respected controller class, removal of unused code and imports, and multiple bug fixes such as fixing imporper comics opening, multiple tables loading, improper refreshing, and improper favouriting
    
## Friday, March 15th, 2024
    
    Group Meeting (Ali, Ahan, Eric, Hashir): discussed division of stories for take home assignment. Divided stories evenly & discussed conventions for e2e tests, bug reports, github issues and code review checklists. Agree upon conventions for these documentations and made template for take home assignment.
    Hashir: did e2e testing and wrote bug reports.
    Ahan: Fixed reported bugs related to my user stories.
    Ali: Fixed reported bugs related to FavoritesPanel.
    
## Saturday, March 16th, 2024

	Oscar: Started working on star rating feature.
    Eric: Refactroed ComicBookSearchResults logic and functionality into its respcitive controller class. Refactored trending functionality to its respective controller class as well. Comic favourites count now increments through postgres function. Renamed instances of 'comic collection...' to 'comic repo...'.
	
## Sunday, March 17th, 2024

	Oscar: Almost finished with star rating feature.
    Eric: Refactored 'National Favoruties' to 'Continetnental/Regional Favourites'. Comicbooks now have a favourites count for every continent and users now have a continent field. Regional favourites logic is seperate fom all time favourites
	
## Monday, March 18th, 2024

	Group Meeting (All Members): Discussed code review, refactoring & end-2-end testing. Fully divided and confirmed everything a second time so that everyone can do the idnvidual parts of the assignment.
    Oscar: Refactored ProfilePanel so that the functionality of the aboutMe buttons are moved to ProfileController.
    Hashir: Performed code review, created problem reports/GitHub issues. Fixed add comic book form issue [BR4]. Worked on refactoring document and develoepr stories (90% finished, rest of group should contribute to these as well). Integrated Jacoco reports into builid system test tasks.
    Eric: User and comic book DTO further refined, proper mappings, and selection for continetns in trending

## Tuesday, March 19th, 2024
    
    Hashir: Added unit tests in business logic and domain object classes and improved data access object integration tests. Added service layer integration tests. Finished recommendations user story. Refactored comic book tables on user profile view page. Hotfixed mapping of genres for users preferred genres between entity and dto. Created empty test classes for tests perceived to be insufficient. These tests will be done by everyone as they span across everyone's features.
    Ahan: Refactoring: removed all instances of UserDto and User constructors throughout the code, used Java Beans in its place.
    Eric: Schema handles regional favourite for users and comic books with proper incrementation, users now get automatically assigned a continent based on the country they select to sign up with during registatation
	 Oscar: Created database table for star ratings.

## Wednesday, March 20th, 2024

    Hashir: Refactored ComicBookDTO and ComicBook entity classes to remove constructors and associated cascades to unecessary tests. This is in line with refactor task 3 in refactor document and refactoring developer stories.
    Group Meeting (All Members): Met with professor and discussed documentation for submission, bug reports, issue tracking,
    Ahan: Refactoring: removed all instances of CommentDto and Comment constructors throughout the code, used Java Beans in its place. Also made a frame for other user that views its comments. Added a social panel that currently displays all the users registerd.
    Eric: Most, if not all, countries are mapped to their proper continent. Added test cases for favouriting, trending, and advanced search
    Oscar: Continued working on fixes for star rating system.
    
## Thursday, March 21st, 2024
    
    Hashir: Worked on refactoring tasks and addressed bugs
    Ahan: Completed my user story for ITR3, full functioning of social panel with a filter feature to filter based on the number of comics reading/ finished and more/ less. Added tests for a new method used. Added more enteries in the script for users.
    Oscar: Continued working on fixes for star rating system.

## Sunday, March 24th, 2024

    Ali: Completed my user story for ITR3, now a user can view another user profile from comments by double clicking

## Tuesday, March 26th, 2024

    Hashir: worked on documentation like readme, planning doc, refactoring doc, diagrams and worked on bugs/problems/issues
    Ahan: Updated README.md by adding instructions to run script via command line. Added auto refresh for social panel, and added more enteries for favorites, reading and finished list in db script.
    Oscar: Addressed bug for "about me" feature and organized star rating buttons.

## Thursday, March 28th, 2024

    Ahan: Updated README.md by adding the latest screenshots as well as adding new features and their description.
    Oscar: Completed fixing star rating system.

## Friday, March 29th, 2024 

    Hashir: updated documentation and slides for D2 submission.
    Group Meeting (Hashir, Ahan, Eric, Oscar): Worked on slides and post mortem analysis.
    Oscar: Wrote test cases for star rating system and populated database with star rating entries.
    Ahan: Did documentation and add more test cases.

## Saturday, March 30th, 2024

    Hashir: Updated documentation, touched up presentation, improved some flawed test cases, increased test coverage in domain objects.

