package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.PlayerBean;
import beans.UserBean;
import db.ConnectionManager;

/**
 * The CreateAccount class creates new users in the db from
 * information from a UserBean and the create account servlet
 */
public class CreateAccount {

	/**
	 * The createNewUser method performs the INSERT query to the DB
	 * @param user - UserBean object to get create account credentials
	 * @return status 
	 */
	public static boolean createNewUser(UserBean user) { 

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement insertUser = null;	// # of executed queries
		int result = 0;	    

		// Connect to Database and execute INSERT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();        
			insertUser = conn.prepareStatement("INSERT INTO users (username, userFirstName, userLastName, password, emailAddress, userType, lastLogin, emailValidated) VALUES (?, ?, ?, ?, ?, ?, ?, 1);");
			insertUser.setString(1, user.getUsername());
			insertUser.setString(2, user.getFirstName());
			insertUser.setString(3, user.getLastName());
			insertUser.setString(4, user.getPassword());
			insertUser.setString(5, user.getEmailAddress());
			insertUser.setString(6, user.getUserType());
			insertUser.setTimestamp(7, user.getLastLogin());
			result = insertUser.executeUpdate();

			// Return true if 1 query executes
			if(result == 1)
				status = true;

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
			if (insertUser != null) {
				try {
					insertUser.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return status;
	}

	/**
	 * The createNewPlayer method performs the INSERT query to the DB
	 * @param player - PlayerBean object to get create a player for the team
	 * @return status 
	 */
	public static boolean createNewPlayer(PlayerBean player, String teamId) { 

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement insertPlayer = null;	// # of executed queries
		PreparedStatement insertPlayerOnTeam = null;	// # of executed queries
		PreparedStatement id = null;	// # of executed queries
		int result = 0;	    

		// Connect to Database and execute INSERT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();        
			insertPlayer = conn.prepareStatement("INSERT INTO player (playerFirstName, playerLastName, playerNumber, playerPosition) VALUES (?, ?, ?, ?);");
			insertPlayer.setString(1, player.getPlayerFirstName());
			insertPlayer.setString(2, player.getPlayerLastName());
			insertPlayer.setString(3, player.getPlayerNumber());
			insertPlayer.setString(4, player.getPlayerPosition());

			result = insertPlayer.executeUpdate();

			// Return true if 1 query executes
			if(result == 1) {
				result = 0;
				id = conn.prepareStatement("SELECT p.playerID FROM player p WHERE p.playerFirstName = ?");
				id.setString(1, player.getPlayerFirstName());
				ResultSet rs = id.executeQuery();
				if (rs.next()) {
					insertPlayerOnTeam = conn.prepareStatement("INSERT INTO playerxteam (playerID, teamID) VALUES (?, ?);");
					insertPlayerOnTeam.setString(1, rs.getString(1));
					insertPlayerOnTeam.setString(2, teamId);

					result = insertPlayerOnTeam.executeUpdate();

					if(result == 1)
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
			if (insertPlayerOnTeam != null) {
				try {
					insertPlayerOnTeam.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertPlayer != null) {
				try {
					insertPlayer.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}

		return status;
	}
}
