package beans;

public class ScorerBean {
	
	// User bean parameters
	private String ID;
	private String name;
	private int goals;

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
}
