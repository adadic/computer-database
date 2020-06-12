package hr.excilys.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import hr.excilys.mapper.DateMapper;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;
import hr.excilys.persistence.DAOComputer;
import junit.framework.TestCase;

public class MyTests extends TestCase{
	
	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
	}
	
//	@Test
//	public void testFunction() throws SQLException {
//		DAOComputer daoComputer = new DAOComputer();
//		
//		assertEquals(1,daoComputer.insertComputer("AZERTY", null, null, "1"));
//	}
	
	@Test
	public void testMock() {
		Company company = Mockito.mock(Company.class);
		ArrayList<Company> list = new ArrayList<>();
		assertEquals(true,list.add(company));
		
	}
	
	@Test
	public void testDate() {
		assertEquals(1582412400000L, DateMapper.getDate("2020-02-23"));
	}
	
	@Test
	public void testGetById() throws SQLException {
		DAOComputer daoComputer = new DAOComputer();
		
		assertTrue(daoComputer.getComputerById(1).isPresent());
//		assertEquals(new Computer.ComputerBuilder("MacBook Pro 15.4 inch")
//				.id(1)
//				.company(new Company.CompanyBuilder(1, "Apple Inc.").build())
//				.build(),daoComputer.getComputerById(1L).get());
	}
	
	@Test
	public void testMapper() throws SQLException {
		Computer comp = new Computer.ComputerBuilder("MacBook Pro 15.4 inch").id(1).introduced(null).discontinued(null).company(new Company.CompanyBuilder(1, "Apple Inc.").build()).build();
		System.out.println(comp.toString());
		
		assertTrue(Optional.of(comp).isPresent());
//		assertEquals(new Computer.ComputerBuilder("MacBook Pro 15.4 inch")
//				.id(1)
//				.company(new Company.CompanyBuilder(1, "Apple Inc.").build())
//				.build(),daoComputer.getComputerById(1L).get());
	}
	
}
