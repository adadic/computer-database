package hr.excilys.client.model;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.excilys.client.service.ServiceInputs;
import hr.excilys.model.Company;
import hr.excilys.model.Computer;

public class Page {

	private final static int lengthList = 10;
	private static int currentPage;
	private static int maxPage;
	private ServiceInputs serviceInputs;
	final Logger logger = LoggerFactory.getLogger(Page.class);

	public Page() {

		serviceInputs = new ServiceInputs();
	}

	public void listComputer(ArrayList<Computer> computers) {

		currentPage = 1;
		maxPage = (int) Math.ceil(computers.size() / lengthList);

		while (true) {

			logger.info("\nId\t|\tname\t|\tintroduced\t|\tdiscontinued\t|\tId Company\t|\tCompany name\n");

			if (currentPage != maxPage) {
				for (int i = (currentPage - 1) * lengthList; i < currentPage * lengthList; i++) {
					logger.info(computers.get(i).toString());
				}
			} else {
				for (int i = (currentPage - 1) * lengthList; i < computers.size(); i++) {
					logger.info(computers.get(i).toString());
				}
			}

			logger.info(currentPage + "/" + maxPage);
			logger.info("Enter page number (0 or above maxPage --> quit) :");

			int choice = serviceInputs.consoleID();

			if (choice == 0 || choice > maxPage) {
				break;
			}
			currentPage = choice;
		}
	}

	public void listCompany(ArrayList<Company> companies) {

		currentPage = 1;
		maxPage = (int) Math.ceil(companies.size() / lengthList);

		while (true) {

			logger.info("\nId\t|\tname\t|\tintroduced\t|\tdiscontinued\t|\tId Company\t|\tCompany name\n");

			if (currentPage != maxPage) {
				for (int i = (currentPage - 1) * lengthList; i < currentPage * lengthList; i++) {
					logger.info(companies.get(i).toString());
				}
			} else {
				for (int i = (currentPage - 1) * lengthList; i < companies.size(); i++) {
					logger.info(companies.get(i).toString());
				}
			}

			logger.info(currentPage + "/" + maxPage);
			logger.info("Enter page number (0 or above maxPage --> quit) :");

			int choice = serviceInputs.consoleID();

			if (choice == 0 || choice > maxPage) {
				break;
			}
			currentPage = choice;
		}
	}
}
