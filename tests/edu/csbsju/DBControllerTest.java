package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dblibrary.project.csci230.UniversityDBLibrary;

public class DBControllerTest {

	private DBController db1, db2, db3;
	private UniversityDBLibrary univDBlib;
	  
	@Before
	public void setUp() throws Exception {
		db1 = new DBController();
		db2 = new DBController();
		this.univDBlib = new UniversityDBLibrary("jacs","jacs","csci230");
		
	}
	@After
	public void tearDown() throws Exception {
	}
	// Testing constructor 1
	@Test
	public void testDBController() {
		db1 = new DBController();
		boolean expResult = true;
		assertEquals("Database loaded successfully: " + expResult, 
				expResult, db1.checkUsername("juser"));
	}
	// Testing constructor 2
	@Test
	public void testDBControllerStringStringString() {
		db1 = new DBController("jacs","jacs","csci230");
		boolean expResult = true;
		assertEquals("Database loaded successfully: " + expResult, 
				expResult, db1.checkUsername("juser"));
	}

	@Test
	public void testCheckUsername() {
		boolean expResult = true;
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.checkUsername("juser"));
		
	}
	
	@Test
	public void testCheckUsernameFalse() {
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
				expResult, db1.findPassword("juser"));
		
		
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
		int expResult = 8;
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
	public void testFindAccountNonExistent() {
		Account expResult = null;
		/**
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.findAccount("juser"));
		*/
		assertEquals("Exists in the database: " + expResult, 
				expResult, db1.findAccount("hgil"));;
		
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
		//Original account values "juser", "Jackson", "User","user", 'u', 'Y'
		// Change first name
		db1.editAccount("juser", "Billy", "User","user", 'u', 'Y');
		// Test that first name was changed.
		String expResult = "Billy";
		assertEquals("Edited Account: " + expResult, 
				expResult, db1.findAccount("juser").getFirstName());
		
		// Change first name back to Jackson
		db1.editAccount("juser", "Jackson", "User","user", 'u', 'Y');
	}

	@Test
	public void testAddUniversity() {
		ArrayList<String> emp = new ArrayList<String>();

		University u = new University("AUGSBURG2", "MINNESOTA", "SMALL-CITY", "PRIVATE",
				10000, 43.0, 420, 490, 29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);	

		db1.addUniversity(u);
		University expResult2 = db1.getAUniversity("AUGSBURG2");
		assertEquals("University: " + u.getUniversityName(), 
				u.getUniversityName(), expResult2.getUniversityName());
		assertEquals("University: " + u.getUniversityName(), 
				u.getState(), expResult2.getState());
		
		db1.removeU("AUGSBURG2");
	}
	
	@Test
	public void testAddUniversityExisting() {
		ArrayList<String> emp = new ArrayList<String>();
		emp.add("BUSINESS-ADMINISTRATION");
		emp.add("EDUCATION");
		emp.add("PERFORMING-ARTS");
		University u = new University("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE",
				10000, 43.0, 420, 490, 29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);	

		boolean actResult = false;
		boolean expResult= db1.addUniversity(u);
		
		assertEquals("University: " + expResult, 
				expResult, actResult);

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

	@Test(expected = IllegalArgumentException.class)
	public void testConfirmEditFail()
	{
		db1.confirmEdit("No");
	}
	
	@Test
	public void testConfirmEdit() {
		boolean expResult = true;
		boolean test = db1.confirmEdit("Y");
		assertEquals("Saved schools " + expResult, 
				expResult, test);
		
		boolean expResult2 = false;
		boolean test2 = db1.confirmEdit("N");
		assertEquals("Saved schools " + expResult2, 
				expResult2, test2);
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
		char expResult = 'N';
		Account a = db1.findAccount("luser");
		
		db1.deactivate(a);
		assertEquals("Deactivate: " + expResult, 
				expResult, a.getStatus());
	}
	
	
	@Test
	public void testAddAccountExisting() throws Exception
	{	
		Account a = new Account("Jacob","Upton","juser","Jsu--2019",'u','Y');
		boolean expResult = false;
		boolean b = db1.addAccount(a.getFirstName(), a.getLastName(), a.getUsername(), a.getPassword(),
				a.getType(), a.getStatus());
		assertEquals("Add Account: " + expResult, expResult, b);
	}
	
	@Test
	public void testAddAccount() throws Exception {
		boolean expResult = true;
		Account a = new Account("Jacob","Upton","andrew","Jsu--2019",'u','Y');
		
		boolean b = db1.addAccount(a.getFirstName(), a.getLastName(), a.getUsername(), a.getPassword(),
				a.getType(), a.getStatus());
		
		assertEquals("Add Account: " + expResult, expResult, b);
		univDBlib.user_deleteUser("andrew");
	}

	@Test
	public void testEditUniversity() {
		ArrayList<String> emp = new ArrayList<String>();
		emp.add("BUSINESS-ADMINISTRATION");
		emp.add("EDUCATION");
		emp.add("PERFORMING-ARTS");
		
		String expResult = "WISCONSIN";
				//new University("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE",
				//10000, 43.0, 420, 490, 29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);	
		
		db1.editUniversity("AUGSBURG", "WISCONSIN", "SMALL-CITY", "PRIVATE", 10000,
				43.0, 420, 490, 29991.0, 80.0, 4000,
	            85.0, 50.0, 1, 3, 4);
		
		University updatedU = db1.getAUniversity("AUGSBURG");
		
		

			assertEquals("Change State: " + expResult, 
				expResult, updatedU.getState());
			
			// Change university back to original
			db1.editUniversity("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000,
					43.0, 420, 490, 29991.0, 80.0, 4000,
		            85.0, 50.0, 1, 3, 4);
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
	public void testAddEmphasesExisting() {
		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("BUSINESS-ADMINISTRATION");
		expResult.add("EDUCATION");
		expResult.add("PERFORMING-ARTS");
		expResult.add("MIS");
		
		int actResult = db1.addEmphases("AUGSBURG", "EDUCATION");
		
		int eResult = -1;
		

		assertEquals("Existing emphases: " + expResult, 
				actResult, eResult);
		
		
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
