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
 * The view is presented to the user when they would like
 * to check if two geographic regions are neighbors. This view
 * asks for the two coordinate regions to be looked at.
 * Extends JFrame
 */
public class NeighborhoodCheckSelectView extends JFrame{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = -468646050580124655L;

	/**Model this view will be obtaining information from */
	private RegionModel model;
	/**String saying whether the recursive or normal check view will be called.*/
	private String checkChoice = "";
	
	/**Variables to contain different components of the view */
	private JComboBox<String> jcbRegionNames1 = new JComboBox<String>();
	private JComboBox<String> jcbRegionNames2 = new JComboBox<String>();
	private JTextField jtfBreadth = new JTextField();
	private JTextField jtfLength = new JTextField();
	private JButton jbtEnter = new JButton("Enter");
	private JButton jbtCancel = new JButton("Cancel");
	
	/**
	 * Default constructor for the Neighborhood Check selection
	 * view. Organizes and displays the view. 
	 */
	public NeighborhoodCheckSelectView(RegionModel model, String checkChoice){
		//Assign model variable
		this.model = model;
		this.checkChoice = checkChoice;
		
		//add main panel
		this.add(new CheckSelectPanel());
		
		//Size and display
		setTitle("Geographic Neighborhood Check");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,150);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end NeighborhoodListSelectView
	
	/**
	 * Class which contains the main panel of the neighborhood
	 * check selection view.
	 *
	 */
	private class CheckSelectPanel extends JPanel{
		
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
		CheckSelectPanel(){
			//Create panel to contain labels and input fields
			JPanel labelPanel = new JPanel();
			labelPanel.setLayout(new GridLayout(4,2));
			
			//Create panel to hold buttons
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));
			
			//Set values for JComboBoxes of Coordinate Regions
			jcbRegionNames1 = new JComboBox<String>(getCoordinateRegionNames());
			jcbRegionNames2 = new JComboBox<String>(getCoordinateRegionNames());
			
			//Add input fields and labels to label panel
			labelPanel.add(new JLabel("Coordinate Region 1:"));
			labelPanel.add(jcbRegionNames1);
			labelPanel.add(new JLabel("Coordinate Region 2:"));
			labelPanel.add(jcbRegionNames2);
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
						//Find selected regions from jcbRegionNames
						Mappable<? extends Region> region1 = null;
						Mappable<? extends Region> region2 = null;
						
						for(City city: model.getAllCities()){
							//Check for first region
							if(city.getName().equals(jcbRegionNames1.getSelectedItem().toString())){
								region1 = city;
							}
							//Check for second region
							if(city.getName().equals(jcbRegionNames2.getSelectedItem().toString())){
								region2 = city;
							}
						} // end for loop
						for(PointOfInterest point: model.getAllPointsOfInterest()){
							//Check for first region
							if(point.getName().equals(jcbRegionNames1.getSelectedItem().toString())){
								region1= point;
							}
							//Check for second region
							if(point.getName().equals(jcbRegionNames2.getSelectedItem().toString())){
								region2= point;
							}
						} // end for loop
						
						//If choice was normal, show normal check view
						if(checkChoice.equals("normal")){
							new GeoNeighborhoodCheckView(region1,region2, Integer.parseInt(jtfBreadth.getText()),
									Integer.parseInt(jtfLength.getText()), model);
						} // end if
						else if(checkChoice.equals("recursive")){ //Display recursive view
							new RecursiveGNCheckView(region1,region2, Integer.parseInt(jtfBreadth.getText()),
									Integer.parseInt(jtfLength.getText()), model);
						} // end else if
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
		} // end CheckSelectPanel
	} // end CheckSelectPanel class
	

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
} // end NeighborhoodCheckSelectView
