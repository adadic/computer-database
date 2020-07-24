package hr.excilys.mapper;

import org.springframework.stereotype.Component;

@Component
public class IntMapper {

	public static long getId(String id) {
		if (!id.equals("0")) {
			return Long.valueOf(id);
		}
		return 0L;
	}
	
}
