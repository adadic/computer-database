package hr.excilys.model;

import java.time.LocalDate;

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
@Table(name = "computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name ="name")
	private String name;
	@Column(name = "introduced")
	private LocalDate introduced;
	@Column(name = "discontinued")
	private LocalDate discontinued;
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

	public String getName() {

		return name;
	}

	public LocalDate getIntroduced() {

		return introduced;
	}

	public LocalDate getDiscontinued() {

		return discontinued;
	}

	public Company getCompany() {

		return company;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static class ComputerBuilder {

		private long id;
		private final String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;

		public ComputerBuilder(String name) {

			this.name = name;
		}

		public ComputerBuilder id(long id) {

			this.id = id;

			return this;
		}

		public ComputerBuilder introduced(LocalDate introduced) {

			if (introduced != null) {
				this.introduced = introduced;
			}

			return this;
		}

		public ComputerBuilder discontinued(LocalDate discontinued) {

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
