package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
    private DashboardService dashboardService;


    public DashboardServlet() {
    	super();
    	this.dashboardService = new DashboardService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	int page = 1;
    	int lines = 10;
    	String search = request.getParameter("search");
		String pageString = request.getParameter("page");
		String linesString = request.getParameter("lines");
		
		if(pageString != null) {
    		page = Integer.valueOf(pageString);
    	}
    	if(linesString != null) {
    		lines = Integer.valueOf(linesString);
    	}
    	System.out.println(page);
    	System.out.println(lines);
    	System.out.println(search);
    	request.setAttribute("page", page);
    	request.setAttribute("search", search);
    	request.setAttribute("max", 20);
    	request.setAttribute("lines", lines);
    	request.setAttribute("size", dashboardService.getCountComputer());
    	request.setAttribute( "computers", dashboardService.getComputersRows(page, lines, search) );
        this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			if(!dashboardService.deleteComputer(request)) {
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
