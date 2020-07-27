package hr.excilys.validator;

import static org.junit.Assert.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;

public class EmailValidatorTest {

	private ConstraintValidatorContext context;
	
	@Test
	public void testIsValidOK() {
		
		EmailValidator emailValidator = new EmailValidator();
		assertEquals(true, emailValidator.isValid("ysabourin@excilys.com", context));
	}
	
	@Test
	public void testIsValidNotOK() {

		EmailValidator emailValidator = new EmailValidator();
		assertEquals(false, emailValidator.isValid("", context));
	}
}
