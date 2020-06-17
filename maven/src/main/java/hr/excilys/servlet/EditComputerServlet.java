package hr.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.main.SpringConfiguration;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.service.CommunService;
import hr.excilys.service.EditService;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPDATEERROR = "-1";
	private static final String UPDATESUCCESS = "3";

	static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	private CommunService communService = applicationContext.getBean(CommunService.class);
	private EditService editService = applicationContext.getBean(EditService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Company> companies = communService.getCompanies();
		request.setAttribute("companies", companies);

		String id_computer = request.getParameter("id");
		Optional<Computer> computer = editService.getComputerById(id_computer);

		if (computer.isPresent()) {
			request.setAttribute("computer", computer.get());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName"))
				.id(request.getParameter("id")).introduced(request.getParameter("introduced"))
				.discontinued(request.getParameter("discontinued"))
				.company(new DTOCompany.DTOCompanyBuilder(request.getParameter("companyId"), "none").build()).build();

		if (!editService.editComputer(computer)) {
			request.setAttribute("msg", UPDATEERROR);
			doGet(request, response);
		} else {
			response.sendRedirect("dashboard?msg=" + UPDATESUCCESS);
		}
	}
}