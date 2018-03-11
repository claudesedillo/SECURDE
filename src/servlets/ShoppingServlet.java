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
import beans.Customer;
import beans.Order;
import beans.OrderList;
import beans.Shoppingcart;
import service.AuthorService;
import service.BookService;
import service.CustomerService;
import service.OrderListService;
import service.OrderService;
import service.PublisherService;
import service.ShoppingcartService;

/**
 * Servlet implementation class ShoppingServlet
 */

@WebServlet(urlPatterns = {"/getCatalog", 
						   "/addToCart", 
						   "/checkout",
						   "/checkoutConfirm",
						   "/getCompleteCatalog",
						   "/viewBook",
						   "/search",
						   "/browseByGenre",
						   "/intoCart",
						   "/getCartList",
						   "/removeFromCart",
						   "/getCheckoutDelivery",
						   "/getCheckoutSignIn",
						   "/getCheckoutPrice"})
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
			//System.out.println("Author Name: " + authorName + " Publisher Name: " + publisherName);
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
		System.out.println("***************/SHOPPING SERVLET - GET CATALOG/***************");
	}
	

	
	private void getCartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.print("GOT IN intoCart");
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		String htmlBookList = "";
		for(Shoppingcart sc : cartlist){
			Book book  = BookService.getBook(sc.getBookid());
			htmlBookList += "<div class=\"row book-div\">" +
								"<div class=\"col-sm-3\">" +
									"<img src=\"css/generic-cover.jpg\" class=\"img-responsive\">" +
								"</div>" +
								"<div class=\"col-sm-9\">"+
									"<form action=\"removeFromCart\" method=\"get\">" +
										"<button type=\"submit\" class=\"btn btn-delete\"  name=\"remove\" value=\"" + String.format("%d", book.getBookID()) + "\"><span class=\"glyphicon glyphicon-remove\"></span></button>" +
									"</form>" +
									"<p class=\"title\">" + book.getTitle() + "</p>" +
									"<p> by <span class=\"author\">" + AuthorService.getAuthorName(book.getAuthorID()) + "</span></p>" + 
									"<p class=\"format\">" + book.getFormat() + "</p>" +
									"<p class=\"price\">P" + String.format("%.2f", book.getPrice()) + " </p>" +
									"<div class=\"col-sm-3\">" +
										"<div class=\"input-group\">" +
											"<span class=\"input-group-btn\">" +
												"<button type=\"button\" class=\"btn btn-default btn-number\" disabled=\"disabled\" data-type=\"minus\" data-field=\"quant[1]\"><span class=\"glyphicon glyphicon-minus\"></span></button>" +
											"</span>" +
											"<input type=\"text\" name=\"quant[1]\" class=\"form-control input-number\" value=\"" + String.format("%d", sc.getQuantity()) + "\" min=\"0\" max=\"" + String.format("%d", book.getStock())  + "\">" +
											"<span class=\"input-group-btn\">" +
												"<button type=\"button\" class=\"btn btn-default btn-number\" data-type=\"plus\" data-field=\"quant[1]\"><span class=\"glyphicon glyphicon-plus\"></span>" +
											"</button>" +
											"</span>" +
										"</div>" +
									"</div>" +
								"</div>" +
								"<hr>" +
							"</div>";
									
		}
		
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
		
	}
	

	private void getCheckOutButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("GOT IN intoCart");
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		float total = 0;
		for(Shoppingcart sc : cartlist){
			total += sc.getPrice();
		}
		
		String htmlBookList =   "<div class=\"col-sm-5\">" +
				                    "<p>SUBTOTAL: </p>" +
				                "</div>" +
				                
				                "<div class=\"col-sm-7\">" +
				                    "<p id=\"totalprice\"> P" + String.format("%.2f", total) + "</p>" +
				                "</div>";
		if(total > 0){
			htmlBookList += "<form action=\"Checkout.jsp\" method=\"get\">" + 
					        	"<button type=\"submit\" class=\"btn btn-default\" id=\"btn-checkout\">CHECKOUT</button>" +
					        "</form>";
		}
		else htmlBookList += "<button type=\"button\" class=\"btn btn-default\" id=\"btn-checkout\">CHECKOUT</button>";
				                
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	}
	
	private void getCheckoutSignIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlBookList = "<div class=\"card\">"
							 	+ "<div class=\"card-header\" id=\"ch-signin\">"
							 		+ "<h5 class=\"mb-0\"><button class=\"btn btn-link\" data-toggle=\"collapse\" data-target=\"#signin-card\" aria-expanded=\"true\" aria-controls=\"signin-card\">SIGN IN</button></h5>" 
							 	+ "</div>"
							 	+ "<div id=\"signin-card\" class=\"collapse show\" aria-labelledby=\"ch-signin\" data-parent=\"#accordion\">"
							 		+ "<div class=\"card-body\">"
							 			+ "<div class=\"row\">"
							 				+ "<div class=\"col-sm-6\" id=\"signin-div\">"
							 					+ "<p>Sign in to your Bookshelf account</p>"
							 					+ "<a href=\"#\">want to create an online account?</a><br><br>"
							 					+ "<form>"
							 						+ "<div class=\"form-group\">"
							 							+ "<input type=\"email\" class=\"form-control\" id=\"email\" placeholder=\"email address\">"
							 						+ "</div>" 
							 						+ "<div class=\"form-group\">"
							 							+ "<input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"password\">"
							 						+ "</div>"
							 						+ "<button type=\"button\" class=\"btn btn-default\" id=\"btn-signin\">sign in</button>"
							 						+ "<a href=\"#\">forgot password?</a>"
							 					+ "</form>"
							 				+ "</div>"
							 				+ "<div class=\"col-sm-6\" id=\"guestco-div\">"
							 					+ "<p>Checkout as a Guest</p>"
							 					+ "<p class=\"sub\">your email will be used to confirm your order.</p>"
							 					+ "<form>"
							 						+ "<div class=\"form-group\">"
							 							+ "<input type=\"email\" class=\"form-control\" id=\"email\" placeholder=\"email address\">"
							 						+ "</div>"
							 						+ "<button type=\"button\" class=\"btn btn-default\" id=\"btn-next1\" >next</button>"
							 					+ "</form>"
							 				+ "</div>"
							 			+ "</div>"
							 		+ "</div>"
							 	+ "</div>"
							 + "</div>";
		
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	}

	private void getCheckoutDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		Customer cust = null;
		for(Shoppingcart c: cartlist) 
			cust = CustomerService.getCustomer(c.getEmail());
		if(cust == null)
			cust = new Customer();
		String htmlBookList = "<div class=\"card\">"
								+ "<div class=\"card-header\" id=\"ch-delivery\">"
									+ "<h5 class=\"mb-0\"><button class=\"btn btn-link collapsed\" data-toggle=\"collapse\" data-target=\"#delivery-card\" aria-expanded=\"false\" aria-controls=\"delivery-card\">DELIVERY</button></h5>"
								+ "</div>"
								+ "<div id=\"delivery-card\" class=\"collapse show\" aria-labelledby=\"ch-delivery\" data-parent=\"#accordion\">"
						        + "<div class=\"card-body\">"
						            + "<div class=\"row\">"
						                 + "<form class=\"form-horizontal\" method = \"POST\" action = \"checkoutConfirm\">"
						                     + "<div class=\"form-group\">"
						                         + "<label class=\"control-label col-sm-2\" for=\"fname-inp\">First Name</label>"
						                         + "<div class=\"col-sm-10\">"
						                             + "<input type=\"text\" class=\"form-control\" id=\"fname-inp\" value = \" " + cust.getFirstname()  +"\">"
						                         + "</div>"
						                     + "</div>"
						                     + "<div class=\"form-group\">"
						                         + "<label class=\"control-label col-sm-2\" for=\"lname-inp\">Last Name</label>"
						                         + "<div class=\"col-sm-10\">"
						                             + "<input type=\"text\" class=\"form-control\" id=\"lname-inp\" value = \" " + cust.getLastname()  +"\">"
						                         + "</div>"
						                    + "</div>"
						                    + "<div class=\"form-group\">"
						                         + "<label class=\"control-label col-sm-2\" for=\"address-inp\">Address</label>"
						                         + "<div class=\"col-sm-10\">"
						                             + "<input type=\"text\" class=\"form-control\" id=\"address-inp\" value = \" " + cust.getStreetaddress()  +"\">"
						                         + "</div>"
						                    + "</div>"
						                     
						                    + "<div class=\"form-group\">"
						                         + "<label class=\"control-label col-sm-2\" for=\"city-inp\">City</label>"
						                         + "<div class=\"col-sm-10\">"
						                             + "<input type=\"text\" class=\"form-control\" id=\"city-inp\" value = \" " + cust.getCity()  +"\">"
						                         + "</div>"
						                    + "</div>"
						                     
						                    + "<button type=\"submit\" class=\"btn btn-default\" id=\"btn-next2\" >next</button>"
						                + "</form>"
						            + "</div>"
						        + "</div>"
						    + "</div>"
						+ "</div>";
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
		
	}
	

	private void getCheckoutPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		float total = 0;
		for(Shoppingcart sc : cartlist){
			total += sc.getPrice();
		}
		
		String htmlBookList = "<div class=\"col-sm-4\" id=\"total-div\">"
								+ "<div class=\"col-sm-5\">"
									+ "<p>ORDER TOTAL: </p>"  
								+ "</div>"
								+ "<div class=\"col-sm-7\">"    
									+ "<p id=\"totalprice\">P" + String.format("%.2f", total) + "</p>"    
								+ "</div>"
							+ "</div>";
		
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	}

	
	private void checkOutConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("AT CHECKOUT CONFIRM");
		HttpSession session = request.getSession();
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		int totalprice = 0;
		
		for(Shoppingcart c: cartlist) {
			totalprice += c.getPrice();
		}
		
		String email = request.getParameter("email"),
			   firstname = request.getParameter("firstname"),
			   lastname = request.getParameter("lastname"),
			   stAddress = request.getParameter("streetAddress"),
			   city = request.getParameter("city");
		System.out.println("Total price: " + totalprice);
		Order order = new Order(email, firstname, lastname, stAddress, city, totalprice);
		
		OrderService.addCustomer(order);
		int orderID = OrderService.getLatestOrder();
		
		for(Shoppingcart sc : cartlist){
			OrderList ol = new OrderList(orderID, sc.getBookid(), sc.getQuantity());
			OrderListService.addOrderList(ol);
		}
		System.out.println("I am at checkout confirm, done!");
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}
	
	private boolean geust;
	private String email;
	
	private void intoCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("GOT IN intoCart");
		HttpSession session = request.getSession();
		
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		
		session.setAttribute("cartlist", cartlist);
		request.setAttribute("cartlist", cartlist);
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
	}
	
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Book book = (Book) session.getAttribute("book");
		//int qty = Integer.parseInt(request.getParameter("qty"));
		
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		if(geust){
			Shoppingcart sc = new Shoppingcart(book.getBookID(), book.getPrice() , email, 1);
			int indexOfDuplicate = checkContains(sc, cartlist);
			
			if(indexOfDuplicate != -1){
				sc.setQuantity(sc.getQuantity() + cartlist.get(indexOfDuplicate).getQuantity());
				sc.setPrice(sc.getPrice() + cartlist.get(indexOfDuplicate).getPrice());
				cartlist.set(indexOfDuplicate, sc);
			}
			else{
				cartlist.add(sc);
			}
		}
		else{
			Shoppingcart sc = new Shoppingcart(book.getBookID(), book.getPrice() , email, 1);
			int indexOfDuplicate = checkContains(sc, cartlist);
			
			if(indexOfDuplicate != -1){
				sc.setQuantity(sc.getQuantity() + cartlist.get(indexOfDuplicate).getQuantity());
				sc.setPrice(sc.getPrice() + cartlist.get(indexOfDuplicate).getPrice());
				cartlist.set(indexOfDuplicate, sc);
				ShoppingcartService.updateShoppincart(sc);
			}
			else{
				cartlist.add(sc);
				ShoppingcartService.addShoppingcart(sc);
			}
		}
		session.setAttribute("cartlist", cartlist);
		request.setAttribute("cartlist", cartlist);
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		
	}
	
	private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int bookid = Integer.parseInt(request.getParameter("remove"));
		
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		
		if(geust){
			int indexRemoved = -1;
			for(Shoppingcart sc : cartlist){
				if(sc.getBookid() == bookid){
					indexRemoved = cartlist.indexOf(sc);
				}
			}
			cartlist.remove(indexRemoved);
		}
		else{
			int indexRemoved = -1;
			for(Shoppingcart sc : cartlist){
				if(sc.getBookid() == bookid){
					indexRemoved = cartlist.indexOf(sc);
				}
			}
			ShoppingcartService.deleteCart(cartlist.get(indexRemoved).getBookid(), cartlist.get(indexRemoved).getEmail());
			cartlist.remove(indexRemoved);
			
		}
		
		session.setAttribute("cartlist", cartlist);
		request.setAttribute("cartlist", cartlist);
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
	}
	
	public List<Shoppingcart> getShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		geust = true;
		email = "Guest";
		
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
			if(!geust)
				cartlist = ShoppingcartService.getShoppingCartList(email);
			System.out.println("NOT NULL");
			System.out.println(cartlist);
		}
		
		return cartlist;
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
		System.out.println(request.getServletPath());
		switch(request.getServletPath()) {
		
		case "/getCompleteCatalog": System.out.println("I am at doGet method, getCompleteCatalog case.");
							getCompleteCatalog(request, response);
							break;
		case "/getCatalog": System.out.println("I am at doGet method, getCatalog case");
							getCatalog(request, response);
							break;
		case "/getCartList" : System.out.println("I am at doGet method, getCartList case");
							getCartList(request, response);
							break;
		case "/checkout" :  System.out.println("I am at doGet method, checkout case");
							getCheckOutButton(request, response);
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
		case "/intoCart": System.out.println("I am at shoppingServlet, intoCart method");
						  intoCart(request, response);
						  break;
		case "/removeFromCart" : System.out.println("I am at shoppingServlet, removeFromCart method");
								 removeFromCart(request, response);
								 break;
		case "/getCheckoutSignIn": System.out.println("I am at shoppingServlet, checkOutLogin method");
								   getCheckoutSignIn(request,response);
								   break;
		case "/getCheckoutDelivery": System.out.println("I am at shoppingServlet, getPrice method");
									 getCheckoutDelivery(request, response);
									 break;
		case "/getCheckoutPrice" : getCheckoutPrice(request, response);
		 						   break;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
			case "/checkoutConfirm" : System.out.println("I am at doGet method, checkoutConfirm case");
			  						  checkOutConfirm(request, response);
			  						  break;
		}
	}

}
