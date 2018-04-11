package beans;

/**
 * The LeagueBean class is meant for passing league information
 * between DAOs and Servlets
 */
public class LeagueBean {
	
	//Division bean parameters
	private String leagueId;
	private String leagueName;
	private String leaguePlayoffs;
	private String leaguePlayoffTeams;	
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
	
	public String getLeaguePlayoffs() {
		return this.leaguePlayoffs;
	}
	
	public String getLeaguePlayoffTeams() {
		return this.leaguePlayoffTeams;
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
	
	public void setLeaguePlayoffs(String play) {
		this.leaguePlayoffs = play;
	}
	
	public void setLeaguePlayoffTeams(String numTeams) {
		this.leaguePlayoffTeams = numTeams;
	}
}
