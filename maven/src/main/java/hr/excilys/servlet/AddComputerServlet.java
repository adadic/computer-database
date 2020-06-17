package hr.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
import hr.excilys.service.AddService;
import hr.excilys.service.CommunService;

@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int ADDSUCCESS = 2;
	private static final int ADDERROR = -1;

	static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	private CommunService communService = applicationContext.getBean(CommunService.class);
	private AddService addService = applicationContext.getBean(AddService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Company> companies = communService.getCompanies();
		request.setAttribute("companies", companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName")).id("0")
				.introduced(request.getParameter("introduced")).discontinued(request.getParameter("discontinued"))
				.company(new DTOCompany.DTOCompanyBuilder(request.getParameter("companyId"), "none").build()).build();

		if (!addService.addComputer(computer)) {
			request.setAttribute("msg", ADDERROR);
			doGet(request, response);
		} else {
			response.sendRedirect("dashboard?msg=" + ADDSUCCESS);
		}
	}
}
