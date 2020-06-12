package hr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;

public class ComputerMapper {

	public Computer getComputer(ResultSet resultSet) throws SQLException {

		return new Computer.ComputerBuilder(resultSet.getString("computer.name")).id(resultSet.getLong("computer.id"))
				.introduced(resultSet.getTimestamp("computer.introduced"))
				.discontinued(resultSet.getTimestamp("computer.discontinued"))
				.company(
						new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name"))
								.build())
				.build();
	}

	public static Optional<Computer> fromDTO(DTOComputer dtoComputer) {

		Timestamp disconDate;
		Timestamp introDate;
		long id = 0;
		if (!dtoComputer.getId().equals("0")) {
			id = Long.valueOf(dtoComputer.getId());
		}
		if (dtoComputer.getName() == "") {

			return Optional.empty();
		} else {
			if (dtoComputer.getIntroduced() != "" && dtoComputer.getIntroduced().matches("\\d{4}-\\d{2}-\\d{2}")) {
				introDate = new Timestamp(DateMapper.getDate(dtoComputer.getIntroduced()));
				if (dtoComputer.getDiscontinued() != ""
						&& dtoComputer.getDiscontinued().matches("\\d{4}-\\d{2}-\\d{2}")) {
					disconDate = new Timestamp(DateMapper.getDate(dtoComputer.getDiscontinued()));
					if (disconDate.getTime() < introDate.getTime()) {

						return Optional.empty();
					}
					return Optional.of(new Computer.ComputerBuilder(dtoComputer.getName()).id(id)
							.introduced(new Timestamp(DateMapper.getDate(dtoComputer.getIntroduced())))
							.discontinued(new Timestamp(DateMapper.getDate(dtoComputer.getDiscontinued())))
							.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getId()),
									dtoComputer.getCompany().getName()).build())
							.build());
				} else {
					return Optional.of(new Computer.ComputerBuilder(dtoComputer.getName()).id(id)
							.introduced(new Timestamp(DateMapper.getDate(dtoComputer.getIntroduced())))
							.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getId()),
									dtoComputer.getCompany().getName()).build())
							.build());
				}
			} else {
				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getName()).id(id)
						.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getId()),
								dtoComputer.getCompany().getName()).build())
						.build());
			}

		}

	}
}
