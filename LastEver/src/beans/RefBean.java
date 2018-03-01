package beans;

/**
 * The UserBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class RefBean {
	
	// User bean parameters
	private String id;
	private String username;
	private String emailAddress;
	private String password;
	private String userType;

/**************************** CONTRUCTORS *****************************/	
	public RefBean() {
	}
	
	public RefBean(String email, String pass) {
		this.emailAddress = email;
		this.password = pass;
	}
	
	public RefBean(String un, String ea, String pass, String ut) {
		this.username = un;
		this.emailAddress = ea;
		this.password = pass;
		this.userType = ut;
	}

/**************************** GETTERS *****************************/
	public String getId() {
		return this.id;
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
	
	public void setId(String id) {
		this.id = id;
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
}
