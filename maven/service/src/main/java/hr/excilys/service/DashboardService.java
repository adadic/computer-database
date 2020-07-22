package hr.excilys.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.excilys.dto.DTOPagination;
import hr.excilys.dto.mapper.PaginationDTOMapper;
import hr.excilys.model.Pagination;
import hr.excilys.model.Computer;
import hr.excilys.persistence.DAOComputer;
import hr.excilys.validator.PageValidator;

@Service
public class DashboardService {

	private final static int ASC = 1;
	private final static int DESC = 0;

	private final DAOComputer daoComputer;
	private final PaginationDTOMapper paginationDTOMapper;
	private final PageValidator pageValidator;

	@Autowired
	public DashboardService(DAOComputer daoComputer, PaginationDTOMapper paginationDTOMapper,
			PageValidator pageValidator) {

		this.daoComputer = daoComputer;
		this.paginationDTOMapper = paginationDTOMapper;
		this.pageValidator = pageValidator;
	}

	public List<Computer> getComputersRows(Pagination page) {
		if (StringUtils.isNotEmpty(page.getOrder())) {
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
	
	public boolean deleteComputer(String id) {

		if (id.isEmpty()) {

			return false;
		}

		return daoComputer.deleteComputer(Long.valueOf(id));
	}

	public Pagination paginate(DTOPagination dtoPagination) {

		Pagination page = paginationDTOMapper.getPage(dtoPagination, getCountComputer(dtoPagination.getSearch()));
		pageValidator.checkPageFields(page);

		return page;
	}
}
