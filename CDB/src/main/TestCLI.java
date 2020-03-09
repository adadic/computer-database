package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import DAO.DAOCompany;
import DAO.DAOComputer;
import model.Company;
import model.Computer;

public class TestCLI {
	
	static int choice;
	
	
	public static void main(String[] args) throws SQLException {
		
		ArrayList<Computer> computers = DAOComputer.getComputers();
		ArrayList<Company> companies = DAOCompany.getCompanies();
		
		
		
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
	
			
			@SuppressWarnings("resource")
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
					System.out.println("Service Stopped...");
					System.exit(0);
					
				case COMPUTER_LIST:
					for(Computer comp : computers)System.out.println(comp.toString());
					System.out.println();
					break;
					
				case COMPANY_LIST:
					for(Company comp : companies)System.out.println(comp.toString());
					System.out.println();
					break;
					
				case COMPUTER_DETAIL:
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
					break;
				case CREATE_COMPUTER:
					//Create
					break;
				case UPDATE_COMPUTER:
					//Update
					break;
				case DELETE_COMPUTER:
					//Delete
					break;
				case ERROR:
					System.out.println("Invalide Entry\n");
			}
		}


	}

}
