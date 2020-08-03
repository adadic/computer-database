package hr.excilys.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.assertEquals;

import hr.excilys.config.BindingConfig;
import hr.excilys.dto.DTOCompany;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class CompanyValidatorTest {
	
	@Autowired
	private CompanyValidator companyValidator;

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
