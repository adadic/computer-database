package hr.excilys.persistence;

import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

public class DAOCompanyTest extends TestCase {

	@Test
	public void testDeleteCompany() throws SQLException {
		DAOCompany daoCompany = new DAOCompany();
		assertEquals(0, daoCompany.deleteCompany(100));
	}

}
