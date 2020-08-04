package hr.excilys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOComputer;
import hr.excilys.dto.mapper.ComputerDTOMapper;
import hr.excilys.model.Computer;
import hr.excilys.persistence.DAOComputer;

@Service
public class AddComputerService {

	private final DAOComputer daoComputer;
	private final ComputerDTOMapper computerDTOMapper;

	@Autowired
	public AddComputerService(DAOComputer daoComputer, ComputerDTOMapper computerDTOMapper) {

		this.daoComputer = daoComputer;
		this.computerDTOMapper = computerDTOMapper;
	}

	public boolean addComputer(DTOComputer dtoComputerAdd) {
		
		Optional<Computer> computer = computerDTOMapper.fromDTO(dtoComputerAdd);
		if (computer.isPresent()) {

			return daoComputer.insertComputer(computer.get());
		}
		
		return false;
	}
}
