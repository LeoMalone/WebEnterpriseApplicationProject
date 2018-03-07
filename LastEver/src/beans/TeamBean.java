package beans;

public class TeamBean {
	
	private String divisionId;
	private String teamId;
	private String teamName;
	private String teamAbbreviation;
	
/**************************** CONTRUCTORS *****************************/
	public TeamBean() {
	}
	
/**************************** GETTERS *****************************/
	public String getDividionId() {
		return this.divisionId;
	}

	public String getTeamId() {
		return this.teamId;
	}
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public String getTeamAbbreviation() {
		return this.teamAbbreviation;
	}

/**************************** SETTERS *****************************/
	public void setDivisionId(String divID) {
		this.divisionId = divID;
	}
	
	public void setTeamId(String id) {
		this.teamId = id;
	}
	
	public void setTeamName(String name) {
		this.teamName = name;
	}
	
	public void setTeamAbbreviation(String ta) {
		this.teamAbbreviation = ta;
	}

}
