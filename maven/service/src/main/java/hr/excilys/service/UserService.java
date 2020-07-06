package hr.excilys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOUser;
import hr.excilys.persistence.DAOUser;

@Service
public class UserService {

	private final DAOUser daoUser;

	@Autowired
	public UserService(DAOUser daoUser) {

		this.daoUser = daoUser;
	}

	public boolean searchUser(DTOUser dtoUser) {

		return daoUser.getUser(dtoUser.getEmailORname(), dtoUser.getPassword());
	}
}
