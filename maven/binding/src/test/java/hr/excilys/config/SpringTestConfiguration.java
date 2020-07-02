package config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hr.excilys.validator", "hr.excilys.mapper"})
public class SpringTestConfiguration {

}
