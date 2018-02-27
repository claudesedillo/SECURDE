package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Author;
import beans.Order;

public class OrderService {

	
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
