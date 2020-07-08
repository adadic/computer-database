package hr.excilys.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hr.excilys.dto.DTOPagination;
import hr.excilys.mapper.ArrayMapper;
import hr.excilys.model.Pagination;
import hr.excilys.service.DashboardService;

@Controller
@RequestMapping(value = {"/", "/dashboard"})
public class DashboardController {

	private static final String ARRAYEMPTY = "-2";
	private static final String DELETESUCCESS = "1";

	private final DashboardService dashboardService;
	private Pagination dashboard;
	private final ArrayMapper arrayMapper;

	@Autowired
	public DashboardController(DashboardService dashboardService, ArrayMapper arrayMapper) {

		this.dashboardService = dashboardService;
		this.arrayMapper = arrayMapper;
	}

	@GetMapping
	public ModelAndView dashboard(DTOPagination dtoPagination) {

		ModelAndView view = new ModelAndView("dashboard");
		dashboard = dashboardService.paginate(dtoPagination);
		setMessage(view, dtoPagination.getMsg());
		setModelView(view);

		return view;
	}

	private void setMessage(ModelAndView model, String msg) {

		if(StringUtils.isNotEmpty(msg)) {
			model.addObject("msg", msg);
		}
	}

	private void setModelView(ModelAndView view) {

		view.addObject("computers", dashboardService.getComputersRows(dashboard));
		view.addObject("page", dashboard.getPage());
		view.addObject("search", dashboard.getSearch());
		view.addObject("max", dashboard.getMaxPage());
		view.addObject("lines", dashboard.getLines());
		view.addObject("size", dashboard.getCount());
		view.addObject("order", dashboard.getOrder());
		view.addObject("direction", dashboard.getDirection());
	}

	@PostMapping(value = "/delete")
	public ModelAndView deleteComputer(@RequestParam(defaultValue = "") String selection) {

		ModelAndView view = new ModelAndView("redirect:/dashboard");

			if (!dashboardService.deleteComputer(arrayMapper.stringTransform(selection))) {
				view.addObject("msg", ARRAYEMPTY);
			} else {
				view.addObject("msg", DELETESUCCESS);
			}

		return view;
	}
}
