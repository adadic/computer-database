package mapper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ArrayMapperTest {

	@Test
	public void testToArray() {
		assertEquals(true, Arrays.equals(ArrayMapper.toArray("{id:1, name:Apple Inc.}"), new String[] {"1", "Apple Inc."}));
	}

}
