package hr.excilys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.excilys.model.CustomUserDetails;
import hr.excilys.model.User;
import hr.excilys.persistence.DAOUser;

@Service
public class UserService implements UserDetailsService {

	private final DAOUser daoUser;

	@Autowired
	public UserService(DAOUser daoUser) {

		this.daoUser = daoUser;
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

	public boolean addUser(User user) {

		return daoUser.create(user);
	}
}
