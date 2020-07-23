package hr.excilys.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hr.excilys.dto.DTOUser;



public class PasswordMatchesValidator 
  implements ConstraintValidator<CustomPasswordMatchesAnnotation, Object> { 
    
	 @Override
	    public void initialize(CustomPasswordMatchesAnnotation constraintAnnotation) {       
	    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){   
        DTOUser user = (DTOUser) obj;
        return user.getPassword().equals(user.getMatchingPassword());    
    }
	 
}