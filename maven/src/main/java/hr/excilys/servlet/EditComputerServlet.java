package hr.excilys.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import hr.excilys.model.Computer;
import hr.excilys.service.CommunService;
import hr.excilys.service.EditService;

@Controller
@RequestMapping(value = "/editComputer")
public class EditComputerServlet {

	private static final String UPDATEERROR = "-1";
	private static final String UPDATESUCCESS = "3";

	@Autowired
	private CommunService communService;
	@Autowired
	private EditService editService;

	@GetMapping
	public ModelAndView dashboard() {
		return new ModelAndView("editComputer");
	}
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		List<Company> companies = communService.getCompanies();
//		request.setAttribute("companies", companies);
//
//		String id_computer = request.getParameter("id");
//		Optional<Computer> computer = editService.getComputerById(id_computer);
//
//		if (computer.isPresent()) {
//			request.setAttribute("computer", computer.get());
//		}
//		this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName"))
//				.id(request.getParameter("id")).introduced(request.getParameter("introduced"))
//				.discontinued(request.getParameter("discontinued"))
//				.company(new DTOCompany.DTOCompanyBuilder(request.getParameter("companyId"), "none").build()).build();
//
//		if (!editService.editComputer(computer)) {
//			request.setAttribute("msg", UPDATEERROR);
//			doGet(request, response);
//		} else {
//			response.sendRedirect("dashboard?msg=" + UPDATESUCCESS);
//		}
//	}
}