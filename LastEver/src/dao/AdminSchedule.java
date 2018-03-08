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
	        	sb.setTitle("Game ID: " + rs.getString(1));
	        	sb.setStart(rs.getString(2));
	        	sb.setGameTime(rs.getString(3));
	        	sb.setHomeTeam(rs.getString(4));
	        	sb.setHomeScore(rs.getString(5));
	        	sb.setAwayTeam(rs.getString(6));
	        	sb.setAwayScore(rs.getString(7));
	        	sb.setGameStatus(rs.getString(8));
	        	sb.setUrl("./editSchedule?=" + rs.getString(1));
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

	
	public static boolean getScheduleById(ScheduleBean schedule) {
		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement getSchedule = null;
	    ResultSet rs = null;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        getSchedule = conn.prepareStatement("SELECT gameID, gameDate, gameTime, homeTeam, homeScore, awayTeam, awayScore, gameStatus from schedule WHERE gameID=?");
	        getSchedule.setString(1, schedule.getTitle());
	        rs = getSchedule.executeQuery();
	        
	        if(rs.next()) {
	        	schedule.setStart(rs.getString(2));
	        	schedule.setGameTime(rs.getString(3));
	        	schedule.setHomeTeam(rs.getString(4));
	        	schedule.setHomeScore(rs.getString(5));
	        	schedule.setAwayTeam(rs.getString(6));
	        	schedule.setAwayScore(rs.getString(7));
	        	schedule.setGameStatus(rs.getString(8));
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
	
	public static boolean updateSchedule(ScheduleBean schedule) {		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement updateSchedule = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        updateSchedule = conn.prepareStatement("UPDATE schedule SET gameDate=?, gameTime=?, homeTeam=?, homeScore=?, awayTeam=?, awayScore=?, gameStatus=? WHERE gameID=?");
	        updateSchedule.setString(1, schedule.getStart());
	        updateSchedule.setString(2, schedule.getGameTime());
	        updateSchedule.setString(3, schedule.getHomeTeam());
	        updateSchedule.setString(4, schedule.getHomeScore());
	        updateSchedule.setString(5, schedule.getAwayTeam());
	        updateSchedule.setString(6, schedule.getAwayScore());
	        updateSchedule.setString(7, schedule.getGameStatus());	 
	        updateSchedule.setString(8, schedule.getTitle());
	        result = updateSchedule.executeUpdate();	        
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
	        if (updateSchedule != null) {
	            try {
	            	updateSchedule.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	public static boolean createNewGame(ScheduleBean schedule) {		
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement createSchedule = null;
	    int result = 0;
	
	    // Connect to Database 
	    try {
	        conn = ConnectionManager.getConnection();
	        createSchedule = conn.prepareStatement("INSERT INTO schedule (gameDate, gameTime, homeTeam, homeScore, awayTeam, awayScore, gameStatus) VALUES (?, ?, ?, ?, ?, ?, ?)");
	        createSchedule.setString(1, schedule.getStart());
	        createSchedule.setString(2, schedule.getGameTime());
	        createSchedule.setString(3, schedule.getHomeTeam());
	        createSchedule.setString(4, schedule.getHomeScore());
	        createSchedule.setString(5, schedule.getAwayTeam());
	        createSchedule.setString(6, schedule.getAwayScore());
	        createSchedule.setString(7, schedule.getGameStatus());	 
	        result = createSchedule.executeUpdate();	        
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
	        if (createSchedule != null) {
	            try {
	            	createSchedule.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
	
	public static boolean deleteSchedule(String id) {
		boolean status = false;					// Status of createNewUser
	    Connection conn = null;					// DB Connection
	    PreparedStatement deleteRef = null;
	    PreparedStatement deleteVenue = null;
	    PreparedStatement deleteSchedule = null;
	    int result = 0;   
	    
	    try {
	        conn = ConnectionManager.getConnection();
	        deleteRef = conn.prepareStatement("DELETE FROM schedulexreferee USING schedulexreferee, schedule WHERE `schedule`.`gameID` = `schedulexreferee`.`gameID` AND schedule.gameID=?");
	        deleteRef.setString(1, id);
	        result = deleteRef.executeUpdate();
	        if(result >= 0) {
	        	deleteVenue = conn.prepareStatement("DELETE FROM venuexgame USING venuexgame, schedule WHERE `schedule`.`gameID` = `venuexgame`.`gameID` AND schedule.gameID=?");
	        	deleteVenue.setString(1, id);
	        	result = deleteVenue.executeUpdate();
	        	if(result >= 0) {
	        		deleteSchedule = conn.prepareStatement("DELETE FROM schedule USING schedule WHERE schedule.gameID=?");
	        		deleteSchedule.setString(1, id);
		        	result = deleteSchedule.executeUpdate();
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
	        if (deleteSchedule != null) {
	            try {
	            	deleteSchedule.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (deleteRef != null) {
	            try {
	            	deleteRef.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (deleteVenue != null) {
	            try {
	            	deleteVenue.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }	    
	    return status;
	}
}
