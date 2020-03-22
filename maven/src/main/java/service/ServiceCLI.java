package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
import model.Computer;
import model.Page;
import persistence.DAOCompany;
import persistence.DAOComputer;

public class ServiceCLI {
	private Scanner scan;
	private Page page;
	private DAOComputer daoComputer;
	private DAOCompany daoCompany;
	private ServiceInputs serviceInputs;
	final Logger logger = LoggerFactory.getLogger(ServiceCLI.class);

	
	
	
	public ServiceCLI() {
		super();
		this.scan = new Scanner(System.in);
		this.scan.useDelimiter("\n");
		this.page = new Page();
		this.daoComputer = new DAOComputer();
		this.daoCompany = new DAOCompany();
		this.serviceInputs = new ServiceInputs();
	}

	public void stopSystem() {
		logger.info("Service Stopped...");
		System.exit(0);
	}
	
	public void listComputer() throws SQLException {
		ArrayList<Computer> computers = new ArrayList<>();
		computers = daoComputer.getComputers();
		page.listComputer(computers);
	}

	public void listCompany() throws SQLException {
		ArrayList<Company> companies = new ArrayList<>();
		companies = daoCompany.getCompanies();
		page.listCompany(companies);
	}

	public void computerGetDetail() throws SQLException {
		int choice = serviceInputs.consoleID();
		if(choice>0) {
			Optional<Computer> comp = daoComputer.getComputerById(choice);
			if(comp.isPresent()) {
				logger.info(comp.get().toString()+"\n");
			}
			else logger.warn("Computer does not exist\n");
		}
		
	}

	private int createComputer() throws SQLException {		
		logger.info("\nCreating new Computer...");
		logger.info("Enter a Name :");
		String name = scan.next();
		logger.info("\nCompany reference...");
		logger.info("Enter ID of Company (0 for NO Company)");
		
		int company_id = serviceInputs.consoleCompanyID();
		
		
		logger.info("\nIntroduction Timestamp...");
		logger.info("Enter Timestamp : (YYYY-MM-DD or 0 if nothing)");
		
		long introducedTime = serviceInputs.consoleIntroduced();
		
		if(introducedTime != 0) {
			logger.info("\nDiscontiued Timestamp...");
			logger.info("Witch is after introduction!!!");
			logger.info("Enter Timestamp : (YYYY-MM-DD or 0 if nothing)");
			
			long discontinuedTime = serviceInputs.consoleDiscontinued(introducedTime);
			return insertChoice(name,introducedTime,discontinuedTime,company_id);

		}
		return insertChoice(name,introducedTime,0,company_id);
	}

	private int insertChoice(String name, long introducedTime, long discontinuedTime, int company_id) throws SQLException {
		if(introducedTime != 0) {
			if(discontinuedTime!=0) {
				return daoComputer.insertComputer(name,new Timestamp(introducedTime),new Timestamp(discontinuedTime),Integer.toString(company_id));
			}
			else {
				return daoComputer.insertComputer(name,new Timestamp(introducedTime),null,Integer.toString(company_id));
			}
		}
		else {
			return daoComputer.insertComputer(name,null,null,Integer.toString(company_id));
		}
		
	}

	private int deleteComputer() throws SQLException {	
		logger.info("\nEnter Computer ID to Delete");
		int id = scan.nextInt();
		return daoComputer.deleteComputer(id);
	}

	private int updateComputer() throws SQLException {
		int id= serviceInputs.consoleCompanyID();
		Optional<Computer> computer = daoComputer.getComputerById(id);
		
		if(computer.isPresent()) {
			logger.info(computer.get().toString()+"\n");

			String name = computer.get().getName();
			Timestamp introduced = computer.get().getIntroduced();
			Timestamp discontinued = computer.get().getDiscontinued();
			Company company = computer.get().getCompany();
			long company_id;
			
			if(company==null) company_id = 0;
			else company_id = company.getId();
			
			logger.info("You'll be asked if you want to change each fields");
			logger.info("Press 0 if you don't want to do anything to the field");
			
			logger.info("Name : (choose wisely)");
			String changeName = scan.next();
			if(!changeName.equals("0")) name = changeName;
			
			logger.info("Introduced : (YYYY-MM-DD)");
			logger.info("-1 to remove parameter");
			
			long introducedTime = serviceInputs.consoleUpdateIntroduced();
			if(introducedTime >= 0) {
				if(introducedTime != 0) {
					introduced = new Timestamp(introducedTime);
				}
				logger.info("Discontinued : (YYYY-MM-DD)");
				long discontinuedTime = serviceInputs.consoleUpdateDiscontinued(introducedTime);
				if(discontinuedTime > introducedTime) {
					return daoComputer.updateComputer(id, name, introduced, new Timestamp(discontinuedTime), company_id);
				}
				else if(discontinuedTime == 0) {
					return daoComputer.updateComputer(id, name, introduced, discontinued, company_id);
				}
				else{
					return daoComputer.updateComputer(id, name, introduced, null, company_id);
				}
			}
			else{
				return daoComputer.updateComputer(id, name, null, null, company_id);
			}
		}
		return 0;
		
	}
	
	public void computerCreation() throws SQLException {
		
		int result;
		try {
			result = createComputer();
		}
		catch (SQLException e) {
			result = 0;
		}
		if(result==1) {
			logger.info("\nNew Computer Inserted\n");
		}
		else logger.error("\nThere was a problem in creating Computer\n");
	}

	public void computerUpdate() throws SQLException {
		
		int result;
		try {
			result = updateComputer();
		}
		catch (SQLException e) {
			result = 0;
		}
		if(result == 1) {
			logger.info("Computer was Changed\n");
		}
		else logger.error("Computer does not exist\n");
		
	}

	public void computerDelition() throws SQLException {
		
		int result = deleteComputer();
		
		if(result == 1) {
			logger.info("Computer was removed\n");
		}
		else logger.error("Computer does not exist\n");
		
	}
	

}
