package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import persistence.DAOComputer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    final Logger logger = LoggerFactory.getLogger(MainServlet.class);


    public MainServlet() {
    	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	DAOComputer daoComputer = new DAOComputer();
    	ArrayList<Computer> computers = new ArrayList<>();
		try {
			computers = daoComputer.getComputers();
		} catch (SQLException e) {
			logger.error("Couldn't get computer list");
		}
		request.setAttribute( "computers", computers );
		request.setAttribute("size", computers.size());
        this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
    }
}
