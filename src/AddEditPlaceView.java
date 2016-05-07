import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * The view to add/edit a Place of Interest.
 * @version 1.0
 */
public class AddEditPlaceView extends JFrame{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 1445748264990590110L;
	
	/** Variables to house different components of the view. */
	private AddEditBasicPanel basicPanel;
	private JTextField jtfName = new JTextField();
	private JTextField jtfArea = new JTextField();
	private JTextField jtfType = new JTextField();
	private JButton jbtEnter = new JButton("Enter");
	private JComboBox<String> jcbContinents = new JComboBox<String>();
	private JComboBox<String> jcbCities = new JComboBox<String>();
	private JList<String> jlCountries = new JList<String>();
	private JScrollPane jspCountryList = new JScrollPane();
	private DefaultListModel<String> countryListModel = new DefaultListModel<String>();
	
	//Saves original name of place if this is one being edited
	private String regionName = "";
	
	//Variable to hold the model this view will be gathering information from. 
	private RegionModel model;
	
	//Variable hold the user's place region location choice 
	private int regionChoice = -1;
	
	/**
	 * Constructor designed for adding a place of interest.
	 * 
	 * @param model Model this view is gathering information from. 
	 * @param regionChoice Int chooses where the place should be located.
	 */
	public AddEditPlaceView(RegionModel model, int regionChoice){
		//Sets the model and region choice for the view.
		this.model = model;
		this.regionChoice = regionChoice;
		
		//Add main panel and display view. 
		basicPanel = new AddEditBasicPanel();
		add(basicPanel);
		setTitle("Add Place of Interest");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,230);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView
	
	/**
	 * Constructor specifically for editing a place of interest.
	 * 
	 * @param place Place being edited. 
	 * @param model Model this view is gathering information from.
	 * @param regionChoice Region location choice for the place. 
	 */
	public AddEditPlaceView(PlaceOfInterest place, RegionModel model, int regionChoice){
		//Sets index, model, and region choice
		this.regionName = place.getName();
		this.model = model;
		this.regionChoice = regionChoice;
		
		//Constructs the main panel for the view
		basicPanel = new AddEditBasicPanel();
		
		//Pre-populate text fields with existing data
		jtfName.setText(place.getName());
		jtfArea.setText(String.valueOf(place.getArea()));
		jtfType.setText(place.getTypeOfPlace());
		
		//If region choice is one it already has, set selected item
		if(regionChoice == 0){
			for(Continent continent: model.getAllContinents()){
				if(place.getPoliticalRegionList().get(0).equals(continent)){
					jcbContinents.setSelectedItem(continent.getName());
				}
			}
		} // end continent
		if(regionChoice == 1){
			for(City city: model.getAllCities()){
				if(place.getPoliticalRegionList().get(0).equals(city)){
					jcbContinents.setSelectedItem(city.getName());
				}
			}
		} // end continent
		int[] indices = new int[place.getPoliticalRegionList().size()];
		if(regionChoice == 2){
			for(int i = 0; i < place.getPoliticalRegionList().size(); ++i){
				for(int j = 0; j < model.getAllCountries().size(); ++j){
					if(place.getPoliticalRegionList().get(i).equals(model.getAllCountries().get(j))){
						indices[i] = j; 
					}
				}
			}
			jlCountries.setSelectedIndices(indices);
		} // end country
		
		//Add components to view and display it.
		add(basicPanel);
		setTitle("Edit Place Of Interest");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,230);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView with parameters
	
	/**
	 * An accessor method for the main panel of the view. 
	 * @return Reference to the main panel of the view. 
	 */
	public AddEditBasicPanel getBasicPanel(){
		return basicPanel;
	} // end getBasicPanel
	
	/**
	 * An accessor method for the JComboBox of
	 * continent names. 
	 * @return Reference to the combobox of continent
	 * names. 
	 */
	public JComboBox<String> getJcbContinents(){
		return jcbContinents;
	} // end getJcbContinents
	
	/**
	 * An accessor method for the JComboBox of
	 * city names. 
	 * @return Reference to the combobox of cit 
	 * names. 
	 */
	public JComboBox<String> getJcbCities(){
		return jcbCities;
	} // end getJcbCities
	
