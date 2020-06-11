package mapper;

import dto.DTOPagination;
import model.Pagination;

public class PaginationMapper {
	
	private static final int INITPAGE = 1;
	private static final int INITLINES = 10;
	private static final int INITZERO = 0;
	
	public static Pagination getPage(DTOPagination dtoPagination, int count){
		
		int page = INITPAGE;
    	int lines = INITLINES;
    	int direction = INITZERO;
		
		if(dtoPagination.getPage() != null) {
    		page = Integer.valueOf(dtoPagination.getPage());
    	}
    	if(dtoPagination.getLines() != null) {
    		lines = Integer.valueOf(dtoPagination.getLines());
    	}
    	if(dtoPagination.getDirection() != null) {
    		direction = Integer.valueOf(dtoPagination.getDirection());
    	}
    	
    	
    	return new Pagination.PaginationBuilder(lines, page, dtoPagination.getSearch())
    			.count(count)
    			.order(dtoPagination.getOrder())
    			.direction(direction)
    			.build();
	}
}
