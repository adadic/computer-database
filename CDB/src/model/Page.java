package model;

import java.util.ArrayList;

import service.ServiceInputs;

public class Page {
	
	private final static int lengthList = 10;
	private static int currentPage;
	private static int maxPage;
	private ServiceInputs serviceInputs;
	
	public Page(){
		serviceInputs = new ServiceInputs();
	}
	
	public void listComputer(ArrayList<Computer> computers) {
		currentPage = 1;
		maxPage=(int) Math.ceil(computers.size()/lengthList);
		while(true) {
			
			System.out.println("\nId\t|\tname\t|\tintroduced\t|\tdiscontinued\t|\tId Company\t|\tCompany name\n");
			
			if(currentPage!=maxPage) {
				for(int i = (currentPage-1)*lengthList; i <currentPage*lengthList; i++) {
					System.out.println(computers.get(i).toString());
				}
			}
			else {
				for(int i = (currentPage-1)*lengthList; i <computers.size(); i++) {
					System.out.println(computers.get(i).toString());
				}
			}
			System.out.println(currentPage + "/" + maxPage);
			System.out.println("Enter page number (0 or above maxPage --> quit) :");
			int choice = serviceInputs.consoleID();
			
			
			if(choice == 0 || choice > maxPage) {
				break;
			}
			currentPage = choice;
		}
		
		
	}

	public void listCompany(ArrayList<Company> companies) {
		currentPage = 1;
		maxPage=(int) Math.ceil(companies.size()/lengthList);
		while(true) {
			
			System.out.println("\nId\t|\tname\t|\tintroduced\t|\tdiscontinued\t|\tId Company\t|\tCompany name\n");
			
			if(currentPage!=maxPage) {
				for(int i = (currentPage-1)*lengthList; i <currentPage*lengthList; i++) {
					System.out.println(companies.get(i).toString());
				}
			}
			else {
				for(int i = (currentPage-1)*lengthList; i <companies.size(); i++) {
					System.out.println(companies.get(i).toString());
				}
			}
			System.out.println(currentPage + "/" + maxPage);
			System.out.println("Enter page number (0 or above maxPage --> quit) :");
			int choice = serviceInputs.consoleID();
			
			
			if(choice == 0 || choice > maxPage) {
				break;
			}
			currentPage = choice;
		}
		
	}

	
	
}
