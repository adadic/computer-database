package hr.excilys.dto.mapper;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOComputer;
import hr.excilys.dto.DTOComputerAdd;
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
			LocalDate introduced = dateMapper.getDate(dtoComputer.getIntroduced());
			LocalDate discontinued = dateMapper.getDate(dtoComputer.getDiscontinued());
			Optional<Company> optionalCompany = companyDTOMapper.fromDTO(dtoComputer.getCompany());
			Company company = optionalCompany.isPresent() ? optionalCompany.get() : null;

			return Optional.of(new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id).introduced(introduced)
					.discontinued(discontinued).company(company).build());

		} else {

			return Optional.empty();
		}
	}
	
	public Optional<Computer> fromDTOAdd(DTOComputerAdd dtoComputerAdd){
		
		if (computerValidator.checkComputerFieldsAdd(dtoComputerAdd)) {
			long id = longMapper.getId(dtoComputerAdd.getId());
			LocalDate introduced = dateMapper.getDate(dtoComputerAdd.getIntroduced());
			LocalDate discontinued = dateMapper.getDate(dtoComputerAdd.getDiscontinued());
			Optional<Company> optionalCompany = companyDTOMapper.fromString(dtoComputerAdd.getCompanyId());
			Company company = optionalCompany.isPresent() ? optionalCompany.get() : null;

			return Optional.of(new Computer.ComputerBuilder(dtoComputerAdd.getComputerName()).id(id).introduced(introduced)
					.discontinued(discontinued).company(company).build());

		} else {

			return Optional.empty();
		}
		
	}
}
