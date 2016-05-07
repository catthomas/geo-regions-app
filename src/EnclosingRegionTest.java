

import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the EnclosingRegion class. 
 *
 */
public class EnclosingRegionTest {

	@Test
	public void testAreaComparatorEquals() {
		EnclosingRegion country1 = new Country("country1", 12.0, 19);
		EnclosingRegion country2 = new Country("country2", 12.0, 19);
		
		int result = EnclosingRegion.areaComparator.compare(country1,country2);
		
		// Tests equals characteristic
		Assert.assertEquals(0,result,0);
	} // end testAreaComparatorEquals
	
	@Test
	public void testAreaComparatorLessThan(){
		EnclosingRegion country1 = new Country("country1", 12.0, 19);
		EnclosingRegion country2 = new Country("country2", 13.0, 19);
		
		int result = EnclosingRegion.areaComparator.compare(country1, country2);
		
		// Test less than characteristic
		Assert.assertEquals(-1, result, 0);
	
	} // end testAreaComparatorLessThan
	
	@Test
	public void testAreaComparatorGreaterThan(){
		EnclosingRegion country1 = new Country("country1", 12.0, 19);
		EnclosingRegion country2 = new Country("country2", 11.0, 19);
		
		int result = EnclosingRegion.areaComparator.compare(country1, country2);
		
		// Test greater than characteristic
		Assert.assertEquals(1, result, 0);
	} // end testAreaComparatorGreaterThan

}
