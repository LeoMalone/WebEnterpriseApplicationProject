package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.TeamBean;
import db.ConnectionManager;

/**
 * The TeamCreateTeam class gets the teamID and creates new team
 */
public class TeamCreateTeam {
	
	/**
	 * The getTeamId method gets the teamID
	 * @param team - TeamBean
	 * @return status - boolean value
	 */
	public static boolean getTeamId(TeamBean team) {
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getTeam = null;	// # of executed queries
	    ResultSet rs = null;    
	
	    // Connect to Database and execute INSERT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();        
	        getTeam = conn.prepareStatement("SELECT teamID FROM team WHERE team.teamAbbreviation=?");
	        getTeam.setString(1, team.getTeamAbbreviation());
	        rs = getTeam.executeQuery();
	        // Return true if 1 query executes
	        if(rs.next()) {
	        	team.setTeamId(rs.getString(1));
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
	
	/**
	 * The createNewTeam method performs the INSERT query to the DB
	 * @param team - TeamBean
	 * @param userName - String
	 * @return status  - boolean value
	 */
	public static boolean createNewTeam(TeamBean team, String userName) { 
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement insertTeam = null;	// # of executed queries
	    PreparedStatement insertIntoDiv = null;	// # of executed queries
	    PreparedStatement insertIntoUserXTeam = null;	// # of executed queries
	    int result = 0;	    
	
	    // Connect to Database and execute INSERT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();        
	        insertTeam = conn.prepareStatement("INSERT INTO team (teamName, teamAbbreviation) VALUE (?, ?)");
	        insertTeam.setString(1, team.getTeamName());
	        insertTeam.setString(2, team.getTeamAbbreviation());
	        result = insertTeam.executeUpdate();	        
	        // Return true if 1 query executes
	        if(result == 1) {
	        	getTeamId(team);
	        	insertIntoDiv = conn.prepareStatement("INSERT INTO teamxdivision (teamID, divisionID) VALUE (?, ?)");
	        	insertIntoDiv.setString(1, team.getTeamId());
	        	insertIntoDiv.setString(2, team.getDivisionId());
	        	result = insertIntoDiv.executeUpdate();
	        	if(result == 1) {
	        		
	        		insertIntoUserXTeam = conn.prepareStatement("INSERT INTO usersxteam (userID, teamID) VALUE (?, ?)");
	        		insertIntoUserXTeam.setString(1, userName);
	        		insertIntoUserXTeam.setString(2, team.getTeamId());
		        	result = insertIntoUserXTeam.executeUpdate();
	        		
	        		
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
	        if (insertTeam != null) {
	            try {
	            	insertTeam.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (insertIntoDiv != null) {
	            try {
	            	insertIntoDiv.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return status;
	}
}
