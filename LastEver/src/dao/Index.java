package dao;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.NewsBean;

/**
 * The Index class gets all the news in the database regardless of what tags the news has
 */
public class Index {

	/**
	 * The getNews method gets all news in the database
	 * @param lang 
	 * @param <NewsBean>
	 * @param lang - The current language of the website
	 * @return status - boolean value
	 */
	public static boolean getNews(List<NewsBean> news, String lang) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getNews = null;		// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database and execute SELECT query with NewsBean data
		try {
			conn = ConnectionManager.getConnection();

			getNews = conn.prepareStatement("select u.userName, n.newsTitle, n.newsTime, n.newsContent from news n"
					+ " inner join users u on u.userID = n.userID order by n.newsTime desc");
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
}
