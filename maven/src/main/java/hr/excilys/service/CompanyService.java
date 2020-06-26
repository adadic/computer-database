package hr.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.model.Company;
import hr.excilys.persistence.DAOCompany;

@Service
public class CompanyService {

	private DAOCompany daoCompany;

	@Autowired
	public CompanyService(DAOCompany daoCompany) {

		this.daoCompany = daoCompany;
	}

	public List<Company> getCompanies() {

			return daoCompany.getCompanies();
	}
}
