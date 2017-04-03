package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DBControllerTest {

	private DBController db1, db2, db3;
	  
	@Before
	public void setUp() throws Exception {
		db1 = new DBController();
		db2 = new DBController();
		
		
	}
	// Testing constructor 1
	@Test
	public void testDBController() {
		
	}
	// Testing constructor 2
	@Test
	public void testDBControllerStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckUsername() {
		boolean expResult = true;
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.checkUsername("JuSer"));
		
		boolean expResult2 = false;
		assertEquals("Exists in the database: " + expResult2, 
				expResult2, db1.checkUsername("ajdehn"));
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindPasswordInvalid() {
		db1.findPassword("ajdehn");
	}
	
	@Test
	public void testFindPassword() {
		String expResult = "user";
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.findPassword("JuSer"));
	}

	@Test
	public void testDetermineType() {
		char expResult = 'u';
		/**
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.determineType("JuSer"));
				*/
	}

	@Test
	public void testGetAccounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllUniversities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAccount() {
		Account expResult = new Account("JOHN", "Miller",
				"juser", "user", 'u', 'Y');
		
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.findAccount("JuSer"));
		
	}

	@Test
	public void testGetAUniversity() {
		ArrayList<String> emp = new ArrayList<String>();
		emp.add("BUSINESS-ADMINISTRATION");
		emp.add("EDUCATION");
		emp.add("PERFORMING-ARTS");
		
		/**
		University expResult = new University("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE",
				10000, 43.0, 420, 490, 29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);	
		*/
		int expResult = 1;
		assertEquals("University: " + expResult, 
				expResult, db1.getAUniversity("AUGSBURG").getAcademicScale());
	}

	@Test
	public void testEditAccount() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testAddUniversity() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserSavedSchools() {
		ArrayList<String> expResult = new ArrayList<String>();
		
		assertEquals("Saved schools " + expResult, 
				expResult, db1.getUserSavedSchools("juser"));
		
	}

	@Test
	public void testAddUniversityToSavedSchools() {
		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("STANFORD");
		expResult.add("UNIVERSITY OF MINNESOTA");
		
		  db1.addUniversityToSavedSchools("STANFORD", "juser");
		  
		  ArrayList<String> actResult;
		  actResult = (ArrayList<String>) db1.getUserSavedSchools("juser");
		assertEquals("Saved schools " + expResult, 
					expResult, actResult);
		  
		  db1.removeUniversityFromSavedSchools("STANFORD", "juser");

	}

	@Test
	public void testRemoveUniversityFromSavedSchools() {
		ArrayList<String> expResult = new ArrayList<String>();
		
		db1.removeUniversityFromSavedSchools("UNIVERSITY OF MINNESOTA", "juser");
		  
		  ArrayList<String> actResult;
		  actResult = (ArrayList<String>) db1.getUserSavedSchools("juser");
		assertEquals("Saved schools " + expResult, 
					expResult, actResult);
		  
		  
		  // Add University of Minnesota back
		  db1.addUniversityToSavedSchools("UNIVERSITY OF MINNESOTA", "juser");
	}

	@Test
	public void testConfirmEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsActive() {
		boolean expResult = true;
		boolean expResult2 = false;
		Account a = new Account("Jacob","Upton","JSU","Jsu--2019",'u','Y');
		Account b = new Account("Jacob","Upton","JSU","Jsu--2019",'u','N');

		assertEquals("Active: " + expResult, 
				expResult, db1.isActive(a));

		assertEquals("Active: " + expResult2, 
				expResult2, db1.isActive(b));
	}

	@Test
	public void testDeactivate() {
		boolean expResult = false;
		Account a = new Account("Jacob","Upton","JSU","Jsu--2019",'u','Y');
		
		db1.deactivate(a);
		assertEquals("Deactivate: " + expResult, 
				expResult, db1.isActive(a));
	}

	@Test
	public void testAddAccount() {
		boolean expResult = true;
		Account a = new Account("Jacob","Upton","JSU","Jsu--2019",'u','Y');
		
		db1.addAccount(a.getFirstName(), a.getLastName(), a.getUsername(), a.getPassword(),
				a.getType(), a.getStatus());
		
		assertEquals("Add Account: " + expResult, 
				expResult, db1.findAccount(a.getUsername()));
				
	}

	@Test
	public void testEditUniversity() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEmphases() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEmphases() {
		fail("Not yet implemented");
	}

}
