package hr.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOPagination;
import hr.excilys.mapper.PaginationMapper;
import hr.excilys.model.Computer;
import hr.excilys.model.Pagination;
import hr.excilys.persistence.DAOComputer;
import hr.excilys.validator.PageValidator;

@Service
public class DashboardService {

	private final static int ASC = 1;
	private final static int DESC = 0;

	@Autowired
	private DAOComputer daoComputer;
	@Autowired
	private PaginationMapper paginationMapper;
	@Autowired
	private PageValidator pageValidator;

	public List<Computer> getComputersRows(Pagination page) {
		if (page.getOrder() != null && !page.getOrder().isEmpty()) {
			if (page.getDirection() == 1) {

					return daoComputer.getComputersSort(page.getPage(), page.getLines(), page.getSearch(),
							page.getOrder(), ASC);
			} else {

					return daoComputer.getComputersSort(page.getPage(), page.getLines(), page.getSearch(),
							page.getOrder(), DESC);
			}
		} else {

				return daoComputer.getComputersRows(page.getPage(), page.getLines(), page.getSearch());
		}
	}

	public int getCountComputer(String search) {

			return daoComputer.countComputer(search);
	}

	public boolean deleteComputer(List<String> selected) {

		if (selected.isEmpty()) {

			return false;
		}

		selected.stream().forEach(e -> {
			daoComputer.deleteComputer(Long.valueOf(e));
		});

		return true;
	}

	public Pagination paginate(DTOPagination dtoPagination) {

		Pagination page = paginationMapper.getPage(dtoPagination, getCountComputer(dtoPagination.getSearch()));
		pageValidator.checkPage(page);

		return page;
	}
}
