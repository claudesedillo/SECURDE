package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.encoder.Encode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Customer;
import beans.Order;
import service.CustomerService;
import service.DecryptorService;
import service.OrderService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = {"/changePassword",
						   "/getUserOrders",
						   "/getAccountDetails",
						   "/updateAccountDetails"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getServletPath()) {
			case "/getUserOrders": System.out.println("I am at UserServlet, getOrderDetails case");
								   getUserOrders(request, response);
								   break;
			case "/getAccountDetails": System.out.println("I am at UserServlet, getOrderDetails case");
			   						getAccountDetails(request, response);
			   						break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getServletPath()) {
			case "/updateAccountDetails": System.out.println("I am at UserServlet, getOrderDetails case");
					updateAccountDetails(request, response);
					break;
			case "/changePassword": System.out.println("I am at UserServlet, getOrderDetails case");
					changePassword(request, response);
					break;
		}
	}
	    
	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***************USER SERVLET - CHANGE PASSWORD***************");
		
		
	}
	
	
    private void updateAccountDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************USER SERVLET - UPDATE ACCOUNT DETAILS***************");
    	String name = request.getParameter("name"),
    			firstname, lastname,
    		email = request.getParameter("email"),
    		streetaddress = request.getParameter("streetAddress"),
    		city = request.getParameter("city"),
    		province = request.getParameter("province"),
    		phonenumber = request.getParameter("phone");
    	int postalcode = Integer.parseInt(request.getParameter("postalcode"));
    	
    	String[] names = name.split("\\s+", 2);
    	firstname = names[0];
    	lastname = names[1];
    	Customer newCustomer = new Customer(email, firstname, lastname, streetaddress, city, province, phonenumber, postalcode);
    	
    	response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    
    	if(CustomerService.updateCustomerDetails(newCustomer))
    		response.getWriter().write("SUCCESS-UPDATE-CUSTOMER");
    	else response.getWriter().write("FAIL-UPDATE-CUSTOMER");
    	
    	System.out.println("***************/USER SERVLET - UPDATE ACCOUNT DETAILS/***************");
    }
    
    private void getUserOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************USER SERVLET - GET USER ORDERS***************");
    	String email = "";
    	Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++){
			Cookie currentCookie = cookies[i];
			if(currentCookie.getName().equals("USER")){
				email = DecryptorService.decryptCookie(currentCookie);
			}
		}

    	ArrayList<Order> orderList = OrderService.getUserOrder(email);
    	String htmlOrderlist = "";
    	
    	for(Order o: orderList) {
    		int orderID = o.getOrderID();
    		htmlOrderlist +=  "<tr>" +
			         "<td><a data-toggle=\"modal\" data-target=\"#details-modal\" class = \"view-orderdetails-btn\" data-orderid = \"" + orderID +"\" id=\"ordernum\">" + orderID +"</a></td>" +
			         "<td>" + Encode.forHtml(o.getEmail()) +"</td>" +
			         "<td>" + Encode.forHtml(o.getFirstName() + " " + o.getLastName()) + "</td>" +
			         "<td>" + Encode.forHtml(String.format("%d", o.getTotal())) +"</td>" +
		         "</tr>";
    	}
		response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlOrderlist);
    	System.out.println("***************/USER SERVLET - GET USER  ORDERS/***************");
    }
    
    private void getAccountDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("***************USER SERVLET - GET ACCOUNT DETAILS***************");
    	String email = "";
    	Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++){
			Cookie currentCookie = cookies[i];
			if(currentCookie.getName().equals("USER")){
				email = DecryptorService.decryptCookie(currentCookie);
			}
		}
		Customer customer = CustomerService.getCustomer(email);
    	
		Gson gson = new GsonBuilder().create();
		String customerJson = gson.toJson(customer);
		response.setContentType("application/json"); 
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(customerJson);
    	System.out.println("***************/USER SERVLET - GET ACCOUNT DETAILS/***************");
    }
}
