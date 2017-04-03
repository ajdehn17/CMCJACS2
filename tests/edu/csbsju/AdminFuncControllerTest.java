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
	  private Admin admin;
	  private Admin admin2;
	  /**
	   * This is a DBController object used to access the Database
	   */
	  private DBController d;
	  /**
	   * This is a universityController object used to access methods 
	   * in the university Controller
	   */
	  private UniversityController uc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		admin = new Admin("cjzins");
		 admin2 = new Admin("Casey", "Zins", "czins", "pass", 'Y', 'u');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdminFuncController() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdminFuncControllerAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUniversity() {
		fail("Not yet implemented");
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
