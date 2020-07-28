package hr.excilys.dto.mapper;

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
import hr.excilys.dto.DTOPagination;
import hr.excilys.model.Pagination;
import hr.excilys.validator.PageValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class PaginationDTOMapperTest {

	@Autowired
	PaginationDTOMapper paginationDTOMapper;
	private static final int count = 1;
	private static final int INITPAGE = 1;
	private static final int INITLINES = 10;
	private static final int INITZERO = 0;
	private PageValidator pageValidator;

	@Before
	public void Setup() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetPageValid() {

		pageValidator = Mockito.mock(PageValidator.class);
		DTOPagination dtoPagination = new DTOPagination("10", "1", "", "", "0", "");
		Mockito.when(pageValidator.checkPage(dtoPagination)).thenReturn(true);

		Pagination page = new Pagination.PaginationBuilder(Integer.valueOf(dtoPagination.getLines()),
				Integer.valueOf(dtoPagination.getPage()), dtoPagination.getSearch()).count(count)
						.order(dtoPagination.getOrder()).direction(Integer.valueOf(dtoPagination.getDirection()))
						.build();

		assertEquals(page, paginationDTOMapper.getPage(dtoPagination, count));
	}

	@Test
	public void testGetPageInvalid() {

		pageValidator = Mockito.mock(PageValidator.class);
		DTOPagination dtoPagination = new DTOPagination("10", "1", "", "", "0", "");
		Mockito.when(pageValidator.checkPage(dtoPagination)).thenReturn(false);

		Pagination page = new Pagination.PaginationBuilder(INITLINES, INITPAGE, dtoPagination.getSearch()).count(count)
				.order(dtoPagination.getOrder()).direction(INITZERO).build();

		assertEquals(page, paginationDTOMapper.getPage(dtoPagination, count));
	}
}
