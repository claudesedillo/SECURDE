package service;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the publisher to the database");
		}
	}
}
