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
 * The Standings class gets the standings for the current division
 */
public class ScheduleResults {

	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param <StandingsBean>
	 * @param user - UserBean credentials
	 * @return status - boolean value
	 */
	public static boolean getSchedule(String id, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
					+ " concat(a.teamName, '') as away, s.awayTeam, v.venueName, v.venueID from schedule s"
					+ " inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam"
					+ " inner join teamxdivision td on td.teamID = h.teamID inner join venuexgame vg on s.gameID"
					+ " = vg.gameID inner join venue v on vg.venueID = v.venueID where td.divisionID = ? and"
					+ " s.gameStatus = 'Scheduled'");
			getSchedule.setString(1, id);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

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
				sched.add(sb);
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

	public static boolean getScheduleWithVenue(String venue, String div, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
					+ " concat(a.teamName, '') as away, s.awayTeam, from schedule s inner join team h on h.teamID"
					+ " = s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on"
					+ " td.teamID = h.teamID inner join venuexgame vg on s.gameID = vg.gameID inner join venue"
					+ " v on vg.venueID = v.venueIDwhere td.divisionID = ? and s.gameStatus = 'Scheduled' and v.venueID=?");
			getSchedule.setString(1, div);
			getSchedule.setString(2, venue);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScheduleResultsBean sb = new ScheduleResultsBean();
				sb.setDate(resultSet.getDate(1));
				sb.setTime(resultSet.getTime(2));
				sb.setHomeTeam(resultSet.getString(3));
				sb.setHomeID(resultSet.getString(4));
				sb.setAwayTeam(resultSet.getString(5));
				sb.setAwayID(resultSet.getString(6));
				sched.add(sb);
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

	public static boolean getScheduleWithTeam(String team, String div, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
					+ " concat(a.teamName, '') as away, s.awayTeam, v.venueName, v.venueID from schedule s"
					+ " inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID = s.awayTeam"
					+ " inner join teamxdivision td on td.teamID = h.teamID inner join venuexgame vg on s.gameID"
					+ " = vg.gameID inner join venue v on vg.venueID = v.venueID where td.divisionID = ? and"
					+ " s.gameStatus = 'Scheduled' and (s.homeTeam=? or s.awayTeam=?)");
			
			getSchedule.setString(1, div);
			getSchedule.setString(2, team);
			getSchedule.setString(3, team);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

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
				sched.add(sb);
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
	
	public static boolean getResultsWithTeam(String team, String div, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getSchedule = conn.prepareStatement("select s.gameDate, h.teamName, s.homeScore, concat(a.teamName, '')" 
					+ " as away, s.awayScore, s.gameStatus, s.gameID from schedule s inner join team h on h.teamID = " 
					+ "s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on " 
					+ "td.teamID = h.teamID where td.divisionID = ? and s.gameStatus = 'Final' and (s.homeTeam=? or s.awayTeam=?)"
					+ " order by s.gameDate desc");
			getSchedule.setString(1, div);
			getSchedule.setString(2, team);
			getSchedule.setString(3, team);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScheduleResultsBean srb = new ScheduleResultsBean();
				srb.setDate(resultSet.getDate(1));
				srb.setHomeTeam(resultSet.getString(2));
				srb.setHomeScore(resultSet.getInt(3));
				srb.setAwayTeam(resultSet.getString(4));
				srb.setAwayScore(resultSet.getInt(5));
				srb.setStatus(resultSet.getString(6));
				sched.add(srb);
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
	
	public static boolean getResults(String id, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getResults = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getResults = conn.prepareStatement("select s.gameDate, h.teamName, s.homeTeam, s.homeScore,"
					+ " concat(a.teamName, '') as away, s.awayTeam, s.awayScore, s.gameStatus, s.gameID"
					+ " from schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID"
					+ " = s.awayTeam inner join teamxdivision td on td.teamID = h.teamID where td.divisionID = ?"
					+ " and s.gameStatus = 'Final'");
			getResults.setString(1, id);
			resultSet = getResults.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScheduleResultsBean srb = new ScheduleResultsBean();
				List<ScorerBean> hlb = new ArrayList<ScorerBean>();
				List<ScorerBean> alb = new ArrayList<ScorerBean>();
				srb.setDate(resultSet.getDate(1));
				srb.setHomeTeam(resultSet.getString(2));
				srb.setHomeID(resultSet.getString(3));
				srb.setHomeScore(resultSet.getInt(4));
				srb.setAwayTeam(resultSet.getString(5));
				srb.setAwayID(resultSet.getString(6));
				srb.setAwayScore(resultSet.getInt(7));
				srb.setStatus(resultSet.getString(8));
				getHomeScorers(resultSet.getString(9), resultSet.getString(2), hlb);
				srb.setHomeScorer(hlb);
				getAwayScorers(resultSet.getString(9), resultSet.getString(5), alb);
				srb.setAwayScorer(alb);
				sched.add(srb);
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

	public static boolean getHomeScorers(String id, String home, List<ScorerBean> hb) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getScorers = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getScorers = conn.prepareStatement("select playerID, playerName, goals from scorers where teamName=? and "
					+ "id=? group by id, playerName");
			getScorers.setString(1, home);
			getScorers.setString(2, id);
			resultSet = getScorers.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScorerBean sb = new ScorerBean();
				sb.setID(resultSet.getString(1));
				sb.setName(resultSet.getString(2));
				sb.setGoals(resultSet.getInt(3));
				hb.add(sb);
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

	public static boolean getAwayScorers(String id, String away, List<ScorerBean> ab) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getScorers = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getScorers = conn.prepareStatement("select playerID, playerName, goals from scorers where teamName=? and"
					+ " id=? group by id, playerName");

			getScorers.setString(1, away);
			getScorers.setString(2, id);
			resultSet = getScorers.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScorerBean sb = new ScorerBean();
				sb.setID(resultSet.getString(1));
				sb.setName(resultSet.getString(2));
				sb.setGoals(resultSet.getInt(3));
				ab.add(sb);
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
