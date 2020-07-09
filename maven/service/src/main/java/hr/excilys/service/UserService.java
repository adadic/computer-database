package hr.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.excilys.model.Role;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = daoUser.getUser(username);

		if (opt.isPresent()) {
			User user = opt.get();
			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName());
			grantList.add(authority);
			UserDetails userDetails = new User.UserBuilder(user.getUsername(), user.getPassword(),
					new Role.RoleBuilder(user.getRole()).build()).build();

			return userDetails;
		}
		
		throw new UsernameNotFoundException("No user by this username");
	}

	public boolean addUser(User user) {

		return daoUser.create(user);
	}
}
