package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "lastever";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String userName = "admin";
    private static String dbPassword = "lastever";
	
	public static Connection getConnection() {
		
		Connection conn = null;		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, dbPassword);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
