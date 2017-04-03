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
		Assert.assertTrue("name is", admin2.getFirstName() == "Casey");
	     Assert.assertTrue("last name is", admin2.getLastName() == "Zins");
	     Assert.assertTrue("username is", admin2.getUsername() == "czins");
	     Assert.assertTrue("password is", admin2.getPassword().equals("pass"));
	     Assert.assertTrue("Type is", admin2.getType() == 'Y');
	     Assert.assertTrue("status is", admin2.getStatus() == 'u');
	}

	@Test
	public void testAdminString() {
		 Assert.assertTrue("name is null", admin.getFirstName() == null);
	     Assert.assertTrue("last name is null", admin.getLastName() == null);
	     Assert.assertTrue("username is null", admin.getUsername() == "cjzins");
	     Assert.assertTrue("password is null", admin.getPassword() == null);
	     Assert.assertTrue("Type is null", admin.getType() == '\0');
	     Assert.assertTrue("status is null", admin.getStatus() == '\0');
	}

	@Test
	public void testSetStatus() {
		 admin2.setStatus('N');
		 Assert.assertTrue("status is N", admin2.isActive() == false);
		 admin.setStatus('Y');
		 Assert.assertTrue("status is Y", admin.isActive()==true);
	}

	@Test
	public void testSetType() {
		admin.setType('u');
	    Assert.assertTrue("type is u", admin.returnType() == 'u');
	    admin2.setType('a');
	    Assert.assertTrue("type is a", admin2.returnType() == 'a');
	}

	@Test
	public void testEditAccount() {
		admin.editAccount("Cas", "zi", "czins", "pa", 'N', 'a');
		Assert.assertTrue("name is", admin.getFirstName()=="Cas");
		Assert.assertTrue("last name is", admin.getLastName()=="zi");
		Assert.assertTrue("password is", admin.getPassword()=="pa");
		Assert.assertTrue("status is", admin.getStatus()=='N');
		Assert.assertTrue("status is", admin.getType()=='a');
	}

	@Test
	public void testAddAccount() {
		Account a = admin.addAccount("lori", "zins", "lvzins", "pass", 'Y', 'a');
		Assert.assertTrue("name is", a.getFirstName()=="lori");
		Assert.assertTrue("last name is", a.getLastName()=="zins");
		Assert.assertTrue("password is", a.getPassword()=="pass");
		Assert.assertTrue("status is", a.getStatus()=='Y');
		Assert.assertTrue("status is", a.getType()=='a');
		
	}


}
