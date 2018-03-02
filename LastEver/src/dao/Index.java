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
public class Index {

	/**
	 * The validateUserLogin method validates a UserBeans login credentials
	 * @param lang 
	 * @param <NewsBean>
	 * @param user - NewsBean credentials
	 * @return status - boolean value
	 */
	public static boolean getNews(List<NewsBean> news, String lang) { 

		boolean status = false;					// query status
		Connection conn = null;					// DB connection
		PreparedStatement getNews = null;		// SQL query
		ResultSet rs = null;					// returned query result set

		// Connect to Database and execute SELECT query with UserBean data
		try {
			conn = ConnectionManager.getConnection();

			getNews = conn.prepareStatement("select u.userName, n.newsTitle, n.newsTime, n.newsContent from news n"
					+ " inner join users u on u.userID = n.userID order by n.newsTime desc");
			rs = getNews.executeQuery();
			status = rs.next();

			rs.beforeFirst();

			while(rs.next()) {
				NewsBean nb = new NewsBean();
				nb.setUserName(rs.getString(1));
				nb.setTitle(rs.getString(2));
				nb.setPostedTime(rs.getTimestamp(3), lang);
				nb.setContent(rs.getString(4));
				news.add(nb);
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
