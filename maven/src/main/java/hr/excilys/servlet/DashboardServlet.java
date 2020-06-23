package hr.excilys.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hr.excilys.dto.DTOPagination;
import hr.excilys.mapper.ArrayMapper;
import hr.excilys.model.Pagination;
import hr.excilys.service.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardServlet {

	private static final String ARRAYEMPTY = "-2";
	private static final String DELETEERROR = "-3";
	private static final String DELETESUCCESS = "1";

	@Autowired
	private DashboardService dashboardService;
	@Autowired
	private Pagination page;

	@GetMapping
	public ModelAndView dashboard() {

		ModelAndView model = new ModelAndView("dashboard");

		page = new Pagination.PaginationBuilder(10, 1, null).count(dashboardService.getCountComputer(""))
				.build();
		setModelView(model);

		return model;
	}
	
	private void setModelView(ModelAndView model) {
		
		model.addObject("computers", dashboardService.getComputersRows(page));
		model.addObject("page", page.getPage());
		model.addObject("search", page.getSearch());
		model.addObject("max", page.getMaxPage());
		model.addObject("lines", page.getLines());
		model.addObject("size", page.getCount());
		model.addObject("order", page.getOrder());
		model.addObject("direction", page.getDirection());
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView addComputer(HttpServletRequest req, HttpServletResponse res) {
		
		ModelAndView view = new ModelAndView("dashboard");

        return view;
	}
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException {
//
//		DTOPagination dtoPagination = new DTOPagination.DTOPaginationBuilder(request.getParameter("lines"),
//				request.getParameter("page"), request.getParameter("search")).order(request.getParameter("order"))
//						.direction(request.getParameter("direction")).build();
//
//		Pagination page = dashboardService.paginate(dtoPagination);
//
//		request.setAttribute("ok", request.getParameter("ok"));
//		request.setAttribute("msg", request.getParameter("msg"));
//
//		request.setAttribute("page", page.getPage());
//		request.setAttribute("search", page.getSearch());
//		request.setAttribute("max", page.getMaxPage());
//		request.setAttribute("lines", page.getLines());
//		request.setAttribute("size", page.getCount());
//		request.setAttribute("order", page.getOrder());
//		request.setAttribute("direction", page.getDirection());
//
//		request.setAttribute("computers", dashboardService.getComputersRows(page));
//		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException {
//
//		try {
//			if (!dashboardService.deleteComputer(ArrayMapper.stringTransform(request.getParameter("selection")))) {
//				request.setAttribute("msg", ARRAYEMPTY);
//			} else {
//				request.setAttribute("msg", DELETESUCCESS);
//			}
//		} catch (NumberFormatException | SQLException e) {
//			request.setAttribute("msg", DELETEERROR);
//		}
//		doGet(request, response);
//	}

}
