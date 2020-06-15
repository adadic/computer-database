package hr.excilys.model;

public class Pagination {

	private static final int NBLINESTART = 10;
	private static final int PAGEINIT = 1;

	private int lines;
	private int page;
	private int maxPage;
	private int count;
	private String search;
	private String order;
	private int direction;

	public Pagination() {

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
		this.order = builder.order;
		this.direction = builder.direction;
	}

	public int getLines() {

		return lines;
	}

	public void setLines(int lines) {

		this.lines = lines;
	}

	public int getPage() {

		return page;
	}

	public void setPage(int page) {

		this.page = page;
	}

	public int getMaxPage() {

		return maxPage;
	}

	public void setMaxPage(int maxPage) {

		this.maxPage = maxPage;
	}

	public int getCount() {

		return count;
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

	public int getDirection() {

		return direction;
	}

	public void setDirection(int direction) {

		this.direction = direction;
	}

	public static class PaginationBuilder {

		private int lines;
		private int page;
		private int count;
		private String search;
		private String order;
		private int direction;

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

		public PaginationBuilder order(String order) {

			this.order = order;

			return this;
		}

		public PaginationBuilder direction(int direction) {

			this.direction = direction;

			return this;
		}
	}
}
