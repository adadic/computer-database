package service;

import java.sql.SQLException;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
import persistence.DAOCompany;
import servlet.DashboardServlet;

public class CommunService {
	private DAOCompany daoCompany;
	
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public CommunService() {
		super();
		this.daoCompany = new DAOCompany();
	}

	public ArrayList<Company> getCompanies() {
		try {
			return daoCompany.getCompanies();
		} catch (SQLException e) {
			logger.error("Couldn't get Companies");
			return null;
		}
	}



}
