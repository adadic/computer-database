package hr.excilys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import hr.excilys.model.Company;

@Component
public final class CompanyRowMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		return new Company.CompanyBuilder(resultSet.getLong("id"), resultSet.getString("name")).build();
	}
}
