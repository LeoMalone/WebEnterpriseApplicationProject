package beans;

/**
 * The ScorerBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class ScorerBean {
	
	// Scorer bean parameters
	private String ID;
	private String name;
	private int goals;
	private boolean hidePage;

/**************************** CONTRUCTORS *****************************/	
	public ScorerBean() {
	}

/**************************** GETTERS *****************************/
	public String getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getGoals() {
		return this.goals;
	}
	
	public boolean getHidePage() {
		return this.hidePage;
	}
	
	/**************************** SETTERS *****************************/
	public void setID(String id) {
		this.ID = id;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setGoals(int g) {
		this.goals = g;
	}

	public void setHidePage(boolean hp) {
		this.hidePage = hp;
	}
}
