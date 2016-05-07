import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * The view to select what populated region(s)
 * to display on a simple bar graph.
 * @version 1.0
 */
public class ListSelectPopSimple extends JFrame implements ActionListener {
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 2678044537076386334L;
	/**Variable to contain the sortType of the data (by area or population) */
	private String sortType;
	RegionModel model;
	
	/**
	 * Default constructor for the list selection view. 
	 * @param regionList List of regions to choose from.
	 * @param sortType
	 */
	public ListSelectPopSimple(ArrayList<? extends EnclosingRegion> regionList,
			String sortType) {
		
		
		this.setSortType(sortType);

		// Set settings
		this.setTitle("Select Regions to be shown");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// add button and list panel
		popListAndButtonPanel panel = new popListAndButtonPanel();
		panel.setRegionList(regionList);

		// set frame to visible
		this.add(panel);
		this.setVisible(true);

	}// end ListSelectViewPopSimple constructor

	/**
	 * Class that contains the main panel of the
	 * list selection view. 
	 *
	 */
	class popListAndButtonPanel extends JPanel {
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -142595308894507562L;
		/**Variable of list of regions to be displayed */
		private ArrayList<? extends EnclosingRegion> regionList;
		private DefaultListModel<String> regionListModel = new DefaultListModel<String>();

		private JList<String> jlRegionList = new JList<String>(regionListModel);

		/**
		 * Default constructor for the list selection view. 
		 */
		public popListAndButtonPanel() {

			// Set Panel Settings
			this.setLayout(new BorderLayout());

			// Create Panel for buttons
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));

			// Set JList to multi select
			jlRegionList
					.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

			// Make the list Scrollable
			JScrollPane scrollableJlRegionList = new JScrollPane(jlRegionList);

			// Create Ok And Cancel Buttons
			JButton okButton = new JButton("OK");
			JButton cancelButton = new JButton("Cancel");

			// Create Actions for buttons
			class okButtonListener implements ActionListener {
				ArrayList<EnclosingRegion> selectedRegions = new ArrayList<EnclosingRegion>();

				@Override
				public void actionPerformed(ActionEvent e) {

					if (jlRegionList.getSelectedValuesList() != null) {
						selectedRegions.clear();
						for (EnclosingRegion region : regionList) {

							for (String name : jlRegionList
									.getSelectedValuesList()) {

								if (region.getName().equals(name)) {
									selectedRegions.add(region);
								}// end if
							}// end nested for
						}// end for

						RegionModel.generateMultiBarGraph(selectedRegions,
								sortType);

					}// end if
				}// end actionPreformed

			}// end okButtonListener

			class cancelButtonListener implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					disposeFrame();
				}
			}// end cancelButtonListener class

			// Add Listeners to buttons
			okButton.addActionListener(new okButtonListener());
			cancelButton.addActionListener(new cancelButtonListener());

			// Add Buttons to ButtonPanel
			buttonPanel.add(okButton);
			buttonPanel.add(cancelButton);

			// Add Components to panel
			this.add(scrollableJlRegionList, BorderLayout.CENTER);
			this.add(buttonPanel,BorderLayout.SOUTH);

		}// end popListAndButtonPanel constructor

		/**
		 * An accessor method for the list of regions being displayed. 
		 * @param regionList New list of regions to be displayed. 
		 */
		public void setRegionList(ArrayList<? extends EnclosingRegion> regionList) {
			this.regionList = regionList;
			// Populate JList with region names
			this.populateRegionJList();
		}// end setRegionList method

		/**
		 * This method pulls the names of the regions from the region list and
		 * stores them in the JList of JList of regions
		 * 
		 * @param newRegionList
		 *            ArrayList of regions whose names will be added to JList
		 */
		private void populateRegionJList() {
			regionListModel.clear();
			if (regionList != null) {
				for (int i = 0; i < regionList.size(); ++i) {
					// Adds the name of each region to the list model
					regionListModel.addElement(regionList.get(i).getName());
				}
			}
		}// end populateRegion Method method

	}// end popListAndButtonPanel class

	/**
	 * A mutator method for the sortType of the list. 
	 * @param sortType Sort choice for the data (area or population). 
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	} // end setSortType

	/**
	 * A mutator method for the model of the list. 
	 * @param model Model for the data 
	 */
	public void setModel(RegionModel model) {
		this.model = model;
	} // end setSortType
	
	/**
	 * Method to tell this view whenever the model
	 * has been updated. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.repaint();
	}// end actionPreformed

	/**
	 * Method to close this frame when there are no more
	 * regions left to be displayed. 
	 */
	public void disposeFrame() {
		this.dispose();
	} // end disposeFrame
}// listSelectArSimple class
