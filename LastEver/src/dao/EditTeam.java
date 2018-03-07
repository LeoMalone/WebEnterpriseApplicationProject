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
	    PreparedStatement updateTeamDiv = null;
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
	        	updateTeamDiv = conn.prepareStatement("CALL update_team(?, ?)");
	        	updateTeamDiv.setString(1, team.getTeamId());
	        	updateTeamDiv.setString(2, team.getDividionId());
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
