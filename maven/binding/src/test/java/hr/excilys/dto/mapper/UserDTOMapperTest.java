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

	@Before
	public void Setup() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFromDTOInvalid() {

		UserValidator userValidator = Mockito.mock(UserValidator.class);
		DTOUser dtoUser = Mockito.mock(DTOUser.class);
		Mockito.when(userValidator.checkUser(dtoUser)).thenReturn(false);
		
		assertEquals(Optional.empty(), userDTOMapper.fromDTO(dtoUser));
	}

	@Test
	public void testFromDTOValid() {

		UserValidator userValidator = Mockito.mock(UserValidator.class);
		DTOUser dtoUser = Mockito.mock(DTOUser.class);
		DTORole dtoRole = Mockito.mock(DTORole.class);
		Mockito.when(dtoUser.getRole()).thenReturn(dtoRole);
		Mockito.when(dtoRole.getroleName()).thenReturn("ADMIN");
		Mockito.when(dtoUser.getUsername()).thenReturn("username");
		Mockito.when(dtoUser.getPassword()).thenReturn("password");
		Mockito.when(userValidator.checkUser(dtoUser)).thenReturn(true);
		
		Role role = new Role.RoleBuilder().setRoleName(dtoUser.getRole().getroleName()).build();
		
		User user  = new User.UserBuilder()
				.setUsername(dtoUser.getUsername())
				.setPassword(dtoUser.getPassword())
				.setRole(role).build();
		
		assertEquals(user, userDTOMapper.fromDTO(dtoUser).get());
	}
}
