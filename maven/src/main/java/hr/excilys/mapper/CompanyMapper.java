package hr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import hr.excilys.model.Company;

@Component
public final class CompanyMapper {

	public static Company getCompany(ResultSet resultSet) throws SQLException {

		return new Company.CompanyBuilder(resultSet.getLong("id"), resultSet.getString("name")).build();
	}
}
