package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.TeamBean;
import db.ConnectionManager;

public class EditTeam {
	
public static boolean getTeamForEdit(TeamBean team) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getTeam = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getTeam = conn.prepareStatement("select teamName, teamAbbreviation from team where teamID=?");
	        getTeam.setString(1, team.getTeamId());
	        rs = getTeam.executeQuery();	              
	        
	        if(rs.next()) {
	        	team.setTeamName(rs.getString(1));
	        	team.setTeamAbbreviation(rs.getString(2));
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
	        if (getTeam != null) {
	            try {
	            	getTeam.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}

	public static boolean saveChanges(TeamBean team) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement updateTeam = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        updateTeam = conn.prepareStatement("UPDATE team SET teamName=?, teamAbbreviation=? WHERE teamID=?");
	        updateTeam.setString(1, team.getTeamName());
	        updateTeam.setString(2, team.getTeamAbbreviation());
	        updateTeam.setString(3, team.getTeamId());
	        
	        
	        result = updateTeam.executeUpdate();	        
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
	        if (updateTeam != null) {
	            try {
	            	updateTeam.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
