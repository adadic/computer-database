package hr.excilys.persistence;

import static org.hamcrest.CoreMatchers.is;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

public class DbUnit extends DBTestCase {

	public DbUnit(String name) {
		
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:~/test");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}

	protected IDataSet getDataSet() throws Exception {
		
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/companies.xml"));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		
		return DatabaseOperation.REFRESH;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		
		return DatabaseOperation.NONE;
	}
	
	@Test
    public void testById() {
 
        int companyId = 5;// get user id from database
        assertEquals(1, is(companyId));
    }
}