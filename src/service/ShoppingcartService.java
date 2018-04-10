package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.owasp.encoder.Encode;

import beans.Admin;
import beans.Shoppingcart;

public class ShoppingcartService {
	
	public static void addShoppingcart(Shoppingcart sc){
		System.out.println("*************************************************************");
		System.out.println("I am at ShoppingcartService, add");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO shoppingcart (bookid, price, email, quantity) VALUES (?,?,?,?)");
			stmt.setInt(1, sc.getBookid());
			stmt.setFloat(2, sc.getPrice());
			stmt.setString(3, sc.getEmail());
			stmt.setInt(4, sc.getQuantity());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("sc Successfully added!");
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("Shoppingcart, addcomplete!");
		System.out.println("*************************************************************");
	}
	
	public static void deleteCart(int bookid, String email) {
		System.out.println("*************************************************************");
		System.out.println("I am at ScService, deleteCart");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "DELETE FROM shoppingcart WHERE email = ? and bookid = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setInt(2, bookid);
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("ScService, deleteCart complete!");
		System.out.println("*************************************************************");
	}
	
	public static void updateShoppincart(Shoppingcart newSc) {
		System.out.println("*************************************************************");
		System.out.println("I am at ShoppingcartService, update");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "UPDATE shoppingcart SET bookid = ?, price = ?, email = ?, quantity = ? WHERE email = ? and bookid = ?";	
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, newSc.getBookid());
			stmt.setFloat(2, newSc.getPrice());
			stmt.setString(3, newSc.getEmail());
			stmt.setInt(4, newSc.getQuantity());
			stmt.setString(5, newSc.getEmail());
			stmt.setInt(6, newSc.getBookid());
			System.out.println("Query is: " + query);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("ShoppingcartService, update complete!");
		System.out.println("*************************************************************");
	}
	
	public static ArrayList<Shoppingcart> getShoppingCartList(String email){
		System.out.println("*************************************************************");
		System.out.println("I am at ShoppingCartService, getShoppingCart");
		ArrayList<Shoppingcart> cartList = new ArrayList<Shoppingcart>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM shoppingcart");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
		
			
			while(rs.next()) {
				if(rs.getString("email").equals(email)){
					Shoppingcart sc = new Shoppingcart(rs.getInt("bookid"), rs.getFloat("price"), Encode.forHtml(rs.getString("email")), rs.getInt("quantity"));
					cartList.add(sc);
				}
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("AdminService, getAdminList complete!");
		System.out.println("*************************************************************");
		return cartList;
	}
}
