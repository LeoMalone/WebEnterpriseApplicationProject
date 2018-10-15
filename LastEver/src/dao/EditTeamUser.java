package dao;

import beans.PlayerBean;
import beans.TeamBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The EditTeamUser class handles all db operation relating to editing a team owner
 * @author Kevin Read
 */
public class EditTeamUser {

	/**
	 * The getTeamForEdit method gets the teamId to be edited
	 * @param userName - String
	 * @return returnTeamId - String
	 */
	public static String getTeamForEdit(String userName) {

		String returnTeamId = null;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		String userId = null;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			userStatement = conn.prepareStatement("select userID from users where username = ?");
			userStatement.setNString(1, userName);
			userRs = userStatement.executeQuery();

			if(userRs.next()) {
				userId = (userRs.getString(1));
			}	 

			getTeam = conn.prepareStatement("select teamID from usersxteam where userID = ?");
			getTeam.setString(1, userId);
			rs = getTeam.executeQuery();	              

			if(rs.next()) {
				returnTeamId = rs.getString(1);
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
			if (getTeam != null) {
				try {
					getTeam.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return returnTeamId;
	}

	/**
	 * The getPlayersFromRoster method gets the players from the roster
	 * @param player - List<PlayerBean>
	 * @param teamId - String
	 * @return status - boolean value
	 */
	public static boolean getPlayersFromRoster(List<PlayerBean> player, String teamId) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement viewTeamRoster = null;
		ResultSet rs = null;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			viewTeamRoster = conn.prepareStatement("SELECT p.playerFirstName, p.playerLastName, p.playerNumber, p.playerPosition, p.playerID from player p "
					+ "inner join playerxteam pxt "
					+ "on pxt.playerID = p.playerID "
					+ "where pxt.teamID = ?");
			viewTeamRoster.setString(1,teamId);
			rs = viewTeamRoster.executeQuery();


			while(rs.next()) {
				PlayerBean ub = new PlayerBean();
				ub.setPlayerFirstName(rs.getString(1));
				ub.setPlayerLastName(rs.getString(2));
				ub.setPlayerNumber(rs.getString(3));
				ub.setPlayerPosition(rs.getString(4));
				ub.setId(rs.getString(5));
				player.add(ub);

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
			if (viewTeamRoster != null) {
				try {
					viewTeamRoster.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The saveChanges method saves the editing team user changes
	 * @param team - TeamBean
	 * @return status - boolean value
	 */
	public static boolean saveChanges(TeamBean team) {

		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement updateTeam = null;
		int result = 0;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			updateTeam = conn.prepareStatement("UPDATE team SET teamName=?, teamAbbreviation=? WHERE teamID=?");
			updateTeam.setString(1, team.getTeamName());
			updateTeam.setString(2, team.getTeamAbbreviation());
			updateTeam.setString(3, team.getTeamId());


			result = updateTeam.executeUpdate();	        
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
			if (updateTeam != null) {
				try {
					updateTeam.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The deleteTeam method recursively deletes the team
	 * @param id - String of the team id
	 * @return status - boolean value 
	 */
	public static boolean deleteTeam(String id) {
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement deleteSchedule = null;
		PreparedStatement deleteDivision = null;
		PreparedStatement deleteUser = null;
		PreparedStatement deleteTeam = null;
		int result = 0;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			deleteSchedule = conn.prepareStatement("DELETE FROM schedule USING schedule, team WHERE `team`.`teamID` = `schedule`.`homeTeam` AND `team`.`teamID` = `schedule`.`awayTeam` AND `team`.`teamID`=?");
			deleteSchedule.setString(1, id);
			result = deleteSchedule.executeUpdate();
			if(result >= 0) {
				deleteDivision = conn.prepareStatement("DELETE FROM teamxdivision USING teamxdivision, team WHERE team.teamID = teamxdivision.teamID AND team.teamID=?");
				deleteDivision.setString(1, id);
				result = deleteDivision.executeUpdate();	        
				if(result == 0 || result == 1) {
					deleteUser = conn.prepareStatement("DELETE FROM usersxteam USING usersxteam, team WHERE team.teamID = usersxteam.teamID AND team.teamID=?");
					deleteUser.setString(1, id);
					result = deleteUser.executeUpdate();
					if(result >= 0) {
						deleteTeam = conn.prepareStatement("DELETE FROM team USING team WHERE team.teamID=?");
						deleteTeam.setString(1, id);
						result = deleteTeam.executeUpdate();
						if(result == 1) {
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
			if (deleteDivision != null) {
				try {
					deleteDivision.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (deleteUser != null) {
				try {
					deleteUser.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (deleteTeam != null) {
				try {
					deleteTeam.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (deleteSchedule != null) {
				try {
					deleteSchedule.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
}
