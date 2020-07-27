package hr.excilys.dto.mapper;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.mapper.DateMapper;
import hr.excilys.mapper.LongMapper;
import hr.excilys.validator.ComputerValidator;

@Component
public class ComputerDTOMapper {

	private final ComputerValidator computerValidator;
	private final DateMapper dateMapper;
	private final LongMapper longMapper;
	private final CompanyDTOMapper companyDTOMapper;

	@Autowired
	public ComputerDTOMapper(ComputerValidator computerValidator, DateMapper dateMapper, 
			CompanyDTOMapper companyDTOMapper, LongMapper longMapper) {

		this.computerValidator = computerValidator;
		this.dateMapper = dateMapper;
		this.longMapper = longMapper;
		this.companyDTOMapper = companyDTOMapper;
		
	}

	public Optional<Computer> fromDTO(DTOComputer dtoComputer) {

		if (computerValidator.checkComputerFields(dtoComputer)) {
			long id = longMapper.getId(dtoComputer.getId());
			if (StringUtils.isNotEmpty(dtoComputer.getIntroduced())) {
				if (StringUtils.isNotEmpty(dtoComputer.getDiscontinued())) {

					Optional<Company> optionalCompany= companyDTOMapper.fromDTO(dtoComputer.getCompany());
					Company company = optionalCompany.isPresent() ? optionalCompany.get() : null;
					return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
							.introduced(dateMapper.getDate(dtoComputer.getIntroduced()))
							.discontinued(dateMapper.getDate(dtoComputer.getDiscontinued()))
							.company(company)
							.build());
				}

				return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
						.introduced(dateMapper.getDate(dtoComputer.getIntroduced()))
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
