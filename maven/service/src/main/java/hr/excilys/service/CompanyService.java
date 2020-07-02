package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Company;
import persistence.DAOCompany;

@Service
public class CompanyService {

	private final DAOCompany daoCompany;

	@Autowired
	public CompanyService(DAOCompany daoCompany) {

		this.daoCompany = daoCompany;
	}

	public List<Company> getCompanies() {

			return daoCompany.getCompanies();
	}
}
