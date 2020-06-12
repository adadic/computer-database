package hr.excilys.dto;

public class DTOCompany {

	private String id;
	private String name;

	public DTOCompany(String id, String name) {

		this.id = id;
		this.name = name;
	}

	public DTOCompany(DTOCompanyBuilder builder) {

		this.id = builder.id;
		this.name = builder.name;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public static class DTOCompanyBuilder {

		private String id;
		private String name;

		public DTOCompanyBuilder(String id, String name) {

			this.id = id;
			this.name = name;
		}

		public DTOCompany build() {

			return new DTOCompany(this);
		}
	}

}
