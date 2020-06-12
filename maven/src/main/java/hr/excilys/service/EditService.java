package hr.excilys.service;

import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.excilys.dto.DTOComputer;
import hr.excilys.mapper.ComputerMapper;
import hr.excilys.model.Computer;
import hr.excilys.persistence.DAOComputer;
import hr.excilys.servlet.DashboardServlet;

public class EditService {

	private DAOComputer daoComputer;

	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	public EditService() {

		super();
		this.daoComputer = new DAOComputer();
	}

	public Optional<Computer> getComputerById(String id_computer) {

		DAOComputer daoComputer = new DAOComputer();
		try {

			return daoComputer.getComputerById(Long.valueOf(id_computer));
		} catch (SQLException e) {
			logger.error("Couldn't get computer list");

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
				logger.error("Server problem");

				return false;
			}
		}
		return false;
	}
}
