package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.StatisticsBean;

/**
 * The Statistics class gets the statistics for the current league
 * @author Kevin Villemaire
 */
public class Statistics {

	/**
	 * The getStatistics method gets the league statistics
	 * @param statistics - <StatisticsBean>
	 * @param id - The current id of the league
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
			getStatistics = conn.prepareStatement("select s.teamName, s.GP, s.playerName, s.goals, s.yellowCards,"
					+ " s.redCards, s.playerID, s.teamID, s.playerHidePage, t.teamLogo from statistics s left outer join"
					+ " team t on t.teamName = s.teamName where leagueID = ? and playoffGame = 0 order by s.goals desc,"
					+ " s.GP asc, s.playerName asc");
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

				//save information from database to a Bean
				sb.setTeamName(resultSet.getString(1));
				sb.setGamesPlayed(resultSet.getInt(2));
				sb.setName(resultSet.getString(3));
				sb.setGoals(resultSet.getInt(4));
				sb.setYellowCard(resultSet.getInt(5));
				sb.setRedCard(resultSet.getInt(6));
				sb.setPlayerID(resultSet.getString(7));
				sb.setTeamID(resultSet.getString(8));
				sb.setHidePage(resultSet.getBoolean(9));
				sb.setTeamLogo(resultSet.getString(10));
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
	 * @param statistics - <StatisticsBean>
	 * @param id - The current id of the team
	 * @param lID - The id of the league
	 * @return status - boolean value
	 */
	public static boolean getStatisticsWithTeam(String id, String lID, List<StatisticsBean> statistics) { 

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
					+ " s.redCards, s.playerID, s.playerHidePage, t.teamLogo from statistics s inner join team t on t.teamName ="
					+ " s.teamName where s.leagueID = ? and t.teamID=? group by s.playerName order by s.goals desc,"
					+ " s.GP asc, s.playerName asc");
			getStatistics.setString(1, lID);
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

				//save information to a Bean
				sb.setTeamName(resultSet.getString(1));
				sb.setGamesPlayed(resultSet.getInt(2));
				sb.setName(resultSet.getString(3));
				sb.setGoals(resultSet.getInt(4));
				sb.setYellowCard(resultSet.getInt(5));
				sb.setRedCard(resultSet.getInt(6));
				sb.setPlayerID(resultSet.getString(7));
				sb.setHidePage(resultSet.getBoolean(8));
				sb.setTeamLogo(resultSet.getString(9));
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
	 * @param statistics - <StatisticsBean>
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
			getStatistics = conn.prepareStatement("select s.teamName, s.GP, s.playerName, s.goals, s.yellowCards,"
					+ " s.redCards, s.teamID, t.teamLogo from statistics s inner join team t on t.teamID = s.teamID"
					+ " where s.playerID = ? group by s.teamName");
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
				sb.setTeamLogo(resultSet.getString(8));
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
	 * The getPlayoffStatistics method gets the leagues playoff statistics
	 * @param statistics - <StatisticsBean>
	 * @param id - The current id of the league
	 * @return status - boolean value
	 */
	public static boolean getPlayoffStatistics(String id, List<StatisticsBean> statistics) { 

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
					+ " s.redCards, s.playerID, s.teamID, s.playerHidePage, t.teamLogo from statistics s inner join team"
					+ " t on t.teamID = s.teamID where s.leagueID = ? and s.playoffGame = 1 order by s.goals desc, s.GP asc,"
					+ " s.playerName asc");
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

				//save results to a bean
				sb.setTeamName(resultSet.getString(1));
				sb.setGamesPlayed(resultSet.getInt(2));
				sb.setName(resultSet.getString(3));
				sb.setGoals(resultSet.getInt(4));
				sb.setYellowCard(resultSet.getInt(5));
				sb.setRedCard(resultSet.getInt(6));
				sb.setPlayerID(resultSet.getString(7));
				sb.setTeamID(resultSet.getString(8));
				sb.setHidePage(resultSet.getBoolean(9));
				sb.setTeamLogo(resultSet.getString(10));
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
}
