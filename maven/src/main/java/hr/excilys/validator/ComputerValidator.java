package hr.excilys.validator;

import java.sql.Timestamp;

import hr.excilys.dto.DTOComputer;
import hr.excilys.mapper.DateMapper;

public class ComputerValidator {

	public static boolean checkString(DTOComputer dtoComputer) {

		try {
			if (dtoComputer.getName().isEmpty()) {

				return false;
			}
			if (!dtoComputer.getIntroduced().isEmpty() && dtoComputer.getIntroduced().matches("\\d{4}-\\d{2}-\\d{2}")) {
				Timestamp timeIntro = new Timestamp(DateMapper.getDate(dtoComputer.getIntroduced()));
				if (!dtoComputer.getDiscontinued().isEmpty()
						&& dtoComputer.getDiscontinued().matches("\\d{4}-\\d{2}-\\d{2}")) {
					Timestamp timeDiscon = new Timestamp(DateMapper.getDate(dtoComputer.getDiscontinued()));
					if (timeIntro.getTime() > timeDiscon.getTime()) {
						
						return false;
					}
				}
			}
			return true;
		} catch (NullPointerException npe) {

			return false;
		}
	}

}
