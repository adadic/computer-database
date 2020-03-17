package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.DAOComputer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {

    DAOComputer daoComputer;
    final Logger logger = LoggerFactory.getLogger(MainServlet.class);


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Getting computers");
        try {
            request.getSession().setAttribute("computers", daoComputer.getComputers());
        } catch (SQLException e) {
            logger.error("Couldn't get Computers from DB.");
        }
        request.getRequestDispatcher("/WEB-INF/dashboard.html").forward(request,response);
    }
}
