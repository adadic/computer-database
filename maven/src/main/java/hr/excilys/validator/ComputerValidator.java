package hr.excilys.validator;

import java.sql.Timestamp;

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

	public boolean checkDate(DTOComputer dtoComputer) {

		try {
			if (dtoComputer.getComputerName().isEmpty()) {

				LOGGER.info("Computer has no name!");
				return false;
			}
			if (!dtoComputer.getIntroduced().isEmpty() && dtoComputer.getIntroduced().matches("\\d{4}-\\d{2}-\\d{2}")) {
				Timestamp timeIntro = new Timestamp(dateMapper.getDate(dtoComputer.getIntroduced()));
				if (!dtoComputer.getDiscontinued().isEmpty()
						&& dtoComputer.getDiscontinued().matches("\\d{4}-\\d{2}-\\d{2}")) {
					Timestamp timeDiscon = new Timestamp(dateMapper.getDate(dtoComputer.getDiscontinued()));
					if (timeIntro.getTime() > timeDiscon.getTime()) {

						LOGGER.info("introduced Date after Discontinued Date in this Computer");
						return false;
					}
				}
			}
			LOGGER.info("Computer can be created");

			return true;
		} catch (NullPointerException npe) {

			LOGGER.error("NullPointerException -> At least one Field was null !!");
			return false;
		}
	}

}
