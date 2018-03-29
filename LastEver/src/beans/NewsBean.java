package beans;

import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Timestamp;
import java.util.Locale;

/**
 * The NewsBean class is meant for passing user information
 * between DAOs and Servlets
 */
public class NewsBean {
	
	// News bean parameters
	private String userName;
	private String title;
	private String titleFR;
	private String postedTime;
	private String content;
	private String contentFR;

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
	
	public String getTitleFR() {
		return this.titleFR;
	}
	
	public String getPostedTime() {
		return this.postedTime;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getContentFR() {
		return this.contentFR;
	}

	/**************************** SETTERS *****************************/
	public void setUserName(String un) {
		this.userName = un;
	}
	
	public void setTitle(String t) {
		this.title = t;
	}
	
	public void setTitleFR(String t) {
		this.titleFR = t;
	}
	
	public void setPostedTime(Timestamp pt, String lang) {
		PrettyTime p = new PrettyTime();
		
		//if there is no set language default to English
		if(lang == null) {
			lang = "en";
		}
		p.setLocale(new Locale(lang));
		this.postedTime = p.format(pt);
	}
	
	public void setContent(String con) {
		this.content = con;
	}
	
	public void setContentFR(String con) {
		this.contentFR = con;
	}
}
