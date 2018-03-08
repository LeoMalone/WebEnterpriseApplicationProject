package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.TeamBean;
/**
 * The Standings class gets the standings for the current division
 */
public class Team {

	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param <StandingsBean>
	 * @param user - UserBean credentials
	 * @return status - boolean value
	 */
	public static boolean getTeamInfo(String id, List<TeamBean> team) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getTeam = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getTeam = conn.prepareStatement("select t.teamLogo, t.teamName, t.teamAbbreviation, d.divisionName from team t"
					+ " inner join teamxdivision td on td.teamID = t.teamID inner join division d on d.divisionID = "
					+ "td.divisionID where t.teamID = ?");
			getTeam.setString(1, id);
			resultSet = getTeam.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				TeamBean tb = new TeamBean();
				tb.setTeamLogo(resultSet.getString(1));
				tb.setTeamName(resultSet.getString(2));
				tb.setTeamAbbreviation(resultSet.getString(3));
				tb.setDivisionName(resultSet.getString(4));
				team.add(tb);
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
			if (getTeam != null) {
				try {
					getTeam.close();
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

	public static String getTeamDivision(String id) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getTeam = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set
		String div = null;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getTeam = conn.prepareStatement("select d.divisionID from division d inner join teamxdivision td on"
					+ " td.divisionID = d.divisionID inner join team t on t.teamID = td.teamID where"
					+ " t.teamID=?");
			getTeam.setString(1, id);
			resultSet = getTeam.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next())
				div = resultSet.getString(1);

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
			if (getTeam != null) {
				try {
					getTeam.close();
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
		return div;
	}
}
