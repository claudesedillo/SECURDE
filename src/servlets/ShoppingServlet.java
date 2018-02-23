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
@WebServlet(urlPatterns = {"/getCatalog", "/addToCart", "/checkout", "/editGet", "/editConfirm", "/getCompleteCatalog"})
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
							"Author: " + b.getAuthorID() + " <br> " +
							"Publisher: " + b.getPublisherID() + " <br> " +
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
							"<div class=\"row\"> " +
							"<p class=\"title\">" + b.getTitle() + "</p> " +
							"<p class=\"author\">" + authorName + "</p> " +
		                    "<p class=\"price\"> " + b.getPrice() + "</p>" +
							"</div>" + 
		                    "</div>";
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
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getServletPath().equals("/editGet")){
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
		else if(request.getServletPath().equals("/editConfirm")){
			HttpSession session = request.getSession();
			int id = (int) session.getAttribute("bookid");
			String title = request.getParameter("title"),
				   isbn = request.getParameter("isbn"),
				   genre = request.getParameter("genre"),
				   format = request.getParameter("format");
			Date pub =  java.sql.Date.valueOf(request.getParameter("pub"));

			float price = Float.parseFloat(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock")),
			    authorID = Integer.parseInt(request.getParameter("authorID")), 
			    publisherID = Integer.parseInt(request.getParameter("publisherID"));;
			Book newBook = new Book(id, title, isbn, genre, format, price, stock, pub, authorID, publisherID);
			BookService.updateBooks(newBook);
			request.getRequestDispatcher("catalogTest.html").forward(request, response);
		}
		else doGet(request, response);
	}

}
