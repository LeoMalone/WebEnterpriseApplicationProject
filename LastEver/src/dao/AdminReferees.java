package dao;

import beans.RefBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The AdminReferees class handles all db operation relating to getting all referees
 * @author Kevin Villemaire
 */
public class AdminReferees {
	
	/**
	 * The getAllReferees methods get all referees from the db
	 * @param rlb <RefBean>
	 * @return boolean status 
	 */
	public static boolean getAllReferees(List<RefBean> rlb) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getRefs = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getRefs = conn.prepareStatement("SELECT refereeID, refereeFirstName, refereeLastName from referee");
	        rs = getRefs.executeQuery();
	        status = rs.next();
	        
	        //return to start of result set
	        rs.beforeFirst();
	        
	        while(rs.next()) {
	        	RefBean rb = new RefBean();
	        	rb.setRefId(rs.getString(1));
	        	rb.setFirstName(rs.getString(2));
	        	rb.setLastName(rs.getString(3));
	        	rlb.add(rb);
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
	        if (getRefs != null) {
	            try {
	            	getRefs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}