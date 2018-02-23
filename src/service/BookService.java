package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Book;

public class BookService {
	
	public static void addBook(Book book) {
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO book (Title, ISBN, Genre, Format, Published, Price, StockLevel) " +
															"VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getIsbn());
			stmt.setString(3, book.getGenre());
			stmt.setString(4, book.getFormat());
			stmt.setDate(5, book.getSQLDate());
			stmt.setFloat(6, book.getPrice());
			stmt.setInt(7, book.getStock());
			stmt.executeUpdate();
			System.out.println("Book was successfully added to the database!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem adding the book to the database");
		}
	}
	
	public static ArrayList<Book> getBookList() {
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book");
			ResultSet rs = stmt.executeQuery();
		
			
			while(rs.next()) {
				Book book = new Book(rs.getInt("bookid"),
								    rs.getString("Title"),
									rs.getString("ISBN"),
									rs.getString("Genre"),
									rs.getString("Format"),
									rs.getFloat("Price"),
									rs.getInt("stocklevel"),
									rs.getDate("Published"),
									rs.getInt("authorID"),
									rs.getInt("publisherID"));
				book.toString();
				bookList.add(book);
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return bookList;
	}
	
	public static Book getBook(int id) {
		
		Book book = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book");
			ResultSet rs = stmt.executeQuery();
		
			
			while(rs.next()) {
				if(id == rs.getInt("bookid")){
					book = new Book(rs.getInt("bookid"),
						    rs.getString("Title"),
							rs.getString("ISBN"),
							rs.getString("Genre"),
							rs.getString("Format"),
							rs.getFloat("Price"),
							rs.getInt("stocklevel"),
							rs.getDate("Published"),
							rs.getInt("authorID"),
							rs.getInt("publisherID"));
				}
				
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return book;
	}
	
	public static void updateBooks(Book newBook) {
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "UPDATE book SET Title = ?, ISBN = ?, Genre = ?, Format = ?, Published = ?, Price = ?, stocklevel = ?, authorid = ?, publisherid = ? WHERE bookid = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, newBook.getTitle());
			stmt.setString(2, newBook.getIsbn());
			stmt.setString(3, newBook.getGenre());
			stmt.setString(4, newBook.getFormat());
			stmt.setDate(5, newBook.getSQLDate());
			stmt.setFloat(6, newBook.getPrice());
			stmt.setInt(7, newBook.getStock());
			stmt.setInt(8, newBook.getAuthorID());
			stmt.setInt(9, newBook.getPublisherID());
			stmt.setInt(10, newBook.getBookID());
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}