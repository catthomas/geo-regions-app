/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014
 * The Driver for the graphical user interface.
 * It connects the various components (model, view, controller)
 * in the appropriate ways.
 * @version 1.0
 */
public class RegionDriver {
	/** Variable to contain the model. */
	private RegionModel model = new RegionModel();
	/** Variable to contain the view. */
	private SelectionView selectionView = new SelectionView();
	/** Variable to contain the controller. */
	private RegionController controller = new RegionController();
	
	/**
	 * Constructor to connect various components. 
	 */
	public RegionDriver() {
		selectionView.setModel(model);
		controller.setModel(model);
		controller.setInputView(selectionView);	
	} // end RegionDriver

	/**
	 * Main method to construct a RegionDriver object. 
	 * @param args There are no command line arguments for this program. 
	 */
	public static void main(String[] args) {
		new RegionDriver();
	} // end main method
} // end RegionDRiver
