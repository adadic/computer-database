package hr.excilys.validator;

import static org.junit.Assert.assertEquals;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import hr.excilys.config.BindingConfig;
import hr.excilys.dto.DTOUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class PasswordMatchesValidatorTest {

	@Autowired
	private PasswordMatchesValidator passwordValidator;
	private ConstraintValidatorContext context;
	private DTOUser mockUser;

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
