package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.StandingsBean;

/**
 * The Standings class gets the standings for the current division
 */
public class Standings {
	
	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param <StandingsBean>
	 * @param user - UserBean credentials
	 * @return status - boolean value
	 */
	public static boolean getStandings(String id, List<StandingsBean> standings) { 
		
		boolean status = false;					// query status
	    Connection conn = null;					// DB connection
	    PreparedStatement getStandings = null;	// SQL query
	    ResultSet resultSet = null;				// returned query result set
	    int rank = 1;							// overall ranking in the standings
	
	    // Connect to Database and execute SELECT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();
	        getStandings = conn.prepareStatement("select team, GP, W, D, L, PTS, GF, GA, GD from standings"
	        		+ " where divisionID=? order by PTS desc, W desc, L asc, GD desc");
	        getStandings.setString(1, id);
	        resultSet = getStandings.executeQuery();
	        status = resultSet.next();
	        
	        resultSet.beforeFirst();
	        
	        while(resultSet.next()) {
	        	StandingsBean sb = new StandingsBean();
	        	sb.setRank(rank++);
	        	sb.setTeamName(resultSet.getString(1));
	        	sb.setGamesPlayed(resultSet.getInt(2));
	        	sb.setWins(resultSet.getInt(3));
	        	sb.setDraws(resultSet.getInt(4));
	        	sb.setLosses(resultSet.getInt(5));
	        	sb.setPoints(resultSet.getInt(6));
	        	sb.setGoalsFor(resultSet.getInt(7));
	        	sb.setGoalsAgainst(resultSet.getInt(8));
	        	sb.setGoalDiff(resultSet.getInt(9));
	        	standings.add(sb);
	        }
	        
	    // handle all possible exceptions
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
	        if (getStandings != null) {
	            try {
	            	getStandings.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (resultSet != null) {
	            try {
	            	resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return status;
	}
}
