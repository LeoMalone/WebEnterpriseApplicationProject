package beans;

/**
 * The StatisticsBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class StatisticsBean {
	
	// Statistics bean parameters
	private String rank;
	private String teamName;
	private String teamID;
	private String name;
	private int gamesPlayed;
	private int goals;
	private int redCard;
	private int yellowCard;
	private String playerID;

/**************************** CONTRUCTORS *****************************/	
	public StatisticsBean() {
	}
	
	public StatisticsBean(String r, String tn, String n, int gp, int g, int rc, int yc) {
		this.rank = r;
		this.teamName = tn;
		this.name = n;
		this.gamesPlayed = gp;
		this.goals = g;
		this.redCard = rc;
		this.yellowCard = yc;
	}

/**************************** GETTERS *****************************/
	public String getRank() {
		return this.rank;
	}
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public String getTeamID() {
		return this.teamID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getGamesPlayed() {
		return this.gamesPlayed;
	}
	
	public int getGoals() {
		return this.goals;
	}
	
	public int getRedCard() {
		return this.redCard;
	}

	public int getYellowCard() {
		return this.yellowCard;
	}
	
	public String getPlayerID() {
		return this.playerID;
	}
	
/**************************** SETTERS *****************************/
	public void setRank(String r) {
		this.rank = r;
	}
	
	public void setTeamName(String tn) {
		this.teamName = tn;
	}
	
	public void setTeamID(String tID) {
		this.teamID = tID;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setGamesPlayed(int gp) {
		this.gamesPlayed = gp;
	}
	
	public void setGoals(int g) {
		this.goals = g;
	}
	
	public void setRedCard(int rc) {
		this.redCard = rc;
	}

	public void setYellowCard(int yc) {
		this.yellowCard = yc;
	}
	
	public void setPlayerID(String pID) {
		this.playerID = pID;
	}
}