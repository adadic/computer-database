package hr.excilys.dto;

public class DTOUser {

	private String emailORname;
	private String password;

	public DTOUser(String emailORname, String password) {

		this.emailORname = emailORname;
		this.password = password;
	}

	public String getEmailORname() {

		return emailORname;
	}

	public String getPassword() {

		return password;
	}
}
