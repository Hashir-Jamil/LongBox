# EECS 2311Z Winter 2024 Team 3 Log of Activities  

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

## Wednesday, February 7th, 2024

    Group (all members present): Spoke with professor about refactoring packages, and making sure everyone implements tests in each of the packages.
    Ahan: Refactored LoginPage and Registration page as JPanels and added a new JFrame AuthenticationPage that houses these two JPanels. Added functionality to switch between LoginPage and RegistrationPage. Cleaned up the code.
    Hashir: refactored the packages to match architecture shown in class slides + created stub database entries as JSON files with gson serialization/deserialization classes.
    
## Thursday, February 8th, 2024

	Ahan: Added user login and registration functionality to the authentication page using the StubDB.
    Ahan + Eric + Hashir: worked together to create proper card layout with panels for proper scene transitions without bugs.
    
## Saturday, February 10th, 2024
	
	Ahan: Completed the user logout functionality started by Dexter. Also integrated the active user session into the design, which helps in validating the user session once logged in. Added the functionality to view the user name on the home page once logged in. Completed All the user stories for this sprint.
    Hashir: created comparators and sorting methods for comic book lists to be sorted by intended business rules/user story features.

## Sunday, February 11th, 2024

    Hashir: Refactoring & Reorganizing packages & business logic classes. Making DAO and Repository Interfaces for clean data access.

## Monday, February 12th, 2024
    
    Group meeting: ended/concluded ITR01-associated sprint on JIRA and assigned new user stories to move forward to next sprint for ITR2/Deliverable1
    Hashir: finished story for user to add a comic book to the stub database.

## Wednesday, February 14th, 2024

    Hashir: Implemented user story for personal profile view and tabular view of all repository items.
    
## Thursday, February 15th, 2024

    Ahan: Refactored Authentication page for better redability. My initial estimate to complete each of my user stories was off a bit as I decided it would be better to focus on login and registration page simultaneously. I was also learning about concepts on the go, and that made the estimate time to go from 3 days to approximately 5 days. After the coding was done, I started with white box testing and then went ahead and refactored the code, changed a library and changed how the system was interacting with the stubDB, that were earlier not working as inteded.
    Hashir: Implemented search for individual comic book story.