import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * A View Class which takes in an arraylist of Cities
 * and displays them located by longitude/latitude on a Plate
 * Caree Map.
 * @version 1.0
 */

public class MapView extends JFrame {
	/**
	 * Unique ID in order for this class to be written to files directly as an
	 * object.
	 */
	private static final long serialVersionUID = 6675198469379515328L;
	/** Plate caree map image */
	private final String IMAGE_LOCATION = "cs2334/Projects/worldmap.gif";

	/**
	 * This constructor displays an image of a plate caree map then plots the
	 * labels to various cities onto the map.
	 * 
	 * @param cityList
	 *            ArrayList of Cities to be plotted onto map.
	 */
	public MapView(ArrayList<City> cityList) {
		// Make plate caree map as background to a JLabel
		JLabel mapImage = new JLabel();
		mapImage.setIcon(new ImageIcon(IMAGE_LOCATION)); // Retrieves image from
															// files
		mapImage.setLayout(new BorderLayout());
		setContentPane(mapImage);
		setLayout(null); // Makes null layout in order to individually plot city
							// labels

		// Add a label for each city in the given ArrayList
		for (City city : cityList) {
			if (city.getLatitude() != null) { // Check if city contains
												// coordinates
				JLabel cityPoint = new JLabel("<html>" + city.getName()
						+ "<br><center>.</center></html>", JLabel.CENTER);
				cityPoint
						.setFont(cityPoint.getFont().deriveFont(Font.BOLD, 10));
				mapImage.add(cityPoint);

				// Position the city label at the proper location
				Insets insets = this.getInsets();
				Dimension size = cityPoint.getPreferredSize();
				cityPoint.setBounds(convertLongitude(city) + insets.left,
						convertLatitude(city) + insets.top, size.width,
						size.height);
			}
		}
	} // end PlateCareeMap

	/**
	 * This method takes a city's longitude and outputs a pixel location (int)
	 * of where the city should be placed on a map.
	 * 
	 * @param city
	 *            City whose longitude is to be converted.
	 * @return int portraying pixel location for longitude.
	 */
	private static int convertLongitude(City city) {
		// Obtain numeric degrees value
		double degrees = Double.parseDouble(city.getLongitude().substring(1));

		// Scales Longitude to picture
		if (city.getLongitude().contains("W")) {
			return (int) (310 - degrees * (310 / 180.0));
		} else {
			return (int) (degrees * (330 / 180.0) + 330);
		}
	} // end convertLongitude

	/**
	 * This method takes a city's latitude and outputs a pixel location (int) of
	 * where the city should be placed on a map.
	 * 
	 * @param city
	 *            City whose latitude is to be converted.
	 * @return int of pixel location for latitude.
	 */
	private static int convertLatitude(City city) {
		// Obtain numeric degrees value
		double degrees = Double.parseDouble(city.getLatitude().substring(1));

		// Scales Latitude to picture
		if (city.getLatitude().contains("N")) {
			return (int) (180 - degrees * (180 / 90.0));
		} else {
			return (int) (degrees * (180 / 90.0) + 180);
		}
	} // end convertLatitude
} // end MapView class