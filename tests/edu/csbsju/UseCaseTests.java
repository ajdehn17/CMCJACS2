/**
 * 
 */
package edu.csbsju;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author jsupton
 *
 */
public class UseCaseTests {

	private AccountUI ac;
	private UserUI u;
	private AdminUI a;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.ac = new AccountUI();
		this.u = new UserUI();
		this.a = new AdminUI();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void UseCase1LoginSuccessful() {
		Account a = ac.logOn("juser","user");
		String p = a.getPassword();
		assertEquals("Password is " + p,p, "user");
		String u = a.getUsername();
		assertEquals("UserName is " + u,u, "juser");
		String f = a.getFirstName();
		assertEquals("First Name is " + f,f, "Jackson");
		String l = a.getLastName();
		assertEquals("Last Name is " + l,l, "User");
		char t = a.getType();
		assertEquals("Type is " + t,t,'u');
		char s = a.getStatus();
		assertEquals("Status is " + s,s,'Y');
	}
	
	@Test (expected = Error.class)
	public void UseCase1LoginInvalidUsername() {
		Account a = ac.logOn("IMAD", "user");
		assertEquals("Account is " + a,a,null);
	}
	
	@Test (expected = Error.class)
	public void UseCase1LoginInvalidPassword() {
		Account a = ac.logOn("juser", "IMAD");
		assertEquals("Account is " + a,a,null);
	}
	
	@Test (expected = Error.class)
	public void UseCase1LoginDeactivated() {
		Account a = ac.logOn("luser", "luser");
		assertEquals("Account is " + a,a,null);
	}
	
