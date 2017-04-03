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
		
		assertTrue("TEST", 1==1);
		//fail("Not yet implemented");
	}

	@Test
	public void testAddUniversity() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditUniversity() {
		fail("Not yet implemented");
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
		String eResult = expResult.toString();
		String aResult = expResult.toString();
		
		for(String val: testEmp)
		{
			assertEquals("Add Emphases: " + expResult, 
				expResult.contains(val), testEmp.contains(val));
			assertEquals("Add Emphases: " + expResult, 
					expResult.size(), testEmp.size());
		}
		
		a.removeEmphases("AUGSBURG", "MIS");
	}

	@Test
	public void testRemoveEmphases() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayUniversity() {
		fail("Not yet implemented");
	}

}
