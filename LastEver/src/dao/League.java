package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.DivisionBean;
import beans.LeagueBean;
import beans.NewsBean;
import db.ConnectionManager;

/**
 * The League class gets all the info about the league
 * @author Kevin Villemaire
 */
public class League {

	/**
	 * The getCurrentLeague method gets the currentLeague
	 * @param lID - String
	 * @param leagueList - List<LeagueBean>
	 * @return status - boolean value
	 */
	public static boolean getCurrentLeague(String lID, List<LeagueBean> leagueList) {

		boolean status = false;							// query status
		Connection conn = null;							// DB Connection
		PreparedStatement league = null;			// SQL query
		ResultSet rs = null;							// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			league = conn.prepareStatement("SELECT leagueID, leagueName, leagueStatus from league where leagueID = ?");
			league.setString(1, lID);
			rs = league.executeQuery();
			status = rs.next();

			//return to the start of the result set
			rs.beforeFirst();

			//Loop through and add the results of the query to a LeagueBean then add it to the list
			while(rs.next()) {
				LeagueBean lb = new LeagueBean();
				lb.setLeagueId(rs.getString(1));
				lb.setLeagueName(rs.getString(2));
				lb.setStatus(rs.getString(3));
				leagueList.add(lb);
			}

			// close all connections and catch all possible Exceptions
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
			if (league != null) {
				try {
					league.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The getAllLeagues method gets all leagues into the List<LeagueBeans>
	 * @param leagueList - List<LeagueBean>
	 * @return status - boolean value
	 */
	public static boolean getAllLeagues(List<LeagueBean> leagueList) {

		boolean status = false;							// query status
		Connection conn = null;							// DB Connection
		PreparedStatement league = null;			// SQL query
		ResultSet rs = null;							// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			league = conn.prepareStatement("SELECT leagueID, leagueName from league");
			rs = league.executeQuery();
			status = rs.next();

			//return to the start of the result set
			rs.beforeFirst();

			//Loop through and add the results of the query to a LeagueBean then add it to the list
			while(rs.next()) {
				LeagueBean lb = new LeagueBean();
				lb.setLeagueId(rs.getString(1));
				lb.setLeagueName(rs.getString(2));
				leagueList.add(lb);
			}

			// close all connections and catch all possible Exceptions
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
			if (league != null) {
				try {
					league.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The getLeagueDivisions method gets the division and league names
	 * @param lID - String
	 * @param divisionList - List<DivisionBean>
	 * @return status - boolean value
	 */
	public static boolean getLeagueDivisions(String lID, List<DivisionBean> divisionList) {

		boolean status = false;							// query status
		Connection conn = null;							// DB Connection
		PreparedStatement division = null;				// SQL query
		ResultSet rs = null;							// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			division = conn.prepareStatement("SELECT d.divisionID, d.leagueName from division d inner join"
					+ " leaguexdivision ld on ld.divisionID = d.divisionID where ld.leagueID = ? limit 1");
			division.setString(1, lID);
			rs = division.executeQuery();
			status = rs.next();

			//return to the start of the result set
			rs.beforeFirst();

			//Loop through and add the results of the query to a LeagueBean then add it to the list
			while(rs.next()) {
				DivisionBean lb = new DivisionBean();
				lb.setDivisionId(rs.getString(1));
				lb.setDivisionName(rs.getString(2));
				divisionList.add(lb);
			}

			// close all connections and catch all possible Exceptions
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
			if (division != null) {
				try {
					division.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The getDivisionsByLeague method gets the divisions based on leagueId
	 * @param divs - List<DivisionBean
	 * @param leagueId - String
	 * @return status - boolean value
	 */
	public static boolean getDivisionsByLeague(List<DivisionBean> divs, String leagueId) {
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getDivisions = null;	// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getDivisions = conn.prepareStatement("SELECT division.divisionID, divisionName from division, leaguexdivision WHERE leagueID=? AND division.divisionID=leaguexdivision.divisionID");
			getDivisions.setString(1, leagueId);
			rs = getDivisions.executeQuery();

			while(rs.next()) {
				DivisionBean div = new DivisionBean();
				div.setDivisionId(rs.getString(1));
				div.setDivisionName(rs.getString(2));
				divs.add(div);
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
			if (getDivisions != null) {
				try {
					getDivisions.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	/**
	 * The getDivisionsByLeague method gets the divisions based on leagueId
	 * @param divs - List<DivisionBean
	 * @param leagueId - String
	 * @return status - boolean value
	 */
	public static boolean getDivisionsWithNoLeague(List<DivisionBean> divs) {
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getDivisions = null;	// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getDivisions = conn.prepareStatement("SELECT d.divisionID, d.divisionName FROM division d natural left join leaguexdivision ld WHERE ld.divisionID IS NULL;");
			rs = getDivisions.executeQuery();

			while(rs.next()) {
				DivisionBean div = new DivisionBean();
				div.setDivisionId(rs.getString(1));
				div.setDivisionName(rs.getString(2));
				divs.add(div);
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
			if (getDivisions != null) {
				try {
					getDivisions.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}

	/**
	 * The getNews method gets all the news associated with the league
	 * @param news - List<NewsBean>
	 * @param id - The current division id
	 * @param lang - The current language of the website
	 * @param offset - The offset to get the news from (for pagination)
	 * @param maxRows - The number of news articles to be fetched each time
	 * @return status - boolean value
	 */
	public static boolean getNews(String id, List<NewsBean> news, String lang, int offset, int maxRows) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getLeague = null;		// SQL query
		PreparedStatement getNews = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set
		ResultSet rs = null;					// returned query result set
		String leagueName = null;				// division name to be used in second query

		// Connect to Database and execute SELECT query
		try {
			conn = ConnectionManager.getConnection();
			getLeague = conn.prepareStatement("select leagueName from league where leagueID = ?");
			getLeague.setString(1, id);

			resultSet = getLeague.executeQuery();
			status = resultSet.next();

			//gets the leagueName in order to get the news associated with the division
			leagueName = resultSet.getString(1);

			//if there is a result then get the news
			if(status) {
				
				getNews = conn.prepareStatement("select concat_ws(' ', u.userFirstName, u.userLastName), n.newsTitle,"
						+ " n.newsTitle_fr, n.newsTime, n.newsContent, n.newsContent_fr from news n inner join users"
						+ " u on u.userID = n.userID inner join newsxtags nt on nt.newsID = n.newsID"
						+ " inner join tags t on nt.tagID = t.tagID where tagDescription = ? order by n.newsTime desc"
						+ " LIMIT ?,?");
				getNews.setString(1, leagueName);
				getNews.setInt(2, offset);
				getNews.setInt(3, maxRows);
				rs = getNews.executeQuery();
				status = rs.next();

				//return to the start of the result set
				rs.beforeFirst();

				//Loop through and add the results of the query to a NewsBean then add it to the list
				while(rs.next()) {
					NewsBean nb = new NewsBean();
					nb.setUserName(rs.getString(1));
					nb.setTitle(rs.getString(2));
					nb.setTitleFR(rs.getString(3));
					nb.setPostedTime(rs.getTimestamp(4), lang);
					nb.setContent(rs.getString(5));
					nb.setContentFR(rs.getString(6));
					news.add(nb);
				}
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
			if (getLeague != null) {
				try {
					getLeague.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getNews != null) {
				try {
					getNews.close();
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
	
	/**
	 * The createNewLeague method handles the insertion into the DB of a League
	 * @param l - LeagueBean
	 * @return status - boolean value
	 */
	public static boolean createNewLeague(LeagueBean l) {
		
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement insertNewLeague = null;
		int result = 0;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			insertNewLeague = conn.prepareStatement("INSERT INTO league (leagueName, leaguePlayoffs, leaguePlayoffTeams, leagueStatus) VALUE (?,?,?,?)");
			insertNewLeague.setString(1, l.getLeagueName());
			insertNewLeague.setString(2, l.getLeaguePlayoffs());
			insertNewLeague.setString(3, l.getLeaguePlayoffTeams());
			insertNewLeague.setString(4, l.getLeagueStatus());
			
			result = insertNewLeague.executeUpdate();
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
			if (insertNewLeague != null) {
				try {
					insertNewLeague.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	/**
	 * The getLeagueForEdit method gets the league to be edited
	 * @param l - LeagueBean
	 * @return status - boolean value
	 */
	public static boolean getLeagueForEdit(LeagueBean l) {
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getLeague = null;		// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getLeague = conn.prepareStatement("SELECT leagueName, leaguePlayoffs, leaguePlayoffTeams, leagueStatus from league WHERE leagueId=?");
			getLeague.setString(1, l.getLeagueId());
			rs = getLeague.executeQuery();

			if(rs.next()) {
				l.setLeagueName(rs.getString(1));
				l.setLeaguePlayoffs(rs.getString(2));
				l.setLeaguePlayoffTeams(rs.getString(3));
				l.setStatus(rs.getString(4));
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
			if (getLeague != null) {
				try {
					getLeague.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	/**
	 * The saveChanges method saves the changes for one league into the db
	 * @param l - LeagueBean from servlet
	 * @return status - boolean value
	 */
	public static boolean saveChanges(LeagueBean l) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement updateLeague = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        updateLeague = conn.prepareStatement("UPDATE league SET leagueName=?, leaguePlayoffs=?, leaguePlayoffTeams=?, leagueStatus=? WHERE leagueID=?");
	        updateLeague.setString(1, l.getLeagueName());
	        updateLeague.setString(2, l.getLeaguePlayoffs());
	        updateLeague.setString(3, l.getLeaguePlayoffTeams());
	        updateLeague.setString(4, l.getLeagueStatus());
	        updateLeague.setString(5, l.getLeagueId());
	        result = updateLeague.executeUpdate();	        
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
	        if (updateLeague != null) {
	            try {
	            	updateLeague.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	/**
	 * The deleteDivision method deletes the division recursively
	 * @param divId - String
	 * @return status - boolean value
	 */
	public static boolean deleteLeague(String lId) {
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement deleteLXD = null;
	    PreparedStatement deleteLeague = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        deleteLXD = conn.prepareStatement("DELETE FROM leaguexdivision USING leaguexdivision, league WHERE `league`.`leagueID` = `leaguexdivision`.`leagueID` AND league.leagueID=?");
	        deleteLXD.setString(1, lId);
	        result = deleteLXD.executeUpdate();
	        if(result >= 0) {
	        	deleteLeague = conn.prepareStatement("DELETE FROM league USING league WHERE league.leagueID=?");
	        	deleteLeague.setString(1, lId);
		        result = deleteLeague.executeUpdate();	        
		        if(result >= 0) {		        	
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
	        if (deleteLXD != null) {
	            try {
	            	deleteLXD.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (deleteLeague != null) {
	            try {
	            	deleteLeague.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
