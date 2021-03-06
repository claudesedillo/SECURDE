package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.owasp.encoder.Encode;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncryptionException;

import beans.Customer;

public class CustomerService {

	public static void addCustomer(Customer customer){
		System.out.println("*************************************************************");
		System.out.println("I am at CustomerService, addCustomer");
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
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Customer Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the customer to the database");
		}
		System.out.println("CustomerService, getAuthorName complete!");
		System.out.println("*************************************************************");
	}
	
	public static boolean doesCustomerExist(String email) {
		System.out.println("*************************************************************");
		System.out.println("I am at CustomerService, doesCustomerExist");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM customers WHERE email = ?");
			
			stmt.setString(1, email);
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			String user;
			
			if(rs.next()){
				user = rs.getString("email");
				
				if(user.equals(email)){
					System.out.println("CustomerService, doesCustomerExist complete! Return Value true");
					System.out.println("*************************************************************");
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("CustomerService, doesCustomerExist complete! Return Value false");
		System.out.println("*************************************************************");
		return false;
	}
	
	public static boolean checkLogin(String email, String hashedPass){
		System.out.println("*************************************************************");
		System.out.println("I am at CustomerService, checkLogin");
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM customers");
			
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			String user, pass;
			
			while(rs.next()){
				user = rs.getString("email");
				pass = rs.getString("hashedpassword");
				
				if(user.equals(email)){
					try {
						pass = ESAPI.encryptor().decrypt(pass);
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(pass.equals(hashedPass)){
						System.out.println("CustomerService, checkLogin complete! Return Value true");
						System.out.println("*************************************************************");
						return true;
					}
					
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("CustomerService, checkLogin complete! Return Value false");
		System.out.println("*************************************************************");
		return false;
	}
	
	public static boolean checkUser(String email){
		System.out.println("*************************************************************");
		System.out.println("I am at CustomerService, checkUser");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM customers");
			
			ResultSet rs = stmt.executeQuery();
			System.out.println("Query is: " + stmt);
			String user;
			
			while(rs.next()){
				user = rs.getString("email");
				
				if(user.equals(email)){
					System.out.println("CustomerService, checkUser complete! Return Value true");
					System.out.println("*************************************************************");
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("CustomerService, checkUser complete! Return Value false");
		System.out.println("*************************************************************");
		return false;
	}
	
	public static Customer getCustomer(String email) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at BookService, getBook");
		
		Customer cust = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM customers");
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				if(rs.getString("email").equals(email)){
					cust = new Customer(rs.getString("email"),
										rs.getString("hashedpassword"),
										Encode.forHtml(rs.getString("firstname")),
										Encode.forHtml(rs.getString("lastname")),
										rs.getString("securityquestion"),
										Encode.forHtml(rs.getString("securityanswer")),
										Encode.forHtml(rs.getString("streetaddress")),
										rs.getInt("postalcode"),
										Encode.forHtml(rs.getString("city")),
										Encode.forHtml(rs.getString("province")),
										Encode.forHtml(rs.getString("phonenumber")));
				}
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		//System.out.println("BookService, getBook complete!");
		//System.out.println("*************************************************************");
		return cust;
	}
	
	public static boolean updateCustomerDetails(Customer newCustomer) {
		System.out.println("*************************************************************");
		System.out.println("I am at CustomerService, updateCustomerDetails");
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("UPDATE customers SET firstname = ?, lastname = ?, streetaddress = ?, city = ?, " +
															"province = ?, phonenumber = ?, postalcode = ? WHERE email = ?");
			stmt.setString(1, newCustomer.getFirstname());
			stmt.setString(2, newCustomer.getLastname());
			stmt.setString(3, newCustomer.getStreetaddress());
			stmt.setString(4, newCustomer.getCity());
			stmt.setString(5, newCustomer.getProvince());
			stmt.setString(6, newCustomer.getPhonenumber());
			stmt.setInt(7, newCustomer.getPostalcode());
			stmt.setString(8, newCustomer.getEmail());
			
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println("CustomerService, updateCustomer complete!");
		System.out.println("*************************************************************");
		return true;
	}
	public static void updateCustomer(Customer newCust){
		System.out.println("*************************************************************");
		System.out.println("I am at CustomerService, updateCustomer");
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "UPDATE customers SET email = ?, hashedpassword = ?, firstname = ?, lastname = ?, securityquestion = ?, "
					+ "securityanswer = ?, streetaddress = ?, postalcode = ?, city = ?, province = ?, phonenumber = ? WHERE email = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, newCust.getEmail());
			stmt.setString(2, newCust.getHashedpassword());
			stmt.setString(3, newCust.getFirstname());
			stmt.setString(4, newCust.getLastname());
			stmt.setString(5, newCust.getSecurityquestion());
			stmt.setString(6, newCust.getSecurityanswer());
			stmt.setString(7, newCust.getStreetaddress());
			stmt.setInt(8, newCust.getPostalcode());
			stmt.setString(9, newCust.getCity());
			stmt.setString(10, newCust.getProvince());
			stmt.setString(11, newCust.getPhonenumber());
			stmt.setString(12, newCust.getEmail());
			
			System.out.println("Query is: " + query);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("CustomerService, updateCustomer complete!");
		System.out.println("*************************************************************");
	}
}
