/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * Interface to contain all actions that a 'mappable' region
 * should be able to perform. A 'mappable' region is one that has 
 * coordinates. 
 * @version 1.0
 */
public interface Mappable<T> {
	/**
	 * An accessor method for the region's name
	 * @return String representation of the region's name.
	 */
	public String getName(); // end getName
	
	
	/**
	 * An accessor method for the region's latitude.
	 * @return String representation of the region's latitude. 
	 */
	public String getLatitude(); // end getLatitude
	
	/**
	 * An accessor method for the region's longitude. 
	 * @return String representation of the region's longitude.
	 */
	public String getLongitude(); // end getLongitude
	
	/**
	 * An accessor method for the region's elevation.
	 * @return Double of region's height above sea level.
	 */
	public double getElevation(); // end getElevation

	/**
	 * A mutator method for the region's latitude.
	 * @param latitude New latitude for the region. 
	 */
	public void setLatitude(String latitude); // end setLatitude
	
	/**
	 * A mutator method for the region's longitude.
	 * @param longitude New longitude for the region. 
	 */
	public void setLongitude(String longitude); // end setLongitude
	
	/**
	 * A mutator method for the region's elevation.
	 * @param elevation New elevation of the region. 
	 */
	public void setElevation(double elevation); // end setElevation
	
	/**
	 * Returns the region as a String.
	 * @return String representation of the coordinate region. 
	 */
	@Override
	public String toString(); // end toString
}
