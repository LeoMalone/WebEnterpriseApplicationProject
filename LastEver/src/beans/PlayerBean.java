package beans;

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
	private String playerCountry;
	private float playerHeight;
	private float playerWeight;
	private String playerPhoto;

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

	public String getPlayerCountry() {
		return this.playerCountry;
	}
	
	public float getPlayerHeight() {
		return this.playerHeight;
	}
	
	public float getPlayerWeight() {
		return this.playerWeight;
	}

	public String getPlayerPhoto() {
		return this.playerPhoto;
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
	
	public void setPlayerCountry(String ctry) {
		this.playerCountry = ctry;
	}
	
	public void setPlayerHeight(float he) {
		this.playerHeight = he;
	}
	
	public void setPlayerWeight(float we) {
		this.playerWeight = we;
	}
	
	public void setPlayerPhoto(String ph) {
		this.playerPhoto = ph;
	}
}
