package hr.excilys.validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOCompany;

@Component
public class CompanyValidator {

	private final static Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);
	
	public boolean checkCompanyFields(DTOCompany dtoCompany) {
		
		if(dtoCompany!=null) {
			
			if (StringUtils.isEmpty(dtoCompany.getCompanyName())) {
				
				LOGGER.info("Company has no name!");
				return false;
			
			}
			
			if(StringUtils.isEmpty(dtoCompany.getCompanyId())) {
				
				LOGGER.info("Company has no id!");
				return false;
			
			}
			
			return true;
			
		}
		
		return true;
		
	}
	
}
