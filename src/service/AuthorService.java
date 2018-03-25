package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Author;
import beans.Book;

public class AuthorService {

	public static List<Integer> findAuthor(String authorName){
		List<Integer> authorIDs = new ArrayList<Integer>();
		//System.out.println("*************************************************************");
		//System.out.println("I am at AuthorService -> findAuthor");
		//System.out.println("Author Name is " + authorName);
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM authors WHERE name LIKE ?");

			stmt.setString(1, "%" + authorName + "%");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				System.out.println("author found!");
				Author author = new Author(rs.getInt("authorid"),
										 rs.getString("name"));
				authorIDs.add(author.getAuthorID());
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem searching the author");
		}
		//System.out.println("AuthorService, findAuthor complete!");
		//System.out.println("*************************************************************");
		
		return authorIDs;
	}
	
	public static void addAuthor(Author author) {
		System.out.println("*************************************************************");
		System.out.println("I am at AuthorService, Add author");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO authors (name) " +
															"VALUES (?)");
			
			stmt.setString(1, author.getName());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			System.out.println("Author Successfully added!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the author to the database");
			System.out.println("*************************************************************");
		}
		System.out.println("AuthorService, addAuthor complete!");
		System.out.println("*************************************************************");
	}
	
	public static String getAuthorName(int id) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at AuthorService, getAuthorName");
		String authorName = "";
		//System.out.println("Author ID: " + id);
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM authors WHERE authorid = ?");
			
			stmt.setInt(1, id);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				authorName = rs.getString("name");
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem getting the author from the database");
		}
		//System.out.println("AuthorService, getAuthorName complete!");
		//System.out.println("*************************************************************");
		return authorName;
	}
	
	public static boolean doesAuthorExist(String authorName) {
		System.out.println("*************************************************************");
		System.out.println("I am at AuthorService, doesAuthorExist");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM authors WHERE name = ?");
			
			stmt.setString(1, authorName);
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
	
	public static int getAuthorID(String authorName) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at PublisherService, getPubliser");
		//System.out.println("Publisher ID: " + id);
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM authors WHERE name = ?");
			
			stmt.setString(1, authorName);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				int authorID = rs.getInt("authorid");
				return authorID;
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