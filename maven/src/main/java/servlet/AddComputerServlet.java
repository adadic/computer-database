package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.DateMapper;
import model.Company;
import model.Computer;
import model.Computer.ComputerBuilder;
import persistence.DAOCompany;


public class AddComputerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	final Logger logger = LoggerFactory.getLogger(MainServlet.class);


	    public AddComputerServlet() {
	    	super();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	DAOCompany daoCompany = new DAOCompany();
	    	ArrayList<Company> companies = new ArrayList<>();
			try {
				companies = daoCompany.getCompanies();
			} catch (SQLException e) {
				logger.error("Couldn't get Companies");
			}
			request.setAttribute("companies", companies);
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request,response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	DateMapper dateMapper = new DateMapper();
			Computer computer = new Computer.ComputerBuilder(request.getParameter("computerName")).build();
			response.sendRedirect("dashboard");
//
//			.introduced(dateMapper.getDate(request.getParameter("computerName")))
//			.discontinued(dateMapper.getDate(request.getParameter("computerName")))
//			.company(new Company.CompanyBuilder(request.getParameter("computerName"), request.getParameter("computerName")).build())
	    }
}
