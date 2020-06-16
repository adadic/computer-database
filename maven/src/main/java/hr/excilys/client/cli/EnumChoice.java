package hr.excilys.client.cli;

public enum EnumChoice {
	QUIT, COMPUTER_LIST, COMPANY_LIST, COMPUTER_DETAIL, CREATE_COMPUTER, UPDATE_COMPUTER, DELETE_COMPUTER, ERROR;

	public static EnumChoice testChoice(int entry) {

		switch (entry) {
		case 0:
			return QUIT;

		case 1:
			return COMPUTER_LIST;

		case 2:
			return COMPANY_LIST;

		case 3:
			return COMPUTER_DETAIL;

		case 4:
			return CREATE_COMPUTER;

		case 5:
			return UPDATE_COMPUTER;

		case 6:
			return DELETE_COMPUTER;

		default:
			return ERROR;
		}
	}
}
