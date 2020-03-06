package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.MysqlConnect;

public class Main {
	private static MysqlConnect db;

	public static void main(String[] args) throws SQLException {
		String req = "SELECT * FROM COMPUTER";
		MysqlConnect.getDbCon();
		ResultSet res = db.query(req);
		System.out.println(res.toString());
	}
}
