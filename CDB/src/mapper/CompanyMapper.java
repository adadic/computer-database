package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;

public class CompanyMapper {
	
	public Company getCompany(ResultSet resultSet) throws SQLException {
		return new Company.CompanyBuilder(resultSet.getLong("id"),resultSet.getString("name"))
			.build();
		
	}

}
