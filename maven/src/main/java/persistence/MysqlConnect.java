package persistence;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MysqlConnect implements AutoCloseable{
	
	private Connection conn;
	private static MysqlConnect db;
	
	//private String url = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String driver = "com.mysql.cj.jdbc.Driver";
	//private String userName = "admincdb";
	//private String password = "qwerty1234";
	
	private MysqlConnect() {
		
		try {
			Class.forName(driver).newInstance();
			this.conn = DataSource.getConnection();
			//this.conn = DriverManager.getConnection(url, userName, password);
		}
		catch(Exception sqle){
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

	public ResultSet query(PreparedStatement query) throws SQLException{
		
		ResultSet res = query.executeQuery();
		
		return res;
	}

	@Override
	public void close() throws SQLException {
		
		if(conn != null) {
			conn.close();
			conn = null;
			db = null;
		}
	}
	
	protected void finalize() throws SQLException{
		
		this.close();
	}
}
