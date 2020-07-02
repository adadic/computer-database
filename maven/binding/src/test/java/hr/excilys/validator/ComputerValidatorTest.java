package validator;

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

import config.SpringTestConfiguration;
import dto.DTOComputer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerValidatorTest {

	@Autowired
	private ComputerValidator computerValidator;
	
	@Before
	public void Setup() {
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckComputerFieldsValidComputer() {
		
		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-01-01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("2020-01-01");

		assertEquals(true, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsInvalidName() {

		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-01-01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}
	

	@Test
	public void testCheckComputerFieldsNullName() {

		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn(null);
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-01-01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsInvalidDate() {

		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020/01/01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsStringInDate() {

		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("qweqwgwewq");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsInvalidDate2() {

		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-13-32");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}
}
