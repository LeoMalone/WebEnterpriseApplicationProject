package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.UserBean;
import db.ConnectionManager;

/**
 * The AdminUsers class handles all db operation relating to editing a User by an Admin
 * @author Liam Maloney, Neal Sen
 */
public class RefEmail {
	
	/**
	 * The getAllUsers methods get all users from the db
	 * @param userList UserBean list from servlet
	 * @return boolean status
	 */
	public static boolean getAllEmails(List<UserBean> admins, List<UserBean> refs) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement allEmails = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        allEmails = conn.prepareStatement("SELECT userType, emailAddress, userFirstName, userLastName from users");
	        rs = allEmails.executeQuery();	        
	        
	        while(rs.next()) {
	        	UserBean user = new UserBean();
	        	user.setEmail(rs.getString(2));
        		user.setFirstName(rs.getString(3));
        		user.setLastName(rs.getString(4));
        		
	        	if(rs.getString(1).equals("Administrator"))
	        		admins.add(user);
	        	if(rs.getString(1).equals("Referee"))
	        		refs.add(user);     	
	        	
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
	        if (allEmails != null) {
	            try {
	            	allEmails.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	/**
	 * The getAllUsers methods get all users from the db
	 * @param userList UserBean list from servlet
	 * @return boolean status
	 */
	public static boolean getAllEmailsForPost(List<String> emails) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement allEmails = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        allEmails = conn.prepareStatement("SELECT userType, emailAddress from users");
	        rs = allEmails.executeQuery();	               
	        
	        while(rs.next()) {
       		
	        	if(rs.getString(1).equals("Administrator"))
	        		emails.add(rs.getString(2));
	        	if(rs.getString(1).equals("Referee"))
	        		emails.add(rs.getString(2));   	
	        	
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
	        if (allEmails != null) {
	            try {
	            	allEmails.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
