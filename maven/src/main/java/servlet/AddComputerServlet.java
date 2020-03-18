package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AddComputerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	final Logger logger = LoggerFactory.getLogger(MainServlet.class);


	    public AddComputerServlet() {
	    	super();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request,response);
	    }
}