	/**
	 * An accessor method for the JList of 
	 * country names. 
	 * @return Reference to the JList of country names.
	 */
	public JList<String> getJlCountries(){
		return jlCountries;
	} // end getJlCountries
	
	/**
	 * An accessor method for the list of countries
	 * model. 
	 * @return Reference to the list of countries
	 * model. 
	 */
	public DefaultListModel<String> getCountryListModel(){
		return countryListModel;
	} // end getCountryListModel
	
	/**
	 * An accessor method for the name textfield. 
	 * @return Reference to the name textfield.
	 */
	public JTextField getJtfName(){
		return jtfName;
	} // end getJtfName
	
	/**
	 * An accessor method for the area textfield. 
	 * @return Reference to the area textfield. 
	 */
	public JTextField getJtfArea(){
		return jtfArea;
	} // end get JtfArea
	
	/**
	 * An accessor method for the type textfield.
	 * @return Reference to the type textfield. 
	 */
	public JTextField getJtfType(){
		return jtfType;
	} // end getJtfPopulation
	
	/**
	 * An accessor method for the enter button. 
	 * @return Reference to the enter button. 
	 */
	public JButton getJbtEnter(){
		return jbtEnter;
	} // end getJbtEnter
	
	/**
	 * An accessor method for the original name of the
	 * place being edited. 
	 * @return Original of place being edite.d 
	 */
	public String getRegionName(){
		return regionName;
	} // end getJbtEnter
	
	/**
	 *  An accessor method for the region location
	 *  choice of the place.
	 * @return Region location choice of place. 
	 */
	public int getRegionChoice(){
		return regionChoice;
	} // end getRegionChoice
	
	/**
	 * The main panel for the view.
	 * Puts all textfield and jlabels into a grid layout. 
	 *
	 */
	public class AddEditBasicPanel extends JPanel{
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -1208047762479637389L;

		/**
		 * Constructor for the main panel of the view. 
		 */
		public AddEditBasicPanel(){
			//Set values for JComboBox Continents if that was selected
			if(regionChoice == 0){
				if(model != null && model.getAllContinents() != null){
					String[] continentList = new String[model.getAllContinents().size()];
	
					for(int i = 0; i < model.getAllContinents().size(); ++i){
						continentList[i] = model.getAllContinents().get(i).getName();
					}
					jcbContinents = new JComboBox<String>(continentList);
				}
			} // end create continent combo box
			//Set values for JComboBox Cities if that was selected
			if(regionChoice == 1){
				if(model != null && model.getAllCities() != null){
					String[] cityList = new String[model.getAllCities().size()];
	
					for(int i = 0; i < model.getAllCities().size(); ++i){
						cityList[i] = model.getAllCities().get(i).getName();
					}
					jcbCities = new JComboBox<String>(cityList);
				}
			} // end create city combobox
			//Set values for JList of countries if that was selected
			if(regionChoice == 2){
				countryListModel = new DefaultListModel<String>();
				if(model != null && model.getAllCountries() != null){
					for(int i = 0; i < model.getAllCountries().size(); ++i){
						//Adds the name of each country to the list model
						countryListModel.addElement(model.getAllCountries().get(i).getName());
					}
				}
				//Create JList from country names
				jlCountries = new JList<String>(countryListModel);
				
				//Enable multi-select on JList
				jlCountries.setSelectionMode(
						ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
				// Make JList for region objects able to scroll
				jspCountryList = new JScrollPane(jlCountries);
			} // end create country list
			
			
			//Panel to group labels
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(6,1));
			
			//Add labels to panel
			panel1.add(new JLabel("Name: "));
			panel1.add(new JLabel("Area: "));
			panel1.add(new JLabel("Type: "));
			panel1.add(new JLabel("Continent: "));
			panel1.add(new JLabel("City: "));
			panel1.add(new JLabel("Country(ies): "));
			
			//Panel to group text fields
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(6,1));
			
			//Add textfields to panel
			panel2.add(jtfName);
			panel2.add(jtfArea);
			panel2.add(jtfType);
			panel2.add(jcbContinents);
			panel2.add(jcbCities);
			panel2.add(jspCountryList);
			
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
} // end AddEditPlaceView class
