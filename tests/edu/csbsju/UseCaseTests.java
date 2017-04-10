/**
 * 
 */
package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author jsupton
 *
 */
public class UseCaseTests {

	private static Admin activeAdmin;
	private static DBController dbc;
	private static User activeUser;
	private static UserFuncController userFuncCon;
	private static University univ;
	private static UniversityController univFuncCon;
	private static AdminFuncController adminFuncCon;

	private AccountUI ac;
	private UserUI u;
	private AdminUI a;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		activeAdmin = new Admin("Samantha", "Schmidgall", "SAS", "sas21", 'a', 'Y');
		dbc = new DBController();
		activeUser = new User("AJ", "Dehn", "AJD", "ajd22", 'u', 'Y');

		ArrayList<String> aList = new ArrayList<String>();
		aList.add("SCHOOL");
		aList.add("ENGINEERING");
		univ = new University("HARVARD", "MA", "URBAN", "PRIVATE", 1010, 54.1, 600, 550, 56000, 40123, 38000, 38.1,
				72.4, 5, 4, 3, aList);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.ac = new AccountUI();
		this.u = new UserUI();
		this.a = new AdminUI();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void UseCase1LoginSuccessful() {
		// Logon with a successful username
		Account a = ac.logOn("juser", "user");

		// Check that each of the attributes for the account logged on are
		// the correct values.
		String p = a.getPassword();
		assertEquals("Password is " + p, p, "user");
		String u = a.getUsername();
		assertEquals("UserName is " + u, u, "juser");
		String f = a.getFirstName();
		assertEquals("First Name is " + f, f, "Jackson");
		String l = a.getLastName();
		assertEquals("Last Name is " + l, l, "User");
		char t = a.getType();
		assertEquals("Type is " + t, t, 'u');
		char s = a.getStatus();
		assertEquals("Status is " + s, s, 'Y');
	}

	@Test(expected = Error.class)
	public void UseCase1LoginInvalidUsername() {
		// Logon using an invlaid username. An error is thrown.
		Account a = ac.logOn("IMAD", "user");
		assertEquals("Account is " + a, a, null);
	}

	@Test(expected = Error.class)
	public void UseCase1LoginInvalidPassword() {
		// Logon using an invalid password for a valid username.
		// and error is thrown
		Account a = ac.logOn("juser", "IMAD");
		assertEquals("Account is " + a, a, null);
	}

	@Test(expected = Error.class)
	public void UseCase1LoginDeactivated() {
		// Logon using a valid username and a valid password but the user
		// is not active. The user is deactivated
		Account a = ac.logOn("luser", "luser");
		assertEquals("Account is " + a, a, null);
	}

	@Test
	public void UseCase2Search() {
		int result = 0;
		int expected = 16;
		// User logs onto the system.
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}

		// This list is the schools that are expected to be returned by the
		// search,.
		List<String> schools = Arrays.asList("BARNARD", "BARUCH", "CCNY", "COLUMBIA", "COOPER UNION",
				"EASTMAN SCHOOL OF MUSIC", "FORDHAM", "JUILLIARD", "NEW YORK UNIVERSITY",
				"POLYTECHNIC INSTITUTE OF NEWYORK", "PRATT", "QUEENS", "ST JOHNS UNIVERSITY", "SUNY BUFFALO", "TOURO",
				"UNIVERSITY OF ROCHESTER");

		// Search using the location, state, and number of students.
		List<University> list = u.searchForSchools(null, "NEW YORK", "URBAN", null, 10000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		// This for loop goes through each university in the searched list and
		// keeps a counter
		for (University u : list) {
			result++;
		}
		// Asserts that the counter is the expected value.
		assertEquals("The length of the list is:" + expected, expected, result);

		result = 0;
		// Then goes throgh a double for loop to ensure that each name of the
		// school expected
		// is in the searched list as well.
		for (University u : list) {
			for (String s : schools)
				if (u.getUniversityName().equals(s))
					;
			result++;
		}
		// Asserts that those two numbers are equal.
		assertEquals("The length of the list is:" + expected, expected, result);

	}

