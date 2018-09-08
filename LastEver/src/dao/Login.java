package dao;

import beans.UserBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The Login class validates a UserBean's credentials
 * using a SELECT statement to compare credentials
 * @author Liam Maloney, Kevin Villemaire
 */
public class Login {

	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param user - UserBean credentials
	 * @return matched - boolean value
	 */
	public static boolean validateUserLogin(UserBean user) { 

		boolean status = false;				// query status
		Connection conn = null;				// DB connection
		PreparedStatement getusers = null;	// SQL query
		PreparedStatement lastLogin = null;
		ResultSet resultSet = null;			// returned query result set
		boolean matched = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getusers = conn.prepareStatement("select username, userType, emailAddress, password, userID, adminActivated,"
					+ " emailValidated from users where emailAddress=?");
			getusers.setString(1, user.getEmailAddress());
			resultSet = getusers.executeQuery();
			status = resultSet.next();

			// if status is true set username and usertype
			if(status) {
				if(BCrypt.checkpw(user.getPassword(), resultSet.getNString(4))) {
					matched = true;
					user.setUsername(resultSet.getString(1));
					user.setUserType(resultSet.getString(2));
					user.setEmail(resultSet.getString(3));
					user.setId(resultSet.getString(5));
					user.setAdminActivated(resultSet.getInt(6));
					user.setEmailValidated(resultSet.getInt(7));
					lastLogin = conn.prepareStatement("UPDATE users SET lastLogin=? WHERE userID=?;");
					lastLogin.setTimestamp(1, user.getLastLogin());
					lastLogin.setString(2, user.getId());
					lastLogin.executeUpdate();
				}
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
			if (getusers != null) {
				try {
					getusers.close();
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
			if (lastLogin != null) {
				try {
					lastLogin.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return matched;
	}
}
