package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;
import model.Computer;

public class ComputerMapper {

	public Computer getComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer.ComputerBuilder(resultSet.getString("computer.name"))
				.id(resultSet.getLong("computer.id"))
				.introduced(resultSet.getTimestamp("computer.introduced"))
				.discontinued(resultSet.getTimestamp("computer.discontinued"))
				.company(new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name")).build())
				.build();
		
		
	}
}
