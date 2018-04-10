package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Author;
import beans.Book;
import beans.Publisher;
import service.AuthorService;
import service.BookService;
import service.PublisherService;

/**
 * Servlet implementation class InventoryServlet
 */
@WebServlet(urlPatterns = {"/getCatalog",
						   "/viewBook",
						   "/search",
						   "/browseByGenre",
						   "/addAuthor",
						   "/getInventory",
						   "/addPublisher",		   
						   "/addBook",
						   "/editBook",
						   "/editConfirm",
						   "/editGet",
						   "/getBookDetails",
						   "/getAuthorName",
						   "/getPublisherName"})
public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private void browseByGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - BROWSE BY GENRE***************");
		
		List<Book> bookList;
		List<String> authorNames = new ArrayList<String>();
		String genre = request.getParameter("genre");
		
		bookList = BookService.filterByGenre(genre);
		for(Book b: bookList) {
			authorNames.add(AuthorService.getAuthorName(b.getAuthorID()));
		}
		
		request.setAttribute("authorNames", authorNames);
		request.setAttribute("genre", genre);
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("ViewByGenre.jsp").forward(request, response);
		System.out.println("***************/INVENTORY SERVLET - BROWSE BY GENRE/***************");
	}
	
	private void getInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY - GET INVENTORY***************");
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		
		for(Book b: bookList) {
			String authorName;
			
			authorName = AuthorService.getAuthorName(b.getAuthorID());
			//System.out.println("Author Name: " + authorName + " Publisher Name: " + publisherName);
			htmlBookList += "<tr>" + 
							"	<td><a data-toggle=\"modal\" data-target=\"#viewbook-div\" id=\"bookid\" data-bookid = \"" + b.getBookID() + "\" class = \"edit-book-btn\">" + b.getTitle() + "</a></td>" +
							"	<td>" + authorName +  " </td>" +
							"	<td>" + b.getFormat() + " </td> " +
							"	<td>" + b.getPrice() + " </td> " +
							"	<td>" + b.getStock() + " </td> " +
							"</tr>";
		}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	    System.out.println("***************/INVENTORY SERVLET - GET INVENTORY/***************");
	}
		
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************INVENTORY SERVLET - ADD BOOK***************");
    	
    	int stock = Integer.parseInt(request.getParameter("stock")), publisherID, authorID;

    	String title = request.getParameter("title");
    	String isbn = request.getParameter("isbn");
    	String genre = request.getParameter("genre");
    	String format = request.getParameter("format");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		
		Date datePublished = java.sql.Date.valueOf((request.getParameter("datePublished")));		
    	float price = Float.parseFloat(request.getParameter("price"));		

		if(!PublisherService.doesPublisherExist(publisherName)) {
			Publisher newPublisher = new Publisher(publisherName);
			PublisherService.addPublisher(newPublisher);	
		}
		
		if(!AuthorService.doesAuthorExist(authorName)) {
			Author newAuthor = new Author(authorName);
			AuthorService.addAuthor(newAuthor);
		}
		
		publisherID = PublisherService.getPublisherID(publisherName);
		authorID = AuthorService.getAuthorID(authorName);
		
		System.out.println("Book Title: " + title);
		System.out.println("ISBN: " + isbn);
		System.out.println("Genre: " + genre);
		System.out.println("Format: " + format);
		System.out.println("Price: " + price);
		System.out.println("Date Published: " + datePublished);
		System.out.println("Stock: " + stock);
		System.out.println("Author ID: " + authorID);
		System.out.println("Publisher ID: " + publisherID);
		
    	Book book = new Book(title, isbn, genre, format, price, stock, datePublished, authorID, publisherID);
    	BookService.addBook(book);
    	System.out.println("***************/INVENTORY SERVLET - ADD BOOK/***************");
    	request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
    	
    }
    
	private void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - EDIT BOOK***************");
		
		int bookID = Integer.parseInt(request.getParameter("bookID")),
	        price = Integer.parseInt(request.getParameter("price")),
	        stock = Integer.parseInt(request.getParameter("stock")),
			publisherID, authorID;
		String title = request.getParameter("title"),
			   authorName = request.getParameter("authorName"),
			   isbn = request.getParameter("isbn"),
			   publisherName = request.getParameter("publisherName"),
			   genre = request.getParameter("genre"),
			   format = request.getParameter("format");
		Date datePublished = java.sql.Date.valueOf((request.getParameter("datePublished")));
		
		if(!PublisherService.doesPublisherExist(publisherName)) {
			Publisher newPublisher = new Publisher(publisherName);
			PublisherService.addPublisher(newPublisher);	
		}
		
		if(!AuthorService.doesAuthorExist(authorName)) {
			Author newAuthor = new Author(authorName);
			AuthorService.addAuthor(newAuthor);
		}
		publisherID = PublisherService.getPublisherID(publisherName);
		authorID = AuthorService.getAuthorID(authorName);
		
		Book newBook = new Book(bookID, title, isbn, genre, format, price, stock, datePublished, authorID, publisherID);
		BookService.updateBooks(newBook);
		System.out.println("***************/INVENTORY SERVLET - EDIT BOOK/***************");
	}
	
	private void editBookConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("***************INVENTORY SERVLET - EDIT BOOK CONFIRM***************");
