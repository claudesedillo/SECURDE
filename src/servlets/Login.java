package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/login", "/adminLogin"})
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
		String user = request.getParameter("email");
		String pass = request.getParameter("password");	
		String type = request.getParameter("bt");
		System.out.println(user + " " + pass + " " + type);
		
		if(type.equals("signin") && CustomerService.checkLogin(user, pass)){
			System.out.println("Succesful Login, pa add po name or something sa index ty Jes ;P");
		}
		else {
			System.out.println("Wrong email/pass gago");
		}
		
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}

}
