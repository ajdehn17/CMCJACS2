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
		univDBlib = new UniversityDBLibrary("jacs","jacs","csci230");
		
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testAdminFuncController() {
		AdminFuncController ac = new AdminFuncController();
		Admin actual = ac.getAdmin();
		assertEquals("The account object is null", null, actual);
	}

	@Test
	public void testAdminFuncControllerAdmin() {
		Admin actual =a2.getAdmin();
		assertEquals("The account object is null", ad, actual);
	}

	@Test
	public void testGetAccounts() {
		ArrayList<Account> acct = d.getAccounts();
		int x = acct.size();
		assertEquals("all accounts retrieved", 6, x);
	}

	@Test
	public void testAddUniversity() {
		int i = 0;
		a2.addUniversity("PPP", "MN", "COLLEGEVILLE", " ", 5, 6.0, 10, 13, 6.0, 6.0, 7, 1.0, 9.0, 5, 6, 7);
		if(d.getAUniversity("PPP") != null){
			i++;
		}
		univDBlib.university_deleteUniversity("PPP");
		assertEquals("UniversityAdded", 1, i++);
	}

	@Test
	public void testDisplayAccounts() {
		ArrayList<Account> ar = d.getAccounts();
	    String s = "";
	    for(Account u: ar){
	      u.displayStudent();
	      s = s + u.displayStudent(); 
	    }
		assertEquals("accounts displayed", a2.displayAccounts(),s);
	}

	@Test
	public void testGetUniversities() {
		ArrayList<University> ar = d.getAllUniversities();
		int x = ar.size();
		assertEquals("all accounts retrieved", 183, x);
		}

	@Test
	public void testDisplayUniversities() {
		ArrayList<University> a = a2.getUniversities();
	    String k = "";
	    for(University u: a){
	    	k = k = u.printString();
	    }
		assertEquals("universities displayed",a2.displayUniversities(),k);
	}

	@Test
	public void testFindAccount() {
		Account a = a2.findAccount("nadmin");
		assertEquals("account found",a.getUsername(), "nadmin");
	}

	@Test
	public void testViewAccount() {
		Account a = a2.viewAccount("nadmin");
		assertEquals("account found",a.getUsername(), "nadmin");
	}

	@Test
	public void testEditAccount() {
		
	}

	@Test
	public void testAddAccount() {
		
	}

	@Test
	public void testAddEmphases() {
		int exp = 1;
		int actual = 0;
		a2.addEmphases("UNIVERSITY OF MINNESOTA", "Science");
		University u = d.getAUniversity("UNIVERSITY OF MINNESOTA");
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
		a2.removeEmphases("UNIVERSITY OF MINNESOTA", "Science");
		University u = d.getAUniversity("UNIVERSITY OF MINNESOTA");
		ArrayList<String> emp = (ArrayList<String>) u.getEmphases();
		for(String e: emp)
			if(e.equals("Science")){
				actual++;
			}
		assertEquals("emphases added",exp,actual);
	}
	}
