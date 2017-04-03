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
	   admin2 = new Admin("Casey", "Zins", "czins", "pass", 'u','Y');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdminStringStringStringStringCharChar() {
		Assert.assertTrue("name is", admin2.getFirstName() == "Casey");
	     Assert.assertTrue("last name is", admin2.getLastName() == "Zins");
	     Assert.assertTrue("username is", admin2.getUsername() == "czins");
	     Assert.assertTrue("password is", admin2.getPassword().equals("pass"));
	}

	@Test
	public void testAdminString() {
	     Assert.assertTrue("username is null", admin.getUsername() == "cjzins");
	}

	@Test
	public void testSetStatus() {
		 admin2.setStatus('N');
		 assertTrue("status is N", admin2.getStatus() == 'N');
		 char exp = admin2.getStatus();
		 assertEquals("Status is N", exp, 'N');
	}

	@Test 
	public void testSetType() {
		admin2.setType('u');
	    assertTrue("type is u", admin2.returnType() == 'u');
		char exp = admin2.returnType();
	    assertEquals("type is u", exp, 'u');
	}

	@Test
	public void testEditAccount() {
		admin.editAccount("Cas", "zi", "czins", "pa", 'a','N');
		Assert.assertTrue("name is", admin.getFirstName()=="Cas");
		Assert.assertTrue("last name is", admin.getLastName()=="zi");
		Assert.assertTrue("password is", admin.getPassword()=="pa");
	}

	@Test
	public void testAddAccount() {
		Account a = admin.addAccount("lori", "zins", "lvzins", "pass", 'a','Y');
		Assert.assertTrue("a name is", a.getFirstName()=="lori");
		Assert.assertTrue("a last name is", a.getLastName()=="zins");
		Assert.assertTrue("a password is", a.getPassword()=="pass");
		
	}


}
