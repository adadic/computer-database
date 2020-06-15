package hr.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOPagination;
import hr.excilys.mapper.PaginationMapper;
import hr.excilys.model.Computer;
import hr.excilys.model.Pagination;
import hr.excilys.persistence.DAOComputer;
import hr.excilys.validator.PageValidator;

@Service("dashboardService")
public class DashboardService {

	private final static int ASC = 1;
	private final static int DESC = 0;

	private DAOComputer daoComputer;

	public DashboardService() {

		super();
		this.daoComputer = new DAOComputer();
	}

	public ArrayList<Computer> getComputersRows(Pagination page) {

		DAOComputer daoComputer = new DAOComputer();

		if (page.getOrder() != null) {
			if (page.getDirection() == 1) {
				try {

					return daoComputer.getComputersSort(page.getPage(), page.getLines(), page.getSearch(),
							page.getOrder(), ASC);
				} catch (SQLException e) {

					return new ArrayList<Computer>();
				}
			} else {
				try {

					return daoComputer.getComputersSort(page.getPage(), page.getLines(), page.getSearch(),
							page.getOrder(), DESC);
				} catch (SQLException e) {

					return new ArrayList<Computer>();
				}
			}
		} else {
			try {

				return daoComputer.getComputersRows(page.getPage(), page.getLines(), page.getSearch());
			} catch (SQLException e) {

				return new ArrayList<Computer>();
			}
		}
	}

	public int getCountComputer(String search) {

		try {

			return daoComputer.countComputer(search);
		} catch (SQLException e) {

			return -1;
		}
	}

	public boolean deleteComputer(List<String> selected) throws NumberFormatException, SQLException {

		if (selected.isEmpty()) {

			return false;
		}

		selected.stream().forEach(e -> {
			try {
				daoComputer.deleteComputer(Long.valueOf(e));
			} catch (NumberFormatException nfe) {

				nfe.printStackTrace();
			} catch (SQLException sqle) {

				sqle.printStackTrace();
			}
		});

		return true;
	}

	public Pagination paginate(DTOPagination dtoPagination) {
		
		Pagination page = PaginationMapper.getPage(dtoPagination, getCountComputer(dtoPagination.getSearch()));
		PageValidator.checkPage(page);
		
		return page;
	}
}
