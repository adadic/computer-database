package hr.excilys.mapper;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public final class ArrayMapper {

	public List<String> stringTransform(String selected) {

		if (StringUtils.isNotEmpty(selected)) {

			return Arrays.asList(selected.split(","));
		}

		return Arrays.asList();
	}

}
