package hr.excilys.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.excilys.dto.DTOPagination;
import hr.excilys.mapper.ArrayMapper;
import hr.excilys.model.Pagination;
import hr.excilys.service.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String PAGEERROR = "-1";
	private static final String ARRAYEMPTY = "-2";
	private static final String DELETEERROR = "-3";
	private static final String DELETESUCCESS = "1";
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	private DashboardService dashboardService;
	private Pagination page;

	public DashboardServlet() {

		super();
		this.dashboardService = new DashboardService();
		this.page = new Pagination();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DTOPagination dtoPagination = new DTOPagination.DTOPaginationBuilder(request.getParameter("lines"),
				request.getParameter("page"), request.getParameter("search")).order(request.getParameter("order"))
						.direction(request.getParameter("direction")).build();

		page = dashboardService.paginate(dtoPagination);

		request.setAttribute("ok", request.getParameter("ok"));
		if(page.hasError()) {
			request.setAttribute("msg", PAGEERROR);
		}
		else {
			request.setAttribute("msg", request.getParameter("msg"));
		}
		request.setAttribute("page", page.getPage());
		request.setAttribute("search", page.getSearch());
		request.setAttribute("max", page.getMaxPage());
		request.setAttribute("lines", page.getLines());
		request.setAttribute("size", page.getCount());
		request.setAttribute("order", page.getOrder());
		request.setAttribute("direction", page.getDirection());
		request.setAttribute("computers", dashboardService.getComputersRows(page));

		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if (!dashboardService.deleteComputer(ArrayMapper.stringTransform(request.getParameter("selection")))) {
				request.setAttribute("msg", ARRAYEMPTY);
				this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
			}
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("msg", DELETEERROR);
			this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		}
		response.sendRedirect("?msg=" + DELETESUCCESS);
	}
}