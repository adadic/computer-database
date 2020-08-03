package hr.excilys.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOUser;
import hr.excilys.dto.mapper.UserDTOMapper;
import hr.excilys.model.CustomUserDetails;
import hr.excilys.model.Role;
import hr.excilys.model.User;
import hr.excilys.persistence.DAOUser;

@Service
public class UserService implements UserDetailsService {

	private final DAOUser daoUser;
	private final UserDTOMapper userDTOMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(DAOUser daoUser, UserDTOMapper userDTOMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.daoUser = daoUser;
		this.userDTOMapper = userDTOMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = daoUser.getUser(username);
		if (opt.isPresent()) {
			User user = opt.get();
			CustomUserDetails userDetails = new CustomUserDetails.CustomBuilder(user).build();

			return userDetails;
		}

		throw new UsernameNotFoundException("No user by this username");
	}

	public List<User> getAllUsers() {
		List<User> allUsers = daoUser.getAllUsers();
//		Stream<CustomUserDetails> listUsersStream = allUsers.stream().map((user) -> {
//
//			return new CustomUserDetails.CustomBuilder(user).build();
//
//		});
//
//		return listUsersStream.collect(Collectors.toList());
		return allUsers;

	}

	public boolean addUser(DTOUser dtoUser) throws Exception {

		Optional<User> opt = daoUser.getUser(dtoUser.getUsername());
		if (opt.isPresent()) {

			throw new Exception("This username already exists");
		}
		dtoUser.setPassword(bCryptPasswordEncoder.encode(dtoUser.getPassword()));
		Optional<User> user = userDTOMapper.fromDTO(dtoUser);
		System.out.println(user);
		if (user.isPresent()) {

			return daoUser.create(user.get());
		}

		return false;
	}

	public List<Role> getRoles() {

		return daoUser.getRoles();
	}
}
