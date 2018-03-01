package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	    PreparedStatement usernamesEmails = null;
	    PreparedStatement insertUser = null;	// # of executed queries
	    ResultSet rs = null;
	    int result = 0;	    
	
	    // Connect to Database and execute INSERT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();
	        usernamesEmails = conn.prepareStatement("SELECT username FROM users WHERE username=? OR emailAddress=?");
	        usernamesEmails.setString(1, user.getUsername());
	        usernamesEmails.setString(2, user.getEmailAddress());
	        rs = usernamesEmails.executeQuery();
	        
	        if(rs.getMetaData().getColumnCount() != 0) {
	        	return false;
	        }	        
	        
	        insertUser = conn.prepareStatement("INSERT INTO users (username, userFirstName, userLastName, password, emailAddress, userType, lastLogin, emailValidated) VALUES (?, ?, ?, ?, ?, ?, ?, 1);");
	        insertUser.setString(1, user.getUsername());
	        insertUser.setString(2, user.getFirstName());
	        insertUser.setString(3, user.getLastName());
	        insertUser.setString(4, user.getPassword());
	        insertUser.setString(5, user.getEmailAddress());
	        insertUser.setString(6, user.getUserType());
	        insertUser.setTimestamp(7, user.getLastLogin());
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
	        if (usernamesEmails != null) {
	            try {
	            	usernamesEmails.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return status;
	}
}
