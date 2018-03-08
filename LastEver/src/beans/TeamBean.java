package beans;

public class TeamBean {
	
	private String divisionId;
	private String divisionName;
	private String teamLogo;
	private String teamId;
	private String teamName;
	private String teamAbbreviation;
	private String teamAbout;
	
/**************************** CONTRUCTORS *****************************/
	public TeamBean() {
	}
	
/**************************** GETTERS *****************************/
	public String getDivisionId() {
		return this.divisionId;
	}
	
	public String getDivisionName() {
		return this.divisionName;
	}
	
	public String getTeamLogo() {
		return this.teamLogo;
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
	
	public String getTeamAbout() {
		return this.teamAbout;
	}

/**************************** SETTERS *****************************/
	public void setDivisionId(String divID) {
		this.divisionId = divID;
	}
	
	public void setTeamId(String id) {
		this.teamId = id;
	}
	
	public void setDivisionName(String divN) {
		this.divisionName = divN;
	}
	
	public void setTeamLogo(String logo) {
		this.teamLogo = logo;
	}
	
	public void setTeamName(String name) {
		this.teamName = name;
	}
	
	public void setTeamAbbreviation(String ta) {
		this.teamAbbreviation = ta;
	}

	public void setTeamAbout(String ab) {
		this.teamAbout = ab;
	}
}
