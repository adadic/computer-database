package model;


public class Pagination {
	
	private static final int NBLINESTART = 10;
	
	private int lines;
	private int page;
	private int maxPage;
	private int count;
	private String search;
	
	public Pagination(){
		this.lines = NBLINESTART;
		this.page = 1;
		this.maxPage = this.page;
	}

	public Pagination(int page, int lines, String search, int count) {
		this.page = page;
		this.lines = lines;
		this.search = search;
		this.count = count;
		this.maxPage = count / lines + 1;
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

	
}
