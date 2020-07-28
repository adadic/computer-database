package hr.excilys.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOUser;

@Component
public class UserValidator {

	public boolean checkUser(DTOUser dtoUser) {

		if (StringUtils.isNotEmpty(dtoUser.getUsername()) && StringUtils.isNotEmpty(dtoUser.getPassword())) {

			return true;
		}

		return false;
	}

}
