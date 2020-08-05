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

	private final UserValidator userValidator;

	@Autowired
	public UserDTOMapper(UserValidator userValidator) {

		this.userValidator = userValidator;
	}

	public Optional<User> fromDTO(DTOUser dtoUser) {
		if (userValidator.checkUser(dtoUser)) {
			Role role = new Role.RoleBuilder().setRoleName(dtoUser.getRole().getroleName()).setroleId(Long.parseLong(dtoUser.getRole().getroleId())).build();

			User user = new User.UserBuilder().setUsername(dtoUser.getUsername()).setPassword(dtoUser.getPassword())
					.setEmail(dtoUser.getEmail()).setRole(role).build();
			return Optional.of(user);
		}

		return Optional.empty();
	}

}
