package hr.excilys.dto.mapper;

import java.sql.Timestamp;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.mapper.DateMapper;
import hr.excilys.validator.ComputerValidator;

@Component
public class ComputerDTOMapper {

	private static final long IDNULL = 0L;

	private final ComputerValidator computerValidator;
	private final DateMapper dateMapper;

	@Autowired
	public ComputerDTOMapper(ComputerValidator computerValidator, DateMapper dateMapper) {

		this.computerValidator = computerValidator;
		this.dateMapper = dateMapper;
	}

	public Optional<Computer> fromDTO(DTOComputer dtoComputer) {

		long id = IDNULL;
		if (computerValidator.checkComputerFields(dtoComputer)) {
			if (!dtoComputer.getId().equals("0")) {
				id = Long.valueOf(dtoComputer.getId());
			}
			if (StringUtils.isNotEmpty(dtoComputer.getIntroduced())) {
				if (StringUtils.isNotEmpty(dtoComputer.getDiscontinued())) {

					return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
							.introduced(new Timestamp(dateMapper.getDate(dtoComputer.getIntroduced())))
							.discontinued(new Timestamp(dateMapper.getDate(dtoComputer.getDiscontinued())))
							.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getCompanyId()),
									dtoComputer.getCompany().getCompanyName()).build())
							.build());
				}

				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
						.introduced(new Timestamp(dateMapper.getDate(dtoComputer.getIntroduced())))
						.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getCompanyId()),
								dtoComputer.getCompany().getCompanyName()).build())
						.build());
			} else {

				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
						.company(new Company.CompanyBuilder(Long.valueOf(dtoComputer.getCompany().getCompanyId()),
								dtoComputer.getCompany().getCompanyName()).build())
						.build());
			}

		} else {

			return Optional.empty();
		}
	}
}
