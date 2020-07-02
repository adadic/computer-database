package hr.excilys.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hr.excilys.controller.ControllerConfiguration;
import hr.excilys.persistence.PersistenceConfiguration;

public final class Application implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		System.out.println("Starting....");

		AnnotationConfigWebApplicationContext dispatcher = new AnnotationConfigWebApplicationContext();
		dispatcher.register(PersistenceConfiguration.class, ControllerConfiguration.class);
		dispatcher.setServletContext(context);

		ServletRegistration.Dynamic servlet = context.addServlet("dispatcher", new DispatcherServlet(dispatcher));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}