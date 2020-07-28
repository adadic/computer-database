package hr.excilys.mapper;

import org.springframework.stereotype.Component;

@Component
public class LongMapper {

	public long getId(String id) {

		return Long.valueOf(id);
	}
}