	@Test
	public void UseCase2SearchBrowseAllSchools() {
		int result = 0;
		int expected = 183;
		// A DBController object is only created to check the answer and get all
		// universities.
		DBController d = new DBController();
		List<University> y = d.getAllUniversities();
		List<String> schools = new ArrayList<String>();
		// Goes through the list of universities and adds their names to a new
		// list
		for (University q : y) {
			String name = q.getUniversityName();
			schools.add(name);
		}

		// Searches using no criteria which should allow the user to browse.
		List<University> list = u.searchForSchools(null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		for (University u : list) {
			result++;
		}
		// Asserts that the search really did return all schools in the
		// database.
		assertEquals("The length of the list is:" + expected, expected, result);

		result = 0;
		// Goes through each school and ensures that the name is in the list.
		for (University u : list) {
			for (String s : schools)
				if (u.getUniversityName().equals(s))
					;
			result++;
		}
		// Ensures that they are equal in number.
		assertEquals("The length of the list is:" + expected, expected, result);
	}

	@Test
	public void UseCase2SearchNoSchoolsFound() {
		// User logs onto the system.
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}

		int result = 0;
		int expected = 0;
		// The user searches for a school that is not in the system.
		List<University> list = u.searchForSchools("JACOBSTON", "JACOB", null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		if (list != null) {
			for (University u : list) {
				result++;
			}
		}
		// Asserts that no schools were found in the search.
		assertEquals("The length of the list is:" + expected, expected, result);
	}

	@Test
	public void UseCase2SearchOnlyUpperBounds() {
		int result = 0;
		int expected = 3;
		// User logs onto the system.
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}

		// A list of expected schools from the search
		List<String> schools = Arrays.asList("COLLEGE OF SAINT BENEDICT", "CSBSJU", "U OF M DULUTH");
		// Searches using only upper bounds.
		List<University> list = u.searchForSchools(null, null, null, null, 0, 10000, 0, 50, 0, 400, 0, 400, 0, 15000, 0,
				50, 0, 1000, 0, 100, 0, 100, 0, 5, 0, 5, 0, 5, null);
		for (University u : list) {
			result++;
		}
		// Asserts that the two lists contain equal length.
		assertEquals("The length of the list is:" + expected, expected, result);

		result = 0;
		// Goes through both lists and ensures that they include that same names
		for (University u : list) {
			for (String s : schools)
				if (u.getUniversityName().equals(s))
					;
			result++;
		}
		// Asserts that they are of equal number.
		assertEquals("The length of the list is:" + expected, expected, result);
	}

	@Test
	public void UseCase2SearchOnlyLowerBounds() {
		int result = 0;
		int expected = 2;
		// User logs onto the system.
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}

		// The list of schools expected from the search.
		List<String> schools = Arrays.asList("BRYN MAWR", "MOUNT HOLYOKE");
		// Searching using only lower bounds as input.
		List<University> list = u.searchForSchools(null, null, null, null, 10000, 0, 75, 0, 400, 0, 400, 0, 15000, 0,
				55, 0, 500, 0, 50, 0, 50, 0, 3, 0, 3, 0, 3, 0, null);
		for (University u : list) {
			result++;
		}
		// Asserts that the lists contain an equal length.
		assertEquals("The length of the list is:" + expected, expected, result);

