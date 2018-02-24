package beans;

import java.sql.Time;
import java.util.Date;

/**
 * The ScheduleBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class ScheduleResultsBean {
	
	// User bean parameters
	private Date date;
	private Time time;
	private String homeTeam;
	private int homeScore;
	private String awayTeam;
	private int awayScore;
	private String status;

/**************************** CONTRUCTORS *****************************/	
	public ScheduleResultsBean() {
	}
	
	public ScheduleResultsBean(Date d, Time t, String ht, String at, String stat) {
		this.date = d;
		this.time = t;
		this.homeTeam = ht;
		this.awayTeam = at;
		this.status = stat;
	}
	
	public ScheduleResultsBean(Date d, Time t, String ht, int hs, String at, int as, String stat) {
		this.date = d;
		this.time = t;
		this.homeTeam = ht;
		this.homeScore = hs;
		this.awayTeam = at;
		this.awayScore = as;
		this.status = stat;
	}

/**************************** GETTERS *****************************/
	public Date getDate() {
		return this.date;
	}
	
	public Time getTime() {
		return this.time;
	}
	
	public String getHomeTeam() {
		return this.homeTeam;
	}
	
	public int getHomeScore() {
		return this.homeScore;
	}
	
	public String getAwayTeam() {
		return this.awayTeam;
	}
	
	public int getAwayScore() {
		return this.awayScore;
	}

	public String getStatus() {
		return this.status;
	}
	
/**************************** SETTERS *****************************/
	public void setDate(Date d) {
		this.date = d;
	}
	
	public void setTime(Time t) {
		this.time = t;
	}
	
	public void setHomeTeam(String ht) {
		this.homeTeam = ht;
	}
	
	public void setHomeScore(int hs) {
		this.homeScore = hs;
	}
	
	public void setAwayTeam(String at) {
		this.awayTeam = at;
	}
	
	public void setAwayScore(int as) {
		this.awayScore = as;
	}

	public void setStatus(String stat) {
		this.status = stat;
	}
}