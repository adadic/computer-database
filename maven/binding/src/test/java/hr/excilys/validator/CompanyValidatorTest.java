package hr.excilys.validator;

import org.junit.Test;

import hr.excilys.dto.DTOCompany;

public class CompanyValidatorTest {

	private CompanyValidator companyValidator = new CompanyValidator();
	
	@Test
	public void checkCompanyFieldsTest() {
		
		DTOCompany company1 = new DTOCompany();
		company1.setCompanyId("1");
		company1.setCompanyName("Apple");
		
		DTOCompany company2 = new DTOCompany();
		company2.setCompanyId("");
		company2.setCompanyName("Apple");
		
		DTOCompany company3 = new DTOCompany();
		company3.setCompanyId("1");
		company3.setCompanyName("");
		
		assert(companyValidator.checkCompanyFields(null));
		assert(companyValidator.checkCompanyFields(company1));
		assert(!companyValidator.checkCompanyFields(company2));
		assert(!companyValidator.checkCompanyFields(company3));
		
	}
	
}
