package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.ScheduleResultsBean;
import beans.TeamBean;

/**
 * The TeamScheduleResults class gets the schedule, results, and team name for the current logged in team owner
 */
public class TeamScheduleResults {

/**
 * The getSchedule method gets the schedule for the team based on the user name of the team owner
 * @param sched - List<ScheduleResultsBean>
 * @param userName - String
 * @return divisionID - String
 */
	public static String getSchedule(List<ScheduleResultsBean> sched, String userName) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement teams_division = null;
		ResultSet divisionRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		String teamID = null;
		String userId = null;
		String divisionID = null;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where username = ?");
			userStatement.setNString(1, userName);
			userRs = userStatement.executeQuery();

			if(userRs.next()) {
				userId = (userRs.getString(1));
			}	 

			getTeam = conn.prepareStatement("select teamID from usersxteam where userID = ?");
			getTeam.setString(1, userId);
			rs = getTeam.executeQuery();	              

			if(rs.next()) {
				teamID = rs.getString(1);
			}	

			teams_division = conn.prepareStatement("select divisionID from teamxdivision where teamID = ?");
			teams_division.setNString(1, teamID);
			divisionRs = teams_division.executeQuery();

			if(divisionRs.next()) {
				divisionID = (divisionRs.getString(1));
			}	

			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, concat(a.teamName, '') "
					+ "as away, v.venueName from schedule s "
					+ "inner join team h on h.teamID = s.homeTeam "
					+ "inner join team a on a.teamID = s.awayTeam "
					+ "inner join teamxdivision td on td.teamID = h.teamID "
					+ "inner join venuexgame vg on s.gameID = vg.gameID "
					+ "inner join venue v on vg.venueID = v.venueID "
					+ "where td.divisionID = ? "
					+ "and ( a.teamID = ? or h.teamID = ? ) ");
			getSchedule.setString(1, divisionID);
			getSchedule.setString(2, teamID);
			getSchedule.setString(3, teamID);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScheduleResultsBean sb = new ScheduleResultsBean();
				sb.setDate(resultSet.getDate(1));
				sb.setTime(resultSet.getTime(2));
				sb.setHomeTeam(resultSet.getString(3));
				sb.setAwayTeam(resultSet.getString(4));
				sb.setVenue(resultSet.getString(5));
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
		return divisionID;
	}

	/**
	 * The getResults method gets the results for the team based on the division id
	 * @param id - String division id
	 * @param sched - List<ScheduleResultsBean>
	 * @return status - boolean value
	 */
	public static boolean getResults(String id, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getResults = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getResults = conn.prepareStatement("select s.gameDate, h.teamName, s.homeScore, concat(a.teamName, '')"
					+ " as away, s.awayScore, s.gameStatus from schedule s inner join team h on h.teamID = "
					+ "s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on "
					+ "td.teamID = h.teamID where td.divisionID = ? and s.gameStatus = 'Final'");
			getResults.setString(1, id);
			resultSet = getResults.executeQuery();
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
	 * The getTeamName method gets the team name based on the userName
	 * @param team - TeamBean
	 * @param userName - String
	 * @return teamName - String
	 */
	public static String getTeamName(TeamBean team, String userName) { 

		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement teams_division = null;
		ResultSet divisionRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		String teamID = null;
		String userId = null;
		String teamName = null;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where username = ?");
			userStatement.setNString(1, userName);
			userRs = userStatement.executeQuery();

			if(userRs.next()) {
				userId = (userRs.getString(1));
			}	 

			getTeam = conn.prepareStatement("select teamID from usersxteam where userID = ?");
			getTeam.setString(1, userId);
			rs = getTeam.executeQuery();	              

			if(rs.next()) {
				teamID = rs.getString(1);
			}	

			teams_division = conn.prepareStatement("select teamName from team where teamID = ?");
			teams_division.setNString(1, teamID);
			divisionRs = teams_division.executeQuery();

			if(divisionRs.next()) {
				teamName = (divisionRs.getString(1));
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
		return teamName;
	}
}
