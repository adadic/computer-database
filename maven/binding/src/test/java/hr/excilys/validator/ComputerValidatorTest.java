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

import hr.excilys.dto.DTOComputer;
import hr.excilys.config.BindingConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerValidatorTest {

	@Autowired
	private ComputerValidator computerValidator;
	private DTOComputer dtoComputer;
	
	@Before
	public void Setup() {
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckComputerFieldsValidComputer() {
		
		dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-01-01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("2020-01-01");

		assertEquals(true, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsInvalidName() {

		dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-01-01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}
	

	@Test
	public void testCheckComputerFieldsNullName() {

		dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn(null);
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-01-01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsInvalidDate() {

		dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020/01/01");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsStringInDate() {

		dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("qweqwgwewq");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}

	@Test
	public void testCheckComputerFieldsInvalidDate2() {

		dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(dtoComputer.getComputerName()).thenReturn("Happy");
		Mockito.when(dtoComputer.getIntroduced()).thenReturn("2020-13-32");
		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");

		assertEquals(false, computerValidator.checkComputerFields(dtoComputer));
	}
}
