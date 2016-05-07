import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the PoliticalRegion class.
 *
 */
public class PoliticalRegionTest {
	@Test
	public void testPopulationComparatorEquals() {
		Country country1 = new Country("country1", 12.0, 19);
		Country country2 = new Country("country2", 11.0, 19);
		
		int result = PoliticalRegion.populationComparator.compare(country1,country2);
		
		// Tests equals characteristic
		Assert.assertEquals(0,result,0);
	} // end testPopulationComparatorEquals
	
	@Test
	public void testPopulationComparatorLessThan(){
		Country country1 = new Country("country1", 12.0, 18);
		Country country2 = new Country("country2", 12.0, 19);
		
		int result = PoliticalRegion.populationComparator.compare(country1, country2);
		
		// Test less than characteristic
		Assert.assertEquals(-1, result, 0);
	
	} // end testPopulationComparatorLessThan
	
	@Test
	public void testPopulationComparatorGreaterThan(){
		Country country1 = new Country("country1", 12.0, 20);
		Country country2 = new Country("country2", 12.0, 19);
		
		int result = PoliticalRegion.populationComparator.compare(country1, country2);
		
		// Test greater than characteristic
		Assert.assertEquals(1, result, 0);
	} // end testPopulationComparatorGreaterThan
} // end PoliticalRegionTest
