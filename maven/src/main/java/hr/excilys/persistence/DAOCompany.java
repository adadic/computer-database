package hr.excilys.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.excilys.mapper.CompanyMapper;
import hr.excilys.model.Company;

@Repository
public final class DAOCompany {

	private final static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private CompanyMapper companyMapper;

	public List<Company> getCompanies() throws SQLException {

		try {

			return namedParameterJdbcTemplate.query(EnumQuery.ALLCOMPANY.getQuery(), companyMapper);
		} catch (DataAccessException e) {
			LOGGER.error("Cannot get all Copanies, probleme in the Query");

			return new ArrayList<>();
		}
	}

	public Optional<Company> getCompanyById(long id) throws SQLException {

		try {
			MapSqlParameterSource parameterMap = new MapSqlParameterSource().addValue("id_company", id);
			List<Company> company = namedParameterJdbcTemplate.query(EnumQuery.IDCOMPANY.getQuery(), parameterMap,
					companyMapper);

			if (company != null) {
				LOGGER.info("Company with id = {} : Found", id);

				return Optional.of(company.get(0));
			}
			LOGGER.info("No company by id = {}", id);
		} catch (DataAccessException e) {
			LOGGER.info("Company with id = {} : NOT Found", id);

			return Optional.empty();
		}

		return Optional.empty();
	}

	@Transactional
	public int deleteCompany(long id) throws SQLException {

		try {
			MapSqlParameterSource parameterMap = new MapSqlParameterSource().addValue("id_company", id);
			namedParameterJdbcTemplate.update(EnumQuery.DELETECOMPUTERCOMPANY.getQuery(), parameterMap);
			namedParameterJdbcTemplate.update(EnumQuery.DELETECOMPANY.getQuery(), parameterMap);
			LOGGER.info("Computer with id_company : {} DELETED", id);

			return 1;
		} catch (DataAccessException e) {
			LOGGER.error("Probleme in query with id_company : {}", id);

			return 0;
		}
	}
}
