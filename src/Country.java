import java.util.ArrayList;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * A Class which generates Country objects
 * Extends PoliticalRegion 
 * @version 1.0
 */
public class Country extends PoliticalRegion{
	
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = -5810222752358972328L;
	/** An ArrayList Containing an individual Country object's list of cities. */
	private ArrayList<City> cityList;
	/** A Continent that the Country is located on */
	private Continent continentLocation;
	
	/**
	 * A constructor of Country objects
	 * Adds country to ArrayList of all country objects
	 */
	public Country(){
		super();
		cityList = new ArrayList<City>();
	} // end Country
	
	/**
	 * A Constructor of Country objects which takes
	 * in parameters
	 * Adds country to ArrayList of all country objects
	 * 
	 * @param countryName String indicating name of country
	 * @param area Double indicating area of country
	 * @param population Long of the total population of the country
	 */
	public Country(String countryName, double area, long population){
		super(countryName, population, area);
		cityList = new ArrayList<City>();
	} // end Country with parameters
	
	/**
	 * A getter which returns a Country object's list of cities
	 * 
	 * @return		An ArrayList equal to cityList
	 */
	public ArrayList<City> getCityList(){
		return cityList;
	} // end getCityList
	
	/**
	 * Accessor method for the continent location of the Country.
	 * @return Continent the Country is located on.
	 */
	public Continent getContinentLocation(){
		return continentLocation;
	} // end getContinentLocation
	
	/**
	 * Mutator method to change the continent location of the Country.
	 * @param continentLocation Continent's name. 
	 */
	public void setContinentLocation(Continent continentLocation){
		this.continentLocation = continentLocation;
	} // end setContinentLocation
	
	/**
	 * A mutator which sets the value of cityList
	 * 
	 * @param cityList	The ArrayList which the value of cityList is to be set to
	 */
	public void setCityList(ArrayList<City> cityList){
		this.cityList = cityList;
	} // end setCityList
	
	/**
	 * A mutator that adds a City object to the end of cityList
	 * 
	 * @param city	The city object to be added
	 * 
	 * @return		A boolean value which returns true if the City object was added to cityList
	 * and false otherwise
	 */
	public boolean addCity(City city){
		return cityList.add(city);
	} // end addCity
	
	/**
	 * A mutator which removes a City object from cityList
	 * 
	 * @param cityName The value of name of the City object to be removed
	 * 
	 * @return		A boolean value which returns true if the City object was removed from cityList
	 * and false otherwise
	 */
	public boolean removeCity(String cityName){
		for(City city: cityList){
			if(city.getName().equals(cityName)){
				return cityList.remove(city);
			}
		}
		return false;
	} // end removeCity
	
	/**
	 * Checks to see if the country contains a given city
	 * in its ArrayList of cities
	 * 
	 * @param cityName String indicating name of searched for city
	 * @return True if found and false otherwise
	 */
	public boolean hasCity(String cityName){
		for(City city: cityList){
			if(city.getName().equals(cityName)){
				return true;
			}
		}
		return false;
	} // end hasCity
	
	/**
	 * returns a string containing the values of a Country object
	 * 
	 * @return a String containing the values of the Country object
	 */
	@Override
	public String toString(){
		return super.toString();
	} // end toString
	
} // end Country class
