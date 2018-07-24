package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * The ConnectionManager class is used whenever
 * a connection to the database is needed
 */
public class ConnectionManager {
	
	// Database Credentials
	private static String url = "jdbc:mysql://lastever.mysql.database.azure.com:3306/lastever";
    private static String driver = "com.mysql.jdbc.Driver";
	
    /**
     * The Connection method uses the database credentials to connect to our database
     * @return Connection - db connection
     */
	public static Connection getConnection() {
		Properties info = new Properties();
		
		// Connection var to be returned
		Connection conn = null;		
		
		// try connecting to db with given credentials
		try {
			info.setProperty("user", "lastever@lastever");
			info.setProperty("password", "Sup3rS0n1c2000");
			info.setProperty("useSSL", "true");
			info.setProperty("characterEncoding", "UTF-8");
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, info);
		
		// Catch all possible exceptions
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
