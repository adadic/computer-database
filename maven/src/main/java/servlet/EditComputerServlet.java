package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DTOCompany;
import dto.DTOComputer;
import mapper.ArrayMapper;
import model.Company;
import model.Computer;
import service.EditService;
import service.CommunService;

public class EditComputerServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private EditService editService;
	private CommunService serviceUI;

	    public EditComputerServlet() {
	    	
	    	super();
	    	this.editService = new EditService();
	    	this.serviceUI = new CommunService();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	    	ArrayList<Company> companies = serviceUI.getCompanies();
			request.setAttribute("companies", companies);
			
	    	String id_computer = request.getParameter("id");
	    	Optional<Computer> computer = editService.getComputerById(id_computer);
	    	
	    	if(computer.isPresent()) {
	    		request.setAttribute( "computer", computer.get());
	    	}
	    	
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request,response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	    	String[] company = ArrayMapper.toArray(request.getParameter("company_id"));
	    	DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName"))
	    			.id(request.getParameter("id"))
	    			.introduced(request.getParameter("introduced"))
	    			.discontinued(request.getParameter("discontinued"))
	    			.company(new DTOCompany.DTOCompanyBuilder(company[0], company[1]).build())
	    			.build();
	    	
	    	if(!editService.editComputer(computer)) {
	    		request.setAttribute("errorLog","Computer(s) not deleted");
		    	this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request,response);
	    	}
	    	else{
	    		response.sendRedirect("dashboard");
	    	}
	    }
}