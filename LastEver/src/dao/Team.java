package dao;

import beans.ScheduleResultsBean;
import beans.TeamBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The Team class gets the information for the team
 * @author Kevin Read
 */
public class Team {

	/**
	 * The getSchedule method gets a divisionID from a userName and a list of ScheduleResultsBeans
	 * @param sched - List<ScheduleResultsBean>
	 * @param userName - String user's username
	 * @return divisionID - String value
	 */
	public static String getSchedule(List<ScheduleResultsBean> sched, String userName) { 

		@SuppressWarnings("unused")
		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement teams_division = null;
		ResultSet divisionRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		String teamID = null;
		String userId = null;
		String divisionID = null;

		// Connect to Database and execute SELECT query with UserBean data
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
				teamID = rs.getString(1);
			}	

			teams_division = conn.prepareStatement("select divisionID from teamxdivision where teamID = ?");
			teams_division.setNString(1, teamID);
			divisionRs = teams_division.executeQuery();

			if(divisionRs.next()) {
				divisionID = (divisionRs.getString(1));
			}	

			getSchedule = conn.prepareStatement("select s.gameDate, s.gameTime, h.teamName, concat(a.teamName, '') "
					+ "as away, v.venueName from schedule s "
					+ "inner join team h on h.teamID = s.homeTeam "
					+ "inner join team a on a.teamID = s.awayTeam "
					+ "inner join teamxdivision td on td.teamID = h.teamID "
					+ "inner join venuexgame vg on s.gameID = vg.gameID "
					+ "inner join venue v on vg.venueID = v.venueID "
					+ "where td.divisionID = ? "
					+ "and ( a.teamID = ? or h.teamID = ? ) ");
			getSchedule.setString(1, divisionID);
			getSchedule.setString(2, teamID);
			getSchedule.setString(3, teamID);
			resultSet = getSchedule.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScheduleResultsBean sb = new ScheduleResultsBean();
				sb.setDate(resultSet.getDate(1));
				sb.setTime(resultSet.getTime(2));
				sb.setHomeTeam(resultSet.getString(3));
				sb.setAwayTeam(resultSet.getString(4));
				sb.setVenue(resultSet.getString(5));
				sched.add(sb);
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (teams_division != null) {
				try {
					teams_division.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (userStatement != null) {
				try {
					userStatement.close();
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
		return divisionID;
	}

	/**
	 * The getResults method fills a ScheduleResultsBean and returns true on success
	 * @param id - String user's username
	 * @param sched - List<ScheduleResultsBean>
	 * @return status - Boolean success value
	 */
	public static boolean getResults(String id, List<ScheduleResultsBean> sched) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getResults = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();
			getResults = conn.prepareStatement("select s.gameDate, h.teamName, s.homeScore, concat(a.teamName, '')"
					+ " as away, s.awayScore, s.gameStatus from schedule s inner join team h on h.teamID = "
					+ "s.homeTeam inner join team a on a.teamID = s.awayTeam inner join teamxdivision td on "
					+ "td.teamID = h.teamID where td.divisionID = ? and s.gameStatus = 'Final'");
			getResults.setString(1, id);
			resultSet = getResults.executeQuery();
			status = resultSet.next();

			resultSet.beforeFirst();

			while(resultSet.next()) {
				ScheduleResultsBean srb = new ScheduleResultsBean();
				srb.setDate(resultSet.getDate(1));
				srb.setHomeTeam(resultSet.getString(2));
				srb.setHomeScore(resultSet.getInt(3));
				srb.setAwayTeam(resultSet.getString(4));
				srb.setAwayScore(resultSet.getInt(5));
				srb.setStatus(resultSet.getString(6));
				sched.add(srb);
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
			if (getResults != null) {
				try {
					getResults.close();
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
	 * The getTeamName method returns a team name based on a user name
	 * @param team - TeamBean
	 * @param userName - String
	 * @return teamName - String
	 */
	public static String getTeamName(TeamBean team, String userName) { 

		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement teams_division = null;
		ResultSet divisionRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		String teamID = null;
		String userId = null;
		String teamName = null;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where username = ?");
			userStatement.setNString(1, userName);
			userRs = userStatement.executeQuery();

			if(userRs.next()) {
				userId = (userRs.getString(1));
			}

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
				teamID = rs.getString(1);
			}	

			teams_division = conn.prepareStatement("select teamName from team where teamID = ?");
			teams_division.setNString(1, teamID);
			divisionRs = teams_division.executeQuery();

			if(divisionRs.next()) {
				teamName = (divisionRs.getString(1));
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
		return teamName;
	}


	/**
	 * The getTeamNameByEmail method returns a team name based on an email address
	 * @param emailAddress - String
	 * @return teamName - String
	 */
	public static String getTeamNameByEmail(String emailAddress) { 

		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement teams_division = null;
		ResultSet divisionRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		String teamID = null;
		String userId = null;
		String teamName = null;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where emailAddress = ?");
			userStatement.setNString(1, emailAddress);
			userRs = userStatement.executeQuery();

			if(userRs.next()) {
				userId = (userRs.getString(1));
			}

			getTeam = conn.prepareStatement("select teamID from usersxteam where userID = ?");
			getTeam.setString(1, userId);
			rs = getTeam.executeQuery();	              

			if(rs.next()) {
				teamID = rs.getString(1);
			}	

			teams_division = conn.prepareStatement("select teamName from team where teamID = ?");
			teams_division.setNString(1, teamID);
			divisionRs = teams_division.executeQuery();

			if(divisionRs.next()) {
				teamName = (divisionRs.getString(1));
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
		return teamName;
	}

	/**
	 * The hasTeamByEmail method returns a true if the user's loginEmail is associated with a team
	 * @param loginEmail - String
	 * @return status - Boolean value
	 */
	public static boolean hasTeamByEmail(String loginEmail) { 

		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		String userId = null;
		boolean status = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where emailAddress = ?");
			userStatement.setNString(1, loginEmail);
			userRs = userStatement.executeQuery();

			if(userRs.next()) {
				userId = (userRs.getString(1));
			} 

			getTeam = conn.prepareStatement("select teamID from usersxteam where userID = ?");
			getTeam.setString(1, userId);
			rs = getTeam.executeQuery();	              

			if(rs.next()) {
				status = true;
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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
	 * The getAllTeams method returns a true if the user's loginEmail is associated with a team
	 * @param tb - List<TeamBean>
	 * @return status - Boolean success value
	 */
	public static boolean getAllTeams(List<TeamBean> tb) {
		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		boolean status = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select teamName from team");
			resultSet = userStatement.executeQuery();

			if (resultSet.next()) {
				resultSet.beforeFirst();
				status = true;
				while(resultSet.next()) {
					TeamBean ntb = new TeamBean();
					ntb.setTeamName(resultSet.getString(1));
					tb.add(ntb);
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
			if (userStatement != null) {
				try {
					userStatement.close();
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
	 * The getUserName method returns a true if it sets the username
	 * @param userName - String
	 * @param loginEmail - String
	 * @return status - Boolean success value
	 */
	public static boolean getUserName(String userName, String loginEmail) {
		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		boolean status = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select username from users where emailAddress = ?");
			userStatement.setNString(1, loginEmail);
			resultSet = userStatement.executeQuery();

			if (resultSet.next()) {
				resultSet.beforeFirst();
				userName = resultSet.getString(1);
				status = true;
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
			if (userStatement != null) {
				try {
					userStatement.close();
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
	 * The insertTeam method inserts a team into the DB
	 * @param tb - TeamBean
	 * @param teamName - String
	 * @param emailAddress - String
	 * @return status - Boolean value
	 */
	public static boolean insertTeam(TeamBean tb, String teamName, String emailAddress) {
		Connection conn = null;					// DB connection
		PreparedStatement teamStatement = null;	// SQL query
		ResultSet teamResultSet = null;				// returned query result set
		PreparedStatement userStatement = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		PreparedStatement insertionStatement = null;	// SQL query
		ResultSet insertionResultSet = null;				// returned query result set
		String userId = null;
		boolean status = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where emailAddress = ?");
			userStatement.setNString(1, emailAddress);
			resultSet = userStatement.executeQuery();

			if (resultSet.next()) {
				resultSet.beforeFirst();
				userId = resultSet.getString(1);

				teamStatement = conn.prepareStatement("select teamID from team where teamName = ?");
				teamStatement.setNString(1, teamName);
				teamResultSet = teamStatement.executeQuery();

				if (teamResultSet.next()) {
					teamResultSet.beforeFirst();
					String teamId = teamResultSet.getString(1);

					insertionStatement = conn.prepareStatement("insert into usersxteam values {?, ?}");
					insertionStatement.setNString(1, teamId);
					insertionStatement.setNString(2, userId);
					insertionResultSet = insertionStatement.executeQuery();

					if (insertionResultSet.next()) {
						status = true;
					}
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
			if (userStatement != null) {
				try {
					userStatement.close();
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
			if (teamStatement != null) {
				try {
					userStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (teamResultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertionStatement != null) {
				try {
					userStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertionResultSet != null) {
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
	 * The insertTeamByUser method inserts a team based on the username
	 * @param tb - TeamBean
	 * @param teamName - String
	 * @param userName - String
	 * @return status - boolean value
	 */
	public static boolean insertTeamByUser(TeamBean tb, String teamName, String userName) {
		Connection conn = null;					// DB connection
		PreparedStatement teamStatement = null;	// SQL query
		ResultSet teamResultSet = null;				// returned query result set
		PreparedStatement userStatement = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		PreparedStatement insertionStatement = null;	// SQL query
		int insertionResultSet = 0;				// returned query result set
		String userId = null;
		boolean status = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where username = ?");
			userStatement.setNString(1, userName);
			resultSet = userStatement.executeQuery();

			if (resultSet.next()) {
				userId = resultSet.getString(1);

				teamStatement = conn.prepareStatement("select teamID from team where teamName = ?");
				teamStatement.setNString(1, teamName);
				teamResultSet = teamStatement.executeQuery();

				if (teamResultSet.next()) {
					String teamId = teamResultSet.getString(1);

					insertionStatement = conn.prepareStatement("insert into usersxteam (userID, teamID) values (?, ?)");
					insertionStatement.setNString(1, userId);
					insertionStatement.setNString(2, teamId);
					insertionResultSet = insertionStatement.executeUpdate();

					if (insertionResultSet > 0) {
						status = true;
					}
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
			if (userStatement != null) {
				try {
					userStatement.close();
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
			if (teamStatement != null) {
				try {
					userStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (teamResultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertionStatement != null) {
				try {
					userStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertionResultSet == 0) {
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
	 * The insertNewTeam method inserts a new team into the DB
	 * @param tb - TeamBean
	 * @param userName - String
	 * @param aboutTeam - String
	 * @return status - boolean value
	 */
	public static boolean insertNewTeam(TeamBean tb, String userName, String aboutTeam) {
		Connection conn = null;					// DB connection
		PreparedStatement teamStatement = null;	// SQL query
		ResultSet teamResultSet = null;				// returned query result set
		PreparedStatement teamInsertionStatement = null;	// SQL query
		int teamInsertResultSet = 0;				// returned query result set
		PreparedStatement divInsertionStatement = null;	// SQL query
		int divInsertResultSet = 0;				// returned query result set
		PreparedStatement userStatement = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		PreparedStatement insertionStatement = null;	// SQL query
		int insertionResultSet = 0;				// returned query result set
		String userId = null;
		boolean status = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where username = ?");
			userStatement.setNString(1, userName);
			resultSet = userStatement.executeQuery();

			if (resultSet.next()) {
				userId = resultSet.getString(1);

				String teamName = tb.getTeamName();
				String teamAbv = tb.getTeamAbbreviation();
				String divRadio = tb.getDivisionId();

				teamInsertionStatement = conn.prepareStatement("insert into team (teamName, teamAbbreviation, teamAbout) values (?, ?, ?)");
				teamInsertionStatement.setNString(1, teamName);
				teamInsertionStatement.setNString(2, teamAbv);
				teamInsertionStatement.setNString(3, aboutTeam);
				teamInsertResultSet = teamInsertionStatement.executeUpdate();

				if (teamInsertResultSet > 0) {
					teamStatement = conn.prepareStatement("select teamID from team where teamName = ?");
					teamStatement.setNString(1, teamName);
					teamResultSet = teamStatement.executeQuery();

					if (teamResultSet.next()) {
						String teamId = teamResultSet.getString(1);


						divInsertionStatement = conn.prepareStatement("insert into teamxdivision (teamID, divisionID) values (?, ?)");
						divInsertionStatement.setNString(1, teamId);
						divInsertionStatement.setNString(2, divRadio);
						divInsertResultSet = divInsertionStatement.executeUpdate();

						if (divInsertResultSet > 0) {
							insertionStatement = conn.prepareStatement("insert into usersxteam (userID, teamID) values (?, ?)");
							insertionStatement.setNString(1, userId);
							insertionStatement.setNString(2, teamId);
							insertionResultSet = insertionStatement.executeUpdate();

							if (insertionResultSet > 0) {
								status = true;
							}
						}
					}
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
			if (userStatement != null) {
				try {
					userStatement.close();
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
			if (teamStatement != null) {
				try {
					userStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (teamResultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertionStatement != null) {
				try {
					userStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertionResultSet == 0) {
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
	 * The hasTeam method returns true if the userName is associated with a team
	 * @param userName - String
	 * @return status - boolean value
	 */
	public static boolean hasTeam(String userName) {

		Connection conn = null;					// DB connection
		PreparedStatement userStatement = null;
		ResultSet userRs = null;
		PreparedStatement getTeam = null;
		ResultSet rs = null;
		PreparedStatement getSchedule = null;	// SQL query
		ResultSet resultSet = null;				// returned query result set
		String userId = null;
		boolean status = false;

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			userStatement = conn.prepareStatement("select userID from users where userName = ?");
			userStatement.setNString(1, userName);
			userRs = userStatement.executeQuery();

			if(userRs.next()) {
				userId = (userRs.getString(1));
			} 

			getTeam = conn.prepareStatement("select teamID from usersxteam where userID = ?");
			getTeam.setString(1, userId);
			rs = getTeam.executeQuery();	              

			if(rs.next()) {
				status = true;
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
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

