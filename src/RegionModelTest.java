import org.junit.Assert;
import org.junit.Test;

/**
 * Implements JUnit testing for the RegionModel class. 
 *
 */
public class RegionModelTest {

	@Test
	public void testDeleteContinent() {
		RegionModel model = new RegionModel();
		//Continent to be added and then deleted
		Continent testContinent = new Continent("name", 10, 10);
		
		//Add continent
		model.addContinent(testContinent);
		//Remove continent
		model.deleteContinent(testContinent);
		
		//Make sure allContinents in model is now size 0
		Assert.assertEquals(0, model.getAllContinents().size());
	} // end testDeleteContinent
	
	@Test
	public void testEditContinent(){
		RegionModel model = new RegionModel();
		
		//Original continent
		Continent originalContinent = new Continent("hello",10,20);
		//Add continent to model
		model.addContinent(originalContinent);
		
		//Replacement continent
		Continent replacementContinent = new Continent("replacement",10,30);
		
		model.editContinent(0, replacementContinent);
		
		//Check original continent was replaced
		Assert.assertEquals(replacementContinent, model.getAllContinents().get(0));
	} // end testEditContinent
	
	@Test
	public void testAddCountry(){
		RegionModel model = new RegionModel();
		
		//Make a continent for the country to be located on
		Continent continent = new Continent("meow", 10, 20);
		//Make country to be added
		Country country = new Country("hello",5,5);
		country.setContinentLocation(continent);
		
		//Add continent to model
		model.addContinent(continent);
		
		//Add country to model
		model.addCountry(country);
		
		Assert.assertEquals(continent, model.getAllCountries().get(0).getContinentLocation());
	} // end testAddCountry

} // end RegionModelTest
