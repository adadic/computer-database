package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import service.ServiceUI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    final Logger logger = LoggerFactory.getLogger(MainServlet.class);
    ServiceUI serviceUI;


    public MainServlet() {
    	super();
    	this.serviceUI = new ServiceUI();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	ArrayList<Computer> computers = serviceUI.getComputers();
		request.setAttribute( "computers", computers );
		request.setAttribute("size", computers.size());
        this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			if(!serviceUI.deleteComputer(request)) {
				request.setAttribute("errorLog","Computer not modify");
		    	this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
			}
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("errorLog","Computer not modify");
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
		}
		response.sendRedirect("dashboard");
    }
}
