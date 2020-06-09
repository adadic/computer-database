package model;


public class Pagination {
	
	private int lengthList = 10;
	private int page = 1;
	private int maxPage;
	
	public Pagination(){
	}
	
	public void setParameters(int page, int lines) {
		this.page = page;
		this.lengthList = lines;
	}
	
}
