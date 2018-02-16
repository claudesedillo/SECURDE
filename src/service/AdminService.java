package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Admin;

public class AdminService {
	
	public static void addAdmin(Admin admin){
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO admin (email, hashedpassword, firstname, lastname, role)"
														  + " VALUES (?,?,?,?,?)");
			stmt.setString(1, admin.getEmail());
			stmt.setString(2, admin.getHashedpassword());
			stmt.setString(3, admin.getFirstname());
			stmt.setString(4, admin.getLastname());
			stmt.setString(5, admin.getRole());
			
			stmt.executeUpdate();
			System.out.println("Admin Successfully added!");
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public static boolean checkLogin(String email, String hashedPass){
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			
			ResultSet rs = stmt.executeQuery();
			
			String user, pass;
			
			while(rs.next()){
				user = rs.getString("email");
				pass = rs.getString("hashedpassword");
				
				if(user.equals(email) && pass.equals(hashedPass)){
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		return false;
	}
	
	public static boolean checkUser(String email){
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			
			ResultSet rs = stmt.executeQuery();
			
			String user;
			
			while(rs.next()){
				user = rs.getString("email");
				
				if(user.equals(email)){
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		return false;
	}
}
