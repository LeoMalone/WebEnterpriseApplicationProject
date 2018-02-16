package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.UserBean;
import db.ConnectionManager;

public class CreateAccount {
	
	public static boolean createNewUser(UserBean user) { 
		
		boolean status = false;
	    Connection conn = null;
	    PreparedStatement insertUser = null;
	    int result = 0;
	
	    try {
	        conn = ConnectionManager.getConnection();
	        insertUser = conn.prepareStatement("INSERT INTO users (`username`,`password`,`emailAddress`,`userType`,`emailValidated`) " + 
	        		"VALUES ('" + user.getUsername()
	        		+ "', '" + user.getPassword()
	        		+ "', '" + user.getEmailAddress()
	        		+ "', '" + user.getUserType()
	        		+ "', 1);");
	        result = insertUser.executeUpdate();
	        if(result == 1) {
	        	status = true;
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
	        if (insertUser != null) {
	            try {
	            	insertUser.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return status;
	}
}
