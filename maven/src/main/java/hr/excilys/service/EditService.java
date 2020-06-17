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

	public EditService() {

		super();
		this.daoComputer = new DAOComputer();
	}

	public Optional<Computer> getComputerById(String id_computer) {

		DAOComputer daoComputer = new DAOComputer();
		try {

			return daoComputer.getComputerById(Long.valueOf(id_computer));
		} catch (SQLException e) {

			return Optional.empty();
		}
	}

	public boolean editComputer(DTOComputer dtoComputer) {

		Optional<Computer> computer = ComputerMapper.fromDTO(dtoComputer);
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
