package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.DivisionBean;
import beans.NewsBean;

/**
 * The Division class gets all the divisions in the database, allows the user to create a new division, get the news associated
 * with the division, allow the user to delete a division, and get a specific division
 */
public class Division {

	/**
	 * The getNews method gets all the news associated with the division
	 * @param <NewsBean>
	 * @param id - The current division id
	 * @param lang - The current language of the website
	 * @param offset - The offset to get the news from (for pagination)
	 * @param maxRows - The number of news articles to be fetched each time
	 * @return status - boolean value
	 */
	public static boolean getNews(String id, List<NewsBean> news, String lang, int offset, int maxRows) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getDivision = null;	// SQL query
		PreparedStatement getNews = null;		// SQL query
		ResultSet resultSet = null;				// returned query result set
		ResultSet rs = null;					// returned query result set
		String divisionName = null;				// division name to be used in second query

		// Connect to Database and execute SELECT query
		try {
			conn = ConnectionManager.getConnection();
			getDivision = conn.prepareStatement("select divisionName from division where divisionID = ?");
			getDivision.setString(1, id);
			
			resultSet = getDivision.executeQuery();
			status = resultSet.next();

			//gets the divisionName in order to get the news associated with the division
			divisionName = resultSet.getString(1);

			//if there is a result then get the news
			if(status) {
				getNews = conn.prepareStatement("select u.userName, n.newsTitle, n.newsTime, n.newsContent from news n"
						+ " inner join users u on u.userID = n.userID inner join newsxtags nt on nt.newsID = n.newsID"
						+ " inner join tags t on nt.tagID = t.tagID where tagDescription = ? order by n.newsTime desc"
						+ " LIMIT ?,?");
				getNews.setString(1, divisionName);
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
					nb.setPostedTime(rs.getTimestamp(3), lang);
					nb.setContent(rs.getString(4));
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
		return status;
	}
	
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
	 * The getSpecificDivision gets information of a specific division
	 * @param <DivisionBean>
	 * @param id - The id of the current database
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
	
	public static boolean createNewDiv(String newDiv) {
		
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement insertNewDiv = null;
		int result = 0;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			insertNewDiv = conn.prepareStatement("INSERT INTO division (divisionName) VALUE (?)");
			insertNewDiv.setString(1, newDiv);
			result = insertNewDiv.executeUpdate();
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
			if (insertNewDiv != null) {
				try {
					insertNewDiv.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
	
	public static boolean getDivisionForEdit(DivisionBean div) {
		boolean status = false;					// Status of createNewUser
		Connection conn = null;					// DB Connection
		PreparedStatement getDivision = null;	// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getDivision = conn.prepareStatement("SELECT divisionID, divisionName from division WHERE divisionID=?");
			getDivision.setString(1, div.getDivisionId());
			rs = getDivision.executeQuery();

			if(rs.next()) {
				div.setDivisionName(rs.getString(2));
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
		}	    
		return status;
	}
	
	public static boolean saveChanges(DivisionBean div) {
		
		boolean status = false;						// Status of createNewUser
	    Connection conn = null;						// DB Connection
	    PreparedStatement updateDivision = null;	// SQL query
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        updateDivision = conn.prepareStatement("UPDATE division SET divisionName=? WHERE divisionID=?");
	        updateDivision.setString(1, div.getDivisionName());
	        updateDivision.setString(2, div.getDivisionId());	        
	        result = updateDivision.executeUpdate();	        
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
	        if (updateDivision != null) {
	            try {
	            	updateDivision.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	public static boolean deleteDivision(String divId) {
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement deleteRefs = null;
	    PreparedStatement deleteTeams = null;
	    PreparedStatement deleteDivs = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        deleteRefs = conn.prepareStatement("DELETE FROM refereexdivision USING refereexdivision, division WHERE `division`.`divisionID` = `refereexdivision`.`divisionID` AND division.divisionID=?");
	        deleteRefs.setString(1, divId);
	        result = deleteRefs.executeUpdate();
	        if(result >= 0) {
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
	        if (deleteRefs != null) {
	            try {
	            	deleteRefs.close();
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
