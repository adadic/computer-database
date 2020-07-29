package hr.excilys.persistence;

public enum EnumQuery {

	ALLCOMPUTER("FROM Computer WHERE name LIKE :search"), IDCOMPUTER("FROM Computer WHERE id = :id_computer"),
	GETCOMPUTERS("FROM Computer"),
	INSERTCOMPUTER(
			"INSERT INTO Computer (name, introduced, discontinued, company_id) VALUES (:name, :introduced, :discontinued, :id_company)"),
	DELETECOMPUTER("DELETE FROM Computer WHERE id = :id_computer"),
	UPDATECOMPUTER(
			"UPDATE Computer SET name = :name , introduced = :introduced , discontinued = :discontinued , company_id = :id_company WHERE id = :id_computer "),
	DELETECOMPUTERCOMPANY("DELETE FROM computer WHERE company_id = :id_company"), ALLCOMPANY("FROM Company"),
	IDCOMPANY("FROM Company WHERE id = :id_company"), DELETECOMPANY("DELETE FROM Company WHERE id = :id_company"),
	SORTPAGECOMPUTERASC("FROM Computer WHERE name LIKE :search ORDER BY name ASC"),
	SORTPAGECOMPANYASC("FROM Computer WHERE name LIKE :search ORDER BY company.name ASC"),
	SORTPAGECOMPUTERDESC("FROM Computer WHERE name LIKE :search ORDER BY name DESC"),
	SORTPAGECOMPANYDESC("FROM Computer WHERE name LIKE :search ORDER BY company.name DESC"),
	GETUSER("FROM User WHERE username = :username"), ALLROLE("FROM Role"),
	UPDATECOMPANY("UPDATE Company SET name = :name  WHERE id = :id ");

	private String message;

	EnumQuery(String message) {

		this.message = message;
	}

	public String getQuery() {

		return this.message;
	}
}
