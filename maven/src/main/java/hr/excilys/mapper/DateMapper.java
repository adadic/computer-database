package hr.excilys.mapper;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class DateMapper {

	public static long getDate(String givenDate) {

		try {
			Timestamp ts = Timestamp.valueOf(givenDate + " 00:00:00");

			return ts.getTime();
		} catch (IllegalArgumentException e) {

			return 0;
		}
	}
}
