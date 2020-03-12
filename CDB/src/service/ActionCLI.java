package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import mapper.DateMapper;
import model.Company;
import model.Computer;
import model.Page;
import persistence.DAOCompany;
import persistence.DAOComputer;

public class ActionCLI {
	private Page page;
	private DAOComputer daoComputer;
	private DAOCompany daoCompany;
	private DateMapper dateMapper;
	
	public void stopSystem() {
		System.out.println("Service Stopped...");
		System.exit(0);
	}
	
	public void listComputer(Scanner scan) throws SQLException {
		page = new Page();
		daoComputer = new DAOComputer();
		ArrayList<Computer> computers = new ArrayList<>();
		computers = daoComputer.getComputers();
		page.listComputer(scan,computers);
	}

	public void listCompany(Scanner scan) throws SQLException {

		page = new Page();
		daoCompany = new DAOCompany();
		ArrayList<Company> companies = new ArrayList<>();
		companies = daoCompany.getCompanies();
		page.listCompany(scan,companies);
	}

	public void computerGetDetail(Scanner scan) throws SQLException {
		
		int choice;
		daoComputer = new DAOComputer();
		
		System.out.println("\nEnter ID");
		try {
			choice = scan.nextInt();
		}
		catch(NumberFormatException e){
			choice=-1;
			System.out.println("Invalide Entry\n" +e.getMessage());
		}
		if(choice>0) {
			Optional<Computer> comp = daoComputer.getComputerById(choice);
			if(comp.isPresent()) {
				System.out.println(comp.get().toString());
				System.out.println();
			}
			else System.out.println("Computer not found\n");
		}
	}

	private int createComputer(Scanner scan) throws SQLException {
		daoCompany = new DAOCompany();
		String name;
		String introduced="";
		String discontinued="";
		int company_id=-1;
		long introducedTime=0;
		long discontinuedTime=0;
		scan.useDelimiter("\n");
		
		
		System.out.println("\nCreating new Computer...");
		System.out.println("Enter a Name :");
		name = scan.next();
		System.out.println("\nCompany reference...");
		System.out.println("Enter ID of Company (0 for NO Company)");
		
		while(true) {
			try{
				company_id = scan.nextInt();
				Optional<Company> check = daoCompany.getCompanyById(company_id);
				if(company_id == 0 || check.isPresent()) {
					break;
				}
				else {
					System.out.println("Company does not exist");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Input MissMatch.... Enter ID :");
				@SuppressWarnings("unused")
				String garbagge = scan.next();
			}
		}
		
		
		System.out.println("\nIntroduction Timestamp...");
		System.out.println("Enter Timestamp : (DD-MM-YY HH:mm or 0 if nothing)");
		
		while(true) {
			introduced = scan.next();
			try {
				introducedTime = dateMapper.getDate(introduced);
				if(introducedTime > 0 || introduced.equals("0")) {
					break;
				}
			}
			catch (NullPointerException e){
				System.out.println("Wrong timestamp Format... Try again :");
			}
		}
		if(!introduced.equals("0")) {
			System.out.println("\nDiscontiued Timestamp...");
			System.out.println("Witch is after introduction!!!");
			System.out.println("Enter Timestamp : (DD-MM-YY HH:mm or 0 if nothing)");
			
			while(true) {
				discontinued = scan.next();
				try{
					discontinuedTime = dateMapper.getDate(discontinued);
					if((discontinuedTime > 0 && discontinuedTime > introducedTime) || discontinued.equals("0")) {
						break;
					}
					else {
						System.out.println("Can't be before introduction Time");
					}
				}
				
				
				catch (NullPointerException e) {
					System.out.println("Wrong timestamp Format... Try again :");
				}
			}
		}
		return insertChoice(name,introducedTime,discontinuedTime,company_id);
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

	private int deleteComputer(Scanner scan) throws SQLException {
		
		int id;
		daoComputer = new DAOComputer();
		
		System.out.println("\nEnter Computer ID to Delete");
		id = scan.nextInt();
		return daoComputer.deleteComputer(id);
		
		
	}

	private int updateComputer(Scanner scan) throws SQLException {

		daoComputer = new DAOComputer();
		
		System.out.println("\nSelect Computer ID to Change");
		scan.useDelimiter("\n");
		int id = scan.nextInt();
		Optional<Computer> computer = daoComputer.getComputerById(id);
		
		if(computer.isPresent()) {
			System.out.println(computer.get().toString());
			System.out.println();
			
			String name = computer.get().getName();
			Timestamp introduced = computer.get().getIntroduced();
			Timestamp discontinued = computer.get().getDiscontinued();
			Company company = computer.get().getCompany();
			long company_id;
			System.out.println(company);
			if(company==null) company_id = 0;
			else company_id = company.getId();
			System.out.println("You'll be asked if you want to change each fields");
			System.out.println("Press 0 if you don't want to do anything to the field");
			
			System.out.println("Name : ");
			String changeName = scan.next();
			if(!changeName.equals("0")) name = changeName;
			
			System.out.println("Introduced : (DD-MM-YY HH:mm)");
			String changeIntroduced = scan.next();
			if(!changeIntroduced.equals("0") || introduced!=null) {
				
				introduced = new Timestamp(dateMapper.getDate(changeIntroduced));
				
				System.out.println("Discontinued : (DD-MM-YY HH:mm)");
				String changeDiscontinued = scan.next();
				if(!changeDiscontinued.equals("0") 
						&& introduced.getTime() > dateMapper.getDate(changeIntroduced)) {
					discontinued = new Timestamp(dateMapper.getDate(changeDiscontinued));
				}
			}
			
			
			
			System.out.println("ID company : ");
			int changeCompanyId = scan.nextInt();
			if(changeCompanyId!=0) company_id = changeCompanyId;
			
			
			return daoComputer.updateComputer(id, name, introduced, discontinued, company_id);
			
		}
		return 0;
		
	}
	
	public void computerCreation(Scanner scan) throws SQLException {
		
		int result;
		
		try {
			result = createComputer(scan);
		} catch (SQLException e) {
			result = 0;
		}
		
		if(result==1) {
			System.out.println("\nNew Computer Inserted\n");
		}
		
		else System.out.println("\nThere was a problem in creating Computer\n");
	}

	public void computerUpdate(Scanner scan) throws SQLException {
		
		int result;
		
		try {
			result = updateComputer(scan);
		} catch (SQLException e) {
			result = 0;
		}

		if(result == 1) {
			System.out.println("Computer was Changed\n");

		}
		else System.out.println("Computer not found\n");
		
	}

	public void computerDelition(Scanner scan) throws SQLException {
		
		int result = deleteComputer(scan);
		
		if(result == 1) {
			System.out.println("Computer was removed\n");
		}
		else System.out.println("Computer not found\n");
		
	}
	

}
