package test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import model.Company;
import persistence.DAOComputer;

public class MyTests extends TestCase{
	
	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFunction() throws SQLException {
		DAOComputer daoComputer = new DAOComputer();
		
		assertEquals(1,daoComputer.insertComputer("AZERTY", null, null, "1"));
	}
	
	@Test
	public void testMock() {
		Company company = Mockito.mock(Company.class);
		ArrayList<Company> list = new ArrayList<>();
		assertEquals(true,list.add(company));
		
	}
	
}
