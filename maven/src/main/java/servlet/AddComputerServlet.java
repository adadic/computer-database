package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import service.ServiceUI;


public class AddComputerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ServiceUI serviceUI;


	    public AddComputerServlet() {
	    	super();
	    	this.serviceUI = new ServiceUI();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	ArrayList<Company> companies = serviceUI.getCompanies();
			request.setAttribute("companies", companies);
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request,response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	if(!serviceUI.addComputer(request)) {
	    		request.setAttribute("errorLog","Computer not added");
		    	this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request,response);
	    	}
	    	else{
	    		response.sendRedirect("dashboard");
	    	}
	    }
}
