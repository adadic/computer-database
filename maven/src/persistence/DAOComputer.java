package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import model.Company;
import model.Computer;

public class DAOComputer {

	
	public static ArrayList<Computer> getComputers() throws SQLException {
		ArrayList<Computer> computers = new ArrayList<>();
		MysqlConnect db = MysqlConnect.getDbCon();
		ResultSet allComputer = db
				.query("select id, name, introduced, discontinued, company_id from computer;");
		
		while(allComputer.next()) {
			Optional<Company> company = DAOCompany.getCompanyById(allComputer.getLong("company_id"));
			if(company.isPresent())
				computers.add(new Computer(allComputer.getLong("id"),
					allComputer.getString("name"),
					allComputer.getTimestamp("introduced"),
					allComputer.getTimestamp("discontinued"),
					company.get()));
			else
				computers.add(new Computer(allComputer.getLong("id"),
						allComputer.getString("name"),
						allComputer.getTimestamp("introduced"),
						allComputer.getTimestamp("discontinued")));
				
		}
			
		return computers;
	}
	
	public static Optional<Computer> getComputerById(long id) throws SQLException {
		MysqlConnect db = MysqlConnect.getDbCon();
		PreparedStatement preparedStatement = db.conn
				.prepareStatement("select id, name, introduced, discontinued, company_id from computer where id = ? ;"); 
		preparedStatement.setLong(1,id);
		ResultSet computer = db.query(preparedStatement);
		
		if(computer.next()) {
			Optional<Company> company = DAOCompany.getCompanyById(computer.getLong("company_id"));
			if(company.isPresent())
				return Optional.of(new Computer(computer.getLong("id"),
						computer.getString("name"),
						computer.getTimestamp("introduced"),
						computer.getTimestamp("discontinued"),
						new Company(company.get())));
			else
				return Optional.of(new Computer(computer.getLong("id"),
						computer.getString("name"),
						computer.getTimestamp("introduced"),
						computer.getTimestamp("discontinued")));
				
			
		}
		else return Optional.empty();
	}
	
	
	public static int insertComputer(String name, Timestamp introduced, Timestamp discontinued, long company_id) throws SQLException{
		//Prepare Statement
		int result = 0;
		MysqlConnect db = MysqlConnect.getDbCon();
		Optional<Company> company = DAOCompany.getCompanyById(company_id);
		if(company.isPresent()) {
			PreparedStatement preparedStatement = db.conn
					.prepareStatement("insert into computer (name, introduced, discontinued, company_id) values (?,?,?,?);"); 
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, introduced);
			preparedStatement.setTimestamp(3, discontinued);
			preparedStatement.setLong(4, company_id);

			result = preparedStatement.executeUpdate();
		}
		else {
			PreparedStatement preparedStatement = db.conn
					.prepareStatement("insert into computer (name, introduced, discontinued) values (?,?,?);"); 
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, introduced);
			preparedStatement.setTimestamp(3, discontinued);
			
			result = preparedStatement.executeUpdate();
		}	
		return result;
	}
	
	public static int updateComputer(long id, String name, Timestamp introduced, Timestamp discontinued, long company_id) throws SQLException{
		MysqlConnect db = MysqlConnect.getDbCon();
		if(company_id == 0) {
			PreparedStatement preparedStatement = db.conn
					.prepareStatement("update computer set name = ? , introduced = ? , discontinued = ? where id = ? ;");
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, introduced);
			preparedStatement.setTimestamp(3, discontinued);
			preparedStatement.setLong(4, company_id);
			return preparedStatement.executeUpdate();
		}
		PreparedStatement preparedStatement = db.conn
				.prepareStatement("update computer set name = ? , introduced = ? , discontinued = ? , company_id = ? where id = ? ;");
		preparedStatement.setString(1, name);
		preparedStatement.setTimestamp(2, introduced);
		preparedStatement.setTimestamp(3, discontinued);
		preparedStatement.setLong(4, company_id);
		preparedStatement.setLong(5, id);
		return preparedStatement.executeUpdate();
	}
	

	
	public static int deleteComputer (long id) throws SQLException{
		MysqlConnect db = MysqlConnect.getDbCon();
		PreparedStatement preparedStatement = db.conn
				.prepareStatement("delete from computer where id = ?");
		preparedStatement.setLong(1, id);
		return preparedStatement.executeUpdate();
	}
}
