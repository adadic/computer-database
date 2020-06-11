package dto;

public class DTOPagination {
	
	private String lines;
	private String page;
	private String search;
	
	public DTOPagination(DTOPaginationBuilder builder) {
		
		this.page = builder.page;
		this.lines = builder.lines;
		this.search = builder.search;
	}

	public String getLines() {
		
		return lines;
	}

	public String getPage() {
		
		return page;
	}

	public String getSearch() {
		
		return search;
	}
	
	public static class DTOPaginationBuilder{
			
		private String lines;
		private String page;
		private String search;
			
			public DTOPaginationBuilder(String lines, String page, String search) {
				
				this.page = page;
				this.lines = lines;
				this.search = search;
			}
			
			public DTOPagination build() {
				
				return new DTOPagination(this);
			}
		}
	
}
