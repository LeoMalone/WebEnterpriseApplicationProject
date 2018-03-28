package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.DivisionBean;
import beans.LeagueBean;
import db.ConnectionManager;

public class League {

	public static boolean getCurrentLeague(String lID, List<LeagueBean> leagueList) {

		boolean status = false;							// query status
		Connection conn = null;							// DB Connection
		PreparedStatement league = null;			// SQL query
		ResultSet rs = null;							// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			league = conn.prepareStatement("SELECT leagueID, leagueName from league where leagueID = ?");
			league.setString(1, lID);
			rs = league.executeQuery();
			status = rs.next();
			
			//return to the start of the result set
			rs.beforeFirst();
			
			//Loop through and add the results of the query to a LeagueBean then add it to the list
			while(rs.next()) {
				LeagueBean lb = new LeagueBean();
				lb.setLeagueId(rs.getString(1));
				lb.setLeagueName(rs.getString(2));
				leagueList.add(lb);
			}

		// close all connections and catch all possible Exceptions
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
			if (league != null) {
				try {
					league.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	public static boolean getLeagueDivisions(String lID, List<DivisionBean> divisionList) {

		boolean status = false;							// query status
		Connection conn = null;							// DB Connection
		PreparedStatement division = null;				// SQL query
		ResultSet rs = null;							// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			division = conn.prepareStatement("SELECT d.divisionID, d.leagueName from division d inner join"
					+ " leaguexdivision ld on ld.divisionID = d.divisionID where ld.leagueID = ? limit 1");
			division.setString(1, lID);
			rs = division.executeQuery();
			status = rs.next();
			
			//return to the start of the result set
			rs.beforeFirst();
			
			//Loop through and add the results of the query to a LeagueBean then add it to the list
			while(rs.next()) {
				DivisionBean lb = new DivisionBean();
				lb.setDivisionId(rs.getString(1));
				lb.setDivisionName(rs.getString(2));
				divisionList.add(lb);
			}

		// close all connections and catch all possible Exceptions
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
			if (division != null) {
				try {
					division.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
}
