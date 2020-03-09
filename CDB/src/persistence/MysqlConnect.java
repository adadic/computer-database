package persistence;
import java.sql.*;

public final class MysqlConnect {
	
	public java.sql.Connection conn;
	private Statement statement;
	public static MysqlConnect db;
	
	private MysqlConnect() {
		String url = "jdbc:mysql://localhost:3306/computer-database-db";
		String driver = "com.mysql.cj.jdbc.Driver";
		String userName = "admincdb";
		String password = "qwerty1234";
		try {
			System.out.println("Connexion\n");
			Class.forName(driver).newInstance();
			this.conn = DriverManager.getConnection(url, userName, password);
		}
		catch(Exception sqle){
			sqle.printStackTrace();
		}
		
	}
	
	public static synchronized MysqlConnect getDbCon() {
		if (db == null) {
			db = new MysqlConnect();
		}
		return db;
	}

	public ResultSet query(String query) throws SQLException{
		statement = db.conn.createStatement();
		ResultSet res = statement.executeQuery(query);
		return res;
	}
	
	public int insert (String insertQuery) throws SQLException{
		statement = db.conn.createStatement();
		int result = statement.executeUpdate(insertQuery);
		return result;
	}
	
	public int drop (String dropQuery) throws SQLException{
		statement = db.conn.createStatement();
		int result = statement.executeUpdate(dropQuery);
		return result;
	}
	
}
