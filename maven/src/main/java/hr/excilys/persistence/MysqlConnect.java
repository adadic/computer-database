package hr.excilys.persistence;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public final class MysqlConnect implements AutoCloseable {

	private Connection conn;
	private static MysqlConnect db;
	private final static Logger LOGGER = LoggerFactory.getLogger(MysqlConnect.class);

	private MysqlConnect() {
	}

	public Connection getConn() {
		
		try {
			conn = DataSource.getConnection();
		} catch (SQLException sqle) {
			LOGGER.info("Cannot fetch connexion to database with Hikari");
		}
		LOGGER.info("Connexion established");
		
		return conn;
	}

	public static synchronized MysqlConnect getDbCon() {

		if (MysqlConnect.db == null) {
			MysqlConnect.db = new MysqlConnect();
		}

		return MysqlConnect.db;
	}

	public ResultSet query(PreparedStatement query) throws SQLException {

		ResultSet res = query.executeQuery();

		return res;
	}

	@Override
	public void close() throws SQLException {

		if (this.conn != null) {
			this.conn.close();
			this.conn = null;
			MysqlConnect.db = null;
		}
	}

	protected void finalize() throws SQLException {

		this.close();
	}
}
