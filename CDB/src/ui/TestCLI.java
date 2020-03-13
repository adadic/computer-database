package ui;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ServiceCLI;

public class TestCLI {
	
	static int choice;
	
	
	public static void main(String[] args) throws SQLException {
		Logger logger = LoggerFactory.getLogger(TestCLI.class);
	    logger.info("TestCLI");
	    
	    ServiceCLI serviceCLI = new ServiceCLI();
	    
		while(true) {
			System.out.println("Welcome in our Command Line Interface (CLI)");
			System.out.println("\tFeatured by OXYL\n");
			System.out.println("1- Computer List");
			System.out.println("2- Company List");
			System.out.println("3- Computer Detail");
			System.out.println("4- Create Computer");
			System.out.println("5- Update Computer");
			System.out.println("6- Delete Computer");
			System.out.println("\nChoose your option : (QUIT --> 0)");
	
			Scanner scan = new Scanner(System.in);
			scan.useDelimiter("\n");
			
			try {
				choice = scan.nextInt();

			}
			catch(Exception e){
				choice=-1;
			}
			
			EnumChoice test = EnumChoice.testChoice(choice);
			
			switch(test) {
				case QUIT:
					scan.close();
					serviceCLI.stopSystem();
					
				case COMPUTER_LIST:
					serviceCLI.listComputer();
					break;
					
				case COMPANY_LIST:
					serviceCLI.listCompany();
					break;
					
				case COMPUTER_DETAIL:
					serviceCLI.computerGetDetail();
					break;
					
				case CREATE_COMPUTER:
					serviceCLI.computerCreation();
					break;
					
				case UPDATE_COMPUTER:
					serviceCLI.computerUpdate();
					break;
					
				case DELETE_COMPUTER:
					serviceCLI.computerDelition();
					break;
					
				case ERROR:
					System.out.println("Invalide Entry\n");
				default:
					
			}
		}


	}

}
