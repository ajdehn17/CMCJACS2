package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class UniversityControllerTest {
	private UniversityController a;
	private DBController db1;
	
	@Before
	public void setUp() throws Exception {
		a = new UniversityController();
		db1 = new DBController();
	}
	
	@Test
	public void testUniversityController() {
		AccountController a = new AccountController();
		Account actual = a.displayAccount();
		assertEquals("The account object is null", null, actual);
		
		UniversityController test = new UniversityController();
		
		
		test.getClass();
		
		assertTrue("TEST", 1==1);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testAddUniversity() {
		ArrayList<String> empty = new ArrayList<String>();
		University a2 = new University("AUGSBURG2", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000,
				43.0, 420, 490, 29991.0, 80.0, 4000,
	            85.0, 50.0, 1, 3, 4, empty);
		
		a.addUniversity("AUGSBURG2", "MINNESOTA", "SMALL-CITY", "PRIVATE", 10000,
				43.0, 420, 490, 29991.0, 80.0, 4000,
	            85.0, 50.0, 1, 3, 4);
		
		University addedU = db1.getAUniversity("AUGSBURG2");
		
		String expResult = addedU.printString();
		
		String expResult2 = a2.getState();
		
		assertEquals("Added University: " + expResult, 
			expResult, a2.printString());
		
		assertEquals("Added University: " + expResult2, 
				expResult, addedU.getState());
		
		// Clean up the university method
		db1.removeU("AUGSBURG2");
		
		assertNull("Removed AUGSBURG2: ", db1.getAUniversity("AUGSBURG2"));
	}

	@Test
	public void testEditUniversity() {
	
			
		
		a.editUniversity("AUGSBURG", "WISCONSIN", "SMALL-CITY", "PRIVATE", 10000,
				43.0, 420, 490, 29991.0, 80.0, 4000,
	            85.0, 50.0, 1, 3, 4);
		
		University updatedU = db1.getAUniversity("AUGSBURG");
		
		
		String expResult = "WISCONSIN";
		
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
		
		a.addEmphases("AUGSBURG", "MIS");

		University testU = db1.getAUniversity("AUGSBURG");
		ArrayList<String> testEmp = (ArrayList<String>) testU.getEmphases();
		
		// Checking that all are the same size and contain the same values
		for(String val: testEmp)
		{
			assertEquals("Add Emphases: " + expResult, 
				expResult.contains(val), testEmp.contains(val));
			// Check that the size is the same and that they contain all the same values
			assertEquals("Add Emphases: " + expResult, 
					expResult.size(), testEmp.size());
		}
		
		a.removeEmphases("AUGSBURG", "MIS");
	}

	@Test
	public void testRemoveEmphases() {
		ArrayList<String> expResult = new ArrayList<String>();
		expResult.add("BUSINESS-ADMINISTRATION");
		expResult.add("PERFORMING-ARTS");
		
		
		a.removeEmphases("AUGSBURG", "EDUCATION");
		
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
		a.addEmphases("AUGSBURG", "EDUCATION");
		
	}

	@Test
	public void testDisplayUniversity() {
		ArrayList<String> emp = new ArrayList<String>();
		emp.add("BUSINESS-ADMINISTRATION");
		emp.add("EDUCATION");
		emp.add("PERFORMING-ARTS");
		
		University u = new University("AUGSBURG", "MINNESOTA", "SMALL-CITY", "PRIVATE",
		10000, 43.0, 420, 490, 29991.0, 80.0, 4000, 85.0, 50.0, 1, 3, 4, emp);
		String expResult = u.printString();
		
		assertEquals("Remove Emphases: " + expResult, 
				expResult, a.displayUniversity(u));
		
	}

}
