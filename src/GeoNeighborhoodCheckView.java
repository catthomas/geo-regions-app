import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 * Project #5
 * CS 2334 Section 10
 * April 22, 2014
 * A view which checks if two coordinate regions
 * can be considered 'geographic neighbors' of each
 * other. The 'geographic neighbor' criteria is based off
 * of user entered breadth or length. 
 * @version 1.0
 */
public class GeoNeighborhoodCheckView extends JFrame {
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = -2033882549899729425L;
	/**Model this view will gather information from. */
	private RegionModel model;
	/**Variable to contain list of geographic neighbors for region 1. */
	private ArrayList<Mappable<? extends Region>> geoNeighbors;
	
	/**
	 * The default constructor for the
	 * geographic neighbor list view.
	 */
	public GeoNeighborhoodCheckView(Mappable<? extends Region> region1,
			Mappable<? extends Region> region2, int breadth, int length,
			RegionModel model){
		//Set the model
		this.model = model; 
		
		//Generate the list of geographic neighbors to region 1
		geoNeighbors = generateGeoNeighbors(region1, breadth, length);

		//Check if region 2 is a geographic neighbor to region 1
		if(geoNeighbors.contains(region2)){ //Yes, they are geographic neighbors
			//Tell the user they ARE geographic neighbors
			JOptionPane.showMessageDialog(null,
				 "Neighborhood Breadth: " + breadth +
				 "\nNeighborhood Length: " + length +
				  "\n \n The two coordinate regions, " + region1.getName() +
				  " and " + region2.getName() + ",\n are definitely geographic neighbors.",
			    "Geographic Neighborhood Check",
			    JOptionPane.INFORMATION_MESSAGE);
		} // end if
		else{ //Tell user they are not geographic neighbors
			//Tell the user they are NOT geographic neighbors
			JOptionPane.showMessageDialog(null,
				 "Neighborhood Breadth: " + breadth +
				 "\nNeighborhood Length: " + length +
				  "\n \n The two coordinate regions, " + region1.getName() +
				  " and " + region2.getName() + ",\n are not geographic neighbors.",
			    "Geographic Neighborhood Check",
			    JOptionPane.INFORMATION_MESSAGE);
		} // end else
	} // end GeoNeighborCheckListView

	
	/**
	 * This method generates a list of geographic neighbors. It is non-recursive.
	 * 
	 * @return ArrayList of non-recursive geographic neighbors.
	 */
	private ArrayList<Mappable<? extends Region>> 
			generateGeoNeighbors(Mappable<? extends Region> region, int breadth, int length){
		//Create list of all coordinate regions
		ArrayList<Mappable<? extends Region>> coordinateRegionList = generateAllCoordinateRegions();
		//Create list to hold geographic neighbor regions
		ArrayList<Mappable<? extends Region>> geoNeighborList = new ArrayList();
		
		
		//If breadth and length are both nonzero, 
		if(breadth != 0 && length != 0){
			//Obtain numeric value of the original region's longitude
			double longPoint = getNumberLongValue(region.getLongitude());
			//Obtain numeric value of the original region's latitude
			double latPoint = getNumberLatValue(region.getLatitude());
			
			//Add coordinate region to list if it is in proper range
			for(Mappable<? extends Region> geoNeighbor: coordinateRegionList){
				if(Math.abs(longPoint - getNumberLongValue(geoNeighbor.getLongitude())) < length
						&& Math.abs(latPoint - getNumberLatValue(geoNeighbor.getLatitude())) < breadth){
					geoNeighborList.add(geoNeighbor);
				}
			} // end for loop
		} // end if statement
		else if(breadth != 0){
			//Obtain numeric value of the original region's latitude
			double centerPoint = getNumberLatValue(region.getLatitude());
			
			//Add coordinate region to list if it is in proper range
			for(Mappable<? extends Region> geoNeighbor: coordinateRegionList){
				if(Math.abs(centerPoint - getNumberLatValue(geoNeighbor.getLatitude())) < breadth){
					geoNeighborList.add(geoNeighbor);
				}
			} // end for loop
		} // end else if
		else{
			//Obtain numeric value of the original region's longitude
			double centerPoint = getNumberLongValue(region.getLongitude());
			
			//Add coordinate region to list if it is in proper range
			for(Mappable<? extends Region> geoNeighbor: coordinateRegionList){
				if(Math.abs(centerPoint - getNumberLongValue(geoNeighbor.getLongitude())) < length){
					geoNeighborList.add(geoNeighbor);
				}
			} // end for loop
		} // end else
		
		//Return list of geographic neighbors
		return geoNeighborList;
	} // end generateGeoNeighbors
	
	/**
	 * This method takes in a string representation of latitude and
	 * converts it to a number between 0 and 180.
	 * @param latitude String representation of latitude.
	 * @return Corresponding latitude 'number' between 0 and 180.
	 * 180 is the farthest South. 
	 */
	private double getNumberLatValue(String latitude){
		//Converts latitude to a numeric value between 0-180
		//NOTE: range of latitude is 0 to 90 north and south
		if(latitude.charAt(0) == ('N') || latitude.charAt(0) == ('n')){
			return 90 - Double.parseDouble(latitude.substring(1));
		}
		else{
			return Double.parseDouble(latitude.substring(1))+ 90;
		}
	} // end getNumberLatValue
	
	/**
	 * This method takes in a string representation of longitude and
	 * converts it to a number between 0 and 360.
	 * @param longitude String representation of longitude.
	 * @return Corresponding latitude 'number' between 0 and 360.
	 * 360 is the farthest East, 0 the farthest West. 
	 */
	private double getNumberLongValue(String longitude){
		//Converts longitude to a numeric value between 0-360
		//NOTE: range of longitude is 0 to 180 east and west
		if(longitude.charAt(0) == ('W') || longitude.charAt(0) == ('w')){
			return 180 - Double.parseDouble(longitude.substring(1));
		}
		else{
			return Double.parseDouble(longitude.substring(1))+ 180;
		}
	} // end getNumberLongValue
	
	/**
	 * This method generates an ArrayList of all regions that implement "Mappable"
	 * (and thus are a coordinate region) contained in the model. The coordinate
	 * region is only if it actually has values for coordinates at the time of generation.
	 * 
	 * @return ArrayList of current model coordinate regions which actually
	 * have coordinates initialized. 
	 */
	private ArrayList<Mappable<? extends Region>> generateAllCoordinateRegions(){
		//Create arraylist to hold coordinate regions
		ArrayList<Mappable<? extends Region>> coordinateRegionList = new ArrayList();
		
		//Add cities if they have coordinates
		for(int i = 0; i < model.getAllCities().size(); ++i){
			//Make sure it has all values for a 'coordinate region', then add name
			if(model.getAllCities().get(i).getLatitude() != null){
				coordinateRegionList.add(model.getAllCities().get(i));
			}
		}// end for loop
		//Add points if they have coordinates
		for(int i = 0; i < model.getAllPointsOfInterest().size(); ++i){
			if(model.getAllPointsOfInterest().get(i).getLatitude() != null){
				coordinateRegionList.add(model.getAllPointsOfInterest().get(i));
			}
		} // end for loop
		//Return the list
		return coordinateRegionList;
	} // end generateAllCoordinateRegions
} // end GeoNeighborhoodCheckView
