package beans;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The RefBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class PlayerBean {
	
	// User bean parameters
	private String id;
	private String playerFirstName;
	private String playerLastName;
	private String playerNumber;
	private String playerPosition;

/**************************** CONTRUCTORS *****************************/	
	public PlayerBean() {
	}
	
	public PlayerBean(String fn, String ln, String pn, String pp) {
		this.playerFirstName = fn;
		this.playerLastName = ln;
		this.playerNumber = pn;
		this.playerPosition = pp;
	}

/**************************** GETTERS *****************************/	
	public String getId() {
		return this.id;
	}
	
	public String getPlayerFirstName() {
		return this.playerFirstName;
	}
	
	public String getPlayerLastName() {
		return this.playerLastName;
	}
	
	public String getPlayerNumber() {
		return this.playerNumber;
	}
	
	public String getPlayerPosition() {
		return this.playerPosition;
	}


/**************************** SETTERS *****************************/
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPlayerFirstName(String fn) {
		this.playerFirstName = fn;
	}
	
	public void setPlayerLastName(String ln) {
		this.playerLastName = ln;
	}
	
	public void setPlayerNumber(String pn) {
		this.playerNumber = pn;
	}
	
	public void setPlayerPosition(String pp) {
		this.playerPosition = pp;
	}
}
