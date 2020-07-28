package hr.excilys.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOUser;

@Component
public class PasswordMatchesValidator implements ConstraintValidator<CustomPasswordMatchesAnnotation, Object> {

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		DTOUser user = (DTOUser) obj;
		
		return user.getPassword().equals(user.getMatchingPassword());
	}

}