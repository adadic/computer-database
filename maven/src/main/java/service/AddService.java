package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.DTOComputer;
import mapper.ComputerMapper;
import mapper.DateMapper;
import model.Computer;
import persistence.DAOComputer;
import servlet.DashboardServlet;

public class AddService {

	private DAOComputer daoComputer;
	private DateMapper dateMapper;
	
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public AddService() {
		
		super();
		this.daoComputer = new DAOComputer();
		this.dateMapper = new DateMapper();
	}

	
	public boolean addComputer(DTOComputer dtoComputer) {
//		
//		Computer computer = ComputerMapper.fromDTO(dtoComputer);
//		if(computer.isPresent()) {
//			
//			
//			try {
//				daoComputer.insertComputer(computer);
//				
//				return true;
//			} catch (SQLException e) {
//				logger.error("Server problem");
//				
//				return false;
//			}
//		}
		return false;
	}
}