	@Test
	public void UseCase2Search() {
		int result = 0;
		int expected = 16;
		List<String> schools = Arrays.asList("BARNARD","BARUCH","CCNY","COLUMBIA",
				"COOPER UNION","EASTMAN SCHOOL OF MUSIC","FORDHAM","JUILLIARD","NEW YORK UNIVERSITY",
				"POLYTECHNIC INSTITUTE OF NEWYORK","PRATT","QUEENS","ST JOHNS UNIVERSITY","SUNY BUFFALO",
				"TOURO","UNIVERSITY OF ROCHESTER");
		List<University> list = u.searchForSchools(null,"NEW YORK","URBAN",null,10000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,null);
		for(University u:list){
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		result = 0;
		for(University u:list){
			for(String s:schools)
			if(u.getUniversityName().equals(s));
				result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
	
	}
	
	@Test
	public void UseCase2SearchBrowseAllSchools() {
		int result = 0;
		int expected = 183;
		DBController d = new DBController();
		List<University> y = d.getAllUniversities();
		List<String> schools = new ArrayList<String>();
		for(University q: y){
			String name = q.getUniversityName();
			schools.add(name);
		}
		List<University> list = u.searchForSchools(null,null,null,null,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,null);
		for(University u:list){
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		result = 0;
		for(University u:list){
			for(String s:schools)
			if(u.getUniversityName().equals(s));
				result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
	}
	
	@Test
	public void UseCase2SearchNoSchoolsFound() {
		Account a = ac.logOn("juser", "user");
		if(a!= null){
			this.u = new UserUI(new User("Jackson","User","juser","user",'u','Y'));
		}
		int result = 0;
		int expected = 0;
		List<String> schools = Arrays.asList();
		List<University> list = u.searchForSchools("JACOBSTON","JACOB",null,null,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,null);
		if(list!=null){
			for(University u:list){
				result++;
			}
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		result = 0;
		for(University u:list){
			for(String s:schools)
			if(u.getUniversityName().equals(s));
				result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
	}
	
	@Test
	public void UseCase2SearchOnlyUpperBounds() {
		int result = 0;
		int expected = 3;
		List<String> schools = Arrays.asList("COLLEGE OF SAINT BENEDICT","CSBSJU","U OF M DULUTH");
		List<University> list = u.searchForSchools(null,null,null,null,0,10000,0,50,0,400,0,400,0,15000,0,50,0,1000,0,100,0,100,0,5,0,5,0,5,null);
		for(University u:list){
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		result = 0;
		for(University u:list){
			for(String s:schools)
			if(u.getUniversityName().equals(s));
				result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
	}
	
	@Test
	public void UseCase2SearchOnlyLowerBounds() {
		int result = 0;
		int expected = 2;
		List<String> schools = Arrays.asList("BRYN MAWR,"+"MOUNT HOLYOKE");
		List<University> list = u.searchForSchools(null,null,null,null,10000,0,75,0,400,0,400,0,15000,0,55,0,500,0,50,0,50,0,3,0,3,0,3,0,null);
		for(University u:list){
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		result = 0;
		for(University u:list){
			for(String s:schools)
			if(u.getUniversityName().equals(s));
				result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
	}
	
	@Test
	public void UseCase2SearchEqualBounds() {
		int result = 0;
		int expected = 1;
		List<String> schools = Arrays.asList("UNIVERSITY OF MINNESOTA");
		List<University> list = u.searchForSchools(null,null,null,null,40000,40000,45,45,490,490,557,557,13772.0,13772.0,50.0,50.0,8500,8500,80,80,60,60,4,4,3,3,4,5,null);
		for(University u:list){
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
		result = 0;
		for(University u:list){
			for(String s:schools)
			if(u.getUniversityName().equals(s));
				result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
	}
	
	@Test
	public void UseCase3ViewStudentProfile() {
		Account a = ac.logOn("juser", "user");
		String x = a.displayStudent();
		String result = "[FirstName=Jackson, LastName=User, Username=juser, Password=user, Type=u, Status=Y]\n";
		assertEquals("The student is " + x, x, result);
		String p = a.getPassword();
		assertEquals("Password is " + p,p, "user");
		String u = a.getUsername();
		assertEquals("UserName is " + u,u, "juser");
		String f = a.getFirstName();
		assertEquals("First Name is " + f,f, "Jackson");
		String l = a.getLastName();
		assertEquals("Last Name is " + l,l, "User");
		char t = a.getType();
		assertEquals("Type is " + t,t,'u');
		char s = a.getStatus();
		assertEquals("Status is " + s,s,'Y');	
	}
	
	@Test
	public void UseCase4ManageSavedSchools() {
		int result =0;
		int exp = 1;
		Account a = ac.logOn("juser", "user");
		if(a!= null){
			this.u = new UserUI(new User("Jackson","User","juser","user",'u','Y'));
		}
		List<String> schools = u.getSavedSchools();
		List<String> expected = Arrays.asList("UNIVERSITY OF MINNESOTA");
		for(String u:schools){
			result++;
		}
		assertEquals("The length of the list is:" + exp, exp, result);
		result = 0;
		for(String u:schools){
			for(String s:expected)
			if(u.equals(s));
				result++;
		}
		assertEquals("The length of the list is:" + exp, exp, result);
	
	}
	
	@Test
	public void UseCase4ManageSavedSchoolsNone() {
		int result =0;
		int exp = 0;
		Account a = ac.logOn("JSU", "Jsu--2019");
		if(a!= null){
			this.u = new UserUI(new User("Jacob","Upton","JSU","Jsu--2019",'u','Y'));
		}
		List<String> schools = u.getSavedSchools();
		List<String> expected = Arrays.asList();
		if(schools!=null){
			for(String u:schools){
				result++;
			}
		}
		assertEquals("The length of the list is:" + exp, exp, result);
		result = 0;
		if(schools!=null){
			for(String u:schools){
				for(String s:expected)
				if(u.equals(s));
					result++;
			}
		}
		assertEquals("The length of the list is:" + exp, exp, result);
	
	}
	
	@Test
	public void UseCase5ManageUniversities() {
		int result = 0;
		int expected = 183;
		Account acc = ac.logOn("nadmin", "admin");
		if(acc!= null){
			this.a = new AdminUI(new Admin("Noreen","Admin","nadmin","admin",'a','Y'));
		}
		List<University> list = a.getUniversities();
		for(University u:list){
			result++;
		}
		assertEquals("The length of the list is:" + expected, expected, result);
	}
	
	@Test
	public void UseCase6ManageUsers() {
		
	}
	
	@Test
	public void UseCase7ViewSearchedUniversity() {
		
	}
	
	@Test
	public void UseCase8SaveToSavedSchoolsSuccessful() {
		
	}
	
	@Test
	public void UseCase8SaveToSavedSchoolsDuplicateSchoo() {
		
	}
	
	@Test
	public void UseCase9SaveEditedUser() {
		
	}
	
	@Test
	public void UseCase10RemoveSavedUniversity() {
		
	}
	
	@Test
	public void UseCase11ViewUniversityDetails() {
		
	}
	
	@Test
	public void UseCase12EditUniversity() {
		
	}
	
	@Test
	public void UseCase13AddUniversity() {
		
	}
	
	@Test
	public void UseCase13AddUniversityDuplicateSchool() {
		
	}
	
	@Test
	public void UseCase14AddUser() {
		
	}
	
	@Test
	public void UseCase14AddUserDuplicateUsername() {
		
	}
	
	@Test
	public void UseCase15EditUser() {
		
	}
	
	@Test
	public void UseCase15EditUserInvalidUsername() {
		
	}
	
	@Test
	public void UseCase16Deactivate() {
		
	}
	
	@Test
	public void UseCase16DeactivateDeactivatedAccount() {
		
	}
	
	@Test
	public void UseCase17Logout() {
		
	}

}
