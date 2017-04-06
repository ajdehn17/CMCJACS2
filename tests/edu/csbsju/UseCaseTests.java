/**
 * 
 */
package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	private static University u;
	private static UniversityController univFuncCon;
	private static AdminFuncController adminFuncCon;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		activeAdmin = new Admin("Samantha","Schmidgall","SAS", "sas21",'a', 'Y');
		dbc = new DBController();
		activeUser = new User("AJ","Dehn","AJD","ajd22",'u','Y');
		
		ArrayList<String> aList = new ArrayList<String>();
		aList.add("SCHOOL");
		aList.add("ENGINEERING");
		u = new University("HARVARD", "MA", "URBAN", "PRIVATE", 1010,
				54.1, 600, 550, 56000, 40123, 38000, 38.1, 72.4, 5, 4, 3,
				aList);
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void UseCase1LoginSuccessful() {
		
	}
	
	@Test
	public void UseCase1LoginInvalidUsername() {
		
	}
	
	@Test
	public void UseCase1LoginInvalidPassword() {
		
	}
	
	@Test
	public void UseCase1LoginDeactivated() {
		
	}
	
	@Test
	public void UseCase2Search() {
		
	}
	
	@Test
	public void UseCase2SearchBrowseAllSchools() {
		
	}
	
	@Test
	public void UseCase2SearchNoSchoolsFound() {
		
	}
	
	@Test
	public void UseCase3ViewStudentProfile() {
		
	}
	
	@Test
	public void UseCase4ManageSavedSchools() {
		
	}
	
	@Test
	public void UseCase4ManageSavedSchoolsNone() {
		
	}
	
	@Test
	public void UseCase5ManageUniversities() {
		
	}
	
	@Test
	public void UseCase6ManageUsers() {
		
	}
	
	@Test
	public void UseCase7ViewSearchedUniversity() {
		
	}
	
	@Test
	public void UseCase8SaveToSavedSchoolsSuccessful() {
		
	}
	
	@Test
	public void UseCase8SaveToSavedSchoolsDuplicateSchoo() {
		
	}
	
	@Test
	public void UseCase9SaveEditedUser() {
		
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
		adminFuncCon.addAccount("Imad", "Rahal", "irahal" , "aPlusWork", 'u', 'Y');
		


		// 3.Admin can select to add the new user to the Database. -- Admin clicks!

		// 4.The system sends the Database the new user information.
		boolean expResult = true;
		assertEquals("User added successfully: " + expResult, 
				expResult, dbc.checkUsername("irahal"));


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
