package hr.excilys.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.excilys.persistence.mapper.CompanyRowMapper;
import hr.excilys.persistence.model.Company;

@Repository
public class DAOCompany {

	private final static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final CompanyRowMapper companyRowMapper;
	private final SessionFactory sessionFactory;

	@Autowired
	public DAOCompany(NamedParameterJdbcTemplate namedParameterJdbcTemplate, CompanyRowMapper companyRowMapper, SessionFactory sessionFactory) {

		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.companyRowMapper = companyRowMapper;
		this.sessionFactory = sessionFactory;
	}

	public List<Company> getCompanies() {

		try {

			return namedParameterJdbcTemplate.query(EnumQuery.ALLCOMPANY.getQuery(), companyRowMapper);
		} catch (DataAccessException dae) {
			LOGGER.error("Cannot get all Copanies, probleme in the Query");

			return new ArrayList<>();
		}
	}

	public Optional<Company> getCompanyById(long id) {

		try {
			MapSqlParameterSource parameterMap = new MapSqlParameterSource().addValue("id_company", id);
			List<Company> company = namedParameterJdbcTemplate.query(EnumQuery.IDCOMPANY.getQuery(), parameterMap,
					companyRowMapper);

			if (company != null) {
				LOGGER.info("Company with id = {} : Found", id);

				return Optional.of(company.get(0));
			}
			LOGGER.info("No company by id = {}", id);
		} catch (DataAccessException dae) {
			LOGGER.info("Company with id = {} : NOT Found", id);

			return Optional.empty();
		}

		return Optional.empty();
	}

	@Transactional
	public int deleteCompany(long id) {

		try {
			MapSqlParameterSource parameterMap = new MapSqlParameterSource().addValue("id_company", id);
			namedParameterJdbcTemplate.update(EnumQuery.DELETECOMPUTERCOMPANY.getQuery(), parameterMap);
			namedParameterJdbcTemplate.update(EnumQuery.DELETECOMPANY.getQuery(), parameterMap);
			LOGGER.info("Computer with id_company : {} DELETED", id);

			return 1;
		} catch (DataAccessException dae) {
			LOGGER.error("Probleme in query with id_company : {}", id);

			return 0;
		}
	}
}
