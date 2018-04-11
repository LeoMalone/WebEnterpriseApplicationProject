package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.VenueBean;

/**
 * The Venue class gets the information about the venue
 */
public class Venue {
	
	/**
	 * The getVenue gets all the relevant information about the venue
	 * @param <VenueBean>
	 * @param id - The id of the current venue
	 * @return status - boolean value
	 */
	public static boolean getVenue(String id, List<VenueBean> venue) { 
		
		boolean status = false;					// query status
	    Connection conn = null;					// DB connection
	    PreparedStatement getVenue = null;		// SQL query
	    ResultSet resultSet = null;				// returned query result set
	
	    // Connect to Database
	    try {
	        conn = ConnectionManager.getConnection();
	        getVenue = conn.prepareStatement("select venueName, venuePicture, venueAddress1, venueAddress2,"
	        		+ " venueCity, venueProvince, venuePostal, venueCountry, venueAbout, venueAboutFR, venueContactName,"
	        		+ "venuePhoneNumber, venueEmail, venueAbout from venue where venueID=?");
	        getVenue.setString(1, id);
	        resultSet = getVenue.executeQuery();
	        status = resultSet.next();
	        
	        //return to the start of the result set
	        resultSet.beforeFirst();
	        
	      //Loop through and add the results of the query to a VenueBean then add it to the list
	        while(resultSet.next()) {
	        	VenueBean vb = new VenueBean();
	        	vb.setVenueName(resultSet.getString(1));
	        	vb.setVenuePicture(resultSet.getString(2));
	        	vb.setAddress1(resultSet.getString(3));
	        	vb.setAddress2(resultSet.getString(4));
	        	vb.setCity(resultSet.getString(5));
	        	vb.setProvince(resultSet.getString(6));
	        	vb.setPostal(resultSet.getString(7));
	        	vb.setCountry(resultSet.getString(8));
	        	vb.setVenueAddress(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), 
	        			resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
	        	vb.setVenueAbout(resultSet.getString(9));
	        	vb.setVenueAboutFR(resultSet.getString(10));
	        	vb.setVenueContact(resultSet.getString(11));
	        	vb.setVenuePhoneNumber(resultSet.getString(12));
	        	vb.setVenueEmail(resultSet.getString(13));
	        	vb.setVenueAbout(resultSet.getString(14));
	        	venue.add(vb);
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
	    return status;
	}
}
