package beans;

/**
 * The ScheduleBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class ScheduleBean {
	
	//Schedule bean parameters
	private String title;			// gameID
	private String start;			// gameStart
	private String url;
	
	private String gameDate;
	private String gameTime;
	private String homeTeam;
	private String homeScore;
	private String awayTeam;
	private String awayScore;
	private String gameStatus;
	private String referee;
	private String venue;
	
	
	/**************************** GETTERS *****************************/
	public String getTitle() {
		return this.title;
	}
	
	public String getStart() {
		return this.start;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	
	public String getGameDate() {
		return this.gameDate;
	}
	
	public String getGameTime() {
		return this.gameTime;
	}
	
	public String getHomeTeam() {
		return this.homeTeam;
	}
	
	public String getHomeScore() {
		return this.homeScore;
	}
	
	public String getAwayTeam() {
		return this.awayTeam;
	}
	
	public String getAwayScore() {
		return this.awayScore;
	}
	
	public String getGameStatus() {
		return this.gameStatus;
	}
	
	public String getReferee() {
		return this.referee;
	}
	
	public String getVenue() {
		return this.venue;
	}
	
	/**************************** SETTERS *****************************/
	public void setTitle(String id) {
		this.title = id;
	}
	
	public void setStart(String gd, String gt) {
		this.start = gd + " " + gt;
	}
	
	public void setUrl(String URL) {
		this.url = URL;
	}
	
	public void setGameDate(String gd) {
		this.gameDate = gd;
	}
	
	public void setGameTime(String gt) {
		this.gameTime = gt;
	}
	
	public void setHomeTeam(String ht) {
		this.homeTeam = ht;
	}
	
	public void setHomeScore(String hs) {
		this.homeScore = hs;
	}
	
	public void setAwayTeam(String at) {
		this.awayTeam = at;
	}
	
	public void setAwayScore(String as) {
		this.awayScore = as;
	}
	
	public void setGameStatus(String gs) {
		this.gameStatus = gs;
	}
	
	public void setReferee(String r) {
		this.referee = r;
	}
	
	public void setVenue(String v) {
		this.venue = v;
	}
}
