package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.LoginAttempt;

public class LoginAttemptService {
	
	public static void addLoginAttempt(LoginAttempt logAttempt){
		System.out.println("*************************************************************");
		System.out.println("I am at LoginAttemptService, addLoginAttempt");

		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO login_attempts (ip_address, attempt_date, attempt_count)"
														  + " VALUES (?,?,?)");
			stmt.setString(1, logAttempt.getIp_address());
			stmt.setTimestamp(2, logAttempt.getAttempt_date());
			stmt.setInt(3, logAttempt.getAttempt_count());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Admin Successfully added!");
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("LoginAttemptService, addLoginAttempt complete!");
		System.out.println("*************************************************************");
	}
	
	public static LoginAttempt getLoginAttempt(String IpAddress){
		System.out.println("*************************************************************");
		System.out.println("I am at LoginAttemptService, getLoginAttempt");
		LoginAttempt logAttempt = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM login_attempts");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				if(IpAddress.equals(rs.getString("ip_address"))){
					logAttempt = new LoginAttempt(rs.getString("ip_address"),
												  rs.getTimestamp("attempt_date"),
												  rs.getInt("attempt_count"));
				}
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("LoginAttemptService, getLoginAttempt complete!");
		System.out.println("*************************************************************");
		
		
		return logAttempt;
	}
	
	public static void updateLoginAttempt(LoginAttempt newLogAttempt){
		System.out.println("*************************************************************");
		System.out.println("I am at LoginAttemptService, updateLoginAttempt");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "UPDATE login_attempts SET ip_address = ?, attempt_date = ?, attempt_count = ? WHERE ip_address = ?";	
			PreparedStatement stmt = conn.prepareStatement(query);
			//XXX NOT DONE
			System.out.println("Query is: " + query);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}
}
