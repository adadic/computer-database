package hr.excilys.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import hr.excilys.servlet.DashboardServlet;

public class Application implements AutoCloseable{

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		try(AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class)){
			DashboardServlet vManagerFactory = applicationContext.getBean("DashboardServlet", DashboardServlet.class);
		}
	}

	@Override
	public void close() throws Exception {		
	}
}