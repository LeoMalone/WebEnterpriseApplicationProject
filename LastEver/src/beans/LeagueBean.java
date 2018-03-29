package beans;

/**
 * The DivisionBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class LeagueBean {
	
	//Division bean parameters
	private String leagueId;
	private String leagueName;
	private String status;
	
/**************************** GETTERS *****************************/
	public String getLeagueId() {
		return this.leagueId;
	}
	
	public String getLeagueName() {
		return this.leagueName;
	}
	
	public String getLeagueStatus() {
		return this.status;
	}
	
/**************************** SETTERS *****************************/
	public void setLeagueId(String string) {
		this.leagueId = string;
	}

	public void setLeagueName(String name) {
		this.leagueName = name;
	}
	
	public void setStatus(String stat) {
		this.status = stat;
	}
}
