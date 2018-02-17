package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The ConnectionManager class is used whenever
 * a connection to the database is needed
 */
public class ConnectionManager {
	
	// Database Credentials
	private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "lastever";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String userName = "admin";
    private static String dbPassword = "lastever";
	
    /**
     * The Connection method uses the database credentials to connect to our database
     * @return Connection - db connection
     */
	public static Connection getConnection() {
		
		// Connection var to be returned
		Connection conn = null;		
		
		// try connecting to db with given credentials
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, dbPassword);
		
		// Catch all possible exceptions
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
