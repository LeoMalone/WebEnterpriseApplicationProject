package dao;

import beans.StandingsBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The Standings class gets the standings for the current division
 * @author Kevin Villemaire
 */
public class Standings {

	/**
	 * The getStandings gets the current standings of a division
	 * @param standings - <StandingsBean>
	 * @param id - The id of the current division
	 * @return status - boolean value
	 */
	public static boolean getStandings(String id, List<StandingsBean> standings) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getStandings = null;	// SQL query
		PreparedStatement getTeamID = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set
		ResultSet rs = null;					// returned query result set
		int rank = 1;							// overall ranking in the standings

		// Connect to Database and execute SELECT query with StandingsBean data
		try {
			conn = ConnectionManager.getConnection();
			getStandings = conn.prepareStatement("select team, GP, W, D, L, PTS, GF, GA, GD, logo from standings"
					+ " where divisionID=? order by PTS desc, W desc, L asc, GD desc");
			getTeamID = conn.prepareStatement("select teamID from team where teamName=?");
			getStandings.setString(1, id);
			resultSet = getStandings.executeQuery();
			status = resultSet.next();

			// return to the start of the result set
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a StandingsBean then add it to the list
			while(resultSet.next()) {
				StandingsBean sb = new StandingsBean();
				
				// gets a teams id to be able to link to their team page
				getTeamID.setString(1, resultSet.getString(1));
				rs = getTeamID.executeQuery();
				
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
				sb.setTeamLogo(resultSet.getString(10));
				
				//Loop through and add the teamID to the StandingsBean
				while(rs.next())
					sb.setTeamID(rs.getString(1));
				standings.add(sb);
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

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}
}
