package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Customer;

public class CustomerService {

	public static void addCustomer(Customer customer){
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO customers (email, hashedpassword, firstname, lastname, "
														  + "securityquestion, securityanswer, streetaddress, postalcode, city, "
														  + "province, phonenumber) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setString(1, customer.getEmail());
			stmt.setString(2, customer.getHashedpassword());
			stmt.setString(3, customer.getFirstname());
			stmt.setString(4, customer.getLastname());
			stmt.setString(5, customer.getSecurityquestion());
			stmt.setString(6, customer.getSecurityanswer());
			stmt.setString(7, customer.getStreetaddress());
			stmt.setInt(8, customer.getPostalcode());
			stmt.setString(9, customer.getCity());
			stmt.setString(10, customer.getProvince());
			stmt.setString(11, customer.getPhonenumber());
			
			stmt.executeUpdate();
			System.out.println("Customer Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the customer to the database");
		}
	}
	
	public static boolean checkLogin(String email, String hashedPass){
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM customers");
			
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
	
}
