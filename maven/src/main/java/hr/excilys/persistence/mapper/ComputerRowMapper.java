package hr.excilys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import hr.excilys.persistence.model.Company;
import hr.excilys.persistence.model.Computer;

@Component
public final class ComputerRowMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		return new Computer.ComputerBuilder(resultSet.getString("computer.name")).id(resultSet.getLong("computer.id"))
				.introduced(resultSet.getTimestamp("computer.introduced"))
				.discontinued(resultSet.getTimestamp("computer.discontinued"))
				.company(
						new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name"))
								.build())
				.build();
	}
}
