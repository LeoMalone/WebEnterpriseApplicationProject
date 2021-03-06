package dao;

import beans.TeamBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The EditTeam class handles all db operation relating to editing a team
 * @author Liam Maloney
 */
public class EditTeam {
	
	/**
	 * The getTeamForEdit method gets the team that is to be edited
	 * @param team - TeamBean
	 * @return status - boolean value
	 */
	public static boolean getTeamForEdit(TeamBean team) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getTeam = null;
	    PreparedStatement getDivId = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getTeam = conn.prepareStatement("SELECT teamName, teamAbbreviation, teamAbout FROM team WHERE team.teamID=?");
	        getTeam.setString(1, team.getTeamId());
	        rs = getTeam.executeQuery();	              
	        
	        if(rs.next()) {
	        	team.setTeamName(rs.getString(1));
	        	team.setTeamAbbreviation(rs.getString(2));
	        	team.setTeamAbout(rs.getString(3));
	        	
	        	getDivId = conn.prepareStatement("SELECT divisionID FROM team, teamxdivision WHERE team.teamID=? AND team.teamID=teamxdivision.teamID");
	        	getDivId.setString(1, team.getTeamId());
	        	rs = getDivId.executeQuery();
	        	if(rs.next())	        	
	        		team.setDivisionId(rs.getString(1));
	        	
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
	        if (getDivId != null) {
	            try {
	            	getDivId.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	/**
	 * The saveChanges method saves the changes for 1 team into the db
	 * @param team - TeamBean from servlet
	 * @return status - boolean value
	 */
	public static boolean saveChanges(TeamBean team) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement updateTeam = null;
	    PreparedStatement updateTeamDiv = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        updateTeam = conn.prepareStatement("UPDATE team SET teamName=?, teamAbbreviation=?, teamAbout=? WHERE teamID=?");
	        updateTeam.setString(1, team.getTeamName());
	        updateTeam.setString(2, team.getTeamAbbreviation());
	        updateTeam.setString(3, team.getTeamAbout());
	        updateTeam.setString(4, team.getTeamId());	        
	        result = updateTeam.executeUpdate();	        
	        if(result == 1) {
	        	updateTeamDiv = conn.prepareStatement("CALL update_team(?, ?)");
	        	updateTeamDiv.setString(1, team.getTeamId());
	        	updateTeamDiv.setString(2, team.getDivisionId());
	        	result = updateTeamDiv.executeUpdate();
	        	if(result >= 0)
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
	        if (updateTeamDiv != null) {
	            try {
	            	updateTeamDiv.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	/**
	 * The deleteTeam method delete a team from the db based on id
	 * @param id - String TeamId from servlet
	 * @return status - boolean value
	 */
	public static boolean deleteTeam(String id) {
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement deleteSchedule = null;
	    PreparedStatement deleteDivision = null;
	    PreparedStatement deleteUser = null;
	    PreparedStatement deleteTeam = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        deleteSchedule = conn.prepareStatement("DELETE FROM schedule USING schedule, team, gamestatistics "
	        		+ "WHERE (team.teamID = schedule.homeTeam OR team.teamID = schedule.awayTeam) AND team.teamID=?");
	        deleteSchedule.setString(1, id);
	        result = deleteSchedule.executeUpdate();
	        if(result >= 0) {
	        	deleteDivision = conn.prepareStatement("DELETE FROM teamxdivision USING teamxdivision, team "
	        			+ "WHERE team.teamID = teamxdivision.teamID AND team.teamID=?");
		        deleteDivision.setString(1, id);
		        result = deleteDivision.executeUpdate();	        
		        if(result == 0 || result == 1) {
		        	deleteUser = conn.prepareStatement("DELETE FROM usersxteam USING usersxteam, team "
		        			+ "WHERE team.teamID = usersxteam.teamID AND team.teamID=?");
		        	deleteUser.setString(1, id);
		        	result = deleteUser.executeUpdate();
		        	if(result >= 0) {
		        		deleteTeam = conn.prepareStatement("DELETE FROM team USING team WHERE team.teamID=?");
		        		deleteTeam.setString(1, id);
		        		result = deleteTeam.executeUpdate();
		        		if(result == 1) {
		        			status = true;
		        		}
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
	        if (deleteDivision != null) {
	            try {
	            	deleteDivision.close();
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
	        if (deleteTeam != null) {
	            try {
	            	deleteTeam.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (deleteSchedule != null) {
	            try {
	            	deleteSchedule.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
