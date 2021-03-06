package hr.excilys.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DTOUser {

	@NotNull
	@NotEmpty(message="Username cannot be empty")
	private String userName;
	@NotNull
	@NotEmpty(message="Password cannot be empty")
	private String password;
	@NotNull
	@NotEmpty(message="Matching password cannot be empty")
	private String matchingPassword;
	private DTORole dtoRole;
	@NotNull
	@NotEmpty(message="Email cannot be empty")
	@Email(message = "Email should be valid")
    private String email;

	public DTOUser(String username, String password) {

		this.userName = username;
		this.password = password;
	}

	@SuppressWarnings("unused")
	private DTOUser() {
	}

//	public String getId() {
//
//		return id;
//	}
//
//	public void setId(String id) {
//
//		this.id = id;
//	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getMatchingPassword() {

		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {

		this.matchingPassword = matchingPassword;
	}

	public DTORole getDtoRole() {

		return dtoRole;
	}

	public void setDtoRole(DTORole dtoRole) {

		this.dtoRole = dtoRole;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public void setPassword(String password) {

		this.password = password;
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
