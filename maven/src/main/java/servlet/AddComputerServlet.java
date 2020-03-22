package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
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
}
