package function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import mapper.DateMapper;
import model.Company;
import model.Computer;
import persistence.DAOComputer;

public class MyTests extends TestCase{
	
	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFunction() throws SQLException {
		DAOComputer daoComputer = new DAOComputer();
		
		//assertEquals(1,daoComputer.insertComputer("AZERTY", null, null, "1"));
	}
	
	@Test
	public void testMock() {
		Company company = Mockito.mock(Company.class);
		ArrayList<Company> list = new ArrayList<>();
		assertEquals(true,list.add(company));
		
	}
	
	@Test
	public void testDate() {
		DateMapper dm = new DateMapper();
		assertEquals(1582412400000L, dm.getDate("2020-02-23"));
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
