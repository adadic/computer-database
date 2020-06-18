package hr.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public final class DataSource {

	private static HikariConfig config = new HikariConfig("/hikari.properties");
	private static HikariDataSource ds = new HikariDataSource(config);
	private DataSource() {
	}

	public static Connection getConnection() throws SQLException {

		return ds.getConnection();
	}

}
