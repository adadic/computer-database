package mapper;

import dto.DTOPagination;
import model.Pagination;

public class PaginationMapper {
	
	private static final int INITPAGE = 1;
	private static final int INITLINES = 10;
	
	public static Pagination getPage(DTOPagination dtoPagination, int count){
		
		int page = INITPAGE;
    	int lines = INITLINES;
		
		if(dtoPagination.getPage() != null) {
    		page = Integer.valueOf(dtoPagination.getPage());
    	}
    	if(dtoPagination.getLines() != null) {
    		lines = Integer.valueOf(dtoPagination.getLines());
    	}
    	
    	return new Pagination.PaginationBuilder(lines, page, dtoPagination.getSearch()).count(count).build();
	}
}
