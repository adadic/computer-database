package hr.excilys.main;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Application implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		System.out.println("Starting....");

		AnnotationConfigWebApplicationContext dispatcher = new AnnotationConfigWebApplicationContext();
		dispatcher.register(SpringConfiguration.class, MvcConfiguration.class);
		dispatcher.setServletContext(context);

		ServletRegistration.Dynamic servlet = context.addServlet("dispatcher", new DispatcherServlet(dispatcher));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}