package beans;

public class ScorerBean {
	
	// User bean parameters
	private String name;
	private int goals;

/**************************** CONTRUCTORS *****************************/	
	public ScorerBean() {
	}

/**************************** GETTERS *****************************/
	public String getName() {
		return this.name;
	}
	
	public int getGoals() {
		return this.goals;
	}
	
	/**************************** SETTERS *****************************/
	public void setName(String n) {
		this.name = n;
	}
	
	public void setGoals(int g) {
		this.goals = g;
	}
}
