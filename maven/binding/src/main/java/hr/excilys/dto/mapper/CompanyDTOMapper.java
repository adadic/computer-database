package hr.excilys.dto.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOCompany;
import hr.excilys.mapper.IntMapper;
import hr.excilys.model.Company;
import hr.excilys.validator.CompanyValidator;

@Component
public class CompanyDTOMapper {

	private final CompanyValidator companyValidator;

	@Autowired
	public CompanyDTOMapper(CompanyValidator companyValidator) {
		this.companyValidator = companyValidator;
	}

	public Optional<Company> fromDTO(DTOCompany dtoCompany) {

		if (dtoCompany == null) {
			return Optional.empty();
		}

		if (companyValidator.checkCompanyFields(dtoCompany)) {

			long id = IntMapper.getId(dtoCompany.getCompanyId());
			Company company = new Company.CompanyBuilder(id, dtoCompany.getCompanyName()).build();

			return Optional.of(company);
		}

		return Optional.empty();

	}

}
