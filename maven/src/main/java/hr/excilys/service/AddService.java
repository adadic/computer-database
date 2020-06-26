package hr.excilys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOComputer;
import hr.excilys.mapper.ComputerMapper;
import hr.excilys.model.Computer;
import hr.excilys.persistence.DAOComputer;

@Service
public class AddService {

	@Autowired
	private DAOComputer daoComputer;
	@Autowired
	private ComputerMapper computerMapper;

	public boolean addComputer(DTOComputer dtoComputer) {

		Optional<Computer> computer = computerMapper.fromDTO(dtoComputer);
		if (computer.isPresent()) {

			return daoComputer.insertComputer(computer.get());
		}
		
		return false;
	}
}
