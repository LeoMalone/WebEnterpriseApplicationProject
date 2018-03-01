package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.RefBean;
import db.ConnectionManager;

public class EditRefUser {
	
	public static boolean getUserForEdit(RefBean user) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getUser = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getUser = conn.prepareStatement("select userID, username, userType, emailAddress, password from users where userType='referee'=?");
	        getUser.setString(1, user.getId());
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
	
	public static boolean saveChanges(RefBean user) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement updateUser = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        updateUser = conn.prepareStatement("UPDATE users SET username=?, userType=?, emailAddress=?, password=?, emailValidated=? WHERE userID=?");
	        updateUser.setString(1, user.getUsername());
	        updateUser.setString(2, user.getUserType());
	        updateUser.setString(3, user.getEmailAddress());
	        updateUser.setString(4, user.getPassword());
	        updateUser.setInt(5, 1);
	        updateUser.setInt(6, Integer.parseInt(user.getId()));
	        
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
