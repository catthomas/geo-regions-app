import java.util.ArrayList;

/**
 * Project #5
 * CS 2334, section 10
 * April 22, 2014
 * A class which generates placeOfInterest objects
 * Extends EnclosingRegion
 * Aggregation with PoliticalRegion
 */
public class PlaceOfInterest extends EnclosingRegion{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 7324259566977835626L;	
	/** A variable to store the type of the place of interest */
	private String typeOfPlace;
	/** A list of the political regions where the place of interest is located */
	private ArrayList<PoliticalRegion> politicalRegionList;
	
	/**
	 * The default constructor for a place of interest.
	 */
	public PlaceOfInterest(){
		politicalRegionList = new ArrayList<PoliticalRegion>();
	} // end PlaceOfInterest
	
	/**
	 * A constructor for a place of interest with specified parameters.
	 * 
	 * @param name String reflecting the name of the place of interest.
	 * @param area Double reflecting the area of the place of interest.
	 * @param typeOfPlace String stating the type of place of interest.
	 */
	public PlaceOfInterest(String name, double area, String typeOfPlace){
		super(name, area);
		this.typeOfPlace = typeOfPlace;
		politicalRegionList = new ArrayList<PoliticalRegion>();
	} // end PlaceOfInterest
	
	/**
	 * An accessor for the type of the place of interest
	 * 
	 * @return String of the type of the place of interest
	 */
	public String getTypeOfPlace(){
		return typeOfPlace;
	} // end getTypeOfPlace
	
	/**
	 * An accessor for the political regions containing the place of interest
	 * 
	 * @return ArrayList of political regions the place of interest is located on
	 */
	public ArrayList<PoliticalRegion> getPoliticalRegionList(){
		return politicalRegionList;
	} // end getPoliticalRegionList
	
	/**
	 * A mutator for the type of the place of interest
	 * 
	 * @param typeOfPlace String of the new place of interest type
	 */
	public void setTypeOfPlace(String typeOfPlace){
		this.typeOfPlace = typeOfPlace;
	} // end setTypeOfPlace
	
	/**
	 * A mutator for the list of political regions a place of interest is located
	 * 
	 * @param politicalRegionList ArrayList containing new political regions
	 */
	public void setPoliticalRegionList(ArrayList<PoliticalRegion> politicalRegionList){
		this.politicalRegionList = politicalRegionList;
	} // end setPoliticalRegionList
	
	/**
	 * This method adds a political region to the list of political regions a place of
	 * interest is located on.
	 * 
	 * @param politicalRegion Political region to be added to the list of political regions
	 * @return True if list was changed and false otherwise.
	 */
	public boolean addPoliticalRegion(PoliticalRegion politicalRegion){
		return politicalRegionList.add(politicalRegion);
	} // end addPoliticalRegion
	
	/**
	 * This method removes a political region from the list of political regions a place
	 * of interest is located on by the political region's name. 
	 * 
	 * @param politicalRegionName Name of the political region to be removed
	 * @return True if list was changed and false otherwise.
	 */
	public boolean removePoliticalRegion(String politicalRegionName){
		for(PoliticalRegion region: politicalRegionList){
			if(region.getName().equals(politicalRegionName)){
				return politicalRegionList.remove(region);
			}
		}
		
		return false;
	} // end removePoliticalRegion
	
	/**
	 * This method determines if a place of interest is located
	 * on a certain political region
	 * 
	 * @param politicalRegionName Name of political region being searched for
	 * @return True if the place of interest is located on the searched for
	 * political region and false otherwise
	 */
	public boolean hasPoliticalRegion(String politicalRegionName){
		for(PoliticalRegion region: politicalRegionList){
			if(region.getName().equals(politicalRegionName)){
				return true;
			}
		}
		
		return false;
	} // end hasPoliticalRegion
	
	
	/**
	 * This method gives the String representation of a place of interest. 
	 * 
	 * @return String representation of a place of interest.
	 */
	@Override
	public String toString(){
		String politicalRegionNames = ""; // holds names of political regions
		
		for(PoliticalRegion region: politicalRegionList){
			politicalRegionNames += region.getName() + ", "; // adds each political region name to String
		}
		
		//Trim the last ', ' from the list of names
		politicalRegionNames = politicalRegionNames.substring(0, politicalRegionNames.length()-2);
		
		return this.getName() + ", " + this.getArea() + ", " 
				+ this.typeOfPlace + ", " + politicalRegionNames;
	} // end toString
} // end PlaceOfInterest Class
