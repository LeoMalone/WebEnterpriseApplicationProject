package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UserBean;
import db.ConnectionManager;

/**
 * The TeamEmails class handles all db operation relating to emails from a Team Owner to its players, other team owners in the division, and Admins
 */
public class TeamEmails {

	/**
	 * The getAllEmails method get all admins and team owners from the db
	 * @param admins - List<UserBean>
	 * @param tos - List<UserBean>
	 * @return boolean status
	 */
	public static boolean getAllEmails(List<UserBean> admins, List<UserBean> tos) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement allEmails = null;
		ResultSet rs = null;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			allEmails = conn.prepareStatement("SELECT userType, emailAddress, userFirstName, userLastName from users");
			rs = allEmails.executeQuery();	        

			while(rs.next()) {
				UserBean user = new UserBean();
				user.setEmail(rs.getString(2));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));

				if(rs.getString(1).equals("Administrator"))
					admins.add(user);
				if(rs.getString(1).equals("Team Owner"))
					tos.add(user);

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
			if (allEmails != null) {
				try {
					allEmails.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}


	/**
	 * The getTeamOwnerEmails method get all teamowner email addresses from the db
	 * @param tos - List<UserBean>
	 * @param division - String
	 * @return boolean status
	 */
	public static boolean getTeamOwnerEmails(List<UserBean> tos, String division) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement teamIds = null;
		ResultSet rsteam = null;
		PreparedStatement userIds = null;
		ResultSet rsuser = null;
		PreparedStatement teamOwnerEmails = null;
		ResultSet rs = null;
		String tOwn = "Team Owner";
		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			teamIds = conn.prepareStatement("SELECT teamID from teamxdivision where divisionID=?");
			teamIds.setString(1, division);
			rsteam = teamIds.executeQuery();
			List<String> divisionTeamIds = new ArrayList<String>();
			while(rsteam.next()) {
				divisionTeamIds.add(rsteam.getString(1));
				status = true;
			}

			if (status) {
				status = false;
				List<String> userownerIds = new ArrayList<String>();
				for (int i = 0; i < divisionTeamIds.size(); i++) {
					userIds = conn.prepareStatement("SELECT userID from usersxteam where teamID=?");
					userIds.setString(1, divisionTeamIds.get(i));
					rsuser = userIds.executeQuery();
					while(rsuser.next()) {
						userownerIds.add(rsuser.getString(1));
						status = true;
					}
				}

				if (status) {
					status = false;

					for (int i = 0; i < userownerIds.size(); i++) {
						teamOwnerEmails = conn.prepareStatement("SELECT emailAddress, userFirstName, userLastName from users where userID=?");
						teamOwnerEmails.setString(1, userownerIds.get(i));
						rs = teamOwnerEmails.executeQuery();	        
						while(rs.next()) {
							UserBean user = new UserBean();
							user.setEmail(rs.getString(1));
							user.setFirstName(rs.getString(2));
							user.setLastName(rs.getString(3));
							user.setUserType(tOwn);
							tos.add(user);
							status = true;
						}
					}	   
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
			if (teamOwnerEmails != null) {
				try {
					teamOwnerEmails.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}


	/**
	 * The getAllEmailsForPost method gets all team owner and administrator email addresses from the db
	 * @param emails - List<String>
	 * @return boolean status
	 */
	public static boolean getAllEmailsForPost(List<String> emails, String division) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement allEmails = null;
		ResultSet rs = null;
		PreparedStatement teamIds = null;
		ResultSet rsteam = null;
		PreparedStatement userIds = null;
		ResultSet rsuser = null;
		PreparedStatement teamOwnerEmails = null;
		ResultSet rsemail = null;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			allEmails = conn.prepareStatement("SELECT emailAddress from users where userType=?");
			allEmails.setString(1, "Administrator");
			rs = allEmails.executeQuery();	        

			while(rs.next()) {
				emails.add(rs.getString(1));
				status = true;
			}
			
			
			
			teamIds = conn.prepareStatement("SELECT teamID from teamxdivision where divisionID=?");
			teamIds.setString(1, division);
			rsteam = teamIds.executeQuery();
			List<String> divisionTeamIds = new ArrayList<String>();
			while(rsteam.next()) {
				divisionTeamIds.add(rsteam.getString(1));
				status = true;
			}

			if (status) {
				status = false;
				List<String> userownerIds = new ArrayList<String>();
				for (int i = 0; i < divisionTeamIds.size(); i++) {
					userIds = conn.prepareStatement("SELECT userID from usersxteam where teamID=?");
					userIds.setString(1, divisionTeamIds.get(i));
					rsuser = userIds.executeQuery();
					while(rsuser.next()) {
						userownerIds.add(rsuser.getString(1));
						status = true;
					}
				}

				if (status) {
					status = false;

					for (int i = 0; i < userownerIds.size(); i++) {
						teamOwnerEmails = conn.prepareStatement("SELECT emailAddress, userFirstName, userLastName from users where userID=?");
						teamOwnerEmails.setString(1, userownerIds.get(i));
						rsemail = teamOwnerEmails.executeQuery();	        
						while(rsemail.next()) {
							emails.add(rsemail.getString(1));
							status = true;
						}
					}	   
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
			if (allEmails != null) {
				try {
					allEmails.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}


	public static boolean getAdminEmails(List<UserBean> admins) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement allEmails = null;
		ResultSet rs = null;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			allEmails = conn.prepareStatement("SELECT emailAddress, userFirstName, userLastName from users where userType=?");
			allEmails.setString(1, "Administrator");
			rs = allEmails.executeQuery();	        

			while(rs.next()) {
				UserBean user = new UserBean();
				user.setEmail(rs.getString(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				admins.add(user);
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
			if (allEmails != null) {
				try {
					allEmails.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
}
