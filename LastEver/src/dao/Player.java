package dao;

import beans.PlayerBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The Login class validates a UserBean's credentials
 * using a SELECT statement to compare credentials
 */
public class Player {
	
	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param user - UserBean credentials
	 * @return status - boolean value
	 */
	public static boolean getPlayerInfo(String pID, List<PlayerBean> player) { 
		
		boolean status = false;				// query status
	    Connection conn = null;				// DB connection
	    PreparedStatement getPlayer = null;	// SQL query
	    ResultSet resultSet = null;			// returned query result set
	
	    // Connect to Database and execute SELECT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();
	        getPlayer = conn.prepareStatement("select playerFirstName, playerLastName, playerNumber, playerPosition,"
	        		+ " playerCountry, playerHeight, playerWeight, playerPhoto from player where playerID=?");
	        getPlayer.setString(1, pID);
	        resultSet = getPlayer.executeQuery();
	        status = resultSet.next();
	        
	        resultSet.beforeFirst();
	        
	        while(resultSet.next()) {
	        	PlayerBean pb = new PlayerBean();
	        	pb.setPlayerFirstName(resultSet.getString(1));
	        	pb.setPlayerLastName(resultSet.getString(2));
	        	pb.setPlayerNumber(resultSet.getString(3));
	        	pb.setPlayerPosition(resultSet.getString(4));
	        	pb.setPlayerCountry(resultSet.getString(5));
	        	pb.setPlayerHeight(resultSet.getFloat(6));
	        	pb.setPlayerWeight(resultSet.getFloat(7));
	        	pb.setPlayerPhoto(resultSet.getString(8));
	        	player.add(pb);
	        }
	        
	    // handle all possible exceptions
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
	        if (getPlayer != null) {
	            try {
	            	getPlayer.close();
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
	
public static String getPlayerName(String pID) { 
		
	    Connection conn = null;				// DB connection
	    PreparedStatement getPlayer = null;	// SQL query
	    ResultSet resultSet = null;			// returned query result set
	    String pName = null;
	    
	    // Connect to Database and execute SELECT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();
	        getPlayer = conn.prepareStatement("select playerFirstName, playerLastName from player where playerID=?");
	        getPlayer.setString(1, pID);
	        resultSet = getPlayer.executeQuery();
	        
	        while(resultSet.next()) {
	        	pName = resultSet.getString(1) + " " + resultSet.getString(2);
	        	
	        }
	        
	    // handle all possible exceptions
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
	        if (getPlayer != null) {
	            try {
	            	getPlayer.close();
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
	    return pName;
	}
}
