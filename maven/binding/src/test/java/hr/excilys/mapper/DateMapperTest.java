package hr.excilys.mapper;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import hr.excilys.config.BindingConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class DateMapperTest {
	
	@Autowired
	private DateMapper mapper;
	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDateValidDate() {

		assertEquals(LocalDate.of(2020, 06, 30), mapper.getDate("2020-06-30"));
	
	}
	
	@Test(expected = DateTimeParseException.class)
	public void testGetDateInvalidDAte() {

		mapper.getDate("2020-06-32");
	}

	@Test(expected = DateTimeParseException.class)
	public void testGetDateSlashDate() {

		mapper.getDate("2020/02/02");
	}

	@Test(expected = DateTimeParseException.class)
	public void testGetDateIntegerString() {

		mapper.getDate("123456789");
	}

	@Test(expected = DateTimeParseException.class)
	public void testGetDateNull() {

		mapper.getDate(null);
	}
}
