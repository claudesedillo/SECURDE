package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Author;

public class AuthorService {

	public static void addAuthor(Author author) {
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO authors (firstname, lastname) " +
															"VALUES (?,?)");
			
			stmt.setString(1, author.getFirstName());
			stmt.setString(2, author.getLastName());
			
			stmt.executeUpdate();
			System.out.println("Author Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the author to the database");
		}
	}
	
	public static String getAuthor(int id) {
		String authorName = "";
		System.out.println("Author ID: " + id);
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM authors WHERE authorid = ?");
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				authorName = rs.getString("name");
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem the author to the database");
		}
		return authorName;
	}
}
