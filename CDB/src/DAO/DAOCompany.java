package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Company;
import persistence.MysqlConnect;

public class DAOCompany {
	
	public static ArrayList<Company> getCompanies() throws SQLException {
		
		ArrayList<Company> companies = new ArrayList<>();
		MysqlConnect db = MysqlConnect.getDbCon();
		ResultSet allCompanies = db.query("select id, name from company");
		
		while(allCompanies.next())
			companies.add(new Company(allCompanies.getLong("id") ,allCompanies.getString("name")));
			
		return companies;
	}

}
