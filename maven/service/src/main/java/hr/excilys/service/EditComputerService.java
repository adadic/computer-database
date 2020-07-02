package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOComputer;
import hr.excilys.dto.mapper.ComputerDTOMapper;
import hr.excilys.model.Computer;
import persistence.DAOComputer;

@Service
public class EditComputerService {

	private final DAOComputer daoComputer;
	private final ComputerDTOMapper computerDTOMapper;

	@Autowired
	public EditComputerService(DAOComputer daoComputer, ComputerDTOMapper computerDTOMapper) {

		this.daoComputer = daoComputer;
		this.computerDTOMapper = computerDTOMapper;
	}

	public Optional<Computer> getComputerById(String id_computer) {

		return daoComputer.getComputerById(Long.valueOf(id_computer));
	}

	public boolean editComputer(DTOComputer dtoComputer) {

		Optional<Computer> computer = computerDTOMapper.fromDTO(dtoComputer);
		if (computer.isPresent()) {

			return daoComputer.updateComputer(computer.get());
		}
		
		return false;
	}
}
