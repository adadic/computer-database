package validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dto.DTOPagination;
import hr.excilys.model.Pagination;

@Component
public class PageValidator {

	public static final int MINPAGE = 1;
	public static final int MINLINE = 10;
	public static final int MAXLINE = 100;
	public static final int INITDIRECTION = 0;
	public static final int DIRECTION = 1;
	public static final boolean ERROR = true;
	private final static Logger LOGGER = LoggerFactory.getLogger(PageValidator.class);

	public boolean checkPage(DTOPagination dtoPage) {

		try {
			Integer.valueOf(dtoPage.getPage());
			@SuppressWarnings("unused")
			int x = 1 / Integer.valueOf(dtoPage.getLines());
			Integer.valueOf(dtoPage.getDirection());
			LOGGER.info("Page can be created");

			return true;
		} catch (NumberFormatException nfe) {
			LOGGER.error("A String was given instead of number, page : {} and lines : {}", dtoPage.getPage(),
					dtoPage.getLines());

			return false;
		} catch (ArithmeticException arite) {
			LOGGER.error("Lines equals 0, can't do that!");

			return false;
		}
	}

	public void checkPageFields(Pagination page) {

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
