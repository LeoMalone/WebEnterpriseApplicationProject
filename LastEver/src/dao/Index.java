package dao;

import beans.NewsBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The Index class gets all the news in the database regardless of what tags the news has
 * @author Kevin Villemaire
 */
public class Index {

	/**
	 * The getNews method gets all news in the database
	 * @param lang 
	 * @param <NewsBean>
	 * @param lang - The current language of the website
	 * @param offset - The offset to get the news from (for pagination)
	 * @param maxRows - The number of news articles to be fetched each time
	 * @return status - boolean value
	 */
	public static boolean getNews(List<NewsBean> news, String lang, int offset, int maxRows) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getNews = null;		// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database and execute SELECT query with NewsBean data
		try {
			conn = ConnectionManager.getConnection();

			getNews = conn.prepareStatement("select concat_ws(' ', u.userFirstName, u.userLastName), n.newsTitle,"
					+ " n.newsTitle_fr, n.newsTime, n.newsContent, n.newsContent_fr from news n inner join users u"
					+ " on u.userID = n.userID order by n.newsTime desc limit ?,?");
			getNews.setInt(1, offset);
			getNews.setInt(2, maxRows);
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
			if (getNews != null) {
				try {
					getNews.close();
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
	 * The numberOfArticles method gets the number of articles
	 * @return number - int value
	 */
	public static int numberOfArticles() { 

		int number = 0;							// number of news articles in database
		Connection conn = null;					// DB connection
		PreparedStatement getNews = null;		// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database and execute SELECT query
		try {
			conn = ConnectionManager.getConnection();

			getNews = conn.prepareStatement("select count(newsID) from news");
			rs = getNews.executeQuery();
			rs.next();
			//store the number of articles
			number = rs.getInt(1);

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
			if (getNews != null) {
				try {
					getNews.close();
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
	
}
