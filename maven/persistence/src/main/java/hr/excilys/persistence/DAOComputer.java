package hr.excilys.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.excilys.model.Company;
import hr.excilys.model.Computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class DAOComputer {

	private static final int ASC = 1;
	private final static Logger LOGGER = LoggerFactory.getLogger(DAOComputer.class);
	private final SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public DAOComputer(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public List<Computer> getComputers(){
		try {
			session = this.sessionFactory.getCurrentSession();
			TypedQuery<Computer> query = session.createQuery(EnumQuery.GETCOMPUTERS.getQuery(), Computer.class);
			return query.getResultList();
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");
		} catch (DataAccessException dae) {
			LOGGER.error("Cannot get Computers at {} page and with {} lines, probleme in the Query maybe search -> {}");
		}
		return new ArrayList<>();
	}
	
	public List<Computer> getComputersRows(int page, int lines, String search) {

		search = prepareSearch(search);
		
		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Computer> query = session.createQuery(EnumQuery.ALLCOMPUTER.getQuery(), Computer.class)
					.setParameter("search", search);
			query.setFirstResult(lines * (page - 1));
			query.setMaxResults(lines);

			return query.getResultList();
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return new ArrayList<>();
		} catch (DataAccessException dae) {
			LOGGER.error("Cannot get Computers at {} page and with {} lines, problem in the Query maybe search -> {}",
					page, lines, search);

			return new ArrayList<>();
		}
	}

	public Optional<Computer> getComputerById(long id) {

		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Computer> query = session.createQuery(EnumQuery.IDCOMPUTER.getQuery(), Computer.class)
					.setParameter("id_computer", id);
			List<Computer> computer = query.getResultList();

			if (!computer.isEmpty()) {
				LOGGER.info("Computer with id = {} : Found", id);

				return Optional.of(computer.get(0));
			}
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return Optional.empty();
		} catch (DataAccessException dae) {
			LOGGER.error("Computer with id = {} : Problem in Query", id);
		}
		LOGGER.info("Computer with id = {} : NOT Found", id);

		return Optional.empty();
	}

	public boolean insertComputer(Computer computer) {
		System.out.println("user in hibernate dao computer "+computer.toString());

		try {
			session = sessionFactory.getCurrentSession();
			if(computer.getCompany().getId() == 0) {
				computer = new Computer.ComputerBuilder(computer.getName())
						.id(computer.getId())
						.introduced(computer.getIntroduced())
						.discontinued(computer.getDiscontinued())
						.company(null)
						.build();
			}
			System.out.println("user in hibernate dao computer 2"+computer.toString());
			session.save(computer);

			return true;
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return false;
		} catch (DataAccessException dae) {
			LOGGER.error("Computer NOT added, problem in query : Check fields");

			return false;
		}
	}

	public boolean updateComputer(Computer computer) {
		
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(EnumQuery.UPDATECOMPUTER.getQuery())
					.setParameter("name", computer.getName())
					.setParameter("introduced", computer.getIntroduced())
					.setParameter("discontinued", computer.getDiscontinued())
					.setParameter("id_company", computer.getCompany().getId())
					.setParameter("id_computer", computer.getId());
			query.executeUpdate();
			return true;
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return false;
		} catch (DataAccessException dae) {
			LOGGER.error("Computer NOT updated, problem in query : Check fields");

			return false;
		}
	}

	public boolean deleteComputer(long id) {

		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(EnumQuery.DELETECOMPUTER.getQuery())
					.setParameter("id_computer", id);
			query.executeUpdate();

			return true;
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return false;
		} catch (DataAccessException dae) {
			LOGGER.error("Computer NOT deleted, problem in query");

			return false;
		}
	}

	public int countComputer(String search) {

		search = prepareSearch(search);
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(EnumQuery.ALLCOMPUTER.getQuery()).setParameter("search", search);

			return query.getResultList().size();
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return -1;
		} catch (DataAccessException dae) {
			LOGGER.info("ResultSet Empty");

			return -1;
		}
	}

	public List<Computer> getComputersSort(int page, int lines, String search, String order, int direct)
			throws DataAccessException {

		search = prepareSearch(search);
		try {
			session = sessionFactory.getCurrentSession();
			String queryString = getOrderQuery(order, direct);
			TypedQuery<Computer> query = session.createQuery(queryString, Computer.class).setParameter("search",
					search);
			query.setFirstResult(lines * (page - 1));
			query.setMaxResults(lines);

			return getComputersWithNoNullCompany(query.getResultList());
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return new ArrayList<>();
		} catch (DataAccessException dae) {
			LOGGER.error(
					"Problem in query, check fields : page = {}, lines = {}, search = {}, order = {}, direct = {}",
					page, lines, search, order, direct);

			return new ArrayList<>();
		}
	}
	
	private List<Computer> getComputersWithNoNullCompany(List<Computer> listComputer){
		
		Stream<Computer> listComputerStream = listComputer.stream().map( (computer) -> {
			if(computer.getCompany()==null) {
				 return new Computer.ComputerBuilder(computer.getName())
						.id(computer.getId())
						.introduced(computer.getIntroduced())
						.discontinued(computer.getDiscontinued())
						.company(new Company.CompanyBuilder(0, null).build())
						.build();
			}
			return computer;
		});
		
		return listComputerStream.collect(Collectors.toList());
		
	}

	private String getOrderQuery(String order, int direct) {

		if (order.equals("computer")) {
			if (direct == ASC) {
				return EnumQuery.SORTPAGECOMPUTERASC.getQuery();
			} else {
				return EnumQuery.SORTPAGECOMPUTERDESC.getQuery();
			}
		} else {
			if (direct == ASC) {
				return EnumQuery.SORTPAGECOMPANYASC.getQuery();
			} else {
				return EnumQuery.SORTPAGECOMPANYDESC.getQuery();
			}
		}
	}

	private String prepareSearch(String search) {

		if (search == null) {
			search = "%";
		} else if (search.contains("%")) {
			search = search.replace("%", "\\%");
		} else {
			search = "%" + search + "%";
		}

		return search;
	}
}