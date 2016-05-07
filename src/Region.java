import java.io.Serializable;
import java.util.ArrayList;


/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * A super class for geographic regions.
 * Contains properties and methods common to all region objects
 * Implements Serializable and Comparable.
 * @version 1.0
 */
public class Region implements Comparable<Region>, Serializable{

	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 7545304986459584771L;
	
	/** A string containing the region's name */
	private String regionName;
	/** List of all points of interest in the Region */
	private ArrayList<PointOfInterest> pointOfInterestList;
	
	/**
	 * A Constructor for region objects with no initial values
	 */
	public Region(){
		pointOfInterestList = new ArrayList<PointOfInterest>();
	} // end Region 
	
	/**
	 * A Constructor for region objects with known values
	 * 
	 * @param regionName	The regions name
	 */
	public Region(String regionName){
		this.regionName = regionName;
		pointOfInterestList = new ArrayList<PointOfInterest>();
	} // end Region constructor with parameters
	
	/**
	 * A getter that returns the region's name.
	 * 
	 * @return A String containing the region's name.
	 */
	public String getName(){
		return regionName;
	} // end getName	
	
	/**
	 * An accessor for the list of points of interest the
	 * EnclosingRegion contains. 
	 * 
	 * @return List of points of interest in region. 
	 */
	public ArrayList<PointOfInterest> getPointOfInterestList(){
		return pointOfInterestList;
	} // end getPointOfInterestList

	/**
	 * A mutator that sets the value of the region's name.
	 * 
	 * @param regionName	The value that name is to be set to.
	 */
	public void setName(String regionName){
		this.regionName = regionName;
	} // end setName
	
	/**
	 * A mutator for the list of points of interest in the 
	 * Region. 
	 * @param pointOfInterestList New points of interest list. 
	 */
	public void setPointOfInterestList(ArrayList<PointOfInterest> pointOfInterestList){
		this.pointOfInterestList = pointOfInterestList;
	} /// end setPointOFInterestList
	
	/**
	 * Adds on a new points of interest to the list of 
	 * points of interest the Region contains. 
	 * @param point PointOfInterest to be added. 
	 * @return True if point of interest list was modified and 
	 * false otherwise. 
	 */
	public boolean addPointOfInterest(PointOfInterest point){
		return pointOfInterestList.add(point);
	} // end addPointOfInterest
	
	/**
	 * Removes a point of interest from the current list of
	 * points of interest the Region contains. 
	 * @param pointName Name of the point of interest to be removed. 
	 * @return True if list was modified and false otherwise. 
	 */
	public boolean removePointOfInterest(String pointName){
		//Search for point with specified name
		for(PointOfInterest point: pointOfInterestList){
			// Remove point if name matches
			if(point.getName().equals(pointName)){
				return pointOfInterestList.remove(point);
			}
		}
		
		return false; // name was never found
	} // end removePointOfInterest
	

	/**
	 * Chcecks to see if the EnclosingRegion has a specified point of interest.
	 * @param pointName Name of the searched for point of interest. 
	 * @return True if the point of interest is contained and false otherwise. 
	 */
	public boolean hasPointOfInterest(String pointName){
		//Search for point with specified name
		for(PointOfInterest point: pointOfInterestList){
			// Return true if point name matches
			if(point.getName().equals(pointName)){
				return true;
			}
		}
		
		return false; // name was never found
	} // end hasPointOfInterest
	
	/**
	 * A comparable which specifies the natural ordering of Region objects.
	 * The natural order is lexicographic based on the name of the Region.
	 * 
	 * @param o	The Region object to be compared with this region.
	 * 
	 * @return	Returns an int value of -1 if less than, 0 if equal, and 1 if greater than
	 */
	@Override
	public int compareTo(Region o) {
		/** Default comparison is lexicographically */
		if(this.getName().compareToIgnoreCase(o.getName()) > 0){
			return 1;
		}
		else if(this.getName().equals(o.getName())){
			return 0;
		}
		else{
			return -1;
		}
	} // end compareTo
	
	/**
	 * A method which returns the value of a Region object as a string
	 * 
	 * @return A string containing the values of a Region Object
	 */
	@Override
	public String toString(){
		return regionName;
	} // end toString

} // end Region class
