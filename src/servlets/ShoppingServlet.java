package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Author;
import beans.Book;
import beans.Publisher;
import service.AuthorService;
import service.BookService;
import service.PublisherService;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet(urlPatterns = {"/getCatalog", "/addToCart", "/checkout"})
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private void getCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> bookList = BookService.getBooks();
		String htmlBookList = "";
		
		for(Book b: bookList) {
			String authorName, publisherName;
			
			System.out.println(b.toString());
			authorName = AuthorService.getAuthor(b.getAuthorID());
			publisherName = PublisherService.getPublisher(b.getPublisherID());
			htmlBookList += "<div class = \"bookDiv\"> <br>" + 
							"Title: " + b.getTitle() + " <br> " +
							"Author: " + authorName +  " <br>" +
							"Publisher: " + publisherName + " <br> " +
							"ISBN: " + b.getIsbn() + " <br> " +
							"Genre: " + b.getGenre() + " <br> " +
							"Format: " + b.getFormat() + " <br> " +
							"Price: " + b.getPrice() + " <br> " +
							"</div>";
		}
		System.out.println("HTML Code: " + htmlBookList);
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	}
	
	private void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		case "/getCatalog": System.out.println("I am at doGet method, getCatalog case.");
							getCatalog(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
