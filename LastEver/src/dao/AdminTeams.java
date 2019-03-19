package dao;

import beans.DivisionBean;
import beans.TeamBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The AdminTeams class handles all db operation relating to editing a Team by an Admin
 * @author Liam Maloney
 */
public class AdminTeams {
	
	/**
	 * The getAllTeams methods get all teams from the db
	 * @param divId divisionId from servlet
	 * @param divisionList DvivisionBean list from servlet
	 * @param teamList TeamBean list from servlet
	 * @return boolean status 
	 */
	public static boolean getAllTeams(String divId, List<DivisionBean> divisionList, List<TeamBean> teamList) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement teamsWithNoDiv = null;
	    PreparedStatement allTeams = null;
	    PreparedStatement allDivisions = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        allDivisions = conn.prepareStatement("SELECT divisionID, divisionName from division");
	        rs = allDivisions.executeQuery();
	        
	        while(rs.next()) {
	        	DivisionBean db = new DivisionBean();
	        	db.setDivisionId(rs.getString(1));
	        	db.setDivisionName(rs.getString(2));
	        	divisionList.add(db);
	        }        
	        
	        if(divId == null) {
	        	allTeams = conn.prepareStatement("SELECT t.teamID, t.teamName, t.teamAbbreviation, t.teamAbout FROM team t natural left join teamxdivision td WHERE td.divisionID IS NULL;");
	        	
	        } else {	        	
		        allTeams = conn.prepareStatement("SELECT team.teamID, teamName, teamAbbreviation, teamAbout, divisionID FROM team, teamxdivision WHERE divisionID=? AND team.teamID=teamxdivision.teamID");
		        allTeams.setString(1, divId);
	        }
	        
	        rs = allTeams.executeQuery();		        
	        while(rs.next()) {
	        	TeamBean tb = new TeamBean();
	        	tb.setTeamId(rs.getString(1));
	        	tb.setTeamName(rs.getString(2));
	        	tb.setTeamAbbreviation(rs.getString(3));
	        	tb.setTeamAbout(rs.getString(4));
	        	if(divId != null)
	        		tb.setDivisionId(rs.getString(5));
	        	else
	        		tb.setDivisionId("0");
	        	teamList.add(tb);
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
	        if (allTeams != null) {
	            try {
	            	allTeams.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (allDivisions != null) {
	            try {
	            	allDivisions.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (teamsWithNoDiv != null) {
	            try {
	            	teamsWithNoDiv.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	/**
	 * The teamsForEditSchedule gets all teams from the db for the schedule forms
	 * @param teamList TeamBean list
	 * @return boolean status
	 */
	public static boolean teamsForEditSchedule(List<TeamBean> teamList) {
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement allTeams = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        allTeams = conn.prepareStatement("SELECT teamID, teamName, teamAbbreviation from team");
	        rs = allTeams.executeQuery();
	        
	        while(rs.next()) {
	        	TeamBean tb = new TeamBean();
	        	tb.setTeamId(rs.getString(1));
	        	tb.setTeamName(rs.getString(2));
	        	tb.setTeamAbbreviation(rs.getString(3));
	        	teamList.add(tb);
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
	        if (allTeams != null) {
	            try {
	            	allTeams.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
