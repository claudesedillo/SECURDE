package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

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
@WebServlet(urlPatterns = { "/getOrders",
							"/getOrderSummary",
		   					"/getOrderDetails",
						    "/getAdminList",
						    "/editAdminGet",
						    "/editAdminConfirm"
		   					})

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void getOrderSummary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - GET ORDER SUMMARY***************");
    	int orderID = Integer.parseInt(request.getParameter("orderID"));
    	System.out.println("Order id is: " + orderID);
    	Order order = OrderService.getOrder(orderID);
    	String htmlOrderSummary = "";
    	String name = order.getFirstName() + " " + order.getLastName();
    	String address = order.getStreetAddress() + " " + order.getCity() + "<br>" + order.getProvince() + " " 
    	+ order.getPostalCode();
    	String contact = order.getPhoneNumber();
    	int subtotal = order.getTotal();
    	
    	htmlOrderSummary += "<div class=\"col-sm-9\">" +
    						"    <p id=\"orderdetails-name\">" + name + "</p>" +
    						"    <p id=\"orderdetails-address\">" + address + "</p>" +
    						"	 <p id=\"orderdeatails-contact\">" + contact + "</p>" +
    						"    <br>" + 
    						"    <p class=\"detail-subheader\">Status: Out for Delivery</p>" +
    						"</div>" +
    						"<div class=\"col-sm-3\">" + 
    						"		<p class=\"labels\">Subtotal: </p> <p class=\"info\" id=\"orderdetails-subtotal\">" + subtotal + "</p><br>" + 
    						"   	<p class=\"labels\">Shipping: </p> <p class=\"info\" id=\"orderdetails-shippingfee\">100</p><br>" + 
    						"   	<p class=\"labels\">Total: </p> <p class=\"info\" id=\"orderdetails-total\">" + (subtotal + 100) + "</p><br>" + 
    						"</div>";
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlOrderSummary);
    	System.out.println("***************/ADMIN SERVLET - GET ORDER SUMMARY/***************");
    }
    
    private void getOrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - GET ORDER DETAILS***************");
    	System.out.println("Order id is: " + request.getParameter("orderID"));
    	ArrayList<OrderList> orderlist = OrderListService.getOrderDetails(Integer.parseInt(request.getParameter("orderID")));
    	String htmlOrderDetails = "";
    	
    	for(OrderList o: orderlist) {
    		Book book = BookService.getBook(o.getBookID());
    		htmlOrderDetails += "<tr class = \"book-order-detail\">" +
    							"    <td>" + book.getTitle() + "</td>" +
    							"    <td>" + o.getQuantity() + "</td>" +
    							"	 <td>" + book.getPrice() + "</td>" +
    							"    <td>P" + (o.getQuantity() * book.getPrice()) + "</td>" +
    							"</tr>";
    	}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlOrderDetails);
    	System.out.println("***************/ADMIN SERVLET - GET ORDER DETAILS/***************");
    }
    

	
    private void getOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************ADMIN SERVLET - GET ORDERS***************");
    	ArrayList<Order> orderList = OrderService.getAllOrders();
    	String htmlOrderlist = "";
    	
    	for(Order o: orderList) {
    		int orderID = o.getOrderID();
    		htmlOrderlist +=  "<tr>" +
	    				         "<td><a data-toggle=\"modal\" data-target=\"#details-modal\" class = \"view-orderdetails-btn\" data-orderid = \"" + orderID +"\" id=\"ordernum\">" + orderID +"</a></td>" +
	    				         "<td>" + o.getEmail() +"</td>" +
	    				         "<td>" + o.getFirstName() + " " + o.getLastName() + "</td>" +
	    				         "<td>" + (o.getTotal() + 100) +"</td>" +
    				         "</tr>";
    	}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlOrderlist);
    	System.out.println("***************/ADMIN SERVLET - GET ORDERS/***************");
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
				
		case "/getOrderSummary": System.out.println("I am at adminServlet, getOrderDetails case");
						getOrderSummary(request, response);
						break;				
		case "/getAdminList" : System.out.println("I am at adminServlet, getAdminList case");
							getAdminList(request, response);	
							break;
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("I am at adminServlet at doPost. Servlet path is " + request.getServletPath());
			switch(request.getServletPath()) {

				case "/editAdminGet" : System.out.println("I am at adminServlet, editAdminGet case");
								editAdmin(request, response);	
								break;
				case "/editAdminConfirm" : System.out.println("I am at adminServlet, editAdminConfirm");
								editAdminConfirm(request, response);	
								break;
			}
	}
}
