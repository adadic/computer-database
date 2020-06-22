package hr.excilys.persistence;

public enum EnumQuery {

	ALLCOMPUTER(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id;"),
	PAGECOMPUTER(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id"
					+ " WHERE computer.name LIKE :search LIMIT :limit OFFSET :offset;"),
	IDCOMPUTER("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = :id_computer;"),
	INSERTCOMPUTER(
			"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name, :introduced, :discontinued, :id_company);"),
	DELETECOMPUTER("DELETE FROM computer WHERE id = :id_computer"),
	UPDATECOMPUTER(
			"UPDATE computer SET name = :name , introduced = :introduced , discontinued = :discontinued , company_id = :id_company WHERE id = :id_computer ;"),
	COUNTCOMPUTER("SELECT COUNT(*) as number FROM computer WHERE computer.name LIKE :search;"),
	DELETECOMPUTERCOMPANY("DELETE FROM computer WHERE company_id = :id_company"),
	ALLCOMPANY("SELECT id, name FROM company;"), IDCOMPANY("SELECT id, name FROM company WHERE id = :id_company;"),
	DELETECOMPANY("DELETE FROM company WHERE id = :id_company"),
	SORTPAGECOMPUTERASC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE :search ORDER BY computer.name ASC LIMIT :limit OFFSET :offset;"),
	SORTPAGECOMPANYASC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE :search ORDER BY company.name ASC LIMIT :limit OFFSET :offset;"),
	SORTPAGECOMPUTERDESC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE :search ORDER BY computer.name DESC LIMIT :limit OFFSET :offset;"),
	SORTPAGECOMPANYDESC(
			"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE :search ORDER BY company.name DESC LIMIT :limit OFFSET :offset;");

	private String message;

	EnumQuery(String message) {

		this.message = message;
	}

	public String getQuery() {

		return this.message;
	}
}
