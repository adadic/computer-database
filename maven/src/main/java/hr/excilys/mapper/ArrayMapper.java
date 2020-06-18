package hr.excilys.mapper;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public final class ArrayMapper {

	public static List<String> stringTransform(String selected) {

		return Arrays.asList(selected.split(","));
	}

}
