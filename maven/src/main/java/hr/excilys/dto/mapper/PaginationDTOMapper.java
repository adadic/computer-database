package hr.excilys.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.excilys.dto.DTOPagination;
import hr.excilys.persistence.model.Pagination;
import hr.excilys.validator.PageValidator;

@Component
public final class PaginationDTOMapper {

	private static final int INITPAGE = 1;
	private static final int INITLINES = 10;
	private static final int INITZERO = 0;

	private final PageValidator pageValidator;

	@Autowired
	public PaginationDTOMapper(PageValidator pageValidator) {

		this.pageValidator = pageValidator;
	}

	public Pagination getPage(DTOPagination dtoPagination, int count) {

		if (pageValidator.checkPage(dtoPagination)) {

			return new Pagination.PaginationBuilder(Integer.valueOf(dtoPagination.getLines()),
					Integer.valueOf(dtoPagination.getPage()), dtoPagination.getSearch()).count(count)
							.order(dtoPagination.getOrder()).direction(Integer.valueOf(dtoPagination.getDirection()))
							.build();
		}

		return new Pagination.PaginationBuilder(INITLINES, INITPAGE, dtoPagination.getSearch()).count(count)
				.order(dtoPagination.getOrder()).direction(INITZERO).build();
	}
}
