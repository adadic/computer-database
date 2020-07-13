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

		long id = IDNULL;
		if (userValidator.checkUser(dtoUser)) {
			return Optional.of(new User.UserBuilder(dtoUser.getUsername(), dtoUser.getPassword(),
					new Role.RoleBuilder(Long.valueOf(dtoUser.getRole().getroleId()), dtoUser.getRole().getroleName())
							.build()).id(id).build());
		}

		return Optional.empty();
	}

}
