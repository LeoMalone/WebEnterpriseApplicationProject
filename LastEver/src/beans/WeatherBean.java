package beans;

import java.sql.Timestamp;

/**
 * The WeatherBean class is meant for passing weather information
 * between DAOs and Servlets
 */
public class WeatherBean {
	
	// Statistics bean parameters
	private String weatherCity;
	private String weatherCountry;
	private Double weatherTemp;
	private String weatherIcon;
	private int weatherCode;
	private String weatherDescription;
	private int weatherPressure;
	private int weatherHumidity;
	private Double weatherWind;
	private Double weatherGust;
	private String weatherDay;
	private Timestamp updateTime;

/**************************** CONTRUCTORS *****************************/	
	public WeatherBean() {
	}

/**************************** GETTERS *****************************/
	public String getWeatherCity() {
		return this.weatherCity;
	}
	
	public String getWeatherCountry() {
		return this.weatherCountry;
	}
	
	public Double getWeatherTemp() {
		return this.weatherTemp;
	}
	
	public String getWeatherIcon() {
		return this.weatherIcon;
	}
	
	public int getWeatherCode() {
		return this.weatherCode;
	}
	
	public String getWeatherDescription() {
		return this.weatherDescription;
	}
	
	public int getWeatherPressure() {
		return this.weatherPressure;
	}

	public int getWeatherHumidity() {
		return this.weatherHumidity;
	}
	
	public Double getWeatherWind() {
		return this.weatherWind;
	}
	
	public Double getWeatherGust() {
		return this.weatherGust;
	}
	
	public String getWeatherDay() {
		return this.weatherDay;
	}
	
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}
	
/**************************** SETTERS *****************************/
	public void setWeatherCity(String wc) {
		this.weatherCity = wc;
	}
	
	public void setWeatherCountry(String wcty) {
		this.weatherCountry = wcty;
	}
	
	public void setWeatherTemp(Double wt) {
		this.weatherTemp = wt;
	}
	
	public void setWeatherIcon(String wi) {
		this.weatherIcon = wi;
	}
	
	public void setWeatherCode(int code) {
		this.weatherCode = code;
	}
	
	public void setWeatherDescription(String wd) {
		this.weatherDescription = wd;
		this.weatherDescription = this.weatherDescription.replaceAll(" ", "_");
	}
	
	public void setWeatherPressure(int wp) {
		this.weatherPressure = wp;
	}

	public void setWeatherHumidity(int wh) {
		this.weatherHumidity = wh;
	}
	
	public void setWeatherWind(Double ww) {
		this.weatherWind = ww;
	}
	
	public void setWeatherGust(Double wg) {
		this.weatherGust = wg;
	}
	
	public void setWeatherDay(String wd) {
		this.weatherDay = wd;
	}
	
	public void setUpdateTime(Timestamp wt) {
		this.updateTime = wt;
	}
}