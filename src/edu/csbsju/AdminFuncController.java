package edu.csbsju;

import java.util.*;

/**
 * This class is the controller for all the functions of an administrator.
 * This class deals with shifting data and calling the Database, as well
 * as sending data back to the UserInterface to display to the Admin. 
 * @author cjzins
 * @version 03-21-17
 */
public class AdminFuncController {
  
  /**
   * a Admin object instance variable. Used to store the current
   * admin object, and to be able to access methods in the Admin class
   */
  private Admin admin;
  /**
   * This is a DBController object used to access the Database
   */
  private DBController d;
  /**
   * This is a universityController object used to access methods 
   * in the university Controller
   */
  private UniversityController uc;
  
  /**
   * This default constructor initiates the Admin to null. It creates
   * a new DBController object as well as a new UniversityController object
   * and sets those equal to their corresponding instance variable
   */
  public AdminFuncController() {
    super();
    this.admin = null;
    this.d = new DBController();
    this.uc = new UniversityController();
  }
  
  /**
   * This constructor initiates the Admin instance variable
   * to a specific Admin object. It creates a new DBController object as well 
   * as a new UniversityController object and sets those equal to their 
   * corresponding instance variable
   * @param admin an admin object
   */
  public AdminFuncController(Admin admin) {
    super();
    this.admin = admin;
    this.d = new DBController();
    this.uc = new UniversityController();
  }
  
  /**
   * This method calls on the database and gets all of the accounts that are
   * in the system. It then returns an arrayList of those accounts
   * @return an array list of the accounts
   */
  public ArrayList<Account> getAccounts(){
    return d.getAccounts();
  }
  
  /**
   * This method allows an admin to be able to edit a university object
   * The changes are sent to the Database to save the changes
   * @param universityName The name of a university
   * @param location The location of a university
   * @param state The state of a university
   * @param control The control of a university
   * @param numberOfStudents the number of students at a university
   * @param percentFemale the percent female of a university
   * @param satVerbal SAT of a university
   * @param satMath SAT of a university
   * @param expenses The Expenses of a university
   * @param financialAid the Financial aid at a university
   * @param numberOfApplicants Number of Applicants of a university
   * @param percentAdmitted The percent admitted of a university
   * @param percentEnrolled The percent Enrolled of a university
   * @param academicScale The academic scale of a university
   * @param socialScale Social scale of a university
   * @param qualityOfLife Quality of life of a university
   * @boolean a value representing a boolean of a successful addition
   * @throws an error if the addition was unsuccessful
   */
  public boolean addUniversity(String universityName, String state, String location, String control, int numberOfStudents,
                            double percentFemale, int satVerbal, int satMath, double expenses, double financialAid, int numberOfApplicants,
                            double percentAdmitted, double percentEnrolled, int academicScale, int socialScale, int qualityOfLife){
    boolean b = uc.addUniversity(universityName, state, location, control, numberOfStudents,
                    percentFemale, satVerbal, satMath, expenses, financialAid, numberOfApplicants,
                    percentAdmitted, percentEnrolled, academicScale, socialScale, qualityOfLife);
    if(!b){
 	   throw new Error("The addtition was unsuccessful");
    }
    return b;
  }
  
  /**
   * This is a method used to display the accounts of all of the Account
   * objects that are in the database.
   * @return a string with all detailed information about all accounts
   */
  public String displayAccounts(){
    ArrayList<Account> a = this.getAccounts();
    String s = "";
    for(Account u: a){
      u.displayStudent();
      s = s + u.displayStudent(); 
    }
    return s;
  }
  
  /**
   * This method calls on the database and gets all of the universities that are
   * in the system. It then returns an arrayList of those Universities
   * @return and array list of universities
   */
  public ArrayList<University> getUniversities(){
    return d.getAllUniversities();
  }
  
  /**
   * This is a method used to display the accounts of all of the University
   * objects that are in the database.
   * @return a string with all detailed information about all universities
   */
  public String displayUniversities(){
    ArrayList<University> a = this.getUniversities();
    String k = "";
    for(University u: a){
    	k = k + u.printString();
    }
    return k;
  }
  
  /**
   * This returns the current admin object in this class
   * @return the current admin instance variable object
   */
  public Admin getAdmin(){
	  return admin;
  }
  
  /**
   * This method if a void method that takes an account object in as a parameter
   * It uses the account object and changes the status of the Account object to 
   * deactivated.
   * @param a an Account object
   * @return a char representing the new deactivated account
   */
  public char deactivate(Account a){
    d.deactivate(a);
    return 'N';
    
  }
  
  /**
   * This method takes a username as a parameter, then searches the database
   * to find an Account that matches the username. If there is no account that matches
   * the method returns null, otherwise the method returns the Account object with 
   * that specified username.
   * @param u String, a username of an Account
   * @return an Account object of the username parameter
   */
  public Account findAccount(String u){
    Account a = d.findAccount(u);
    return a;
  }
  
