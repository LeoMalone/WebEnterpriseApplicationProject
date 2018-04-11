package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.DivisionBean;
import beans.StandingsBean;
import db.ConnectionManager;

/**
 * The Division class gets all the divisions in the database, allows the user to create a new division, get the news associated
 * with the division, allow the user to delete a division, and get a specific division
 */
public class Division {

	/**
	 * The numberOfArticles method gets the number of articles associated with each division
	 * @param dID - The current division id
	 * @return number - int value
	 */
	public static int numberOfArticles(String dID) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getDivision = null;	// SQL query
		PreparedStatement getNews = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set
		ResultSet rs = null;					// returned query result set
		String divisionName = null;				// division name to be used in second query
		int number = 0;							// number of news articles

		// Connect to Database and execute SELECT query
		try {
			conn = ConnectionManager.getConnection();
			getDivision = conn.prepareStatement("select divisionName from division where divisionID = ?");
			getDivision.setString(1, dID);
			
			resultSet = getDivision.executeQuery();
			status = resultSet.next();

			//gets the divisionName in order to get the news associated with the division
			divisionName = resultSet.getString(1);

			//if there is a result then get the news amount
			if(status) {
				getNews = conn.prepareStatement("select count(n.newsID) from news n inner join newsxtags nt on nt.newsID"
						+ " = n.newsID inner join tags t on nt.tagID = t.tagID where t.tagDescription = ?");
				getNews.setString(1, divisionName);
				rs = getNews.executeQuery();
				status = rs.next();
				
				number = rs.getInt(1);
				
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
			if (getDivision != null) {
				try {
					getDivision.close();
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
		return number;
	}
	
	/**
	 * The getAllDivisions method gets all the divisions in the database
	 * @param <DivisionBean>
	 * @return status - boolean value
	 */	
	public static boolean getAllDivisions(List<DivisionBean> divisionList) {

		boolean status = false;					// query status
		Connection conn = null;					// DB Connection
		PreparedStatement allDivisions = null;	// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			allDivisions = conn.prepareStatement("SELECT divisionID, divisionName from division");
			rs = allDivisions.executeQuery();
			status = rs.next();
			
			//return to the start of the result set
			rs.beforeFirst();
			
			//Loop through and add the results of the query to a DivisionBean then add it to the list
			while(rs.next()) {
				DivisionBean db = new DivisionBean();
				db.setDivisionId(rs.getString(1));
				db.setDivisionName(rs.getString(2));
				divisionList.add(db);
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
			if (allDivisions != null) {
				try {
					allDivisions.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	/**
	 * The getDivisionStandings method gets the divisional standings based on the leagueID
	 * @param lID - String of the league's ID
	 * @param divisionList - List<DivisionBean>
	 * @return status - boolean value
	 */
	public static boolean getDivisionStandings(String lID, List<DivisionBean> divisionList) {

		boolean status = false;							// query status
		Connection conn = null;							// DB Connection
		PreparedStatement divisionStandings = null;		// SQL query
		ResultSet rs = null;							// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			divisionStandings = conn.prepareStatement("SELECT d.divisionID, d.divisionName from division d inner join"
					+ " leaguexdivision ld on ld.divisionID = d.divisionID where ld.leagueID = ?");
			divisionStandings.setString(1, lID);
			rs = divisionStandings.executeQuery();
			status = rs.next();
			
			//return to the start of the result set
			rs.beforeFirst();
			
			//Loop through and add the results of the query to a DivisionBean then add it to the list
			while(rs.next()) {
				DivisionBean db = new DivisionBean();
				List<StandingsBean> standings = new ArrayList<StandingsBean>();
				db.setDivisionId(rs.getString(1));
				db.setDivisionName(rs.getString(2));
				Standings.getStandings(rs.getString(1), standings);
				db.setStandings(standings);
				divisionList.add(db);
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
			if (divisionStandings != null) {
				try {
					divisionStandings.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	/**
	 * The getSpecificDivision gets information of a specific division
	 * @param divisionList - List<DivisionBean>
	 * @param id - String id of the current database
	 * @return status - boolean value
	 */	
	public static boolean getSpecificDivision(List<DivisionBean> divisionList, String id) {

		boolean status = false;					// query status
		Connection conn = null;					// DB Connection
		PreparedStatement specificDiv = null;	// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			specificDiv = conn.prepareStatement("SELECT divisionID, divisionName from division WHERE divisionID=?");
			specificDiv.setString(1, id);
			rs = specificDiv.executeQuery();
			status = rs.next();
			
			//return to the start of the result set
			rs.beforeFirst();
			
			//Loop through and add the results of the query to a DivisionBean then add it to the list
			while(rs.next()) {
				DivisionBean db = new DivisionBean();
				db.setDivisionId(rs.getString(1));
				db.setDivisionName(rs.getString(2));
				divisionList.add(db);
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
			if (specificDiv != null) {
				try {
					specificDiv.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	/**
	 * The createNewDiv method handles the insertion into the DB of a division
	 * @param div - DivisionBean
	 * @return status - boolean value
	 */
	public static boolean createNewDiv(DivisionBean div) {
		
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement insertNewDiv = null;
		PreparedStatement getNewDivId = null; 
		PreparedStatement insertNewLeague = null;
		ResultSet rs = null;
		int result = 0;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			insertNewDiv = conn.prepareStatement("INSERT INTO division (divisionName) VALUE (?)");
			insertNewDiv.setString(1, div.getDivisionName());
			result = insertNewDiv.executeUpdate();
			if(result == 1) {
				getNewDivId = conn.prepareStatement("SELECT divisionID FROM division WHERE divisionName=?");
				getNewDivId.setString(1, div.getDivisionName());
				rs = getNewDivId.executeQuery();
				if(rs.next()) {
					div.setDivisionId(rs.getString(1));
					insertNewLeague = conn.prepareStatement("INSERT INTO leaguexdivision (leagueID, divisionID) VALUE (?, ?)");
					insertNewLeague.setString(1, div.getLeageId());
					insertNewLeague.setString(2, div.getDivisionId());
					result = insertNewLeague.executeUpdate();
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
			if (insertNewDiv != null) {
				try {
					insertNewDiv.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getNewDivId != null) {
				try {
					getNewDivId.close();
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
	 * The getDivisionForEdit method gets the divison to be edited
	 * @param div - DivisionBean
	 * @return status - boolean value
	 */
	public static boolean getDivisionForEdit(DivisionBean div) {
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getDivision = null;	// SQL query
		PreparedStatement getLeagueId = null;
		ResultSet rs = null;					// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getDivision = conn.prepareStatement("SELECT divisionID, divisionName from division WHERE divisionID=?");
			getDivision.setString(1, div.getDivisionId());
			rs = getDivision.executeQuery();

			if(rs.next()) {
				div.setDivisionName(rs.getString(2));
				
				getLeagueId = conn.prepareStatement("SELECT leagueID FROM division, leaguexdivision WHERE division.divisionID=? AND division.divisionID=leaguexdivision.divisionID");
				getLeagueId.setString(1, div.getDivisionId());
				rs = getLeagueId.executeQuery();
				if(rs.next())
					div.setLeagueId(rs.getString(1));				
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
			if (getDivision != null) {
				try {
					getDivision.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (getLeagueId != null) {
				try {
					getLeagueId.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	/**
	 * The saveChanges method saves changes from editing the division
	 * @param div - DivisionBean
	 * @return status - boolean value
	 */
	public static boolean saveChanges(DivisionBean div) {
		
		boolean status = false;						// Status of createNewUser
	    Connection conn = null;						// DB Connection
	    PreparedStatement updateDivision = null;	// SQL query
	    PreparedStatement updateLeague = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        updateDivision = conn.prepareStatement("UPDATE division SET divisionName=? WHERE divisionID=?");
	        updateDivision.setString(1, div.getDivisionName());
	        updateDivision.setString(2, div.getDivisionId());	        
	        result = updateDivision.executeUpdate();	        
	        if(result == 1) {
	        	updateLeague = conn.prepareStatement("CALL update_div(?, ?)");
	        	updateLeague.setString(1, div.getDivisionId());
	        	updateLeague.setString(2, div.getLeageId());
	        	result = updateLeague.executeUpdate();
	        	if(result >= 0)
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
	        if (updateDivision != null) {
	            try {
	            	updateDivision.close();
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
	public static boolean deleteDivision(String divId) {
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement deleteTeams = null;
	    PreparedStatement deleteDivs = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();	        
        	deleteTeams = conn.prepareStatement("DELETE FROM teamxdivision USING teamxdivision, division WHERE `division`.`divisionID` = `teamxdivision`.`divisionID` AND division.divisionID=?");
        	deleteTeams.setString(1, divId);
	        result = deleteTeams.executeUpdate();	        
	        if(result >= 0) {
	        	deleteDivs = conn.prepareStatement("DELETE FROM division USING division WHERE division.divisionID=?");
	        	deleteDivs.setString(1, divId);
	        	result = deleteDivs.executeUpdate();
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
	        if (deleteTeams != null) {
	            try {
	            	deleteTeams.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (deleteDivs != null) {
	            try {
	            	deleteDivs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
