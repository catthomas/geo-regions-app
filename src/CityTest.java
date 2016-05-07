import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the City class.
 *
 */
public class CityTest {

	@Test
	public void testCityStringStringDouble() {
		String name = "testCity";
		double area = 100;
		int population = 200;
				
		City testCity = new City(name, area, population); //Create city object with test values
		Assert.assertEquals("testCity", testCity.getName());

	} // end testCountryStringIntIntString
	
	@Test
	public void testSetLatitude(){
		City testCity = new City("hello", 100,100, "30N", "30W", 20.0);
		
		Assert.assertEquals("30N", testCity.getLatitude());
	} // end testSetLatitude
	
	@Test
	public void testCompareTo(){
		City testCity = new City("City", 100, 100, "30N", "20W", 10.0);
		
		// Test to see if the testCity is equal to itself
		Assert.assertEquals(0, testCity.compareTo(testCity));
	} // end CompareTo

	
} // end CityTest
