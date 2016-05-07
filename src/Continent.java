import java.util.ArrayList;

/**
 * Project #4
 * CS 2334, Section 10
 * March 7, 2014
 * A Class which generates Continent objects
 * Extends PoliticalRegion 
 * @version 1.0
 */
public class Continent extends PoliticalRegion{
	
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = -3812487147173105419L;
	/* An ArrayList which contains the countries of each individual continent */
	private ArrayList<Country> countryList;
	
	/**
	 * Constructor for continent objects. 
	 * Adds continent to ArrayList of allContinents.
	 */
	public Continent(){
		super();
		countryList = new ArrayList<Country>();	
	} // end Continent
	
	/**
	 * Constructor for continent objects which takes
	 * in parameters.
	 * Adds continent to ArrayList of allContinents.
	 * 
	 * @param continentName String indicating name of continent
	 * @param area Double indicating area of continent
	 * @param population Long indicating population of the continent
	 */
	public Continent(String continentName, double area, long population){
		super(continentName, population, area);
		countryList = new ArrayList<Country>();
	} // end Continent with parameters
	
	/**
	 * A getter which returns an ArrayList containing the value of countryList
	 * 
	 * @return	An arrayList whose value is equal to countryList
	 */
	public ArrayList<Country> getCountryList(){
		return countryList;
	} // end getCountryList
	
	/**
	 * A mutator which sets the value of countryList
	 * 
	 * @param countryList An ArrayList containing Country Objects
	 */
	public void setCountryList(ArrayList<Country> countryList){
		this.countryList = countryList;
	} // end setCountryList
	
	/**
	 * A mutator which adds a Country object to the end of countryList
	 * 
	 * @param country  The country object to be added
	 * 
	 * @return	A boolean which returns true if the object was added to the end of countryList
	 * and false otherwise
	 */
	public boolean addCountry(Country country){
		return countryList.add(country);
	} // end addCountry
	
	/**
	 * A mutator which removes a Country from countryList
	 * 
	 * @param countryName The value of name of the country to be removed
	 * 
	 * @return A boolean which returns true if the Country ArrayList was modified
	 * and false otherwise
	 */
	public boolean removeCountry(String countryName){
		for(Country country: countryList){
			if(country.getName().equals(countryName)){
				return countryList.remove(country);
			}
		}
		return false;
	} // end removeCountry

	/**
	 * Checks to see if the continent contains a given country
	 * in its ArrayList of countries
	 * 
	 * @param countryName String indicating name of searched for country
	 * @return True if found and false otherwise
	 */
	public boolean hasCountry(String countryName){
		for(Country country: countryList){
			if(country.getName().equals(countryName)){
				return true;
			}
		}
		return false;
	} // end hasCountry
	
	/**
	 * Returns the value of the Continent object as a String
	 * 
	 * @return A String containing the values of the Continent object
	 */
	@Override
	public String toString(){
		return super.toString();
	} // end toString
	
} // end Continent class
