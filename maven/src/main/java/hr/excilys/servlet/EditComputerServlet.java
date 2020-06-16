package hr.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.service.CommunService;
import hr.excilys.service.EditService;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPDATEERROR = "-1";
	private static final String UPDATESUCCESS = "3";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext servletContext = getServletContext();
		Object obj = servletContext.getAttribute("CommunService");
		if (obj instanceof CommunService) {
			CommunService communService = (CommunService) obj;
			ArrayList<Company> companies = communService.getCompanies();
			request.setAttribute("companies", companies);

		} else {
			throw new ServletException("communService unavailable");
		}
		
		obj = servletContext.getAttribute("EditService");
		if (obj instanceof EditService) {
			EditService editService = (EditService) obj;
			String id_computer = request.getParameter("id");
			Optional<Computer> computer = editService.getComputerById(id_computer);

			if (computer.isPresent()) {
				request.setAttribute("computer", computer.get());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
		} else {
			throw new ServletException("editService unavailable");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DTOComputer computer = new DTOComputer.DTOComputerBuilder(request.getParameter("computerName"))
				.id(request.getParameter("id")).introduced(request.getParameter("introduced"))
				.discontinued(request.getParameter("discontinued"))
				.company(new DTOCompany.DTOCompanyBuilder(request.getParameter("companyId"), "none").build()).build();

		ServletContext servletContext = getServletContext();
		Object obj = servletContext.getAttribute("EditService");
		if (obj instanceof EditService) {
			EditService editService = (EditService) obj;
			if (!editService.editComputer(computer)) {
				request.setAttribute("msg", UPDATEERROR);
				doGet(request, response);
			} else {
				response.sendRedirect("dashboard?msg=" + UPDATESUCCESS);
			}
		} else {
			throw new ServletException("editService unavailable");
		}

	}
}