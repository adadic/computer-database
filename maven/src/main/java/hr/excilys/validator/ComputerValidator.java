package hr.excilys.validator;

import java.sql.Timestamp;

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
	@Autowired
	private DateMapper dateMapper;

	public boolean checkComputerFields(DTOComputer dtoComputer) {
		
		if (StringUtils.isEmpty(dtoComputer.getComputerName())) {
			LOGGER.info("Computer has no name!");

			return false;
		}
		try {

			if (StringUtils.isNotEmpty(dtoComputer.getIntroduced())) {
				Timestamp timeIntro = checkDate(dtoComputer.getIntroduced());
				System.out.println(timeIntro.getTime());
				if (StringUtils.isNotEmpty(dtoComputer.getDiscontinued())) {
					Timestamp timeDiscon = checkDate(dtoComputer.getDiscontinued());
					System.out.println(timeDiscon.getTime());
					if (timeIntro.getTime() > timeDiscon.getTime()) {
						LOGGER.info("introduced Date after Discontinued Date in this Computer");
		
						return false;
					}
				}
			}
			LOGGER.info("Computer can be created");

			return true;
		} catch (IllegalArgumentException illae) {
			LOGGER.error("NullPointerException -> At least one Field was null !!");

			return false;
		}
	}

	private Timestamp checkDate(String date) throws IllegalArgumentException {

		return new Timestamp(dateMapper.getDate(date));
	}
}
