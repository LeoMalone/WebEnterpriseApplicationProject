package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.ConnectionManager;
import beans.RefBean;

public class RefUsers {
	
	public static boolean getAllUsers(List<RefBean> userList) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement allUsers = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        allUsers = conn.prepareStatement("select userID, username, userType, emailAddress, password from users where userType = 'referee'");
	        rs = allUsers.executeQuery();	              
	        
	        while(rs.next()) {
	        	RefBean ub = new RefBean();
	        	ub.setId(rs.getString(1));
	        	ub.setUsername(rs.getString(2));
	        	ub.setUserType(rs.getString(3));
	        	ub.setEmail(rs.getString(4));
	        	ub.setPassword(rs.getString(5));
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
