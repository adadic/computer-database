package hr.excilys.persistence;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MysqlConnect implements AutoCloseable {

	private Connection conn;
	private static MysqlConnect db;

	private MysqlConnect() {
	}

	public Connection getConn() {
		
		try {
			conn = DataSource.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
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
