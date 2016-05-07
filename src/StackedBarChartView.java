import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * A View Class which takes in an arraylist of Regions and
 * displays them on a stacked bar chart style graph.
 * @version 1.0
 */
public class StackedBarChartView extends JFrame{
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = 7337757117835808167L;
	private double areaScalar; // Number which is used to scale the bars of the graph
	
	//TODO: fix bar sizing, make right hand side scaled to longest name.
	
	/**
	 * Constructs a panel containing a scrollbar and the single bar graph. Adds
	 * to the overall frame.
	 * 
	 * @param regionList List of regions to be graphed
	 */
	public <E extends EnclosingRegion> StackedBarChartView(ArrayList<E> regionList){
			areaScalar = regionList.get(0).getArea() / 10 + 20;
		
			JPanel container = new JPanel(); // Create panel to hold the bar graph
			container.add(new AllRegionsSingleBarPanel(regionList)); // Adds the bar graph
			JScrollPane scroll = new JScrollPane(container); // Creates a scrollpane for the panel
			this.add(scroll); // adds Scroll pane to SingleBarGraph frame
		} // end SingleBarGraph

	/**
	 * This class is a panel containing the entire single bar graph
	 * composed of many regions' information.
	 *
	 */
	public class AllRegionsSingleBarPanel extends JPanel{	
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -1849766804650165416L;

		/**
		 * Formats the components of the single bar graph.
		 * 
		 * @param regionList List of regions whose data is being charted.
		 */
		public <E extends EnclosingRegion> AllRegionsSingleBarPanel(ArrayList<E> regionList){
			//creates a box layout to contain region bars
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			//For each slot in the box layout, corresponding region rectangle is added
			for(EnclosingRegion region: regionList){
				RegionBarPanel newPanel = new RegionBarPanel(region);
				add(newPanel);
				newPanel.setAlignmentY(CENTER_ALIGNMENT);
			}
		} //end AllRegionsSingleBarPanel constructor
	} // end AllRegionsSingleBarPanel class
	
	/**
	 * This class is a panel containing the a single bar with
	 * the region's name to the right and area value to the left. 
	 *
	 */
	public class RegionBarPanel extends JPanel{

		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = 4889785153427780221L;
		
		/**
		 * This method lays out the formating and adds components
		 * to a single region's bar.
		 * 
		 * @param region Region whose information is to be graphed. 
		 */
		public RegionBarPanel(EnclosingRegion region) {
			setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(400, (int)(region.getArea()/areaScalar)+1));
			
			JLabel areaLabel = new JLabel("" + region.getArea()); //Creates area label
			areaLabel.setPreferredSize(new Dimension(100,5));
			add(areaLabel, BorderLayout.WEST); // Adds area label to right of bar
			add(new RegionBar(region), BorderLayout.CENTER); // centers the bar
			add(new JLabel("" + region.getName()), BorderLayout.EAST); //Adds region name to left of bar
		} // end RegionBarPanel
	} // end RegionBarPanel class
	
	public class RegionBar extends JPanel{
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = 2402553338554600385L;
		private EnclosingRegion region;
		
		/**
		 * Constructs the bar for a single region.
		 * @param region Region's whose information is to be graphed
		 */
		public RegionBar(EnclosingRegion region) {
			this.region = region;
		} // end RegionBarPanel
		
		@Override
		/**
		 * Overrides the paintComponent method to draw a rectangle
		 * corresponding the the region's area size. 
		 */
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawRect(0, 0, 60, (int)(region.getArea()/areaScalar));
		}
	} // end RegionBar
}
