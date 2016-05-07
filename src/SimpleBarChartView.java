import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project #5
 *  CS 2334
 *  Section 10 
 *  April 22, 2014 
 *  A View Class which takes in an
 * arraylist of geographic region objects 
 * and displays them on a simple bar
 * chart graph.
 * 
 * @version 1.0
 */
public class SimpleBarChartView extends JFrame implements ActionListener {
	/**
	 * Unique ID in order for this class to be written to files
	 * directly as an object.
	 */
	private static final long serialVersionUID = -4939989149292094277L;
	private RegionModel model;
	
	/**
	 * Constructor for a simple bar chart. 
	 * @param regionList List of regions to be displayed.
	 * @param sortOption Describes whether they are displaying
	 * population or area. 
	 */
	public <E extends EnclosingRegion> SimpleBarChartView(ArrayList<E> regionList,
			String sortOption) {
		// Set Frame Options
		this.setTitle("Simple Bar Graph");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(1000, 450));

		JScrollPane panel = new JScrollPane(new AllBarPanel(regionList,
				sortOption));

		this.add(panel);

		this.setVisible(true);

	}// end SimpleBarChartView

	/**
	 * An accessor method for the model of this view. 
	 * @param model New model for the view. 
	 */
	public void setModel(RegionModel model) {
		this.model = model;

		// Set SimpleBarChartView as a listener to region model
		if (model != null) {
			model.addActionListener(this);
		}// end if
	}// end setModel method

	@Override
	/**
	 * Tells this view whenever an action to the model has been performed. 
	 */
	public void actionPerformed(ActionEvent e) {

		this.repaint();
	}// end actionPreformed

	/**
	 * Class that contains the main panel for the simple bar graph. 
	 * Holds all the bars. 
	 */
	public class AllBarPanel extends JPanel {
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -5132530652967864635L;

		/**
		 * Constructor for the main panel of the simple bar graph.
		 * @param regionList List of regions being graphed.
		 * @param sortType Describes whether they were sorted by 
		 * area or population. 
		 */
		public <E extends EnclosingRegion> AllBarPanel(ArrayList<E> regionList,
				String sortType) {

			// set layout for the panel
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			// add the bars to the panel
			for (EnclosingRegion region : regionList) {
				SingleBarWithLabels labelPanel = (new SingleBarWithLabels(
						region, sortType));
				labelPanel.setAlignmentY(BOTTOM_ALIGNMENT);
				this.add(labelPanel);
			}

		}// end AllBarPanel constructor

	}// end AllBarPanel class

	/**
	 * Class to contain a single bar with geographic labels. 
	 *
	 */
	public class SingleBarWithLabels extends JPanel {
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = 908167389542514133L;

		/**
		 * Default constructor for a single bar with labels. 
		 * @param region Single region being drawn.
		 * @param sortType Whether we are graphing area or population. 
		 */
		public SingleBarWithLabels(EnclosingRegion region, String sortType) {
			// set layout
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			// add data to top of bar
			if (sortType.equalsIgnoreCase("po")) {
				this.add(new JLabel(""
						+ (int)((PoliticalRegion) region).getPopulation() + " (Pop)"));
				this.setPreferredSize(new Dimension(100, 10 * (int) Math
						.log(((PoliticalRegion) region).getPopulation())));

			}// end if

			else if (sortType.equalsIgnoreCase("ar")) {
				this.add(new JLabel("" + (int)region.getArea() + " (Mi2)"));
				this.setPreferredSize(new Dimension(100, 10 * (int) Math
						.log((region).getArea())));

			}// end else if

			// add SingleBar
			this.add(new SingleBar(region, sortType));

			// add name of region below the bar
			this.add(new JLabel(region.getName()));

		}// end SingleBarWithLabels constructor

	}// end SingleBarWithLabels class

	/**
	 * Class to contain a single bar for the graph. 
	 *
	 */
	public class SingleBar extends JPanel {
		/**
		 * Unique ID in order for this class to be written to files
		 * directly as an object.
		 */
		private static final long serialVersionUID = -7322714209074033052L;
		private EnclosingRegion region;
		private String sortChoice;
		
		/**
		 * Default construct for a single bar.
		 * @param region Region whose bar is being drawn.
		 * @param sortChoice The sortChoice ("po" or "ar") of the regions. 
		 */
		public SingleBar(EnclosingRegion region, String sortChoice) {
			this.region = region;
			this.sortChoice = sortChoice;
			
			if (sortChoice.equalsIgnoreCase("ar")){
			this.setMaximumSize(new Dimension(100,25 *(int) Math.log(region.getArea())));
			}// end if
			
			else if (sortChoice.equalsIgnoreCase("po")){
				this.setMaximumSize(new Dimension(100,25 * (int) Math
						.log(((PoliticalRegion) region).getPopulation())));
				
			}//end else-if
		}// end singleBar constructor

		/**
		 * Method to actually draw the bars of the bar graph
		 * @param g Graphics item in which to get paint methods. 
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.setColor(Color.BLUE);

			// Determine height of rectangles by sort choice
			if (sortChoice.equalsIgnoreCase("po")) {
				g.fillRect(0, 30, 50, 100 * (int) Math
						.log(((PoliticalRegion) region).getPopulation()));
			}// end if

			else if (sortChoice.equalsIgnoreCase("ar")) {
				g.fillRect(0, 30, 50, 100 * (int) Math.log(region.getArea()));
			}// end else-if

		}// end paintComponent method

	}// end SingleBar Class

}// end SimpleBarChartView
