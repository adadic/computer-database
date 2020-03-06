package persistence;
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;

/**
 * @desc Singleton database access Class for Mysql
 * @author Net-Ressource
 */

public final class MysqlConnect {
	
	public Connection conn;
	private Statement statement;
	public static MysqlConnect db;
	
	private MysqlConnect() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "computer-database-db";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "admincdb";
		String password = "querty1234";
		try {
			Class.forName(driver).newInstance();
			this.conn = (Connection) DriverManager.getConnection(url+dbName, userName, password);
		}
		catch(Exception sqle){
			sqle.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @return MysqlConnect Database connection object
	 */
	public static synchronized MysqlConnect getDbCon() {
		if (db == null) {
			db = new MysqlConnect();
		}
		return db;
	}
	
	/**
	 * 
	 * @param query String : the query to execute
	 * @return a ResultSet object for result (can be null)
	 * @throws SQLException
	 */
	public ResultSet query(String query) throws SQLException{
		statement = db.conn.createStatement();
		ResultSet res = statement.executeQuery(query);
		return res;
	}
	
	/**
	 * 
	 * @desc Insert Methode
	 * @param insertQuery String
	 * @return boolean
	 * @throws SQLException
	 */
	public int insert (String insertQuery) throws SQLException{
		statement = db.conn.createStatement();
		int result = statement.executeUpdate(insertQuery);
		return result;
	}
	
}
