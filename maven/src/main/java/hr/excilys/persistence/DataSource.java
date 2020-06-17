package hr.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import hr.excilys.main.SpringConfiguration;

@Component
public class DataSource {

	static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	private static HikariDataSource ds = applicationContext.getBean(HikariDataSource.class);

	private DataSource() {
	}

	public static Connection getConnection() throws SQLException {

		return ds.getConnection();
	}

}
