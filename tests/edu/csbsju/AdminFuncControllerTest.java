package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dblibrary.project.csci230.UniversityDBLibrary;

public class AdminFuncControllerTest {

	 /**
	   * a Admin object instance variable. Used to store the current
	   * admin object, and to be able to access methods in the Admin class
	   */
	  private AdminFuncController a;
	  private AdminFuncController a2;
	  private Admin ad;
	  private UniversityDBLibrary univDBlib;
	  /**
	   * This is a DBController object used to access the Database
	   */
	  private DBController d;
	 
	  /**
	   * This is a universityController object used to access methods 
	   * in the university Controller
	   */
	  private UniversityController uc;
	@Before
	public void setUp() throws Exception {
		uc = new UniversityController();
		d = new DBController();
		ad = new Admin("Casey", "Zins", "czins", "pass", 'a', 'Y');
		a = new AdminFuncController();
		a2 = new AdminFuncController(ad);
		
	}

	@After
	public void tearDown() throws Exception {
		univDBlib.university_deleteUniversity("x");
	}

	@Test
	public void testAdminFuncController() {
		Admin actual = a.getAdmin();
		assertEquals("The account object is null", null, actual);
	}

	@Test
	public void testAdminFuncControllerAdmin() {
		Admin actual =a2.getAdmin();
		assertEquals("The account object is null", ad, actual);
	}

	@Test
	public void testGetAccounts() {
		assertTrue("all accounts retrieved", a2.getAccounts() == d.getAccounts());
	}

	@Test
	public void testAddUniversity() {
		a2.addUniversity("SJU", "MN", "COLLEGEVILLE", " ", 5, 6.0, 10, 13, 6.0, 6.0, 7, 1.0, 9.0, 5, 6, 7);
		assertTrue("UniversityAdded", d.getAUniversity("SJU").getUniversityName()== "SJU");
	}

	@Test
	public void testDisplayAccounts() {
		ArrayList<Account> ar = d.getAccounts();
	    String s = "";
	    for(Account u: ar){
	      u.displayStudent();
	      s = s + u.displayStudent(); 
	    }
		assertTrue("accounts displayed", a2.displayAccounts()== s);
	}

	@Test
	public void testGetUniversities() {
		assertTrue("universities retrieved", a2.getUniversities()==d.getAllUniversities());
	}

	@Test
	public void testDisplayUniversities() {
		ArrayList<University> ab = d.getAllUniversities();
	    String k = "";
	    for(University u: ab){
	      u.printString();
	      k = k + u;
	    }
		assertTrue("universities displayed",a2.displayUniversities()== k);
		
	}

	@Test
	public void testFindAccount() {
		assertTrue("account found",d.findAccount("nAdmin").equals(a2.findAccount("nAdmin")));
	}

	@Test
	public void testViewAccount() {
		assertTrue("account viewed", d.findAccount("nAdmin").equals(a2.viewAccount("nAdmin")));
	}

	@Test
	public void testEditAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEmphases() {
		int exp = 1;
		int actual = 0;
		a2.addEmphases("SJU", "Science");
		University u = d.getAUniversity("SJU");
		ArrayList<String> emp = (ArrayList<String>) u.getEmphases();
		for(String e: emp)
			if(e.equals("Science")){
				actual++;
			}
		assertEquals("emphases added",exp,actual);
	}

	@Test
	public void testRemoveEmphases() {
		int exp = 0;
		int actual = 0;
		a2.addEmphases("SJU", "Science");
		a2.removeEmphases("SJU", "Science");
		University u = d.getAUniversity("SJU");
		ArrayList<String> emp = (ArrayList<String>) u.getEmphases();
		for(String e: emp)
			if(e.equals("Science")){
				actual++;
			}
		assertEquals("emphases added",exp,actual);
	}
	}
