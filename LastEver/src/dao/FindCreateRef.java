package dao;

import beans.RefBean;
import db.ConnectionManager;

import java.sql.*;

/**
 * The FindCreateRef class handles all db operation relating to finding or creating an actual referee
 * and not a referee user
 * @author Kevin Villemaire
 */
public class FindCreateRef {

	/**
	 * The findReferee method finds a referee based on a users First and Last Name
	 * @param ref - RefBean
	 * @return status - boolean value
	 */
	public static boolean findReferee(RefBean ref) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getUser = null;		// SQL query
		ResultSet rs = null;					//returned query result set
		PreparedStatement updateRef = null;		// SQL query to update user
		int result = 0;							// number of rows affected in update
		Timestamp currTime = new Timestamp(System.currentTimeMillis());

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getUser = conn.prepareStatement("select refereeFirstName, refereeLastName, refereeID from referee"
					+ " where refereeFirstName=? and refereeLastName=?");
			getUser.setString(1, ref.getFirstName());
			getUser.setString(2, ref.getLastName());
			rs = getUser.executeQuery();	              
			status = rs.next();

			//if there is an existing ref user then update the user with the referee id
			if(status) {
				updateRef = conn.prepareStatement("update users set refereeID=?, accountUpdated=? where userID=?");
				updateRef.setInt(1, rs.getInt(3));
				updateRef.setTimestamp(2, currTime);
				updateRef.setInt(3, Integer.parseInt(ref.getId()));
				result = updateRef.executeUpdate();

				//if one row is affected then the query was successful
				if(result == 1)
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

			if (updateRef != null) {
				try {
					updateRef.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The createReferee method creates a referee based on the users First and Last Name
	 * @param ref - RefBean
	 * @return status - boolean value
	 */
	public static boolean createReferee(RefBean ref) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement createRef = null;		// SQL query
		ResultSet rs = null;					//returned query result set
		PreparedStatement getRefID = null;		// SQL query to get refereeID
		PreparedStatement updateRef = null;		// SQL query to update user
		int result = 0;							// number of rows affected in update
		Timestamp currTime = new Timestamp(System.currentTimeMillis());

		// Connect to Database
		try {
			conn = ConnectionManager.getConnection();
			createRef = conn.prepareStatement("insert into referee (refereeFirstName, refereeLastName) values (?, ?)");
			createRef.setString(1, ref.getFirstName());
			createRef.setString(2, ref.getLastName());
			result = createRef.executeUpdate();

			//if insert was successful then get the refereeID
			if(result == 1) {
				getRefID = conn.prepareStatement("select refereeID from referee where refereeFirstName=? and refereeLastName=?");
				getRefID.setString(1, ref.getFirstName());
				getRefID.setString(2, ref.getLastName());
				rs = getRefID.executeQuery();
				status = rs.next();

				//also if successful then update the user with the newly created refereeID
				if(status) {
					updateRef = conn.prepareStatement("update users set refereeID=?, accountUpdated=? where userID=?");
					updateRef.setInt(1, rs.getInt(1));
					updateRef.setTimestamp(2, currTime);
					updateRef.setInt(3, Integer.parseInt(ref.getId()));
					result = updateRef.executeUpdate();

					//if one row is affected then the query was successful
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
			if (createRef != null) {
				try {
					createRef.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getRefID != null) {
				try {
					getRefID.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (updateRef != null) {
				try {
					updateRef.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}


	/**
	 * The getReferee method gets all referee user information based on user id
	 * @param user - RefBean
	 * @param id - Referee userID
	 * @return status - boolean value
	 */
	public static boolean getReferee(RefBean user, String id) {
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getUser = null;		// SQL Query
		ResultSet rs = null;					// Returned query resultSet

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getUser = conn.prepareStatement("select userID, userFirstName, userLastName from users"
					+ " where userType='referee' and userID=?");
			getUser.setString(1, id);
			rs = getUser.executeQuery();	              
			status = rs.next();

			//set ref data to the bean
			user.setId(rs.getString(1));
			user.setFirstName(rs.getString(2));
			user.setLastName(rs.getString(3));


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
}
