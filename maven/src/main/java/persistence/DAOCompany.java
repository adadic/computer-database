package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import mapper.CompanyMapper;
import model.Company;

public class DAOCompany {
	
	private CompanyMapper companyMapper;
	private final String allCompaniesRequest = "SELECT id, name FROM company;";
	private final String companyByIdRequest = "SELECT id, name FROM company WHERE id = ? ;";
	
	public ArrayList<Company> getCompanies() throws SQLException {

		ArrayList<Company> companies = new ArrayList<>();
		
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			companyMapper = new CompanyMapper();
			
			PreparedStatement preparedStatement = db.getConn().prepareStatement(allCompaniesRequest);
			ResultSet requestCompanies = db.query(preparedStatement);
			while(requestCompanies.next()) {
				companies.add(companyMapper.getCompany(requestCompanies));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		return companies;
		
	}
	

	public Optional<Company> getCompanyById(long id) throws SQLException {
		try(MysqlConnect db = MysqlConnect.getDbCon()){
			PreparedStatement preparedStatement = db.getConn().prepareStatement(companyByIdRequest);
			preparedStatement.setLong(1,id);
			ResultSet company = db.query(preparedStatement);
			
			if(company.next()) {
				return Optional.of(new Company(company.getLong("id"),
						company.getString("name")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		return Optional.empty();
	}

}
