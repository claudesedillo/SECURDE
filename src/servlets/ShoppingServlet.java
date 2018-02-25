package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
						   "/search",
						   "/browseByGenre"})
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private void browseByGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*************************************************************");
		System.out.println("I am at browseByGenre method at shoppingServlet");
		
		List<Book> bookList;
		String genre = request.getParameter("genre");
		
		bookList = BookService.filterByGenre(genre);
		
		System.out.println("I am at shoppingServlet");
		System.out.println("bookList contains:");
		
		for(Book b: bookList) {
			System.out.println(b.toString());
		}
		
		request.setAttribute("genre", genre);
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("ViewByGenre.jsp").forward(request, response);
		System.out.println("*************************************************************");
	}
	private void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("bookID"));
		Book book = BookService.getBook(bookid);
		String authorName = AuthorService.getAuthorName(book.getAuthorID()), 
			   publisherName = PublisherService.getPublisher(book.getPublisherID());
		request.setAttribute("authorName", authorName);
		request.setAttribute("publisherName", publisherName);
		request.setAttribute("book", book);
		request.getRequestDispatcher("ViewBook.jsp").forward(request, response);
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*************************************************************");
		System.out.println("I am at search method at shoppingServlet");
		
		List<Integer> authorIDs;
		List<Book> bookList, bookListByAuthor;
		String searchTerm = request.getParameter("searchTerm");
		System.out.println("Search term is " + searchTerm);
		
		authorIDs = AuthorService.findAuthor(searchTerm);
		bookListByAuthor = BookService.getBookByAuthorID(authorIDs);
		bookList = BookService.searchBook(searchTerm);
		
		bookList.removeAll(bookListByAuthor);
		bookList.addAll(bookListByAuthor);
		System.out.println("I am at shoppingServlet");
		System.out.println("bookList contains:");
		
		for(Book b: bookList) {
			System.out.println(b.toString());
		}
		request.setAttribute("searchTerm", searchTerm);
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("resultPage.jsp").forward(request, response);
		System.out.println("*************************************************************");
	}
	
	private void getCompleteCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		
		for(Book b: bookList) {
			String authorName, publisherName;
			
			System.out.println(b.toString());
			authorName = AuthorService.getAuthorName(b.getAuthorID());
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
							"<Button type= \"submit\" name = \"btn-deleteProd\" value =" + String.format("%d", b.getBookID())  + "> DELETE </button>" +
							"</form>";
		}
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
						  viewBook(request, response);
						  break;
		case "/search": System.out.println("I am at shoppingServlet, search case");
						search(request, response);
						break;
		case "/browseByGenre": System.out.println("I am at shoppingServlet, BrowseByGenre method");
						browseByGenre(request, response);
						break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
