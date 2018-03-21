package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.ConnectionManager;
import beans.UserBean;

/**
 * The AdminUsers class handles all db operation relating to editing a User by an Admin
 */
public class AdminUsers {
	
	/**
	 * The getAllUsers methods get all users from the db
	 * @param userList UserBean list from servlet
	 * @return boolean status
	 */
	public static boolean getAllUsers(List<UserBean> userList) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement allUsers = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        allUsers = conn.prepareStatement("SELECT userID, userFirstName, userLastName, username, userType, emailAddress, emailValidated, accountCreated, accountUpdated, lastLogin from users");
	        rs = allUsers.executeQuery();
	        
	        
	        while(rs.next()) {
	        	UserBean ub = new UserBean();
	        	ub.setId(rs.getString(1));
	        	ub.setFirstName(rs.getString(2));
	        	ub.setLastName(rs.getString(3));
	        	ub.setUsername(rs.getString(4));
	        	ub.setUserType(rs.getString(5));
	        	ub.setEmail(rs.getString(6));
	        	ub.setEmailValidated(rs.getInt(7));
	        	ub.setAccountCreated(rs.getTimestamp(8));
	        	ub.setAccountUpdated(rs.getTimestamp(9));
	        	ub.setLastLogin(rs.getTimestamp(10));	        	
	        	userList.add(ub);
	        	
	        	status = true;
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
	        if (allUsers != null) {
	            try {
	            	allUsers.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
