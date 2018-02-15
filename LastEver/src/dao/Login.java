package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	
	private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "lastever";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String userName = "admin";
    private static String dbPassword = "lastever";
	
	public static boolean validateLogin(String email, String password) { 
		
		boolean status = false;
	    Connection conn = null;
	    PreparedStatement getusers = null;
	    ResultSet resultSet = null;
	
	    try {
	        Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url + dbName, userName, dbPassword);
	        getusers = conn.prepareStatement("select username, emailAddress, password from users where emailAddress=? and password=?");
	        getusers.setString(1, email);
	        getusers.setString(2, password);
	        resultSet = getusers.executeQuery();
	        status = resultSet.next();
	
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
	        if (getusers != null) {
	            try {
	            	getusers.close();
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
	    }
	    return status;
	}
}
