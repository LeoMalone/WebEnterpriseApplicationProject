package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.PlayerBean;
import db.ConnectionManager;

/**
 * The EditTeamPlayer class handles all db operation relating to editing a team's player
 * @author Kevin Read
 */
public class EditTeamPlayer {
	/**
	 * The getPlayerForEdit method gets one player's credentials to display for edit
	 * @param player - PlayerBean from servlet
	 * @param playerId - String
	 * @return status - boolean value
	 */
	public static boolean getPlayerForEdit(PlayerBean player, String playerId) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getUser = null;
		ResultSet rs = null;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getUser = conn.prepareStatement("select p.playerFirstName, p.playerLastName, p.playerNumber, p.playerPosition from player p where p.playerID=?");
			getUser.setString(1, playerId);
			rs = getUser.executeQuery();	              

			if(rs.next()) {
				player.setPlayerFirstName(rs.getString(1));
				player.setPlayerLastName(rs.getString(2));
				player.setPlayerNumber(rs.getString(3));
				player.setPlayerPosition(rs.getString(4));
				status = true;
			}


			// Catch all possible Exceptions
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
			if (getUser != null) {
				try {
					getUser.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The saveChanges method saves the changes for 1 user into the db
	 * @param player - PlayerBean from servlet
	 * @param playerId - String
	 * @return status - boolean value
	 */
	public static boolean saveChanges(PlayerBean player, String playerId) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement updatePlayer = null;
		int result = 0;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();

			updatePlayer = conn.prepareStatement("UPDATE player SET playerFirstName=?, playerLastName=?, playerNumber=?, playerPosition=? WHERE playerID=?");
			updatePlayer.setString(1, player.getPlayerFirstName());
			updatePlayer.setString(2, player.getPlayerLastName());
			updatePlayer.setString(3, player.getPlayerNumber());
			updatePlayer.setString(4, player.getPlayerPosition());
			updatePlayer.setString(5, playerId);

			result = updatePlayer.executeUpdate();	        
			if(result == 1) {
				status = true;
			}

			// Catch all possible Exceptions
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
			if (updatePlayer != null) {
				try {
					updatePlayer.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The deletePlayer method delete a player from the db based on id
	 * @param id - String playerID from servlet
	 * @return status - boolean value
	 */
	public static boolean deletePlayer(String id) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement deletePlayer = null;
		PreparedStatement deletePlayerFromTeam = null;
		int result = 0;   

		try {
			conn = ConnectionManager.getConnection();
			deletePlayerFromTeam = conn.prepareStatement("DELETE FROM playerxteam USING playerxteam, player WHERE `player`.`playerID` = `playerxteam`.`playerID` AND player.playerID=?");
			deletePlayerFromTeam.setString(1, id);
			result = deletePlayerFromTeam.executeUpdate();
			if(result == 1 || result == 0) {
				deletePlayer = conn.prepareStatement("DELETE FROM player USING player WHERE player.playerID=?");
				deletePlayer.setString(1, id);
				result = deletePlayer.executeUpdate();
				if(result == 1) {
					status = true;
				}
			}

			// Catch all possible Exceptions
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
			if (deletePlayer != null) {
				try {
					deletePlayer.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (deletePlayerFromTeam != null) {
				try {
					deletePlayerFromTeam.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
}
