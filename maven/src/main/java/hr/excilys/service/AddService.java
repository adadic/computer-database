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

public class AddService {

	private DAOComputer daoComputer;

	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	public AddService() {

		super();
		this.daoComputer = new DAOComputer();
	}

	public boolean addComputer(DTOComputer dtoComputer) {

		Optional<Computer> computer = ComputerMapper.fromDTO(dtoComputer);
		if (computer.isPresent()) {
			try {
				daoComputer.insertComputer(computer.get());

				return true;
			} catch (SQLException e) {
				logger.error("Server problem");

				return false;
			}
		}
		return false;
	}
}
