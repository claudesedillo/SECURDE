package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Publisher;

public class PublisherService {
	
	public static void addPublisher(Publisher publisher) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at PublisherService, addPublisher");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO publisher (publishername) " +
															"VALUES (?)");
			
			stmt.setString(1, publisher.getPublisherName());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Publisher Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the publisher to the database");
		}
		System.out.println("PublisherService, addPublisher complete!");
		System.out.println("*************************************************************");
	}
	
	public static String getPublisherName(int id) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at PublisherService, getPubliser");
		//System.out.println("Publisher ID: " + id);
		String publisherName = "";
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM publisher WHERE publisherid = ?");
			
			stmt.setInt(1, id);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				publisherName = rs.getString("publishername");
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("There was a problem retrieving the publisher from the database");
		}
		//System.out.println("PublisherService, getPublisher complete!");
		//System.out.println("*************************************************************");
		return publisherName;
	}
	
	public static boolean doesPublisherExist(String publisherName) {
		System.out.println("*************************************************************");
		System.out.println("I am at PublisherService, doesPublisherExist");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM publisher WHERE publishername = ?");
			
			stmt.setString(1, publisherName);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("There was a problem retrieving the publisher from the database");
		}
		System.out.println("*************************************************************");
		return false;
	}
	
	public static int getPublisherID(String publisherName) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at PublisherService, getPubliser");
		//System.out.println("Publisher ID: " + id);
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM publisher WHERE publishername = ?");
			
			stmt.setString(1, publisherName);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				int publisherID = rs.getInt("publisherid");
				return publisherID;
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("There was a problem retrieving the publisher from the database");
		}
		//System.out.println("PublisherService, getPublisher complete!");
		//System.out.println("*************************************************************");
		return 0;
	}
	
}
