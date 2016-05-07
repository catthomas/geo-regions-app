import javax.swing.*;

import java.awt.GridLayout;
import java.awt.BorderLayout;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * The view to add/edit a Continent.
 * @version 1.0
 */
public class AddEditContinentView extends JFrame{	
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 1445748264990590110L;
	
	/**Variables to contain the components of the view. */
	private AddEditBasicPanel basicPanel;
	private JTextField jtfName = new JTextField();
	private JTextField jtfArea = new JTextField();
	private JTextField jtfPopulation = new JTextField();
	private JButton jbtEnter = new JButton("Enter");
	
	/** Variable to save the name of continent if this is one being edited. */
	private String regionName = "";
	
	/**
	 * Constructor specifically for adding a Continent.
	 * Displays an add continent view. 
	 */
	public AddEditContinentView(){
		//Construct main panel
		basicPanel = new AddEditBasicPanel();
		
		//Add main panel to the frame, title, and display frame
		add(basicPanel);
		setTitle("Add Continent");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,150);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditContinentView
	
	/**
	 * Constructor specifically for editing a
	 * continents. All fields are prepopulated with information.
	 * @param continent Continent being edited.
	 */
	public AddEditContinentView(Continent continent){
		//Assign continent's index to class variable
		this.regionName = continent.getName();
		
		//Construct main panel of edit view
		basicPanel = new AddEditBasicPanel();
		
		//Prepopulate text fields with existing data
		jtfName.setText(continent.getName());
		jtfArea.setText(String.valueOf(continent.getArea()));
		jtfPopulation.setText(String.valueOf(continent.getPopulation()));
		
		//Add panel and display view
		add(basicPanel);
		setTitle("Edit Continent");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,150);
		setLocationRelativeTo(null);
		setVisible(true);
	} // end AddEditContinentView with parameters
	
	/**
	 * An accessor method for the 
	 * main panel of the view.
	 * @return Reference to the main panel of the view.
	 */
	public AddEditBasicPanel getBasicPanel(){
		return basicPanel;
	} // end getBasicPanel
	
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
	 * An accessor method for the population textfield
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
	 * An accessor method for the continent's original name.
	 * @return Original name of the continent.
	 */
	public String getRegionName(){
		return regionName;
	} // end getJbtEnter
	
	/**
	 * This class houses the main panel
	 * for the AddEditContinentView. 
	 *
	 */
	public class AddEditBasicPanel extends JPanel{
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -1208047762479637389L;
		
		/**
		 * Constructor for the basic panel.
		 */
		public AddEditBasicPanel(){
			//Panel to group labels
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(3,1));
			
			//Add labels to panel
			panel1.add(new JLabel("Name: "));
			panel1.add(new JLabel("Area: "));
			panel1.add(new JLabel("Population: "));
			
			//Panel to group text fields
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(3,1));
			
			//Add textfields to panel
			panel2.add(jtfName);
			panel2.add(jtfArea);
			panel2.add(jtfPopulation);
			
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
} // end AddEditContinentView
