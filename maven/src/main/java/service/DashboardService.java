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
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public DashboardService() {
		
		super();
		this.daoComputer = new DAOComputer();
	}

	public ArrayList<Computer> getComputersRows(int page, int lines, String search){
		
		DAOComputer daoComputer = new DAOComputer();
		try {
			
			return daoComputer.getComputersRows(page, lines, search);
		} catch (SQLException e) {
			logger.error("Couldn't get computer list");
			
			return new ArrayList<Computer>();
		}
	}
	
	public int getCountComputer(String search) {
		
		try {
			
			return daoComputer.countComputer(search);
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

	public Pagination paginate(HttpServletRequest request) {
		
		int page = 1;
    	int lines = 10;
    	String search = request.getParameter("search");
		String pageString = request.getParameter("page");
		String linesString = request.getParameter("lines");
		
		if(pageString != null) {
    		page = Integer.valueOf(pageString);
    	}
    	if(linesString != null) {
    		lines = Integer.valueOf(linesString);
    	}
    	int count = getCountComputer(search);
    	
		return new Pagination(page, lines, search, count);
	}
}
