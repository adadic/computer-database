package hr.excilys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOComputer;
import hr.excilys.dto.mapper.ComputerDTOMapper;
import hr.excilys.model.Computer;
import hr.excilys.persistence.DAOComputer;

@Service
public class EditService {

	private DAOComputer daoComputer;
	private ComputerDTOMapper computerDTOMapper;
	
	@Autowired
	public EditService(DAOComputer daoComputer, ComputerDTOMapper computerDTOMapper) {

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
