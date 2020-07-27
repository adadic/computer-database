package hr.excilys.validator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import hr.excilys.dto.DTOCompany;

public class CompanyValidatorTest {

	private CompanyValidator companyValidator = new CompanyValidator();
	
	@Test
	public void testCheckCompanyFieldsNull() {
		
		assertEquals(true, companyValidator.checkCompanyFields(null));		
	}
	
	@Test
	public void testCheckCompanyFieldsValid() {
		
		DTOCompany company1 = new DTOCompany();
		company1.setCompanyId("1");
		company1.setCompanyName("Apple");
		
		assertEquals(true, companyValidator.checkCompanyFields(company1));
	}
	
	@Test
	public void testCheckCompanyFieldsNoID() {
		
		DTOCompany company2 = new DTOCompany();
		company2.setCompanyId("");
		company2.setCompanyName("Apple");
		
		assertEquals(false, companyValidator.checkCompanyFields(company2));
	}
	
	@Test
	public void testCheckCompanyFieldsNoName() {
		
		DTOCompany company3 = new DTOCompany();
		company3.setCompanyId("1");
		company3.setCompanyName("");
		
		assertEquals(false, companyValidator.checkCompanyFields(company3));
	}
	
}
