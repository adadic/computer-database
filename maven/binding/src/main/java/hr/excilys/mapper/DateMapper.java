package hr.excilys.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

@Component
public final class DateMapper {

	public LocalDate getDate(String givenDate) throws DateTimeParseException {

		return LocalDate.parse(givenDate);
		
	}
}
