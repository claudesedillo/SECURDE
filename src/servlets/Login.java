package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Admin;
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
		
		if(request.getServletPath().equals("/login")){
			String user = request.getParameter("email");
			String pass = request.getParameter("password");	
			String type = request.getParameter("btn-signin");
			System.out.println(user + " " + pass + " " + type );
			
			if(type.equals("cust-signin") && CustomerService.checkLogin(user, pass)){
				System.out.println("Succesful Login (Customer)");
				request.getRequestDispatcher("Index.jsp").forward(request, response);
			}
			
			else if(type.equals("admin-signin") && AdminService.checkLogin(user, pass)){
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
			String type = request.getParameter("btn-signup");
			
			if(type.equals("cust-signup")){
				String secQ = request.getParameter("securityQ");
				String secA = request.getParameter("securityA");
				System.out.println(user + " " + pass + " " + type + " " + secQ + " " +  secA);
				
				if(!CustomerService.checkUser(user)){
					if(pass.equals(pass2)){
						Customer cust = new Customer();
						cust.setEmail(user);
						cust.setHashedpassword(pass);
						cust.setSecurityquestion(secQ);
						cust.setSecurityanswer(secA);
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
			else if(type.equals("admin-signup")){
				String fName = request.getParameter("firstName");
				String lName = request.getParameter("lastName");
				String role = request.getParameter("role");
				System.out.println(user + " " + pass + " " + type + " " + fName + " " +  lName + " " + role);
				
				if(!AdminService.checkUser(user)){
					if(pass.equals(pass2)){
						Admin admin = new Admin();
						admin.setEmail(user);
						admin.setHashedpassword(pass);
						admin.setFirstname(fName);
						admin.setLastname(lName);
						admin.setRole(role);
						AdminService.addAdmin(admin);
						System.out.println("Succesful signup (ADMIN)");
						request.getRequestDispatcher("adminPage.html").forward(request, response);
					}
					else{
						System.out.println("Your passwords dont match!!! >:(");
						request.getRequestDispatcher("adminSignUp.html").forward(request, response);
					}
				}
				else{
					System.out.println("Your email already exists!!! >:(");
					request.getRequestDispatcher("adminSignUp.html").forward(request, response);
				}
			}
		}
	}

}
