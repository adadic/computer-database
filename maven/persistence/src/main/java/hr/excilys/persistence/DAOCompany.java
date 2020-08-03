package hr.excilys.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.excilys.model.Company;

@Repository
@Transactional
public class DAOCompany {

	private final static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);
	private final SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public DAOCompany(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public List<Company> getCompanies() {

		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Company> query = session.createQuery(EnumQuery.ALLCOMPANY.getQuery(), Company.class);

			return query.getResultList();
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return new ArrayList<>();
		} catch (DataAccessException dae) {
			LOGGER.error("Cannot get all Copanies, probleme in the Query");

			return new ArrayList<>();
		}
	}

	public Optional<Company> getCompanyById(long id) {

		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Company> query = session.createQuery(EnumQuery.IDCOMPANY.getQuery(), Company.class)
					.setParameter("id_company", id);
			List<Company> company = query.getResultList();

			if (company != null) {
				LOGGER.info("Company with id = {} : Found", id);

				return Optional.of(company.get(0));
			}
			LOGGER.info("No company by id = {}", id);
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return Optional.empty();
		} catch (DataAccessException dae) {
			LOGGER.info("Company with id = {} : NOT Found", id);

			return Optional.empty();
		}

		return Optional.empty();
	}

	public boolean deleteCompany(long id) {

		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Company> computerQuery = session
					.createQuery(EnumQuery.DELETECOMPUTERCOMPANY.getQuery(), Company.class)
					.setParameter("id_company", id);
			TypedQuery<Company> companyQuery = session.createQuery(EnumQuery.DELETECOMPANY.getQuery(), Company.class)
					.setParameter("id_company", id);

			computerQuery.executeUpdate();
			companyQuery.executeUpdate();
			LOGGER.info("Computer with id_company : {} DELETED", id);

			return true;
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return false;
		} catch (DataAccessException dae) {
			LOGGER.error("Problem in query with id_company : {}", id);

			return false;
		}
	}

	public boolean updateCompany(Company company) {
			
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(EnumQuery.UPDATECOMPANY.getQuery())
					.setParameter("name", company.getName())
					.setParameter("id", company.getId());
			query.executeUpdate();
			
			return true;
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return false;
		} catch (DataAccessException dae) {
			LOGGER.error("Company NOT updated, problem in query : Check fields");

			return false;
		}
	}
}
