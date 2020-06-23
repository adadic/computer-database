package hr.excilys.servlet;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView dashboard() {
		return new ModelAndView("addComputer");
	}
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		List<Company> companies = communService.getCompanies();
//		request.setAttribute("companies", companies);
//		this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
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
