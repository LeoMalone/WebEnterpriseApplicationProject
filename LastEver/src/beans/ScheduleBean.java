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
	
	private String gameTime;
	private String homeTeam;
	private String homeScore;
	private String awayTeam;
	private String awayScore;
	private String gameStatus;
	
	/**
	 * Getters
	 **/
	public String getTitle() {
		return this.title;
	}
	
	public String getStart() {
		return this.start;
	}
	
	public String getUrl() {
		return this.url;
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
	
	/**
	 * Setters
	 **/
	public void setTitle(String id) {
		this.title = id;
	}
	
	public void setStart(String gd) {
		this.start = gd;
	}
	
	public void setUrl(String URL) {
		this.url = URL;
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
}
