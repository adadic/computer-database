package hr.excilys.persistence.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table( name = "COMPUTER" )
public class Computer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name ="name")
	private String name;
	@Column(name = "introduced")
	private Timestamp introduced;
	@Column(name = "discontinued")
	private Timestamp discontinued;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Company.class)
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;

	public Computer() {
	}

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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	public Company getCompany() {

		return company;
	}

	public void setCompany(Company company) {

		this.company = company;
	}

	public static class ComputerBuilder {

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

			if (introduced != null) {
				this.introduced = introduced;
			}

			return this;
		}

		public ComputerBuilder discontinued(Timestamp discontinued) {

			if (discontinued != null) {
				this.discontinued = discontinued;
			}

			return this;
		}

		public ComputerBuilder company(Company company) {

			if (company != null) {
				this.company = company;
			}

			return this;
		}

		public Computer build() {

			return new Computer(this);
		}
	}
}
