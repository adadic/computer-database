package hr.excilys.dto.mapper;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import hr.excilys.config.BindingConfig;
import hr.excilys.dto.DTORole;
import hr.excilys.dto.DTOUser;
import hr.excilys.model.Role;
import hr.excilys.model.User;
import hr.excilys.validator.UserValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BindingConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class UserDTOMapperTest {

	@Autowired
	UserDTOMapper userDTOMapper;
	private UserValidator userValidator;

	@Before
	public void Setup() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFromDTOInvalid() {

		userValidator = Mockito.mock(UserValidator.class);
		DTOUser dtoUser = new DTOUser("", "");
		Mockito.when(userValidator.checkUser(dtoUser)).thenReturn(false);

		assertEquals(Optional.empty(), userDTOMapper.fromDTO(dtoUser));
	}

	@Test
	public void testFromDTOValid() {

		userValidator = Mockito.mock(UserValidator.class);
		DTOUser dtoUser = new DTOUser("username", "password");
		DTORole dtoRole = new DTORole("", "ADMIN");
		dtoUser.setDtoRole(dtoRole);
		Mockito.when(userValidator.checkUser(dtoUser)).thenReturn(true);

		Role role = new Role.RoleBuilder().setRoleName(dtoUser.getRole().getroleName()).build();

		User user = new User.UserBuilder().setUsername(dtoUser.getUsername()).setPassword(dtoUser.getPassword())
				.setRole(role).build();

		assertEquals(user, userDTOMapper.fromDTO(dtoUser).get());
	}
}
