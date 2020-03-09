package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import model.Computer;
import persistence.MysqlConnect;

public class DAOComputer {

	
	public static ArrayList<Computer> getComputers() throws SQLException {
		ArrayList<Computer> computers = new ArrayList<>();
		MysqlConnect db = MysqlConnect.getDbCon();
		ResultSet allComputer = db.query("select id, name, introduced, discontinued, company_id from computer");
		
		while(allComputer.next()) 
			computers.add(new Computer(allComputer.getLong("id"),
				allComputer.getString("name"),
				allComputer.getTimestamp("introduced"),
				allComputer.getTimestamp("discontinued"),
				allComputer.getLong("company_id")));
		
		return computers;
	}
	
	public static Optional<Computer> getComputerById(int id) throws SQLException {
		MysqlConnect db = MysqlConnect.getDbCon();
		ResultSet computer = db.query("select id, name, introduced, discontinued, company_id from computer where id = " + id);
		
		if(computer.next())
			return Optional.of(new Computer(computer.getLong("id"),
					computer.getString("name"),
					computer.getTimestamp("introduced"),
					computer.getTimestamp("discontinued"),
					computer.getLong("company_id")));

		else return Optional.empty();
	}
	
	public static int insertComputer(long id, String name, Timestamp introduced, Timestamp discontinued, long company_id) throws SQLException{
		//Prepare Statement
		
		int result = 0;
		//int result = statement.executeUpdate(insertQuery);
		
		
		return result;
	}
}
