package hr.excilys.validator;

import static org.junit.Assert.*;

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
public class UserValidatorTest {

	@Autowired
	private UserValidator userValidator = new UserValidator();
	private DTOUser mockUser;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckUserValid() {

		mockUser = Mockito.mock(DTOUser.class);
		Mockito.when(mockUser.getUsername()).thenReturn("alain");
		Mockito.when(mockUser.getPassword()).thenReturn("password");

		assertEquals(true, userValidator.checkUser(mockUser));
	}

	@Test
	public void testIsValidValidNoUsername() {

		mockUser = Mockito.mock(DTOUser.class);
		Mockito.when(mockUser.getUsername()).thenReturn("");
		Mockito.when(mockUser.getPassword()).thenReturn("password");

		assertEquals(false, userValidator.checkUser(mockUser));
	}

	@Test
	public void testIsValidValidNoPassword() {

		mockUser = Mockito.mock(DTOUser.class);
		Mockito.when(mockUser.getUsername()).thenReturn("alain");
		Mockito.when(mockUser.getPassword()).thenReturn("");

		assertEquals(false, userValidator.checkUser(mockUser));
	}

	@Test
	public void testIsValidValidInvalid() {

		mockUser = Mockito.mock(DTOUser.class);
		Mockito.when(mockUser.getUsername()).thenReturn("");
		Mockito.when(mockUser.getPassword()).thenReturn("");

		assertEquals(false, userValidator.checkUser(mockUser));
	}
}
