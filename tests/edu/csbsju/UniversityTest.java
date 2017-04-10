package edu.csbsju;

import edu.csbsju.University;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author ajdehn
 *
 */
public class UniversityTest {

	private University a, b, c;
	private ArrayList<String> aList, bList;
	
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
		b = new University("University of Saint Thomas", "MA", "URBAN", "PRIVATE", 1010,
				48.8, 570, 450, 3000, 49245, 13000, 67.1, 7.4, 3, 4, 1,
				bList);
		c = new University("X");
	}
	
	@Test
	public void testUniversityStringStringStringStringIntDoubleIntIntDoubleDoubleIntDoubleDoubleIntIntIntListOfString() {
		University local;
		ArrayList<String> lList;
		lList = new ArrayList<String>();
		lList.add("PURPLE");
		lList.add("SPORTS");
		local = new University("University of Saint Thomas", "MA", "URBAN", "PRIVATE", 1010,
				48.8, 570, 450, 3000, 49245, 13000, 67.1, 7.4, 3, 4, 1,
				lList);
		
		String expResult = b.printString();
		assertEquals("University Name " + expResult, 
				expResult, local.printString());
	}

	@Test
	public void testUniversityString() {
		University local = new University("X");
		
		String expResult = "X";
		assertEquals("University Name " + expResult, 
				expResult, local.getUniversityName());
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
		String expResult2 = "HARVARD2";
		assertEquals("University Name is Harvard" + expResult2, 
				expResult2, a.getUniversityName());
	}

	@Test
	public void testGetLocation() {
		String expResult = "URBAN";
		assertEquals("Located in: " + expResult, 
				expResult, a.getLocation());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLocationInvalid()
	{
		b.setLocation("Big city");
	}
	
	@Test
	public void testSetLocation() {
		b.setLocation("SMALL-CITY");
		String expResult = "SMALL-CITY";
		assertEquals("Located in: " + expResult, 
				expResult, b.getLocation());
		// Test upperCase functions
		a.setLocation("sUburban");
		String expResult2 = "SUBURBAN";
		assertEquals("Located in" + expResult2, 
				expResult2, a.getLocation());
	}


	@Test
	public void testGetState() {
		String expResult = "MA";
		assertEquals("State: " + expResult, 
				expResult, a.getState());
	}

	@Test
	public void testSetState() {
		b.setState("MN");
		String expResult = "MN";
		assertEquals("State: " + expResult, 
				expResult, b.getState());
		// Test upperCase functions
		a.setState("Ms");
		String expResult2 = "MS";
		assertEquals("State: " + expResult2, 
				expResult2, a.getState());
	}
	
	@Test
	public void testGetControl() {
		String expResult = "PRIVATE";
		assertEquals("Control: " + expResult, 
				expResult, a.getControl());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetControlInvalid()
	{
		b.setControl("Public");
	}

	@Test
	public void testSetControl() {
		b.setControl("State");
		String expResult = "STATE";
		assertEquals("Control: " + expResult, 
				expResult, b.getControl());
		b.setControl("private");
		
		String expResult2 = "PRIVATE";
		assertEquals("Control: " + expResult2, 
				expResult2, b.getControl());
		
		b.setControl("City");
		String expResult3 = "CITY";
		assertEquals("Control: " + expResult3, 
				expResult3, b.getControl());
		
	}

	@Test
	public void testGetNumberOfStudents() {
		int expResult = 1010;
		assertEquals("Number Of Students: " + expResult, 
				expResult, a.getNumberOfStudents());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberOfStudentsNegativeInvalid()
	{
		b.setNumberOfStudents(-100);
	}
	
	@Test
	public void testSetNumberOfStudents() {
		b.setNumberOfStudents(0);
		int expResult = 0;
		assertEquals("Number of Students: " + expResult, 
				expResult, b.getNumberOfStudents());
		
		a.setNumberOfStudents(50);
		int expResult2 = 50;
		assertEquals("Number of Students: " + expResult2, 
				expResult2, a.getNumberOfStudents());
	}

	@Test
	public void testGetPercentFemale() {
		Double expResult = 54.1;
		assertEquals("Percent Female: " + expResult, 
				expResult, a.getPercentFemale(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPercentFemaleNegativeInvalid()
	{
		b.setPercentFemale(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPercentFemaleOverHundred()
	{
		b.setPercentFemale(101.0);
	}
	
	@Test
	public void testSetPercentFemale() {
		a.setPercentFemale(0);
		int expResult = 0;
		assertEquals("Percent Female: " + expResult, 
				expResult, a.getPercentFemale(), 0);
		a.setPercentFemale(100);
		int expResult2 = 100;
		assertEquals("Percent Female: " + expResult2, 
				expResult2, a.getPercentFemale(), 0);
		a.setPercentFemale(1);
		int expResult3 = 1;
		assertEquals("Percent Female: " + expResult3, 
				expResult3, a.getPercentFemale(), 0);
	}

	// a =600 b = 570
	@Test
	public void testGetSatVerbal() {
		int expResult = 600;
		assertEquals("SAT Verbal: " + expResult, 
				expResult, a.getSatVerbal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSatVerbalNegative()
	{
		b.setSatVerbal(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSatVerbalTooLarge()
	{
		b.setSatVerbal(801);
	}
	
	@Test
	public void testSetSatVerbal() {
		a.setSatVerbal(0);
		int expResult = 0;
		assertEquals("SAT Verbal: " + expResult, 
				expResult, a.getSatVerbal());
		a.setSatVerbal(800);
		int expResult2 = 800;
		assertEquals("SAT Verbal: " + expResult2, 
				expResult2, a.getSatVerbal());
		a.setSatVerbal(1);
		int expResult3 = 1;
		assertEquals("SAT Verbal: " + expResult3, 
				expResult3, a.getSatVerbal());
	}

	@Test
	public void testGetSatMath() {
		int expResult = 550;
		assertEquals("SAT Math: " + expResult, 
				expResult, a.getSatMath());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSatMathNegative()
	{
		b.setSatMath(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSatMathTooLarge()
	{
		b.setSatMath(801);
	}
	
	@Test
	public void testSetSatMath() {
		a.setSatMath(0);
		int expResult = 0;
		assertEquals("SAT Math: " + expResult, 
				expResult, a.getSatMath());
		a.setSatMath(800);
		int expResult2 = 800;
		assertEquals("SAT Math: " + expResult2, 
				expResult2, a.getSatMath());
		a.setSatMath(1);
		int expResult3 = 1;
		assertEquals("SAT Math: " + expResult3, 
				expResult3, a.getSatMath());
	}

	@Test
	public void testGetExpenses() {
		int expResult = 56000;
		assertEquals("Expenses: " + expResult, 
				expResult, a.getExpenses(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetExpensesNegative() {
		a.setExpenses(-1);
	}
	
	@Test
	public void testSetExpenses() {
		a.setExpenses(0);
		int expResult = 0;
		assertEquals("Expenses: " + expResult, 
				expResult, a.getExpenses(), 0);
		
		a.setExpenses(1);
		int expResult2 = 1;
		assertEquals("Expenses: " + expResult2, 
				expResult2, a.getExpenses(), 0);
	}

	@Test
	public void testGetFinancialAid() {
		int expResult = 40123;
		assertEquals("Financial Aid: " + expResult, 
				expResult, a.getFinancialAid(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetFinancialAidNegative() {
		a.setFinancialAid(-1);
	}
	
	@Test
	public void testSetFinancialAid() {
		a.setFinancialAid(0);
		int expResult = 0;
		assertEquals("Financial Aid: " + expResult, 
				expResult, a.getFinancialAid(), 0);
		
		a.setFinancialAid(1);
		int expResult2 = 1;
		assertEquals("Financial Aid: " + expResult2, 
				expResult2, a.getFinancialAid(), 0);
	}

	@Test
	public void testGetNumberOfApplicants() {
		int expResult = 38000;
		assertEquals("Number of Applicants: " + expResult, 
				expResult, a.getNumberOfApplicants(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberOfApplicantsNegative() {
		a.setNumberOfApplicants(-1);
	}
	@Test
	public void testSetNumberOfApplicants() {
		a.setNumberOfApplicants(0);
		int expResult = 0;
		assertEquals("Number Of Applicants: " + expResult, 
				expResult, a.getNumberOfApplicants());
		
		a.setNumberOfApplicants(1);
		int expResult2 = 1;
		assertEquals("Financial Aid: " + expResult2, 
				expResult2, a.getNumberOfApplicants());
	}

	@Test
	public void testGetPercentAdmitted() {
		double expResult = 38.1;
		assertEquals("Percent Admitted: " + expResult, 
				expResult, a.getPercentAdmitted(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPercentAdmittedNegative() {
		a.setPercentAdmitted(-1.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPercentAdmittedTooLarge() {
		a.setPercentAdmitted(101.0);
	}
	
	@Test
	public void testSetPercentAdmitted() {
		a.setPercentAdmitted(0.0);
		double expResult = 0;
		assertEquals("Number Of Applicants: " + expResult, 
				expResult, a.getPercentAdmitted(), 0);
		
		a.setPercentAdmitted(1.0);
		double expResult2 = 1;
		assertEquals("Financial Aid: " + expResult2, 
				expResult2, a.getPercentAdmitted(), 0);
		
		a.setPercentAdmitted(100.0);
		double expResult3 = 100;
		assertEquals("Financial Aid: " + expResult3, 
				expResult3, a.getPercentAdmitted(), 0);
	}

	@Test
	public void testGetPercentEnrolled() {
		double expResult = 72.4;
		assertEquals("Percent Enrolled: " + expResult, 
				expResult, a.getPercentEnrolled(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPercentEnrolledNegative() {
		a.setPercentEnrolled(-1.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPercentEnrolledTooLarge() {
		a.setPercentEnrolled(101.0);
	}
	
	@Test
	public void testSetPercentEnrolled() {
		a.setPercentEnrolled(0.0);
		double expResult = 0;
		assertEquals("Number Of Applicants: " + expResult, 
				expResult, a.getPercentEnrolled(), 0);
		
		a.setPercentEnrolled(1.0);
		double expResult2 = 1.0;
		assertEquals("Financial Aid: " + expResult2, 
				expResult2, a.getPercentEnrolled(), 0);
		
		a.setPercentEnrolled(100.0);
		double expResult3 = 100;
		assertEquals("Financial Aid: " + expResult3, 
				expResult3, a.getPercentEnrolled(), 0);
	}

	@Test
	public void testGetAcademicScale() {
		int expResult = 5;
		assertEquals("Academic Scale: " + expResult, 
				expResult, a.getAcademicScale());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAcademicScaleNegative() {
		a.setAcademicScale(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAcademicScaleTooLarge() {
		a.setAcademicScale(6);
	}
	
	@Test
	public void testSetAcademicScale() {
		a.setAcademicScale(0);
		int expResult = 0;
		assertEquals("Academic Scale: " + expResult, 
				expResult, a.getAcademicScale());
		
		a.setAcademicScale(5);
		int expResult2 = 5;
		assertEquals("Financial Aid: " + expResult2, 
				expResult2, a.getAcademicScale());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSocialScaleNegative() {
		a.setSocialScale(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSocialScaleTooLarge() {
		a.setSocialScale(6);
	}
	
	@Test
	public void testGetSocialScale() {
		int expResult = 4;
		assertEquals("Social Scale: " + expResult, 
				expResult, a.getSocialScale());
	}

	@Test 
	public void testSetSocialScale(){
		a.setSocialScale(0);
		int expResult = 0;
		assertEquals("Social Scale: " + expResult, 
				expResult, a.getSocialScale());
		
		a.setSocialScale(5);
		int expResult2 = 5;
		assertEquals("Social Scale: " + expResult2, 
				expResult2, a.getSocialScale());
	}

	@Test
	public void testGetQualityOfLife() {
		int expResult = 3;
		assertEquals("Quality of Life: " + expResult, 
				expResult, a.getQualityOfLife());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetQualityOfLifeNegative() {
		a.setQualityOfLife(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetQualityOfLifeTooLarge() {
		a.setQualityOfLife(6);
	}

	@Test
	public void testSetQualityOfLife() {
		a.setQualityOfLife(0);
		int expResult = 0;
		assertEquals("Quality of Life: " + expResult, 
				expResult, a.getQualityOfLife());
		
		a.setQualityOfLife(5);
		int expResult2 = 5;
		assertEquals("Quality of Life: " + expResult2, 
				expResult2, a.getQualityOfLife());
	}

	@Test
	public void testGetEmphases() {
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("PURPLE");
		testList.add("SPORTS");
		assertEquals("Emphases: " + testList, 
				testList, b.getEmphases());
		
	}

	@Test
	public void testPrintString() {
		String ex =  ("Name: \t\t\t" + a.getUniversityName() + "\nLocation: \t\t" + a.getLocation() + "\nState: \t\t\t" + a.getState()
				+ "\nControl: \t\t" + a.getControl() + "\nNumber Of Students: \t" + a.getNumberOfStudents() + "\nPercent Female: \t" + a.getPercentFemale()
				+ "\nSAT Verbal: \t\t" + a.getSatVerbal() + "\nSAT Math: \t\t" + a.getSatMath() + "\nExpenses: \t\t" + a.getExpenses() + "\nfinancialAid: \t\t"
				+ a.getFinancialAid() + "\nNumber Of Applicants: \t" + a.getNumberOfApplicants() + "\nPercent Admitted: \t" + a.getPercentAdmitted()
				+ "\nPercent Enrolled: \t" + a.getPercentEnrolled() + "\nAcademic Scale: \t" + a.getAcademicScale() + "\nSocial Scale: \t\t"
				+ a.getSocialScale() + "\nQuality Of Life: \t" + a.getQualityOfLife() + "\nEmphases: \t\t" + a.getEmphases() + "]\n");
				assertEquals("They are equal", ex, a.printString());
	}

	@Test
	public void testAddEmphases() {
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("PURPLE");
		testList.add("SPORTS");
		testList.add("MARKETING");
		
		b.addEmphases("Marketing");
		assertEquals("Emphases: " + testList, 
				testList, b.getEmphases());
	}
	
	

	@Test
	public void testRemoveEmphases() {
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("PURPLE");
		
		b.removeEmphases("SPORTS");
		assertEquals("Emphases: " + testList, 
				testList, b.getEmphases());
	}

	@Test
	public void testEditUniversity() {
		a.editUniversity("HARVARD", "Minnesota", "URBAN", "PRIVATE", 1010,
				54.1, 600, 550, 56000, 40123, 38000, 38.1, 72.4, 5, 4, 3);
		
		String expResult = "MINNESOTA";
		
		assertEquals("Edited university: " + expResult, 
				a.getState(), expResult);
	}

	@Test
	public void testAddUniversity() {
		University addedU = a.addUniversity("HARVARD2", "Minnesota", "URBAN", "PRIVATE", 1010,
				54.1, 600, 550, 56000, 40123, 38000, 38.1, 72.4, 5, 4, 3);
		
		String expResult = "HARVARD2";
		
		assertEquals("Added university: " + expResult, 
				addedU.getUniversityName(), expResult);
	}

}
