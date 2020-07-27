package hr.excilys.validator;

import static org.junit.Assert.assertEquals;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import hr.excilys.dto.DTOUser;

public class PasswordMatchesValidatorTest {
	
	@Autowired
	private DTOUser mockUser;
	
	private PasswordMatchesValidator passwordValidator = new PasswordMatchesValidator();
	private ConstraintValidatorContext context;
	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIsValidValid() {
		
		mockUser = Mockito.mock(DTOUser.class);
		Mockito.when(mockUser.getPassword()).thenReturn("password");
		Mockito.when(mockUser.getMatchingPassword()).thenReturn("password");

		assertEquals(true, passwordValidator.isValid(mockUser, context));
	}
	
	@Test
	public void testIsValidNotValid() {
		
		mockUser = Mockito.mock(DTOUser.class);
		Mockito.when(mockUser.getPassword()).thenReturn("password");
		Mockito.when(mockUser.getMatchingPassword()).thenReturn("password1");

		assertEquals(false, passwordValidator.isValid(mockUser, context));
	}

}
