package beans;

import java.util.List;

/**
 * The DivisionBean class is meant for passing division information
 * between DAOs and Servlets
 */
public class DivisionBean {
	
	//Division bean parameters
	private String divisionId;
	private String divisionName;
	private String leagueId;
	private List<StandingsBean> standings;
	
/**************************** GETTERS *****************************/
	public String getDivisionId() {
		return this.divisionId;
	}
	
	public String getDivisionName() {
		return this.divisionName;
	}
	
	public List<StandingsBean> getStandings(){
		return this.standings;
	}
	
	public String getLeageId() {
		return this.leagueId;
	}
	
/**************************** SETTERS *****************************/
	public void setDivisionId(String string) {
		this.divisionId = string;
	}

	public void setDivisionName(String name) {
		this.divisionName = name;
	}
	
	public void setStandings(List<StandingsBean> stand) {
		this.standings = stand;
	}
	
	public void setLeagueId(String lID) {
		this.leagueId = lID;
	}
}
