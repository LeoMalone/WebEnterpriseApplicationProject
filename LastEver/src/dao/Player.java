package dao;

import beans.PlayerBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The Player class gets info related to a player
 * @author Kevin Villemaire
 */
public class Player {

	/**
	 * The getPlayerInfo gets the current players information
	 * @param player - <PlayerBean>
	 * @param pID - The id of the current player
	 * @return status - boolean value
	 */
	public static boolean getPlayerInfo(String pID, List<PlayerBean> player) { 

		boolean status = false;				// query status
		Connection conn = null;				// DB connection
		PreparedStatement getPlayer = null;	// SQL query
		ResultSet resultSet = null;			// returned query result set

		// Connect to Database and execute SELECT query with PlayerBean data
		try {
			conn = ConnectionManager.getConnection();
			getPlayer = conn.prepareStatement("select p.playerFirstName, p.playerLastName, p.playerNumber, p.playerPosition,"
					+ " p.playerCountry, p.playerHeight, p.playerWeight, p.playerPhoto, d.divisionName, t.teamName,"
					+ " p.playerHidePage, l.leagueName from player p inner join playerxteam pt on pt.playerID = p.playerID"
					+ " inner join team t on t.teamID = pt.teamID inner join teamxdivision td on td.teamID = t.teamID inner join"
					+ " division d on d.divisionID = td.divisionID inner join leaguexdivision ld on ld.divisionID = d.divisionID"
					+ " inner join league l on l.leagueID = ld.leagueID where p.playerID=?");
			getPlayer.setString(1, pID);
			resultSet = getPlayer.executeQuery();
			status = resultSet.next();

			//return to the start of the result set
			resultSet.beforeFirst();

			//Loop through and add the results of the query to a PlayerBean then add it to the list
			while(resultSet.next()) {
				PlayerBean pb = new PlayerBean();
				pb.setPlayerFirstName(resultSet.getString(1));
				pb.setPlayerLastName(resultSet.getString(2));
				pb.setPlayerNumber(resultSet.getString(3));
				pb.setPlayerPosition(resultSet.getString(4));
				pb.setPlayerCountry(resultSet.getString(5));
				pb.setPlayerHeight(resultSet.getFloat(6));
				pb.setPlayerWeight(resultSet.getFloat(7));
				pb.setPlayerPhoto(resultSet.getString(8));
				pb.setDivisionName(resultSet.getString(9));
				pb.setTeamName(resultSet.getString(10));
				pb.setHidePage(resultSet.getBoolean(11));
				pb.setLeagueName(resultSet.getString(12));
				player.add(pb);
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
			if (getPlayer != null) {
				try {
					getPlayer.close();
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
