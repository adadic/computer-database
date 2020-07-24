package hr.excilys.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOComputer;
import hr.excilys.mapper.DateMapper;

@Component
public class ComputerValidator {

	private final static Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);
	private final DateMapper dateMapper;
	private final CompanyValidator companyValidator;

	@Autowired
	public ComputerValidator(DateMapper dateMapper, CompanyValidator companyValidator) {
		
		this.dateMapper = dateMapper;
		this.companyValidator = companyValidator;
		
	}

	public boolean checkComputerFields(DTOComputer dtoComputer) {
		
		if (StringUtils.isEmpty(dtoComputer.getComputerName())) {
			LOGGER.info("Computer has no name!");

			return false;
		}
		try {
			
			if (!StringUtils.isEmpty(dtoComputer.getIntroduced())) {
				return false;
			}
			
			if(!StringUtils.isEmpty(dtoComputer.getDiscontinued())) {
				return false;
			}
			
			LocalDate timeIntro = checkDate(dtoComputer.getIntroduced());
			LocalDate timeDiscon = checkDate(dtoComputer.getDiscontinued());
			
			if (timeIntro.isAfter(timeDiscon)) {
				LOGGER.info("introduced Date after Discontinued Date in this Computer");
				return false;
			}
			
			if(!companyValidator.checkCompanyFields(dtoComputer.getCompany())) {
				return false;
			}
			
			LOGGER.info("Computer can be created");
			return true;
			
		} catch (DateTimeParseException dtpe) {
			
			LOGGER.error("NullPointerException -> At least one Field was null !!");
			return false;
			
		}
	}

	private LocalDate checkDate(String date) throws DateTimeParseException {

		return dateMapper.getDate(date);
	}
}
