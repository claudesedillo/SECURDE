package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Admin;
import beans.Author;
import beans.Book;
import beans.Customer;
import beans.Publisher;
import service.AdminService;
import service.AuthorService;
import service.BookService;
import service.CustomerService;
import service.PublisherService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/addAuthor",
		   "/addPublisher",
		   "/addBook",
		   "/editConfirm",
		   "/editGet",
		   "/getAdminList",
		   "/editAdminGet",
		   "/editAdminConfirm"})

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
		System.out.println("***************ADMIN SERVLET - EDIT BOOK***************");
		if(request.getParameter("btn-editProd") != null){
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
		else{
			int id = Integer.valueOf(request.getParameter("btn-deleteProd"));
			BookService.deleteBook(id);
			System.out.println("***************/ADMIN SERVLET - EDIT BOOK/***************");
			request.getRequestDispatcher("catalogTest.html").forward(request, response);
		}
	}
	
	private void editBookConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************ADMIN SERVLET - EDIT BOOK CONFIRM***************");
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("bookid");
		String title = request.getParameter("title"),
			   isbn = request.getParameter("isbn"),
			   genre = request.getParameter("genre"),
			   format = request.getParameter("format");
		Date pub =  java.sql.Date.valueOf(request.getParameter("pub"));
		int authorID = Integer.parseInt(request.getParameter("authorID")), 
			publisherID = Integer.parseInt(request.getParameter("publisherID"));
		
		float price = Float.parseFloat(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		Book newBook = new Book(id, title, isbn, genre, format, price, stock, pub, authorID, publisherID);
		BookService.updateBooks(newBook);
		System.out.println("***************/ADMIN SERVLET - EDIT BOOK CONFIRM/***************");
		request.getRequestDispatcher("catalogTest.html").forward(request, response);
	}
	
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - ADD BOOK***************");
		
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
    	System.out.println("***************/ADMIN SERVLET - ADD BOOK/***************");
    	
    }
    protected void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - ADD AUTHOR***************");
		System.out.println("First name: " + request.getParameter("authorFirstName"));
		System.out.println("Last Name: " + request.getParameter("authorLastName"));
		
    	String authorFirstName = request.getParameter("authorFirstName");
    	String authorLastName = request.getParameter("authorLastName");
    	String name = authorFirstName + " " + authorLastName;
    	
    	Author author = new Author(name);
    	AuthorService.addAuthor(author);
    	System.out.println("***************/ADMIN SERVLET - ADD AUTHOR/***************");
    }
    
    protected void addPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	System.out.println("***************ADMIN SERVLET - ADD PUBLISER***************");
    	System.out.println("*****************************ADD PUBLISHER doPost************************");
		System.out.println("Publisher Name: " + request.getParameter("publisherName"));
		
    	String publisherName = request.getParameter("publisherName");
    	
    	Publisher publisher = new Publisher(publisherName);
    	
    	PublisherService.addPublisher(publisher);
		System.out.println("***************ADMIN SERVLET - ADD PUBLISHER***************");
    }
    
    private void getAdminList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************/ADMIN SERVLET - GET ADMIN LIST/***************");
		
		ArrayList<Admin> adminList = AdminService.getAdminList();
		String htmlBookList = "";
		System.out.println("gt in");
		for(Admin a: adminList) {
			htmlBookList += "<form action=\"editAdminGet\" method=\"post\">" + 
							"<div class = \"Div\"> <br>" + 
							"Email: " + a.getEmail() + " <br> " +
							"First Name: " + a.getFirstname() + " <br> " +
							"Last Name: " + a.getLastname() + " <br> " +
							"Role: " + a.getRole() + "<br>" +
							"<Button type= \"submit\" name = \"btn-editProd\" value =" + a.getEmail()  + "> EDIT </button>" +
							"<Button type= \"submit\" name = \"btn-deleteProd\" value =" + a.getEmail()  + "> DELETE </button>" +
							"</form>";
		}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	    
	    System.out.println("***************/ADMIN SERVLET - GET ADMIN LIST/***************");
	}
    
    private void editAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************ADMIN SERVLET - EDIT ADMIN***************");
		if(request.getParameter("btn-editProd") != null){
			String email = request.getParameter("btn-editProd");
			Admin oldAdmin = AdminService.getAdmin(email);
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			request.setAttribute("email", session.getAttribute("email"));
			request.setAttribute("pass", oldAdmin.getHashedpassword());
			request.setAttribute("first", oldAdmin.getFirstname());
			request.setAttribute("last", oldAdmin.getLastname());
			request.setAttribute("role", oldAdmin.getRole());
			request.getRequestDispatcher("updateAdmin.jsp").forward(request, response);
		}
		else{
			
			String email = request.getParameter("btn-deleteProd");
			AdminService.deleteAdmin(email);
			request.getRequestDispatcher("adminList.html").forward(request, response);
			
		}
		System.out.println("***************/ADMIN SERVLET - EDIT ADMIN/***************");	
	}
    
    private void editAdminConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - EDIT ADMIN CONFIRM***************");
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email"),
			   pass = request.getParameter("pass"),
			   first = request.getParameter("first"),
			   last = request.getParameter("last"),
			   role = request.getParameter("role");
		Admin admin = new Admin(email, pass, first, last, role);
		AdminService.updateAdmin(admin);
		request.getRequestDispatcher("adminList.html").forward(request, response);
		System.out.println("***************/ADMIN SERVLET - EDIT ADMIN CONFIRM/***************");	
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
				case "/getAdminList" : System.out.println("I am at adminServlet, getAdminList case");
								getAdminList(request, response);	
								break;
				case "/editAdminGet" : System.out.println("I am at adminServlet, editAdminGet case");
								editAdmin(request, response);	
								break;
				case "/editAdminConfirm" : System.out.println("I am at adminServlet, editAdminConfirm");
								editAdminConfirm(request, response);	
								break;
			}
	}
}
