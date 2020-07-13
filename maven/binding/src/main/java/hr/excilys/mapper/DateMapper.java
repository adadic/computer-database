package hr.excilys.mapper;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public final class DateMapper {

	public long getDate(String givenDate) throws IllegalArgumentException {

		return Timestamp.valueOf(givenDate + " 00:00:00").getTime();
	}
}
