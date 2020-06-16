package hr.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.excilys.mapper.CompanyMapper;
import hr.excilys.model.Company;

public class DAOCompany {

	private final static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	public ArrayList<Company> getCompanies() throws SQLException {

		ArrayList<Company> companies = new ArrayList<>();

		try (MysqlConnect db = MysqlConnect.getDbCon()) {

			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.ALLCOMPANY.getQuery());
			ResultSet requestCompanies = db.query(preparedStatement);

			while (requestCompanies.next()) {
				companies.add(CompanyMapper.getCompany(requestCompanies));
			}
			LOGGER.info("Companies Fetched");
		} catch (SQLException e) {
			LOGGER.error("Cannot get all Copanies, probleme with the database");

		}

		return companies;
	}

	public Optional<Company> getCompanyById(long id) throws SQLException {

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.IDCOMPANY.getQuery());
			preparedStatement.setLong(1, id);
			ResultSet company = db.query(preparedStatement);

			if (company.next()) {
				LOGGER.info("Company(id : {}) fetched", id);

				return Optional.of(new Company(company.getLong("id"), company.getString("name")));
			}
			LOGGER.info("No company by id = {}", id);
		} catch (SQLException e) {
			LOGGER.error("Cannot get Company by id = {}", id);

		}

		return Optional.empty();
	}

	public int deleteCompany(long id) throws SQLException {

		try (MysqlConnect db = MysqlConnect.getDbCon()) {

			Connection conn = db.getConn();
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement = conn.prepareStatement(EnumQuery.DELETECOMPUTERCOMPANY.getQuery());
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			preparedStatement = conn.prepareStatement(EnumQuery.DELETECOMPANY.getQuery());
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			LOGGER.info("Computer Sort Done");

			return 1;
		} catch (SQLException e) {
			LOGGER.error("Probleme in query with id : {}", id);

			return 0;
		}
	}
}
