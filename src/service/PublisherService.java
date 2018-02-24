package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Publisher;

public class PublisherService {
	
	public static void addPublisher(Publisher publisher) {
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO publisher (publishername) " +
															"VALUES (?)");
			
			stmt.setString(1, publisher.getPublisherName());
			stmt.executeUpdate();
			System.out.println("Publisher Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the publisher to the database");
		}
	}
	
	public static String getPublisher(int id) {
		System.out.println("Publisher ID: " + id);
		String publisherName = "";
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM publisher WHERE publisherid = ?");
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				publisherName = rs.getString("publishername");
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem retrieving the publisher from the database");
		}
		return publisherName;
	}
}
