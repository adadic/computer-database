package hr.excilys.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DTORole {

	private String roleId;
	private String roleName;

	public DTORole() {
	}

	public String getroleId() {
		
		return roleId;
	}


	public void setroleId(String roleId) {
		
		this.roleId = roleId;
	}


	public String getroleName() {
		
		return roleName;
	}


	public void setroleName(String roleName) {
		
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
