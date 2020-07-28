package hr.excilys.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public final class DateMapper {

	public LocalDate getDate(String givenDate) throws DateTimeParseException {

		if (StringUtils.isEmpty(givenDate)) {
			
			return null;
		}
		
		return LocalDate.parse(givenDate);
	}
}
