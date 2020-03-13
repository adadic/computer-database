package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

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
		System.out.println("Service Stopped...");
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
				System.out.println(comp.get().toString()+"\n");
			}
			else System.out.println("Computer does not exist\n");
		}
		
	}

	private int createComputer() throws SQLException {		
		System.out.println("\nCreating new Computer...");
		System.out.println("Enter a Name :");
		String name = scan.next();
		System.out.println("\nCompany reference...");
		System.out.println("Enter ID of Company (0 for NO Company)");
		
		int company_id = serviceInputs.consoleCompanyID();
		
		
		System.out.println("\nIntroduction Timestamp...");
		System.out.println("Enter Timestamp : (DD-MM-YY HH:mm or 0 if nothing)");
		
		long introducedTime = serviceInputs.consoleIntroduced();
		
		if(introducedTime != 0) {
			System.out.println("\nDiscontiued Timestamp...");
			System.out.println("Witch is after introduction!!!");
			System.out.println("Enter Timestamp : (DD-MM-YY HH:mm or 0 if nothing)");
			
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
		System.out.println("\nEnter Computer ID to Delete");
		int id = scan.nextInt();
		return daoComputer.deleteComputer(id);
	}

	private int updateComputer() throws SQLException {
		int id= serviceInputs.consoleCompanyID();
		Optional<Computer> computer = daoComputer.getComputerById(id);
		
		if(computer.isPresent()) {
			System.out.println(computer.get().toString());
			System.out.println();
			
			String name = computer.get().getName();
			Timestamp introduced = computer.get().getIntroduced();
			Timestamp discontinued = computer.get().getDiscontinued();
			Company company = computer.get().getCompany();
			long company_id;
			
			if(company==null) company_id = 0;
			else company_id = company.getId();
			
			System.out.println("You'll be asked if you want to change each fields");
			System.out.println("Press 0 if you don't want to do anything to the field");
			
			System.out.println("Name : (choose wisely)");
			String changeName = scan.next();
			if(!changeName.equals("0")) name = changeName;
			
			System.out.println("Introduced : (DD-MM-YY HH:mm)");
			System.out.println("-1 to remove parameter");
			
			long introducedTime = serviceInputs.consoleUpdateIntroduced();
			if(introducedTime >= 0) {
				if(introducedTime != 0) {
					introduced = new Timestamp(introducedTime);
				}
				System.out.println("Discontinued : (DD-MM-YY HH:mm)");
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
			System.out.println("\nNew Computer Inserted\n");
		}
		else System.out.println("\nThere was a problem in creating Computer\n");
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
			System.out.println("Computer was Changed\n");
		}
		else System.out.println("Computer does not exist\n");
		
	}

	public void computerDelition() throws SQLException {
		
		int result = deleteComputer();
		
		if(result == 1) {
			System.out.println("Computer was removed\n");
		}
		else System.out.println("Computer does not exist\n");
		
	}
	

}
