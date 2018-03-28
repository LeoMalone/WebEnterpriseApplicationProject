package beans;


import java.sql.Timestamp;

/**
 * The UserBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class UserBean {
	
	// User bean parameters
	private int emailValidated;
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String emailAddress;
	private String password;
	private String userType;
	private Timestamp accountUpdated;
	private Timestamp lastLogin;
	private Timestamp accountCreated;

/**************************** CONTRUCTORS *****************************/	
	public UserBean() {
	}
	
	public UserBean(String email, String pass, Timestamp au) {
		this.lastLogin = au;
		this.emailAddress = email;
		this.password = pass;
	}
	
	public UserBean(String fn, String ln, String un, String ea, String pass, String ut, Timestamp ll) {
		this.firstName = fn;
		this.lastName = ln;
		this.username = un;
		this.emailAddress = ea;
		this.password = pass;
		this.userType = ut;
		this.lastLogin = ll;
		this.accountCreated = ll;
	}

/**************************** GETTERS *****************************/
	public int getEmailValidated() {
		return this.emailValidated;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public Timestamp getLastLogin() {
		return this.lastLogin;
	}
	
	public Timestamp getAccountUpdated() {
		return this.accountUpdated;
	}
	
	public Timestamp getAccountCreated() {
		return this.accountCreated;
	}

/**************************** SETTERS *****************************/
	public void setEmailValidated(int i) {
		this.emailValidated = i;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public void setUsername(String un) {
		this.username = un;
	}
	
	public void setEmail(String email) {
		this.emailAddress = email;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setUserType(String ut) {
		this.userType = ut;
	}
	
	public void setLastLogin(Timestamp ll) {		
		this.lastLogin = ll;
	}
	
	public void setAccountCreated(Timestamp ac) {
		this.accountCreated = ac;
	}
	
	public void setAccountUpdated(Timestamp au) {
		this.accountUpdated = au;
	}
}
