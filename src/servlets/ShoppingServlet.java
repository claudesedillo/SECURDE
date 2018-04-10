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

import org.owasp.encoder.Encode;

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

@WebServlet(urlPatterns = {"/addToCart", 
						   "/checkout",
						   "/checkoutConfirm",
						   "/getCartList",
						   "/removeFromCart",
						   "/getCheckoutDelivery",
						   "/getCheckoutSignIn",
						   "/getCheckoutPrice",
						   "/getCartCount"})
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private void getCartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("***************SHOPPING SERVLET - GET CART LIST***************");
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
									"<p class=\"title\">" + Encode.forHtml(book.getTitle()) + "</p>" +
									"<p> by <span class=\"author\">" + Encode.forHtml(AuthorService.getAuthorName(book.getAuthorID())) + "</span></p>" + 
									"<p class=\"format\">" + book.getFormat() + "</p>" +
									"<p class=\"price\">P" + Encode.forHtml(String.format("%.2f", book.getPrice())) + " </p>" +
									"<div class=\"col-sm-3\">" +
										"<div class=\"input-group\">" +
											"<span class=\"input-group-btn\">" +
												"<button type=\"button\" class=\"btn btn-default btn-number\" disabled=\"disabled\" data-type=\"minus\" data-field=\"quant[1]\"><span class=\"glyphicon glyphicon-minus\"></span></button>" +
											"</span>" +
											"<input type=\"text\" name=\"quant[1]\" class=\"form-control input-number\" value=\"" + Encode.forHtml(String.format("%d", sc.getQuantity())) + "\" min=\"0\" max=\"" + String.format("%d", book.getStock())  + "\">" +
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
	    System.out.println("***************/SHOPPING SERVLET - GET CART LIST/***************");
	}
	

	private void getCheckOutButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - getCheckOutButton***************");
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		float total = 0;
		for(Shoppingcart sc : cartlist){
			total += sc.getPrice();
		}
		
		String checkoutHTML =   "<div class=\"col-sm-5\">" +
				                    "<p>SUBTOTAL: </p>" +
				                "</div>" +
				                
				                "<div class=\"col-sm-7\">" +
				                    "<p id=\"totalprice\"> P" + Encode.forHtml(String.format("%.2f", total)) + "</p>" +
				                "</div>";
		if(total > 0){
			checkoutHTML += "<form action=\"Checkout.jsp\" method=\"get\">" + 
					        	"<button type=\"submit\" class=\"btn btn-default\" id=\"btn-checkout\">CHECKOUT</button>" +
					        "</form>";
		}
		else checkoutHTML += "<button type=\"button\" class=\"btn btn-default\" id=\"btn-checkout\">CHECKOUT</button>";
				                
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(checkoutHTML);
		System.out.println("***************/SHOPPING SERVLET - getCheckOutButton/***************");
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
						                             + "<input type=\"text\" class=\"form-control\" id=\"fname-inp\" value = \" " + Encode.forHtml(cust.getFirstname())  +"\">"
						                         + "</div>"
						                     + "</div>"
						                     + "<div class=\"form-group\">"
						                         + "<label class=\"control-label col-sm-2\" for=\"lname-inp\">Last Name</label>"
						                         + "<div class=\"col-sm-10\">"
						                             + "<input type=\"text\" class=\"form-control\" id=\"lname-inp\" value = \" " + Encode.forHtml(cust.getLastname())  +"\">"
						                         + "</div>"
						                    + "</div>"
						                    + "<div class=\"form-group\">"
						                         + "<label class=\"control-label col-sm-2\" for=\"address-inp\">Address</label>"
						                         + "<div class=\"col-sm-10\">"
						                             + "<input type=\"text\" class=\"form-control\" id=\"address-inp\" value = \" " + Encode.forHtml(cust.getStreetaddress())  +"\">"
						                         + "</div>"
						                    + "</div>"
						                     
						                    + "<div class=\"form-group\">"
						                         + "<label class=\"control-label col-sm-2\" for=\"city-inp\">City</label>"
						                         + "<div class=\"col-sm-10\">"
						                             + "<input type=\"text\" class=\"form-control\" id=\"city-inp\" value = \" " + Encode.forHtml(cust.getCity())  +"\">"
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
		System.out.println("***************SHOPPING SERVLET - GET CHECKOUT PRICE***************");
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
									+ "<p id=\"totalprice\">P" + Encode.forHtml(String.format("%.2f", total)) + "</p>"    
								+ "</div>"
							+ "</div>";
		
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlBookList);
	    System.out.println("***************/SHOPPING SERVLET - GET CHECKOUT PRICE/***************");
	}

	
	private void checkOutConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - CHECKOUT CONFIRM***************");
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
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		System.out.println("***************/SHOPPING SERVLET - CHECKOUT CONFIRM/***************");
	}
	
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - ADD TO CART***************");
		
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		System.out.println("Book ID is: " + bookID);
		Book book = BookService.getBook(bookID);
		
		HttpSession session = request.getSession();
		//int qty = Integer.parseInt(request.getParameter("qty"));
		
		boolean guest = true;
		String email = "Guest";
		
		try {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("USER")){
					guest = false;
					email = currentCookie.getValue();
				}
			}
		}
		
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		if(guest){
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
		
		Cookie theCookie;
		theCookie = new Cookie("CART", "not empty"); 
		theCookie.setMaxAge(60*60*24*7*30); //30 minutes
		
		response.getWriter().write("PASS-ADD-CART");
		}
		catch(Exception e) {
			response.getWriter().write("FAIL-ADD-CART");
		}
		System.out.println("***************/SHOPPING SERVLET - ADD TO CART/***************");
	}
	
	private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - REMOVE FROM CART***************");
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int bookid = Integer.parseInt(request.getParameter("remove"));
		
		boolean guest = true;
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("USER")){
					guest = false;
				}
			}
		}
		
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		
		if(guest){
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
		
		if(cartlist.isEmpty()) {
			if(cookies!=null){
				for(int i = 0; i < cookies.length; i++){
					Cookie currentCookie = cookies[i];
					if(currentCookie.getName().equals("CART")){
						currentCookie.setMaxAge(0);
						response.addCookie(currentCookie);
					}
				}
			}
		}
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
		System.out.println("***************/SHOPPING SERVLET - REMOVE FROM CART/***************");
	}
	
	private List<Shoppingcart> getShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - GET SHOPPING CART***************");
		HttpSession session = request.getSession();
		boolean guest = true;
		String email = "Guest";
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("USER")){
					guest = false;
					email = currentCookie.getValue();
				}
			}
		}
		
		List<Shoppingcart> cartlist = (List<Shoppingcart>) session.getAttribute("cartlist");
		
		if(cartlist == null){
			System.out.println("Cart is empty!");
			if(guest) {
				System.out.println("Cartlist is null, user is a guest");
				cartlist = new ArrayList<Shoppingcart>();
			}
			else {
				System.out.println("Cartlist is null, user is not a guest");
				cartlist = ShoppingcartService.getShoppingCartList(email);
			}
		}
		else{
			if(!guest) {
				System.out.println("Cartlist is not null, user is not a guest");
				cartlist = ShoppingcartService.getShoppingCartList(email);
			}
			System.out.println("Cart is not empty!");
			System.out.println(cartlist);
		}
		System.out.println("***************/SHOPPING SERVLET - GET SHOPPING CART/***************");
		return cartlist;
		
	}
	
	private void getCartCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************SHOPPING SERVLET - GET CART COUNT***************");
		List<Shoppingcart> cartlist = getShoppingCart(request, response);
		String cartCount = Integer.toString(cartlist.size());
		System.out.println("Cart count: " + cartCount);
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(cartCount);
	    System.out.println("***************/SHOPPING SERVLET - GET CART COUNT/***************");
	}
	
	private int checkContains(Shoppingcart sc, List<Shoppingcart> cartlist){
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

		case "/getCartList" : System.out.println("I am at doGet method, getCartList case");
							getCartList(request, response);
							break;
		case "/checkout" :  System.out.println("I am at doGet method, checkout case");
							getCheckOutButton(request, response);
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
		case "/getCartCount" : getCartCount(request, response);
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
			case "/addToCart": System.out.println("I am at shoppingServlet, addToCart method");
			   addToCart(request, response);
			   break;
		}
	}

}
