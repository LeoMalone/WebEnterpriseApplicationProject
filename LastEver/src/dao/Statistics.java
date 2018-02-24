package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.StatisticsBean;

/**
 * The Standings class gets the standings for the current division
 */
public class Statistics {
	
	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param <StandingsBean>
	 * @param user - UserBean credentials
	 * @return status - boolean value
	 */
	public static boolean getStatistics(String id, List<StatisticsBean> statistics) { 
		
		boolean status = false;					// query status
	    Connection conn = null;					// DB connection
	    PreparedStatement getStandings = null;	// SQL query
	    ResultSet resultSet = null;				// returned query result set
	    int rank = 1;							// overall ranking in the statistics
	    int increase = 1;
	
	    // Connect to Database and execute SELECT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();
	        getStandings = conn.prepareStatement("select teamName, GP, playerName, goals, yellowCards,"
	        		+ " redCards from statistics where divisionID = ? order by goals desc, GP asc, playerName asc");
	        getStandings.setString(1, id);
	        resultSet = getStandings.executeQuery();
	        status = resultSet.next();
	        
	        resultSet.beforeFirst();
	        
	        while(resultSet.next()) {
	        	StatisticsBean sb = new StatisticsBean();
	        	if(statistics.size() == 0) {
	        		sb.setRank("" + rank);	
	        	}
	        	else {
	        		if(resultSet.getInt(4) == statistics.get(statistics.size()-1).getGoals()) {
	        			statistics.get(statistics.size()-1).setRank("T" + rank);
	        			sb.setRank("T" + rank);
	        			increase++;
	        		}
	        		else {
	        			rank += increase;
	        			sb.setRank("" + rank);
	        			increase = 1;
	        		}
	        	}
	        	
	        	sb.setTeamName(resultSet.getString(1));
	        	sb.setGamesPlayed(resultSet.getInt(2));
	        	sb.setName(resultSet.getString(3));
	        	sb.setGoals(resultSet.getInt(4));
	        	sb.setYellowCard(resultSet.getInt(5));
	        	sb.setRedCard(resultSet.getInt(6));
	        	statistics.add(sb);
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
