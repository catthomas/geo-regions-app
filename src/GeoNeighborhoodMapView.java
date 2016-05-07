import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Project #5
 * CS 2334 Section 10
 * April 22, 2014
 * A view which displays geographic coordinate regions
 * on a map with their geographic neighbors.
 * @version 1.0
 */
public class GeoNeighborhoodMapView extends JFrame{
	/**
	 * Unique ID in order for this class to be written to files directly as an
	 * object.
	 */
	private static final long serialVersionUID = 6675198469379515328L;
	/** Variable to store location of plate caree map. */
	private final String IMAGE_LOCATION = "/images/worldmap.gif";
	/** Model this map will be gathering information from. */
	RegionModel model;

	/**
	 * This constructor displays an image of a plate caree map then plots
	 * a coordinate region and its neighbors on the map. 
	 */
	public GeoNeighborhoodMapView(Mappable<? extends Region> region, int breadth,
			int length, RegionModel model) {
		//Set the model for the map view
		this.model = model;
		
		// Make plate caree map as background to a JLabel
		JLabel mapImage = new JLabel();
		mapImage.setIcon(new ImageIcon(IMAGE_LOCATION)); // Retrieves image from
															// files
		mapImage.setLayout(new BorderLayout());
		setContentPane(mapImage);
		setLayout(null); // Makes null layout in order to individually plot city
							// labels
		
		//Generate the geographic neighborhood
		ArrayList<Mappable<? extends Region>> neighbors = generateGeoNeighbors(region,
				breadth, length);
		

		// Add a label for each region in the geographic neighborhood
		for (Mappable<? extends Region> neighbor: neighbors) {
				//Create label
				JLabel neighborPoint = new JLabel("<html>" + neighbor.getName()
						+ "<br><center>.</center></html>", JLabel.CENTER);
				neighborPoint.setFont(neighborPoint.getFont().deriveFont(Font.BOLD, 10));
				mapImage.add(neighborPoint);
				
				// Position the city label at the proper location
				Insets insets = this.getInsets();
				Dimension size = neighborPoint.getPreferredSize();
				neighborPoint.setBounds(convertLongitude(neighbor) + insets.left,
						convertLatitude(neighbor) + insets.top, size.width,
						size.height);
				
				//If 'neighbor' was original region, draw a border around it
				if(neighbor.equals(region)){
					//Map the border
					mapBorder(region, breadth, length, mapImage, neighborPoint);
				}
		}
		
		//Make map visible
		Insets insets = this.getInsets();
		this.setSize(700 + insets.left + insets.right, 405 + insets.top
				+ insets.bottom);
		this.setLocationRelativeTo(null); // Centers frame
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); // Displays frame to the user
	} // end PlateCareeMap

	/**
	 * This method takes a coordinate region's longitude and outputs a pixel location (int)
	 * of where the coordinate region should be placed on a map.
	 * 
	 * @param region Coordinate region whose longitude should be converted. 
	 * @return int portraying pixel location for longitude.
	 */
	private static int convertLongitude(Mappable<? extends Region> region) {
		// Obtain numeric degrees value
		double degrees = Double.parseDouble(region.getLongitude().substring(1));

		// Scales Longitude to picture
		if (region.getLongitude().contains("W")) {
			return (int) (310 - degrees * (310 / 180.0));
		} else {
			return (int) (degrees * (330 / 180.0) + 330);
		}
	} // end convertLongitude

	/**
	 * This method takes a coordinate regions's latitude and outputs a pixel location (int) of
	 * where the coordinate region should be placed on a map.
	 * 
	 * @param region Coordinate region whose lotitude will be converted. 
	 * @return int of pixel location for latitude.
	 */
	private static int convertLatitude(Mappable<? extends Region> region) {
		// Obtain numeric degrees value
		double degrees = Double.parseDouble(region.getLatitude().substring(1));

		// Scales Latitude to picture
		if (region.getLatitude().contains("N")) {
			return (int) (180 - degrees * (180 / 90.0));
		} else {
			return (int) (degrees * (180 / 90.0) + 180);
		}
	} // end convertLatitude
	
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
	
	/**
	 * This method draws a border around a particular region label
	 * 
	 * @param region Region whose border will be drawn	
	 * @param breadth Breadth of border
	 * @param length Length of border
	 * @param mapImage Image of map that the border will be placed on
	 * @param neighborPoint JLabel of region on map
	 */
	private void mapBorder(Mappable<? extends Region> region, int breadth, int length,
			JLabel mapImage, JLabel neighborPoint){
		if(breadth != 0 && length != 0){ //Border will be a square
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			//JLabel which will represent border
			JLabel neighborhoodBorder = new JLabel();
			
			//Scale breadth and length to frame
			//remember breadth is height and length is width
			breadth = breadth*7;
			length = length*7;
			
			//Base dimension of border of breadth and length
			Dimension borderDimensions = new Dimension(length, breadth);
			neighborhoodBorder.setPreferredSize(borderDimensions);
			neighborhoodBorder.setBorder(border); //add border
			mapImage.add(neighborhoodBorder); // add to map
			
			//Set location of border relative to center point			
			//Values for x and y position of top left corner of border
			int x = convertLongitude(region) - (int)(.5 * length) + (int)(.5*neighborPoint.getWidth());
			int y = convertLatitude(region) - (int)(.5 * breadth) + (int)(.5*neighborPoint.getHeight());
			//Set Location
			neighborhoodBorder.setBounds(x,y, borderDimensions.width,
					borderDimensions.height);
		}
		else if(breadth != 0){ // Will have border extending to sides of map
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			//JLabel which will represent border
			JLabel neighborhoodBorder = new JLabel();
			
			//Scale breadth and length to frame
			//remember breadth is height and length is width
			breadth = breadth*7;
			length = 1600;
			
			//Base dimension of border of breadth and length
			Dimension borderDimensions = new Dimension(length, breadth);
			neighborhoodBorder.setPreferredSize(borderDimensions);
			neighborhoodBorder.setBorder(border); //add border
			mapImage.add(neighborhoodBorder); // add to map
			
			//Set location of border relative to center point			
			//Values for x and y position of top left corner of border
			int x = convertLongitude(region) - (int)(.5 * length) + (int)(.5*neighborPoint.getWidth());
			int y = convertLatitude(region) - (int)(.5 * breadth) + (int)(.5*neighborPoint.getHeight());
			//Set Location
			neighborhoodBorder.setBounds(x,y, borderDimensions.width,
					borderDimensions.height);
		}
		else{
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			//JLabel which will represent border
			JLabel neighborhoodBorder = new JLabel();
			
			//Scale breadth and length to frame
			//remember breadth is height and length is width
			breadth = 1200;
			length = length*7;
			
			//Base dimension of border of breadth and length
			Dimension borderDimensions = new Dimension(length, breadth);
			neighborhoodBorder.setPreferredSize(borderDimensions);
			neighborhoodBorder.setBorder(border); //add border
			mapImage.add(neighborhoodBorder); // add to map
			
			//Set location of border relative to center point			
			//Values for x and y position of top left corner of border
			int x = convertLongitude(region) - (int)(.5 * length) + (int)(.5*neighborPoint.getWidth());
			int y = convertLatitude(region) - (int)(.5 * breadth) + (int)(.5*neighborPoint.getHeight());
			//Set Location
			neighborhoodBorder.setBounds(x,y, borderDimensions.width,
					borderDimensions.height);
		}
	} // end mapBorder
} // end GeoNeighborhoodMapView
