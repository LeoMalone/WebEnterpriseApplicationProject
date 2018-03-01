package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.NewsBean;

/**
 * The Division class gets the standings for the current division
 */
public class Division {
	
	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param <NewsBean>
	 * @param user - NewsBean credentials
	 * @return status - boolean value
	 */
	public static boolean getNews(String id, List<NewsBean> news) { 
		
		boolean status = false;					// query status
	    Connection conn = null;					// DB connection
	    PreparedStatement getDivision = null;	// SQL query for DivisionName
	    PreparedStatement getNews = null;		// SQL query
	    ResultSet resultSet = null;				// returned query result set
	    ResultSet rs = null;					// returned query result set
	    String divisionName = null;				// division name to be used in second query
	
	    // Connect to Database and execute SELECT query with UserBean data
	    try {
	        conn = ConnectionManager.getConnection();
	        getDivision = conn.prepareStatement("select divsionName from division where divisionID = ?");
	        getDivision.setString(1, id);
	        
	        resultSet = getDivision.executeQuery();
	        status = resultSet.next();
	        
	        divisionName = resultSet.getString(1);
	        
	        if(status) {
	        	
	        	getNews = conn.prepareStatement("select u.userName, n.newsTitle, n.newsTime, n.newsContent from news n"
		        		+ " inner join users u on u.userID = n.userID inner join newsxtags nt on nt.newsID = n.newsID"
		        		+ " inner join tags t on nt.tagID = t.tagID where tagDescription = ? order by n.newsTime desc");
		        getNews.setString(1, divisionName);
		        rs = getNews.executeQuery();
		        status = rs.next();
		        
		        rs.beforeFirst();
		        
		        while(rs.next()) {
		        	NewsBean nb = new NewsBean();
		        	nb.setUserName(rs.getString(1));
		        	nb.setTitle(rs.getString(2));
		        	nb.setPostedTime(rs.getTimestamp(3));
		        	nb.setContent(rs.getString(4));
		        	news.add(nb);
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
}
