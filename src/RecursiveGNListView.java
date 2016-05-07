import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Project #5
 * CS 2334 Section 10
 * April 22, 2014
 * A view which displays geographic coordinate regions
 * in a list with their geographic neighbors. It implements
 * recursive methods.
 * @version 1.0
 */
public class RecursiveGNListView extends JFrame{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 954099899422283545L;
	/**Variable to contain list of geographic neighbors. */
	private DefaultListModel<String> geoNeighborModel = new DefaultListModel<String>();
	private JList<String> jlGeoNeighbors;
	/**Model this view will gather information from. */
	private RegionModel model;
	
	/**
	 * The default constructor for the
	 * geographic neighbor list view.
	 */
	public RecursiveGNListView(Mappable<? extends Region> chosenRegion, int breadth, int length,
			RegionModel model){
		//Set the model
		this.model = model; 
		
		//Generate the appropriate list
		populateGeoNeighborModel(chosenRegion, breadth, length);
		jlGeoNeighbors = new JList<String>(geoNeighborModel);
		
		// Make JList for region objects able to scroll
		JScrollPane scrollableGeoNeighborList = new JScrollPane(jlGeoNeighbors);
		
		//Create JLabel
		JLabel jlTitle = new JLabel("Recursive Geographic Neighborhood for " + chosenRegion.getName()
				+" \n Breadth: " + breadth + " Length: " + length);
		
		//Add labels and list to the frame
		this.setLayout(new BorderLayout());
		this.add(jlTitle, BorderLayout.NORTH);
		this.add(scrollableGeoNeighborList, BorderLayout.CENTER);
		
		//Set frame settings then display
		setTitle("Recursive Geographic Neighborhood List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,150);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end GeoNeighborhoodListView

	/**
	 * This method pulls the names of the regions from the geographic neighbor list
	 * and stores them in the model of geographic neighbor region names
	 */
	public void populateGeoNeighborModel(Mappable<? extends Region> region, int breadth, int length) {
		//Clear the model
		geoNeighborModel.clear();

		//Variable to contain visited regions for recursive call
		ArrayList<Mappable<? extends Region>> visitedRegions = new ArrayList<Mappable<? extends Region>>();
		
		//Add the names of the geographic neighborhood regions to the model
		ArrayList<Mappable<? extends Region>> neighborList = generateRecursiveGeoNeighbors(region, breadth, 
				length, visitedRegions);
		//Add original region as a neighbor to itself
		neighborList.add(region);
		
		for(Mappable<? extends Region> geoNeighbor: neighborList){
			geoNeighborModel.addElement(geoNeighbor.getName());
		}//end for loop
	}// end setRegionList method
	
	/**
	 * This method generates a list of geographic neighbors. It is recursive.
	 * 
	 * @return ArrayList of recursive geographic neighbors.
	 */
	public ArrayList<Mappable<? extends Region>> generateRecursiveGeoNeighbors(Mappable<? extends Region> region,
					int breadth, int length, ArrayList<Mappable<? extends Region>> visitedRegions){
		//Create list of all coordinate regions
		ArrayList<Mappable<? extends Region>> coordinateRegionList = generateAllCoordinateRegions();
		//Create list to hold geographic neighbor regions
		ArrayList<Mappable<? extends Region>> geoNeighborList = new ArrayList();
		
		//Add region to visited regions
		visitedRegions.add(region);
		
		//If breadth and length are both nonzero 
		if(breadth != 0 && length != 0){
			//Obtain numeric value of the original region's longitude
			double longPoint = getNumberLongValue(region.getLongitude());
			//Obtain numeric value of the original region's latitude
			double latPoint = getNumberLatValue(region.getLatitude());
			
			//Add coordinate region to list if it is in proper range
			for(Mappable<? extends Region> geoNeighbor: coordinateRegionList){
				if(Math.abs(longPoint - getNumberLongValue(geoNeighbor.getLongitude())) < length
						&& Math.abs(latPoint - getNumberLatValue(geoNeighbor.getLatitude())) < breadth){
					//If this region has not been visited before, add it's neighbors as well as itself
					if(!visitedRegions.contains(geoNeighbor)){
						geoNeighborList.add(geoNeighbor);
						
						geoNeighborList.addAll(generateRecursiveGeoNeighbors(geoNeighbor, breadth,
								length, visitedRegions));
					} // end if statement
				} // end if statement
			} // end for loop
		} // end if statement
		else if(breadth != 0){
			//Obtain numeric value of the original region's latitude
			double centerPoint = getNumberLatValue(region.getLatitude());
			
			//Add coordinate region to list if it is in proper range
			for(Mappable<? extends Region> geoNeighbor: coordinateRegionList){
				if(Math.abs(centerPoint - getNumberLatValue(geoNeighbor.getLatitude())) < breadth){
					//If this region has not been visited before, add it's neighbors as well as itself
					if(!visitedRegions.contains(geoNeighbor)){
						geoNeighborList.add(geoNeighbor);
						
						geoNeighborList.addAll(generateRecursiveGeoNeighbors(geoNeighbor, breadth,
								length, visitedRegions));
					} // end if statement
				} // end if statement
			} // end for loop
		} // end else if
		else{
			//Obtain numeric value of the original region's longitude
			double centerPoint = getNumberLongValue(region.getLongitude());
			
			//Add coordinate region to list if it is in proper range
			for(Mappable<? extends Region> geoNeighbor: coordinateRegionList){
				if(Math.abs(centerPoint - getNumberLongValue(geoNeighbor.getLongitude())) < length){
					//If this region has not been visited before, add it's neighbors as well as itself
					if(!visitedRegions.contains(geoNeighbor)){
						geoNeighborList.add(geoNeighbor);
						
						geoNeighborList.addAll(generateRecursiveGeoNeighbors(geoNeighbor, breadth,
								length, visitedRegions));
					} // end if statement
				} // end if statement
			} // end for loop
		} // end else
		
		//Return list of geographic neighbors
		return geoNeighborList;
	} // end generateRecursiveGeoNeighbors
	
	/**
	 * This method takes in a string representation of latitude and
	 * converts it to a number between 0 and 180.
	 * @param latitude String representation of latitude.
	 * @return Corresponding latitude 'number' between 0 and 180.
	 * 180 is the farthest South. 
	 */
	public double getNumberLatValue(String latitude){
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
	public double getNumberLongValue(String longitude){
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
	public ArrayList<Mappable<? extends Region>> generateAllCoordinateRegions(){
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
} // end RecursiveGNCheckView class

