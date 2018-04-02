package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.json.JSONObject;

import beans.WeatherBean;
import db.ConnectionManager;

/**
 * The Weather class handles checking to see if the weather from the database needs to be updated,
 * update the weather from the OpenWeatherMap (OWM) API, and getting the current conditions from the database
 * @author Kevin Villemaire
 */
public class Weather {

	/**
	 * The checkForUpdate checks to see if the weather data needs to be updated
	 * @param DateTime - current time of the server
	 * @return update - boolean value: if weather needs to be updated
	 */
	public static boolean checkForUpdate(DateTime currTime) { 
		
		@SuppressWarnings("unused")				// suppress warnings that status is unused
		boolean status = false;					// query status
	    Connection conn = null;					// DB connection
	    PreparedStatement getVenue = null;		// SQL query
	    ResultSet resultSet = null;				// returned query result set
	    DateTime updateTime = null;				// the last update time from the database
	    boolean update = false;					// if weather needs to be updated or not
	    
	    // Connect to Database
	    try {
	        conn = ConnectionManager.getConnection();
	        getVenue = conn.prepareStatement("select updateTime from weather where weatherID=1");
	        resultSet = getVenue.executeQuery();
	        status = resultSet.next();
	        
	        //select the update time from the database and convert it to DateTime for comparison
	        updateTime = new DateTime(resultSet.getTimestamp(1));
	        
	        //checks the amount of minutes between the current time and the time of last update
	        Minutes min = Minutes.minutesBetween(updateTime, currTime);
	        //sets the interval to be 30 minutes
	        Minutes interval = Minutes.minutes(30);
	        
	        //if 30 minutes has passed since last update then set update to true, otherwise data needs no updating
	        if(min.isGreaterThan(interval))
	        	update = true;
	        else
	        	update = false;
	      
	        
	    // close all connections and handle all possible exceptions
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (getVenue != null) {
	            try {
	            	getVenue.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (resultSet != null) {
	            try {
	            	resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return update;
	}

	/**
	 * The updateWeather updates the weather data with data from the OWM API
	 * @param json - JSONObject which was returned from the API call
	 * @param description_fr - the French weather description
	 * @return status - boolean value
	 */
	public static boolean updateWeather(JSONObject json, String description_fr) { 
		
		boolean status = false;						// query status
	    Connection conn = null;						// DB connection
	    PreparedStatement updateWeather = null;		// SQL query
	    int result = 0;								// returned query result set
	    Timestamp currTime = new Timestamp(System.currentTimeMillis());
	    
	    
	    // Connect to Database
	    try {
	        conn = ConnectionManager.getConnection();
	        //upadate the current weather with the data from the API call
	        updateWeather = conn.prepareStatement("UPDATE weather SET weatherTemp=?, weatherIcon=?, weatherCode=?,"
	        		+ " weatherDescription=?, weatherDescriptionFR=?, weatherPressure=?, weatherHumidity=?, weatherWind=?,"
	        		+ " weatherGust=?, weatherDay=?, updateTime=? WHERE weatherCity=?");
	        
	        //gets the main object (which contains temperature information and ) and get current temperature
	        updateWeather.setDouble(1, json.getJSONObject("main").getDouble("temp"));
	      
	         /* Weather is an array of JSONObjects so get the JSONObject at position zero and use that
	         * JSONObject to get the icon, the weather code, and description */
	        updateWeather.setString(2, json.getJSONArray("weather").getJSONObject(0).getString("icon"));
	        updateWeather.setInt(3, json.getJSONArray("weather").getJSONObject(0).getInt("id"));
	        updateWeather.setString(4, json.getJSONArray("weather").getJSONObject(0).getString("description"));
	        updateWeather.setString(5, description_fr);
	        //get the main object and get the pressure and humidity
	        updateWeather.setInt(6, json.getJSONObject("main").getInt("pressure"));
	        updateWeather.setInt(7, json.getJSONObject("main").getInt("humidity"));
	        //get the current wind speed from the wind JSONObject
	        updateWeather.setDouble(8, json.getJSONObject("wind").getDouble("speed"));
	        
	        /*if the JSONObject wind contains the gust property (not always included in the response)
	        set the wind to the data from the API call. Otherwise set it to zero and it won't show in the current
	        conditions */
	        if(json.getJSONObject("wind").has("gust"))
	        	updateWeather.setDouble(9, json.getJSONObject("wind").getDouble("gust"));
	        else
	        	updateWeather.setDouble(9, 0.0);
	        
	        /*
	         * Weather Icon Stored as: 04d
	         * Checks to see if the icon contains a d/n and set the day
	         * This is used if the weather is codes 800 (clear), 801 (partly cloudy),
	         * 802 (mostly cloudy), and 803 (broken clouds)
	         */
	        if(json.getJSONArray("weather").getJSONObject(0).getString("icon").contains("d"))
	        	updateWeather.setString(10, "day");
	        else
	        	updateWeather.setString(10, "night");
	        
	        //get the current time and update the time of weather update
	        updateWeather.setTimestamp(11, currTime);
	        updateWeather.setString(12, json.getString("name"));
	        
	        //execute query
	        result = updateWeather.executeUpdate();	      
	        
	        //if one row is affected then the query was successful
	        if(result == 1) {
	        	status = true;
	        }
	      
	    // close all connections and handle all possible exceptions
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (updateWeather != null) {
	            try {
	            	updateWeather.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return status;
	}
	
	/**
	 * The getWeather gets the current weather conditions for a city
	 * @param <WeatherBean>
	 * @param city - the city to get the data from
	 * @return status - boolean value
	 */
	public static boolean getWeather(WeatherBean wb, String city) { 
		
		boolean status = false;						// query status
	    Connection conn = null;						// DB connection
	    PreparedStatement getWeather = null;		// SQL query
	    ResultSet resultSet = null;					// returned query result set
	    
	    // Connect to Database
	    try {
	        conn = ConnectionManager.getConnection();
	        getWeather = conn.prepareStatement("select weatherCity, weatherCountry, weatherTemp, weatherIcon,"
	        		+ " weatherCode, weatherDescription, weatherDescriptionFR, weatherPressure, weatherHumidity,"
	        		+ " weatherWind, weatherGust, weatherDay, updateTime from weather WHERE weatherCity=?");
	        getWeather.setString(1, city);
	        resultSet = getWeather.executeQuery();
	        status = resultSet.next();
	        
	        //return to the start of the result set
	        resultSet.beforeFirst();
	        
	        //loop through the result set and add the results to a WeatherBean
	        while(resultSet.next()) {
	        	wb.setWeatherCity(resultSet.getString(1));
	        	wb.setWeatherCountry(resultSet.getString(2));
	        	wb.setWeatherTemp(resultSet.getDouble(3));
	        	wb.setWeatherIcon(resultSet.getString(4));
	        	wb.setWeatherCode(resultSet.getInt(5));
	        	wb.setWeatherDescription(resultSet.getString(6));
	        	wb.setWeatherDescriptionFR(resultSet.getString(7));
	        	wb.setWeatherPressure(resultSet.getInt(8));
	        	wb.setWeatherHumidity(resultSet.getInt(9));
	        	wb.setWeatherWind(resultSet.getDouble(10));
	        	wb.setWeatherGust(resultSet.getDouble(11));
	        	wb.setWeatherDay(resultSet.getString(12));
	        	wb.setUpdateTime(resultSet.getTimestamp(13));
	        }
	   
	    // close all connections and handle all possible exceptions
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (getWeather != null) {
	            try {
	            	getWeather.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return status;
	}
}
