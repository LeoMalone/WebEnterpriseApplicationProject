package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.UserBean;
import db.ConnectionManager;

/**
 * The TeamEmails class handles all db operation relating to emails from a Team Owner to its players,
 * other team owners in the division, and Admins
 * @author Kevin Read
 */
public class TeamEmails {
	
	/**
	 * The getAllEmails method get all admins and team owners from the db
	 * @param admins - List<UserBean>
	 * @param tos - List<UserBean>
	 * @return boolean status
	 */
	public static boolean getAllEmails(List<UserBean> admins, List<UserBean> tos) {
		
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
	        	if(rs.getString(1).equals("Team Owner"))
	        		tos.add(user);
	        	
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
	 * The getTeamOwnerEmails method get all teamowner email addresses from the db
	 * @param tos - List<UserBean>
	 * @param division - String
	 * @return boolean status
	 */
	public static boolean getTeamOwnerEmails(List<UserBean> tos, String division) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement teamOwnerEmails = null;
	    ResultSet rs = null;
	    String tOwn = "Team Owner";
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        teamOwnerEmails = conn.prepareStatement("SELECT emailAddress, userFirstName, userLastName from users where userID=?");
	        teamOwnerEmails.setString(1, tOwn);
	        rs = teamOwnerEmails.executeQuery();	        
	        
	        while(rs.next()) {
	        	UserBean user = new UserBean();
	        	user.setEmail(rs.getString(1));
        		user.setFirstName(rs.getString(2));
        		user.setLastName(rs.getString(3));
        		user.setUserType(tOwn);
        		tos.add(user);
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
	        if (teamOwnerEmails != null) {
	            try {
	            	teamOwnerEmails.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	
	/**
	 * The getAllEmailsForPost method gets all team owner and administrator email addresses from the db
	 * @param emails - List<String>
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
	        allEmails = conn.prepareStatement("SELECT emailAddress from users where (userType=? or userType=?)");
	        allEmails.setString(1, "Administrator");
	        allEmails.setString(2, "Team Owner");
	        rs = allEmails.executeQuery();	        
	        
	        while(rs.next()) {
	        	emails.add(rs.getString(1));
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
