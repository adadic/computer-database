package service;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.DateMapper;
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
					if(disconDate.getTime() < introDate.getTime()) {
						
						return false;
					}
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
}
