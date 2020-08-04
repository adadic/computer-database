package hr.excilys.dto.mapper;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOCompany;
import hr.excilys.mapper.LongMapper;
import hr.excilys.model.Company;
import hr.excilys.persistence.DAOCompany;
import hr.excilys.validator.CompanyValidator;

@Component
public class CompanyDTOMapper {

	private final CompanyValidator companyValidator;
	private final LongMapper longMapper;
	private final DAOCompany daoCompany;

	@Autowired
	public CompanyDTOMapper(CompanyValidator companyValidator, LongMapper longMapper, DAOCompany daoCompany) {

		this.companyValidator = companyValidator;
		this.longMapper = longMapper;
		this.daoCompany = daoCompany;
	}

	public Optional<Company> fromDTO(DTOCompany dtoCompany) {

		if (dtoCompany == null) {

			return Optional.empty();
		}

		if (companyValidator.checkCompanyFields(dtoCompany)) {

			try {
				long id = longMapper.getId(dtoCompany.getCompanyId());
				Company company = new Company.CompanyBuilder(id, dtoCompany.getCompanyName()).build();

				return Optional.of(company);

			} catch (NumberFormatException nfe) {

				return Optional.empty();

			}
		}

		return Optional.empty();
	}
	
	public Optional<Company> fromString(String companyId){
		
		if (StringUtils.isEmpty(companyId)) {
			return Optional.empty();
		}
		try {
			
			long id=longMapper.getId(companyId);
			return daoCompany.getCompanyById(id);
			
		}catch(NumberFormatException nfe) {
			return Optional.empty();
		}
		
	}

}
