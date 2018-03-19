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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Admin;
import beans.Author;
import beans.Book;
import beans.Order;
import beans.OrderList;
import beans.Publisher;
import service.AdminService;
import service.AuthorService;
import service.BookService;
import service.OrderListService;
import service.OrderService;
import service.PublisherService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/addAuthor",
		   "/getOrders",
		   "/getOrderDetails",
		   "/getInventory",
		   "/addPublisher",
		   "/addBook",
		   "/editConfirm",
		   "/editGet",
		   "/getAdminList",
		   "/editAdminGet",
		   "/editAdminConfirm",
		   "/getBookDetails",
		   "/getAuthorName",
		   "/getPublisherName"})

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void getOrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - GET ORDER DETAILS***************");
    	System.out.println("Order id is: " + request.getParameter("orderID"));
    	ArrayList<OrderList> orderlist = OrderListService.getOrderDetails(Integer.parseInt(request.getParameter("orderID")));
    	String htmlOrderDetails = "";
    	
    	for(OrderList o: orderlist) {
    		htmlOrderDetails += "<div class = \"order\"> " +
    							"<p> Book Title: " + BookService.getBook(o.getBookID()) + "		Quantity: " + o.getQuantity() + "</p>";
    	}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlOrderDetails);
    	System.out.println("***************/ADMIN SERVLET - GET ORDER DETAILS/***************");
    }
    
	private void getInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************ADMIN - GET INVENTORY***************");
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		
		for(Book b: bookList) {
			String authorName;
			
			authorName = AuthorService.getAuthorName(b.getAuthorID());
			//System.out.println("Author Name: " + authorName + " Publisher Name: " + publisherName);
			htmlBookList += "<tr>" + 
							"	<td><a data-toggle=\"modal\" data-target=\"#viewbook-div\" data-bookid = \"" + b.getBookID() + "\" class = \"edit-book-btn\">" + b.getTitle() + "</a></td>" +
							"	<td>" + authorName +  " </td>" +
							"	<td>" + b.getFormat() + " </td> " +
							"	<td>" + b.getPrice() + " </td> " +
							"	<td>" + b.getStock() + " </td> " +
							"</tr>";
		}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	    System.out.println("***************/ADMIN SERVLET - GET INVENTORY/***************");
	}
	
    private void getOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - GET ORDERS***************");
    	ArrayList<Order> orderList = OrderService.getAllOrders();
    	String htmlOrderlist = "";
    	
    	for(Order o: orderList) {
    		htmlOrderlist +=  "<tr>" +
	    				         "<td><a data-toggle=\"modal\" data-target=\"#\">" + o.getOrderID() +"</a></td>" +
	    				         "<td>" + o.getEmail() +"</td>" +
	    				         "<td>" + o.getFirstName() + " " + o.getLastName() + "</td>" +
	    				         "<td>" + o.getTotal() +"</td>" +
    				         "</tr>";
    	}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlOrderlist);
    	System.out.println("***************/ADMIN SERVLET - GET ORDERS/***************");
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
		System.out.println("Format: " + request.getParameter("optradio"));
		System.out.println("Price: " + request.getParameter("price"));
		System.out.println("Date Published: " + request.getParameter("published"));
		System.out.println("Stock: " + request.getParameter("qty"));
		 
    	String title = request.getParameter("bookTitle");
    	String isbn = request.getParameter("isbn");
    	String genre = request.getParameter("genre");
    	String format = request.getParameter("optradio");
    	String published = request.getParameter("published");
    	float price = Float.parseFloat(request.getParameter("price"));
    	int stock = Integer.parseInt(request.getParameter("qty"));
    	
    	Book book = new Book(title, isbn, genre, format, price, stock);
    	book.setSQLDate(published);
    	BookService.addBook(book);
    	System.out.println("***************/ADMIN SERVLET - ADD BOOK/***************");
    	request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
    	
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
		String htmlAdminList = "";
		System.out.println("gt in");
		for(Admin a: adminList) {
			htmlAdminList += "<tr> " +
							 "	<td> " + a.getFirstname() + "</td>" +
							 "	<td> " + a.getLastname() + "</td>" +
							 "	<td> " + a.getEmail() + "</td>" + 
							 "	<td> " + a.getRole() + "</td>" +
							 "  <td><button class=\"btn btn-default btn-tabledelacc\" data-toggle=\"modal\" data-target=\"#delaccount-modal\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>" + 
							 "</tr>";
		}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(htmlAdminList);
	    
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
    
	private void getBookDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - VIEW BOOK***************");
		int bookid = Integer.parseInt(request.getParameter("bookID"));
		Book book = BookService.getBook(bookid);

		Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd").create();
		String bookJson = gson.toJson(book);
		System.out.println("Book JSON: " + bookJson);
		response.setContentType("application/json"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(bookJson);
		System.out.println("***************/SHOPPING SERVLET - VIEW BOOK/***************");
	}
	
	private void getPublisherName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************ADMIN SERVLET - GET PUBLISHER NAME***************");
		String publisherName = PublisherService.getPublisher(Integer.parseInt(request.getParameter("publisherID")));

		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(publisherName);
		System.out.println("***************/ADMIN SERVLET - GET PUBLISHER NAME/***************");
	}
	
	private void getAuthorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************ADMIN SERVLET - GET AUTHOR NAME***************");
		String authorName = AuthorService.getAuthorName(Integer.parseInt(request.getParameter("authorID")));

		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(authorName);
		System.out.println("***************/ADMIN SERVLET - GET AUTHOR NAME/***************");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
		
		case "/getOrders": System.out.println("I am at adminServlet, getOrders case");
						getOrders(request, response);
						break;
		case "/getOrderDetails": System.out.println("I am at adminServlet, getOrderDetails case");
						getOrderDetails(request, response);
						break;
		case "/getInventory": System.out.println("I am at adminServlet, getOrderDetails case");
							getInventory(request, response);
							break;
		case "/getAdminList" : System.out.println("I am at adminServlet, getAdminList case");
							getAdminList(request, response);	
							break;
		case "/getBookDetails" : System.out.println("I am at adminServlet, getAdminList case");
							getBookDetails(request, response);	
							break;
		case "/getAuthorName" : System.out.println("I am at adminServlet, getAuthorName case");
							getAuthorName(request,response);
							break;
		case "/getPublisherName": System.out.println("I am at adminServlet, getPublisherName case");
							getPublisherName(request, response);
		}	
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
				case "/editAdminGet" : System.out.println("I am at adminServlet, editAdminGet case");
								editAdmin(request, response);	
								break;
				case "/editAdminConfirm" : System.out.println("I am at adminServlet, editAdminConfirm");
								editAdminConfirm(request, response);	
								break;
			}
	}
}
