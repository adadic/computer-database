package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Pagination;
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
    private Pagination page;


    public DashboardServlet() {
    	super();
    	this.dashboardService = new DashboardService();
    	this.page = new Pagination();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	page = dashboardService.paginate(request);
    	request.setAttribute("page", page.getPage());
    	request.setAttribute("search", page.getSearch());
    	request.setAttribute("max", page.getMaxPage());
    	request.setAttribute("lines", page.getLines());
    	request.setAttribute("size", page.getCount());
    	request.setAttribute( "computers", dashboardService.getComputersRows(page.getPage(), page.getLines(), page.getSearch()) );
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
