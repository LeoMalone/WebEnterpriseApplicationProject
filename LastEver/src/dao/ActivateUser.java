package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import beans.UserBean;
import db.ConnectionManager;

/**
 * The ActivateUser tries to activate a user based on userID and token
 * sent to a users email
 * @author Kevin Villemaire
 */
public class ActivateUser {

	/**
	 * The activate method tries to activate a users email address
	 * @param user - UserBean object to check 
	 * @return status 
	 */
	public static boolean activate(UserBean user, String token) { 

		boolean status = false;					// Status of activate
		Connection conn = null;					// DB Connection
		PreparedStatement getUser = null;		// # of executed queries
		PreparedStatement updateUser = null;	// # of executed queries
		DateTime currTime = new DateTime(new Timestamp(System.currentTimeMillis()));	//Current time
		DateTime activationTime;				// The end of the users Activation period
		ResultSet result;						// resultSet of query
		@SuppressWarnings("unused")
		int stat = 0;

		// Connect to Database and execute SELECT query with UserId and token
		try {
			conn = ConnectionManager.getConnection();        
			getUser = conn.prepareStatement("select activationTo from activation where userID=? and activationCode=?");
			getUser.setString(1, user.getId());
			getUser.setString(2, token);
			result = getUser.executeQuery();

			// Return true if 1 query executes
			if(result.next()) {
				// Update the activation end time
				activationTime = new DateTime(result.getTimestamp(1));

				// Compares the time in-between now and the end of the users activation period
				Seconds sec = Seconds.secondsBetween(activationTime, currTime);
				Seconds interval = Seconds.seconds(1);

				// If within activation period then activate the user by updating emailValidated
				if(interval.isGreaterThan(sec)) {
					updateUser = conn.prepareStatement("update users set emailValidated=?, accountUpdated=? where userID=?");
					updateUser.setInt(1, 1);
					updateUser.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
					updateUser.setString(3, user.getId());
					stat = updateUser.executeUpdate();

					//Call the deleteToken method to delete the activation token of the user
					if(EmailActivation.deleteToken(user))
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
			if (getUser != null) {
				try {
					getUser.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (updateUser != null) {
				try {
					updateUser.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return status;
	}
}
