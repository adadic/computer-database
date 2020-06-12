package hr.excilys.dto;

public class DTOPagination {

	private String lines;
	private String page;
	private String search;
	private String order;
	private String direction;

	public DTOPagination(DTOPaginationBuilder builder) {

		this.page = builder.page;
		this.lines = builder.lines;
		this.search = builder.search;
		this.order = builder.order;
		this.direction = builder.direction;
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

	public String getOrder() {

		return order;
	}

	public String getDirection() {

		return direction;
	}

	public static class DTOPaginationBuilder {

		private String lines;
		private String page;
		private String search;
		private String order;
		private String direction;

		public DTOPaginationBuilder(String lines, String page, String search) {

			this.page = page;
			this.lines = lines;
			this.search = search;
		}

		public DTOPagination build() {

			return new DTOPagination(this);
		}

		public DTOPaginationBuilder order(String order) {

			this.order = order;

			return this;
		}

		public DTOPaginationBuilder direction(String direction) {

			this.direction = direction;

			return this;
		}
	}
}
