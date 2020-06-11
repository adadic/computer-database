package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.DTOPagination;
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
    	
    	DTOPagination dtoPagination = new DTOPagination.DTOPaginationBuilder(request.getParameter("lines"), request.getParameter("page"), request.getParameter("search"))
    			.build();
    	page = dashboardService.paginate(dtoPagination);
    	request.setAttribute("ok", request.getParameter("ok"));
    	request.setAttribute("page", page.getPage());
    	request.setAttribute("search", page.getSearch());
    	request.setAttribute("max", page.getMaxPage());
    	request.setAttribute("lines", page.getLines());
    	request.setAttribute("size", page.getCount());
    	request.setAttribute( "computers", dashboardService.getComputersRows(page) );
    	
        this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String selected = request.getParameter("selection");
		
    	try {
			if(!dashboardService.deleteComputer(selected)) {
				request.setAttribute("errorLog","Computer not modify");
			}
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("errorLog","Computer not modify");
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
		}
    	
    	request.setAttribute("page", page.getPage());
    	request.setAttribute("search", page.getSearch());
    	request.setAttribute("max", page.getMaxPage());
    	request.setAttribute("lines", page.getLines());
    	request.setAttribute("size", page.getCount());
		response.sendRedirect("dashboard?ok=1");
    }
}
