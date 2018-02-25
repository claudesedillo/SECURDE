package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.Shoppingcart;
import service.AuthorService;
import service.BookService;
import service.PublisherService;
import service.ShoppingcartService;

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
		System.out.println("***************SHOPPING SERVLET - BROWSE BY GENRE***************");
		
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
		System.out.println("***************/SHOPPING SERVLET - BROWSE BY GENRE/***************");
	}
	
	private void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - VIEW BOOK***************");
		int bookid = Integer.parseInt(request.getParameter("bookID"));
		Book book = BookService.getBook(bookid);
		String authorName = AuthorService.getAuthorName(book.getAuthorID()), 
			   publisherName = PublisherService.getPublisher(book.getPublisherID());
		request.setAttribute("authorName", authorName);
		request.setAttribute("publisherName", publisherName);
		HttpSession session = request.getSession();
		session.setAttribute("book", book);
		request.setAttribute("book", book);
		request.getRequestDispatcher("ViewBook.jsp").forward(request, response);
		System.out.println("***************/SHOPPING SERVLET - VIEW BOOK/***************");
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - SEARCH***************");
		
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
		System.out.println("***************/SHOPPING SERVLET - SEARCH/***************");
	}
	
	private void getCompleteCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - GET COMPLETE CATALOG***************");
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		
		for(Book b: bookList) {
			String authorName, publisherName;
			
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
	    System.out.println("***************/SHOPPING SERVLET - GET COMPLETE CATALOG/***************");
	}
	
	private void getCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - GET CATALOG***************");
		ArrayList<Book> bookList = BookService.getBookList();
		String htmlBookList = "";
		System.out.println("Book List:");
		
		for(Book b: bookList) {
			System.out.println("Book Name: " + b.getTitle());
			System.out.println("Book ID: " + b.getBookID());
			System.out.println("Author ID: " + b.getAuthorID());
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
		System.out.println("***************/SHOPPING SERVLET - GET CATALOG/***************");
	}
	
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Book book = (Book) session.getAttribute("book");
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		boolean geust = true;
		String email = "Guest";
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("logged")){
					geust = false;
					email = currentCookie.getValue();
				}
			}
		}
		
		List<Shoppingcart> cartlist = (List<Shoppingcart>) session.getAttribute("cartlist");
		
		if(cartlist == null){
			System.out.println("NULL");
			if(geust)
				cartlist = new ArrayList<Shoppingcart>();
			else
				cartlist = ShoppingcartService.getShoppingCartList(email);
		}
		else{
			System.out.println("NOT NULL");
			System.out.println(cartlist);
		}
		
		if(geust){
			Shoppingcart sc = new Shoppingcart(book.getBookID(), book.getPrice() * qty, email, qty);
			
			int indexOfDuplicate = checkContains(sc, cartlist);
			
			System.out.println("indexOfDuplicate : " + indexOfDuplicate);
			
			if(indexOfDuplicate != -1){
				sc.setQuantity(sc.getQuantity() + cartlist.get(indexOfDuplicate).getQuantity());
				sc.setPrice(sc.getPrice() + cartlist.get(indexOfDuplicate).getPrice());
				cartlist.add(indexOfDuplicate, sc);
			}
			else{
				cartlist.add(sc);
			}
			
			session.setAttribute("cartlist", cartlist);
			request.setAttribute("cartlist", cartlist);
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
		else{
			Shoppingcart sc = new Shoppingcart(book.getBookID(), book.getPrice() * qty, email, qty);
			
			int indexOfDuplicate = checkContains(sc, cartlist);
			
			
			System.out.println("indexOfDuplicate : " + indexOfDuplicate);
			if(indexOfDuplicate != -1){
				sc.setQuantity(sc.getQuantity() + cartlist.get(indexOfDuplicate).getQuantity());
				sc.setPrice(sc.getPrice() + cartlist.get(indexOfDuplicate).getPrice());
				cartlist.add(indexOfDuplicate, sc);
				ShoppingcartService.updateShoppincart(sc);
			}
			else{
				cartlist.add(sc);
				ShoppingcartService.addShoppingcart(sc);
			}
			
			session.setAttribute("cartlist", cartlist);
			request.setAttribute("cartlist", cartlist);
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
		
	}
	
	public int checkContains(Shoppingcart sc, List<Shoppingcart> cartlist){
		for(Shoppingcart c : cartlist){
			if(c.getBookid() == sc.getBookid() && c.getEmail().equals(sc.getEmail())){
				return cartlist.indexOf(c);
			}
		}
		return -1;
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
		case "/addToCart": System.out.println("I am at shoppingServlet, addToCart method");
						addToCart(request, response);
						break;				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
