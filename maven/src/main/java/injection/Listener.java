package injection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import hr.excilys.service.AddService;
import hr.excilys.service.CommunService;
import hr.excilys.service.DashboardService;
import hr.excilys.service.EditService;

@WebListener
public class Listener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ServletContext servletContext = sce.getServletContext();

		servletContext.setAttribute("DashboardService", new DashboardService());
		servletContext.setAttribute("EditService", new EditService());
		servletContext.setAttribute("AddService", new AddService());
		servletContext.setAttribute("CommunService", new CommunService());

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}