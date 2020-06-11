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
	
	public DAOComputer() {
		
		computerMapper = new ComputerMapper();
	}
	
	public ArrayList<Computer> getComputers() throws SQLException {
		
		ArrayList<Computer> computers = new ArrayList<>();
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.ALLCOMPUTER.getQuery());
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
		
		ArrayList<Computer> computers = new ArrayList<>();
		
		if(search == null) {
			search = "%";
		}
		else {
			search = "%" + search + "%";
		}
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = extractedGetRows(page, lines, search, db);
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

	private PreparedStatement extractedGetRows(int page, int lines, String search, MysqlConnect db) throws SQLException {
		
		PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.PAGECOMPUTER.getQuery());
		preparedStatement.setString(1, search);
		preparedStatement.setLong(2, lines);
		preparedStatement.setLong(3, lines * (page - 1));
		
		return preparedStatement;
	}
	
	public Optional<Computer> getComputerById(long id) throws SQLException {
				
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.IDCOMPUTER.getQuery()); 
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
	
	
	public int insertComputer(Computer computer) throws SQLException{
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = extractedInsert(computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), db);
	
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return 0;
		}
	}

	private PreparedStatement extractedInsert(String name, Timestamp introduced, Timestamp discontinued,
			long company_id, MysqlConnect db) throws SQLException {
		
		PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.INSERTCOMPUTER.getQuery()); 
		preparedStatement.setString(1, name);
		preparedStatement.setTimestamp(2, introduced);
		preparedStatement.setTimestamp(3, discontinued);
		
		if(company_id == 0) {
			preparedStatement.setNString(4, null);
		}
		else{
			preparedStatement.setLong(4, company_id);
		}
		
		return preparedStatement;
	}
	
	public int updateComputer(Computer computer) throws SQLException{
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = extractedUpdate(computer.getId(), computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), db);
			
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return 0;
		}
	}

	private PreparedStatement extractedUpdate(long id, String name, Timestamp introduced, Timestamp discontinued,
			long company_id, MysqlConnect db) throws SQLException {
		
		PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.UPDATECOMPUTER.getQuery());
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
		
		return preparedStatement;
	}
	

	
public int deleteComputer (long id) throws SQLException{
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.DELETECOMPUTER.getQuery());
			preparedStatement.setLong(1, id);
			
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return 0;
		}
	}

	public int deleteComputerCompany (long id) throws SQLException{
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.DELETECOMPUTERCOMPANY.getQuery());
			preparedStatement.setLong(1, id);
			
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return 0;
		}
	}

	public int countComputer (String search) throws SQLException{
		
		if(search == null) {
			search = "%";
		}
		else {
			search = "%" + search + "%";
		}
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.COUNTCOMPUTER.getQuery());
			preparedStatement.setString(1, search);
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
