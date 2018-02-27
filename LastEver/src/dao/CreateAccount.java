package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.UserBean;
import db.ConnectionManager;

/**
 * The CreateAccount class creates new users in the db from
 * information from a UserBean and the create account servlet
 */
public class CreateAccount {
	
	/**
	 * The createNewUser method performs the INSERT query to the DB
	 * @param user - UserBean object to get create account credentials
	 * @return status 
	 */
	public static boolean createNewUser(UserBean user) { 
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement insertUser = null;	// # of executed queries
	    int result = 0;	    
	
	    // Connect to Database and execute INSERT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();
	        insertUser = conn.prepareStatement("INSERT INTO users (`username`,`password`,`emailAddress`,`userType`,`emailValidated`) " + 
	        		"VALUES ('" + user.getUsername()
	        		+ "', '" + user.getPassword()
	        		+ "', '" + user.getEmailAddress()
	        		+ "', '" + user.getUserType()
	        		+ "', 1);");
	        result = insertUser.executeUpdate();
	        
	        // Return true if 1 query executes
	        if(result == 1)
	        	status = true;
	
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
