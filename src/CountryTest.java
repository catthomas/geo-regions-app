import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the Country class.
 *
 */
public class CountryTest {

	@Test
	public void testCountryStringDoubleInt() {
		Country testCountry = new Country("countryName", 20.0, 12);
		
		// Checks construct with getArea method
		Assert.assertEquals(20.0,testCountry.getArea(), 0.0);
	} // end testCountryStringDoubleInt

	@Test
	public void testAddCity() {
		Country testCountry = new Country();
		City testCity = new City("cityName", 13.0,13);
		
		testCountry.addCity(testCity);
		
		// Checks if country now contains given city
		Assert.assertEquals(true, testCountry.hasCity("cityName"));
	} // end testAddCity

	@Test
	public void testCompareTo() {
		Country testCountry = new Country();
		
		// Test to see if the testCity is equal to itself
		Assert.assertEquals(0, testCountry.compareTo(testCountry), 0);
	} // end testCompareTo

} // end CountryTest