package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Author;
import beans.Book;
import beans.Customer;
import service.AuthorService;
import service.BookService;
import service.CustomerService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/addAuthor",
		   "/addPublisher",
		   "/addBook",
		   "/editConfirm",
		   "/editGet"})

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("btn-editProd"));
		Book oldBook = BookService.getBook(id);
		HttpSession session = request.getSession();
		session.setAttribute("bookid", id);
		request.setAttribute("bookid", session.getAttribute("bookid"));
		request.setAttribute("title", oldBook.getTitle());
		request.setAttribute("isbn", oldBook.getIsbn());
		request.setAttribute("genre", oldBook.getGenre());
		request.setAttribute("format", oldBook.getFormat());
		request.setAttribute("pub", oldBook.getSQLDate());
		request.setAttribute("price", oldBook.getPrice());
		request.setAttribute("stock", oldBook.getStock());
		request.setAttribute("authorID", oldBook.getAuthorID());
		request.setAttribute("publisherID", oldBook.getPublisherID());
		request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
	}
	
	private void editBookConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("bookid");
		String title = request.getParameter("title"),
			   isbn = request.getParameter("isbn"),
			   genre = request.getParameter("genre"),
			   format = request.getParameter("format");
		Date pub =  java.sql.Date.valueOf(request.getParameter("pub"));
		int authorID = Integer.parseInt(request.getParameter("")), publisherID = Integer.parseInt(request.getParameter(""));
		
		float price = Float.parseFloat(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		Book newBook = new Book(id, title, isbn, genre, format, price, stock, pub, authorID, publisherID);
		BookService.updateBooks(newBook);
		request.getRequestDispatcher("catalogTest.html").forward(request, response);
	}
	
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("******************************ADD Book doPost*****************************");
		System.out.println("Book Title: " + request.getParameter("bookTitle"));
		System.out.println("ISBN: " + request.getParameter("isbn"));
		System.out.println("Genre: " + request.getParameter("genre"));
		System.out.println("Format: " + request.getParameter("format"));
		System.out.println("Price: " + request.getParameter("price"));
		System.out.println("Date Published: " + request.getParameter("published"));
		System.out.println("Stock: " + request.getParameter("stock"));
		 
    	String title = request.getParameter("bookTitle");
    	String isbn = request.getParameter("isbn");
    	String genre = request.getParameter("genre");
    	String format = request.getParameter("format");
    	String published = request.getParameter("published");
    	float price = Float.parseFloat(request.getParameter("price"));
    	int stock = Integer.parseInt(request.getParameter("stock"));
    	
    	Book book = new Book(title, isbn, genre, format, price, stock);
    	book.setSQLDate(published);
    	
    	BookService.addBook(book);
    	
    }
    protected void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*****************************ADD AUTHOR doPost***************************");
		System.out.println("First name: " + request.getParameter("authorFirstName"));
		System.out.println("Last Name: " + request.getParameter("authorLastName"));
		
    	String authorFirstName = request.getParameter("authorFirstName");
    	String authorLastName = request.getParameter("authorLastName");
    	String name = authorFirstName + " " + authorLastName;
    	
    	Author author = new Author(name);
    	AuthorService.addAuthor(author);
    	
    	System.out.println("**************************************************************************");
    }
    
    protected void addPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	/*
    	System.out.println("*****************************ADD PUBLISHER doPost************************");
		System.out.println("Publisher Name: " + request.getParameter("publisherName"));
		
    	String publisherName = request.getParameter("publisherName");
    	
    	Publisher publisher = new Publisher(publisherName);
    	
    	PublisherService.addPublisher(publisher);
    	System.out.println("**************************************************************************");
    	*/
    	System.out.println("*****************************CUSTOMER TEST doPost*************************");
    	Customer cust = new Customer();
    	cust.setEmail("gary_non@yahoo.com");
    	cust.setHashedpassword("bruh1234#");
    	
    	CustomerService.addCustomer(cust);
    	System.out.println("**************************************************************************");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("I am at adminServlet at doPost. Servlet path is " + request.getServletPath());
			switch(request.getServletPath()) {
				case "/addAuthor": System.out.println("I am at adminServlet, addAuthor case");
								  addAuthor(request, response);
								  break;
				case "/addPublisher": System.out.println("I am at adminServlet, addPublisher case");
								  addPublisher(request, response);
								  break;
				case "/addBook": System.out.println("I am at adminServlet, addBook case");
								 addBook(request, response);
								 break;
				case "/editGet": System.out.println("I am at adminServlet, editGet case");
								editBook(request, response);
								break;
				case "/editConfirm": System.out.println("I am at adminServlet, editGet case");
								editBookConfirm(request, response);	
								break;				 					 
			}
	}
}
