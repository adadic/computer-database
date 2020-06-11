package model;

public class Pagination {
	
	private static final int NBLINESTART = 10;
	private static final int PAGEINIT = 1;
	
	private int lines;
	private int page;
	private int maxPage;
	private int count;
	private String search;
	
	public Pagination(){
		
		this.lines = NBLINESTART;
		this.page = PAGEINIT;
		this.maxPage = this.page;
	}

	public Pagination(PaginationBuilder builder) {
		
		this.page = builder.page;
		this.lines = builder.lines;
		this.search = builder.search;
		this.count = builder.count;
		this.maxPage = builder.count / builder.lines + 1;
	}

	public int getLines() {
		
		return lines;
	}

	public int getPage() {
		
		return page;
	}

	public int getMaxPage() {
		
		return maxPage;
	}

	public int getCount() {
		
		return count;
	}

	public String getSearch() {
		
		return search;
	}

	public static class PaginationBuilder{
		
		private int lines;
		private int page;
		private int count;
		private String search;
			
		public PaginationBuilder(int lines, int page, String search) {
			
			this.page = page;
			this.lines = lines;
			this.search = search;
		}
		public PaginationBuilder count(int count) {
			
			this.count = count;
			
			return this;
		}
		
		public Pagination build() {
			
			return new Pagination(this);
		}
	}
}
