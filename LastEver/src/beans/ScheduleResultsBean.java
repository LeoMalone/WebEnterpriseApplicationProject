package beans;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * The ScheduleResultsBean class is meant for passing schedule and results information
 * between DAOs and Servlets
 */
public class ScheduleResultsBean {
	
	// ScheduleResults bean parameters
	private int gameID;
	private Date date;
	private Time time;
	private String homeTeam;
	private String homeID;
	private String homeTeamLogo;
	private int homeScore;
	private String awayTeam;
	private String awayID;
	private String awayTeamLogo;
	private int awayScore;
	private String venue;
	private String status;
	private List<ScorerBean> homeScorer;
	private List<ScorerBean> awayScorer;
	private String venueID;

/**************************** CONTRUCTORS *****************************/	
	public ScheduleResultsBean() {
	}
	
	public ScheduleResultsBean(Date d, Time t, String ht, String at, String stat, String ven) {
		this.date = d;
		this.time = t;
		this.homeTeam = ht;
		this.awayTeam = at;
		this.venue = ven;
		this.status = stat;
	}
	
	public ScheduleResultsBean(Date d, Time t, String ht, int hs, String at, int as, String ven, String stat) {
		this.date = d;
		this.time = t;
		this.homeTeam = ht;
		this.homeScore = hs;
		this.awayTeam = at;
		this.awayScore = as;
		this.venue = ven;
		this.status = stat;
	}

/**************************** GETTERS *****************************/
	public int getGameID() {
		return this.gameID;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Time getTime() {
		return this.time;
	}
	
	public String getHomeTeam() {
		return this.homeTeam;
	}
	
	public String getHomeID() {
		return this.homeID;
	}
	
	public String getHomeTeamLogo() {
		return this.homeTeamLogo;
	}
	
	public int getHomeScore() {
		return this.homeScore;
	}
	
	public String getAwayTeam() {
		return this.awayTeam;
	}
	
	public String getAwayID() {
		return this.awayID;
	}
	
	public String getAwayTeamLogo() {
		return this.awayTeamLogo;
	}
	
	public int getAwayScore() {
		return this.awayScore;
	}
	
	public String getVenue() {
		return this.venue;
	}

	public String getStatus() {
		return this.status;
	}
	
	public List<ScorerBean> getHomeScorer(){
		return this.homeScorer;
	}
	
	public List<ScorerBean> getAwayScorer(){
		return this.awayScorer;
	}
	
	public String getVenueID() {
		return this.venueID;
	}
	
/**************************** SETTERS *****************************/
	public void setGameID(int id) {
		this.gameID = id;
	}
	
	public void setDate(Date d) {
		this.date = d;
	}
	
	public void setTime(Time t) {
		this.time = t;
	}
	
	public void setHomeTeam(String ht) {
		this.homeTeam = ht;
	}
	
	public void setHomeID(String hID) {
		this.homeID = hID;
	}
	
	public void setHomeTeamLogo(String htl) {
		this.homeTeamLogo = htl;
	}
	
	public void setHomeScore(int hs) {
		this.homeScore = hs;
	}
	
	public void setAwayTeam(String at) {
		this.awayTeam = at;
	}
	
	public void setAwayID(String aID) {
		this.awayID = aID;
	}
	
	public void setAwayTeamLogo(String atl) {
		this.awayTeamLogo = atl;
	}
	
	public void setAwayScore(int as) {
		this.awayScore = as;
	}
	
	public void setVenue(String ven) {
		this.venue = ven;
	}

	public void setStatus(String stat) {
		this.status = stat;
	}
	
	public void setHomeScorer(List<ScorerBean> hlb){
		this.homeScorer = hlb;
	}
	
	public void setAwayScorer(List<ScorerBean> alb){
		this.awayScorer = alb;
	}

	public void setVenueID(String vID) {
		this.venueID = vID;
	}
}