package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Author;
import beans.Publisher;
import service.AuthorService;
import service.PublisherService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/addAuthor",
						   "/addPublisher",
						   "/addBook"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String authorFirstName = request.getParameter("authorFirstName");
    	String authorLastName = request.getParameter("authorLastName");
    	
    	Author author = new Author(authorFirstName, authorLastName);
    	
    	AuthorService.addAuthor(author);
    	System.out.println("Author Successfully added!");
    }
    
    protected void addPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String publisherName = request.getParameter("publisherName");
    	
    	Publisher publisher = new Publisher(publisherName);
    	
    	PublisherService.addPublisher(publisher);
    	System.out.println("Publisher Successfully added!");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("I am at adminServlet at doPost. Servlet path is " + request.getServletPath());
			switch(request.getServletPath()) {
				case "/addAuthor": System.out.println("I am at addAuthor case");
								  System.out.println("*****************************ADD AUTHOR doPost***************************");
								  System.out.println("First name: " + request.getParameter("authorFirstName"));
								  System.out.println("Last Name: " + request.getParameter("authorLastName"));
								  addAuthor(request, response);
								  break;
				case "/addPublisher": System.out.println("I am at addPublisher case");
								  System.out.println("*****************************ADD PUBLISHER doPost************************");
								  System.out.println("Publisher Name: " + request.getParameter("publisherName"));
								  addPublisher(request, response);
								  break;
			}
	}

}
