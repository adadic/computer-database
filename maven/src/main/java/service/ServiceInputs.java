package service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import mapper.DateMapper;
import model.Company;
import persistence.DAOCompany;

public class ServiceInputs {
	private Scanner scan;
	private DAOCompany daoCompany;
	private DateMapper dateMapper;
	
	public ServiceInputs() {
		this.scan = new Scanner(System.in);
		this.scan.useDelimiter("\n");
		this.daoCompany = new DAOCompany();
		this.dateMapper = new DateMapper();
	}

	public int consoleID() {
		int input;
		while(true) {
			System.out.println("\nEnter ID");
			try {
				input = scan.nextInt();
				return input;
			}
			catch(NumberFormatException e){
				input=-1;
				System.out.println("Invalide Entry\n");
			}
		}
	}

	public int consoleCompanyID() throws SQLException {
		while(true) {
			try{
				int id = scan.nextInt();
				Optional<Company> check = daoCompany.getCompanyById(id);
				if(id == 0 || check.isPresent()) {
					return id;
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
	}

	public long consoleIntroduced() {
		while(true) {
			String input = scan.next();
			try {
				long inputTime = dateMapper.getDate(input);
				if(inputTime > 0 || input.equals("0")) {
					return inputTime;
				}
			}
			catch (NullPointerException e){
				System.out.println("Wrong timestamp Format... Try again :");
			}
		}
	}

	public long consoleDiscontinued(long introducedTime) {
		while(true) {
			String input = scan.next();
			try{
				long discontinuedTime = dateMapper.getDate(input);
				if((discontinuedTime > 0 && discontinuedTime > introducedTime)) {
					return discontinuedTime;
				}
				else {
					System.out.println("Can't be before introduction Time \nRe-enter Discontinued Time :");
				}
			}
			catch (NullPointerException e) {
				System.out.println("Wrong timestamp Format... Try again :");
			}
		}
	}

	public long consoleUpdateIntroduced() {
		while(true) {
			String input = scan.next();
			try {
				long inputTime = dateMapper.getDate(input);
				if(inputTime > 0 || input.equals("0")) {
					return inputTime;
				}
				else if(input.equals("1")) {
					return -1;
				}
			}
			catch (NullPointerException e){
				System.out.println("Wrong timestamp Format... Try again :");
			}
		}
	}

	public long consoleUpdateDiscontinued(long introducedTime) {
		while(true) {
			String input = scan.next();
			try{
				long discontinuedTime = dateMapper.getDate(input);
				if((discontinuedTime > 0 && discontinuedTime > introducedTime)) {
					return discontinuedTime;
				}
				else if(input.equals("1")) {
					return -1;
				}
				else {
					System.out.println("Can't be before introduction Time \nRe-enter Discontinued Time :");
				}
			}
			catch (NullPointerException e) {
				System.out.println("Wrong timestamp Format... Try again :");
			}
		}
	}
}
