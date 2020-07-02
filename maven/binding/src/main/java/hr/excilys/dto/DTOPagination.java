package dto;

public final class DTOPagination {

	private String lines;
	private String page;
	private String search;
	private String order;
	private String direction;
	private String msg = "";
	
	public String getLines() {
		
		return lines;
	}

	public void setLines(String lines) {
		
		this.lines = lines;
	}

	public String getPage() {
		
		return page;
	}

	public void setPage(String page) {
		
		this.page = page;
	}

	public String getSearch() {
		
		return search;
	}

	public void setSearch(String search) {
		
		this.search = search;
	}

	public String getOrder() {
		
		return order;
	}

	public void setOrder(String order) {
		
		this.order = order;
	}

	public String getMsg() {
		
		return msg;
	}

	public void setMsg(String msg) {
		
		this.msg = msg;
	}

	public void setDirection(String direction) {
		
		this.direction = direction;
	}

	public String getDirection() {

		return direction;
	}
}
