package beans;

/**
 * The UserBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class StandingsBean {
	
	// User bean parameters
	private int rank;
	private String teamName;
	private int gamesPlayed;
	private int wins;
	private int losses;
	private int draws;
	private int points;
	private int goalsFor;
	private int goalsAgainst;
	private int goalDiff;
	private String teamID;

/**************************** CONTRUCTORS *****************************/	
	public StandingsBean() {
	}
	
	public StandingsBean(int r, String team, int gp, int w, int l, int d, int pts, int gf, int ga, int gd) {
		this.rank = r;
		this.teamName = team;
		this.gamesPlayed = gp;
		this.wins = w;
		this.losses = l;
		this.draws = d;
		this.points = pts;
		this.goalsFor = gf;
		this.goalsAgainst = ga;
		this.goalDiff = gd;
	}

/**************************** GETTERS *****************************/
	public int getRank() {
		return this.rank;
	}
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public int getGamesPlayed() {
		return this.gamesPlayed;
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public int getLosses() {
		return this.losses;
	}
	
	public int getDraws() {
		return this.draws;
	}

	public int getPoints() {
		return this.points;
	}
	
	public int getGoalsFor() {
		return this.goalsFor;
	}
	
	public int getGoalsAgainst() {
		return this.goalsAgainst;
	}
	
	public int getGoalDiff() {
		return this.goalDiff;
	}
	
	public String getTeamID() {
		return this.teamID;
	}
	
/**************************** SETTERS *****************************/
	public void setRank(int r) {
		this.rank = r;
	}
	
	public void setTeamName(String team) {
		this.teamName = team;
	}
	
	public void setGamesPlayed(int gp) {
		this.gamesPlayed = gp;
	}
	
	public void setWins(int w) {
		this.wins = w;
	}
	
	public void setLosses(int l) {
		this.losses = l;
	}
	
	public void setDraws(int d) {
		this.draws = d;
	}

	public void setPoints(int pts) {
		this.points = pts;
	}
	
	public void setGoalsFor(int gf) {
		this.goalsFor = gf;
	}
	
	public void setGoalsAgainst(int ga) {
		this.goalsAgainst = ga;
	}
	
	public void setGoalDiff(int gd) {
		this.goalDiff = gd;
	}
	
	public void setTeamID(String tID) {
		this.teamID = tID;
	}
}