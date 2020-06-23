package hr.excilys.servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.main.SpringConfiguration;
import hr.excilys.model.Company;
import hr.excilys.service.AddService;
import hr.excilys.service.CommunService;

@Controller
@RequestMapping(value = "/addComputer")
public class AddComputerServlet {

	private static final int ADDSUCCESS = 2;
	private static final int ADDERROR = -1;

	@Autowired
	private CommunService communService;
	@Autowired
	private AddService addService;

	@GetMapping
	public ModelAndView add() {

		ModelAndView model = new ModelAndView("addComputer");
		model.addObject("companies", communService.getCompanies());

		return model;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView addComputer(HttpServletRequest req, HttpServletResponse res) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:dashboard");

        return view;
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException {
//
//		DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName")).id("0")
//				.introduced(request.getParameter("introduced")).discontinued(request.getParameter("discontinued"))
//				.company(new DTOCompany.DTOCompanyBuilder(request.getParameter("companyId"), "none").build()).build();
//
//		if (!addService.addComputer(computer)) {
//			request.setAttribute("msg", ADDERROR);
//			doGet(request, response);
//		} else {
//			response.sendRedirect("dashboard?msg=" + ADDSUCCESS);
//		}
//	}
}
