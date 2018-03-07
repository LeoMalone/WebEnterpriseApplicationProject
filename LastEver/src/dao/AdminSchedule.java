package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.ScheduleBean;
import db.ConnectionManager;

public class AdminSchedule {
	
public static boolean getSchedule(List<ScheduleBean> schedule) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getSchedule = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getSchedule = conn.prepareStatement("SELECT gameID, gameDate, gameTime, homeTeam, homeScore, awayTeam, awayScore, gameStatus from schedule");
	        rs = getSchedule.executeQuery();
	        
	        while(rs.next()) {
	        	ScheduleBean sb = new ScheduleBean();
	        	sb.setTitle(rs.getString(1));
	        	sb.setStart(rs.getString(2));
	        	sb.setGameTime(rs.getString(3));
	        	sb.setHomeTeam(rs.getString(4));
	        	sb.setHomeScore(rs.getString(5));
	        	sb.setAwayTeam(rs.getString(6));
	        	sb.setAwayScore(rs.getString(7));
	        	sb.setGameStatus(rs.getString(8));
	        	schedule.add(sb);
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
