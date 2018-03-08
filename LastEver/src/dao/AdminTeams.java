package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.ConnectionManager;
import beans.TeamBean;
import beans.DivisionBean;

public class AdminTeams {
	
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
	        	allTeams = conn.prepareStatement("SELECT t.teamID, t.teamName, t.teamAbbreviation FROM team t natural left join teamxdivision td WHERE td.divisionID IS NULL;");
	        	
	        } else {	        	
		        allTeams = conn.prepareStatement("SELECT team.teamID, teamName, teamAbbreviation, divisionID FROM team, teamxdivision WHERE divisionID=? AND team.teamID=teamxdivision.teamID");
		        allTeams.setString(1, divId);
	        }
	        
	        rs = allTeams.executeQuery();		        
	        while(rs.next()) {
	        	TeamBean tb = new TeamBean();
	        	tb.setTeamId(rs.getString(1));
	        	tb.setTeamName(rs.getString(2));
	        	tb.setTeamAbbreviation(rs.getString(3));
	        	if(divId != null)
	        		tb.setDivisionId(rs.getString(4));
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
}
