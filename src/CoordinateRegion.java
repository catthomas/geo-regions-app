
/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * A Class which generates CoordinateRegion objects
 * A coordinate region is one which contains variables for
 * latitude, longitude, and elevation. 
 * Extends Region
 * @version 1.0
 */
public class CoordinateRegion extends Region{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = -1445311483704544760L;
	/** String representation of the region's latitude. */
	private String latitude;
	/** String representation of the region's longitude. */
	private String longitude; 
	/**Double representation of the region's distance from sea level. */
	private double elevation;
	
	/**
	 * Default constructor for a coordinate region. 
	 */
	public CoordinateRegion(){
		super();
	} // end CoordinateRegion
	
	/**
	 * Constructor with parameters for a coordinate region. 
	 * @param regionName String representing the region's name. 
	 * @param latitude String representing the region's latitude.
	 * @param longitude String representing the region's longitude. 
	 * @param elevation Double representing the region's elevation.
	 */
	public CoordinateRegion(String regionName, String latitude, String longitude,
			double elevation){
		super(regionName);
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
	} // end CoordinateRegion with parameters
	
	
	/**
	 * An accessor method for the region's latitude.
	 * @return String representation of the region's latitude. 
	 */
	public String getLatitude(){
		return latitude;
	} // end getLatitude
	
	/**
	 * An accessor method for the region's longitude. 
	 * @return String representation of the region's longitude.
	 */
	public String getLongitude(){
		return longitude;
	} // end getLongitude
	
	/**
	 * An accessor method for the region's elevation.
	 * @return Double of region's height above sea level.
	 */
	public double getElevation(){
		return elevation;
	} // end getElevation
	
	
	/**
	 * A mutator method for the region's latitude.
	 * @param latitude New latitude for the region. 
	 */
	public void setLatitude(String latitude){
		this.latitude = latitude;
	} // end setLatitude
	
	/**
	 * A mutator method for the region's longitude.
	 * @param longitude New longitude for the region. 
	 */
	public void setLongitude(String longitude){
		this.longitude = longitude;
	} // end setLongitude
	
	/**
	 * A mutator method for the region's elevation.
	 * @param elevation New elevation of the region. 
	 */
	public void setElevation(double elevation){
		this.elevation = elevation;
	} // end setElevation
	
	/**
	 * Returns the region as a String.
	 * @return String representation of the coordinate region. 
	 */
	@Override
	public String toString(){
		return super.toString() + ", " + latitude + ", " + longitude
		+ ", " + elevation;
	} // end toString
} // end CoordinateRegion Class
