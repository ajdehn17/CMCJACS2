package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class UniversityControllerTest {
	private University a, b, c;
	ArrayList<String> aList, bList;
	
	@Before
	public void setUp() throws Exception {
		aList = new ArrayList<String>();
		aList.add("SCHOOL");
		aList.add("ENGINEERING");
		a = new University("HARVARD", "MA", "URBAN", "PRIVATE", 1010,
				54.1, 600, 550, 56000, 40123, 38000, 38.1, 72.4, 5, 4, 3,
				aList);
		bList = new ArrayList<String>();
		bList.add("PURPLE");
		bList.add("SPORTS");
		a = new University("University of Saint Thomas", "MA", "URBAN", "PRIVATE", 1010,
				48.8, 570, 450, 3000, 49245, 13000, 67.1, 72.4, 3, 4, 1,
				bList);
		c = new University("X");
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
		fail("Not yet implemented");
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
