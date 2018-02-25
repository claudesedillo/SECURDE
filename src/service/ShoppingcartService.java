package service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import beans.Shoppingcart;

public class ShoppingcartService {
	
	public static void addShoppingcart(Shoppingcart sc){
		System.out.println("*************************************************************");
		System.out.println("I am at ShoppingcartService, addAdmin");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO shoppingcart (bookid, price, email, quantity) VALUES (?,?,?,?,?)");
			stmt.setInt(1, sc.getBookid());
			stmt.setFloat(2, sc.getPrice());
			stmt.setString(3, sc.getEmail());
			stmt.setInt(4, sc.getQuantity());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Admin Successfully added!");
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("Shoppingcart, addAdmin complete!");
		System.out.println("*************************************************************");
	}
}
