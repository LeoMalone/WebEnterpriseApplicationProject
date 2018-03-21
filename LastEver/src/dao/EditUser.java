package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import beans.UserBean;
import db.ConnectionManager;

/**
 * The EditUser class handles all db operation relating to editing a user
 */
public class EditUser {
	
	/**
	 * The get UsersforEdit method gets one users credentials to drisplay for edit
	 * @param user User Bean from servlet
	 * @return boolean status
	 */
	public static boolean getUserForEdit(UserBean user) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getUser = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getUser = conn.prepareStatement("select userID, username, userType, emailAddress, password, userFirstName, userLastName from users where userID=?");
	        getUser.setString(1, user.getId());
	        rs = getUser.executeQuery();	              
	        
	        if(rs.next()) {
	        	user.setId(rs.getString(1));
	        	user.setUsername(rs.getString(2));
	        	user.setUserType(rs.getString(3));
	        	user.setEmail(rs.getString(4));
	        	user.setPassword(rs.getString(5));
	        	user.setFirstName(rs.getString(6));
	        	user.setLastName(rs.getString(7));
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
	 * The saveChanges method saves the changes for 1 user into the db
	 * @param User Bean from servlet 
	 * @return boolean status
	 */
	public static boolean saveChanges(UserBean user) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement updateUser = null;
	    int result = 0;
	    String hashedpw = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        
	        if(user.getPassword() == "") {
	        	updateUser = conn.prepareStatement("UPDATE users SET username=?, userType=?, emailAddress=?, emailValidated=?, userFirstName=?, userLastName=?, accountUpdated=? WHERE userID=?");
	        	updateUser.setString(1, user.getUsername());
		        updateUser.setString(2, user.getUserType());
		        updateUser.setString(3, user.getEmailAddress());
		        updateUser.setInt(4, 1);
		        updateUser.setString(5, user.getFirstName());
		        updateUser.setString(6, user.getLastName());
		        updateUser.setTimestamp(7, user.getAccountUpdated());
		        updateUser.setString(8, user.getId());
	        
	        } else {
	        	updateUser = conn.prepareStatement("UPDATE users SET username=?, userType=?, emailAddress=?, password=?, emailValidated=?, userFirstName=?, userLastName=?, accountUpdated=? WHERE userID=?");
	        	//Using BCrypt hash the users password with a new generated salt
		        hashedpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
		        updateUser.setString(1, user.getUsername());
		        updateUser.setString(2, user.getUserType());
		        updateUser.setString(3, user.getEmailAddress());
		        updateUser.setString(4, hashedpw);
		        updateUser.setInt(5, 1);
		        updateUser.setString(6, user.getFirstName());
		        updateUser.setString(7, user.getLastName());
		        updateUser.setTimestamp(8, user.getAccountUpdated());
		        updateUser.setString(9, user.getId());
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
	 * The deleteUser method delete a user from the db based on id
	 * @param id user if from servlet
	 * @return boolean status
	 */
	public static boolean deleteUser(String id) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement deleteNews = null;
	    PreparedStatement deleteTeam = null;
	    PreparedStatement deleteUser = null;
	    int result = 0;   
	    
	    try {
	        conn = ConnectionManager.getConnection();
	        deleteNews = conn.prepareStatement("DELETE FROM news USING news, users WHERE `users`.`userID` = `news`.`userID` AND users.userID=?");
	        deleteNews.setString(1, id);
	        result = deleteNews.executeUpdate();
	        if(result >= 0) {
	        	deleteTeam = conn.prepareStatement("DELETE FROM usersxteam USING usersxteam, users WHERE `users`.`userID` = `usersxteam`.`userID` AND users.userID=?");
	        	deleteTeam.setString(1, id);
	        	result = deleteTeam.executeUpdate();
	        	if(result == 1 || result == 0) {
	        		deleteUser = conn.prepareStatement("DELETE FROM users USING users WHERE users.userID=?");
			        deleteUser.setString(1, id);
			        result = deleteUser.executeUpdate();
			        if(result == 1) {
			        	status = true;
			        }
	        	}
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
	        if (deleteNews != null) {
	            try {
	            	deleteNews.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (deleteTeam != null) {
	            try {
	            	deleteTeam.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
