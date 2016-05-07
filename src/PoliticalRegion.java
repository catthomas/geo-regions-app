import java.util.ArrayList;
import java.util.Comparator;

/**
 * Project #4
 * CS 2334, Section 10
 * April 2, 2014
 * A Class which generates Political Region
 * Extends EnclosingRegion 
 * 
 * @version 1.0
 */
public class PoliticalRegion extends EnclosingRegion {
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 2208814490226451349L;
	
	/** A long containing the region's population */
	private long population;
	/** Contains places of interest held by region */
	private ArrayList<PlaceOfInterest> placesList;
	
	/**
	 * A constructor for political region objects with no
	 * initial values
	 */
	public PoliticalRegion(){
		placesList = new ArrayList<PlaceOfInterest>();
	} // end politicalRegion
	
	/**
	 * A Constructor for political region objects with known values
	 * 
	 * @param regionName	The regions name
	 * @param area	The regions area
	 * @param population	The regions population
	 */
	public PoliticalRegion(String regionName, long population, double area){
		super(regionName, area);
		this.population = population;
		placesList = new ArrayList<PlaceOfInterest>();
	} // end Region constructor with parameters
	
	/**
	 * An accessor which retrieves the value of population
	 * for the political region
	 * 
	 * @return Long of the population of the region.
	 */
	public long getPopulation(){
		return this.population;
	} // end getPopulation
	
	/**
	 * An accessor which retrieves the value of places list
	 * 
	 * @return arraylist of all places of interest.
	 */
	public ArrayList<PlaceOfInterest> getPlacesList(){
		return placesList;
	} // end getPlacesList
	
	/**
	 * A mutator which sets the value of population.
	 * 
	 * @param population The value population is to be set to.
	 */
	public void setPopulation(long population){
		this.population = population;
	} // end setPopulation
	
	/**
	 * A mutator which sets the value of places list.
	 * 
	 * @param placesList The value placesList is to be set to.
	 */
	public void setPlacesList(ArrayList<PlaceOfInterest> placesList){
		this.placesList = placesList;
	} // end setPlacesList
	
	/**
	 * Adds on a new places of interest to the list of 
	 * places of interest the Region contains. 
	 * @param place PlaceOfInterest to be added. 
	 * @return True if place of interest list was modified and 
	 * false otherwise. 
	 */
	public boolean addPlaceOfInterest(PlaceOfInterest place){
		return placesList.add(place);
	} // end addPointOfInterest
	
	/**
	 * Removes a place of interest from the current list of
	 * places of interest the Region contains. 
	 * @param placeName Name of the place of interest to be removed. 
	 * @return True if list was modified and false otherwise. 
	 */
	public boolean removePlaceOfInterest(String placeName){
		//Search for place with specified name
		for(PlaceOfInterest place: placesList){
			// Remove place if name matches
			if(place.getName().equals(placeName)){
				return placesList.remove(place);
			}
		}
		
		return false; // name was never found
	} // end removePointOfInterest
	
	/**
	 * A method which returns the value of a Region object as a string
	 * 
	 * @return A string containing the values of a Region Object
	 */
	@Override
	public String toString(){
		return this.getName() + ", " + population + ", " + this.getArea();
	} // end toString
	
	/**
	 * Contains methods to compare a political region based on population.
	 */
	public static Comparator<PoliticalRegion> populationComparator = new Comparator<PoliticalRegion>(){
		/**
		 * A comparator which compares the population of Region objects
		 * 
		 * @param o1  The first Region to be compared
		 * @param o2  The second Region to be compared
		 * 
		 * @return   An int value of -1 if the first Region's population is less than the second's,
		 * 0 if they have equal values, and 1 if the first Region's population is  greater than the second's
		 */
		@Override
		public int compare(PoliticalRegion o1, PoliticalRegion o2) {
			if(o1.getPopulation() > o2.getPopulation()){
				return 1;
			}
			else if(o1.getPopulation() < o2.getPopulation()){
				return -1;
			}
			else{
				return 0;
			}
		} // end compare
	}; // end PopulationComparator
} // end PoliticalRegion class