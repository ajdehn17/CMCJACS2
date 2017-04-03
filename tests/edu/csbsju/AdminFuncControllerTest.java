package edu.csbsju;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdminFuncControllerTest {

	 /**
	   * a Admin object instance variable. Used to store the current
	   * admin object, and to be able to access methods in the Admin class
	   */
	  private AdminFuncController a;
	  private AdminFuncController a2;
	  private Admin ad;
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
		uc = new U
		d = new DBController();
		ad = new Admin("Casey", "Zins", "czins", "pass", 'Y', 'u');
		a = new AdminFuncController();
		a2 = new AdminFuncController(ad);
		
	}

	@After
	public void tearDown() throws Exception {
		d.r
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
		assertTrue("all accounts retrieved", a.getAccounts() ==d.getAccounts());
	}

	@Test
	public void testAddUniversity() {
		admin.addUniversity("SJU", "MN", "COLLEGEVILLE", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		Assert.assertTrue("UniversityAdded", d.getUniversity("SJU").getUniversityName()== "SJU");
	}

	@Test
	public void testDisplayAccounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUniversities() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayUniversities() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeactivate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditUniversity() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEmphases() {
		fail("Not yet implemented");
	}

}
