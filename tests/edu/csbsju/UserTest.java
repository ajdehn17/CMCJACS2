package edu.csbsju;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User a,b,c;
	@Before
	public void setUp() throws Exception {
		a = new User("Samantha","Schmidgall","SAS","sas21",'u','Y');
		b = new User("Suzie","Queue","SIQ","suzE",'u','N');
	@Test
	public void testDisplayStudent() {
		String expResult = "[FirstName=Samantha, LastName=Schmidgall, Username=SAS, Password=sas21, Type=u, Status=Y]\n";
		String result = a.displayStudent();
		assertEquals("The User is " + expResult, expResult, result );
	}

	@Test
	public void testUserStringStringStringStringCharChar() {
		a = new User("Suzie","Queue","SIQ","suzE",'Y','u');
		String p = a.getPassword();
		assertEquals("Password is " + p,p, "suzE");
		String u = a.getUsername();
		assertEquals("UserName is " + u,u, "SIQ");
		String f = a.getFirstName();
		assertEquals("First Name is " + f,f, "Suzie");
		String l = a.getLastName();
		assertEquals("Last Name is " + l,l, "Queue");
		char t = a.getType();
		assertEquals("Type is " + t,t, 'u');
		char s = a.getStatus();
		assertEquals("Status is " + s,s, 'Y');
	}

	@Test
	public void testUserString() {
		a = new User("Samantha");
		String p = a.getPassword();
		assertEquals("Password is " + p,p, null);
		String u = a.getUsername();
		assertEquals("UserName is " + u,u, "Samantha");
		String f = a.getFirstName();
		assertEquals("First Name is " + f,f, null);
		String l = a.getLastName();
		assertEquals("Last Name is " + l,l, null);
		char t = a.getType();
		assertEquals("Type is " + t,t, '\0');
		char s = a.getStatus();
		assertEquals("Status is " + s,s, '\0');
	}

	@Test
	public void testDisplaySavedSchoolsError() {
		String e = a.displaySavedSchools();
		assertEquals("error is: "+,e,e,"There are no saved Schools in your Saved Schools List.")
		
	}

	@Test
	public void testDisplaySearchError() {
		String e = a.displaySearchError();
		assertEquals("error is: "+,e,e,"No schools were found. Please try again.");
	}

	@Test
	public void testEditStudentProfile() {
		
	
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
	}

}
