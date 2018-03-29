package beans;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private Timestamp lastLogin;
	private Timestamp accountCreated;

/**************************** CONTRUCTORS *****************************/	
	public RefBean() {}
	
	public RefBean(String email, String pass, Timestamp au) {
		this.lastLogin = au;
		this.emailAddress = email;
		this.password = pass;
	}
	
	public RefBean(String fn, String ln, String un, String ea, String pass, String ut, Timestamp ll) {
		this.firstName = fn;
		this.lastName = ln;
		this.username = un;
		this.emailAddress = ea;
		this.password = pass;
		this.userType = ut;
		this.lastLogin = ll;
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
	
	public Timestamp getLastAccountUpdate() {
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
	
	public void setLastLogin(String ll) {		
		this.lastLogin = convertTimestamp(ll);
	}
	
	public void setAccountCreated(String ac) {
		this.accountCreated = convertTimestamp(ac);
	}
	
	public void setLastAccountUpdate(String au) {
		this.accountUpdated = convertTimestamp(au);
	}
	
	private Timestamp convertTimestamp(String ts) {
		try {
		      DateFormat formatter;
		      formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		      Date date = (Date) formatter.parse(ts);
		      Timestamp timeStampDate = new Timestamp(date.getTime());

		      return timeStampDate;
		    } catch (ParseException e) {
		      System.out.println("Exception :" + e);
		      return null;
		    }
	}
}
