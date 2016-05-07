import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Project #5
 * CS 2334, section 10
 * April 26, 2014
 * The view presented to the user when they would like
 * to generate a geographic neighborhood list. This view
 * asks for the coordinate region to be looked at. 
 * Extends JFrame
 */
public class NeighborhoodListSelectView extends JFrame{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 3342131598311861884L;
	/**Model this view will be obtaining information from */
	private RegionModel model;
	/**String portraying whether the recursive or normal view should be displayed
	 * String should either be "normal" or "recursive"
	 */
	private String listChoice = "";
	
	/**Variables to contain different components of the view */
	JComboBox<String> jcbRegionNames = new JComboBox<String>();
	JTextField jtfBreadth = new JTextField();
	JTextField jtfLength = new JTextField();
	JButton jbtEnter = new JButton("Enter");
	JButton jbtCancel = new JButton("Cancel");
	
	/**
	 * Default constructor for the Neighborhood List selection
	 * view. Organizes and displays the view. 
	 */
	public NeighborhoodListSelectView(RegionModel model, String listChoice){
		//Assign model variable
		this.model = model;
		this.listChoice = listChoice;
		
		//add main panel
		this.add(new ListSelectPanel());
		
		//Size and display
		setTitle("Geographic Neighborhood List Selection");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,150);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end NeighborhoodListSelectView
	
	/**
	 * Class which contains the main panel of the neighborhood
	 * list selection view.
	 *
	 */
	private class ListSelectPanel extends JPanel{
		
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -6328318082795406573L;

		/**
		 * Default constructor for the list selection panel.
		 * Contains a list of coordinate regions to choose from
		 * and text fields for breadth and length. 
		 */
		ListSelectPanel(){
			//Create panel to contain labels and input fields
			JPanel labelPanel = new JPanel();
			labelPanel.setLayout(new GridLayout(3,2));
			
			//Create panel to hold buttons
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));
			
			//Set values for JComboBox Coordinate Regions
			jcbRegionNames = new JComboBox<String>(getCoordinateRegionNames());
			
			//Add input fields and labels to label panel
			labelPanel.add(new JLabel("Coordinate Region:"));
			labelPanel.add(jcbRegionNames);
			labelPanel.add(new JLabel("Breadth:"));
			labelPanel.add(jtfBreadth);
			labelPanel.add(new JLabel("Length:"));
			labelPanel.add(jtfLength);
			
			// Create Actions for buttons
			class enterButtonListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(jtfBreadth.getText() != null &&
							!"".equals(jtfBreadth.getText()) &&
							jtfLength.getText() != null &&
							!"".equals(jtfLength.getText())){
						//Find selected region from jcbRegionNames
						Mappable<? extends Region> chosenRegion = null;
						
						for(City city: model.getAllCities()){
							if(city.getName().equals(jcbRegionNames.getSelectedItem().toString())){
								chosenRegion = city;
							}
						} // end for loop
						for(PointOfInterest point: model.getAllPointsOfInterest()){
							if(point.getName().equals(jcbRegionNames.getSelectedItem().toString())){
								chosenRegion = point;
							}
						} // end for loop
						
						if(listChoice.equals("normal")){
							new GeoNeighborhoodListView(chosenRegion, Integer.parseInt(jtfBreadth.getText()),
									Integer.parseInt(jtfLength.getText()), model);
						}
						else if(listChoice.equals("recursive")){
							new RecursiveGNListView(chosenRegion, Integer.parseInt(jtfBreadth.getText()),
									Integer.parseInt(jtfLength.getText()), model);
						}
					} //end if-statement
				}// end actionPreformed
			}// end enterButtonListener

			class cancelButtonListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					disposeFrame();
				}
			}// end cancelButtonListener class

			// Add Listeners to buttons
			jbtEnter.addActionListener(new enterButtonListener());
			jbtCancel.addActionListener(new cancelButtonListener());

			// Add Buttons to ButtonPanel
			buttonPanel.add(jbtEnter);
			buttonPanel.add(jbtCancel);
			
			//Add different panels to main one
			this.setLayout(new BorderLayout());
			this.add(labelPanel, BorderLayout.CENTER);
			this.add(buttonPanel, BorderLayout.SOUTH);
		} // end ListSelectPanel
	} // end ListSelectPanel class
	

	/**
	 * Method to close this frame when the user is done selecting
	 */
	public void disposeFrame() {
		this.dispose();
	} // end disposeFrame
	
	/**
	 * Method that makes a string array of coordinate region names.
	 * @return String array of coordinate region names arranged
	 * alphabetically
	 */
	public String[] getCoordinateRegionNames(){
		if(model != null){
			ArrayList<String> coordinateNameArray = new ArrayList<String>();
			
			String[] coordinateRegionList = new String[0];
			
			//Add Names of cities
			for(int i = 0; i < model.getAllCities().size(); ++i){
				//Make sure it has all values for a 'coordinate region', then add name
				if(model.getAllCities().get(i).getLatitude() != null){
					coordinateNameArray.add(model.getAllCities().get(i).getName());
				}
			}
			//Add Names of points of interest
			for(int i = 0; i < model.getAllPointsOfInterest().size(); ++i){
				if(model.getAllPointsOfInterest().get(i).getLatitude() != null){
					coordinateNameArray.add(model.getAllPointsOfInterest().get(i).getName());
				}
			}
			//Arrange names alphabetically
			Collections.sort(coordinateNameArray);
			
			coordinateRegionList = coordinateNameArray.toArray(coordinateRegionList);
			//Return array
			return coordinateRegionList;
		}// end if statement
		
		return null;
	}// end getCoordinateRegionNames
} // end NeighborhoodListSelectView
