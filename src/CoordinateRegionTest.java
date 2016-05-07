import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the 
 * CoordinateRegion class.
 *
 */
public class CoordinateRegionTest {

	@Test
	public void setElevationTest() {
		//Create new coordinate region
		CoordinateRegion test = new CoordinateRegion();
		
		//Set test elevation
		test.setElevation(10.1);
		
		//Make test statement
		Assert.assertEquals(10.1, test.getElevation(), 0);
	} // end setElevationTest
	
	@Test
	public void getLatitudeTest() {
		//Create new coordinate region
		CoordinateRegion test = new CoordinateRegion("name", 
				"N10", "W10", 10.0);
		
		Assert.assertEquals("N10", test.getLatitude());
	} // end getLatitudeTest
} // end CoordinateRegionTest class
