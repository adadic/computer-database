package hr.excilys.dto.mapper;

import static org.junit.Assert.*;

import java.util.Optional;

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
import hr.excilys.dto.DTOCompany;
import hr.excilys.model.Company;
import hr.excilys.validator.CompanyValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class CompanyDTOMapperTest {

	private static final long id = 1;
	@Autowired
	CompanyDTOMapper companyDTOMapper;

	@Before
	public void Setup() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFromDTONull() {

		assertEquals(Optional.empty(), companyDTOMapper.fromDTO(null));
	}

	@Test
	public void testFromDTONumberFormatException() {

		CompanyValidator companyValidator = Mockito.mock(CompanyValidator.class);
		DTOCompany dtoCompany = Mockito.mock(DTOCompany.class);
		Mockito.when(companyValidator.checkCompanyFields(dtoCompany)).thenReturn(true);
		Mockito.when(dtoCompany.getCompanyId()).thenReturn("wqeqwe");

		assertEquals(Optional.empty(), companyDTOMapper.fromDTO(dtoCompany));
	}

	@Test
	public void testFromDTOInvalid() {

		CompanyValidator companyValidator = Mockito.mock(CompanyValidator.class);
		DTOCompany dtoCompany = Mockito.mock(DTOCompany.class);
		Mockito.when(companyValidator.checkCompanyFields(dtoCompany)).thenReturn(false);

		assertEquals(Optional.empty(), companyDTOMapper.fromDTO(dtoCompany));
	}

	@Test
	public void testFromDTOValid() {

		CompanyValidator companyValidator = Mockito.mock(CompanyValidator.class);
		DTOCompany dtoCompany = Mockito.mock(DTOCompany.class);
		Mockito.when(companyValidator.checkCompanyFields(dtoCompany)).thenReturn(false);
		Mockito.when(dtoCompany.getCompanyId()).thenReturn("1");
		Mockito.when(dtoCompany.getCompanyName()).thenReturn("Excilys");

		Company company = new Company.CompanyBuilder(id, dtoCompany.getCompanyName()).build();

		assertEquals(company, companyDTOMapper.fromDTO(dtoCompany).get());
	}

}
