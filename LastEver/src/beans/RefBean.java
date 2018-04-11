package beans;

import java.sql.Timestamp;

/**
 * The RefBean class is meant for passing referee user information
 * between DAOs and Servlets
 */
public class RefBean {
	
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
	private String refId;

/**************************** CONTRUCTORS *****************************/	
	public RefBean() {}
	
	public RefBean(String email, String pass) {
		this.emailAddress = email;
		this.password = pass;
	}
	
	public RefBean(String fn, String ln, String un, String ea, String pass, String ut) {
		this.firstName = fn;
		this.lastName = ln;
		this.username = un;
		this.emailAddress = ea;
		this.password = pass;
		this.userType = ut;
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
	
	public Timestamp getAccountUpdated() {
		return this.accountUpdated;
	}
	
	public String getRefId() {
		return this.refId;
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
	
	public void setAccountUpdated(Timestamp au) {
		this.accountUpdated = au;
	}
	
	public void setRefId(String rID) {
		this.refId = rID;
	}
	
}
