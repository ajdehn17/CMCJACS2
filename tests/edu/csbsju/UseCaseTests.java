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
		Account a = ac.logOn("juser", "user");
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
		Account a = ac.logOn("IMAD", "user");
		assertEquals("Account is " + a, a, null);
	}

	@Test(expected = Error.class)
	public void UseCase1LoginInvalidPassword() {
		Account a = ac.logOn("juser", "IMAD");
		assertEquals("Account is " + a, a, null);
	}

	@Test(expected = Error.class)
	public void UseCase1LoginDeactivated() {
		Account a = ac.logOn("luser", "luser");
		assertEquals("Account is " + a, a, null);
	}

	@Test
	public void UseCase2Search() {
		int result = 0;
		int expected = 16;
		List<String> schools = Arrays.asList("BARNARD", "BARUCH", "CCNY", "COLUMBIA", "COOPER UNION",
				"EASTMAN SCHOOL OF MUSIC", "FORDHAM", "JUILLIARD", "NEW YORK UNIVERSITY",
				"POLYTECHNIC INSTITUTE OF NEWYORK", "PRATT", "QUEENS", "ST JOHNS UNIVERSITY", "SUNY BUFFALO", "TOURO",
				"UNIVERSITY OF ROCHESTER");
		List<University> list = u.searchForSchools(null, "NEW YORK", "URBAN", null, 10000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
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

	}

	@Test
	public void UseCase2SearchBrowseAllSchools() {
		int result = 0;
		int expected = 183;
		DBController d = new DBController();
		List<University> y = d.getAllUniversities();
		List<String> schools = new ArrayList<String>();
		for (University q : y) {
			String name = q.getUniversityName();
			schools.add(name);
		}
		List<University> list = u.searchForSchools(null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, null);
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
	}

	@Test
	public void UseCase2SearchNoSchoolsFound() {
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}
		int result = 0;
		int expected = 0;
		List<String> schools = Arrays.asList();
		List<University> list = u.searchForSchools("JACOBSTON", "JACOB", null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		if (list != null) {
			for (University u : list) {
				result++;
			}
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
	}

	@Test
	public void UseCase2SearchOnlyUpperBounds() {
		int result = 0;
		int expected = 3;
		List<String> schools = Arrays.asList("COLLEGE OF SAINT BENEDICT", "CSBSJU", "U OF M DULUTH");
		List<University> list = u.searchForSchools(null, null, null, null, 0, 10000, 0, 50, 0, 400, 0, 400, 0, 15000, 0,
				50, 0, 1000, 0, 100, 0, 100, 0, 5, 0, 5, 0, 5, null);
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
	}

	@Test
	public void UseCase2SearchOnlyLowerBounds() {
		int result = 0;
		int expected = 2;
		List<String> schools = Arrays.asList("BRYN MAWR," + "MOUNT HOLYOKE");
		List<University> list = u.searchForSchools(null, null, null, null, 10000, 0, 75, 0, 400, 0, 400, 0, 15000, 0,
				55, 0, 500, 0, 50, 0, 50, 0, 3, 0, 3, 0, 3, 0, null);
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
	}

	@Test
	public void UseCase2SearchEqualBounds() {
		int result = 0;
		int expected = 1;
		List<String> schools = Arrays.asList("UNIVERSITY OF MINNESOTA");
		List<University> list = u.searchForSchools(null, null, null, null, 40000, 40000, 45, 45, 490, 490, 557, 557,
				13772.0, 13772.0, 50.0, 50.0, 8500, 8500, 80, 80, 60, 60, 4, 4, 3, 3, 4, 5, null);
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
	}

	@Test
	public void UseCase3ViewStudentProfile() {
		Account a = ac.logOn("juser", "user");
		String x = a.displayStudent();
		String result = "[FirstName=Jackson, LastName=User, Username=juser, Password=user, Type=u, Status=Y]\n";
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
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}
		List<String> schools = u.getSavedSchools();
		List<String> expected = Arrays.asList("UNIVERSITY OF MINNESOTA");
		for (String u : schools) {
			result++;
		}
		assertEquals("The length of the list is:" + exp, exp, result);
		result = 0;
		for (String u : schools) {
			for (String s : expected)
				if (u.equals(s))
					;
			result++;
		}
		assertEquals("The length of the list is:" + exp, exp, result);

	}

	@Test
	public void UseCase4ManageSavedSchoolsNone() {
		int result = 0;
		int exp = 0;
		Account a = ac.logOn("JSU", "Jsu--2019");
		if (a != null) {
			this.u = new UserUI(new User("Jacob", "Upton", "JSU", "Jsu--2019", 'u', 'Y'));
		}
		List<String> schools = u.getSavedSchools();
		List<String> expected = Arrays.asList();
		if (schools != null) {
			for (String u : schools) {
				result++;
			}
		}
		assertEquals("The length of the list is:" + exp, exp, result);
		result = 0;
		if (schools != null) {
			for (String u : schools) {
				for (String s : expected)
					if (u.equals(s))
						;
				result++;
			}
		}
		assertEquals("The length of the list is:" + exp, exp, result);

	}

	@Test
	public void UseCase5ManageUniversities() {
		int result = 0;
		int expected = 183;
		Account acc = ac.logOn("nadmin", "admin");
		if (acc != null) {
			this.a = new AdminUI(new Admin("Noreen", "Admin", "nadmin", "admin", 'a', 'Y'));
		}
		List<University> list = a.getUniversities();
		for (University u : list) {
			result++;
		}
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
		// The Student selects a school to view from the menu displayed on U2
		// The Database is prompted to search for requested University detailed
		// information
		String selectedSchool = "UNIVERSITY OF MINNESOTA";
		// The Database sends the University information back to the system.
		// The Database is prompted to search for 5 Universities closely related
		// to the one the Student requested to view.
		// The Database sends the detailed information for the 5 universities.
		DBController d = new DBController();
		int result = 0;
		int expected = 5;
		List<String> schools = Arrays.asList("UNIVERSITY OF WASHINGTON", "UNIVERSITY OF CALIFORNIA LOS ANGELES",
				"OKLAHOMA STATE UNIVERSITY", "UNIVERSITY OF KANSAS", "UNIVERSITY OF CALIFORNIA DAVIS");
		SearchController sc = new SearchController();
		List<University> list = sc.display5Schools(d.getAUniversity(selectedSchool));
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
		DBController db1 = new DBController();
		db1.addUniversityToSavedSchools("STANFORD", "juser");

		ArrayList<String> actResult;
		actResult = (ArrayList<String>) db1.getUserSavedSchools("juser");
		assertEquals("Saved schools " + expResult, expResult, actResult);

		// Clean up after saving university
		db1.removeUniversityFromSavedSchools("STANFORD", "juser");


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
		DBController db1 = new DBController();
		db1.addUniversityToSavedSchools("STANFORD", "juser");

		ArrayList<String> actResult;
		actResult = (ArrayList<String>) db1.getUserSavedSchools("juser");
		assertEquals("Saved schools " + expResult, expResult, actResult);

		// Clean up after saving university
		db1.removeUniversityFromSavedSchools("STANFORD", "juser");


	}

	@Test
	public void UseCase9SaveEditedUser() {
		Account a = ac.logOn("juser", "user");
		if (a != null) {
			this.u = new UserUI(new User("Jackson", "User", "juser", "user", 'u', 'Y'));
		}
//		The Student decides to save the changes they made to their personal information
			String newFirstName = "Jackson2";
			String newLastName = "User2";
			String newPassword =  "user2";
			u.editStudentProfile(newFirstName, newLastName, newPassword);
//		The Student initiates the save.
//		The Database is prompted to lookup Student’s information.
//		The Database updates the Student’s information with the new values.
//		The Database confirms update with the system.
			DBController db1 = new DBController();
			Account editedAcct = db1.findAccount("juser");
			
			assertEquals("Updated first name: " + newFirstName,
					newFirstName, editedAcct.getFirstName());
			assertEquals("Updated last name: " + newLastName,
					newLastName, editedAcct.getLastName());
			assertEquals("Updated password: " + newPassword,
					newPassword, editedAcct.getPassword());
			
			
//		The Student’s newly edited profile is now changed.
			newFirstName =  "Jackson";
			newLastName = "User";
			newPassword =  "user";
			u.editStudentProfile(newFirstName, newLastName, newPassword);
	}

	@Test
	public void UseCase10RemoveSavedUniversity() {

	}

	@Test
	public void UseCase11ViewUniversityDetails() {

	}

	@Test
	public void UseCase12EditUniversity() {

	}

	@Test
	public void UseCase13AddUniversity() {

	}

	@Test
	public void UseCase13AddUniversityDuplicateSchool() {

	}

	@Test
	public void UseCase14AddUser() {
		// 1.The Administrator has selected to Add New User
		// 2.The Admin enters user details into the specified text areas
		// a.The Admin enters in the user’s name, last name, username, and
		// password.
		// b.The user’s status is set to “Y” for active.
		adminFuncCon = new AdminFuncController(activeAdmin);
		adminFuncCon.addAccount("Imad", "Rahal", "irahal", "aPlusWork", 'u', 'Y');

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

	@Test
	public void UseCase14AddUserDuplicateUsername() {

	}

	@Test
	public void UseCase15EditUser() {

	}

	@Test
	public void UseCase15EditUserInvalidUsername() {

	}

	@Test
	public void UseCase16Deactivate() {

	}

	@Test
	public void UseCase16DeactivateDeactivatedAccount() {

	}

	@Test
	public void UseCase17Logout() {

	}

}
