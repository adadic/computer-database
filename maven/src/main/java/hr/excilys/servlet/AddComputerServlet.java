package hr.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.service.AddService;
import hr.excilys.service.CommunService;

@Controller
@RequestMapping(value = "/addComputer")
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int ADDSUCCESS = 2;
	private static final int ADDERROR = -1;

	@Autowired
	private CommunService communService = new CommunService();

	@Autowired
	private AddService addService = new AddService();

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
