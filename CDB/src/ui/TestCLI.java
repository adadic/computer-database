package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
import model.Computer;
import persistence.DAOCompany;
import persistence.DAOComputer;
import service.ActionCLI;

public class TestCLI {
	
	static int choice;
	
	
	public static void main(String[] args) throws SQLException {
		Logger logger = LoggerFactory.getLogger(TestCLI.class);
	    logger.info("TestCLI");

		
		ArrayList<Computer> computers = DAOComputer.getComputers();
		ArrayList<Company> companies = DAOCompany.getCompanies();
		int result;
		
		while(true) {
			System.out.println("Welcome in our Command Line Interface (CLI)");
			System.out.println("\tFeatured by OXYL");
			System.out.println("\n1- Computer List");
			System.out.println("2- Company List");
			System.out.println("3- Computer Detail");
			System.out.println("4- Create Computer");
			System.out.println("5- Update Computer");
			System.out.println("6- Delete Computer");
			System.out.println("\nChoose your option:");
	
			Scanner scan = new Scanner(System.in);
			try {
				choice = scan.nextInt();

			}
			catch(Exception e){
				choice=-1;
			}
			
			EnumChoice test = EnumChoice.testChoice(choice);
			
			switch(test) {
				case QUIT:
					ActionCLI.stopSystem();
					
				case COMPUTER_LIST:
					ActionCLI.listComputer(computers);
					break;
					
				case COMPANY_LIST:
					ActionCLI.listCompany(companies);
					break;
					
				case COMPUTER_DETAIL:
					ActionCLI.computerGetDetail(scan);
					break;
					
				case CREATE_COMPUTER:
					result = ActionCLI.createComputer(scan);
					if(result==1) {
						System.out.println("\nNew Computer Inserted\n");
						computers = DAOComputer.getComputers();
					}
					else System.out.println("\nThere was a problem in creating Computer\n");
					break;
					
				case UPDATE_COMPUTER:
					result = ActionCLI.updateComputer(scan);
					if(result == 1) {
						System.out.println("Computer was Changed\n");
						computers = DAOComputer.getComputers();
					}
					else System.out.println("Computer not found\n");
					break;
					
				case DELETE_COMPUTER:
					result = ActionCLI.deleteComputer(scan);
					if(result == 1) {
						System.out.println("Computer was removed\n");
						computers = DAOComputer.getComputers();
					}
					else System.out.println("Computer not found\n");
					break;
					
				case ERROR:
					System.out.println("Invalide Entry\n");
			}
		}


	}

}
