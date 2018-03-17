package servlets;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.*;
import org.owasp.esapi.errors.EncryptionException;

import beans.Admin;
import beans.Customer;
import service.AdminService;
import service.CustomerService;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/login", "/adminLogin", "/signup", "/emailKey", "/logout"})
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
		System.out.println(request.getServletPath());
		if(request.getServletPath().equals("/logout")){
			System.out.println("I am at login servlet, doPost, signout case");
			signout(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Login do post: " + request.getServletPath());
		if(request.getServletPath().equals("/login")){
			login(request, response);
		}
		else if(request.getServletPath().equals("/emailKey") ){
			checkAdminLogin(request, response);
		}
		else if(request.getServletPath().equals("/signup")){
			signup(request, response);
		}
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************SHOPPING SERVLET - LOGIN***************");
		String user = request.getParameter("email");
		String pass = request.getParameter("password");	
		System.out.println(user + " " + pass);
		
		if(CustomerService.doesCustomerExist(user)) {
			if(CustomerService.checkLogin(user, pass)){
				//Login hash cookie
				Cookie cookie = new Cookie("logged", user);
				cookie.setMaxAge(60*60*24*365*2);
				response.addCookie(cookie);
				System.out.println("Succesful Login (Customer)");
				response.getWriter().write("PASS-LOGIN-CUSTOMER");
			}
			
			else{
				System.out.println("Customer: wrong email/pass");
				response.getWriter().write("FAIL-LOGIN-CUSTOMER");
			}
		}
		
//		if(AdminService.checkLogin(user, pass)){
//			String emailKey = UUID.randomUUID().toString().replace("-", "");
//			emailKey = emailKey.substring(0, 5);
//			Email email = new Email(user, "ADMIN LOGIN ATTEMPTED", "Authentication Key : " + emailKey);
//			sendEmail(request, response, email);
//			HttpSession session = request.getSession();
//			session.setAttribute("emailkey", emailKey);
//			request.setAttribute("emailkey", session.getAttribute("emailkey"));
//			response.sendRedirect("adminEmailDoor.html");
//			response.getWriter().write("PASS-LOGIN-ADMIN");
//		}	
//		else{
//			System.out.println("Wrong email/pass ma dude");
//			request.getRequestDispatcher("Portal.jsp").forward(request, response);
//			response.getWriter().write("FAIL-LOGIN-ADMIN");
//		}
		System.out.println("***************/SHOPPING SERVLET - LOGIN/***************");
	}
	
	private void checkAdminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		HttpSession session = request.getSession();
//		String emailKey = (String) session.getAttribute("emailkey");
//		String inputKey = request.getParameter("emailkey");
//		System.out.println("session key : " + emailKey);
//		if(inputKey.equals(emailKey)){
//			System.out.println("Succesful Login (Admin)");
//			response.sendRedirect("AdminDashboard.jsp");
//		}
//		else{
//			System.out.println("wrong input >:(");
//			response.sendRedirect("adminEmailDoor.html");
//		}
		System.out.println("***************SHOPPING SERVLET - ADMIN LOGIN***************");
		String user = request.getParameter("email");
		String pass = request.getParameter("password");	
		System.out.println(user + " " + pass);
		
		if(AdminService.checkLogin(user, pass)){
			String emailKey = UUID.randomUUID().toString().replace("-", "");
			emailKey = emailKey.substring(0, 5);
			Email email = new Email(user, "ADMIN LOGIN ATTEMPTED", "Authentication Key : " + emailKey);
			sendEmail(request, response, email);
			HttpSession session = request.getSession();
			session.setAttribute("emailkey", emailKey);
			request.setAttribute("emailkey", session.getAttribute("emailkey"));
			response.sendRedirect("adminEmailDoor.html");
			response.getWriter().write("PASS-LOGIN-ADMIN");
		}	
		else{
			System.out.println("Wrong email/pass ma dude");
			request.getRequestDispatcher("Portal.jsp").forward(request, response);
			response.getWriter().write("FAIL-LOGIN-ADMIN");
		}
		System.out.println("***************/SHOPPING SERVLET - ADMIN LOGIN/***************");
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String user = request.getParameter("email");
		String pass = request.getParameter("password");	
		String pass2 = request.getParameter("password2");	
		String type = request.getParameter("btn-signup");
		
		//boolean isValid = ESAPI.validator().isValidInput("signup", user, "email", 45, false);
		//System.out.println("isValid : " + isValid);
		System.out.println("Pass : " + pass);
		String passHash = null; 
		try {
			passHash = ESAPI.encryptor().hash(pass, "testtest");
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(passHash);
		
		if(type.equals("cust-signup")){
			String secQ = request.getParameter("securityQ"),
				   secA = request.getParameter("securityA"),
				   firstname = request.getParameter("fname"),
				   lastname = request.getParameter("lname");
				   //address = request.getParameter("streetaddress"),
				   //city = request.getParameter("city"),
				   //province = request.getParameter("province"),
				   //phone = request.getParameter("phone");
			//int postal = Integer.parseInt(request.getParameter("postal"));
			if(!CustomerService.checkUser(user)){
				if(pass.equals(pass2)){
					//Login hash cookie
					Cookie cookie = new Cookie("logged", user);
					cookie.setMaxAge(60*60*24*365*2);
					response.addCookie(cookie);
					
					Customer cust = new Customer();
					cust.setEmail(user);
					cust.setHashedpassword(pass);
					cust.setSecurityquestion(secQ);
					cust.setSecurityanswer(secA);
					cust.setFirstname(firstname);
					cust.setLastname(lastname);
					//cust.setStreetaddress(address);
					//cust.setPostalcode(postal);
					//cust.setCity(city);
					//cust.setProvince(province);
					//cust.setPhonenumber(phone);
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
			
			
			System.out.println(user + " " + type + " " + fName + " " +  lName + " " + role);
			
			if(!AdminService.checkUser(user)){
					Admin admin = new Admin();
					admin.setEmail(user);
					String adminPass = UUID.randomUUID().toString().replace("-", "");
					adminPass = adminPass.substring(0, 10);
					Email email = new Email(user, "Your Admin Account has been Created!", "Password : " + adminPass);
					sendEmail(request, response, email);
					admin.setHashedpassword(adminPass);
					admin.setFirstname(fName);
					admin.setLastname(lName);
					admin.setRole(role);
					AdminService.addAdmin(admin);
					System.out.println("Succesful signup (ADMIN)");
					request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
			}
			else{
				System.out.println("Your email already exists!!! >:(");
				request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
			}
		}
	}
	
	private void signout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************SHOPPING SERVLET - SIGN OUT***************");
		request.getSession().invalidate();
		
		// kill cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("logged")){
					currentCookie.setMaxAge(0);
					response.addCookie(currentCookie);
				}
			}
		}
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		System.out.println("***************/SHOPPING SERVLET - SIGN OUT/***************");
	}

	private void sendEmail(HttpServletRequest request, HttpServletResponse response, Email email) throws ServletException, IOException{
		final String user="indigo.emailkey@gmail.com";
		final String pass="#96NDIGO@SECURDE78#";  
		
		 Properties props = new Properties();
		 props.put("mail.smtp.host", "smtp.gmail.com");
	     //below mentioned mail.smtp.port is optional
	     props.put("mail.smtp.port", "587");		
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	     
	     Session sesObj = Session.getInstance(props,new javax.mail.Authenticator(){
	    	 protected PasswordAuthentication getPasswordAuthentication() {
	    		 return new PasswordAuthentication(user,pass);
	    	 }
	     });
	     
	     try {
	    	 MimeMessage message = new MimeMessage(sesObj);
	         message.setFrom(new InternetAddress(user));
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getEmailAddress()));
	         message.setSubject(email.getHeader());
	         message.setText(email.getBody());
	         
	         Transport.send(message);
	      }
	      catch(Exception e)
	      {
	      	 e.printStackTrace();
	      }
	}
	
	public class Email{
		String emailAddress;
		String header;
		String body;
		
		public Email(){}
		
		public Email(String emailAddress, String header, String body){
			this.emailAddress = emailAddress;
			this.header = header;
			this.body = body;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public String getHeader() {
			return header;
		}

		public String getBody() {
			return body;
		}
	}
}
