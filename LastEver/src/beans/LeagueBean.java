package beans;

/**
 * The DivisionBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class LeagueBean {
	
	//Division bean parameters
	private String leagueId;
	private String leagueName;	
	
/**************************** GETTERS *****************************/
	public String getLeagueId() {
		return this.leagueId;
	}
	
	public String getLeagueName() {
		return this.leagueName;
	}
	
/**************************** SETTERS *****************************/
	public void setLeagueId(String string) {
		this.leagueId = string;
	}

	public void setLeagueName(String name) {
		this.leagueName = name;
	}
}
