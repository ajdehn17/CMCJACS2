package edu.csbsju;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdminTest {
	private Admin admin;
	  private Admin admin2;
	@Before
	public void setUpBeforeClass() throws Exception {
		admin = new Admin("cjzins");
	   admin2 = new Admin("Casey", "Zins", "czins", "pass", 'Y', 'u');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdminStringStringStringStringCharChar() {
		assertTrue("name is", admin2.getFirstName() == "Casey");
	    assertTrue("last name is", admin2.getLastName() == "Zins");
	    assertTrue("username is", admin2.getUsername() == "czins");
	     assertTrue("password is", admin2.getPassword().equals("pass"));
	}

	@Test
	public void testAdminString() {
	     assertTrue("username is null", admin.getUsername() == "cjzins");
	}

	@Test
	public void testSetStatus() {
		 admin2.setStatus('N');
		 assertTrue("status is N", admin2.getStatus() == 'N');
	}

	@Test
	public void testSetType() {
		admin2.setType('u');
	    assertTrue("type is u", admin2.returnType() == 'u');
	}

	@Test
	public void testEditAccount() {
		admin.editAccount("Cas", "zi", "czins", "pa", 'N', 'a');
		assertTrue("name is", admin.getFirstName()=="Cas");
		assertTrue("last name is", admin.getLastName()=="zi");
		assertTrue("password is", admin.getPassword()=="pa");
	}

	@Test
	public void testAddAccount() {
		Account a = admin.addAccount("lori", "zins", "lvzins", "pass", 'Y', 'a');
		assertTrue("a name is", a.getFirstName()=="lori");
		assertTrue("a last name is", a.getLastName()=="zins");
		assertTrue("a password is", a.getPassword()=="pass");
		
	}


}
