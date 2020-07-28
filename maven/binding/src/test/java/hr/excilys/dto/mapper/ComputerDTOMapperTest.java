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
import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.mapper.DateMapper;
import hr.excilys.mapper.LongMapper;
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
	private ComputerValidator computerValidator;
	private LongMapper longMapper;
	private DateMapper dateMapper;
	private CompanyDTOMapper companyDTOMapper;

	@Before
	public void Setup() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFromDTOInvalid() {

		computerValidator = Mockito.mock(ComputerValidator.class);
		DTOComputer dtoComputer = Mockito.mock(DTOComputer.class);
		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(false);

		assertEquals(Optional.empty(), computerDTOMapper.fromDTO(dtoComputer));
	}

	@Test
	public void testFromDTOValidNoDatesNoCompany() {

		Computer computer = new Computer.ComputerBuilder("qwert").id(id).company(null).build();
		DTOComputer dtoComputer = new DTOComputer("1", "qwert", "", "", null);

		computerValidator = Mockito.mock(ComputerValidator.class);
		longMapper = Mockito.mock(LongMapper.class);
		dateMapper = Mockito.mock(DateMapper.class);
		companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);

		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(Optional.empty());
		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(longMapper.getId(dtoComputer.getId())).thenReturn(1L);
		Mockito.when(dateMapper.getDate(dtoComputer.getIntroduced())).thenReturn(null);
		Mockito.when(dateMapper.getDate(dtoComputer.getDiscontinued())).thenReturn(null);

		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
	}

	@Test
	public void testFromDTOValidNoDates() {

		Company company = new Company.CompanyBuilder(1, "Excilys").build();
		Computer computer = new Computer.ComputerBuilder("qwert").id(id).company(company).build();
		DTOCompany dtoCompany = new DTOCompany("1", "Excilys");
		DTOComputer dtoComputer = new DTOComputer("1", "qwert", "", "", dtoCompany);

		computerValidator = Mockito.mock(ComputerValidator.class);
		longMapper = Mockito.mock(LongMapper.class);
		dateMapper = Mockito.mock(DateMapper.class);
		companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);

		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(Optional.empty());
		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(longMapper.getId(dtoComputer.getId())).thenReturn(1L);
		Mockito.when(dateMapper.getDate(dtoComputer.getIntroduced())).thenReturn(null);
		Mockito.when(dateMapper.getDate(dtoComputer.getDiscontinued())).thenReturn(null);

		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
	}

	@Test
	public void testFromDTOValidOnlyIntroduced() {

		LocalDate introduced = LocalDate.parse("2012-10-15");
		Company company = new Company.CompanyBuilder(1, "Excilys").build();
		Computer computer = new Computer.ComputerBuilder("qwert").id(id).introduced(introduced).company(company)
				.build();
		DTOCompany dtoCompany = new DTOCompany("1", "Excilys");
		DTOComputer dtoComputer = new DTOComputer("1", "qwert", "2012-10-15", "", dtoCompany);

		computerValidator = Mockito.mock(ComputerValidator.class);
		longMapper = Mockito.mock(LongMapper.class);
		dateMapper = Mockito.mock(DateMapper.class);
		companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);

		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(Optional.empty());
		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(longMapper.getId(dtoComputer.getId())).thenReturn(1L);
		Mockito.when(dateMapper.getDate(dtoComputer.getIntroduced())).thenReturn(introduced);
		Mockito.when(dateMapper.getDate(dtoComputer.getDiscontinued())).thenReturn(null);

		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
	}

	@Test
	public void testFromDTOValidAll() {

		LocalDate introduced = LocalDate.parse("2012-10-15");
		LocalDate discontinued = LocalDate.parse("2012-10-17");
		Company company = new Company.CompanyBuilder(1, "Excilys").build();
		Computer computer = new Computer.ComputerBuilder("qwert").id(id).introduced(introduced)
				.discontinued(discontinued).company(company).build();
		DTOCompany dtoCompany = new DTOCompany("1", "Excilys");
		DTOComputer dtoComputer = new DTOComputer("1", "qwert", "2012-10-15", "2012-10-17", dtoCompany);

		computerValidator = Mockito.mock(ComputerValidator.class);
		longMapper = Mockito.mock(LongMapper.class);
		dateMapper = Mockito.mock(DateMapper.class);
		companyDTOMapper = Mockito.mock(CompanyDTOMapper.class);

		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(companyDTOMapper.fromDTO(null)).thenReturn(Optional.empty());
		Mockito.when(computerValidator.checkComputerFields(dtoComputer)).thenReturn(true);
		Mockito.when(longMapper.getId(dtoComputer.getId())).thenReturn(1L);
		Mockito.when(dateMapper.getDate(dtoComputer.getIntroduced())).thenReturn(null);
		Mockito.when(dateMapper.getDate(dtoComputer.getDiscontinued())).thenReturn(null);

		assertEquals(computer, computerDTOMapper.fromDTO(dtoComputer).get());
	}
}
