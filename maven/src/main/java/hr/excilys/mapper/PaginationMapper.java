package hr.excilys.mapper;

import hr.excilys.dto.DTOPagination;
import hr.excilys.model.Pagination;
import hr.excilys.validator.PageValidator;

public class PaginationMapper {

	private static final int INITPAGE = 1;
	private static final int INITLINES = 10;
	private static final int INITZERO = 0;
	private static final boolean HASERROR = true;

	public static Pagination getPage(DTOPagination dtoPagination, int count) {

		if (PageValidator.checkString(dtoPagination)) {

			return new Pagination.PaginationBuilder(Integer.valueOf(dtoPagination.getLines()),
					Integer.valueOf(dtoPagination.getPage()), dtoPagination.getSearch()).count(count)
							.order(dtoPagination.getOrder()).direction(Integer.valueOf(dtoPagination.getDirection()))
							.build();
		}

		return new Pagination.PaginationBuilder(INITLINES, INITPAGE, dtoPagination.getSearch()).count(count)
				.order(dtoPagination.getOrder()).direction(INITZERO).hasError(HASERROR).build();
	}
}