//		HttpSession session = request.getSession();
//		int id = (int) session.getAttribute("bookid");
//		String title = request.getParameter("title"),
//			   isbn = request.getParameter("isbn"),
//			   genre = request.getParameter("genre"),
//			   format = request.getParameter("format");
//		Date pub =  java.sql.Date.valueOf(request.getParameter("pub"));
//		int authorID = Integer.parseInt(request.getParameter("authorID")), 
//			publisherID = Integer.parseInt(request.getParameter("publisherID"));
////		
//		float price = Float.parseFloat(request.getParameter("price"));
//		int stock = Integer.parseInt(request.getParameter("stock"));
//		Book newBook = new Book(id, title, isbn, genre, format, price, stock, pub, authorID, publisherID);
//		BookService.updateBooks(newBook);
//		System.out.println("***************/INVENTORY SERVLET - EDIT BOOK CONFIRM/***************");
//		request.getRequestDispatcher("catalogTest.html").forward(request, response);
	}
	
	private void getBookDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - VIEW BOOK***************");
		int bookid = Integer.parseInt(request.getParameter("bookID"));
		Book book = BookService.getBook(bookid);

		Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd").create();
		String bookJson = gson.toJson(book);
		response.setContentType("application/json"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(bookJson);
		System.out.println("***************/INVENTORY SERVLET - VIEW BOOK/***************");
	}
	
	private void getPublisherName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - GET PUBLISHER NAME***************");
		String publisherName = PublisherService.getPublisherName(Integer.parseInt(request.getParameter("publisherID")));

		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(publisherName);
		System.out.println("***************/INVENTORY SERVLET - GET PUBLISHER NAME/***************");
	}
	
	private void getAuthorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - GET AUTHOR NAME***************");
		String authorName = AuthorService.getAuthorName(Integer.parseInt(request.getParameter("authorID")));

		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(authorName);
		System.out.println("***************/INVENTORY SERVLET - GET AUTHOR NAME/***************");
	}
	
	private void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - VIEW BOOK***************");
		int bookid = Integer.parseInt(request.getParameter("bookID"));
		Book book = BookService.getBook(bookid);
		String authorName = AuthorService.getAuthorName(book.getAuthorID()), 
			   publisherName = PublisherService.getPublisherName(book.getPublisherID());
		request.setAttribute("authorName", authorName);
		request.setAttribute("publisherName", publisherName);
		HttpSession session = request.getSession();
		session.setAttribute("book", book);
		request.setAttribute("book", book);
		request.getRequestDispatcher("ViewBook.jsp").forward(request, response);
		System.out.println("***************/INVENTORY SERVLET - VIEW BOOK/***************");
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - SEARCH***************");
		
		List<Integer> authorIDs;
		List<Book> bookList, bookListByAuthor;
		
		List<String> authorNames = new ArrayList<String>();
		
		String searchTerm = request.getParameter("searchTerm");
		System.out.println("Search term is " + searchTerm);
		
		authorIDs = AuthorService.findAuthor(searchTerm);
		bookListByAuthor = BookService.getBookByAuthorID(authorIDs);
		bookList = BookService.searchBook(searchTerm);
		
		bookList.removeAll(bookListByAuthor);
		bookList.addAll(bookListByAuthor);		
		
		for(Book b: bookList) {
			authorNames.add(AuthorService.getAuthorName(b.getAuthorID()));
		}
		
		request.setAttribute("authorNames", authorNames);
		request.setAttribute("searchTerm", searchTerm);
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
		System.out.println("***************/INVENTORY SERVLET - SEARCH/***************");
	}
	

	
	private void getCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************INVENTORY SERVLET - GET CATALOG***************");
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		//System.out.println("Book List:");
		
		for(Book b: bookList) {
			//System.out.println("Book Name: " + b.getTitle());
			//System.out.println("Book ID: " + b.getBookID());
			//System.out.println("Author ID: " + b.getAuthorID());
			String authorName;
			authorName = AuthorService.getAuthorName(b.getAuthorID());
			htmlBookList += "<div class=\"col-sm-3 book-div\"> " +
							"<img src=\"css/generic-cover.jpg\" class=\"img-responsive\"> " +
							"<form method = \"GET\" > " +
							"<div class=\"row\"> " +
							"<a class = \"bookLink\" data-bookId = " + b.getBookID() + "><p class=\"title\">" + b.getTitle() + "</p></a> " +
							"<p class=\"author\">" + authorName + "</p> " +
		                    "<p class=\"price\"> " + b.getPrice() + "</p>" +
							"</div>" + 
		                    "</div>" +
							"</form>";
		}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
		System.out.println("***************/INVENTORY SERVLET - GET CATALOG/***************");
	}
	

    
    protected void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************INVENTORY SERVLET - ADD AUTHOR***************");
		System.out.println("First name: " + request.getParameter("authorFirstName"));
		System.out.println("Last Name: " + request.getParameter("authorLastName"));
		
    	String authorFirstName = request.getParameter("authorFirstName");
    	String authorLastName = request.getParameter("authorLastName");
    	String name = authorFirstName + " " + authorLastName;
    	
    	Author author = new Author(name);
    	AuthorService.addAuthor(author);
    	System.out.println("***************/INVENTORY SERVLET - ADD AUTHOR/***************");
    }
    
    protected void addPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	System.out.println("***************INVENTORY SERVLET - ADD PUBLISER***************");
		System.out.println("Publisher Name: " + request.getParameter("publisherName"));
		
    	String publisherName = request.getParameter("publisherName");
    	
    	Publisher publisher = new Publisher(publisherName);
    	
    	PublisherService.addPublisher(publisher);
		System.out.println("***************INVENTORY SERVLET - ADD PUBLISHER***************");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getServletPath()) {
		
		case "/getCatalog": System.out.println("I am at InventoryServlet, getCatalog case");
							getCatalog(request, response);
							break;
		case "/viewBook": System.out.println("I am at InventoryServlet, viewBook case");
						  viewBook(request, response);
						  break;
		case "/search": System.out.println("I am at InventoryServlet, search case");
						search(request, response);
						break;
		case "/browseByGenre": System.out.println("I am at InventoryServlet, BrowseByGenre method");
							   browseByGenre(request, response);
							   break;
		case "/getInventory": System.out.println("I am at InventoryServlet, getOrderDetails case");
							getInventory(request, response);
							break;
		case "/getBookDetails" : System.out.println("I am at InventoryServlet, getAdminList case");
								getBookDetails(request, response);	
								break;
		case "/getAuthorName" : System.out.println("I am at InventoryServlet, getAuthorName case");
								getAuthorName(request,response);
								break;
		case "/getPublisherName": System.out.println("I am at InventoryServlet, getPublisherName case");
								getPublisherName(request, response);
								break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		switch(request.getServletPath()) {
		
		case "/addAuthor": System.out.println("I am at InventoryServlet, addAuthor case");
							addAuthor(request, response);
							break;					
		case "/addPublisher": System.out.println("I am at InventoryServlet, addPublisher case");
						  addPublisher(request, response);
						  break;				  
		case "/addBook": System.out.println("I am at InventoryServlet, addBook case");
		 				addBook(request, response);
		 				break;		
		case "/editConfirm": System.out.println("I am at InventoryServlet, editGet case");
						editBookConfirm(request, response);	
						break;
		case "/editGet": System.out.println("I am at InventoryServlet, editGet case");
						editBook(request, response);
						break;
		case "/editBook": System.out.println("I am at InventoryServlet, editBook case");
						editBook(request, response);
						break;
		}
	}

}
