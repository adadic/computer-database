package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.DTOPagination;
import mapper.PaginationMapper;
import model.Computer;
import model.Pagination;
import persistence.DAOComputer;
import servlet.DashboardServlet;

public class DashboardService {
	
	private final static int ASC = 1;
	private final static int DESC = 0;

	private DAOComputer daoComputer;
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public DashboardService() {
		
		super();
		this.daoComputer = new DAOComputer();
	}

	public ArrayList<Computer> getComputersRows(Pagination page){
		
		DAOComputer daoComputer = new DAOComputer();
		
		if(page.getOrder() != null) {
			if(page.getDirection() == 1) {
				try {
					
					return daoComputer.getComputersSort(page.getPage(), page.getLines(), page.getSearch(), page.getOrder(), ASC);
				} catch (SQLException e) {
					logger.error("Couldn't get computer list");
					
					return new ArrayList<Computer>();
				}
			}else {
				try {
					
					return daoComputer.getComputersSort(page.getPage(), page.getLines(), page.getSearch(), page.getOrder(), DESC);
				} catch (SQLException e) {
					logger.error("Couldn't get computer list");
					
					return new ArrayList<Computer>();
				}
			}
		}
		else {
			try {
				
				return daoComputer.getComputersRows(page.getPage(), page.getLines(), page.getSearch());
			} catch (SQLException e) {
				logger.error("Couldn't get computer list");
				
				return new ArrayList<Computer>();
			}
		}
	}
	
	public int getCountComputer(String search) {
		
		try {
			
			return daoComputer.countComputer(search);
		} catch (SQLException e) {
			
			return -1;
		}
	}

	public boolean deleteComputer(String selected) throws NumberFormatException, SQLException {
				
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

	public Pagination paginate(DTOPagination dtoPagination) {
    	
		return PaginationMapper.getPage(dtoPagination, getCountComputer(dtoPagination.getSearch()));
	}
}
