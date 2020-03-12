package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Page {
	
	private final static int lengthList = 10;
	private static int currentPage;
	private static int maxPage;
	
	public static void listComputer(Scanner scan, ArrayList<Computer> computers) {
		
		boolean inList = true;
		int choice;
		currentPage = 1;
		maxPage=(int) Math.ceil(computers.size()/lengthList);
		while(inList) {
			
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
			while(true) {
				try{
					choice = scan.nextInt();
					break;
				}
				catch(InputMismatchException e) {
					@SuppressWarnings("unused")
					String garbagge = scan.next();
				}
			}
			
			
			if(choice == 0 || choice > maxPage) inList = false;
			currentPage = choice;
		}
		
		
	}

	public static void listCompany(Scanner scan, ArrayList<Company> companies) {
		boolean inList = true;
		int choice;
		currentPage = 1;
		maxPage=(int) Math.ceil(companies.size()/lengthList);
		while(inList) {
			
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
			while(true) {
				try{
					choice = scan.nextInt();
					break;
				}
				catch(InputMismatchException e) {
					@SuppressWarnings("unused")
					String garbagge = scan.next();
				}
			}
			
			
			if(choice == 0) inList = false;
			currentPage = choice;
		}
		
	}

	
	
}
