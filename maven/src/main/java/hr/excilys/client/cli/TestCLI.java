package hr.excilys.client.cli;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.excilys.client.service.ServiceCLI;

public class TestCLI {

	static int choice;

	public static void main(String[] args) throws SQLException {

		Logger logger = LoggerFactory.getLogger(TestCLI.class);
		logger.info("TestCLI");

		ServiceCLI serviceCLI = new ServiceCLI();
		logger.info("Welcome in our Command Line Interface (CLI)");
		logger.info("\tFeatured by OXYL\n");

		while (true) {
			logger.info("1- Computer List");
			logger.info("2- Company List");
			logger.info("3- Computer Detail");
			logger.info("4- Create Computer");
			logger.info("5- Update Computer");
			logger.info("6- Delete Computer");
			logger.info("\nChoose your option : (QUIT --> 0)");

			Scanner scan = new Scanner(System.in);
			scan.useDelimiter("\n");

			try {
				choice = scan.nextInt();

			} catch (InputMismatchException e) {
				choice = -1;
			}

			EnumChoice test = EnumChoice.testChoice(choice);

			switch (test) {
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
				logger.error("Invalide Entry\n");
			default:

			}
		}
	}
}
