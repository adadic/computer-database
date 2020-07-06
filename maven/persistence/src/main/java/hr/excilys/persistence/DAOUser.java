package hr.excilys.persistence;

import java.util.List;

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

	public boolean getUser(String emailORusername, String password) {

		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<User> query = session.createQuery(EnumQuery.GETUSER.getQuery(), User.class)
					.setParameter("username", emailORusername).setParameter("email", emailORusername)
					.setParameter("password", password);
			List<User> user = query.getResultList();

			if (!user.isEmpty()) {
				LOGGER.info("User with email or name = {} : Found", emailORusername);

				return true;
			}
		} catch (HibernateException hex) {
			LOGGER.error("Cannot get the session");

			return false;
		} catch (DataAccessException dae) {
			LOGGER.error("User with email or name = {} : Probleme in Query", emailORusername);
		}
		LOGGER.info("User with email or name = {} : NOT Found", emailORusername);

		return false;
	}
}
