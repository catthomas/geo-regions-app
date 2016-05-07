import java.awt.BorderLayout;
import java.awt.GridLayout;

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
 * The view to add/edit a City.
 * @version 1.0
 */
public class AddEditCityView extends JFrame{	
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 7370287229164221168L;
	
	/** Variables to contain various components of the view */
	private AddEditBasicPanel basicPanel;
	private JTextField jtfName = new JTextField();
	private JTextField jtfArea = new JTextField();
	private JTextField jtfPopulation = new JTextField();
	private JTextField jtfLatitude = new JTextField();
	private JTextField jtfLongitude = new JTextField();
	private JTextField jtfElevation = new JTextField();
	private JButton jbtEnter = new JButton("Enter");
	private JComboBox<String> jcbCountries = new JComboBox<String>();
	
	/**Variable to hold name of city if it is being edited*/
	private String regionName = "";
	
	/**The model assigned to this view */
	private RegionModel model;
	
	/**
	 * This is the constructor specifically made
	 * for displaying a view to add a city. 
	 * 
	 * @param model Model this view is getting information from.
	 */
	public AddEditCityView(RegionModel model){
		//Set the model
		this.model = model;
		
		//Construct main panel of the view
		basicPanel = new AddEditBasicPanel();
		
		//Add panels and display view
		add(basicPanel);
		setTitle("Add City");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,250);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView
	
	/**
	 * This is the constructor specifically made for editing 
	 * a city. The main difference is it pre-populates 
	 * all fields with information from the model and 
	 * tracks the index of the object being edited. 
	 * 
	 * @param city City being edited
	 * @param model Model in which city information is stored. 
	 */
	public AddEditCityView(City city, RegionModel model){
		//Save name of city being edited
		this.regionName = city.getName();
		//Set the model
		this.model = model;

		//Construct main panel for the view
		basicPanel = new AddEditBasicPanel();
		
		//Prepopulate text fields and combobox with existing data
		jtfName.setText(city.getName());
		jtfArea.setText(String.valueOf(city.getArea()));
		jtfPopulation.setText(String.valueOf(city.getPopulation()));
		jtfLatitude.setText(city.getLatitude());
		jtfLongitude.setText(city.getLongitude());
		jtfElevation.setText(String.valueOf(city.getElevation()));
		jcbCountries.setSelectedItem(city.getCountryLocation().getName());
		
		//Add panels and display the view
		add(basicPanel);
		setTitle("Edit City");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,250);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView with parameters
	
	/**
	 * An accessor method for the basic panel.
	 * @return Reference to the main panel of the view. 
	 */
	public AddEditBasicPanel getBasicPanel(){
		return basicPanel;
	} // end getBasicPanel
	
	/**
	 * An accessor method for the JComboBox of 
	 * country names.
	 * @return Reference to the JComboBox 
	 */
	public JComboBox<String> getJcbCountries(){
		return jcbCountries;
	} // end getJcbContinents
	
	/**
	 * An accessor method for the JTextField
	 * containing name data.
	 * @return Reference to the name textfield.
	 */
	public JTextField getJtfName(){
		return jtfName;
	} // end getJtfName
	
	/**
	 * An accessor method for the JTextField
	 * containing area data.
	 * @return Reference to the area textfield.
	 */
	public JTextField getJtfArea(){
		return jtfArea;
	} // end get JtfArea
	
	/**
	 * An accessor method for the JTextField
	 * containing population data.
	 * @return Reference to the population textfield.
	 */
	public JTextField getJtfPopulation(){
		return jtfPopulation;
	} // end getJtfPopulation
	
	/**
	 * An accessor method for the JTextField
	 * containing latitude data.
	 * @return Reference to the latitude textfield.
	 */
	public JTextField getJtfLatitude(){
		return jtfLatitude;
	} // end getJtfLatitude
	
	/**
	 * An accessor method for the JTextField 
	 * containing longitude data.
	 * @return Reference to the longitude textfield.
	 */
	public JTextField getJtfLongitude(){
		return jtfLongitude;
	} // end getJtfLongitud
	
	/**
	 * An accessor method for the JTextField
	 * containing elevation data.
	 * @return Reference to the elevation textfield.
	 */
	public JTextField getJtfElevation(){
		return jtfElevation;
	} // end getJtfElevation
	
	/**
	 * An accessor method for the enter button.
	 * @return Reference to the enter button.
	 */
	public JButton getJbtEnter(){
		return jbtEnter;
	} // end getJbtEnter
	
	/**
	 * An accessor method for the name of the
	 * city being edited.
	 * @return Name of the city being edited. 
	 */
	public String getRegionName(){
		return regionName;
	} // end getJbtEnter
	
	/**
	 * The main panel for the AddEditCityView.
	 * It puts all textfields and labels into a grid layout. 
	 *
	 */
	public class AddEditBasicPanel extends JPanel{

		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -1208047762479637389L;

		/**
		 * Constructor for the AddEditBasical Panel.
		 */
		public AddEditBasicPanel(){
			//set values for JComboBox
			if(model != null && model.getAllCountries() != null){
				String[] countryList = new String[model.getAllCountries().size()];

				for(int i = 0; i < model.getAllCountries().size(); ++i){
					countryList[i] = model.getAllCountries().get(i).getName();
				}
				jcbCountries = new JComboBox<String>(countryList);
			}
			
			//Panel to group labels
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(7,1));
			
			//Add labels to panel
			panel1.add(new JLabel("Name: "));
			panel1.add(new JLabel("Area: "));
			panel1.add(new JLabel("Population: "));
			panel1.add(new JLabel("Latitude (ex. N23.5): "));
			panel1.add(new JLabel("Longitude (ex. W41.5): "));
			panel1.add(new JLabel("Elevation: "));
			panel1.add(new JLabel("Country: "));
			
			//Panel to group text fields
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(7,1));
			
			//Add textfields to panel
			panel2.add(jtfName);
			panel2.add(jtfArea);
			panel2.add(jtfPopulation);
			panel2.add(jtfLatitude);
			panel2.add(jtfLongitude);
			panel2.add(jtfElevation);
			panel2.add(jcbCountries);
			
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
} // end AddEditCityView
