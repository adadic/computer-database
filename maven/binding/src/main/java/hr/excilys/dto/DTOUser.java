package hr.excilys.dto;

public class DTOUser {

	private String username;
	private String password;

	public DTOUser(String username, String password) {

		this.username = username;
		this.password = password;
	}

	public String getEmailORname() {

		return username;
	}

	public String getPassword() {

		return password;
	}
}
