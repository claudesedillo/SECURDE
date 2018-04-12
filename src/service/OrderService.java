package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.owasp.encoder.Encode;

import beans.Book;
import beans.Order;

public class OrderService {

	public static Order getOrder(int orderID) {
		Order order = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM orders WHERE orderid = ?");
			
			stmt.setInt(1, orderID);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				order = new Order(rs.getInt("orderid"),
						Encode.forHtml(rs.getString("email")),
						Encode.forHtml(rs.getString("firstName")),
						Encode.forHtml(rs.getString("lastName")),
						Encode.forHtml(rs.getString("streetaddress")),
						Encode.forHtml(rs.getString("city")),
						Encode.forHtml(rs.getString("province")),
						rs.getInt("postalcode"),
						Encode.forHtml(rs.getString("phonenumber")),
						rs.getInt("total"));
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		//System.out.println("BookService, getBook complete!");
		//System.out.println("*************************************************************");
		return order;
	}
	
	public static ArrayList<Order> getUserOrder(String email) {
		System.out.println("*************************************************************");
		System.out.println("I am at OrderService -> getAllOrders");
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM orders WHERE email = ?");

			stmt.setString(1, email);
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Order order = new Order(rs.getInt("orderid"),
										 Encode.forHtml(rs.getString("email")),
										 rs.getDate("orderDate"),
										 rs.getInt("total"),
										 Encode.forHtml(rs.getString("streetAddress")),
										 Encode.forHtml(rs.getString("city")),
										 Encode.forHtml(rs.getString("province")),
										 rs.getInt("postalcode"),
										 Encode.forHtml(rs.getString("phonenumber")),
										 Encode.forHtml(rs.getString("firstname")),
										 Encode.forHtml(rs.getString("lastname")));
				orderList.add(order);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem getting the order list");
		}
		System.out.println("OrderService, getAllOrders complete!");
		System.out.println("*************************************************************");
		return orderList;
	}
	
	public static int getLatestOrder() {
		int orderID = 0;
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT MAX(orderid) AS 'orderid' FROM orders");
			
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				orderID = rs.getInt("orderid");
			}
			conn.close();
		}catch(Exception e) {
				e.printStackTrace();
				System.out.println("There was a problem adding the order to the database");
			}
		return orderID;
	}
	public static void addOrder(Order order){
		System.out.println("*************************************************************");
		System.out.println("I am at OrderService, addOrder");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO orders (email, orderdate, firstname, lastname, streetaddress, city, province, postalcode, phonenumber, total) VALUES (?, NOW(), ?,?,?,?,?,?,?,?)");
			System.out.println("At addOrder: Name is " + order.getFirstName() + order.getLastName());
			stmt.setString(1, order.getEmail());
			stmt.setString(2, order.getFirstName());
			stmt.setString(3, order.getLastName());
			stmt.setString(4, order.getStreetAddress());
			stmt.setString(5, order.getCity());
			stmt.setString(6,  order.getProvince());
			stmt.setInt(7,  order.getPostalCode());
			stmt.setString(8,  order.getPhoneNumber());
			stmt.setInt(9, order.getTotal());
			
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Order Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the order to the database");
		}
		System.out.println("OrderService, addOrder complete!");
		System.out.println("*************************************************************");
	}
	
	public static ArrayList<Order> getAllOrders() {
		System.out.println("*************************************************************");
		System.out.println("I am at OrderService -> getAllOrders");
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM orders");


			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Order order = new Order(rs.getInt("orderid"),
										 Encode.forHtml(rs.getString("email")),
										 rs.getDate("orderDate"),
										 rs.getInt("total"),
										 Encode.forHtml(rs.getString("streetAddress")),
										 Encode.forHtml(rs.getString("city")),
										 Encode.forHtml(rs.getString("province")),
										 rs.getInt("postalcode"),
										 Encode.forHtml(rs.getString("phonenumber")),
										 Encode.forHtml(rs.getString("firstname")),
										 Encode.forHtml(rs.getString("lastname")));
				orderList.add(order);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem getting the order list");
		}
		System.out.println("OrderService, getAllOrders complete!");
		System.out.println("*************************************************************");
		return orderList;
	}
}
