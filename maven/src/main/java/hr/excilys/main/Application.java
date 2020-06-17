package hr.excilys.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Application implements AutoCloseable{

	public static void main(String[] args) {
		
		try(AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class)){
		}
	}

	@Override
	public void close() throws Exception {		
	}
}