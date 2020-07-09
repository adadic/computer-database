package hr.excilys.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DTOUser {

	private String id = "0";
	private String userName;
	private String password;
	private DTORole dtoRole;

	public DTOUser(String username, String password) {

		this.userName = username;
		this.password = password;
	}
	
	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getUsername() {

		return userName;
	}

	public String getPassword() {

		return password;
	}
	
	public DTORole getRole() {

		return dtoRole;
	}

	public void setRole(DTORole dtoRole) {

		this.dtoRole = dtoRole;
	}
	
	@Override
	public String toString() {

		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
