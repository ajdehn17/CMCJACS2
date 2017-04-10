package edu.csbsju;

import static org.junit.Assert.*;

import java.util.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserFuncControllerTest {
	private UserFuncController ufc;
	
	
	
	@Before
	public void setUp() throws Exception {
		ufc = new UserFuncController(new User("Jacob","Upton","JSU","Jsu--2019",'u','Y'));
		
		
	}
	
	@Test (expected = Error.class)
	public void testUserFuncController() {
		UserFuncController a = new UserFuncController();
		User actual = a.displayStudentDetails();
		assertEquals("The account object is null", null, actual);
	}

	@Test
	public void testUserFuncControllerUser() {
		UserFuncController a = new UserFuncController(new User("Jacob","Upton","JSU","Jsu--2019",'u','Y'));
		Account exp = a.displayStudentDetails();
		assertEquals("Password is " + exp.getPassword(),exp.getPassword(), "Jsu--2019");
		assertEquals("UserName is " + exp.getUsername(),exp.getUsername(), "JSU");
		assertEquals("First Name is " + exp.getFirstName(),exp.getFirstName(), "Jacob");
		assertEquals("Last Name is " + exp.getLastName(),exp.getLastName(), "Upton");
		assertEquals("Type is " + exp.getType(),exp.getType(),'u');
		assertEquals("Status is " + exp.getStatus(),exp.getStatus(),'Y');
	}

	@Test
	public void testAddUniversityToSavedSchools() {
		ufc.addUniversityToSavedSchools("AUGSBURG");
		ArrayList <String> l = (ArrayList<String>) ufc.getSavedSchools();
		String a = l.get(0);
		assertEquals("school added is "+a,a,"AUGSBURG");
		ufc.removeUniversityFromSavedSchools("AUGSBURG");
		
		
	}

	@Test
	public void testRemoveUniversityFromSavedSchools() {
		ufc.addUniversityToSavedSchools("AUGSBURG");
		ufc.removeUniversityFromSavedSchools("AUGSBURG");
		ArrayList <String> l = (ArrayList<String>) ufc.getSavedSchools();
		int a = l.size();
		assertEquals("Th size of the list is "+a,a,0);
		
	}

	@Test (expected=Error.class)
	public void testSearchForSchools() {
		ArrayList<University> m = (ArrayList<University>) ufc.searchForSchools("AUGSBURG", null,null,null,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,null);
		assertEquals("list of schools is"+m.size(),m.size(),0);
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("ENGINEERING");
		ArrayList<University> l = (ArrayList<University>) ufc.searchForSchools("UNIVERSITY OF MINNESOTA","MINNESOTA","URBAN","STATE",40000,60000,40,50,400,500,500,600,13000,15000,40,60,8000,10000,80,90,55,65,3,5,3,5,3,5,emphases);
		assertEquals("list of schools is"+l.size(),l.size(),1);
		
	}

	@Test
	public void testDisplayStudentDetails() {
		User u = ufc.displayStudentDetails();
		assertEquals("firstname is"+u.getFirstName(),u.getFirstName(),"Jacob");
		assertEquals("lastname is"+u.getLastName(),u.getLastName(),"Upton");
		assertEquals("username is"+u.getUsername(),u.getUsername(),"JSU");
		assertEquals("password is"+u.getPassword(),u.getPassword(),"Jsu--2019");
		assertEquals("type is"+u.getType(),u.getType(),'u');
		assertEquals("status is"+u.getStatus(),u.getStatus(),'Y');
		
	}

	@Test
	public void testEditStudentProfile() {
		ufc.editStudentProfile("Jacob","Bleh","Jsu--2019");
		User u = ufc.displayStudentDetails();
		assertEquals("new lastname is "+u.getLastName(),u.getLastName(),"Bleh");
		ufc.editStudentProfile("Jacob","Upton","Jsu--2019");
		ufc.editStudentProfile("Jacob","Upton","Bleh");
		assertEquals("new password is "+u.getPassword(),u.getPassword(),"Bleh");
		ufc.editStudentProfile("Jacob","Upton","Jsu--2019");
		ufc.editStudentProfile("Bleh","Upton","Jsu--2019");
		assertEquals("new firstName is "+u.getFirstName(),u.getFirstName(),"Bleh");
		ufc.editStudentProfile("Jacob","Upton","Jsu--2019");	
		
	}

	@Test
	public void testGetSavedSchools() {
		ufc.addUniversityToSavedSchools("AUGSBURG");
		ArrayList <String> l = (ArrayList<String>) ufc.getSavedSchools();
		String a = l.get(0);
		assertEquals("school added is "+a,a,"AUGSBURG");
		ufc.removeUniversityFromSavedSchools("AUGSBURG");
		
	}

	@Test
	public void testGetFiveMatches() {
		DBController d = new DBController();
		University u = d.getAUniversity("UNIVERSITY OF MINNESOTA");
		ArrayList<University> l = (ArrayList<University>) ufc.getFiveMatches("UNIVERSITY OF MINNESOTA");
		assertEquals("list length is "+l.size(),l.size(),5);
	}

}
