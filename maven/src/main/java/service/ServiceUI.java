package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.DateMapper;
import model.Company;
import model.Computer;
import persistence.DAOCompany;
import persistence.DAOComputer;
import servlet.MainServlet;

public class ServiceUI {
	private DAOComputer daoComputer;
	private DAOCompany daoCompany;
	private DateMapper dateMapper;
	
	final Logger logger = LoggerFactory.getLogger(MainServlet.class);
	
	public ServiceUI() {
		super();
		this.daoComputer = new DAOComputer();
		this.daoCompany = new DAOCompany();
		this.dateMapper = new DateMapper();
	}

	public boolean addComputer(HttpServletRequest request) {
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String compId = request.getParameter("companyId");
		Timestamp introDate;
		Timestamp disconDate;
		
		if(name == "") {
			logger.error("No name Given");
			return false;
		}
		else {
			if(introduced != "" && introduced.matches("\\d{4}-\\d{2}-\\d{2}")) {
				introDate = new Timestamp(dateMapper.getDate(introduced));
				if(discontinued != "" && discontinued.matches("\\d{4}-\\d{2}-\\d{2}")) {
					disconDate = new Timestamp(dateMapper.getDate(discontinued));
					
				}
				else {
					disconDate = null;
				}
			}
			else {
				introDate = null;
				disconDate = null;
			}
			
		}
		try {
			daoComputer.insertComputer(name, introDate, disconDate, compId);
			return true;
		} catch (SQLException e) {
			logger.error("Server problem");
			return false;
		}
	}

	public ArrayList<Company> getCompanies() {
		try {
			return daoCompany.getCompanies();
		} catch (SQLException e) {
			logger.error("Couldn't get Companies");
			return null;
		}
	}
	
	public ArrayList<Computer> getComputers(){
		DAOComputer daoComputer = new DAOComputer();
		try {
			return daoComputer.getComputers();
		} catch (SQLException e) {
			logger.error("Couldn't get computer list");
			return null;
		}
	}

	public Optional<Computer> getComputerById(String id_computer) {
		DAOComputer daoComputer = new DAOComputer();
		try {
			return daoComputer.getComputerById(Long.valueOf(id_computer));
		} catch (SQLException e) {
			logger.error("Couldn't get computer list");
			return null;
		}
	}

	public boolean editComputer(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

}
