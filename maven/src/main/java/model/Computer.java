package model;

import java.sql.Timestamp;

public class Computer {
	private long id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private Company company = null;
	
	public Computer(ComputerBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	
	@Override
	public String toString() {
		if(company!=null) {
			return id + "\t|\t" + name + "\t|\t" 
					+ introduced + "\t|\t" + discontinued 
					+ "\t|\t" + company.getId() + "\t|\t" 
					+ company.getName();
		}
		return id + "\t|\t" + name + "\t|\t" 
			+ introduced + "\t|\t" + discontinued 
			+ "\t|\tnull\t|\tnull";

	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public static class ComputerBuilder{
		private long id;
		private String name;
		private Timestamp introduced;
		private Timestamp discontinued;
		private Company company;
		
		public ComputerBuilder(String name) {
			this.name = name;
		}
		
		public ComputerBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public ComputerBuilder introduced(Timestamp introduced) {
			if(introduced != null) {
				this.introduced = introduced;
			}
			return this;
		}
		
		public ComputerBuilder discontinued(Timestamp discontinued) {
			if(discontinued != null) {
				this.discontinued = discontinued;
			}
			return this;
		}
		
		public ComputerBuilder company(Company company) {
			if(company != null) {
				this.company = company;
			}
			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}
		
	}
	
}
