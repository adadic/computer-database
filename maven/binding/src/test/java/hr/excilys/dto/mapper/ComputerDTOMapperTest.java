package hr.excilys.dto.mapper;

import static org.junit.Assert.*;

import java.time.LocalDate;
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
import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.validator.ComputerValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerDTOMapperTest {

	private static final long id = 1;
	@Autowired
	ComputerDTOMapper computerDTOMapper;

	@Before
	public void Setup() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFromDTOInvalid() {

		ComputerValidator computerValidator = Mockito.mock(ComputerValidator.class);
		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(false);

		assertEquals(Optional.empty(), computerDTOMapper.fromDTO(dtoComputer));
	}

//	@Test
//	public void testFromDTOValidNoDatesNoCompany() {
//
//		ComputerValidator computerValidator = Mockito.mock(ComputerValidator.class);
//		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
//		CompanyDTOMapper companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);
//
//		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(null);
//		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(false);
//
//		Mockito.when(dtoComputer.getId()).thenReturn("1");
//		Mockito.when(dtoComputer.getIntroduced()).thenReturn(null);
//		Mockito.when(dtoComputer.getDiscontinued()).thenReturn(null);
//		Mockito.when(dtoComputer.getComputerName()).thenReturn("alpha");
//		Mockito.when(dtoComputer.getCompany()).thenReturn(null);
//
//		Computer computer = new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id).company(null).build();
//
//		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
//	}
//
//	@Test
//	public void testFromDTOValidNoDates() {
//
//		Company company = new Company.CompanyBuilder(1, "Excilys").build();
//
//		ComputerValidator computerValidator = Mockito.mock(ComputerValidator.class);
//		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
//		CompanyDTOMapper companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);
//		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(Optional.of(company));
//
//		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(false);
//		Mockito.when(dtoComputer.getId()).thenReturn("1");
//		Mockito.when(dtoComputer.getIntroduced()).thenReturn(null);
//		Mockito.when(dtoComputer.getDiscontinued()).thenReturn(null);
//		Mockito.when(dtoComputer.getComputerName()).thenReturn("alpha");
//		Mockito.when(dtoComputer.getCompany()).thenReturn(null);
//
//		Computer computer = new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id).company(company).build();
//
//		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
//	}
//
//	@Test
//	public void testFromDTOValidOnlyIntroduced() {
//
//		Company company = new Company.CompanyBuilder(1, "Excilys").build();
//
//		ComputerValidator computerValidator = Mockito.mock(ComputerValidator.class);
//		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
//		CompanyDTOMapper companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);
//		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(false);
//		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(Optional.of(company));
//		Mockito.when(dtoComputer.getId()).thenReturn("");
//		Mockito.when(dtoComputer.getIntroduced()).thenReturn("");
//		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");
//		Mockito.when(dtoComputer.getComputerName()).thenReturn("");
//		Mockito.when(dtoComputer.getCompany()).thenReturn(null);
//
//		Computer computer = new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
//				.introduced(LocalDate.parse(dtoComputer.getIntroduced()))
//				.discontinued(LocalDate.parse(dtoComputer.getDiscontinued())).company(company).build();
//
//		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
//	}
//
//	@Test
//	public void testFromDTOValidAll() {
//
//		Company company = new Company.CompanyBuilder(1, "Excilys").build();
//
//		ComputerValidator computerValidator = Mockito.mock(ComputerValidator.class);
//		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
//		CompanyDTOMapper companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);
//		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(false);
//		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(Optional.of(company));
//		Mockito.when(dtoComputer.getId()).thenReturn("");
//		Mockito.when(dtoComputer.getIntroduced()).thenReturn("");
//		Mockito.when(dtoComputer.getDiscontinued()).thenReturn("");
//		Mockito.when(dtoComputer.getComputerName()).thenReturn("");
//		Mockito.when(dtoComputer.getCompany()).thenReturn(null);
//
//		Computer computer = new Computer.ComputerBuilder(dtoComputer.getComputerName()).id(id)
//				.introduced(LocalDate.parse(dtoComputer.getIntroduced()))
//				.discontinued(LocalDate.parse(dtoComputer.getDiscontinued())).company(company).build();
//
//		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
//	}

}
