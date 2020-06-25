package hr.excilys.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import hr.excilys.dto.DTOComputer;
import junit.framework.TestCase;

public class ComputerValidatorTest extends TestCase{

	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCheckString() {
		ComputerValidator computerValidator = new ComputerValidator();
		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-01-01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");
		
		
		assertEquals(true, computerValidator.checkString(dtoComputer));
		
	}
}
