package hr.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.service.AddService;
import hr.excilys.service.CommunService;

@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int ADDSUCCESS = 2;
	private static final int ADDERROR = -1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext servletContext = getServletContext();
		Object obj = servletContext.getAttribute("CommunService");
		if (obj instanceof CommunService) {
			CommunService communService = (CommunService) obj;
			ArrayList<Company> companies = communService.getCompanies();
			request.setAttribute("companies", companies);
			this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);

		} else {
			throw new ServletException("communService unavailable");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName")).id("0")
				.introduced(request.getParameter("introduced")).discontinued(request.getParameter("discontinued"))
				.company(new DTOCompany.DTOCompanyBuilder(request.getParameter("companyId"), "none").build()).build();

		ServletContext servletContext = getServletContext();
		Object obj = servletContext.getAttribute("AddService");
		if (obj instanceof AddService) {
			AddService addService = (AddService) obj;
			if (!addService.addComputer(computer)) {
				request.setAttribute("msg", ADDERROR);
				doGet(request, response);
			} else {
				response.sendRedirect("dashboard?msg=" + ADDSUCCESS);
			}
		} else {
			throw new ServletException("addComputer unavailable");
		}

	}
}
