package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Do NOT forget to include this on your gitignore file.
 * This is used to access your database since we may have different passwords and root username.
 * @author Alex II
 *
 */
public class DatabaseManager {
	private static final String url = "jdbc:mysql://localhost:3306/indigo?autoReconnect=true&useSSL=false";
	private static final String user = "root";
	private static final String pass= "password";
	
	public DatabaseManager() {}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		return DriverManager.getConnection(url, user, pass);
	}
	
	
}
