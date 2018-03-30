package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import beans.RefBean;
import db.ConnectionManager;

/**
 * The EditRefUser class handles all db operation relating to editing a ref user
 */
public class EditRefUser {
	
	/**
	 * The getUserForEdit method gets the referee user
	 * @param user - RefBean
	 * @return status - boolean value
	 */
	public static boolean getUserForEdit(RefBean user, String username) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getUser = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getUser = conn.prepareStatement("select userID, username, userType, emailAddress, password from users where"
	        		+ " userType='referee' and username=?");
	        getUser.setString(1, username);
	        rs = getUser.executeQuery();	              
	        
	        if(rs.next()) {
	        	user.setId(rs.getString(1));
	        	user.setUsername(rs.getString(2));
	        	user.setUserType(rs.getString(3));
	        	user.setEmail(rs.getString(4));
	        	user.setPassword(rs.getString(5));
	        	status = true;
	        }
	        
	        
	    // Catch all possible Exceptions 1 change made
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
	        if (getUser != null) {
	            try {
	            	getUser.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	/**
	 * The saveChanges method saves the editing of information about the referee
	 * @param user - RefBean
	 * @return status - boolean value
	 */
	public static boolean saveChanges(RefBean user) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement updateUser = null;
	    int result = 0;
	    String hashedpw;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        
	        if(user.getPassword() == "") {
	        	updateUser = conn.prepareStatement("UPDATE users SET emailAddress=?, emailValidated=?, accountUpdated=? WHERE userID=?");
		        updateUser.setString(1, user.getEmailAddress());
		        updateUser.setInt(2, 1);
		        updateUser.setTimestamp(3, user.getAccountUpdated());
		        updateUser.setString(4, user.getId());
	        
	        } else {
	        	updateUser = conn.prepareStatement("UPDATE users SET emailAddress=?, password=?, emailValidated=?, accountUpdated=? WHERE userID=?");
	        	//Using BCrypt hash the users password with a new generated salt
		        hashedpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
		        updateUser.setString(1, user.getEmailAddress());
		        updateUser.setString(2, hashedpw);
		        updateUser.setInt(3, 1);
		        updateUser.setTimestamp(4, user.getAccountUpdated());
		        updateUser.setString(5, user.getId());
	        }
	        
	        result = updateUser.executeUpdate();	        
	        if(result == 1) {
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
	        if (updateUser != null) {
	            try {
	            	updateUser.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	/**
	 * The deleteRefUser method deletes the referee user from the DB
	 * @param id - int
	 * @return status - boolean value
	 */
	public static boolean deleteRefUser(int id) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement deleteUser = null;
	    int result = 0;	    
	    
	    try {
	        conn = ConnectionManager.getConnection();
	        deleteUser = conn.prepareStatement("DELETE FROM users WHERE userID=?");
	        deleteUser.setInt(1, id);
	        
	        result = deleteUser.executeUpdate();	        
	        if(result == 1) {
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
	        if (deleteUser != null) {
	            try {
	            	deleteUser.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
