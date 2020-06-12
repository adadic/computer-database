package hr.excilys.service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.excilys.mapper.DateMapper;
import hr.excilys.model.Company;
import hr.excilys.persistence.DAOCompany;

public class ServiceInputs {
	private Scanner scan;
	private DAOCompany daoCompany;
	final Logger logger = LoggerFactory.getLogger(ServiceInputs.class);

	public ServiceInputs() {

		this.scan = new Scanner(System.in);
		this.scan.useDelimiter("\n");
		this.daoCompany = new DAOCompany();
	}

	public int consoleID() {

		int input;
		while (true) {
			logger.info("\nEnter ID");
			try {
				input = scan.nextInt();

				return input;
			} catch (NumberFormatException e) {
				input = -1;
				logger.error("Invalide Entry\n");
			}
		}
	}

	public int consoleCompanyID() throws SQLException {

		while (true) {
			try {
				int id = scan.nextInt();
				Optional<Company> check = daoCompany.getCompanyById(id);
				if (id == 0 || check.isPresent()) {
					return id;
				} else {
					logger.error("Company does not exist");
				}
			} catch (InputMismatchException e) {
				logger.warn("Input MissMatch.... Enter ID :");
				@SuppressWarnings("unused")
				String garbagge = scan.next();
			}
		}
	}

	public long consoleIntroduced() {

		while (true) {
			String input = scan.next();
			try {
				long inputTime = DateMapper.getDate(input);
				if (inputTime > 0 || input.equals("0")) {
					return inputTime;
				}
			} catch (NullPointerException e) {
				logger.warn("Wrong timestamp Format... Try again :");
			}
		}
	}

	public long consoleDiscontinued(long introducedTime) {

		while (true) {
			String input = scan.next();
			try {
				long discontinuedTime = DateMapper.getDate(input);
				if ((discontinuedTime > 0 && discontinuedTime > introducedTime)) {
					return discontinuedTime;
				} else {
					logger.warn("Can't be before introduction Time \nRe-enter Discontinued Time :");
				}
			} catch (NullPointerException e) {
				logger.warn("Wrong timestamp Format... Try again :");
			}
		}
	}

	public long consoleUpdateIntroduced() {

		while (true) {
			String input = scan.next();
			try {
				long inputTime = DateMapper.getDate(input);
				if (inputTime > 0 || input.equals("0")) {
					return inputTime;
				} else if (input.equals("1")) {
					return -1;
				}
			} catch (NullPointerException e) {
				logger.warn("Wrong timestamp Format... Try again :");
			}
		}
	}

	public long consoleUpdateDiscontinued(long introducedTime) {

		while (true) {
			String input = scan.next();
			try {
				long discontinuedTime = DateMapper.getDate(input);
				if ((discontinuedTime > 0 && discontinuedTime > introducedTime)) {
					return discontinuedTime;
				} else if (input.equals("1")) {
					return -1;
				} else {
					logger.warn("Can't be before introduction Time \nRe-enter Discontinued Time :");
				}
			} catch (NullPointerException e) {
				logger.warn("Wrong timestamp Format... Try again :");
			}
		}
	}
}
