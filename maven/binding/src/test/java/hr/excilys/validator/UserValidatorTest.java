package hr.excilys.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import hr.excilys.dto.DTOUser;

public class UserValidatorTest {

	@Autowired
	private DTOUser mockUser;
	private UserValidator userValidator = new UserValidator();
	
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
