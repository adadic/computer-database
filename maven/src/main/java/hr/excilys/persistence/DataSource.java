package hr.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String UNAME = "admincdb";
	private static final String PWD = "qwerty1234";
	private static final String CACHEPS = "true";
	private static final String CACHESIZE = "250";
	private static final String CACHELIMIT = "2048";

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		config.setJdbcUrl(URL);
		config.setUsername(UNAME);
		config.setPassword(PWD);
		config.addDataSourceProperty("cachePrepStmts", CACHEPS);
		config.addDataSourceProperty("prepStmtCacheSize", CACHESIZE);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", CACHELIMIT);
		ds = new HikariDataSource(config);
	}

	private DataSource() {
	}

	public static Connection getConnection() throws SQLException {

		return ds.getConnection();
	}

}
