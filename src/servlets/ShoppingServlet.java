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
import beans.Book;
import service.AuthorService;
import service.BookService;
import service.PublisherService;

/**
 * Servlet implementation class ShoppingServlet
 */

@WebServlet(urlPatterns = {"/getCatalog", 
						   "/addToCart", 
						   "/checkout",
						   "/getCompleteCatalog",
						   "/viewBook",
						   "/search"})
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int book = Integer.parseInt(request.getParameter("bookID"));
		request.setAttribute("shit", book);
		request.getRequestDispatcher("viewBook.jsp").forward(request, response);
	}
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> bookList;
		
	}
	
	private void getCompleteCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		
		for(Book b: bookList) {
			String authorName, publisherName;
			
			System.out.println(b.toString());
			authorName = AuthorService.getAuthor(b.getAuthorID());
			publisherName = PublisherService.getPublisher(b.getPublisherID());
			System.out.println("Author Name: " + authorName + " Publisher Name: " + publisherName);
			htmlBookList += "<form action=\"editGet\" method=\"post\">" + 
							"<div class = \"bookDiv\"> <br>" + 
							"Title: " + b.getTitle() + " <br> " +
							"Author: " + authorName +  " <br>" +
							"Publisher: " + publisherName + " <br> " +
							"ISBN: " + b.getIsbn() + " <br> " +
							"Genre: " + b.getGenre() + " <br> " +
							"Format: " + b.getFormat() + " <br> " +
							"Price: " + b.getPrice() + " <br> " +
							"Stock: " + b.getStock() + " <br> " +
							"<Button type= \"submit\" name = \"btn-editProd\" value =" + String.format("%d", b.getBookID())  + "> EDIT </button>" +
							"</form>";
		}
		System.out.println("HTML Code: " + htmlBookList);
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	}
	private void getCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		
		for(Book b: bookList) {
			String authorName;
			
			System.out.println(b.toString());
			authorName = AuthorService.getAuthor(b.getAuthorID());
			htmlBookList += "<div class=\"col-sm-3 book-div\"> " +
							"<img src=\"css/generic-cover.jpg\" class=\"img-responsive\"> " +
							"<form method = \"GET\" > " +
							"<div class=\"row\"> " +
							"<a href = \"/viewBook" + b.getBookID() + "> <p class=\"title\">" + b.getTitle() + "</p></a> " +
							"<p class=\"author\">" + authorName + "</p> " +
		                    "<p class=\"price\"> " + b.getPrice() + "</p>" +
							"</div>" + 
		                    "</div>" +
							"</form>";
		}
		System.out.println("HTML Code: " + htmlBookList);
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	}
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am at shopping servlet, doGet method");
		switch(request.getServletPath()) {
		
		case "/getCompleteCatalog": System.out.println("I am at doGet method, getCompleteCatalog case.");
							getCompleteCatalog(request, response);
							break;
		case "/getCatalog": System.out.println("I am at doGet method, getCatalog case");
							getCatalog(request, response);
							break;
		case "/viewBook": System.out.println("I am at doGet method, viewBook case");
						  System.out.println("TITETITETITE");
						  viewBook(request, response);
		case "/search": System.out.println("I am at shoppingServlet, search case");
							search(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
