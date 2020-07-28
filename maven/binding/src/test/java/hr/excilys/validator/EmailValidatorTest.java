package hr.excilys.validator;

import static org.junit.Assert.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import hr.excilys.config.BindingConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class EmailValidatorTest {

	@Autowired
	private EmailValidator emailValidator;
	private ConstraintValidatorContext context;

	@Test
	public void testIsValidOK() {
		
		assertEquals(true, emailValidator.isValid("ysabourin@excilys.com", context));
	}

	@Test
	public void testIsValidNotOK() {
		
		assertEquals(false, emailValidator.isValid("", context));
	}
}
