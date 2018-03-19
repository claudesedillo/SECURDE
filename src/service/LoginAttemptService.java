package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
	
	public static boolean checkIfExists(String IpAddress){
		System.out.println("*************************************************************");
		System.out.println("I am at LoginAttemptService, checkIfExists");
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM login_attempts");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				if(IpAddress.equals(rs.getString("ip_address"))){
					return true;
				}
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("LoginAttemptService, checkIfExists complete!");
		System.out.println("*************************************************************");
		return false;
	}
	
	public static boolean checkForBruteForce(String IpAddress){
		System.out.println("*************************************************************");
		System.out.println("I am at LoginAttemptService, checkForBruteForce");
		java.sql.Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM login_attempts");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				if(IpAddress.equals(rs.getString("ip_address"))){
					if(rs.getInt("attempt_count") > 5){
						if(currentTime.getTime() - rs.getTimestamp("attempt_date").getTime() < 300000){
							return true;
						}
						else {
							deleteLoginAttempt(IpAddress);
							return false;
						}
					}
					else {
						LoginAttempt logA = getLoginAttempt(IpAddress);
						logA.setAttempt_count(logA.getAttempt_count() + 1);
						updateLoginAttempt(logA);
						return false;
					}
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
		return false;
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
			stmt.setString(1, newLogAttempt.getIp_address());
			stmt.setTimestamp(2, newLogAttempt.getAttempt_date());
			stmt.setInt(3, newLogAttempt.getAttempt_count());		
			stmt.setString(4, newLogAttempt.getIp_address());
			System.out.println("Query is: " + query);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void deleteLoginAttempt(String IpAddress){
		System.out.println("*************************************************************");
		System.out.println("I am at LoginAttemptService, deleteLoginAttempt");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "DELETE FROM login_attempts WHERE ip_address = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, IpAddress);
			stmt.executeUpdate();
			System.out.println("Query is: " + stmt);
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("LoginAttemptService, deleteLoginAttempt complete!");
		System.out.println("*************************************************************");
	}
	
}
