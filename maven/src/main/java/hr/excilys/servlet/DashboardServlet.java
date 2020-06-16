package hr.excilys.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.excilys.dto.DTOPagination;
import hr.excilys.mapper.ArrayMapper;
import hr.excilys.model.Pagination;
import hr.excilys.service.DashboardService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ARRAYEMPTY = "-2";
	private static final String DELETEERROR = "-3";
	private static final String DELETESUCCESS = "1";
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DTOPagination dtoPagination = new DTOPagination.DTOPaginationBuilder(request.getParameter("lines"),
				request.getParameter("page"), request.getParameter("search")).order(request.getParameter("order"))
						.direction(request.getParameter("direction")).build();

		ServletContext servletContext = getServletContext();
		Object obj = servletContext.getAttribute("DashboardService");
		if (obj instanceof DashboardService) {
			DashboardService dashboardService = (DashboardService) obj;
			Pagination page = dashboardService.paginate(dtoPagination);
	
			request.setAttribute("ok", request.getParameter("ok"));
			request.setAttribute("msg", request.getParameter("msg"));
	
			request.setAttribute("page", page.getPage());
			request.setAttribute("search", page.getSearch());
			request.setAttribute("max", page.getMaxPage());
			request.setAttribute("lines", page.getLines());
			request.setAttribute("size", page.getCount());
			request.setAttribute("order", page.getOrder());
			request.setAttribute("direction", page.getDirection());
	
			request.setAttribute("computers", dashboardService.getComputersRows(page));
			this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		} else {
			throw new ServletException("dashboardService unavailable");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext servletContext = getServletContext();
		Object obj = servletContext.getAttribute("DashboardService");
		if (obj instanceof DashboardService) {
			DashboardService dashboardService = (DashboardService) obj;
			try {
				if (!dashboardService.deleteComputer(ArrayMapper.stringTransform(request.getParameter("selection")))) {
					request.setAttribute("msg", ARRAYEMPTY);
					doGet(request, response);
				}
			} catch (NumberFormatException | SQLException e) {
				request.setAttribute("msg", DELETEERROR);
				doGet(request, response);
			}
			request.setAttribute("msg", DELETESUCCESS);
			doGet(request, response);
		} else {
			throw new ServletException("dashboardService unavailable");
		}

	}
}
