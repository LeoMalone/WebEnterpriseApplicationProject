package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.TeamBean;
import beans.UserBean;
/**
 * The TeamPage class gets the standings for the current division
 */
public class TeamPage {

	/**
	 * The getTeamInfo gets all information about the team
	 * @param <TeamBean>
	 * @param id - The current teams id
	 * @return status - boolean value
	 */
	public static boolean getTeamInfo(String id, List<TeamBean> team) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getTeam = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with TeamBean data
		try {
			conn = ConnectionManager.getConnection();
			getTeam = conn.prepareStatement("select t.teamID, t.teamLogo, t.teamName, t.teamAbbreviation,"
					+ " d.divisionName, t.teamAbout from team t inner join teamxdivision td on td.teamID = t.teamID"
					+ " inner join division d on d.divisionID = td.divisionID where t.teamID = ?");
			getTeam.setString(1, id);
			resultSet = getTeam.executeQuery();
			status = resultSet.next();

			//return to the start of the result set
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a TeamBean then add it to the list
			while(resultSet.next()) {
				TeamBean tb = new TeamBean();
				tb.setTeamId(resultSet.getString(1));
				tb.setTeamLogo(resultSet.getString(2));
				tb.setTeamName(resultSet.getString(3));
				tb.setTeamAbbreviation(resultSet.getString(4));
				tb.setDivisionName(resultSet.getString(5));
				tb.setTeamAbout(resultSet.getString(6));
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

	/**
	 * The getTeamDivision gets the division associated with the team
	 * @param id - The current id of the team
	 * @return div - String value
	 */
	public static String getTeamDivision(String id) { 

		Connection conn = null;					// DB connection
		PreparedStatement getTeam = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set
		String div = null;						// name of the teams division

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getTeam = conn.prepareStatement("select d.divisionID from division d inner join teamxdivision td on"
					+ " td.divisionID = d.divisionID inner join team t on t.teamID = td.teamID where"
					+ " t.teamID=?");
			getTeam.setString(1, id);
			resultSet = getTeam.executeQuery();

			//Loop through and set div to be the teams division
			while(resultSet.next())
				div = resultSet.getString(1);

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

		/**
	 * The getTeamOwner gets the owner of the team
	 * @param user - <UserBean>
	 * @param id - The current id of the team
	 * @return status - boolean value
	 */
	public static boolean getTeamOwner(String id, List<UserBean> user) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getTeam = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getTeam = conn.prepareStatement("select u.userFirstName, u.userLastName, u.emailAddress from"
					+ " usersxteam ut inner join users u on u.userID = ut.userID where ut.teamID = ?");
			getTeam.setString(1, id);
			resultSet = getTeam.executeQuery();
			status = resultSet.next();

			//return to the start of the result set
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a UserBean then add it to the list
			while(resultSet.next()) {
				UserBean ub = new UserBean();
				ub.setFirstName(resultSet.getString(1));
				ub.setLastName(resultSet.getString(2));
				ub.setEmail(resultSet.getString(3));
				user.add(ub);
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

}
