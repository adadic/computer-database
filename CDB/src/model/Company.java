package model;

public class Company {
	private long id;
	private String name;

	
	public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Company(Company company) {
		if(company.getId()!=0) {
			this.id = company.getId();
			this.name = company.getName();
		}
		else {
			this.id =  0;
			this.name = null;
		}
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id + "\t|\t" + name;
	}
	
}
