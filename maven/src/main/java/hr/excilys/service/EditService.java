package hr.excilys.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOComputer;
import hr.excilys.mapper.ComputerMapper;
import hr.excilys.model.Computer;
import hr.excilys.persistence.DAOComputer;

@Service
public class EditService {

	@Autowired
	private DAOComputer daoComputer;
	@Autowired
	private ComputerMapper computerMapper;

	public Optional<Computer> getComputerById(String id_computer) {

		try {

			return daoComputer.getComputerById(Long.valueOf(id_computer));
		} catch (SQLException e) {

			return Optional.empty();
		}
	}

	public boolean editComputer(DTOComputer dtoComputer) {

		Optional<Computer> computer = computerMapper.fromDTO(dtoComputer);
		if (computer.isPresent()) {
			try {
				daoComputer.updateComputer(computer.get());

				return true;
			} catch (SQLException e) {

				return false;
			}
		}
		return false;
	}
}
