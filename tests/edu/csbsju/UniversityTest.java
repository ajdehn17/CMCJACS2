package edu.csbsju;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UniversityTest {

	private University a, b, c;
	
	@Before
	public void setUp() throws Exception {
		aList = Arrays.asList("SCHOOL", "ENGINEERING");
		a = new University("HARVARD", "MA", "URBAN", "PRIVATE", 1010,
				54.1, 600, 550, 56000, 40123, 38000, 38.1, 72.4, 5, 4, 3,
				aList);
		bList = Arrays.asList("SPORTS", "PURPLE");
		a = new University("University of Saint Thomas", "MA", "URBAN", "PRIVATE", 1010,
				48.8, 570, 450, 3000, 49245, 13000, 67.1, 72.4, 3, 4, 1,
				bList);
		
	}
	
	@Test
	public void testUniversityStringStringStringStringIntDoubleIntIntDoubleDoubleIntDoubleDoubleIntIntIntListOfString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUniversityString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUniversityName() {
		String expResult = "HARVARD";
		assertEquals("University Name is Harvard" + expResult, 
				expResult, a.getUniversityName());
	}

	@Test
	public void testSetUniversityName() {
		b.setUniversityName("NHCC");
		String expResult = "NHCC";
		assertEquals("University Name should be NHCC" + expResult, 
				expResult, b.getUniversityName());
		// Test upperCase functions
		a.setUniversityName("Harvard2");
		String expResult = "HARVARD2";
		assertEquals("University Name is Harvard" + expResult, 
				expResult, a.getUniversityName());
	}

	@Test
	public void testGetLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetControl() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetControl() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfStudents() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNumberOfStudents() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPercentFemale() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPercentFemale() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSatVerbal() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSatVerbal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSatMath() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSatMath() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExpenses() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetExpenses() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFinancialAid() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFinancialAid() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfApplicants() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNumberOfApplicants() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPercentAdmitted() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPercentAdmitted() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPercentEnrolled() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPercentEnrolled() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAcademicScale() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAcademicScale() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSocialScale() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSocialScale() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQualityOfLife() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetQualityOfLife() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmphases() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintString() {
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
	public void testEditUniversity() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUniversity() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
