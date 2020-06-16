package hr.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import hr.excilys.model.Company;
import hr.excilys.persistence.DAOCompany;

@Service("communService")
public class CommunService {

	private DAOCompany daoCompany;

	public CommunService() {

		super();
		this.daoCompany = new DAOCompany();
	}

	public ArrayList<Company> getCompanies() {

		try {

			return daoCompany.getCompanies();
		} catch (SQLException e) {

			return new ArrayList<Company>();
		}
	}
}
