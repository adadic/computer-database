package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import mapper.ComputerMapper;
import model.Computer;

public class DAOComputer {
	private ComputerMapper computerMapper;
	
	private final String allComputersRequest = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name" 
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id;";
	private final String computerByIdRequest = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ? ;";
	private final String insertComputer = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private final String updateComputer = "UPDATE computer SET name = ? , introduced = ? , discontinued = ? , company_id = ? WHERE id = ? ;";
	private final String deleteComputer = "DELETE FROM computer WHERE id = ?";
	
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
	
	public Optional<Computer> getComputerById(long id) throws SQLException {
		
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
			preparedStatement.setLong(4, Integer.parseInt(company_id));
	
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
			preparedStatement.setLong(4, company_id);
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
}
