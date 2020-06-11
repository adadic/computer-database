package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DTOCompany;
import dto.DTOComputer;
import mapper.ArrayMapper;
import model.Company;
import service.AddService;
import service.CommunService;


public class AddComputerServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private AddService addService;
	private CommunService serviceUI;


	    public AddComputerServlet() {
	    	
	    	super();
	    	this.addService = new AddService();
	    	this.serviceUI = new CommunService();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	    	ArrayList<Company> companies = serviceUI.getCompanies();
			request.setAttribute("companies", companies);
			
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request,response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	    	String[] company = ArrayMapper.toArray(request.getParameter("company_id"));
	    	DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName"))
	    			.id("0")
	    			.introduced(request.getParameter("introduced"))
	    			.discontinued(request.getParameter("discontinued"))
	    			.company(new DTOCompany.DTOCompanyBuilder(company[0], company[1]).build())
	    			.build();
	    	
	    	if(!addService.addComputer(computer)) {
	    		request.setAttribute("errorLog","Computer not added");
		    	this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request,response);
	    	}
	    	else{
	    		response.sendRedirect("dashboard");
	    	}
	    }
}
