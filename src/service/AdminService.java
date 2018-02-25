package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Admin;

public class AdminService {
	
	public static void addAdmin(Admin admin){
		System.out.println("*************************************************************");
		System.out.println("I am at AdminService, addAdmin");
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
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Admin Successfully added!");
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("AdminService, addAdmin complete!");
		System.out.println("*************************************************************");
	}
	
public static ArrayList<Admin> getAdminList() {
		System.out.println("*************************************************************");
		System.out.println("I am at AdminService, getAdminList");
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			System.out.println("Query is: " + stmt);
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
		System.out.println("AdminService, getAdminList complete!");
		System.out.println("*************************************************************");
		return adminList;
	}
	
	public static Admin getAdmin(String email) {
		System.out.println("*************************************************************");
		System.out.println("I am at AdminService, getAdmin");
		Admin admin = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			System.out.println("Query is: " + stmt);
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
		System.out.println("AdminService, getAdmin complete!");
		System.out.println("*************************************************************");
		return admin;
	}

	public static void updateAdmin(Admin newAdmin) {
		System.out.println("*************************************************************");
		System.out.println("I am at AdminService, updateAdmin");
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
			System.out.println("Query is: " + query);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("AdminService, updateAdmin complete!");
		System.out.println("*************************************************************");
	}
	
	public static void deleteAdmin(String email) {
		System.out.println("*************************************************************");
		System.out.println("I am at AdminService, deleteAdmin");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "DELETE FROM admin WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			stmt.executeUpdate();
			System.out.println("Query is: " + stmt);
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("AdminService, deleteAdmin complete!");
		System.out.println("*************************************************************");
	}
	
	
	public static boolean checkLogin(String email, String hashedPass){
		System.out.println("*************************************************************");
		System.out.println("I am at AdminService, checkLogin");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			String user, pass;
			
			while(rs.next()){
				user = rs.getString("email");
				pass = rs.getString("hashedpassword");
				
				if(user.equals(email) && pass.equals(hashedPass)){
					System.out.println("AdminService, checkLogin complete! Return value true");
					System.out.println("*************************************************************");
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("AdminService, checkLogin complete! Return value false");
		System.out.println("*************************************************************");
		return false;
	}
	
	public static boolean checkUser(String email){
		System.out.println("*************************************************************");
		System.out.println("I am at AdminService, checkUser");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM admin");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			String user;
			
			while(rs.next()){
				user = rs.getString("email");
				
				if(user.equals(email)){
					System.out.println("AdminService, checkUser complete! Return value true");
					System.out.println("*************************************************************");
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("AdminService, checkUser complete! Return value false");
		System.out.println("*************************************************************");
		return false;
	}
}
