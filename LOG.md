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
    Ali: Initiated development on the Favorite Collection Comic Book feature, laying the groundwork for its implementation. 
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
        '-> Eric: problem statement, customer video highlights, talk about user stories on itr1,2 on plann ing document and ppt., vision & future of project
    Ali: Finalized the Favorite Panel functionality without reliance on a database, utilizing a list-based approach for storage and management.
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
        '-> Ali: user can add a comic book to favorites and user can remove a comic book from favorites
        '-> Eric: user can login with email or username (may need modification for username's valid characters upon registration)
        '-> Hashir: user can add a comic book to their current reading list and add comic books to their finished list
        '-> Oscar: fix fonts, about me box for profile view1
    Ahan: Made emails unique while registration.

## Friday, March 1st, 2024
    
    Ahan: Refactored the Search Results page. Added viewing comments besides viewing search result.

## Saturday, March 2nd, 2024

    Eric & Ahan: Passed user session into the search comic repo and the favoruties panel.
    Ahan: Added user can comment on a comic functionality, and the add comment appears in the list of comments.

## Sunday, March 3rd, 2024

    Hashir: Finished hibernate entity class relationship cardinalities
