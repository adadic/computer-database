package hr.excilys.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DAOCompanyTest {

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
