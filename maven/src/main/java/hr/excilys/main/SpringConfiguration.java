package hr.excilys.main;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("hr.excilys")
@ImportResource("classpath:/applicationContext.xml")
public class SpringConfiguration {

	@Bean(destroyMethod = "close")
	public DataSource dataSource(){
		
	    HikariConfig hikariConfig = new HikariConfig("/hikari.properties");
	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);

	    return dataSource;
	}
}