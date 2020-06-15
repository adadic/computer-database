package hr.excilys.persistence;

import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

public class DAOCompanyTest extends TestCase {
	
	@Test
	public void testGetCompanies() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompanyById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCompany() throws SQLException {
		DAOCompany daoCompany = new DAOCompany();
		assertEquals(1, daoCompany.deleteCompany(100));
	}

}
