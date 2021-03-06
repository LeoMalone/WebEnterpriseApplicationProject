package dao;

import beans.ScheduleResultsBean;
import beans.ScorerBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ScheduleResults class gets the schedule or results for league or venue
 * @author Kevin Villemaire
 */
public class ScheduleResults {

	/**
	 * The getSchedule method gets a Leagues schedule
	 * @param sched - <ScheduleResultsBean>
	 * @param id - Current division id
	 * @return status - boolean value
	 */
	public static boolean getSchedule(String id, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with ScheduleResultsBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
					+ " concat(a.teamName, '') as away, s.awayTeam, v.venueName, v.venueID, h.teamLogo, a.teamLogo"
					+ " from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam"
					+ " inner join teamxdivision td on td.teamID = h.teamID inner join division d on"
					+ " d.divisionID = td.divisionID inner join leaguexdivision ld on ld.divisionID = d.divisionID"
					+ " left outer join venuexgame vg on s.gameID = vg.gameID left outer join venue v on vg.venueID ="
					+ " v.venueID where ld.leagueID = ? and s.gameStatus = 'Scheduled'");
			getSchedule.setString(1, id);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			//return to the start of the resultSet
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScheduleResultsBean then add it to the list
			while(resultSet.next()) {
				ScheduleResultsBean sb = new ScheduleResultsBean();
				sb.setDate(resultSet.getDate(1));
				sb.setTime(resultSet.getTime(2));
				sb.setHomeTeam(resultSet.getString(3));
				sb.setHomeID(resultSet.getString(4));
				sb.setAwayTeam(resultSet.getString(5));
				sb.setAwayID(resultSet.getString(6));
				sb.setVenue(resultSet.getString(7));
				sb.setVenueID(resultSet.getString(8));
				sb.setHomeTeamLogo(resultSet.getString(9));
				sb.setAwayTeamLogo(resultSet.getString(10));
				sched.add(sb);
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
	 * The getScheduleWithVenue method gets a Venue's schedule
	 * @param sched - <ScheduleResultsBean>
	 * @param venue - Current venue id
	 * @param lID - Current league id
	 * @return status - boolean value
	 */
	public static boolean getScheduleWithVenue(String venue, String lID, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with ScheduleResultsBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
					+ " concat(a.teamName, '') as away, s.awayTeam, h.teamLogo, a.teamLogo from schedule s"
					+ " inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam"
					+ " inner join teamxdivision td on td.teamID = h.teamID inner join division d on d.divisionID"
					+ " = td.divisionID inner join leaguexdivision ld on ld.divisionID = d.divisionID inner join"
					+ " venuexgame vg on s.gameID = vg.gameID inner join venue v on vg.venueID = v.venueID where"
					+ " ld.leagueID = ? and s.gameStatus = 'Scheduled' and v.venueID=?");
			getSchedule.setString(1, lID);
			getSchedule.setString(2, venue);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScheduleResultsBean then add it to the list
			while(resultSet.next()) {
				ScheduleResultsBean sb = new ScheduleResultsBean();
				sb.setDate(resultSet.getDate(1));
				sb.setTime(resultSet.getTime(2));
				sb.setHomeTeam(resultSet.getString(3));
				sb.setHomeID(resultSet.getString(4));
				sb.setAwayTeam(resultSet.getString(5));
				sb.setAwayID(resultSet.getString(6));
				sb.setHomeTeamLogo(resultSet.getString(7));
				sb.setAwayTeamLogo(resultSet.getString(8));
				sched.add(sb);
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
	 * The getScheduleWithTeam method gets a Teams schedule
	 * @param sched - <ScheduleResultsBean>
	 * @param team - Current team id
	 * @param lID - Current league id
	 * @return status - boolean value
	 */
	public static boolean getScheduleWithTeam(String team, String lID, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with ScheduleResultsBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
					+ " concat(a.teamName, '') as away, s.awayTeam, v.venueName, v.venueID, h.teamLogo, a.teamLogo"
					+ " from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam"
					+ " inner join teamxdivision td on td.teamID = h.teamID inner join division d on d.divisionID"
					+ " = td.divisionID inner join leaguexdivision ld on ld.divisionID = d.divisionID left outer"
					+ " join venuexgame vg on s.gameID = vg.gameID left outer join venue v on vg.venueID = v.venueID"
					+ " where ld.leagueID = ? and s.gameStatus = 'Scheduled' and (s.homeTeam=? or s.awayTeam=?)");

			getSchedule.setString(1, lID);
			getSchedule.setString(2, team);
			getSchedule.setString(3, team);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			//return to the start of the resultSet
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScheduleResultsBean then add it to the list
			while(resultSet.next()) {
				ScheduleResultsBean sb = new ScheduleResultsBean();
				sb.setDate(resultSet.getDate(1));
				sb.setTime(resultSet.getTime(2));
				sb.setHomeTeam(resultSet.getString(3));
				sb.setHomeID(resultSet.getString(4));
				sb.setAwayTeam(resultSet.getString(5));
				sb.setAwayID(resultSet.getString(6));
				sb.setVenue(resultSet.getString(7));
				sb.setVenueID(resultSet.getString(8));
				sb.setHomeTeamLogo(resultSet.getString(9));
				sb.setAwayTeamLogo(resultSet.getString(10));
				sched.add(sb);
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
	 * The getResultsWithTeam method gets a Teams resent results ordered by recent games first
	 * @param sched - <ScheduleResultsBean>
	 * @param team - Current team id
	 * @param lID - Current league id
	 * @return status - boolean value
	 */
	public static boolean getResultsWithTeam(String team, String lID, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with ScheduleResultsBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, h.teamName, s.homeTeam, s.homeScore, concat(a.teamName, '')" 
					+ " as away, s.awayTeam, s.awayScore, s.gameStatus, s.gameID, h.teamLogo, a.teamLogo from schedule s"
					+ " inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam inner join"
					+ " teamxdivision td on td.teamID = h.teamID inner join division d on d.divisionID = td.divisionID inner"
					+ " join leaguexdivision ld on ld.divisionID = d.divisionID left outer join venuexgame vg on"
					+ " s.gameID = vg.gameID left outer join venue v on vg.venueID = v.venueID where ld.leagueID = ?"
					+ " and s.gameStatus = 'Final' and (s.homeTeam=? or s.awayTeam=?) order by s.gameDate desc");
			getSchedule.setString(1, lID);
			getSchedule.setString(2, team);
			getSchedule.setString(3, team);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			//return to the start of the resultSet
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScheduleResultsBean then add it to the list
			while(resultSet.next()) {
				ScheduleResultsBean srb = new ScheduleResultsBean();
				srb.setDate(resultSet.getDate(1));
				srb.setHomeTeam(resultSet.getString(2));
				srb.setHomeID(resultSet.getString(3));
				srb.setHomeScore(resultSet.getInt(4));
				srb.setAwayTeam(resultSet.getString(5));
				srb.setAwayID(resultSet.getString(6));
				srb.setAwayScore(resultSet.getInt(7));
				srb.setStatus(resultSet.getString(8));
				srb.setGameID(resultSet.getInt(9));
				srb.setHomeTeamLogo(resultSet.getString(10));
				srb.setAwayTeamLogo(resultSet.getString(11));
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
	 * The getResults method gets a Divisions results
	 * @param sched - <ScheduleResultsBean>
	 * @param id - Current league id
	 * @param offset - The offset to get the news from (for pagination)
	 * @param maxRows - The number of results to be fetched each time
	 * @return status - boolean value
	 */
	public static boolean getResults(String id, List<ScheduleResultsBean> sched, int offset, int maxRows) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getResults = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getResults = conn.prepareStatement("select s.gameID, s.gameDate, h.teamName, s.homeTeam, s.homeScore,"
					+ " concat(a.teamName, '') as away, s.awayTeam, s.awayScore, s.gameStatus, s.gameID, h.teamLogo, a.teamLogo"
					+ " from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID"
					+ " = s.awayTeam inner join teamxdivision td on td.teamID = h.teamID inner join division d on"
					+ " d.divisionID = td.divisionID inner join leaguexdivision ld on ld.divisionID = d.divisionID"
					+ " where ld.leagueID = ? and s.gameStatus = 'Final' order by s.gameDate desc limit ?,?");

			getResults.setString(1, id);
			getResults.setInt(2, offset);
			getResults.setInt(3, maxRows);

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
				srb.setGameID(resultSet.getInt(1));
				srb.setDate(resultSet.getDate(2));
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
				srb.setHomeTeamLogo(resultSet.getString(11));
				srb.setAwayTeamLogo(resultSet.getString(12));
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
	 * The numberOfResults method gets the number of results associated with each division
	 * @param lID - The current league id
	 * @return number - int value
	 */
	public static int numberOfResults(String lID) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getResult = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set
		int number = 0;							// number of news articles

		// Connect to Database and execute SELECT query
		try {
			conn = ConnectionManager.getConnection();
			getResult = conn.prepareStatement("select count(s.gameID) from schedule s inner join team h"
					+ " on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision"
					+ " td on td.teamID = h.teamID inner join division d on d.divisionID = td.divisionID inner join"
					+ " leaguexdivision ld on ld.divisionID = d.divisionID where ld.leagueID = ? and s.gameStatus = 'Final'");
			getResult.setString(1, lID);
			resultSet = getResult.executeQuery();
			status = resultSet.next();

			if(status)
				number = resultSet.getInt(1);


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
			if (getResult != null) {
				try {
					getResult.close();
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
		return number;
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
			getScorers = conn.prepareStatement("select playerID, playerName, goals, playerHidePage from scorers"
					+ " where teamName=? and id=? group by id, playerName order by goals desc");
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
				sb.setHidePage(resultSet.getBoolean(4));
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
			getScorers = conn.prepareStatement("select playerID, playerName, goals, playerHidePage from scorers"
					+ " where teamName=? and id=? group by id, playerName order by goals desc");

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
				sb.setHidePage(resultSet.getBoolean(4));
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

	/**
	 * The getPlayoffSchedule method gets a Leagues playoff schedule
	 * @param sched - <ScheduleResultsBean>
	 * @param id - Current division id
	 * @return status - boolean value
	 */
	public static boolean getPlayoffSchedule(String id, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with ScheduleResultsBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
					+ " concat(a.teamName, '') as away, s.awayTeam, v.venueName, v.venueID, h.teamLogo, a.teamLogo from"
					+ " schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam"
					+ " inner join teamxdivision td on td.teamID = h.teamID inner join division d on"
					+ " d.divisionID = td.divisionID inner join leaguexdivision ld on ld.divisionID = d.divisionID"
					+ " left outer join venuexgame vg on s.gameID = vg.gameID left outer join venue v on vg.venueID ="
					+ " v.venueID where ld.leagueID = ? and s.gameStatus = 'Scheduled' and s.playoffGame = 1");
			getSchedule.setString(1, id);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			//return to the start of the resultSet
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a ScheduleResultsBean then add it to the list
			while(resultSet.next()) {
				ScheduleResultsBean sb = new ScheduleResultsBean();
				sb.setDate(resultSet.getDate(1));
				sb.setTime(resultSet.getTime(2));
				sb.setHomeTeam(resultSet.getString(3));
				sb.setHomeID(resultSet.getString(4));
				sb.setAwayTeam(resultSet.getString(5));
				sb.setAwayID(resultSet.getString(6));
				sb.setVenue(resultSet.getString(7));
				sb.setVenueID(resultSet.getString(8));
				sb.setAwayTeamLogo(resultSet.getString(9));
				sb.setHomeTeamLogo(resultSet.getString(10));
				sched.add(sb);
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
