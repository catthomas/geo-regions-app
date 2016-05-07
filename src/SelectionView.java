import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.event.*;

/**
 * Project #5
 *  CS 2334
 * Section 10 
 * April 22, 2014 
 * The main view for our geographic region GUI. It has several panels displaying 
 * information about different geographic regions as well as places for user 
 * input and commands. It listens to the model (RegionModel) and notifies 
 * the controller (RegionController) of user actions.
 * 
 * @version 1.0
 */
public class SelectionView extends JFrame implements ActionListener{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = -6883313166622058684L;
	
	/** Variable that contains model the view is listening to. */
	private RegionModel model;
	
	/** Panels to contain corresponding region data. */
	private ListAndButtonPanel continentPanel;
	private ListAndButtonPanel countryPanel;
	private ListAndButtonPanel cityPanel;
	private ListAndButtonPanel pointOIPanel;
	private ListAndButtonPanel placeOIPanel;
	/** Variable to contain menu. */
	private Menu menu;
	
	
	/**
	 * Constructs the selection view. 
	 */
	public SelectionView() {
		// Construct menu
		menu = new Menu();
		
		//Construct region panels. 
		continentPanel = new ListAndButtonPanel("Continents");
		countryPanel = new ListAndButtonPanel("Countries");
		cityPanel = new ListAndButtonPanel("Cities");
		pointOIPanel = new ListAndButtonPanel(
				"Points of Interest");
		placeOIPanel = new ListAndButtonPanel(
				"Places of Interest");

		// setFrameSettings
		this.setLayout(new BorderLayout());
		this.setSize(1300, 300);
		this.setMinimumSize(new Dimension(1000, 100));
		this.setLocationRelativeTo(null);
		this.setTitle("GeoGrapher");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create panel for lists and buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 5));

		
		panel.add(continentPanel);
		panel.add(countryPanel);
		panel.add(cityPanel);
		panel.add(placeOIPanel);
		panel.add(pointOIPanel);
		
		// add Components
		this.add(menu, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);

		// set Frame to visible
		this.setVisible(true);

	}// end slectionView constructor
	
	/**
	 * An accessor for the view's model.
	 * @return Model the view is listening to. 
	 */
	public RegionModel getRegionModel() {
		return model;
	}// end getRegionModel method
	
	/**
	 * An accessor for the panel of continents.
	 * @return Panel of continents. 
	 */
	public ListAndButtonPanel getContinentPanel(){
		return continentPanel;
	} // end getContinentPanel
	
	/**
	 * An accessor for the panel of countries. 
	 * @return Panel of countries. 
	 */
	public ListAndButtonPanel getCountryPanel(){
		return countryPanel;
	} // end getCountryPanel
	
	/**
	 * An accessor for the panel of cites. 
	 * @return Panel of cities. 
	 */
	public ListAndButtonPanel getCityPanel(){
		return cityPanel;
	} // end getCityPanel
	
	/**
	 * An accessor for the panel of points of interest.
	 * @return Panel of points of interest. 
	 */
	public ListAndButtonPanel getPointOIPanel(){
		return pointOIPanel;
	} // end getPointOIPanel
	
	/**
	 * An accessor for the panel of places of interest.
	 * @return Panel of places of interest.
	 */
	public ListAndButtonPanel getPlaceOIPanel(){
		return placeOIPanel;
	} // end getPlaceOIPanel
	
	/**
	 * An accessor for the menu.
	 * @return Menu of the selection view. 
	 */
	public Menu getMenu(){
		return menu;
	} // end getMenu
	
	/**
	 * Method that catches events from the model and then updates
	 * the selection view. 
	 */
	public void actionPerformed(ActionEvent actionEvent){
		//Arrange the lists alphabetically, then refresh all region panels.
		Collections.sort(model.getAllContinents());
		continentPanel.setRegionList(model.getAllContinents());
		Collections.sort(model.getAllCountries());
		countryPanel.setRegionList(model.getAllCountries());
		Collections.sort(model.getAllCities());
		cityPanel.setRegionList(model.getAllCities());
		Collections.sort(model.getAllPointsOfInterest());
		pointOIPanel.setRegionList(model.getAllPointsOfInterest());
		Collections.sort(model.getAllPlacesOfInterest());
		placeOIPanel.setRegionList(model.getAllPlacesOfInterest());
		
		//TODO: add tooltips for grayed out things and add
		// gray buttons for new installments (gn check view, list, etc.)

		//Enable and disable buttons based on data in the model.
		if(model.getAllContinents().size() > 0){
			//Enable edit, delete for continents
			continentPanel.getJBTNPanel().getJBTDelete().setEnabled(true);
			continentPanel.getJBTNPanel().getJBTEdit().setEnabled(true);
			
			//Enable add country button
			countryPanel.getJBTNPanel().getJBTAdd().setEnabled(true);
			
			//Enable add place button
			placeOIPanel.getJBTNPanel().getJBTAdd().setEnabled(true);
			
			//Enable add point button
			pointOIPanel.getJBTNPanel().getJBTAdd().setEnabled(true);
			
			//Enable save and export data
			menu.getJMISave().setEnabled(true);
			menu.getJMIExport().setEnabled(true);
			
			//Enable graph options
			menu.getjmiAreaBarContinent().setEnabled(true);
			menu.getjmiPopBarContinent().setEnabled(true);
			
			//Check if there are any countries, enable corresponding buttons
			if(model.getAllCountries().size() >0){
				//Enable edit, delete country button
				countryPanel.getJBTNPanel().getJBTDelete().setEnabled(true);
				countryPanel.getJBTNPanel().getJBTEdit().setEnabled(true);
				
				//Enable add city button
				cityPanel.getJBTNPanel().getJBTAdd().setEnabled(true);
				
				//Enable All country graph options
				menu.getjmiAreaBarCountry().setEnabled(true);
				menu.getjmiPopBarCountry().setEnabled(true);
				menu.getjmiStackedPopCou_Con().setEnabled(true);
				menu.getjmiStackedAreaCou_Con().setEnabled(true);
			} // end if statement
			else{ // No countries, disable corresponding buttons
				//Disable edit, delete country button
				countryPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
				countryPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
				
				//Disable add city here
				cityPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
				
				//Disable All country graph options
				menu.getjmiAreaBarCountry().setEnabled(false);
				menu.getjmiPopBarCountry().setEnabled(false);
				menu.getjmiStackedPopCou_Con().setEnabled(false);
				menu.getjmiStackedAreaCou_Con().setEnabled(false);
			}
			
			//Check if there are any cities, enable corresponding buttons
			if(model.getAllCities().size() > 0){
				//Enable edit, delete city button
				cityPanel.getJBTNPanel().getJBTDelete().setEnabled(true);
				cityPanel.getJBTNPanel().getJBTEdit().setEnabled(true);
				
				//Enable City map options
				menu.getjmiCitiesWithinCountries().setEnabled(true);
				menu.getjmiCitiesWorldWide().setEnabled(true);
				
				//Enable city graph options
				menu.getjmiAreaBarCity().setEnabled(true);
				menu.getjmiPopBarCity().setEnabled(true);
				menu.getjmiStackedAreaCit_Cou().setEnabled(true);
				menu.getjmiStackedPopCit_Cou().setEnabled(true);
				
				//Enable neighborhood buttons
				menu.getjmiNeighborhoodCheck().setEnabled(true);
				menu.getjmiNeighborhoodList().setEnabled(true);
				menu.getjmiNeighborhoodMap().setEnabled(true);
				menu.getjmiRecNeighborhoodCheck().setEnabled(true);
				menu.getjmiRecNeighborhoodList().setEnabled(true);
				menu.getjmiRecNeighborhoodMap().setEnabled(true);
			} // end if statement
			else{ // No cities, disable corresponding buttons
				//Disable edit, delete city button
				cityPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
				cityPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
				
				//Disable City map options
				menu.getjmiCitiesWithinCountries().setEnabled(false);
				menu.getjmiCitiesWorldWide().setEnabled(false);
				
				//Disable city graph options
				menu.getjmiAreaBarCity().setEnabled(false);
				menu.getjmiPopBarCity().setEnabled(false);
				menu.getjmiStackedAreaCit_Cou().setEnabled(false);
				menu.getjmiStackedPopCit_Cou().setEnabled(false);
				
				//If there are also no points, disable neighborhood buttons
				if(model.getAllPointsOfInterest().size() == 0){
					//Disable neighborhood buttons
					menu.getjmiNeighborhoodCheck().setEnabled(false);
					menu.getjmiNeighborhoodList().setEnabled(false);
					menu.getjmiNeighborhoodMap().setEnabled(false);
					menu.getjmiRecNeighborhoodCheck().setEnabled(false);
					menu.getjmiRecNeighborhoodList().setEnabled(false);
					menu.getjmiRecNeighborhoodMap().setEnabled(false);
				}
			}
			
			//Check if there are any places, enable corresponding buttons
			if(model.getAllPlacesOfInterest().size() >0){
				//Enable edit, delete place button
				placeOIPanel.getJBTNPanel().getJBTDelete().setEnabled(true);
				placeOIPanel.getJBTNPanel().getJBTEdit().setEnabled(true);
				
				//Enable place graph options
				menu.getjmiAreaBarPlacesOfInterest().setEnabled(true);
				menu.getjmiStackedAreaPloi_Cit().setEnabled(true);
				menu.getjmiStackedAreaPloi_Con().setEnabled(true);
				menu.getjmiStackedAreaPloi_Cou().setEnabled(true);
			} // end if statement 
			else{ // no places, disable corresponding buttons
				//Disable edit, delete place button
				placeOIPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
				placeOIPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
				
				//Disable place graph options
				menu.getjmiAreaBarPlacesOfInterest().setEnabled(false);
				//Disable place graph options
				menu.getjmiStackedAreaPloi_Cit().setEnabled(false);
				menu.getjmiStackedAreaPloi_Con().setEnabled(false);
				menu.getjmiStackedAreaPloi_Cou().setEnabled(false);
			}
			
			//Check if there are any points, enable corresponding buttons
			if(model.getAllPointsOfInterest().size() >0){
				//Enable edit, delete point button
				pointOIPanel.getJBTNPanel().getJBTDelete().setEnabled(true);
				pointOIPanel.getJBTNPanel().getJBTEdit().setEnabled(true);
				
				//Enable point map options
				menu.getjmiPOIWithinCities().setEnabled(true);
				menu.getjmiPOIWithinContinents().setEnabled(true);
				menu.getjmiPOIWithinCountries().setEnabled(true);
				menu.getjmiPOIWorldWide().setEnabled(true);
				
				//Enable neighborhood buttons
				menu.getjmiNeighborhoodCheck().setEnabled(true);
				menu.getjmiNeighborhoodList().setEnabled(true);
				menu.getjmiNeighborhoodMap().setEnabled(true);
				menu.getjmiRecNeighborhoodCheck().setEnabled(true);
				menu.getjmiRecNeighborhoodList().setEnabled(true);
				menu.getjmiRecNeighborhoodMap().setEnabled(true);
			} // end if statement
			else{ // No points, disable corresponding buttons
				//Disable edit, delete point button
				pointOIPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
				pointOIPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
				
				//Disable point map options
				menu.getjmiPOIWithinCities().setEnabled(false);
				menu.getjmiPOIWithinContinents().setEnabled(false);
				menu.getjmiPOIWithinCountries().setEnabled(false);
				menu.getjmiPOIWorldWide().setEnabled(false);
				
				//If there are also no cities, disable neighborhood buttons
				if(model.getAllCities().size() == 0){
					//Disable neighborhood buttons
					menu.getjmiNeighborhoodCheck().setEnabled(false);
					menu.getjmiNeighborhoodList().setEnabled(false);
					menu.getjmiNeighborhoodMap().setEnabled(false);
					menu.getjmiRecNeighborhoodCheck().setEnabled(false);
					menu.getjmiRecNeighborhoodList().setEnabled(false);
					menu.getjmiRecNeighborhoodMap().setEnabled(false);
				}
			}
		} // end if statement
		else{
			//There are no regions in the model, disable buttons appropriately
			//Disable edit, delete continent buttons
			continentPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
			continentPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
			
			//Disable add, edit, delete country buttons
			countryPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
			countryPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
			countryPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
			
			//Disable add, edit, delete city buttons
			cityPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
			cityPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
			cityPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
			
			//Disable add, edit, delete places buttons
			placeOIPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
			placeOIPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
			placeOIPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
			
			//Disable add, edit, delete points buttons
			pointOIPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
			pointOIPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
			pointOIPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
			
			//Disable Save and Export in file menu
			menu.getJMISave().setEnabled(false);
			menu.getJMIExport().setEnabled(false);
			
			//Disable map option
			menu.getjmiCitiesWithinCountries().setEnabled(false);
			menu.getjmiCitiesWorldWide().setEnabled(false);
			menu.getjmiPOIWithinCities().setEnabled(false);
			menu.getjmiPOIWithinContinents().setEnabled(false);
			menu.getjmiPOIWithinCountries().setEnabled(false);
			menu.getjmiPOIWorldWide().setEnabled(false);
			
			//Disable graph buttons
			//All city graph options
			menu.getjmiAreaBarCity().setEnabled(false);
			menu.getjmiPopBarCity().setEnabled(false);
			menu.getjmiStackedAreaCit_Cou().setEnabled(false);
			menu.getjmiStackedPopCit_Cou().setEnabled(false);
			
			//All Continent graph options
			menu.getjmiAreaBarContinent().setEnabled(false);
			menu.getjmiPopBarContinent().setEnabled(false);
			
			//All country graph options
			menu.getjmiAreaBarCountry().setEnabled(false);
			menu.getjmiPopBarCountry().setEnabled(false);
			menu.getjmiStackedPopCou_Con().setEnabled(false);
			menu.getjmiStackedAreaCou_Con().setEnabled(false);
			
			//All place graph options
			menu.getjmiAreaBarPlacesOfInterest().setEnabled(false);
			menu.getjmiStackedAreaPloi_Cit().setEnabled(false);
			menu.getjmiStackedAreaPloi_Con().setEnabled(false);
			menu.getjmiStackedAreaPloi_Cou().setEnabled(false);
			
			//Disable neighborhood buttons
			menu.getjmiNeighborhoodCheck().setEnabled(false);
			menu.getjmiNeighborhoodList().setEnabled(false);
			menu.getjmiNeighborhoodMap().setEnabled(false);
			menu.getjmiRecNeighborhoodCheck().setEnabled(false);
			menu.getjmiRecNeighborhoodList().setEnabled(false);
			menu.getjmiRecNeighborhoodMap().setEnabled(false);
		} // end else statement
	} // end actionPerformed
	
	/**
	 * This method sets the model for this view and registers the view
	 * as a listener with the model.
	 * It also populates the lists of regions. 
	 * @param model The model to be set. 
	 */
	public void setModel(RegionModel model) {
		this.model = model;
		
		//Populate JLists with region names (should all be blank)
		continentPanel.setRegionList(model.getAllContinents());
		countryPanel.setRegionList(model.getAllCountries());
		cityPanel.setRegionList(model.getAllCities());
		pointOIPanel.setRegionList(model.getAllPointsOfInterest());
		placeOIPanel.setRegionList(model.getAllPlacesOfInterest());
		
		//TODO: add tooltips for grayed out things and add
		// gray buttons for new installments (gn check view, list, etc.) ^delete that?
		
		//Register the view as listener for the model
		if(this.model != null){
			model.addActionListener(this);
		}
		
		//Set button enabled/disabled to default (model is blank)
		//Disable edit, delete continent buttons
		continentPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
		continentPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
		
		//Disable add, edit, delete country buttons
		countryPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
		countryPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
		countryPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
		
		//Disable add, edit, delete city buttons
		cityPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
		cityPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
		cityPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
		
		//Disable add, edit, delete places buttons
		placeOIPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
		placeOIPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
		placeOIPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
		
		//Disable add, edit, delete points buttons
		pointOIPanel.getJBTNPanel().getJBTAdd().setEnabled(false);
		pointOIPanel.getJBTNPanel().getJBTEdit().setEnabled(false);
		pointOIPanel.getJBTNPanel().getJBTDelete().setEnabled(false);
		
		//Disable Save and Export in file menu
		menu.getJMISave().setEnabled(false);
		menu.getJMIExport().setEnabled(false);
		
		//Disable map option
		menu.getjmiCitiesWithinCountries().setEnabled(false);
		menu.getjmiCitiesWorldWide().setEnabled(false);
		menu.getjmiPOIWithinCities().setEnabled(false);
		menu.getjmiPOIWithinContinents().setEnabled(false);
		menu.getjmiPOIWithinCountries().setEnabled(false);
		menu.getjmiPOIWorldWide().setEnabled(false);
		
		//Disable graph buttons
		menu.getjmiAreaBarCity().setEnabled(false);
		menu.getjmiAreaBarContinent().setEnabled(false);
		menu.getjmiAreaBarCountry().setEnabled(false);
		menu.getjmiAreaBarPlacesOfInterest().setEnabled(false);
		menu.getjmiPopBarCity().setEnabled(false);
		menu.getjmiPopBarContinent().setEnabled(false);
		menu.getjmiPopBarCountry().setEnabled(false);
		menu.getjmiStackedAreaCit_Cou().setEnabled(false);
		menu.getjmiStackedAreaCou_Con().setEnabled(false);
		menu.getjmiStackedAreaPloi_Cit().setEnabled(false);
		menu.getjmiStackedAreaPloi_Con().setEnabled(false);
		menu.getjmiStackedAreaPloi_Cou().setEnabled(false);
		menu.getjmiStackedPopCit_Cou().setEnabled(false);
		menu.getjmiStackedPopCou_Con().setEnabled(false);
		
		//Disable neighborhood buttons
		menu.getjmiNeighborhoodCheck().setEnabled(false);
		menu.getjmiNeighborhoodList().setEnabled(false);
		menu.getjmiNeighborhoodMap().setEnabled(false);
		menu.getjmiRecNeighborhoodCheck().setEnabled(false);
		menu.getjmiRecNeighborhoodList().setEnabled(false);
		menu.getjmiRecNeighborhoodMap().setEnabled(false);
	}// end setRegionModel

	/**
	 * Class that contains the panel for the add edit and delete buttons. 
	 *
	 */
	public class AddEditDeletePanel extends JPanel {
		private static final long serialVersionUID = 399495427971842892L;
		//Create add, edit, and delete buttons
		private JButton jbtAdd = new JButton("Add");
		private JButton jbtEdit = new JButton("Edit");
		private JButton jbtDelete = new JButton("Delete");
		
		public AddEditDeletePanel(String regionType) {
			// Sets the corresponding tooltip to each button
			jbtAdd.setToolTipText("Add " + regionType + " to the list");
			jbtEdit.setToolTipText("Edit " + regionType + " from the list");
			jbtDelete.setToolTipText("Delete " + regionType + "from the list"
					+ "\n"
					+ "(!Also Deletes all regions located within the region!)");

			// Add buttons to panel 1
			this.setLayout(new GridLayout(0, 3));
			this.add(jbtAdd);
			this.add(jbtEdit);
			this.add(jbtDelete);

		}// end ButtonPanel Constructor
		
		/**
		 * An accessor for the add button on the region panel.
		 * @return JButton that can be pressed by the user to add a region.
		 */
		public JButton getJBTAdd(){
			return jbtAdd;
		} // end getJBTAdd
		
		/**
		 * An accessor for the edit button on the region panel.
		 * @return JButton that can be pressed by the user to edit a region.
		 */
		public JButton getJBTEdit(){
			return jbtEdit;
		} // end getJBTEdit
		
		/**
		 * An accessor for the delete button on the region panel.
		 * @return JButton that can be pressed by the user to delete a region.
		 */
		public JButton getJBTDelete(){
			return jbtDelete;
		} // end getJBTDelete

	}// end ButtonPanel class

	/**
	 * Class that contains the panel which has region names
	 * and add, edit, delete buttons. 
	 *
	 */
	public class ListAndButtonPanel extends JPanel {
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = 800769674190701132L;
		/** Variables to contain different components of the view. */
		private ArrayList<? extends Region> regionList;
		private DefaultListModel<String> regionListModel = new DefaultListModel<String>();
		private JList<String> jlRegionList = new JList<String>(regionListModel);
		private AddEditDeletePanel jbtnPanel;
		
		public ListAndButtonPanel(String regionType) {
			//Enable multi-select on JList
			jlRegionList.setSelectionMode(
					ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			// Make JList for region objects able to scroll
			JScrollPane scrollableRegionList = new JScrollPane(jlRegionList);

			// create Title border for panel 2
			// Credit to:
			// http://docs.oracle.com/javase/tutorial/uiswing/components/border.html
			Border blackline = BorderFactory.createLineBorder(Color.black);
			TitledBorder panelTwoBorder;
			panelTwoBorder = BorderFactory.createTitledBorder(blackline,
					regionType, TitledBorder.CENTER, TitledBorder.ABOVE_TOP);

			// Create the panel containing the JList and buttons
			this.setLayout((new BorderLayout()));
			this.setBorder(panelTwoBorder);
			this.setToolTipText("This is a list of " + regionType + ". " + "\n"
					+ " You may add, edit, and delete items "
					+ "from this list using the buttons below");
			this.add(scrollableRegionList, BorderLayout.CENTER);
			this.jbtnPanel = new AddEditDeletePanel(regionType);
			this.add(jbtnPanel, BorderLayout.SOUTH);

		}// end listAndButtonPanel constructor
		
		/**
		 * An accessor method for the region list.
		 * @return RegionList that is being displayed. 
		 */
		public ArrayList<? extends Region> getRegionList() {
			return regionList;
		}// end getRegionList method
		
		/**
		 * An accessor method for getting the add/edit/delete panel.
		 * @return Panel with add/edit/delete buttons. 
		 */
		public AddEditDeletePanel getJBTNPanel(){
			return jbtnPanel;
		} // end getJBTNPanel
		
		/**
		 * An accessor method for the Jlist of region names. 
		 * @return Jlist of region names.
		 */
		public JList<String> getJlRegionList(){
			return jlRegionList;
		} // end getJlRegionList
		
		/**
		 * A mutator method for the list of regions
		 * being displayed. 
		 * @param regionList New list of regions to be displayed. 
		 */
		public void setRegionList(ArrayList<? extends Region> regionList){
			this.regionList = regionList;
			//Populate JList with region names
			this.populateRegionJList();
		} // end setRegionList
		
		/**
		 * This method pulls the names of the regions from the region list
		 * and stores them in the JList of JList of regions
		 */
		private void populateRegionJList() {
			regionListModel.clear();
			if(regionList != null){
				for(int i = 0; i < regionList.size(); ++i){
					//Adds the name of each region to the list model
					regionListModel.addElement(regionList.get(i).getName());
				}
			}
		}// end setRegionList method

	}// end listAndButtonPanel class

	/**
	 * Class that contains information to be displayed
	 * in the menu bar. 
	 *
	 */
	public class Menu extends JMenuBar {
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -7156605665552631565L;
		/** JMenuItems to hold load, save, import, and export options. */
		private JMenuItem jmiLoad;
		private JMenuItem jmiSave;
		private JMenuItem jmiImport;
		private JMenuItem jmiExport;
		private JMenuItem jmiCitiesWithinCountries;
		private JMenuItem jmiCitiesWorldWide;
		private JMenuItem jmiPOIWithinCities;
		private JMenuItem jmiPOIWithinCountries;
		private JMenuItem jmiPOIWithinContinents;
		private JMenuItem jmiPOIWorldWide;
		private JMenuItem jmiAreaBarContinent;
		private JMenuItem jmiAreaBarCountry;
		private JMenuItem jmiAreaBarCity;
		private JMenuItem jmiAreaBarPlacesOfInterest;
		private JMenuItem jmiPopBarContinent;
		private JMenuItem jmiPopBarCountry;
		private JMenuItem jmiPopBarCity;
		private JMenuItem jmiStackedAreaCou_Con;
		private JMenuItem jmiStackedAreaCit_Cou;
		private JMenuItem jmiStackedAreaPoi_Con;
		private JMenuItem jmiStackedAreaPoi_Cou;
		private JMenuItem jmiStackedAreaPoi_Cit;
		private JMenuItem jmiStackedPopCou_Con;
		private JMenuItem jmiStackedPopCit_Cou;
		private JMenuItem jmiNeighborhoodList;
		private JMenuItem jmiRecNeighborhoodList;
		private JMenuItem jmiNeighborhoodCheck;
		private JMenuItem jmiRecNeighborhoodCheck;
		private JMenuItem jmiNeighborhoodMap;
		private JMenuItem jmiRecNeighborhoodMap;
		
		/**
		 * Constructor for the menu bar. 
		 */
		public Menu() {

			// Create Main menus
			// Credit to
			// http://docs.oracle.com/javase/tutorial/uiswing/components/border.html
			Border blackBorder = BorderFactory.createLineBorder(Color.black);
			
			//Label and add the JMenu objects. 
			JMenu fileMenu = new JMenu("File");
			fileMenu.setBorder(blackBorder);
			this.add(fileMenu);
			JMenu graphMenu = new JMenu("Graph");
			graphMenu.setBorder(blackBorder);
			this.add(graphMenu);
			JMenu neighborMenu = new JMenu("Neighborhoods");
			this.add(neighborMenu);

			// Create MenuItems for fileMenu
			jmiLoad = new JMenuItem("Load Geography");
			fileMenu.add(jmiLoad);
			jmiSave = new JMenuItem("Save Geography");
			fileMenu.add(jmiSave);
			jmiImport = new JMenuItem("Import Geography");
			fileMenu.add(jmiImport);
			jmiExport = new JMenuItem("Export Geography");
			fileMenu.add(jmiExport);

			// Create Sub-Menus for graphMenu
			JMenu geoNeighborhood = new JMenu("Neighborhood");
			graphMenu.add(geoNeighborhood);
			JMenu mapMenu = new JMenu("Map");
			graphMenu.add(mapMenu);
			JMenu simpleBarChartMenu = new JMenu("Simple Bar Chart");
			graphMenu.add(simpleBarChartMenu);
			JMenu stackedBarChartMenu = new JMenu("Stacked Bar Chart");
			graphMenu.add(stackedBarChartMenu);

			
			//Create sub menus for Geographic Neighborhoods
			jmiNeighborhoodMap = new JMenuItem("Geographic Neighborhood");
			geoNeighborhood.add(jmiNeighborhoodMap);
			jmiRecNeighborhoodMap = new JMenuItem("Recursive Geographic Neighborhood");
			geoNeighborhood.add(jmiRecNeighborhoodMap);
			
			// Create sub menus for simpleBarChartMenu
			JMenu simpleBarAreaMenu = new JMenu("Area");
			simpleBarChartMenu.add(simpleBarAreaMenu);
			JMenu simpleBarPopMenu = new JMenu("Population");
			simpleBarChartMenu.add(simpleBarPopMenu);

			// Create menu items for simple bar Area and population sub-menus
			jmiAreaBarContinent = new JMenuItem("Continent");
			simpleBarAreaMenu.add(jmiAreaBarContinent);
			jmiAreaBarCountry = new JMenuItem("Country");
			simpleBarAreaMenu.add(jmiAreaBarCountry);
			jmiAreaBarCity = new JMenuItem("City");
			simpleBarAreaMenu.add(jmiAreaBarCity);
			jmiAreaBarPlacesOfInterest = new JMenuItem("Places of Interest");
			simpleBarAreaMenu.add(jmiAreaBarPlacesOfInterest);

			jmiPopBarContinent = new JMenuItem("Continent");
			simpleBarPopMenu.add(jmiPopBarContinent);
			jmiPopBarCountry = new JMenuItem("Country");
			simpleBarPopMenu.add(jmiPopBarCountry);
			jmiPopBarCity = new JMenuItem("City");
			simpleBarPopMenu.add(jmiPopBarCity);

			// Create sub-menus for stackedBarChartMenu
			JMenu stackedBarAreaMenu = new JMenu("Area");
			stackedBarChartMenu.add(stackedBarAreaMenu);
			JMenu stackedBarPopulationMenu = new JMenu("Population");
			stackedBarChartMenu.add(stackedBarPopulationMenu);

			// Create menuItems for area and population for stackedBarChartMenu
			jmiStackedAreaCou_Con = new JMenuItem("Countries within Continents");
			stackedBarAreaMenu.add(jmiStackedAreaCou_Con);
			jmiStackedAreaCit_Cou = new JMenuItem("Cities within Countries");
			stackedBarAreaMenu.add(jmiStackedAreaCit_Cou);
			jmiStackedAreaPoi_Con = new JMenuItem("Places Of Interest within Continents");
			stackedBarAreaMenu.add(jmiStackedAreaPoi_Con);
			jmiStackedAreaPoi_Cou = new JMenuItem("Places Of Interest within Contries");
			stackedBarAreaMenu.add(jmiStackedAreaPoi_Cou);
			jmiStackedAreaPoi_Cit = new JMenuItem("Places OF Interest within Cities");
			stackedBarAreaMenu.add(jmiStackedAreaPoi_Cit);

			jmiStackedPopCou_Con = new JMenuItem("Countries within Continents");
			stackedBarPopulationMenu.add(jmiStackedPopCou_Con);
			jmiStackedPopCit_Cou = new JMenuItem("Cities within Countries");
			stackedBarPopulationMenu.add(jmiStackedPopCit_Cou);
			
			// Create menuItems for mapMenu
			jmiCitiesWithinCountries = new JMenuItem("Cities Within Countries");
			mapMenu.add(jmiCitiesWithinCountries);
			jmiCitiesWorldWide = new JMenuItem("Cities Worldwide");
			mapMenu.add(jmiCitiesWorldWide);
			jmiPOIWithinCities = new JMenuItem(
					"Points of Interest Within Cities");
			mapMenu.add(jmiPOIWithinCities);
			jmiPOIWithinCountries = new JMenuItem(
					"Points of Interest Within Countries");
			mapMenu.add(jmiPOIWithinCountries);
			jmiPOIWithinContinents = new JMenuItem(
					"Points of Interest Within Continents");
			mapMenu.add(jmiPOIWithinContinents);
			jmiPOIWorldWide = new JMenuItem("Points of Interest Worldwide");
			mapMenu.add(jmiPOIWorldWide);
			
			//Create menuItems for neighborMenu and add them
			jmiNeighborhoodList = new JMenuItem("Neighborhood List");
			neighborMenu.add(jmiNeighborhoodList);
			jmiNeighborhoodCheck =new JMenuItem("Neighborhood Check");
			neighborMenu.add(jmiNeighborhoodCheck);
			jmiRecNeighborhoodList = new JMenuItem("Recursive Neighborhood List");
			neighborMenu.add(jmiRecNeighborhoodList);
			jmiRecNeighborhoodCheck = new JMenuItem("Recursive Neighborhood Check");
			neighborMenu.add(jmiRecNeighborhoodCheck);
		}// end MenuAndListButtonConstructor

		
		/**
		 * An accessor method for jmiSave.
		 * 
		 * @return JMenuItem that is clickable by the user and saves data.
		 */
		public JMenuItem getJMISave() {
			return jmiSave;
		} // end getJMISave

		/**
		 * An accessor method for jmiImport
		 * 
		 * @return JMenuItem that is clickable by the user and imports data.
		 */
		public JMenuItem getJMIImport() {
			return jmiImport;
		} // end getJMIImport

		/**
		 * An accessor method for jmiExport.
		 * 
		 * @return JMenuItem that is clickable by the user and exports data.
		 */
		public JMenuItem getJMIExport() {
			return jmiExport;
		} // end getJMIExport
		
		/**
		 * An accessor method for jmiLoad
		 * @return JMenuItem that is clickable by the user and loads data. 
		 */
		public JMenuItem getJMILoad(){
			return jmiLoad;
		}// end getHMILoad

		/**
		 * An accessor method for jmiCitiesWithinCountries
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of cities within countries. 
		 */
		public JMenuItem getjmiCitiesWithinCountries(){
			return jmiCitiesWithinCountries;
		} // end getJmiCitiesWithinCountries
		
		/**
		 * An accessor method for jmiCitiesWorldWide
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of cities worldwide. 
		 */
		public JMenuItem  getjmiCitiesWorldWide(){
			return jmiCitiesWorldWide; 
		} // end getjmiCitiesWorldWide
		
		/**
		 * An accessor method for jmiPOIWithinCities
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of points of interest within cities. 
		 */
		public JMenuItem  getjmiPOIWithinCities(){
			return jmiPOIWithinCities;
		} // end getjmiPOIWithinCities
		
		/**
		 * An accessor method for jmiPOIWithinCountries
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of points of interest within countries. 
		 */
		public JMenuItem  getjmiPOIWithinCountries(){
			return jmiPOIWithinCountries;
		} // end getjmiPOIWithinCountries
		
		/**
		 * An accessor method for jmiPOIWithinContinents
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of points of interest within continents. 
		 */
		public JMenuItem  getjmiPOIWithinContinents(){
			return jmiPOIWithinContinents;
		} // end getjmiPOIWithinContinents
		
		/**
		 * An accessor method for jmiPOIWorldWide
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of points of interest worldwide. 
		 */
		public JMenuItem  getjmiPOIWorldWide(){
			return jmiPOIWorldWide;
		} // end getjmiPOIWorldWide
		
		/**
		 * An accessor method for jmiAreaBarContinent.
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of the area of certain continents. 
		 */
		public  JMenuItem getjmiAreaBarContinent(){
			return jmiAreaBarContinent;
		} // end getjmiAreaBarContinent
		
		/**
		 * An accessor method for jmiAreaBarCountry.
		 * 
		 * @return JMenuItem that is clickable by the user and displays a 
		 * graph of the area of certain countries. 
		 */
		public JMenuItem  getjmiAreaBarCountry(){
			return jmiAreaBarCountry;
		} // end getjmiAreaBarCountry
		
		/**
		 * An accessor method for jmiAreaBarCity.
		 * 
		 * @return JMenuItem that is clickable by the user and displays a graph
		 * of the area of certain cities.
		 */
		public JMenuItem  getjmiAreaBarCity(){
			return jmiAreaBarCity;
		} // end getjmiAreaBarCity
		
		/**
		 * An accessor method for jmiAreaBarPlacesOfInterest
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of the area of certain places of interest. 
		 */
		public JMenuItem  getjmiAreaBarPlacesOfInterest(){
			return jmiAreaBarPlacesOfInterest;
		} // end getjmiAreaBarPlacesOfInterest
		
		/**
		 * An accessor method for jmiPopBarContinent.
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of the population of certain continents. 
		 */
		public JMenuItem  getjmiPopBarContinent(){
			return jmiPopBarContinent;
		} // end getjmiPopBarContinent
		
		/**
		 * An accessor method for jmiPopBarCountry. 
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of the population of certain countries. 
		 */
		public JMenuItem  getjmiPopBarCountry(){
			return jmiPopBarCountry;
		} // end getjmiPopBarCountry
		
		/**
		 * An accessor method for jmiPopBarCity
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of the population of certain cities. 
		 */
		public JMenuItem  getjmiPopBarCity(){
			return jmiPopBarCity;
		} // end getjmiPopBarCity
		
		/**
		 * An accessor method for jmiStackedAreaCou_Con.
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of the area of countries within a certain continent. 
		 */
		public JMenuItem  getjmiStackedAreaCou_Con(){
			return jmiStackedAreaCou_Con;
		} //end getjmiStackedAreaCou_Con 
		
		/**
		 * An accessor method for jmiStackedAreaCit_Cou
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * a graph of the are of cities within a certain country. 
		 */
		public JMenuItem  getjmiStackedAreaCit_Cou(){
			return jmiStackedAreaCit_Cou;
		} // end getjmiStackedAreaCit_Cou
		
		/**
		 * An accessor method for jmiStackedAreaPoi_Con
		 * 
		 * @return JMenuItem that is clickable by the user and  displays
		 * the area of certain places of interest in a continent. 
		 */
		public JMenuItem  getjmiStackedAreaPloi_Con(){
			return jmiStackedAreaPoi_Con;
		} // end getjmiStackedAreaPoi_Con
		
		/**
		 * An accessor method for jmiStackedAreaPoi_Cou
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * the area of certain places of interest in a country.
		 */
		public JMenuItem  getjmiStackedAreaPloi_Cou(){
			return jmiStackedAreaPoi_Cou;
		} // end getjmiStackedArePoi_Cou
		
		/**
		 * An accessor method for jmiStackedAreaPoi_Cit
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * the area of certain places of interest in a city. 
		 */
		public JMenuItem getjmiStackedAreaPloi_Cit(){
			return jmiStackedAreaPoi_Cit;
		} // end getjmmiStackedAreaPoi_Cit
		
		/**
		 * An accessor method for jmiStackedPopCou_Con
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * the population for certain countries in a continent. 
		 */
		public JMenuItem  getjmiStackedPopCou_Con(){
			return jmiStackedPopCou_Con;
		} // end getjmiStackedPopCou_Con
		
		/**
		 * An accessor method for jmiStackedPopCit_Cou
		 * 
		 * @return JMenuItem that is clickable by the user and displays
		 * the population for certain cities in a country.
		 */
		public JMenuItem  getjmiStackedPopCit_Cou(){
			return jmiStackedPopCit_Cou;
		} // end getjmiStackedPopCit_Cou
		
		/**
		 * An accessor method for jmiNeighborhoodList
		 * 
		 * @return JMenuItem that is clickable by the user
		 * and displays a list of geographic neighbors. 
		 */
		public JMenuItem getjmiNeighborhoodList(){
			return jmiNeighborhoodList;
		} // end getjmiNeighborhoodList

		/**
		 * An accessor method for jmiRecNeighborhoodList
		 * 
		 * @return JMenuItem that is clickable by the user
		 * and displays a list of geographic neighbors recursively. 
		 */
		public JMenuItem getjmiRecNeighborhoodList(){
			return jmiRecNeighborhoodList;
		} // end getjmiRecNeighborhoodList

		/**
		 * An accessor method for jmiNeighborhoodCheck
		 * 
		 * @return JMenuItem that is clickable by the user
		 * and checks if two regions are geographic neighbors.  
		 */
		public JMenuItem getjmiNeighborhoodCheck(){
			return jmiNeighborhoodCheck;
		} // end getjmiNeighborhoodCheck

		/**
		 * An accessor method for jmiRecNeighborhoodCheck
		 * 
		 * @return JMenuItem that is clickable by the user
		 * and recursively checks if two regions are geographic neighbors.
		 */
		public JMenuItem getjmiRecNeighborhoodCheck(){
			return jmiRecNeighborhoodCheck;
		} // end getjmiRecNeighborhoodCheck

		/**
		 * An accessor method for jmiNeighborhoodMap
		 * 
		 * @return JMenuItem that is clickable by the user
		 * and displays a map of geographic neighbors.
		 */
		public JMenuItem getjmiNeighborhoodMap(){
			return jmiNeighborhoodMap;
		} // end getjmiNeighborhoodMap

		/**
		 * An accessor method for jmiRecNeighborhoodMap
		 * 
		 * @return JMenuItem that is clickable by the user
		 * and displays a map of geographic neighbors recursively.
		 */
		public JMenuItem getjmiRecNeighborhoodMap(){
			return jmiRecNeighborhoodMap;
		} // end getjmiRecNeighborhoodMap
	}// end MenuAndListButtonClass

}// end SelectionView class

