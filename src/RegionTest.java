import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the Region class.
 *
 */
public class RegionTest {
	
	@Test
	public void testRegionStringDoubleInt() {
		Region testContinent = new Continent("regionName", 12.0, 19);
		
		// Tests the super construct held within abstract class region implicitly
		Assert.assertEquals("regionName", testContinent.getName());
	} // end testRegionStringDoubleInt
	
	@Test
	public void testSetName() {
		Region testCountry = new Country();
		
		testCountry.setName("testName");
		
		// Checks if name was change to testName
		Assert.assertEquals("testName", testCountry.getName());
	} // end testSetName

} // end RegionTest class