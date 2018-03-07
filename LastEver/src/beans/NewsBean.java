package beans;

import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Timestamp;
import java.util.Locale;

public class NewsBean {
	
	// User bean parameters
	private String userName;
	private String title;
	private String postedTime;
	private String content;

/**************************** CONTRUCTORS *****************************/	
	public NewsBean() {
	}
	
	public NewsBean(String un, String t, Timestamp pt, String con, String lang) {
		PrettyTime p = new PrettyTime();
		p.setLocale(new Locale(lang));
		this.userName = un;
		this.title = t;
		this.postedTime = p.format(pt);
		this.content = con;
	}

/**************************** GETTERS *****************************/
	public String getUserName() {
		return this.userName;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getPostedTime() {
		return this.postedTime;
	}
	
	public String getContent() {
		return this.content;
	}

	/**************************** SETTERS *****************************/
	public void setUserName(String un) {
		this.userName = un;
	}
	
	public void setTitle(String t) {
		this.title = t;
	}
	
	public void setPostedTime(Timestamp pt, String lang) {
		PrettyTime p = new PrettyTime();
		if(lang == null) {
			lang = "en";
		}
		p.setLocale(new Locale(lang));
		this.postedTime = p.format(pt);
	}
	
	public void setContent(String con) {
		this.content = con;
	}
}