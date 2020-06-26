package hr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.validator.ComputerValidator;

@Component
public final class ComputerMapper implements RowMapper<Computer> {

	private static final long IDNULL = 0L;

	@Autowired
	private ComputerValidator computerValidator;
	@Autowired
	private DateMapper dateMapper;

	public Optional<Computer> fromDTO(DTOComputer dtoComputer) {

		System.out.println();
		System.out.println(dtoComputer.toString());
		System.out.println();
		long id = IDNULL;
		if (computerValidator.checkString(dtoComputer)) {
			if (!dtoComputer.getId().equals("0")) {
				id = Long.valueOf(dtoComputer.getId());
			}
			if (!dtoComputer.getIntroduced().isEmpty()) {
				if (!dtoComputer.getDiscontinued().isEmpty()) {

					return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
							.introduced(new Timestamp(dateMapper.getDate(dtoComputer.getIntroduced())))
							.discontinued(new Timestamp(dateMapper.getDate(dtoComputer.getDiscontinued())))
							.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getCompanyId()),
									dtoComputer.getCompany().getName()).build())
							.build());
				}

				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
						.introduced(new Timestamp(dateMapper.getDate(dtoComputer.getIntroduced())))
						.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getCompanyId()),
								dtoComputer.getCompany().getName()).build())
						.build());
			} else {

				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
						.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getCompanyId()),
								dtoComputer.getCompany().getName()).build())
						.build());
			}

		} else {

			return Optional.empty();
		}
	}

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
