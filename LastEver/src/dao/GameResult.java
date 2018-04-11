package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ScheduleResultsBean;
import beans.ScorerBean;

/**
 * The GameResult class gets detailed information for a particular game
 * @author Kevin Villemaire
 */
public class GameResult {
	/**
	 * The getResult method gets detailed game information for a game
	 * @param sched - <ScheduleResultsBean>
	 * @param id - Current game id
	 * @return status - boolean value
	 */
	public static boolean getResult(String id, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getResults = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getResults = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam, s.homeScore,"
					+ " concat(a.teamName, '') as away, s.awayTeam, s.awayScore, s.gameStatus, s.gameID, v.venueName,"
					+ " v.venueID, h.teamLogo, a.teamLogo from schedule s inner join team h on h.teamID = s.homeTeam"
					+ " inner join team a on a.teamID = s.awayTeam left outer join venuexgame vg on s.gameID = vg.gameID"
					+ " left outer join venue v on vg.venueID = v.venueID where s.gameID = ?");

			getResults.setString(1, id);

			resultSet = getResults.executeQuery();
			status = resultSet.next();

			//return to the start of the resultSet
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScheduleResultsBean then add it to the list
			//Adds the home scorers to a ScorerBean list and do the same for away scorers
			while(resultSet.next()) {
				ScheduleResultsBean srb = new ScheduleResultsBean();
				List<ScorerBean> hlb = new ArrayList<ScorerBean>();
				List<ScorerBean> alb = new ArrayList<ScorerBean>();
				srb.setDate(resultSet.getDate(1));
				srb.setTime(resultSet.getTime(2));
				srb.setHomeTeam(resultSet.getString(3));
				srb.setHomeID(resultSet.getString(4));
				srb.setHomeScore(resultSet.getInt(5));
				srb.setAwayTeam(resultSet.getString(6));
				srb.setAwayID(resultSet.getString(7));
				srb.setAwayScore(resultSet.getInt(8));
				srb.setStatus(resultSet.getString(9));
				//gets the list of scorers for the home team
				getHomeScorers(resultSet.getString(10), resultSet.getString(3), hlb);
				srb.setHomeScorer(hlb);
				//gets the list of scorers for the away team
				getAwayScorers(resultSet.getString(10), resultSet.getString(6), alb);
				srb.setVenue(resultSet.getString(11));
				srb.setVenueID(resultSet.getString(12));
				srb.setHomeTeamLogo(resultSet.getString(13));
				srb.setAwayTeamLogo(resultSet.getString(14));
				srb.setAwayScorer(alb);
				sched.add(srb);
			}

			// handle all possible exceptions
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			//close all connections and handle exceptions
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getResults != null) {
				try {
					getResults.close();
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
	 * The getHomeScorers method gets a games home scorers
	 * @param hb - <ScorerBean>
	 * @param id - Current game id
	 * @param home - ID of the home team
	 * @return status - boolean value
	 */
	public static boolean getHomeScorers(String id, String home, List<ScorerBean> hb) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getScorers = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getScorers = conn.prepareStatement("select playerID, playerName, goals, yellowCards,"
					+ " redCards, playerHidePage from scorers where teamName=? and id=? group by id,"
					+ " playerName order by goals desc");
			getScorers.setString(1, home);
			getScorers.setString(2, id);
			resultSet = getScorers.executeQuery();
			status = resultSet.next();

			//return to the start of the resultSet
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScorerBean then add it to the list
			while(resultSet.next()) {
				ScorerBean sb = new ScorerBean();
				sb.setID(resultSet.getString(1));
				sb.setName(resultSet.getString(2));
				sb.setGoals(resultSet.getInt(3));
				sb.setYellowCards(resultSet.getInt(4));
				sb.setRedCards(resultSet.getInt(5));
				sb.setHidePage(resultSet.getBoolean(6));
				hb.add(sb);
			}

			// handle all possible exceptions
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			//close all connections and handle exceptions
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getScorers != null) {
				try {
					getScorers.close();
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
	 * The getAwayScorers method gets a games away scorers
	 * @param ab - <ScorerBean>
	 * @param id - Current game id
	 * @param away - ID of the away team
	 * @return status - boolean value
	 */
	public static boolean getAwayScorers(String id, String away, List<ScorerBean> ab) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getScorers = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getScorers = conn.prepareStatement("select playerID, playerName, goals, yellowCards,"
					+ " redCards, playerHidePage from scorers where teamName=? and id=? group by id,"
					+ " playerName order by goals desc");

			getScorers.setString(1, away);
			getScorers.setString(2, id);
			resultSet = getScorers.executeQuery();
			status = resultSet.next();

			//return to the start of the resultSet
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScorerBean then add it to the list
			while(resultSet.next()) {
				ScorerBean sb = new ScorerBean();
				sb.setID(resultSet.getString(1));
				sb.setName(resultSet.getString(2));
				sb.setGoals(resultSet.getInt(3));
				sb.setYellowCards(resultSet.getInt(4));
				sb.setRedCards(resultSet.getInt(5));
				sb.setHidePage(resultSet.getBoolean(6));
				ab.add(sb);
			}

			// handle all possible exceptions
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			//close all connections and handle exceptions
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getScorers != null) {
				try {
					getScorers.close();
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
