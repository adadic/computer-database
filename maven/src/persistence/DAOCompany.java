package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import model.Company;

public class DAOCompany {
	
	public static ArrayList<Company> getCompanies() throws SQLException {
		
		ArrayList<Company> companies = new ArrayList<>();
		MysqlConnect db = MysqlConnect.getDbCon();
		ResultSet allCompanies = db.query("select id, name from company;");
		
		while(allCompanies.next())
			companies.add(new Company(allCompanies.getLong("id") ,allCompanies.getString("name")));
			
		return companies;
	}
	

	public static Optional<Company> getCompanyById(long id) throws SQLException {
		MysqlConnect db = MysqlConnect.getDbCon();
		PreparedStatement preparedStatement = db.conn.prepareStatement("select id, name from company where id = ? ;"); 
		preparedStatement.setLong(1,id);
		ResultSet company = db.query(preparedStatement);
		
		if(company.next())
			return Optional.of(new Company(company.getLong("id"),
					company.getString("name")));

		else return Optional.empty();
	}

}
