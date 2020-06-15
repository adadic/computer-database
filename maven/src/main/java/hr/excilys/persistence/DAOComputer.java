package hr.excilys.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Optional;

import hr.excilys.mapper.ComputerMapper;
import hr.excilys.model.Computer;

public class DAOComputer {

	private static final int ASC = 1;

	public ArrayList<Computer> getComputers() throws SQLException {

		ArrayList<Computer> computers = new ArrayList<>();

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.ALLCOMPUTER.getQuery());
			ResultSet requestComputers = db.query(preparedStatement);

			while (requestComputers.next()) {
				computers.add(ComputerMapper.getComputer(requestComputers));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computers;
	}

	public ArrayList<Computer> getComputersRows(int page, int lines, String search) throws SQLException {

		ArrayList<Computer> computers = new ArrayList<>();

		search = checkSearch(search);

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = setGetRows(page, lines, search, db);
			ResultSet requestComputers = db.query(preparedStatement);

			while (requestComputers.next()) {
				computers.add(ComputerMapper.getComputer(requestComputers));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computers;
	}

	private PreparedStatement setGetRows(int page, int lines, String search, MysqlConnect db) throws SQLException {

		PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.PAGECOMPUTER.getQuery());
		preparedStatement.setString(1, search);
		preparedStatement.setLong(2, lines);
		preparedStatement.setLong(3, lines * (page - 1));

		return preparedStatement;
	}

	public Optional<Computer> getComputerById(long id) throws SQLException {

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.IDCOMPUTER.getQuery());
			preparedStatement.setLong(1, id);
			ResultSet requestComputer = db.query(preparedStatement);

			if (requestComputer.next()) {

				return Optional.of(ComputerMapper.getComputer(requestComputer));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public int insertComputer(Computer computer) throws SQLException {

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = setInsert(computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued(), computer.getCompany().getId(), db);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	private PreparedStatement setInsert(String name, Timestamp introduced, Timestamp discontinued, long company_id,
			MysqlConnect db) throws SQLException {

		PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.INSERTCOMPUTER.getQuery());
		preparedStatement.setString(1, name);
		preparedStatement.setTimestamp(2, introduced);
		preparedStatement.setTimestamp(3, discontinued);

		if (company_id == 0) {
			preparedStatement.setNString(4, null);
		} else {
			preparedStatement.setLong(4, company_id);
		}

		return preparedStatement;
	}

	public int updateComputer(Computer computer) throws SQLException {

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = setUpdate(computer.getId(), computer.getName(),
					computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), db);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	private PreparedStatement setUpdate(long id, String name, Timestamp introduced, Timestamp discontinued,
			long company_id, MysqlConnect db) throws SQLException {

		PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.UPDATECOMPUTER.getQuery());
		preparedStatement.setString(1, name);
		preparedStatement.setTimestamp(2, introduced);
		preparedStatement.setTimestamp(3, discontinued);

		if (company_id < 1) {

			preparedStatement.setNull(4, Types.BIGINT);
		} else {
			preparedStatement.setLong(4, company_id);
		}

		preparedStatement.setLong(5, id);

		return preparedStatement;
	}

	public int deleteComputer(long id) throws SQLException {

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.DELETECOMPUTER.getQuery());
			preparedStatement.setLong(1, id);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	public int countComputer(String search) throws SQLException {

		search = checkSearch(search);

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = db.getConn().prepareStatement(EnumQuery.COUNTCOMPUTER.getQuery());
			preparedStatement.setString(1, search);
			ResultSet resultSet = db.query(preparedStatement);

			if (resultSet.next()) {

				return resultSet.getInt("number");
			}

			return -1;
		} catch (SQLException e) {
			e.printStackTrace();

			return -1;
		}
	}

	public ArrayList<Computer> getComputersSort(int page, int lines, String search, String order, int direct)
			throws SQLException {
		ArrayList<Computer> computers = new ArrayList<>();

		search = checkSearch(search);

		try (MysqlConnect db = MysqlConnect.getDbCon()) {
			PreparedStatement preparedStatement = setSortRow(page, lines, search, order, direct, db);
			ResultSet requestComputers = db.query(preparedStatement);

			while (requestComputers.next()) {
				computers.add(ComputerMapper.getComputer(requestComputers));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computers;
	}

	private PreparedStatement setSortRow(int page, int lines, String search, String order, int direct, MysqlConnect db)
			throws SQLException {

		PreparedStatement preparedStatement;

		if (order.equals("computer")) {
			if (direct == ASC) {
				preparedStatement = db.getConn().prepareStatement(EnumQuery.SORTPAGECOMPUTERASC.getQuery());
			} else {
				preparedStatement = db.getConn().prepareStatement(EnumQuery.SORTPAGECOMPUTERDESC.getQuery());
			}
		} else {
			if (direct == ASC) {
				preparedStatement = db.getConn().prepareStatement(EnumQuery.SORTPAGECOMPANYASC.getQuery());
			} else {
				preparedStatement = db.getConn().prepareStatement(EnumQuery.SORTPAGECOMPANYDESC.getQuery());
			}
		}

		preparedStatement.setString(1, search);
		preparedStatement.setLong(2, lines);
		preparedStatement.setLong(3, lines * (page - 1));

		return preparedStatement;
	}

	private String checkSearch(String search) {

		if (search == null) {
			search = "%";
		} else {
			search = "%" + search + "%";
		}

		return search;
	}
}
