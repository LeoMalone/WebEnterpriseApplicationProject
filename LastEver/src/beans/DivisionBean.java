package beans;

/**
 * The DivisionBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class DivisionBean {
	
	//Division bean parameters
	private String divisionId;
	private String divisionName;	
	
/**************************** GETTERS *****************************/
	public String getDivisionId() {
		return this.divisionId;
	}
	
	public String getDivisionName() {
		return this.divisionName;
	}
	
/**************************** SETTERS *****************************/
	public void setDivisionId(String string) {
		this.divisionId = string;
	}

	public void setDivisionName(String name) {
		this.divisionName = name;
	}
}
