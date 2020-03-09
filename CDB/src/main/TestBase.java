package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.MysqlConnect;

public class TestBase {
	
	private static MysqlConnect db;

	public static void main(String[] args) throws SQLException {
		String req = "SELECT count(id) as tot FROM computer";
		db = MysqlConnect.getDbCon();
		ResultSet res = db.query(req);
		
		res.next();
		System.out.println(res.getInt(1));
	}
}
