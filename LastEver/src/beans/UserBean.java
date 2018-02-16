package beans;

public class UserBean {
	
	private String username;
	private String emailAddress;
	private String password;
	private String userType;
	
	public UserBean() {
	}
	
	public UserBean(String email, String pass) {
		this.emailAddress = email;
		this.password = pass;
	}
	
	public UserBean(String un, String ea, String pass, String ut) {
		this.username = un;
		this.emailAddress = ea;
		this.password = pass;
		this.userType = ut;
	}

/**************************** GETTERS *****************************/
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
	
/**************************** SETTERS *****************************/
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
