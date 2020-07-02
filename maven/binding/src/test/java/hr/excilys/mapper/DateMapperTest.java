package mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import config.SpringTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
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

		assertEquals(1593468000000L, mapper.getDate("2020-06-30"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDateInvalidDAte() {

		mapper.getDate("2020-06-32");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDateSlashDate() {

		mapper.getDate("2020/02/02");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDateIntegerString() {

		mapper.getDate("123456789");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDateNull() {

		mapper.getDate(null);
	}
}
