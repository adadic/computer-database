package hr.excilys.dto.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOCompany;
import hr.excilys.mapper.LongMapper;
import hr.excilys.model.Company;
import hr.excilys.validator.CompanyValidator;

@Component
public class CompanyDTOMapper {

	private final CompanyValidator companyValidator;
	private final LongMapper longMapper;
	
	@Autowired
	public CompanyDTOMapper(CompanyValidator companyValidator, LongMapper longMapper) {
		this.companyValidator=companyValidator;
		this.longMapper = longMapper;
	}
	
	public Optional<Company> fromDTO(DTOCompany dtoCompany){
		
		if(dtoCompany==null) {
			return Optional.empty();
		}
		
		if(companyValidator.checkCompanyFields(dtoCompany)) {
			
			try {
				
				long id= longMapper.getId(dtoCompany.getCompanyId());
				Company company= new Company.CompanyBuilder(id, dtoCompany.getCompanyName())
						.build();
				
				return Optional.of(company);
				
			} catch (NumberFormatException nfe) {
		
				return Optional.empty();
			
			}
		}
		
		return Optional.empty();
		
	}
	
}
