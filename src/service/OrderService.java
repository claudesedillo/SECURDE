package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Order;

public class OrderService {

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
	public static void addCustomer(Order order){
		System.out.println("*************************************************************");
		System.out.println("I am at OrderService, addOrder");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO orders (email, firstname, lastname, streetaddress, city, total) VALUES (?,?,?,?,?,?)");
			
			stmt.setString(1, order.getEmail());
			stmt.setString(2, order.getFirstName());
			stmt.setString(3, order.getLastName());
			stmt.setString(4, order.getStreetAddress());
			stmt.setString(5, order.getCity());
			stmt.setInt(6, order.getTotal());
			
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
										 rs.getString("email"),
										 rs.getDate("orderDate"),
										 rs.getInt("total"),
										 rs.getString("streetAddress"),
										 rs.getString("city"),
										 rs.getString("province"),
										 rs.getInt("postalcode"),
										 rs.getString("phonenumber"));
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
