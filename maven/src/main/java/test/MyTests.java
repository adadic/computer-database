package test;

import java.sql.SQLException;

import junit.framework.TestCase;
import persistence.DAOComputer;

public class MyTests extends TestCase{
	
	public void testFunction() throws SQLException {
		DAOComputer daoComputer = new DAOComputer();
		
		assertEquals(1,daoComputer.insertComputer("AZERTY", null, null, "1"));
	}
	
}
