package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import beans.Log;

public class LogService {
	
	public static void addLog(Log log){
		System.out.println("*************************************************************");
		System.out.println("I am at LogService, addLog");
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO log (ip_address, attempt_date, successful, email, type)"
					  									  + " VALUES (?,?,?,?, ?)");
			stmt.setString(1, log.getIp_address());
			stmt.setTimestamp(2, log.getAttempt_date());
			stmt.setBoolean(3, log.isSuccessful());
			stmt.setString(4, log.getEmail());
			stmt.setString(5, log.getType());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Admin Successfully added!");
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("LogService, addLog complete!");
		System.out.println("*************************************************************");
	}
}
