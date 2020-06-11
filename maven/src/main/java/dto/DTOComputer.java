package dto;

public class DTOComputer {
	
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private DTOCompany company;
	
	public DTOComputer(DTOComputerBuilder builder) {
		
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}
	
	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public String getIntroduced() {
		
		return introduced;
	}

	public void setIntroduced(String introduced) {
		
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		
		this.discontinued = discontinued;
	}
	
	

	public DTOCompany getCompany() {
		
		return company;
	}

	public void setCompany(DTOCompany company) {
		
		this.company = company;
	}
	
	public static class DTOComputerBuilder{
		
		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private DTOCompany company;
		
		public DTOComputerBuilder(String name) {
			
			this.name = name;
		}
		
		public DTOComputerBuilder id(String id) {
			
			this.id = id;
			
			return this;
		}
		
		public DTOComputerBuilder introduced(String introduced) {
			
			if(introduced != null) {
				this.introduced = introduced;
			}
			
			return this;
		}
		
		public DTOComputerBuilder discontinued(String discontinued) {
			
			if(discontinued != null) {
				this.discontinued = discontinued;
			}
			
			return this;
		}
		
		public DTOComputerBuilder company(DTOCompany company) {
			
			if(company != null) {
				this.company = company;
			}
			
			return this;
		}
		
		public DTOComputer build() {
			
			return new DTOComputer(this);
		}
	}
}
