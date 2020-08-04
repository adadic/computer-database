package hr.excilys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.mapper.CompanyDTOMapper;
import hr.excilys.model.Company;
import hr.excilys.persistence.DAOCompany;

@Service
public class CompanyService {

	private final DAOCompany daoCompany;
	private final CompanyDTOMapper companyDTOMapper;

	@Autowired
	public CompanyService(DAOCompany daoCompany, CompanyDTOMapper companyDTOMapper) {

		this.daoCompany = daoCompany;
		this.companyDTOMapper = companyDTOMapper;
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

	public boolean editCompany(DTOCompany dtoCompany) {
		
		Optional<Company> company = companyDTOMapper.fromDTO(dtoCompany);
		if (company.isPresent()) {
			
			return daoCompany.updateCompany(company.get());
		}
		
		return false;
	}

	public boolean addCompany(DTOCompany dtoCompany) {
		
		Optional<Company> company = companyDTOMapper.fromDTO(dtoCompany);
		if (company.isPresent()) {

			return daoCompany.insertCompany(company.get());
		}
		
		return false;
	}
}
