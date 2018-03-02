package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.ConnectionManager;
import beans.UserBean;

public class AdminUsers {
	
	public static boolean getAllUsers(List<UserBean> userList) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement allUsers = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        allUsers = conn.prepareStatement("select userID, userFirstName, userLastName, username, userType, emailAddress, password, emailValidated, accountCreated, accountUpdated, lastLogin from users");
	        rs = allUsers.executeQuery();	              
	        
	        while(rs.next()) {
	        	UserBean ub = new UserBean();
	        	ub.setId(rs.getString(1));
	        	ub.setFirstName(rs.getString(2));
	        	ub.setLastName(rs.getString(3));
	        	ub.setUsername(rs.getString(4));
	        	ub.setUserType(rs.getString(5));
	        	ub.setEmail(rs.getString(6));
	        	ub.setPassword(rs.getString(7));
	        	ub.setEmailValidated(rs.getInt(8));
	        	ub.setAccountCreated(rs.getTimestamp(9));
	        	ub.setLastAccountUpdate(rs.getTimestamp(10));
	        	ub.setLastLogin(rs.getTimestamp(10));	        	
	        	userList.add(ub);	        	
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
