package servlets;

import java.io.IOException;
import java.sql.Timestamp;
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
import beans.LoginAttempt;
import service.AdminService;
import service.CustomerService;
import service.LoginAttemptService;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/login", 
						"/adminLogin", 
						"/signup", 
						"/emailKey", 
						"/logout" , 
						"/forgotPassword", 
						"/forgetKey", 
						"/newPasswordConfirm"})
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
		
		//System.out.println("IP : " + request.getRemoteAddr());
		//System.out.println(request.getRemoteHost());
		
		// TODO Auto-generated method stub
		System.out.println("Login do post: " + request.getServletPath());
		if(request.getServletPath().equals("/login")){
			login(request, response);
		}
		else if(request.getServletPath().equals("/signup")){
			signup(request, response);
		}
		else if(request.getServletPath().equals("/adminLogin")){
			adminLogin(request, response);
		}
		else if(request.getServletPath().equals("/emailKey") ){
			checkAdminLogin(request, response);
		}
		else if(request.getServletPath().equals("/forgotPassword")){
			forgotPassword(request, response);
		}
		else if(request.getServletPath().equals("/forgetKey")){
			forgotKey(request, response);
		}
		else if(request.getServletPath().equals("/newPasswordConfirm")){
			newPasswordConfirm(request, response);
		}
	}

	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("***************LOGIN SERVLET - ADMIN LOGIN***************");
		String user = request.getParameter("email");
		String pass = request.getParameter("password");	
		System.out.println(user + " " + pass);
		
		String IpAddress = request.getRemoteAddr();
		if(!LoginAttemptService.checkIfExists(IpAddress)){
			java.sql.Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			LoginAttemptService.addLoginAttempt(new LoginAttempt(IpAddress,currentTime,0));
		}
		
		if(AdminService.checkLogin(user, pass)){
			if(!LoginAttemptService.checkForBruteForce(IpAddress)){
				String emailKey = UUID.randomUUID().toString().replace("-", "");
				emailKey = emailKey.substring(0, 5);
				//TODO: REMOVE THIS ASAP. FOR CHECKING ONLY
				System.out.println("emailKey is: " + emailKey);
				Email email = new Email(user, "ADMIN LOGIN ATTEMPTED", "Authentication Key : " + emailKey);
				sendEmail(request, response, email);
				HttpSession session = request.getSession();
				session.setAttribute("emailkey", emailKey);
				session.setAttribute("email", user);
				request.setAttribute("emailkey", session.getAttribute("emailkey"));
				//response.sendRedirect("adminEmailDoor.html");
				//request.getRequestDispatcher("EmailDoor.jsp").forward(request, response);
				response.getWriter().write("PASS-LOGIN-ADMIN");
			}
		}	
		else{
			if(LoginAttemptService.checkForBruteForce(IpAddress)){
				System.out.println("BRUTE FORCE FOR IP : " + IpAddress + " DETECTED");
			}
			else System.out.println("Wrong email/pass ma dude");
			request.getRequestDispatcher("Portal.jsp").forward(request, response);
			response.getWriter().write("FAIL-LOGIN-ADMIN");
		}
		System.out.println("***************/LOGIN SERVLET - ADMIN LOGIN/***************");
	}

	private void checkAdminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		System.out.println("***************LOGIN SERVLET - CHECK ADMIN LOGIN***************");
		HttpSession session = request.getSession();
		String emailKey = (String) session.getAttribute("emailkey");
		String email = (String) session.getAttribute("email");
		String inputKey = "";
		
		for(int x = 1; x < 6; x+=1){
			inputKey += request.getParameter("s" + Integer.toString(x));
		}
		
		System.out.println("session key : " + emailKey);
		System.out.println("input key : " + inputKey);
		
		if(inputKey.equals(emailKey)){
			
			try {
				email = ESAPI.encryptor().encrypt(email);
			} catch (EncryptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Cookie theCookie;
			theCookie = new Cookie("ADMIN", email); 
			theCookie.setMaxAge(60*30); //30 minutes
				
			//Checking
			System.out.println("Cookie placed: " + theCookie.getName());
			System.out.println("Cookie value: " + theCookie.getValue());

			//Add cookie
			response.addCookie(theCookie);
			System.out.println("Succesful Login (Admin)");
			response.sendRedirect("AdminDashboard.jsp");
		}
		else{
			System.out.println("wrong input >:(");
			//response.sendRedirect("adminEmailDoor.html");
			request.getRequestDispatcher("EmailDoor.jsp").forward(request, response);
		}
		System.out.println("***************/LOGIN SERVLET - CHECK ADMIN LOGIN/***************");
	}
	
	private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************LOGIN SERVLET - FORGOT PASSWORD***************");
		// TODO Auto-generated method stub
		String user = request.getParameter("email");
		if(CustomerService.doesCustomerExist(user)) {
			String emailKey = UUID.randomUUID().toString().replace("-", "");
			emailKey = emailKey.substring(0, 5);
			Email email = new Email(user, "PASSWORD RECOVERY", "Authentication Key : " + emailKey);
			sendEmail(request, response, email);
			HttpSession session = request.getSession();
			session.setAttribute("emailkey", emailKey);
			request.setAttribute("emailkey", session.getAttribute("emailkey"));
			session.setAttribute("user", user);
			request.setAttribute("user", session.getAttribute("user"));
			//response.sendRedirect("PassRecoveryEmailDoor.html");
			response.sendRedirect("ResetPasswordDoor.jsp");
		}
		else response.sendRedirect("ForgotPasswordPortal.jsp");
		System.out.println("***************/LOGIN SERVLET - FORGOT PASSWORD/***************");
	}

	private void forgotKey(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************LOGIN SERVLET - FORGOT KEY***************");
		HttpSession session = request.getSession();
		String emailKey = (String) session.getAttribute("emailkey");
		String inputKey = "";
		for(int x = 1; x < 6; x+=1){
			inputKey += request.getParameter("s" + Integer.toString(x));
		}
		System.out.println("session key : " + emailKey);
		if(inputKey.equals(emailKey)){
			response.sendRedirect("ResetPassword.jsp");
		}
		else{
			System.out.println("wrong input >:(");
			response.sendRedirect("ResetPasswordDoor.jsp");
		}
		System.out.println("***************/LOGIN SERVLET - FORGOT KEY/***************");
	}
	
	private void newPasswordConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************LOGIN SERVLET - NEW PASSWORD CONFIRM***************");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");	
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		
		if(isPasswordValid(pass, pass2)){
			//Hashing Password
			try {
				pass = ESAPI.encryptor().encrypt(pass);
			} catch (EncryptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Customer newCust = CustomerService.getCustomer(user);
			newCust.setHashedpassword(pass);
			CustomerService.updateCustomer(newCust);
			
			System.out.println("Password of Customer Succesfully Updated");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
		else request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
		System.out.println("***************/LOGIN SERVLET - NEW PASSWORD CONFIRM/***************");
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************LOGIN SERVLET - SIGN UP***************");
		String user = request.getParameter("email");
		String pass = request.getParameter("password");	
		String pass2 = request.getParameter("password2");	
		String type = request.getParameter("btn-signup");
		
		if(type.equals("cust-signup")){
			String secQ = request.getParameter("securityQ"),
				   secA = request.getParameter("securityA"),
				   firstname = request.getParameter("fname"),
				   lastname = request.getParameter("lname");
			if(!CustomerService.checkUser(user)){
				if(isPasswordValid(pass, pass2)){
					//Hashing Password
					try {
						pass = ESAPI.encryptor().encrypt(pass);
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Customer cust = new Customer();
					cust.setEmail(user);
					cust.setHashedpassword(pass);
					cust.setSecurityquestion(secQ);
					cust.setSecurityanswer(secA);
					cust.setFirstname(firstname);
					cust.setLastname(lastname);
					CustomerService.addCustomer(cust);
					System.out.println("Succesful signup (Customer)");
					request.getRequestDispatcher("Index.jsp").forward(request, response);
				}
				else request.getRequestDispatcher("SignUp.jsp").forward(request, response);
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
					
					//Hashing Password
					try {
						adminPass = ESAPI.encryptor().encrypt(adminPass);
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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
		System.out.println("***************/LOGIN SERVLET - SIGN UP/***************");
	}
	
	public boolean isPasswordValid(String pass, String pass2){
		System.out.println("***************LOGIN SERVLET - IS PASSWORD VALID***************");
		if(pass.equals(pass2)){
			if(pass.length()>= 8){
				if(!pass.equals(pass.toUpperCase()) && !pass.equals(pass.toLowerCase())){
					if(!pass.matches("[A-Za-z0-9 ]*")){
						if(!pass.contains("&") && !pass.contains("<") && !pass.contains(">")){
							return true;
						}
						else{
							System.out.println("Your password contains illegal characters, i.e. <, >, and &");
							return false;
						}
					}
					else{
						System.out.println("Your password must contain a special, non alpha numeric character, ex : !,?, or %");
						return false;
					}	
				}
				else{
					System.out.println("Your password must contain both UPPER CASE and LOWER CASE letters");
					return false;
				}
			}
			else{
				System.out.println("Your password is too weak (LESS THAN 8 CHARACTERS)");
				return false;
			}
		}
		else{
			System.out.println("Your passwords dont match!!! >:(");
			return false;
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************LOGIN SERVLET - LOGIN***************");
		String user = request.getParameter("email");
		String pass = request.getParameter("password");	
		System.out.println(user + " " + pass);
		
		String IpAddress = request.getRemoteAddr();
		if(!LoginAttemptService.checkIfExists(IpAddress)){
			java.sql.Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			LoginAttemptService.addLoginAttempt(new LoginAttempt(IpAddress,currentTime,0));
		}
		
		if(CustomerService.doesCustomerExist(user)) {
			if(CustomerService.checkLogin(user, pass)){
				if(!LoginAttemptService.checkForBruteForce(IpAddress)){
					//Login hash cookie
					
					try {
						user = ESAPI.encryptor().encrypt(user);
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Cookie cookie = new Cookie("USER", user);
					cookie.setMaxAge(60*30);
					response.addCookie(cookie);
					LoginAttemptService.deleteLoginAttempt(IpAddress);
					System.out.println("Succesful Login (Customer)");
					response.getWriter().write("PASS-LOGIN-CUSTOMER");
				}
			}
			else{
				if(LoginAttemptService.checkForBruteForce(IpAddress)){
					System.out.println("BRUTE FORCE FOR IP : " + IpAddress + " DETECTED");
				}
				System.out.println("Customer: wrong email/pass");
				response.getWriter().write("FAIL-LOGIN-CUSTOMER");
			}
		}
		System.out.println("***************/LOGIN SERVLET - LOGIN/***************");
	}

	private void signout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("***************LOGIN SERVLET - SIGN OUT***************");
		request.getSession().invalidate();
		
		// kill cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("USER")){
					currentCookie.setMaxAge(0);
					response.addCookie(currentCookie);
				}
			}
		}
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		System.out.println("***************/LOGIN SERVLET - SIGN OUT/***************");
	}

	private void sendEmail(HttpServletRequest request, HttpServletResponse response, Email email) throws ServletException, IOException{
		System.out.println("***************LOGIN SERVLET - SEND EMAIL***************");
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
		System.out.println("***************/LOGIN SERVLET - SEND EMAIL/***************");
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
