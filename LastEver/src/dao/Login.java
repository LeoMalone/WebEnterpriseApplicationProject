package dao;

import beans.UserBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	
	public static boolean validateUserLogin(UserBean user) { 
		
		boolean status = false;
	    Connection conn = null;
	    PreparedStatement getusers = null;
	    ResultSet resultSet = null;
	
	    try {
	        conn = ConnectionManager.getConnection();
	        getusers = conn.prepareStatement("select username, userType, emailAddress, password from users where emailAddress=? and password=?");
	        getusers.setString(1, user.getEmailAddress());
	        getusers.setString(2, user.getPassword());
	        resultSet = getusers.executeQuery();
	        status = resultSet.next();
	        
	        if(status) {
	        	user.setUsername(resultSet.getString(1));
	        	user.setUserType(resultSet.getString(2));
	        } 
	
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
	        if (getusers != null) {
	            try {
	            	getusers.close();
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
