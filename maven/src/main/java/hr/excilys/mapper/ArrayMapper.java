package hr.excilys.mapper;

import java.util.Arrays;
import java.util.List;

public class ArrayMapper {

	public static List<String> stringTransform(String selected) {

		return Arrays.asList(selected.split(","));
	}

}
