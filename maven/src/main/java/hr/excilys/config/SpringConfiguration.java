package hr.excilys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "hr.excilys.dto", "hr.excilys.mapper", "hr.excilys.model", "hr.excilys.persistence",
		"hr.excilys.service", "hr.excilys.controller", "hr.excilys.validator" })
@ImportResource("classpath:/applicationContext.xml")
public class SpringConfiguration {

	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {

		HikariConfig hikariConfig = new HikariConfig("/hikari.properties");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);

		return dataSource;
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(HikariDataSource datasource) {

		return new NamedParameterJdbcTemplate(datasource);
	}
}