import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the Continent class.
 *
 */
public class ContinentTest {

	@Test
	public void testContinentStringDoubleInt() {
		Continent testContinent = new Continent("testContinent", 100.0, 4);
		
		Assert.assertEquals(100.0, testContinent.getArea(), 0.0);
	} // end testContinentStringDoubleInt

	@Test
	public void testRemoveCountry() {
		Continent testContinent = new Continent();
		Country testCountry = new Country("removeMe", 0.0, 100);
		
		testContinent.addCountry(testCountry); // add country to the Continent object
		
		Assert.assertEquals(true, testContinent.removeCountry("removeMe"));
	} // end testRemoveCountry

	@Test
	public void testCompareTo() {
		Continent testContinent = new Continent();
		
		// Test to see if the testCity is equal to itself
		Assert.assertEquals(0, testContinent.compareTo(testContinent), 0);
	} // end testCompareTo

} // end ContinentTest class
