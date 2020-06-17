package hr.excilys.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("hr.excilys")
@ImportResource("classpath:/applicationContext.xml")
public class SpringConfiguration {

}