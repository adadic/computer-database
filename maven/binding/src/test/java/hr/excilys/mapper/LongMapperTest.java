package hr.excilys.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class LongMapperTest {

	private LongMapper longMapper = new LongMapper();
	
	@Test
	public void getIdTest() {
		
		try {
			longMapper.getId("blablou");
			fail();
		} catch (NumberFormatException nfe) {
			assert(true);
			assertEquals(5L, longMapper.getId("5"));
		}
		
	}
	
}
