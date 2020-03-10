package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import mapper.DateMapper;
import model.Company;
import model.Computer;
import persistence.DAOComputer;

public class ActionCLI {
	
	public static void stopSystem() {
		System.out.println("Service Stopped...");
		System.exit(0);
	}
	
	public static void listComputer(ArrayList<Computer> computers) {
		for(Computer comp : computers)System.out.println(comp.toString());
		System.out.println();
	}

	public static void listCompany(ArrayList<Company> companies) {
		for(Company comp : companies)System.out.println(comp.toString());
		System.out.println();
	}

	public static void computerGetDetail(Scanner scan) throws SQLException {
		int choice;
		System.out.println("\nEnter ID");
		try {
			choice = scan.nextInt();
		}
		catch(NumberFormatException e){
			choice=-1;
			System.out.println("Invalide Entry\n" +e.getMessage());
		}
		if(choice>0) {
			Optional<Computer> comp = DAOComputer.getComputerById(choice);
			if(comp.isPresent()) {
				System.out.println(comp.get().toString());
				System.out.println();
			}
			else System.out.println("Computer not found\n");
		}
	}

	public static int createComputer(Scanner scan) throws SQLException {
		String name;
		String introduced;
		String discontinued;
		int company_id=0;
		
		System.out.println("\nCreating new Computer...");
		System.out.println("Enter a Name :");
		scan.useDelimiter("\n");
		name = scan.next();
		
		System.out.println("You can stop here (Press 0 to STOP or Press 1)...");
		int press = 0;
		press = scan.nextInt();
		
		if(press!=0) {
			System.out.println("\nCompany reference...");
			System.out.println("Enter ID of Company (Wrong number result in no Company)");
			company_id = scan.nextInt();
			
			
			System.out.println("\nIntroduction Timestamp...");
			System.out.println("Enter Timestamp : (DD-MM-YY HH:mm or 0 if nothing)");
			introduced = scan.next();
			if(!introduced.equals("0")) {
				long introducedTime = DateMapper.getDate(introduced);
				
				System.out.println("\nDiscontiued Timestamp...");
				System.out.println("Witch is after introduction!!!");
				System.out.println("Enter Timestamp : (DD-MM-YY HH:mm or 0 if nothing)");
				discontinued = scan.next();
				if(!discontinued.equals("0")) {
					long discontinuedTime = DateMapper.getDate(discontinued);
					if(introducedTime>discontinuedTime) 
						return DAOComputer.insertComputer(name,
							new Timestamp(introducedTime),
							null,
							company_id);
					return DAOComputer.insertComputer(name,
							new Timestamp(introducedTime),
							new Timestamp(discontinuedTime),
							company_id);
				}
				return DAOComputer.insertComputer(name,
						new Timestamp(introducedTime),
						null,
						company_id);

			}
			
			
			return DAOComputer.insertComputer(name,null,null,company_id);
		}
		else {
			return DAOComputer.insertComputer(name,null,null,0);
		}
	}

	public static int deleteComputer(Scanner scan) throws SQLException {
		int id;
		System.out.println("\nEnter Computer ID to Delete");
		id = scan.nextInt();
		return DAOComputer.deleteComputer(id);
		
		
	}

	public static int updateComputer(Scanner scan) throws SQLException {
		
		System.out.println("\nSelect Computer ID to Change");
		scan.useDelimiter("\n");
		int id = scan.nextInt();
		Optional<Computer> computer = DAOComputer.getComputerById(id);
		
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
				
				introduced = new Timestamp(DateMapper.getDate(changeIntroduced));
				
				System.out.println("Discontinued : (DD-MM-YY HH:mm)");
				String changeDiscontinued = scan.next();
				if(!changeDiscontinued.equals("0") 
						&& introduced.getTime()>DateMapper.getDate(changeIntroduced)) {
					discontinued = new Timestamp(DateMapper.getDate(changeDiscontinued));
				}
			}
			
			
			
			System.out.println("ID company : ");
			int changeCompanyId = scan.nextInt();
			if(changeCompanyId!=0) company_id = changeCompanyId;
			
			
			return DAOComputer.updateComputer(id, name, introduced, discontinued, company_id);
			
		}
		return 0;
		
	}
	

}
