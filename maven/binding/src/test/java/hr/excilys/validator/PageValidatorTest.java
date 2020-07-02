package hr.excilys.validator;

import static org.junit.Assert.assertEquals;

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

import hr.excilys.dto.DTOPagination;
import hr.excilys.config.BindingConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class PageValidatorTest {

	@Autowired
	private PageValidator pageValidator;
	private DTOPagination pageMock;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckPageValid() {

		pageMock = Mockito.mock(DTOPagination.class);
		Mockito.when(pageMock.getPage()).thenReturn("1");
		Mockito.when(pageMock.getLines()).thenReturn("10");
		Mockito.when(pageMock.getDirection()).thenReturn("0");

		assertEquals(true, pageValidator.checkPage(pageMock));
	}

	@Test
	public void testCheckPageInvalidPage() {

		pageMock = Mockito.mock(DTOPagination.class);
		Mockito.when(pageMock.getPage()).thenReturn("");
		Mockito.when(pageMock.getLines()).thenReturn("10");
		Mockito.when(pageMock.getDirection()).thenReturn("0");

		assertEquals(false, pageValidator.checkPage(pageMock));
	}

	@Test
	public void testCheckPageInvalidLine() {

		pageMock = Mockito.mock(DTOPagination.class);
		Mockito.when(pageMock.getPage()).thenReturn("1");
		Mockito.when(pageMock.getLines()).thenReturn("");
		Mockito.when(pageMock.getDirection()).thenReturn("0");

		assertEquals(false, pageValidator.checkPage(pageMock));
	}

	@Test
	public void testCheckPageInvalidDirection() {

		pageMock = Mockito.mock(DTOPagination.class);
		Mockito.when(pageMock.getPage()).thenReturn("1");
		Mockito.when(pageMock.getLines()).thenReturn("10");
		Mockito.when(pageMock.getDirection()).thenReturn("");

		assertEquals(false, pageValidator.checkPage(pageMock));
	}

	@Test
	public void testCheckPageNull() {

		pageMock = Mockito.mock(DTOPagination.class);
		Mockito.when(pageMock.getPage()).thenReturn(null);
		Mockito.when(pageMock.getLines()).thenReturn("10");
		Mockito.when(pageMock.getDirection()).thenReturn("");

		assertEquals(false, pageValidator.checkPage(pageMock));
	}
}
