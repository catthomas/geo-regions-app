import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the nontrivial methods of the PointOfInterest class.
 *
 */
public class PointOfInterestTest {
	@Test
	public void testCompareTo(){
		PointOfInterest testPointOfInterest = new PointOfInterest("name", "monument",
				"N10", "W10", 100);
		
		// Test to see if the testPointOfInterest is equal to itself
		Assert.assertEquals(0, testPointOfInterest.compareTo(testPointOfInterest));
	} // end CompareTo

	@Test
	public void testGetType(){
		PointOfInterest testPointOfInterest = new PointOfInterest();
		
		testPointOfInterest.setType("monument");
		
		Assert.assertEquals("monument", testPointOfInterest.getType());
	} // end testGetType
} // end PointOfInterestTest
