package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.owasp.encoder.Encode;

import beans.Book;

public class BookService {
	
	public static List<Book> filterByGenre(String genre){
		//System.out.println("*************************************************************");
		//System.out.println("I am at BookService, filterByGenre");
		
		List<Book> bookList = new ArrayList<Book>();
		//System.out.println("I am at BookService -> searchBook");
		//System.out.println("Genre is " + genre);
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book WHERE genre = ?");
			stmt.setString(1, genre);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				//System.out.println("book found!");
				Book book = new Book(rs.getInt("bookid"),
									Encode.forHtml(rs.getString("Title")),
									Encode.forHtml(rs.getString("ISBN")),
									Encode.forHtml(rs.getString("Genre")),
									Encode.forHtml(rs.getString("Format")),
									rs.getFloat("Price"),
									rs.getInt("stocklevel"),
									rs.getDate("Published"),
									rs.getInt("authorID"),
									rs.getInt("publisherID"));
				bookList.add(book);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("There was a problem searching the book");
		}
		//System.out.println("BookService, filterByGenre complete!");
		//System.out.println("*************************************************************");
		return bookList;
	}
	
	
	public static List<Book> searchBook(String bookTitle) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at BookService, searchBook");
		List<Book> bookList = new ArrayList<Book>();
		//System.out.println("I am at BookService -> searchBook");
		//System.out.println("Book title is " + bookTitle);
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book WHERE title LIKE ?");

			stmt.setString(1, "%" + bookTitle + "%");
			System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				//System.out.println("book found!");
				Book book = new Book(rs.getInt("bookid"),
									Encode.forHtml(rs.getString("Title")),
									Encode.forHtml(rs.getString("ISBN")),
									Encode.forHtml(rs.getString("Genre")),
									Encode.forHtml(rs.getString("Format")),
									rs.getFloat("Price"),
									rs.getInt("stocklevel"),
									rs.getDate("Published"),
									rs.getInt("authorID"),
									rs.getInt("publisherID"));
				bookList.add(book);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("There was a problem searching the book");
		}
		//System.out.println("BookService, searchBook complete!");
		//System.out.println("*************************************************************");
		return bookList;
	}
	
	public static void addBook(Book book) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at BookService, addBook");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO book (Title, ISBN, Genre, Format, Published, Price, StockLevel, authorID, publisherID) " +
															"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getIsbn());
			stmt.setString(3, book.getGenre());
			stmt.setString(4, book.getFormat());
			stmt.setDate(5, book.getSQLDate());
			stmt.setFloat(6, book.getPrice());
			stmt.setInt(7, book.getStock());
			stmt.setInt(8, book.getAuthorID());
			stmt.setInt(9, book.getPublisherID());
			//System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			//System.out.println("Book was successfully added to the database!");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("There was a problem adding the book to the database");
		}
		//System.out.println("BookService, addBook complete!");
		//System.out.println("*************************************************************");
	}
	
	public static ArrayList<Book> getBookList() {
		//System.out.println("*************************************************************");
		//System.out.println("I am at BookService, getBookList");
		ArrayList<Book> bookList = new ArrayList<Book>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book");
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt("bookid"),
									Encode.forHtml(rs.getString("Title")),
									Encode.forHtml(rs.getString("ISBN")),
									Encode.forHtml(rs.getString("Genre")),
									Encode.forHtml(rs.getString("Format")),
									rs.getFloat("Price"),
									rs.getInt("stocklevel"),
									rs.getDate("Published"),
									rs.getInt("authorID"),
									rs.getInt("publisherID"));
				bookList.add(book);
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		//System.out.println("BookService, getBookList complete!");
		//System.out.println("*************************************************************");
		return bookList;
	}
	
	public static Book getBook(int id) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at BookService, getBook");
		
		Book book = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book WHERE bookid = ?");
			
			stmt.setInt(1, id);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				book = new Book(rs.getInt("bookid"),
						Encode.forHtml(rs.getString("Title")),
						Encode.forHtml(rs.getString("ISBN")),
						Encode.forHtml(rs.getString("Genre")),
						Encode.forHtml(rs.getString("Format")),
						rs.getFloat("Price"),
						rs.getInt("stocklevel"),
						rs.getDate("Published"),
						rs.getInt("authorID"),
						rs.getInt("publisherID"));
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		//System.out.println("BookService, getBook complete!");
		//System.out.println("*************************************************************");
		return book;
	}
	
	public static void deleteBook(int bookid) {
		System.out.println("*************************************************************");
		System.out.println("I am at BookService, deleteBook");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "DELETE FROM book WHERE bookid = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, bookid);
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("BookService, deleteBook complete!");
		System.out.println("*************************************************************");
	}
	
	public static void updateBooks(Book newBook) {
		System.out.println("*************************************************************");
		System.out.println("I am at BookService, updateBooks");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement("UPDATE book SET Title = ?, " +
															"authorid = ?, " +
															"publisherid = ?, " +
															"ISBN = ?, " +
															"Genre = ?, " +
															"Format = ?, " +
															"Published = ?, " +
															"Price = ?, " +
															"stocklevel = ? " +
															"WHERE bookid = ?");
			
			stmt.setString(1, newBook.getTitle());
			stmt.setInt(2, newBook.getAuthorID());
			stmt.setInt(3, newBook.getPublisherID());
			stmt.setString(4, newBook.getIsbn());
			stmt.setString(5, newBook.getGenre());
			stmt.setString(6, newBook.getFormat());
			stmt.setDate(7, newBook.getSQLDate());
			stmt.setFloat(8, newBook.getPrice());
			stmt.setInt(9, newBook.getStock());
			stmt.setInt(10, newBook.getBookID());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("BookService, updateBooks complete!");
		System.out.println("*************************************************************");
	}
	
	public static void updateBookStock(Book newBook, int numBought) {
		System.out.println("*************************************************************");
		System.out.println("I am at BookService, updateBooks");
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			String query = "UPDATE book SET stocklevel = ? WHERE bookid = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, newBook.getStock() - numBought);
			stmt.setInt(1, newBook.getBookID());
			System.out.println("Query is: " + stmt);
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("BookService, updateBooks complete!");
		System.out.println("*************************************************************");
	}
	
	public static String getBookTitle(int bookID) {
		String title = "";
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book WHERE bookid = ?");
			
			stmt.setInt(1, bookID);
			//System.out.println("Query is: " + stmt);
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()) {
				title = rs.getString("Title");
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		//System.out.println("BookService, getBook complete!");
		//System.out.println("*************************************************************");
		return title;
	}
	public static List<Book> getBookByAuthorID(List<Integer> authorIDs) {
		//System.out.println("*************************************************************");
		//System.out.println("I am at BookService, getBookByAuthorID (searching books by author)");
		List<Book> bookList = new ArrayList<Book>();
		
		//System.out.println("I am at BookService -> getBookByAuthorID");
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			for(int i: authorIDs) {
				PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM book WHERE authorid = ?");
	
				stmt.setInt(1, i);
				//System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				//System.out.println("Query is: " + stmt);
				while(rs.next()) {
					//System.out.println("book found!");
					Book book = new Book(rs.getInt("bookid"),
										Encode.forHtml(rs.getString("Title")),
										Encode.forHtml(rs.getString("ISBN")),
										Encode.forHtml(rs.getString("Genre")),
										Encode.forHtml(rs.getString("Format")),
										rs.getFloat("Price"),
										rs.getInt("stocklevel"),
										rs.getDate("Published"),
										rs.getInt("authorID"),
										rs.getInt("publisherID"));
					bookList.add(book);
				}
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("There was a problem searching the book");
		}
		//System.out.println("BookService, getBookByAuthorID (searching books by author) complete!");
		//System.out.println("*************************************************************");
		return bookList;
	}
}