  /**
   * This Method displays all of the information about a specified Account object.
   * It gathers all of the attributes for an account object and then displays
   * that information for the user to see
   * @param name a string representing the username of an account
   * @return an Account object. Null if the username doesn't exist in the system.
   */
  public Account viewAccount(String name){
    Account a = this.findAccount(name);
    return a;
  }
  
  /**
   * This method allows an admin to be able to edit a university object.
   * The parameters are used to update the specific university object.
   * The changes are sent to the Database to save the changes.
   * @param universityName The name of a university
   * @param location The location of a university
   * @param state The state of a university
   * @param control The control of a university
   * @param numberOfStudents the number of students at a university
   * @param percentFemale the percent female of a university
   * @param satVerbal SAT of a university
   * @param satMath SAT of a university
   * @param expenses The Expenses of a university
   * @param financialAid the Financial aid at a university
   * @param numberOfApplicants Number of Applicants of a university
   * @param percentAdmitted The percent admitted of a university
   * @param percentEnrolled The percent Enrolled of a university
   * @param academicScale The academic scale of a university
   * @param socialScale Social scale of a university
   * @param qualityOfLife Quality of life of a university
   * @return a boolean noting if an edit was successful
   * @throws an error if the edit was unsuccessful
   */
  public boolean editUniversity(String universityName, String state, String location, String control, int numberOfStudents,
                             double percentFemale, int satVerbal, int satMath, double expenses, double financialAid, int numberOfApplicants,
                             double percentAdmitted, double percentEnrolled, int academicScale, int socialScale, int qualityOfLife){
   
	  boolean b = uc.editUniversity( universityName, state, location, control, numberOfStudents,
           percentFemale, satVerbal, satMath, expenses, financialAid, numberOfApplicants,
	           percentAdmitted, percentEnrolled, academicScale, socialScale, qualityOfLife);    
	   if(!b){
	 	   throw new Error("The edit was unsuccessful");
	   }
	   return b;
  }
  
  /**
   * This method is used to edit the account. It takes all attributes of an account
   * object in as parameters and then, using the username as a key, it finds the right account
   * object in the database and then updates it
   * @param firstname the firstname of the account
   * @param lastname the lastname of the account
   * @param username the username of the account
   * @param password the password of the account
   * @param type the type of the account
   * @param status the status of the account
   * @return boolean representing a successful edit
   * @throws an error if the edit was not successful.
   */
  public boolean editAccount(String firstname, String lastname, String username, String password, char type, char status){
    boolean b = d.editAccount(username, firstname, lastname, password, type, status);
    admin.editAccount(username, firstname, lastname, password, type, status);
    if(!b){
    	throw new Error("The account object could not be edited. Please try again");
    }
    return b;
   }
  
  /**
   * This method is used to add a new the account. It takes in all attributes
   * of an account object and then uses those to create a new account. This method
   * will fail if an account already exists with the same username
   * @param firstname the firstname of the new account
   * @param lastname the lastname of the new account
   * @param username the username of the new account
   * @param password the password of the new account
   * @param type the type of the new account
   * @param status the status of the new account
   * @return boolean representing a successful edit
   * @throws an error if the edit was not successful.
   */
  public boolean addAccount(String firstname, String lastname, String username, String password, char type, char status){
     Account a = admin.addAccount(firstname, lastname, username, password, type, status);
     boolean b = d.addAccount(a.getFirstName(),a.getLastName(),a.getUsername(),a.getPassword(),a.getType(),a.getStatus());
     if(!b){
     	throw new Error("The account object could not be added. Please try again");
     }
     return b;
  }
  
  /**
   * This adds an emphases for the specified university object.
   * The parameter string is then added to the University's emphases
   * @param universityName a name of a university
   * @param emphases an emphases for a university
   * @return a boolean stating if an addition was a success
   * @throws an error if the emphases could not be added
   * */
  public boolean addEmphases(String universityName,String emphases ){
     int i = uc.addEmphases(universityName, emphases);
     boolean b = true;
     if(i==-1){
    	 b = false;
     }
     if(!b){
    	 throw new Error("The addition of an emphases was unsuccessful");
     }
     return b;
  }
  
  /**
   * This removes an emphases for the specified university object.
   * The parameter string is then removed to the University's emphases
   * @param universityName a name for the specified university
   * @param emphases an emphases for the specified university
   * @return a boolean stating if an addition was a success
   * @throw an error if the emphases could not be removed
   */
  public boolean removeEmphases(String universityName,String emphases ){
    int i = uc.removeEmphases(universityName, emphases);
    boolean b = true;
    if(i==-1){
   	 b = false;
    }
    if(!b){
   	 throw new Error("The removal of an emphases was unsuccessful");
    }
    return b;

  }
  
}
