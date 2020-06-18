package hr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.validator.ComputerValidator;

@Component
public final class ComputerMapper {

	private static final long IDNULL = 0L;

	public static Computer getComputer(ResultSet resultSet) throws SQLException {

		return new Computer.ComputerBuilder(resultSet.getString("computer.name")).id(resultSet.getLong("computer.id"))
				.introduced(resultSet.getTimestamp("computer.introduced"))
				.discontinued(resultSet.getTimestamp("computer.discontinued"))
				.company(
						new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name"))
								.build())
				.build();
	}

	public static Optional<Computer> fromDTO(DTOComputer dtoComputer) {

		long id = IDNULL;
		if (ComputerValidator.checkString(dtoComputer)) {
			if (!dtoComputer.getId().equals("0")) {
				id = Long.valueOf(dtoComputer.getId());
			}
			if (dtoComputer.getIntroduced() != "") {
				if (dtoComputer.getDiscontinued() != "") {

					return Optional.of(new Computer.ComputerBuilder(dtoComputer.getName()).id(id)
							.introduced(new Timestamp(DateMapper.getDate(dtoComputer.getIntroduced())))
							.discontinued(new Timestamp(DateMapper.getDate(dtoComputer.getDiscontinued())))
							.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getId()),
									dtoComputer.getCompany().getName()).build())
							.build());
				}

				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getName()).id(id)
						.introduced(new Timestamp(DateMapper.getDate(dtoComputer.getIntroduced())))
						.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getId()),
								dtoComputer.getCompany().getName()).build())
						.build());
			} else {

				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getName()).id(id)
						.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getId()),
								dtoComputer.getCompany().getName()).build())
						.build());
			}

		} else {

			return Optional.empty();
		}
	}
}
