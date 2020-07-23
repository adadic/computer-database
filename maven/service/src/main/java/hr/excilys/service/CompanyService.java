package hr.excilys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.model.Company;
import hr.excilys.persistence.DAOCompany;

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
	
	public Optional<Company> getCompany(String id) {
		
		return daoCompany.getCompanyById(Long.valueOf(id));
	}
	
	public boolean deleteCompany(String id) {
		return daoCompany.deleteCompany(Long.valueOf(id));
	}
}
