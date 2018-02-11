package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Author;
import beans.Book;
import beans.Customer;
import beans.Publisher;
import service.AuthorService;
import service.BookService;
import service.CustomerService;
import service.PublisherService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/addAuthor",
						   "/addPublisher",
						   "/addBook"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
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
    	
    	Author author = new Author(authorFirstName, authorLastName);
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
				case "/addAuthor": System.out.println("I am at addAuthor case");
								  addAuthor(request, response);
								  break;
				case "/addPublisher": System.out.println("I am at addPublisher case");
								  addPublisher(request, response);
								  break;
				case "/addBook": System.out.println("I am at addBook case");
								 addBook(request, response);
								 break;
			}
	}
}
