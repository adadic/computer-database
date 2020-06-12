package hr.excilys.persistence;

public enum EnumQuery {

	ALLCOMPUTER(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id;"),
	PAGECOMPUTER(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? LIMIT ? OFFSET ?;"),
	IDCOMPUTER("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ? ;"),
	INSERTCOMPUTER("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);"),
	DELETECOMPUTER("DELETE FROM computer WHERE id = ?"),
	UPDATECOMPUTER("UPDATE computer SET name = ? , introduced = ? , discontinued = ? , company_id = ? WHERE id = ? ;"),
	COUNTCOMPUTER("SELECT COUNT(*) as number FROM computer WHERE computer.name LIKE ?;"),
	DELETECOMPUTERCOMPANY("DELETE FROM computer WHERE company_id = ?"), ALLCOMPANY("SELECT id, name FROM company;"),
	IDCOMPANY("SELECT id, name FROM company WHERE id = ? ;"), DELETECOMPANY("DELETE FROM company WHERE id = ?"),
	SORTPAGECOMPUTERASC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? ORDER BY computer.name ASC LIMIT ? OFFSET ?;"),
	SORTPAGECOMPANYASC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? ORDER BY company.name ASC LIMIT ? OFFSET ?;"),
	SORTPAGECOMPUTERDESC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? ORDER BY computer.name DESC LIMIT ? OFFSET ?;"),
	SORTPAGECOMPANYDESC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? ORDER BY company.name DESC LIMIT ? OFFSET ?;");

	private String message;

	EnumQuery(String message) {

		this.message = message;
	}

	public String getQuery() {

		return this.message;
	}
}
