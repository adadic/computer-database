package hr.excilys.dto.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOUser;
import hr.excilys.model.Role;
import hr.excilys.model.User;
import hr.excilys.validator.UserValidator;

@Component
public class UserDTOMapper {

	private static final long IDNULL = 0L;
	private final UserValidator userValidator;

	@Autowired
	public UserDTOMapper(UserValidator userValidator) {

		this.userValidator = userValidator;
	}

	public Optional<User> fromDTO(DTOUser dtoUser) {

		@SuppressWarnings("unused")
		long id = IDNULL;
		if (userValidator.checkUser(dtoUser)) {
			Role role = new Role.RoleBuilder().setRoleName(dtoUser.getRole().getroleName()).build();
			
			User user  = new User.UserBuilder()
					.setUsername(dtoUser.getUsername())
					.setPassword(dtoUser.getPassword())
					.setRole(role).build();
			
			return Optional.of(user);
		}

		return Optional.empty();
	}

}
