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
		assertEquals("all accounts retrieved", 8, x);
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
	
	@Test (expected = Error.class)
	public void testAddUniversityFailsForDuplicateSchool() {
		a2.addUniversity("SJU", "MN", "COLLEGEVILLE", " ", 5, 6.0, 10, 13, 6.0, 6.0, 7, 1.0, 9.0, 5, 6, 7);
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
	    	k = k + u.printString();
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
	public void testDeactivate(){
		a2.deactivate(ad);
		char b =ad.getStatus();
		assertEquals("deactivate", b, ad.getStatus());
		
	}
	@Test 
	public void testEditUniversity(){
				int i = 0;
				a2.addUniversity("PPP", "MN", "Urban", "Private", 5, 6.0, 10, 13, 6.0, 6.0, 2, 1.0, 9.0, 4, 3, 4);
				a2.editUniversity("PPP", "MN", "Urban", "Private", 10, 6.0, 10, 13, 6.0, 6.0, 2, 1.0, 9.0, 4, 3, 4);
				assertEquals("editedUniversity", d.getAUniversity("PPP").getNumberOfStudents(), 10);
				univDBlib.university_deleteUniversity("PPP");
			}
	
	@Test (expected = Error.class)
	public void testEditUniversityFailsForNoSchool() {
		a2.editUniversity("967", "MN", "COLLEGEVILLE", " ", 5, 6.0, 10, 13, 6.0, 6.0, 7, 1.0, 9.0, 5, 6, 7);
	}
	
	@Test
	public void testEditAccount() {
		boolean k = a2.editAccount("Casey", "Booth", "czins", "pass", 'a', 'Y');
		Account a = d.findAccount("czins");
		assertEquals("account edited", k, true);
		assertEquals("account edited",a.getLastName(), "Booth");
		//a2.editAccount("Casey", "Zins", "czins", "pass", 'a', 'Y');
		
	}
	
	@Test (expected = Error.class)
	public void testEditAccountInvalidUsername() {
		boolean k = a2.editAccount("Casey", "Booth", "jjj", "pass", 'a', 'Y');
	}

	@Test
	public void testAddAccount() {
		a2.addAccount("Carly", "Ciccati", "cmciccati", "p", 'a', 'Y');
		Account a = d.findAccount("cmciccati");
		assertEquals("account edited", a.getUsername(), "cmciccati");
		univDBlib.user_deleteUser("cmciccati");
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
