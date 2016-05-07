import java.util.Comparator;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * A Class which generates City objects
 * Extends PoliticalRegion 
 * Aggregation with Country
 * @version 1.0
 */
public class City extends PoliticalRegion implements Mappable<City>{
	
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 6991477106361903727L;
	/** A Country which contains the country the city is located on */
	private Country countryLocation;
	/** Variable to contain coordinate information about the city. */
	private CoordinateRegion coordinates = new CoordinateRegion();
	
	/**
	 * A constructor for City objects with no initial values
	 * Adds City to ArrayList of all city objects
	 */
	public City(){
		super();
	} // end default City constructor
	
	/**
	 * A constructor for City objects with known initial values 
	 * Adds City to ArrayList of all city objects
	 *
	 * @param cityName  A string indicating the name of the city
	 * @param area A double indication area of the city
	 * @param population An long indicating population of the city
	 */
	public City(String cityName, double area, long population){
		super(cityName, population, area);
	} // end City constructor with parameters
	
	
	/**
	 * A constructor for City objects with known initial values 
	 * Adds City to static ArrayList of all city objects
	 *
	 * @param latitude  A string indicating the latitude
	 * @param longitude A string indicating the longitude
	 * @param elevation A double indicating the elevation
	 */
	public City(String cityName, double area, long population, String latitude, String longitude, double elevation){
		super(cityName, population, area);
		coordinates.setLatitude(latitude);
		coordinates.setLongitude(longitude);
		coordinates.setElevation(elevation);
	} // end City constructor with parameters
	
	/**
	 * A getter which returns a String containing the latitude
	 * 
	 * @return	A String containing the latitude
	 */
	public String getLatitude(){
		return coordinates.getLatitude(); 
	} // end getLatitude
	
	/**
	 * A getter which returns a String containing the longitude
	 * 
	 * @return		A String containing the longitude	 
	 */
	public String getLongitude(){
		return coordinates.getLongitude(); 
	} // end getLongitude
	
	/**
	 * A getter which returns a double containing the elevation
	 * 
	 * @return		A double valued to the cities' elevation
	 */
	public double getElevation(){
		return coordinates.getElevation();
	} // end getElevation
	
	/**
	 * An accessor method for the Country Location of the City.
	 * @return Country the City is located on.
	 */
	public Country getCountryLocation(){
		return countryLocation;
	} // end getCountryLocation
	
	/**
	 * An accessor method for the coordinate information of the city.
	 * @return CoordinateRegion containing coordinate information.
	 */
	public CoordinateRegion getCoordinates(){
		return coordinates;
	} // end getCoordinates
	
	/**
	 * A mutator method for the coordinate information of the city.
	 * @param coordinates CoordinateRegion showing new coordinate information.
	 */
	public void setCoordinates(CoordinateRegion coordinates){
		this.coordinates = coordinates;
	}
	
	/**
	 * A mutator method for the Country Location of the City.
	 * @param countryLocation New Country the City is located on.
	 */
	public void setCountryLocation(Country countryLocation){
		this.countryLocation = countryLocation;
	} // end setCountryLocation
	
	/**
	 * A mutator which sets the value of latitude
	 * 
	 * @param latitude	The value latitude is to be set to	
	 */
	public void setLatitude(String latitude){
		coordinates.setLatitude(latitude);
	} // end setLatitude
	
	/**
	 * A mutator which sets the value of longitude
	 * 
	 * @param longitude	The value longitude is to be set to
	 */
	public void setLongitude(String longitude){
		coordinates.setLongitude(longitude);; 
	} // end setLongitude
	
	/**
	 * A mutator which sets the value of elevation
	 * 
	 * @param elevation	The value which elevation is to be set to.
	 */
	public void setElevation(double elevation){
		coordinates.setElevation(elevation);
	} // end setElevation
	
	/**
	 * Returns the value of a City object as a string
	 * 
	 * @return	A String containing the value of the object
	 */
	@Override
	public String toString(){
		if(coordinates.getLatitude() == null || coordinates.getLongitude() == null){
			return super.toString();
		}
		return super.toString() + ", " + coordinates.getLatitude() + ", " + coordinates.getLongitude()
				+ ", " + coordinates.getElevation();
	} // end toString
	
	/**
	 * Creates a latitude comparator for City objects
	 */
	public static Comparator<City> latitudeComparator = new Comparator<City>(){
		/**
		 * A comparator which compares the latitude of City objects
		 * 
		 * @param o1  The first City to be compared
		 * @param o2  The second City to be compared
		 * 
		 * @return   An int value of -1 if the first City is farther South than the second,
		 * 0 if they have equal values, and 1 if the first City is farther North than the second.
		 */
		@Override
		public int compare(City o1, City o2) {
			// First check to see if either or both are null, returning values based on that
			if(o1.getLatitude() == null && o2.getLatitude() == null){
				return 0;
			}
			else if(o1.getLatitude() == null){
				return -1;
			}
			else if(o2.getLatitude() == null){
				return 1;
			}
			
			/** Takes the degrees from latitude and stores it as a double */
			double o1degrees = Double.parseDouble(o1.getLatitude().substring(1));
			double o2degrees = Double.parseDouble(o2.getLatitude().substring(1));
			
			//Checks if latitudes are equals
			if(o1.getLatitude().equals(o2.getLatitude())){
				return 0;
			}
			//Check's if first city's latitude is farther north than the second city's
			else if(o1.getLatitude().contains("N") && o2.getLatitude().contains("S")){
				return 1;
			}
			else if(o1.getLatitude().contains("N") && o2.getLatitude().contains("N")){
				if(o1degrees > o2degrees){
					return 1;
				}
			}
			else if(o1.getLatitude().contains("S") && o2.getLatitude().contains("S")){
				if(o1degrees < o2degrees){
					return 1;
				}
			}
			
			//If it was neither equal or greater than, then return less than
			return -1;
			
		} // end compare
	}; // end latitudeComparator
} // end City class
