package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Book;
import beans.OrderList;

public class OrderListService {

	public static void addOrderList(OrderList orderlist) {
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO orderlist (orderid, bookid, quantity) VALUES (?,?,?)");
			
			stmt.setInt(1, orderlist.getOrderID());
			stmt.setInt(2, orderlist.getBookID());
			stmt.setInt(3, orderlist.getQuantity());
			
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Order Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the order to the database");
		}
	}
	public static ArrayList<OrderList> getOrderDetails(int orderID) {
		System.out.println("*************************************************************");
		System.out.println("I am at OrderList Service, getOrderDetails");
		
		ArrayList<OrderList> orderlist = new ArrayList<OrderList>();
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM orderlist WHERE orderid = ?");
			
			stmt.setInt(1, orderID);
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				OrderList ol = new OrderList(rs.getInt("orderid"),
										  rs.getInt("bookid"),
										  rs.getInt("quantity"));
				orderlist.add(ol);
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("BookService, getBook complete!");
		System.out.println("*************************************************************");
		return orderlist;
	}
}
