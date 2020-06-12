package hr.excilys.persistence;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MysqlConnect implements AutoCloseable {

	private Connection conn;
	private static MysqlConnect db;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

	private MysqlConnect() {

		try {
			Class.forName(DRIVER).newInstance();
			this.conn = DataSource.getConnection();
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}

	public Connection getConn() {

		return conn;
	}

	public static synchronized MysqlConnect getDbCon() {

		if (db == null) {
			db = new MysqlConnect();
		}

		return db;
	}

	public ResultSet query(PreparedStatement query) throws SQLException {

		ResultSet res = query.executeQuery();

		return res;
	}

	@Override
	public void close() throws SQLException {

		if (conn != null) {
			conn.close();
			conn = null;
			db = null;
		}
	}

	protected void finalize() throws SQLException {

		this.close();
	}
}
