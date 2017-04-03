package edu.csbsju;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountControllerTest.class, AccountTest.class, AccountUITest.class, AdminFuncControllerTest.class,
		AdminTest.class, DBControllerTest.class, SearchControllerTest.class, UniversityControllerTest.class,
		UniversityTest.class, UserFuncControllerTest.class, UserTests.class })
public class AllTests {

}