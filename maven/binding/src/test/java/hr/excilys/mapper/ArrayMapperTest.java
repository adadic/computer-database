package hr.excilys.mapper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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
public class ArrayMapperTest {

	@Autowired
	private ArrayMapper mapper;
	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testStringTransformValidArray() {

		List<String> reference = Arrays.asList("1", "2", "3");
		
		assertEquals(reference, mapper.stringTransform("1,2,3"));
	}
	
	@Test
	public void testStringTransformOneElement() {

		List<String> reference = Arrays.asList("1");
		
		assertEquals(reference, mapper.stringTransform("1"));
	}

	@Test
	public void testStringTransformNullElement() {

		List<String> reference = Arrays.asList();
		
		assertEquals(reference, mapper.stringTransform(null));
	}
	
	@Test
	public void testStringTransformEmpty() {

		List<String> reference = Arrays.asList();
		
		assertEquals(reference, mapper.stringTransform(""));
	}
	
	@Test
	public void testStringTransformText() {

		List<String> reference = Arrays.asList("Morgan", "Freeman");
		
		assertEquals(reference, mapper.stringTransform("Morgan,Freeman"));
	}
	
	@Test
	public void testStringTransformSpaces() {

		List<String> reference = Arrays.asList("1", "2");
		
		assertNotEquals(reference, mapper.stringTransform("1, 2"));
	}
}
