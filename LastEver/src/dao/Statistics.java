package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.StatisticsBean;

/**
 * The Statistics class gets the statistics for the current division
 */
public class Statistics {

	/**
	 * The getStatistics method gets the divisions statistics
	 * @param <StatisticsBean>
	 * @param id - The current id of the division
	 * @return status - boolean value
	 */
	public static boolean getStatistics(String id, List<StatisticsBean> statistics) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getStatistics = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		int rank = 1;							// overall ranking in the statistics
		int increase = 1;						// the amount to increase the rank by

		// Connect to Database and execute SELECT query with StatisticsBean data
		try {
			conn = ConnectionManager.getConnection();
			getStatistics = conn.prepareStatement("select teamName, GP, playerName, goals, yellowCards,"
					+ " redCards, playerID, teamID from statistics where divisionID = ? order by goals desc, GP asc,"
					+ " playerName asc");
			getStatistics.setString(1, id);
			resultSet = getStatistics.executeQuery();
			status = resultSet.next();

			//return to the start of the result set
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a StatisticsBean then add it to the list
			while(resultSet.next()) {
				StatisticsBean sb = new StatisticsBean();
				
				// if there is no statistics in the list then set the rank to be the first rank
				if(statistics.size() == 0) {
					sb.setRank("" + rank);	
				}
				else {
					/* if the current player has the same goals as the one before it add a T (tied) to the rank
					   and keep the rank the same and increase the value to increase the rank by the next time
					   there is a difference in the number of goals  */
					if(resultSet.getInt(4) == statistics.get(statistics.size()-1).getGoals()) {
						statistics.get(statistics.size()-1).setRank("T" + rank);
						sb.setRank("T" + rank);
						increase++;
					}
					// if goals are not the same then add the amount of increase to the rank and set the rank to the current value
					// reset increase to be one
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
				sb.setPlayerID(resultSet.getString(7));
				sb.setTeamID(resultSet.getString(8));
				statistics.add(sb);
			}

			// close all connections handle all possible exceptions
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
			if (getStatistics != null) {
				try {
					getStatistics.close();
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

	
	/**
	 * The getStatisticsWithTeam method gets the teams statistics
	 * @param <StatisticsBean>
	 * @param id - The current id of the team
	 * @param div - The id of the division
	 * @return status - boolean value
	 */
	public static boolean getStatisticsWithTeam(String id, String div, List<StatisticsBean> statistics) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getStatistics = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		int rank = 1;							// overall ranking in the statistics
		int increase = 1;						// the amount to increase the rank by

		// Connect to Database and execute SELECT query with StatisticsBean data
		try {
			conn = ConnectionManager.getConnection();
			getStatistics = conn.prepareStatement("select s.teamName, s.GP, s.playerName, s.goals, s.yellowCards,"
					+ " s.redCards, s.playerID from statistics s inner join team t on t.teamName = s.teamName"
					+ " where s.divisionID = ? and t.teamID=? group by s.playerName order by s.goals desc,"
					+ " s.GP asc, s.playerName asc");
			getStatistics.setString(1, div);
			getStatistics.setString(2, id);
			resultSet = getStatistics.executeQuery();
			status = resultSet.next();

			//return to the start of the result set
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a StatisticsBean then add it to the list
			while(resultSet.next()) {
				StatisticsBean sb = new StatisticsBean();
				
				// if there is no statistics in the list then set the rank to be the first rank
				if(statistics.size() == 0) {
					sb.setRank("" + rank);	
				}
				else {
					/* if the current player has the same goals as the one before it add a T (tied) to the rank
					   and keep the rank the same and increase the value to increase the rank by the next time
					   there is a difference in the number of goals  */
					if(resultSet.getInt(4) == statistics.get(statistics.size()-1).getGoals()) {
						statistics.get(statistics.size()-1).setRank("T" + rank);
						sb.setRank("T" + rank);
						increase++;
					}
					// if goals are not the same then add the amount of increase to the rank and set the rank to the current value
					// reset increase to be one
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
				sb.setPlayerID(resultSet.getString(7));
				statistics.add(sb);
			}

			// close all connections and handle all possible exceptions
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
			if (getStatistics != null) {
				try {
					getStatistics.close();
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
		
	/**
	 * The getStatisticsWithPlayer method gets the players statistics
	 * @param <StatisticsBean>
	 * @param pID - The current id of the player
	 * @return status - boolean value
	 */
	public static boolean getStatisticsWithPlayer(String pID, List<StatisticsBean> statistics) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getStatistics = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with StatisticsBean data
		try {
			conn = ConnectionManager.getConnection();
			getStatistics = conn.prepareStatement("select teamName, GP, playerName, goals, yellowCards,"
					+ " redCards, teamID from statistics where playerID = ? group by teamName");
			getStatistics.setString(1, pID);
			resultSet = getStatistics.executeQuery();
			status = resultSet.next();

			//return the start of the result set
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a StatisticsBean then add it to the list
			while(resultSet.next()) {
				StatisticsBean sb = new StatisticsBean();
				sb.setTeamName(resultSet.getString(1));
				sb.setGamesPlayed(resultSet.getInt(2));
				sb.setName(resultSet.getString(3));
				sb.setGoals(resultSet.getInt(4));
				sb.setYellowCard(resultSet.getInt(5));
				sb.setRedCard(resultSet.getInt(6));
				sb.setTeamID(resultSet.getString(7));
				statistics.add(sb);
			}

			// close all connections and handle all possible exceptions
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
			if (getStatistics != null) {
				try {
					getStatistics.close();
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
