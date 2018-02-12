package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Customer;
import service.AdminService;
import service.CustomerService;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/login", "/adminLogin", "/signup"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		switch(request.getServletPath()) {
			case "/login":  System.out.println("i am at login case " + request.getParameter("username") + " " + request.getParameter("password") + " xd");
							request.getRequestDispatcher("Index.jsp").forward(request, response);
							break;
			case "/adminLogin": System.out.println("i am at adminLogin case " + request.getParameter("adminEmail") + request.getParameter("adminPassword") + " xdd");
								response.sendRedirect("adminPage.html");
							break;
			
		}*/
		if(request.getServletPath().equals("/login")){
			String user = request.getParameter("email");
			String pass = request.getParameter("password");	
			String type = request.getParameter("bt");
			System.out.println(user + " " + pass + " " + type);
			
			if(type.equals("signin") && CustomerService.checkLogin(user, pass)){
				System.out.println("Succesful Login (Customer)");
				request.getRequestDispatcher("Index.jsp").forward(request, response);
			}
			
			else if(type.equals("signin") && AdminService.checkLogin(user, pass)){
				System.out.println("Succesful Login (Admin)");
				response.sendRedirect("adminPage.html");
			}
			else {
				System.out.println("Wrong email/pass gago");
				request.getRequestDispatcher("Index.jsp").forward(request, response);
			}
		}
		else if(request.getServletPath().equals("/signup")){
			String user = request.getParameter("email");
			String pass = request.getParameter("password");	
			String pass2 = request.getParameter("password2");	
			String type = request.getParameter("bt");
			System.out.println(user + " " + pass + " " + pass2 + " " + type);
			
			if(!CustomerService.checkUser(user)){
				if(pass.equals(pass2)){
					Customer cust = new Customer();
					cust.setEmail(user);
					cust.setHashedpassword(pass);
					CustomerService.addCustomer(cust);
					System.out.println("Succesful signup (Customer)");
					request.getRequestDispatcher("Index.jsp").forward(request, response);
				}
				else{
					System.out.println("Your passwords dont match!!! >:(");
					request.getRequestDispatcher("SignUp.jsp").forward(request, response);
				}
			}
			else{
				System.out.println("Your email already exists!!! >:(");
				request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			}
		}
	}

}
