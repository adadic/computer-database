package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import model.Pagination;
import persistence.DAOComputer;
import servlet.DashboardServlet;

public class DashboardService {

	private DAOComputer daoComputer;
	private Pagination pagination;
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public DashboardService() {
		super();
		this.daoComputer = new DAOComputer();
		this.pagination = new Pagination();
	}

	public ArrayList<Computer> getComputersRows(int page, int lines, String search){
		DAOComputer daoComputer = new DAOComputer();
		try {
			return daoComputer.getComputersRows(page, lines, search);
		} catch (SQLException e) {
			logger.error("Couldn't get computer list");
			return null;
		}
	}
	
	public int getCountComputer() {
		try {
			return daoComputer.countComputer();
		} catch (SQLException e) {
			return -1;
		}
	}

	public boolean deleteComputer(HttpServletRequest request) throws NumberFormatException, SQLException {
		String selected = request.getParameter("selection");
		String str[] = selected.split(",");
		List<String> list = new ArrayList<String>();
		list = Arrays.asList(str);
		
		if(list.isEmpty()) {
			return false;
		}
		for(String element: list){
			daoComputer.deleteComputer(Long.valueOf(element));
		}
		return true;
	}
	
	public ArrayList<Integer> getPageInfo(){
		return null;
		
	}
}
