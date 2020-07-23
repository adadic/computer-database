package hr.excilys.service;

import java.util.List;
import java.util.Optional;

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
	

	@Autowired
	public UserService(DAOUser daoUser, UserDTOMapper userDTOMapper) {

		this.daoUser = daoUser;
		this.userDTOMapper = userDTOMapper;
		
	}
	
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
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

	public boolean addUser(DTOUser dtoUser) {
		
		dtoUser.setPassword(bCryptPasswordEncoder.encode(dtoUser.getPassword()));
		System.out.println(dtoUser.toString());
		Optional<User> user = userDTOMapper.fromDTO(dtoUser);
		System.out.println(user);
		
		if(user.isPresent()) {
			return daoUser.create(user.get());
		}

		return false;
	}
	

	public List<Role> getRoles() {

		return daoUser.getRoles();
	}
}
