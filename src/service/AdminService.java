package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
public static ArrayList<Admin> getAdminList() {
		
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			ResultSet rs = stmt.executeQuery();
		
			
			while(rs.next()) {
				Admin admin = new Admin(rs.getString("email"),
								     	rs.getString("hashedpassword"),
								     	rs.getString("firstname"),
								     	rs.getString("lastname"),
								     	rs.getString("role"));
				adminList.add(admin);
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return adminList;
	}
	
	public static Admin getAdmin(String email) {
		
		Admin admin = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			ResultSet rs = stmt.executeQuery();
		
			
			while(rs.next()) {
				if(email.equals(rs.getString("email"))){
					admin = new Admin(rs.getString("email"),
							rs.getString("hashedpassword"),
							rs.getString("firstname"),
							rs.getString("lastname"),
							rs.getString("role"));
				}
				
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return admin;
	}

	public static void updateAdmin(Admin newAdmin) {
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "UPDATE admin SET email = ?, hasedpassword = ?, firstname = ?, lastname = ?, role = ? WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, newAdmin.getEmail());
			stmt.setString(2, newAdmin.getHashedpassword());
			stmt.setString(3, newAdmin.getFirstname());
			stmt.setString(4, newAdmin.getLastname());
			stmt.setString(5, newAdmin.getRole());
			stmt.setString(6, newAdmin.getEmail());
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void deleteAdmin(String email) {
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "DELETE FROM admin WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
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
