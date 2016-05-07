
/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * The view to add/edit a City.
 * @version 1.0
 */
public class PointOfInterest extends Region implements Mappable<PointOfInterest>{
		/**
	 	* Unique ID in order for this class to be written to files
	 	* directly as an object.
	 	*/	
		private static final long serialVersionUID = -6921819509939688332L;
		/** Short description of the type of point (statue, etc.) */
		private String pointType;
		/** The geographic region on which the point of interest is located. */
		private Region regionLocation;
		/**Variable to contain coordinates and geographic neighborhoods of this region. */
		CoordinateRegion coordinates = new CoordinateRegion();
		
	/**
	 * Default construct for a point of interest. 
	 */
	public PointOfInterest(){
		super();
	} // Default point of interest constructor
	
	/**
	 * Constructor with parameters for a point of interest. 
	 * @param name Name of the point. 
	 * @param pointType Brief description of what the point of interest is. 
	 * @param latitude String representation of the point's latitude. 
	 * @param longitude String representation of the point's longitude.
	 * @param elevation The point's height above sea level. 
	 */
	public PointOfInterest(String name, String pointType, String latitude,
			String longitude, double elevation){
		super(name);
		this.pointType = pointType;
		coordinates.setLatitude(latitude);
		coordinates.setLongitude(longitude);
		coordinates.setElevation(elevation);
	} // Constructor of point of interest with parameters
	
	/**
	 * An accessor method for the point's type.
	 * @return The type of the point of interest. 
	 */
	public String getType(){
		return pointType;
	} // end getType
	
	/**
	 * An accessor method for the coordinate region's latitude.
	 */
	@Override
	public String getLatitude() {
		return coordinates.getLatitude();
	} // end getLatitude

	/**
	 * An accessor method for the coordinate region's longitude. 
	 */
	@Override
	public String getLongitude() {
		return coordinates.getLongitude();
	} // end getLongitude

	/**
	 * An accessor method for the coordinate region's elevation.
	 */
	@Override
	public double getElevation() {
		return coordinates.getElevation();
	} // end getElevation
	
	/**
	 * An accessor method for the point's region location.
	 * @return Region that the point is located on.
	 */
	public Region getRegionLocation(){
		return regionLocation;
	} // end getRegionLocation
	
	/**
	 * An accessor method for the point's coordinates.
	 * @return CoordinateRegion that contains all mapping information. 
	 */
	public CoordinateRegion getCoordinates(){
		return coordinates;
	} // end getCoordinates
	
	/**
	 * An mutator method for the point's coordinates.
	 * @param coordinates New CoordinateRegion that contains all mapping information. 
	 */
	public void setCoordinates(CoordinateRegion coordinates){
		this.coordinates = coordinates;
	} // end getCoordinates

	/**
	 * A mutator method for the coordinate regions' latitude. 
	 */
	@Override
	public void setLatitude(String latitude) {
		coordinates.setLatitude(latitude);
	} // end setLatitude

	/**
	 * A mutator method for the coordinate region's longitude. 
	 */
	@Override
	public void setLongitude(String longitude) {
		coordinates.setLongitude(longitude);
	} // end setLongitude

	/**
	 * A mutator method for the coordinate region's elevation. 
	 */
	@Override
	public void setElevation(double elevation) {
		coordinates.setElevation(elevation);
	} // end setElevation
	
	/**
	 * A mutator method for the point's type.
	 * @param pointType New point type.
	 */
	public void setType(String pointType){
		this.pointType = pointType;
	} // end setType
	
	/**
	 * A mutator method for the point's region location.
	 * @param regionLocation New region location of the point. 
	 */
	public <E extends Region> void setRegionLocation(E regionLocation){
		this.regionLocation = regionLocation;
	} // end setRegionLocationName
	
	/**
	 * Returns the point of interest as a String.
	 * @return String representation of the point of interest. 
	 */
	@Override
	public String toString(){
		return this.getName() + ", " +  pointType + ", " + coordinates.getLatitude() + ", " + 
		coordinates.getLongitude() + ", " + coordinates.getElevation() + ", " + regionLocation.getName();
	} // end toString

} // end point of interest class
