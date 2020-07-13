package hr.excilys.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hr.excilys.validator", "hr.excilys.mapper", "hr.excilys.dto"})
public class BindingConfig {

}
