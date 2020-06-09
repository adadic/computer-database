package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
import model.Computer;
import service.ServiceUI;

public class EditComputerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	ServiceUI serviceUI;
	
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);


	    public EditComputerServlet() {
	    	super();
	    	this.serviceUI = new ServiceUI();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	ArrayList<Company> companies = serviceUI.getCompanies();
			request.setAttribute("companies", companies);
	    	String id_computer = request.getParameter("id");

	    	Optional<Computer> computer = serviceUI.getComputerById(id_computer);
	    	if(computer.isPresent()) {
	    		request.setAttribute( "computer", computer.get());
	    	}
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request,response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	if(!serviceUI.editComputer(request)) {
	    		request.setAttribute("errorLog","Computer(s) not deleted");
		    	this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request,response);
	    	}
	    	else{
	    		response.sendRedirect("dashboard");
	    	}
	    }
}