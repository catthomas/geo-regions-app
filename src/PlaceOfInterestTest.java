import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the PlaceOfInterest class.
 */
public class PlaceOfInterestTest {

	@Test
	public void testGetTypeOfPlace() {
		PlaceOfInterest testPlace = new PlaceOfInterest("Test Place", 100, "lake");
		
		Assert.assertEquals("lake", testPlace.getTypeOfPlace());
	} // end testGetTypeOfPlace

	@Test
	public void testAddPoliticalRegion() {
		PlaceOfInterest testPlace = new PlaceOfInterest();
		
		testPlace.addPoliticalRegion(new Country("test", 100, 100));
		
		Assert.assertEquals(true, testPlace.hasPoliticalRegion("test"));
	} // end testAddPoliticalRegion

	@Test
	public void testToString(){
		PlaceOfInterest testPlace = new PlaceOfInterest();
		
		testPlace.addPoliticalRegion(new Country("test", 100, 100));
		testPlace.addPoliticalRegion(new Country("test2", 100, 100));
		
		System.out.println(testPlace.toString()); // looking at format
		
		// TODO: add assert statement
	}
} // end PlaceOfInterestTest
