import java.util.Comparator;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * A Class which generates Enclosing objects
 * Extends Region 
 * The purpose of this class is to delineate between types of 
 * Geographic Regions with significant area size and those that do 
 * not.
 * @version 1.0
 */

public class EnclosingRegion extends Region {
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 5135619454479529352L;
	/** A double containing the region's area */
	private double area;
	
	/**
	 * Default construct for an enclosing region
	 */
	public EnclosingRegion(){
		super();
	} // Default constructor for an enclosing region
	
	/**
	 * Constructor for an EnclosingRegion object with parameters.
	 * 
	 * @param regionName Name of EnclosingRegion.
	 * @param area Area of the EnclosingRegion.
	 */
	public EnclosingRegion(String regionName, double area){
		super(regionName);
		this.area = area;
	} // Constructor with parameters
	
	/**
	 * A getter that returns the region's area.
	 * 
	 * @return	A double containing the region's area
	 */
	public double getArea(){
		return area;
	} // end getArea
	
	/**
	 * A mutator that sets the value of area.
	 * 
	 * @param area	The value that area is to be set to.
	 */
	public void setArea(double area){
		this.area = area;
	} // end setArea
		
	/**
	 * desc
	 */
	public static Comparator<EnclosingRegion> areaComparator = new Comparator<EnclosingRegion>(){
		/**
		 * A comparator which compares the area of Region objects
		 * 
		 * @param o1  The first Region to be compared
		 * @param o2  The second Region to be compared
		 * 
		 * @return   An int value of -1 if the first Region's area is less than the second Region's,
		 * 0 if they have equal values, and 1 if the first Region's area is  greater than the second
		 */
		@Override
		public int compare(EnclosingRegion o1, EnclosingRegion o2) {
			if(o1.getArea() > o2.getArea()){
				return 1;
			}
			else if(o1.getArea() < o2.getArea()){
				return -1;
			}
			else{
				return 0;
			}
		} // end compare
	}; // end areaComparator
	
	
	/**
	 * Converts the EnclosingRegion to a string. 
	 */
	@Override
	public String toString(){
		//TODO: should list of points be added to this?
		return super.toString() +", " + area;
	} // end toString
} // end EnclosingRegion class
