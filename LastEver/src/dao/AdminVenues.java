package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.VenueBean;
import db.ConnectionManager;

/**
 * The AdminVenues class handles all db operation relating to getting all venues
 * @author Kevin Villemaire
 */
public class AdminVenues {
	
	/**
	 * The getAllVenues methods get all venues from the db
	 * @param vlb <VenueBean>
	 * @return boolean status 
	 */
	public static boolean getAllVenues(List<VenueBean> vlb) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getVenue = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getVenue = conn.prepareStatement("SELECT venueID, venueName from venue");
	        rs = getVenue.executeQuery();
	        status = rs.next();
	        
	        //return to start of result set
	        rs.beforeFirst();
	        
	        while(rs.next()) {
	        	VenueBean vb = new VenueBean();
	        	vb.setVenueID(rs.getString(1));
	        	vb.setVenueName(rs.getString(2));
	        	vlb.add(vb);
	        }                
	        
	    // Catch all possible Exceptions
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
	    }	    
	    return status;
	}
}