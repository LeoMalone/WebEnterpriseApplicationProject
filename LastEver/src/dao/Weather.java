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

public class Weather {

	/**
	 * The getVenue gets all the relevant information about the venue
	 * @param <VenueBean>
	 * @param id - The id of the current venue
	 * @return status - boolean value
	 */
	public static boolean checkForUpdate(DateTime currTime) { 
		
		@SuppressWarnings("unused")
		boolean status = false;					// query status
	    Connection conn = null;					// DB connection
	    PreparedStatement getVenue = null;		// SQL query
	    ResultSet resultSet = null;				// returned query result set
	    DateTime updateTime = null;
	    boolean update = false;
	    
	    // Connect to Database
	    try {
	        conn = ConnectionManager.getConnection();
	        getVenue = conn.prepareStatement("select updateTime from weather where weatherID=1");
	        resultSet = getVenue.executeQuery();
	        status = resultSet.next();
	        
	        updateTime = new DateTime(resultSet.getTimestamp(1));
	        
	        Minutes min = Minutes.minutesBetween(updateTime, currTime);
	        Minutes interval = Minutes.minutes(30);
	        
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
	
	public static boolean updateWeather(JSONObject json) { 
		
		boolean status = false;						// query status
	    Connection conn = null;						// DB connection
	    PreparedStatement updateWeather = null;		// SQL query
	    int result = 0;					// returned query result set
	    Timestamp currTime = new Timestamp(System.currentTimeMillis());
	    DateTime sunrise = new DateTime(json.getJSONObject("sys").getLong("sunrise"));
	    DateTime sunset = new DateTime(json.getJSONObject("sys").getLong("sunset"));
	    DateTime dt = new DateTime(currTime);
	   
	    Seconds sunSec = Seconds.secondsBetween(sunrise, dt);
	    Seconds setSec = Seconds.secondsBetween(dt, sunset);
        Seconds interval = Seconds.seconds(1);
	    
	    
	    // Connect to Database
	    try {
	        conn = ConnectionManager.getConnection();
	        updateWeather = conn.prepareStatement("UPDATE weather SET weatherTemp=?, weatherIcon=?, weatherCode=?,"
	        		+ " weatherDescription=?, weatherPressure=?, weatherHumidity=?, weatherWind=?, weatherDay=?, updateTime=?"
	        		+ " WHERE weatherCity=?");
	        updateWeather.setDouble(1, json.getJSONObject("main").getDouble("temp"));
	        updateWeather.setString(2, json.getJSONArray("weather").getJSONObject(0).getString("icon"));
	        updateWeather.setInt(3, json.getJSONArray("weather").getJSONObject(0).getInt("id"));
	        updateWeather.setString(4, json.getJSONArray("weather").getJSONObject(0).getString("description"));
	        updateWeather.setInt(5, json.getJSONObject("main").getInt("pressure"));
	        updateWeather.setInt(6, json.getJSONObject("main").getInt("humidity"));
	        updateWeather.setDouble(7, json.getJSONObject("wind").getDouble("speed"));
	        
	        if(sunSec.isGreaterThan(interval) && setSec.isLessThan(interval))
	        	updateWeather.setString(8, "night");
	        else
	        	updateWeather.setString(8, "day");
	        
	        updateWeather.setTimestamp(9, currTime);
	        updateWeather.setString(10, json.getString("name"));
	        
	        result = updateWeather.executeUpdate();	        
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
	
	public static boolean getWeather(WeatherBean wb, String city) { 
		
		boolean status = false;						// query status
	    Connection conn = null;						// DB connection
	    PreparedStatement getWeather = null;		// SQL query
	    ResultSet resultSet = null;				// returned query result set
	    
	    // Connect to Database
	    try {
	        conn = ConnectionManager.getConnection();
	        getWeather = conn.prepareStatement("select weatherCity, weatherCountry, weatherTemp, weatherIcon,"
	        		+ " weatherCode, weatherDescription, weatherPressure, weatherHumidity, weatherWind, weatherDay,"
	        		+ " updateTime from weather WHERE weatherCity=?");
	        getWeather.setString(1, city);
	        resultSet = getWeather.executeQuery();
	        status = resultSet.next();
	        
	        resultSet.beforeFirst();
	        
	        while(resultSet.next()) {
	        	wb.setWeatherCity(resultSet.getString(1));
	        	wb.setWeatherCountry(resultSet.getString(2));
	        	wb.setWeatherTemp(resultSet.getDouble(3));
	        	wb.setWeatherIcon(resultSet.getString(4));
	        	wb.setWeatherCode(resultSet.getInt(5));
	        	wb.setWeatherDescription(resultSet.getString(6));
	        	wb.setWeatherPressure(resultSet.getInt(7));
	        	wb.setWeatherHumidity(resultSet.getInt(8));
	        	wb.setWeatherWind(resultSet.getDouble(9));
	        	wb.setWeatherDay(resultSet.getString(10));
	        	wb.setUpdateTime(resultSet.getTimestamp(11));
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
