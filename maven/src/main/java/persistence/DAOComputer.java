package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Optional;

import mapper.ComputerMapper;
import model.Computer;

public class DAOComputer {
	private ComputerMapper computerMapper;
	
	private final String allComputersRequest = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name" 
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id;";
	private final String computerRequest = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name" 
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? LIMIT ? OFFSET ?;";
	
	private final String computerByIdRequest = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ? ;";
	private final String insertComputer = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private final String updateComputer = "UPDATE computer SET name = ? , introduced = ? , discontinued = ? , company_id = ? WHERE id = ? ;";
	private final String deleteComputer = "DELETE FROM computer WHERE id = ?";
	private final String countComputer = "SELECT COUNT(*) as number FROM computer;";
	
	public ArrayList<Computer> getComputers() throws SQLException {
		
		computerMapper = new ComputerMapper();
		ArrayList<Computer> computers = new ArrayList<>();
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(allComputersRequest);
			ResultSet requestComputers = db.query(preparedStatement);
			
			while(requestComputers.next()) {
				computers.add(computerMapper.getComputer(requestComputers));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
	
public ArrayList<Computer> getComputersRows(int page, int lines, String search) throws SQLException {
		
		computerMapper = new ComputerMapper();
		ArrayList<Computer> computers = new ArrayList<>();
		if(search == null) {
			search = "%";
		}
		else {
			search = "%" + search + "%";
		}
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(computerRequest);
			preparedStatement.setString(1, search);
			preparedStatement.setLong(2, lines);
			preparedStatement.setLong(3, lines * (page - 1));
			ResultSet requestComputers = db.query(preparedStatement);
			
			while(requestComputers.next()) {
				computers.add(computerMapper.getComputer(requestComputers));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
	
	public Optional<Computer> getComputerById(long id) throws SQLException {
		computerMapper = new ComputerMapper();
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(computerByIdRequest); 
			preparedStatement.setLong(1,id);
			ResultSet requestComputer = db.query(preparedStatement);
			
			if(requestComputer.next()) {
				return Optional.of(computerMapper.getComputer(requestComputer));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	
	public int insertComputer(String name, Timestamp introduced, Timestamp discontinued, String company_id) throws SQLException{
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(insertComputer); 
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, introduced);
			preparedStatement.setTimestamp(3, discontinued);
			if(company_id.equals("0")) {
				preparedStatement.setNString(4, null);
			}
			else{
				preparedStatement.setLong(4, Integer.parseInt(company_id));
			}
	
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateComputer(long id, String name, Timestamp introduced, Timestamp discontinued, long company_id) throws SQLException{
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(updateComputer);
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, introduced);
			preparedStatement.setTimestamp(3, discontinued);
			if(company_id < 1) {

				preparedStatement.setNull(4, Types.BIGINT);
			}
			else{
				preparedStatement.setLong(4, company_id);
			}
			preparedStatement.setLong(5, id);
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	

	
	public int deleteComputer (long id) throws SQLException{
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(deleteComputer);
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int countComputer () throws SQLException{
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(countComputer);
			ResultSet resultSet = db.query(preparedStatement);
			
			if(resultSet.next()) {
				return resultSet.getInt("number");
			}
			return -1;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
