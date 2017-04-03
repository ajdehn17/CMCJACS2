package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBControllerTest {

	private DBController db1, db2, db3;
	  
	@Before
	public void setUp() throws Exception {
		db1 = new DBController();
		db2 = new DBController();
		
	}
	@After
	public void tearDown() throws Exception {
		db1.removeU("AUGSBURG2");
	}
	// Testing constructor 1
	@Test
	public void testDBController() {
		db1 = new DBController();
		boolean expResult = true;
		assertEquals("Database loaded successfully: " + expResult, 
				expResult, db1.checkUsername("JuSer"));
	}
	// Testing constructor 2
	@Test
	public void testDBControllerStringStringString() {
		db1 = new DBController("jacs","jacs","csci230");
		boolean expResult = true;
		assertEquals("Database loaded successfully: " + expResult, 
				expResult, db1.checkUsername("JuSer"));
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
		Account a = new Account("Jacob","Upton","JSU","Jsu--2019",'u','Y');
		char val = db1.determineType(a);
		assertEquals("Determine type " + expResult, 
				expResult, val);
	}

	@Test
	public void testGetAccounts() {
		int expResult = 7;
		ArrayList<Account> allAccts = db1.getAccounts();
		int numAccounts = allAccts.size();
		
		assertEquals("Exists in the database: " + expResult, 
				expResult, numAccounts);
	}

	@Test
	public void testGetAllUniversities() {
		int expResult = 183;
		ArrayList<University> allUniv = db1.getAllUniversities();
		int numUniversities = allUniv.size();
		

		assertEquals("Exists in the database: " + expResult, 
				expResult, numUniversities);
	}

	@Test
	public void testFindAccount() {
		Account expResult = new Account("Jackson", "User",
				"juser", "user", 'u', 'Y');
		/**
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.findAccount("juser"));
		*/
		assertEquals("Exists in the database: " + expResult, 
				expResult.getUsername(), db1.findAccount("juser").getUsername());
		assertEquals("Exists in the database: " + expResult, 
				expResult.getPassword(), db1.findAccount("juser").getPassword());
		assertEquals("Exists in the database: " + expResult, 
				expResult.getType(), db1.findAccount("juser").getType());
		
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
		
		String expResult2 = "MINNESOTA";
		assertEquals("State: " + expResult2, 
				expResult2, db1.getAUniversity("AUGSBURG").getState());
	}

	@Test
	public void testEditAccount() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testAddUniversity() {
		ArrayList<String> emp = new ArrayList<String>();
		emp.add("BUSINESS-ADMINISTRATION");
		emp.add("EDUCATION");
		emp.add("PERFORMING-ARTS");
		University expResult = new University("AUGSBURG2", "MINNESOTA", "SMALL-CITY", "PRIVATE",
				10000, 43.0, 420, 490, 29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);	

		db1.addUniversity(expResult);
		
		
		
		
	}

	@Test
	public void testGetUserSavedSchools() {
		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("UNIVERSITY OF MINNESOTA");
		
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
		ArrayList<String> emp = new ArrayList<String>();
		emp.add("BUSINESS-ADMINISTRATION");
		emp.add("EDUCATION");
		emp.add("PERFORMING-ARTS");
		
		University expResult = new University("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE",
				10000, 43.0, 420, 490, 29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);	
		
		db1.editUniversity("AUGSBURG", "WISCONSIN", "SMALL-CITY", "PRIVATE", 10000,
				43.0, 420, 490, 29991.0, 80.0, 4000,
	            85.0, 50.0, 1, 3, 4);
		
		University updatedU = db1.getAUniversity("AUGSBURG");
		
		

			assertEquals("Add Emphases: " + expResult, 
				expResult, updatedU);

	}

	@Test
	public void testAddEmphases() {
		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("BUSINESS-ADMINISTRATION");
		expResult.add("EDUCATION");
		expResult.add("PERFORMING-ARTS");
		expResult.add("MIS");
		
		db1.addEmphases("AUGSBURG", "MIS");
		
		
		University testU = db1.getAUniversity("AUGSBURG");
		ArrayList<String> testEmp = (ArrayList<String>) testU.getEmphases();
		String eResult = expResult.toString();
		String aResult = expResult.toString();
		
		for(String val: testEmp)
		{
			assertEquals("Add Emphases: " + expResult, 
				expResult.contains(val), testEmp.contains(val));
			assertEquals("Add Emphases: " + expResult, 
					expResult.size(), testEmp.size());
		}
		
		db1.removeEmphases("AUGSBURG", "MIS");
		
		
	}

	@Test
	public void testRemoveEmphases() {
		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("BUSINESS-ADMINISTRATION");
		expResult.add("PERFORMING-ARTS");
		
		
		db1.removeEmphases("AUGSBURG", "EDUCATION");
		
		University testU = db1.getAUniversity("AUGSBURG");
		ArrayList<String> testEmp = (ArrayList<String>) testU.getEmphases();
		
		for(String val: testEmp)
		{
			assertEquals("Remove Emphases: " + expResult, 
				expResult.contains(val), testEmp.contains(val));
			assertEquals("Remove Emphases: " + expResult, 
					expResult.size(), testEmp.size());
		}

		// Add back in emphases
		db1.addEmphases("AUGSBURG", "EDUCATION");
	}

}
