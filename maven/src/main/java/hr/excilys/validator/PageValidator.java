package hr.excilys.validator;

import hr.excilys.dto.DTOPagination;
import hr.excilys.model.Pagination;

public class PageValidator {

	public static final int MINPAGE = 1;
	public static final int MINLINE = 10;
	public static final int MAXLINE = 100;
	public static final int INITDIRECTION = 0;
	public static final int DIRECTION = 1;
	public static final boolean ERROR = true;

	public static boolean checkString(DTOPagination dtoPage) {

		try {
			Integer.valueOf(dtoPage.getPage());
			@SuppressWarnings("unused")
			int x = 1 / Integer.valueOf(dtoPage.getLines());
			Integer.valueOf(dtoPage.getDirection());

			return true;
		} catch (NumberFormatException | ArithmeticException e) {

			return false;
		}
	}

	public static void checkPage(Pagination page) {

		if (page.getLines() < MINLINE) {
			page.setLines(MINLINE);
			page.setMaxPage(page.getCount() / MINLINE);
		}
		if (page.getLines() > MAXLINE) {
			page.setLines(MAXLINE);
			page.setMaxPage(page.getCount() / MAXLINE);
		}

		if (page.getPage() < MINPAGE) {
			page.setPage(MINPAGE);
		}
		if (page.getPage() > page.getMaxPage()) {
			page.setPage(page.getMaxPage());
		}

		if (page.getDirection() != INITDIRECTION && page.getDirection() != DIRECTION) {
			page.setDirection(INITDIRECTION);
		}
	}

}
