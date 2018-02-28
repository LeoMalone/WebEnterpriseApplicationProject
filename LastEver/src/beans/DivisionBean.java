package beans;

public class DivisionBean {
	
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
