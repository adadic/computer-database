package hr.excilys.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import hr.excilys.config.BindingConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class LongMapperTest {

	@Autowired
	private LongMapper longMapper;

	@Test
	public void testGetIdInt() {

		assertEquals(5L, longMapper.getId("5"));
	}

	@Test(expected = NumberFormatException.class)
	public void testGetIdLong() {

		assertEquals(5L, longMapper.getId("5L"));
	}

	@Test(expected = NumberFormatException.class)
	public void testGetIdString() {

		longMapper.getId("blablou");
	}

	@Test(expected = NumberFormatException.class)
	public void testGetIdNull() {

		longMapper.getId(null);
	}

}
