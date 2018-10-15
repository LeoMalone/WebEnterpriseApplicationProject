package dao;

import beans.ScheduleBean;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The RefereeSchedule class handles all db operation relating to viewing a referees schedule
 * @author Liam Maloney, Kevin Villemaire
 */
public class RefereeSchedule {

	/**
	 * The getSchedule gets all schedule entries from the db
	 * @param schedule ScheduleBean list
	 * @return boolean status
	 */
	public static boolean getSchedule(List<ScheduleBean> schedule, String username) {

		boolean status = false;					// Status of getSchedule
		Connection conn = null;					// DB Connection
		PreparedStatement getSchedule = null;
		PreparedStatement getRefID = null;
		ResultSet rs = null;
		ResultSet resultSet = null;

		// Connect to Database 
		try {
			conn = ConnectionManager.getConnection();
			getRefID = conn.prepareStatement("select refereeID from users where username=?");
			getRefID.setString(1, username);
			resultSet = getRefID.executeQuery();
			status = resultSet.next();

			if(status) {
				getSchedule = conn.prepareStatement("select s.gameID, s.gameDate, s.gameTime, h.teamName, s.homeTeam,"
						+ " s.homeScore, concat(a.teamName, '') as away, s.awayTeam, s.awayScore, s.gameStatus, v.venueName from"
						+ " schedule s inner join team h on h.teamID = s.homeTeam inner join team a on a.teamID ="
						+ " s.awayTeam inner join teamxdivision td on td.teamID = h.teamID inner join schedulexreferee sr"
						+ " on sr.gameID = s.gameID inner join venuexgame vg on vg.gameID = s.gameID inner join"
						+ " venue v on v.venueID = vg.venueID where sr.refereeID=?");
				getSchedule.setString(1, resultSet.getString(1));
				rs = getSchedule.executeQuery();

				while(rs.next()) {
					ScheduleBean sb = new ScheduleBean();
					sb.setTitle(rs.getString(4) + " vs " + rs.getString(7));
					sb.setStart(rs.getString(2), rs.getString(3));
					sb.setGameDate(rs.getString(2));
					sb.setGameTime(rs.getString(3));
					sb.setHomeTeam(rs.getString(5));
					sb.setHomeScore(rs.getString(6));
					sb.setAwayTeam(rs.getString(8));
					sb.setAwayScore(rs.getString(9));
					sb.setGameStatus(rs.getString(10));
					sb.setVenue(rs.getString(11));
					schedule.add(sb);
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
			if (getSchedule != null) {
				try {
					getSchedule.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	    
		return status;
	}
}