package hr.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table( name = "COMPANY" )
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name ="name")
	private String name;

	public Company(CompanyBuilder builder) {

		this.id = builder.id;
		this.name = builder.name;
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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	public static class CompanyBuilder {

		private long id;
		private String name;

		public CompanyBuilder(long id, String name) {

			this.id = id;
			this.name = name;
		}

		public Company build() {

			return new Company(this);
		}
	}

}
