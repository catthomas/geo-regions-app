import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * The view to add/edit a Point of Interest.
 * @version 1.0
 */
public class AddEditPointView extends JFrame {
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 7370287229164221168L;
	
	/** Variables to hold different components of the view.*/
	private AddEditBasicPanel basicPanel;
	private JTextField jtfName = new JTextField();
	private JTextField jtfType = new JTextField();
	private JTextField jtfLatitude = new JTextField();
	private JTextField jtfLongitude = new JTextField();
	private JTextField jtfElevation = new JTextField();
	private JButton jbtEnter = new JButton("Enter");
	private JComboBox<String> jcbRegions = new JComboBox<String>();
	
	//Saves original name of country if this is one being edited
	private String regionName = "";
	
	//Holds model this view is gathering information from. 
	private RegionModel model;
	
	/**
	 * Constructor that makes a view for 
	 * adding a point of interest.
	 * @param model Model this view 
	 */
	public AddEditPointView(RegionModel model){
		//Sets the model variable
		this.model = model;
		
		//Constructs main panel for the view
		basicPanel = new AddEditBasicPanel();
		
		//Adds panel to view then displays it
		add(basicPanel);
		setTitle("Add Point of Interest");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(350,250);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView
	
	/**
	 * Constructor that makes a view for
	 * editing a point of interest.
	 * @param point  Point to be edited. 
	 * @param model Model this view is gathering information from.
	 */
	public AddEditPointView(PointOfInterest point, RegionModel model){
		//Save original name of city being edited
		this.regionName = point.getName();
		//Set the model 
		this.model = model;
		
		//Constructs the main panel for the view.
		basicPanel = new AddEditBasicPanel();
		
		//Prepopulate text fields with existing data
		jtfName.setText(point.getName());
		jtfType.setText(point.getType());
		jtfLatitude.setText(point.getLatitude());
		jtfLongitude.setText(point.getLongitude());
		jtfElevation.setText(String.valueOf(point.getElevation()));
		jcbRegions.setSelectedItem(point.getRegionLocation().getName());
		
		//Add panel to view and display view. 
		add(basicPanel);
		setTitle("Edit Point of Interest");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(350,250);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView with parameters
	
	/**
	 * An accessor method for the main panel of the view. 
	 * @return Reference to main panel of the view. 
	 */
	public AddEditBasicPanel getBasicPanel(){
		return basicPanel;
	} // end getBasicPanel
	
	/**
	 * An accesor method for the combobox of regions. 
	 * @return Combobox of region name.s 
	 */
	public JComboBox<String> getJcbRegions(){
		return jcbRegions;
	} // end getJcbContinents
	
	/**
	 * An accessor method for the name textfield.
	 * @return Textfield containing name information. 
	 */
	public JTextField getJtfName(){
		return jtfName;
	} // end getJtfName
	
	/**
	 * An accessor method for the type textfield. 
	 * @return Textfield containing type of point. 
	 */
	public JTextField getJtfType(){
		return jtfType;
	} // end get JtfArea
	
	/**
	 * An accessor method for the latitude of the point. 
	 * @return Textfield containing latitude of point. 
	 */
	public JTextField getJtfLatitude(){
		return jtfLatitude;
	} // end getJtfLatitude
	
	/**
	 * An accessor method for the longitude of the point. 
	 * @return Textfield containing longitude of point .
	 */
	public JTextField getJtfLongitude(){
		return jtfLongitude;
	} // end getJtfLongitud
	
	/**
	 * An accessor method for the elevation of the point. 
	 * @return Textfield containing the elevation of the point .
	 */
	public JTextField getJtfElevation(){
		return jtfElevation;
	} // end getJtfElevation
	
	/**
	 * An accessor method for the enter button.
	 * @return Enter button for the view. 
	 */
	public JButton getJbtEnter(){
		return jbtEnter;
	} // end getJbtEnter
	
	/**
	 * An accessor method for the original name of the 
	 * point being edited. 
	 * @return Original name of point being edited. 
	 */
	public String getRegionName(){
		return regionName;
	} // end getJbtEnter
	
	/**
	 * Class containing the main panel 
	 * for the view. 
	 *
	 */
	public class AddEditBasicPanel extends JPanel{
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -1208047762479637389L;

		/**
		 * Constructor for the main panel.
		 */
		public AddEditBasicPanel(){
			//set values for JComboBox
			ArrayList<String> regionNameList = new ArrayList<String>();
			
			//Add names of all continents
			if(model != null && model.getAllContinents() != null){
				for(Continent continent: model.getAllContinents()){
					regionNameList.add(continent.getName());
				}
			}
			
			//Add names of all countries
			if(model != null && model.getAllCountries() != null){
				for(Country country: model.getAllCountries()){
					regionNameList.add(country.getName());
				}
			}
			
			//Add names of all cities
			if(model != null && model.getAllCities() != null){
				for(City city: model.getAllCities()){
					regionNameList.add(city.getName());
				}
			}
			
			//Add names of all places
			if(model != null && model.getAllPlacesOfInterest() != null){
				for(PlaceOfInterest place: model.getAllPlacesOfInterest()){
					regionNameList.add(place.getName());
				}
			}
			
			//Add names of all points
			if(model != null && model.getAllPointsOfInterest() != null){
				for(PointOfInterest point: model.getAllPointsOfInterest()){
					regionNameList.add(point.getName());
				}
			}
			
			//Sort alphabetically
			java.util.Collections.sort(regionNameList);
			
			//Set list of regions to combobox
			String[] regions = new String[regionNameList.size()];
			regions = regionNameList.toArray(regions);
			jcbRegions = new JComboBox<String>(regions);
			
			//Panel to group labels
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(6,1));
			
			//Add labels to panel
			panel1.add(new JLabel("Name: "));
			panel1.add(new JLabel("Type: "));
			panel1.add(new JLabel("Latitude (ex. N23.5): "));
			panel1.add(new JLabel("Longitude (ex. W41.5): "));
			panel1.add(new JLabel("Elevation: "));
			panel1.add(new JLabel("Region Location: "));
			
			//Panel to group text fields
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(6,1));
			
			//Add textfields to panel
			panel2.add(jtfName);
			panel2.add(jtfType);
			panel2.add(jtfLatitude);
			panel2.add(jtfLongitude);
			panel2.add(jtfElevation);
			panel2.add(jcbRegions);
			
			//Panel to group buttons
			JPanel panel3 = new JPanel();
			panel3.setLayout(new BorderLayout());
			panel3.add(jbtEnter, BorderLayout.CENTER);
			
			//Set frame layout and add panels
			setLayout(new BorderLayout());
			add(panel1, BorderLayout.WEST);
			add(panel2, BorderLayout.CENTER);
			add(panel3, BorderLayout.SOUTH);
		} // end AddEditBasicPanel constructor
	} // end AddEditBasicPanel
} // end AddEditPointView class
