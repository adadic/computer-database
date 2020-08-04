package hr.excilys.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class DTOComputer {

	private String id = "0";
	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;
	
	public DTOComputer() {
	}

	public DTOComputer(String id, String computerName, String introduced, String discontinued, String companyId) {

		this.id = id;
		this.computerName = computerName;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getComputerName() {

		return computerName;
	}

	public void setComputerName(String computerName) {

		this.computerName = computerName;
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

	public String getCompanyId() {

		return companyId;
	}

	public void setCompanyId(String companyId) {

		this.companyId = companyId;
	}

	@Override
	public String toString() {

		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