		result = 0;
		// Goes through both lists and ensures that names of the universities
		// are the same.
		for (University u : list) {
			for (String s : schools)
				if (u.getUniversityName().equals(s))
					;
			result++;
		}
		// Asserts that the lists contain an equal length.
		assertEquals("The length of the list is:" + expected, expected, result);
	}

	@Test
	public void UseCase2SearchEqualBounds() {
		int result = 0;
		int expected = 1;
		// User logs onto the system.
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}

		// Contains a list of schools expected to be found by this search
		// criteria
		List<String> schools = Arrays.asList("UNIVERSITY OF MINNESOTA");
		// Searches using all the criteria shown to below.
		List<University> list = u.searchForSchools(null, null, null, null, 40000, 40000, 45, 45, 490, 490, 557, 557,
				13772.0, 13772.0, 50.0, 50.0, 8500, 8500, 80, 80, 60, 60, 4, 4, 3, 3, 4, 5, null);
		for (University u : list) {
			result++;
		}
		// Ensures that the returned list is the same length as the expected
		// list.
		assertEquals("The length of the list is:" + expected, expected, result);

		result = 0;
		// Goes through both lists and determines if they contain the same
		// names.
		for (University u : list) {
			for (String s : schools)
				if (u.getUniversityName().equals(s))
					;
			result++;
		}
		// Ensures that the returned list contains the same names as the
		// expected list.
		assertEquals("The length of the list is:" + expected, expected, result);
	}

	@Test
	public void UseCase3ViewStudentProfile() {
		// Loggs on a user and sets it to an account object.
		Account a = ac.logOn("juser", "user");

		// Displays the student information and asserts that it equals what we
		// expect
		String x = a.displayStudent();
		String result = "[FirstName=Jackson, LastName=User, Username=juser, Password=user, Type=u, Status=Y]\n";
		// Asserts that each of the attributes are what we expect as well.
		assertEquals("The student is " + x, x, result);
		String p = a.getPassword();
		assertEquals("Password is " + p, p, "user");
		String u = a.getUsername();
		assertEquals("UserName is " + u, u, "juser");
		String f = a.getFirstName();
		assertEquals("First Name is " + f, f, "Jackson");
		String l = a.getLastName();
		assertEquals("Last Name is " + l, l, "User");
		char t = a.getType();
		assertEquals("Type is " + t, t, 'u');
		char s = a.getStatus();
		assertEquals("Status is " + s, s, 'Y');
	}

	@Test
	public void UseCase4ManageSavedSchools() {
		int result = 0;
		int exp = 1;
		// Loggs on a user object and sets it to the UserUI instance variable.
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}
		// This gets all of the saved schools for the user.
		List<String> schools = u.getSavedSchools();
		// Gets a list of the expected saved schools
		List<String> expected = Arrays.asList("UNIVERSITY OF MINNESOTA");
		for (String u : schools) {
			result++;
		}
		// Ensures that both lists contain equal length.
		assertEquals("The length of the list is:" + exp, exp, result);

		result = 0;
		// Goes through both lists and ensures that the list contains the same
		// names
		for (String u : schools) {
			for (String s : expected)
				if (u.equals(s))
					;
			result++;
		}
		// Asserts that they are both equal in length.
		assertEquals("The length of the list is:" + exp, exp, result);

	}

	@Test
	public void UseCase4ManageSavedSchoolsNone() {
		int result = 0;
		int exp = 0;
		// Logs in a user object with no saved schools.
		Account a = ac.logOn("JSU", "Jsu--2019");
		if (a != null) {
			this.u = new UserUI(new User("Jacob", "Upton", "JSU", "Jsu--2019", 'u', 'Y'));
		}
		// gets all of the saved schools from the user.
		List<String> schools = u.getSavedSchools();
		if (schools != null) {
			for (String u : schools) {
				result++;
			}
		}
		// Asserts that there were no universities in the user's saved schools.
		assertEquals("The length of the list is:" + exp, exp, result);
	}

	@Test
	public void UseCase5ManageUniversities() {
		int result = 0;
		int expected = 183;
		// Logs in an admin account
		Account acc = ac.logOn("nadmin", "admin");
		if (acc != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}

		// Gets all the universities for in the database. They will be displayed
		// on the screen for the admin to manipulate.
		List<University> list = a.getUniversities();
		for (University u : list) {
			result++;
		}
		// Asserts that the list contains all the universities in the database.
		assertEquals("The length of the list is:" + expected, expected, result);
	}

	@Test
	public void UseCase6ManageUsers() {
		int result = 0;
		int expected = 8;
		Account acc = ac.logOn("nadmin", "admin");
		if (acc != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}
		// Users requests a list of users from the Database
		List<Account> list = a.getAccounts();
		for (Account u : list) {
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		// Below comments are done on the backend
		// A list of users is loaded onto the administrator’s screen.
		// Admin may choose to add a new user.

	}

	@Test
	public void UseCase7ViewSearchedUniversity() {
		Account a = ac.logOn("JSU", "Jsu--2019");
		if (a != null) {
			this.u = new UserUI(new User("Jacob", "Upton", "JSU", "Jsu--2019", 'u', 'Y'));
		}
		// The Student selects a school to view from the menu displayed on U2
		// The Database is prompted to search for requested University detailed
		// information
		String selectedSchool = "UNIVERSITY OF MINNESOTA";
		// The Database sends the University information back to the system.
		// The Database is prompted to search for 5 Universities closely related
		// to the one the Student requested to view.
		// The Database sends the detailed information for the 5 universities.
		int result = 0;
		int expected = 5;
		List<String> schools = Arrays.asList("UNIVERSITY OF WASHINGTON", "UNIVERSITY OF CALIFORNIA LOS ANGELES",
				"OKLAHOMA STATE UNIVERSITY", "UNIVERSITY OF KANSAS", "UNIVERSITY OF CALIFORNIA DAVIS");
		List<University> list = u.getFiveMatches(selectedSchool);
		for (University u : list) {
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		result = 0;
		for (University u : list) {
			for (String s : schools)
				if (u.getUniversityName().equals(s))
					;
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		// The information for the University selected is displayed for the
		// Student
		// The Student is also presented with 5 recommendation schools that are
		// similar to the selected school
		// The Student can now save any of the schools to their saved schools
		// list from this screen
		// Extended by U8

	}

	@Test
	public void UseCase8SaveToSavedSchoolsSuccessful() {
		// Initialize user
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}
		// The Student decides to save a specified university to their saved
		// schools.
		// The Student initiates the save.
		// The Database is prompted for the university’s detailed information.
		u.addUniversityToSavedSchools("STANFORD");

		// The Database updates the Student’s saved schools list with the
		// detailed information of the new university.
		// The Database confirms the update with the system.
		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("STANFORD");
		expResult.add("UNIVERSITY OF MINNESOTA");
		ArrayList<String> actResult;
		actResult = (ArrayList<String>) u.getSavedSchools();
		assertEquals("Saved schools " + expResult, expResult, actResult);

		// Clean up after saving university
		u.removeUniversityFromSavedSchools("STANFORD");

	}

	@Test
	public void UseCase8SaveToSavedSchoolsDuplicateSchoo() {
		// Initialize user
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}
		// The Student decides to save a specified university to their saved
		// schools.
		// The Student initiates the save.
		// The Database is prompted for the university’s detailed information.
		u.addUniversityToSavedSchools("UNIVERSITY OF MINNESOTA");

		// The Database updates the Student’s saved schools list with the
		// detailed information of the new university.
		// The Database confirms the update with the system.

		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("UNIVERSITY OF MINNESOTA");
		ArrayList<String> actResult;
		actResult = (ArrayList<String>) u.getSavedSchools();
		assertEquals("Saved schools " + expResult, expResult, actResult);

	}

	@Test
	public void UseCase9SaveEditedUser() {
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}
		// The Student decides to save the changes they made to their personal
		// information
		String newFirstName = "Jackson2";
		String newLastName = "User2";
		String newPassword = "user2";
		u.editStudentProfile(newFirstName, newLastName, newPassword);

		// The Student initiates the save.
		// The Database is prompted to lookup Student’s information.
		// The Database updates the Student’s information with the new values.
		// The Database confirms update with the system.
		// This just contacts the DBController in order to check that the data
		// was changed.
		Account editedAcct = dbc.findAccount("juser");
		assertEquals("Updated first name: " + newFirstName, newFirstName, editedAcct.getFirstName());
		assertEquals("Updated last name: " + newLastName, newLastName, editedAcct.getLastName());
		assertEquals("Updated password: " + newPassword, newPassword, editedAcct.getPassword());

		// The Student’s newly edited profile is now changed.
		newFirstName = "Jackson";
		newLastName = "User";
		newPassword = "user";
		u.editStudentProfile(newFirstName, newLastName, newPassword);
	}

	@Test
	public void UseCase10RemoveSavedUniversity() {

	}

	@Test
	public void UseCase11ViewUniversityDetails() {
		//
		// 1.The Database is prompted to lookup detailed information for a
		// specified university
		//
		//
		// 2.The Database sends detailed Univeristy information back to the
		// system
		//
		//
		// 3.The detailed Univeristy information is displayed for the User.
		//
		//
		// 4.An administrator can select to edit the selected University.
		//
		//
		// a.Extended by U12
		
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}
		ArrayList<String> emp = new ArrayList<String>();
		emp.add("BUSINESS-ADMINISTRATION");
		emp.add("EDUCATION");
		emp.add("PERFORMING-ARTS");

		University u = new University("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000, 43.0, 420, 490, 29991.0,
				80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);
		String expResult = u.printString();
		

		assertEquals("Remove Emphases: " + expResult, expResult, this.a.displayUniversity(expResult));

	}

	@Test
	public void UseCase12EditUniversity() {

		// 1.The system prompts the Database to check to ensure the user is an
		// Administrator
		//
		//
		// 2.The database confirms with the system that the user is an
		// administrator.
		//
		//
		// 3.The System displays the current information for a specified
		// Univeristy.
		//
		//
		// 4.Admin updates the text fields desired for the given university
		//
		//
		// a.The Admin can change any values of the university except for the
		// University’s name. Any field left blank will be updated with that
		// blank value.
		//
		//
		// 5.The Admin can apply the changes made
		//
		//
		// 6.The system sends the Database the changes made for the University
		//
		//
		// 7.The Database updates the specified University with the detailed
		// information of the new changes of the University made by the User.
		//
		//
		// 8.The Database confirms the update with the system.
		//
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}
		this.a.editUniversity("AUGSBURG", "WISCONSIN", "SMALL-CITY", "PRIVATE", 10000, 43.0, 420, 490, 29991.0, 80.0, 4000,
				85.0, 50.0, 1, 3, 4);

		University updatedU = dbc.getAUniversity("AUGSBURG");

		String expResult = "WISCONSIN";

		assertEquals("Change State: " + expResult, expResult, updatedU.getState());

		// Change university back to original
		dbc.editUniversity("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000, 43.0, 420, 490, 29991.0, 80.0, 4000,
				85.0, 50.0, 1, 3, 4);

	}

	@Test
	public void UseCase13AddUniversity() {

		// 1.The Admin is prompted to fill in the requested information for the
		// new university.

		// 2.Administrator selects add university button.

		// 3.The System sends the Database the detailed information for the new
		// University

		// 4.The Database searches its list of Universities to ensure it is not
		// already in the list

		// 5.The Database adds the specified University with the detailed
		// information of the University entered by the User.

		// 6.The Database confirms the update with the system.

		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		} 
		
		ArrayList<String> empty = new ArrayList<String>();
		University a2 = new University("AUGSBURG2", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000, 43.0, 420, 490,
				29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, empty);

		this.a.addUniversity("AUGSBURG2", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000, 43.0, 420, 490, 29991.0, 80.0, 4000,
				85.0, 50.0, 1, 3, 4);

		University addedU = dbc.getAUniversity("AUGSBURG2");

		String expResult = addedU.printString();

		assertEquals("Added University: " + expResult, expResult, a2.printString());

		// Clean up the university method
		dbc.removeU("AUGSBURG2");

		assertNull("Removed AUGSBURG2: ", dbc.getAUniversity("AUGSBURG2"));

	}

	@Test(expected = Error.class)
	public void UseCase13AddUniversityDuplicateSchool() {

		// a.Return to step 1

		// b.An error message is displayed for the user telling them that the
		// addition of the new university failed, the university they entered
		// already exists.

		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}
		this.a.addUniversity("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000, 43.0, 420, 490, 29991.0, 80.0,
				4000, 85.0, 50.0, 1, 3, 4);

	}

	@Test
	public void UseCase14AddUser() {
		// 1.The Administrator has selected to Add New User
		// 2.The Admin enters user details into the specified text areas
		// a.The Admin enters in the user’s name, last name, username, and
		// password.
		// b.The user’s status is set to “Y” for active.
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}
		this.a.addAccount("Imad", "Rahal", "irahal", "aPlusWork", 'u', 'Y');

		// 3.Admin can select to add the new user to the Database. -- Admin
		// clicks!
		// 4.The system sends the Database the new user information.
		boolean expResult = true;
		assertEquals("User added successfully: " + expResult, expResult, dbc.checkUsername("irahal"));

		// 5.The Database searches its memory of users, ensuring that the
		// username entered is unique.
		// 6.The Database updates its memory of users, adding the new user.
		// 7.The Database confirms its successful addition with the system.

		// Clean up new user
		dbc.removeA("irahal");
	}

	@Test(expected = Error.class)
	public void UseCase14AddUserDuplicateUsername() {
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}
		// 1.Step 5 fails, The Admin attempts to create a user with a username
		// that is already being used.
		this.a.addAccount("Jesse", "Upton", "JSU", "Jsu--2019", 'u', 'Y');

		// a.Return to step 1.

		// b.An error message is displayed telling the user of the unsuccessful
		// addition due to the fact that the username is already in use.

	}

	@Test
	public void UseCase15EditUser() {
		DBController db1 = new DBController();
		Account ad = ac.logOn("nadmin", "admin");
		String fname = "cjzins";
		AssertTrue(u.checkUsername(fname));
		Account ac = findAccount("cjzins");
		String newFirstName = "Jo";
		String newLastName ="Casey";
		String username = "cjzins";
		String newPassword = "newp"
		Account na = a.editAccount(newFirstName, newLastName, username, newPassword, 'a', 'Y');
		assertEquals("Updated first name: " + newFirstName, newFirstName, ac.getFirstName());
		assertEquals("Updated last name: " + newLastName, newLastName, ac.getLastName());
		assertEquals("Updated password: " + newPassword, newPassword, ac.getPassword());
		newFirstName =  "Casey";
		newLastName = "Zins";
		newPassword =  "pass";
		u.editStudentProfile(newFirstName, newLastName, username, newPassword, 'a', 'Y');
		}

	}

	@Test
	public void UseCase15EditUserInvalidUsername() {
		DBController db1 = new DBController();
		Account ad = ac.logOn("nadmin", "admin");
		String fname = "cjzins";
		AssertTrue(u.checkUsername(fname));
		Account ac = findAccount("cjzins");
		String newFirstName = "Jo";
		String newLastName ="Casey";
		String username = "cins";
		String newPassword = "newp"
		assertFalse(a.editAccount(newFirstName, newLastName, username, newPassword, 'a', 'Y'));
	}

	@Test
	public void UseCase16Deactivate() {
		DBController db1 = new DBController();
		Account ad = ac.logOn("nadmin", "admin");
		char k = ad.deactivate(findAccount("cjzins"));
		assertEquals(k. 'N')
	}

	@Test
	public void UseCase16DeactivateDeactivatedAccount() {
		char k = ad.deactivate(findAccount("cjzins"));
		assertEquals(k. 'N')
	}

	@Test
	public void UseCase17Logout() {
		DBController db1 = new DBController();
		Account ad = ac.logOn("nadmin", "admin");
		ad.logOff();
		assertNull(this.username);
		assertNull(this.firstName);
		assertNull(this.lastName);
		assertNull(this.password);
		assertEquals(this.status, '\0');
		assertEquals(this.type, '\0');
	}

}
