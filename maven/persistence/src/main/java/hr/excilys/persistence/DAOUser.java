package hr.excilys.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import hr.excilys.model.Role;
import hr.excilys.model.User;

@Repository
@Transactional
public class DAOUser {

	private final static Logger LOGGER = LoggerFactory.getLogger(DAOUser.class);
	private final SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public DAOUser(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public Optional<User> getUser(String username) {

		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<User> query = session.createQuery(EnumQuery.GETUSER.getQuery(), User.class)
					.setParameter("username", username);
			List<User> user = query.getResultList();

			if (!user.isEmpty()) {
				LOGGER.info("User with email or name = {} : Found", username);

				return Optional.of(user.get(0));
			}
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return Optional.empty();
		} catch (DataAccessException dae) {
			LOGGER.error("User with email or name = {} : Probleme in Query", username);
		}
		LOGGER.info("User with email or name = {} : NOT Found", username);

		return Optional.empty();
	}

	public boolean create(User user) {

		try {
			session = sessionFactory.getCurrentSession();

			session.save(user);

			return true;
		} catch (HibernateException hex) {
			System.out.println("user in hibernate "+user.toString());
			LOGGER.error("Cannot get the session");
			System.out.println("Cannot get the session");

			return false;
		} catch (DataAccessException dae) {
			LOGGER.error("User NOT added, probleme in query : Check fields");
			System.out.println("User NOT added, probleme in query : Check fields");
			

			return false;
		}
	}

	public List<Role> getRoles() {

		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Role> query = session.createQuery(EnumQuery.ALLROLE.getQuery(), Role.class);

			return query.getResultList();
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return new ArrayList<>();
		} catch (DataAccessException dae) {
			LOGGER.error("Cannot get all Copanies, probleme in the Query");

			return new ArrayList<>();
		}
	}
}
