package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Authentication
 */
@WebFilter(urlPatterns={"*.html" ,"*.jsp"})
public class Authenticate implements Filter {
	private HttpServletRequest req;
	private HttpServletResponse res;
	private boolean user;

    /**
     * Default constructor. 
     */
    public Authenticate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;
		user = false; //Fixed infinite redirection
		boolean admin = false, cartEmpty = true, adminAttempt = false;
		String url = req.getServletPath();
		
		System.out.println("\n*************AUTHENTICATE SERVLET*********************");
		System.out.println("Checking if cookies and session exists.");
		
		//Check if the cookie "USER" exists.
		Cookie[] cookieList = req.getCookies();
		System.out.println("Authenticate url path: "+  url);
		if(cookieList != null) {
			for(Cookie c : cookieList) {
				if(c.getName().equals("USER")) {
					System.out.println("USER Cookie found!");
						
					if(c.getMaxAge() != 0)
						user = true; //if it exists, proceed.
				
				} 
				else if(c.getName().equals("ADMIN")) {
					System.out.println("ADMIN Cookie found!");
						
					if(c.getMaxAge() != 0){
						admin = true;
					}
				}
				else if(c.getName().equals("CART")) {
					System.out.println("CART cookie found!");
					
					if(c.getMaxAge() != 0){
						cartEmpty = false;
					}
				}
				else if(c.getName().equals("ADMIN-ATTEMPT")) {
					System.out.println("ADMIN ATTEMPT found!");
					
					if(c.getMaxAge() != 0){
						adminAttempt = true;
					}
				}
			}
		}
		
		System.out.println("***********************AUTHENTICATE FILTER LOG**********************");
		System.out.println("Url:" + url);
		System.out.println("User exists: " + user);
		System.out.println("Admin exists:" + admin);
		System.out.println("Admin sign in attempt exists: " + adminAttempt);
		System.out.println("Is cart empty? : " + cartEmpty);
		
		System.out.println("Is it user or admin?::");
		if(user) {
			System.out.println("User");
		}
		else if(admin) {
			System.out.println("Admin");
		}
		else if(adminAttempt){
			System.out.println("Admin Attempt!");
		}
		else if(!cartEmpty) {
			System.out.println("Cart not empty!");
		}
		switch(url) {
            //Public Pages
			case "/Portal.jsp":
			case "/nav.html":
			case "/usernav.html":
			case "/footer.html":
			case "/Index.jsp":
			case "/ErrorPage.html":
					 System.out.println("Public page! continue to your page");
					 chain.doFilter(request, response);
				 break;
			
			//For not logged in users only
			case "/SignUp.jsp":
					if(!user && !admin) {
						 System.out.println("Not logged in! continue to sign up");
						 chain.doFilter(request, response);
					}
					else if(user) {
						 System.out.println("User found! redirecting to index"); 
						 res.sendRedirect("Index.jsp");
					}
					else {
						System.out.println("Admin found! Redirecting to adminDashboard"); 
						res.sendRedirect("AdminDashboard.jsp");
					}
					break;
					
			//For users and guests with shopping carts only
			case "/Checkout.jsp":
				if(admin) {
					System.out.println("Admin detected! redirecting to admindashboard"); 
					res.sendRedirect("AdminDashboard.jsp");
				}
				else if(!cartEmpty) {
					 System.out.println("Cart is not empty! continue to checkout");
					 chain.doFilter(request, response);
				}
				else {
					System.out.println("Cart empty! Redirecting to error page");
					res.sendRedirect("ErrorPage.html");
				}
				break;
				
			//For users and guests only
			case "/ForgotPasswordPortal.jsp":
			case "/Cart.jsp":
				if(admin) {
					System.out.println("Admin found! redirecting to dashboard"); 
					res.sendRedirect("AdminDashboard.jsp");
				}
				else{
					 System.out.println("Not an admin! redirecting to page");
					 chain.doFilter(request, response);
				}
				break;
				
			//For users only
			case "/AccountCenter.jsp":
				if(user) {
					 System.out.println("User found! redirecting to account center");
					 chain.doFilter(request, response);
				}
				else {
                    System.out.println("No user found! redirecting to error page"); 
                    res.sendRedirect("ErrorPage.html");
				}
				break;
            //Admin Pages
			case "/EmailDoor.jsp":
				if(adminAttempt) {
					 	System.out.println("Admin attempt found! continue to emailDoor");
					 	chain.doFilter(request, response);
				}
				else {
                    	System.out.println("No admin attempt found! redirecting to errorpage.html"); 
                    	res.sendRedirect("ErrorPage.html");
				}
				break;
				
            case "/AdminDashboard.jsp":
                                if(user) {
									 System.out.println("User found! redirecting to index"); 
									 res.sendRedirect("Index.jsp");
								 }
                                else if(admin){
               					 	System.out.println("Admin found! continue to page");
               					 	chain.doFilter(request, response);
                                }
                                else {
                                    System.out.println("No user found! redirecting to error page"); 
                                    res.sendRedirect("ErrorPage.html");
                                }
                                break;
		}
		System.out.println("*********************************************\n\n");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}