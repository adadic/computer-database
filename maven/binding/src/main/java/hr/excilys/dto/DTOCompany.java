package hr.excilys.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class DTOCompany {

	private String companyId;
	private String companyName;

	public DTOCompany() {
	}

	public String getCompanyId() {
		
		return companyId;
	}


	public void setCompanyId(String companyId) {
		
		this.companyId = companyId;
	}


	public String getCompanyName() {
		
		return companyName;
	}


	public void setCompanyName(String companyName) {
		
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
