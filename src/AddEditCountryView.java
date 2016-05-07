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
 * The view to add/edit a Country.
 * @version 1.0
 */
public class AddEditCountryView extends JFrame {
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 1445748264990590110L;

	/**Variables to hold different components of the view */
	private AddEditBasicPanel basicPanel;
	private JTextField jtfName = new JTextField();
	private JTextField jtfArea = new JTextField();
	private JTextField jtfPopulation = new JTextField();
	private JButton jbtEnter = new JButton("Enter");
	private JComboBox<String> jcbContinents = new JComboBox<String>();
	
	//Saves original name of country if this is one being edited
	private String regionName = "";
	
	//The model this view will get information from. 
	private RegionModel model;
	
	/**
	 * Constructor specifically created as an 
	 * add Country view. 
	 * @param model Model this view will get information from. 
	 */
	public AddEditCountryView(RegionModel model){
		//Sets the model
		this.model = model;
		
		//Constructs main panel of the view
		basicPanel = new AddEditBasicPanel();
		
		//Adds panel to view and displays frame.
		add(basicPanel);
		setTitle("Add Country");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,150);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView
	
	/**
	 * A constructor designed to be used
	 * when a Country is being edited. 
	 * 
	 * @param country Country being edited.
	 * @param model Model this view is gathering information from. 
	 */
	public AddEditCountryView(Country country, RegionModel model){
		//Sets regionName and model variables
		this.regionName = country.getName();
		this.model = model;
		
		//Constructs main panel of the view
		basicPanel = new AddEditBasicPanel();
		
		//Prepopulate text fields with existing data
		jtfName.setText(country.getName());
		jtfArea.setText(String.valueOf(country.getArea()));
		jtfPopulation.setText(String.valueOf(country.getPopulation()));
		jcbContinents.setSelectedItem(country.getContinentLocation().getName());
		
		//Add panel to the view and display the frame. 
		add(basicPanel);
		setTitle("Edit Country");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,150);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditCountryView with parameters
	
	/**
	 * An accessor method for the main panel 
	 * of the view.
	 * @return Main panel of the view. 
	 */
	public AddEditBasicPanel getBasicPanel(){
		return basicPanel;
	} // end getBasicPanel
	
	/**
	 * An accessor method for the combobox of
	 * continents.
	 * @return Reference to JComboBox of continents. 
	 */
	public JComboBox<String> getJcbContinents(){
		return jcbContinents;
	} // end getJcbContinents
	
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
	 * An accessor method for the population textfield.
	 * @return Reference to the population textfield. 
	 */
	public JTextField getJtfPopulation(){
		return jtfPopulation;
	} // end getJtfPopulation
	
	/**
	 * An accessor method for the enter button.
	 * @return Reference to the enter button. 
	 */
	public JButton getJbtEnter(){
		return jbtEnter;
	} // end getJbtEnter
	
	/**
	 * An accessor method for the original name
	 * of the country being edited. 
	 * @return Original name of country being edited. 
	 */
	public String getRegionName(){
		return regionName;
	} // end getJbtEnter
	
	/**
	 * Class containing main panel of this view.
	 * Houses all textfields and labels and organizes
	 * them in a grid layout. 
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
			if(model != null && model.getAllContinents() != null){
				String[] continentList = new String[model.getAllContinents().size()];
				//Adds name of all continents to combobox
				for(int i = 0; i < model.getAllContinents().size(); ++i){
					continentList[i] = model.getAllContinents().get(i).getName();
				}
				jcbContinents = new JComboBox<String>(continentList);
			}
			
			//Panel to group labels
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(4,1));
			
			//Add labels to panel
			panel1.add(new JLabel("Name: "));
			panel1.add(new JLabel("Area: "));
			panel1.add(new JLabel("Population: "));
			panel1.add(new JLabel("Continent: "));
			
			//Panel to group text fields
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(4,1));
			
			//Add textfields to panel
			panel2.add(jtfName);
			panel2.add(jtfArea);
			panel2.add(jtfPopulation);
			panel2.add(jcbContinents);
			
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
} // end AddEditCountryView class
