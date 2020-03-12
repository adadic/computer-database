package model;

import java.sql.Timestamp;

public class Computer {
	private long id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private Company company;
	
	public Computer(String name) {
		this.name = name;
	}
	
	public Computer(long id, String name, Timestamp introduced, Timestamp discontinued, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public Computer(long id, String name, Timestamp introduced, Timestamp discontinued) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = null;
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
		if(company!=null)return id + "\t|\t" + name + "\t|\t" + introduced + "\t|\t" + discontinued + "\t|\t" + company.getId() + "\t|\t" + company.getName();
		return id + "\t|\t" + name + "\t|\t" + introduced + "\t|\t" + discontinued + "\t|\tnull\t|\tnull";

	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
