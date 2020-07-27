package hr.excilys.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongMapperTest {

	private LongMapper longMapper = new LongMapper();
	
	@Test
	public void testGetIdInt() {
		
		assertEquals(5L, longMapper.getId("5"));
		
	}
	
	@Test(expected = NumberFormatException.class)
	public void testGetIdLong() {
		
		assertEquals(5L, longMapper.getId("5L"));
		
	}
	
	@Test(expected = NumberFormatException.class)
	public void testGetIdString() {
		
		longMapper.getId("blablou");
	}
	
	@Test(expected = NumberFormatException.class)
	public void testGetIdNull() {

		longMapper.getId(null);
	}
	
}
