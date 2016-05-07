import javax.swing.JOptionPane;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project #4
 * CS 2334, Section 10
 * April 22, 2014
 * The Controller for the graphical user interface. 
 * Receives events from the input view (SelectionView) and updates
 * the model's (RegionModel) data. 
 * @version 1.0
 */
public class RegionController {
	/** Model containing geographic region data. */
	private RegionModel model;
	/** View to listen to for model modification events. */
	private SelectionView inputView;
	
	/** Variables to contain views for various add/edit input screens. */
	private AddEditContinentView addContinentView;
	private ArrayList<AddEditContinentView> editContinentViews = new ArrayList<AddEditContinentView>();
	private AddEditCountryView addCountryView;
	private ArrayList<AddEditCountryView> editCountryViews = new ArrayList<AddEditCountryView>();
	private AddEditCityView addCityView;
	private ArrayList<AddEditCityView> editCityViews = new ArrayList<AddEditCityView>();
	private AddEditPlaceView addPlaceView;
	private ArrayList<AddEditPlaceView> editPlaceViews = new ArrayList<AddEditPlaceView>();
	private AddEditPointView addPointView;
	private ArrayList<AddEditPointView> editPointViews = new ArrayList<AddEditPointView>();


	/**
	 * Creates new RegionController
	 */
	public RegionController() {
		// empty
	} // end RegionController

	/**
	 * Class containing actions to be performed if the 
	 * user wants to add a continents. 
	 */
	private class AddContinentListener implements ActionListener{	
		/**
		 * This method catches the event from the view and
		 * performs the add continent action. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // no model associated yet.
			
			//Project the add continent view
			addContinentView = new AddEditContinentView();
			
			//Make the controller listen to this view
			setContinentAddView(addContinentView);
		} // end actionPerformed
	} // end AddListener class
	
	/**
	 * Class containing actions to be performed
	 * when the user clicks 'enter' on the add continent view. 
	 */
	private class AddContinentEnterListener implements ActionListener{
		/**
		 * This method catches the event from the add continent view
		 * and tries to perform this action. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // no model associated yet
			
			if(addContinentView.getJtfName().getText() != null &&
					!"".equals(addContinentView.getJtfName().getText()) &&
					addContinentView.getJtfArea().getText() != null &&
					!"".equals(addContinentView.getJtfArea().getText()) &&
					addContinentView.getJtfPopulation().getText() != null &&
					!"".equals(addContinentView.getJtfPopulation().getText())){
			//Add Continent to the model
			model.addContinent(new Continent(addContinentView.getJtfName().getText(),
					new Double(addContinentView.getJtfArea().getText()).doubleValue(),
					new Long(addContinentView.getJtfPopulation().getText()).longValue()));
			} // end if statement
		} // end actionPerformed
	} // end AddContinentEnterListener
	
	/**
	 * Class containing actions to be performed
	 * if the user would like to add a country. 
	 *
	 */
	private class AddCountryListener implements ActionListener{
		/**
		 * Method that catches the event from selection view
		 * and displays an add country view. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // no model associated yet.
			
			//Project the add country view
			addCountryView = new AddEditCountryView(model);
			
			//Make the controller listen to this view
			setCountryAddView(addCountryView);
		} // end actionPerformed
	} // end AddListener class
	
	/**
	 * Class containing actions to be performed when
	 * the user clicks 'enter' on the add country view. 
	 */
	private class AddCountryEnterListener implements ActionListener{
		/**
		 * Method that catches event from the add country
		 * view and attempts to add the country to the model. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // no model associated yet
			
			if(addCountryView.getJtfName().getText() != null &&
					!"".equals(addCountryView.getJtfName().getText()) &&
					addCountryView.getJtfArea().getText() != null &&
					!"".equals(addCountryView.getJtfArea().getText()) &&
					addCountryView.getJtfPopulation().getText() != null &&
					!"".equals(addCountryView.getJtfPopulation().getText())){
			//Add Country to the model
			Country country = new Country(addCountryView.getJtfName().getText(),
					new Double(addCountryView.getJtfArea().getText()).doubleValue(),
					new Long(addCountryView.getJtfPopulation().getText()).longValue());
			//Set country to appropriate continent
			for(Continent continent: model.getAllContinents()){
				if(continent.getName().equals(addCountryView.getJcbContinents().getSelectedItem().toString())){
					continent.addCountry(country);
					country.setContinentLocation(continent);
				}
			}
			model.addCountry(country);
			}
		} // end actionPerformed
	} // end AddCountryEnterListener
	
	/**
	 * Class containing actions to be performed when the user
	 * would like to add a city. 
	 *
	 */
	private class AddCityListener implements ActionListener{
		/**
		 * Method that catches an event from the view and
		 * displays an add city view. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // no model associated yet.
			
			//Project the add city view
			addCityView = new AddEditCityView(model);
			
			//Make the controller listen to this view
			setCityAddView(addCityView);
		} // end actionPerformed
	} // end AddListener class
	
	/**
	 * Class containing the actions to be performed
	 * when a user clicks 'enter' on an add city view. 
	 *
	 */
	private class AddCityEnterListener implements ActionListener{
		/**
		 * This method catches an event from the add city view
		 * and attempts to actually add the city. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			
			//Check for inputs, then create city 
			if(addCityView.getJtfName().getText() != null &&
					!"".equals(addCityView.getJtfName().getText()) &&
					addCityView.getJtfArea().getText() != null &&
					!"".equals(addCityView.getJtfArea().getText()) &&
					addCityView.getJtfPopulation().getText() != null &&
					!"".equals(addCityView.getJtfPopulation().getText()) &&
					addCityView.getJtfLatitude().getText() != null &&
					!"".equals(addCityView.getJtfLatitude().getText()) &&
					addCityView.getJtfLongitude().getText() != null &&
					!"".equals(addCityView.getJtfLongitude().getText()) &&
					addCityView.getJtfElevation().getText() != null &&
					!"".equals(addCityView.getJtfElevation().getText())
					){
			//Add City to the model
			//String cityName, double area, long population, String latitude, String longitude, double elevation
			City city = new City(addCityView.getJtfName().getText(),
					new Double(addCityView.getJtfArea().getText()).doubleValue(),
					new Long(addCityView.getJtfPopulation().getText()).longValue(),
					addCityView.getJtfLatitude().getText(),
					addCityView.getJtfLongitude().getText(),
					new Double(addCityView.getJtfElevation().getText()).doubleValue());
			//Set city to appropriate country
			for(Country country: model.getAllCountries()){
				if(country.getName().equals(addCityView.getJcbCountries().getSelectedItem().toString())){
					country.addCity(city);
					city.setCountryLocation(country);
				}
			}
			model.addCity(city);
			}
		} // end actionPerformed method
	}// end AddCityEnterListener class
	
	/**
	 * This class contains the actions to be done when 
	 * the user wants to add a place of interest. 
	 *
	 */
	private class AddPlaceListener implements ActionListener{
		/**
		 * This method catches events from the selection
		 * view and displays an add place view. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // no model associated yet.
			
			//Ask user if they would like to add the place
			// To a continent, country or city
			Object[] options = {"Continent", "City",
	                    "Country(ies)"};
			int regionChoice = JOptionPane.showOptionDialog(null,"What type of region "
						+ "will the place of interest \n be added to?", "Type of Region", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			
			//Project the add place view
			//NOTE: For region choice, 2 = country, 1 = city, 0 = continents
			addPlaceView = new AddEditPlaceView(model, regionChoice);
			
			//Make the controller listen to this view
			setPlaceAddView(addPlaceView);
		} // end actionPerformed
	} // end AddListener class
	
	/**
	 * This class contains the actions to be performed when 
	 * a user clicks the 'enter' button on an add place view.
	 *
	 */
	private class AddPlaceEnterListener implements ActionListener{
		/**
		 * This method catches events from the add place view
		 * and attempts to actually add the place of interest to the model. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			
			if(addPlaceView.getJtfName().getText() != null &&
					!"".equals(addPlaceView.getJtfName().getText()) &&
					addPlaceView.getJtfArea().getText() != null &&
					!"".equals(addPlaceView.getJtfArea().getText()) &&
					addPlaceView.getJtfType().getText() != null &&
					!"".equals(addPlaceView.getJtfType().getText())){
				//Construct new place
				PlaceOfInterest place = new PlaceOfInterest(addPlaceView.getJtfName().getText(),
						new Double(addPlaceView.getJtfArea().getText()).doubleValue(),
						addPlaceView.getJtfType().getText());
				
				//Set place to appropriate political region
				if(addPlaceView.getRegionChoice() == 0){
					for(Continent continent: model.getAllContinents()){
						if(continent.getName().equals(addPlaceView.getJcbContinents().getSelectedItem().toString())){
							continent.addPlaceOfInterest(place);
							place.addPoliticalRegion(continent);
						}
					}
				}
				else if(addPlaceView.getRegionChoice() == 1){
					for(City city: model.getAllCities()){
						if(city.getName().equals(addPlaceView.getJcbCities().getSelectedItem().toString())){
							city.addPlaceOfInterest(place);
							place.addPoliticalRegion(city);
						}
					}
				}
				else if(addPlaceView.getRegionChoice() == 2){
					int[] indices = addPlaceView.getJlCountries().getSelectedIndices();
					//Add selected countries to place and vice versa
					for(int i = 0; i < indices.length; ++i){
						for(Country country: model.getAllCountries()){
							if(country.getName().equals(addPlaceView.getCountryListModel().get(indices[i]))){
								country.addPlaceOfInterest(place);
								place.addPoliticalRegion(country);
							}
						}
					}
				}
				model.addPlaceOfInterest(place);
			}// end if statement
		} // end ActionPerformed
	} // end AddPlaceEnterListener
	
	/**
	 * This class contains the actions to be performed
	 * when the user would like to add a point of interest. 
	 *
	 */
	private class AddPointListener implements ActionListener{
		/**
		 * Method that catches the events from the selection 
		 * view and displays an add point view. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			
			//Project the add point view
			addPointView = new AddEditPointView(model);
			
			//Make the controller listen to this view
			setPointAddView(addPointView);
		} // end actionPerformed
	} // end AddPointListener class
	
	/**
	 * This class contains the actions to be performed 
	 * when the user clicks the 'enter' button on the add point 
	 * view. 
	 *
	 */
	private class AddPointEnterListener implements ActionListener{
		/**
		 * This method catches events from the add place view
		 * and attempts to actually add the place. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return;
			
			//Check for inputs, then create point
			if(addPointView.getJtfName().getText() != null &&
					!"".equals(addPointView.getJtfName().getText()) &&
					addPointView.getJtfType().getText() != null &&
					!"".equals(addPointView.getJtfType().getText()) &&
					addPointView.getJtfLatitude().getText() != null &&
					!"".equals(addPointView.getJtfLatitude().getText()) &&
					addPointView.getJtfLongitude().getText() != null &&
					!"".equals(addPointView.getJtfLongitude().getText()) &&
					addPointView.getJtfElevation().getText() != null &&
					!"".equals(addPointView.getJtfElevation().getText())
					){
			//Add Point to the model
			//String name, String pointType, String latitude, String longitude, double elevation
			PointOfInterest point = new PointOfInterest(addPointView.getJtfName().getText(),
					addPointView.getJtfType().getText(),
					addPointView.getJtfLatitude().getText(),
					addPointView.getJtfLongitude().getText(),
					new Double(addPointView.getJtfElevation().getText()).doubleValue());
			//Set point to appropriate Region
			//Check Continents
			for(Continent continent: model.getAllContinents()){
				if(continent.getName().equals(addPointView.getJcbRegions().getSelectedItem().toString())){
					continent.addPointOfInterest(point);
					point.setRegionLocation(continent);
					break;
				}
			}
			//Check Countries
			if(point.getRegionLocation() == null){
				for(Country country: model.getAllCountries()){
					if(country.getName().equals(addPointView.getJcbRegions().getSelectedItem().toString())){
						country.addPointOfInterest(point);
						point.setRegionLocation(country);
						break;
					}
				}
			}
			//Check Cities
			if(point.getRegionLocation() == null){
				for(City city: model.getAllCities()){
					if(city.getName().equals(addPointView.getJcbRegions().getSelectedItem().toString())){
						city.addPointOfInterest(point);
						point.setRegionLocation(city);
						break;
					}
				}
			}
			
			//Check Places
			if(point.getRegionLocation() == null){
				for(PlaceOfInterest place: model.getAllPlacesOfInterest()){
					if(place.getName().equals(addPointView.getJcbRegions().getSelectedItem().toString())){
						place.addPointOfInterest(point);
						point.setRegionLocation(place);
						break;
					}
				}
			}
			//Check points
			if(point.getRegionLocation() == null){
				for(PointOfInterest checkPoint: model.getAllPointsOfInterest()){
					if(checkPoint.getName().equals(addPointView.getJcbRegions().getSelectedItem().toString())){
						checkPoint.addPointOfInterest(point);
						point.setRegionLocation(checkPoint);
						break;
					}
				}
			}
			model.addPointOfInterest(point);
			}
		} // end actionPerformed
	} // end AddPointEnterListener
	
	/**
	 * This class contains the actions to be performed when 
	 * the user would like to delete a continent. 
	 *
	 */
	private class DeleteContinentListener implements ActionListener{
		/**
		 * Method that catches the delete event from the view
		 * and then attempts to delete the selected continents.
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet. 
			
			//Get indices of selected Continents
			int[] indices = inputView.getContinentPanel().getJlRegionList().getSelectedIndices();
			
			//Construct String of names ond array of selected continents
			String continents = "";
			ArrayList<Continent> selectedContinents = new ArrayList<Continent>();
			for(int i = 0; i < indices.length ; ++i){
				continents += model.getAllContinents().get(indices[i]).getName() + "\n";
				selectedContinents.add(model.getAllContinents().get(indices[i]));
			}
			
			Object[] options = {"Yes", "No"};
			//Ask user to confirm deletion
			int n = JOptionPane.showOptionDialog(null, "Delete all information for following Continent(s):"
					+ "\n" + continents, "Confirm Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			//If yes, delete continents
			if(n == 0){
				for(int i = 0; i < selectedContinents.size(); ++i){
					model.deleteContinent(selectedContinents.get(i));
				}// end for loop
			} // end if statement
		} // end actionPerformed
	} // end DeleteListener class
	
	/**
	 * This class contains the actions to be performed when 
	 * the user would like to delete a country. 
	 *
	 */
	private class DeleteCountryListener implements ActionListener{
		/**
		 * Method that catches the delete event from the view
		 * and then attempts to delete the selected countries.
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet. 
			
			//Get indices of selected Countries
			int[] indices = inputView.getCountryPanel().getJlRegionList().getSelectedIndices();
			
			//Construct String of names ond array of selected countries
			String countries = "";
			ArrayList<Country> selectedCountries = new ArrayList<Country>();
			for(int i = 0; i < indices.length ; ++i){
				countries += model.getAllCountries().get(indices[i]).getName() + "\n";
				selectedCountries.add(model.getAllCountries().get(indices[i]));
			}
			
			Object[] options = {"Yes", "No"};
			//Ask user to confirm deletion
			int n = JOptionPane.showOptionDialog(null, "Delete all information for following Country(ies):"
					+ "\n" + countries, "Confirm Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			//If yes, delete continents
			if(n == 0){
				for(int i = 0; i < selectedCountries.size(); ++i){
					model.deleteCountry(selectedCountries.get(i));
				}// end for loop
			} // end if statement
		} // end actionPerformed
	} // end DeleteListener class
	
	/**
	 * This class contains the actions to be performed when 
	 * the user would like to delete a city. 
	 *
	 */
	private class DeleteCityListener implements ActionListener{
		/**
		 * Method that catches the delete event from the view
		 * and then attempts to delete the selected cities.
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; //No model associated yet.
			
			//Get indices of selected Cities
			int[] indices = inputView.getCityPanel().getJlRegionList().getSelectedIndices();
			
			//Construct String of names ond array of selected cities
			String cities = "";
			ArrayList<City> selectedCities = new ArrayList<City>();
			for(int i = 0; i < indices.length ; ++i){
				cities += model.getAllCities().get(indices[i]).getName() + "\n";
				selectedCities.add(model.getAllCities().get(indices[i]));
			}
			
			Object[] options = {"Yes", "No"};
			//Ask user to confirm deletion
			int n = JOptionPane.showOptionDialog(null, "Delete all information for following City(ies):"
					+ "\n" + cities, "Confirm Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			//If yes, delete cities
			if(n == 0){
				for(int i = 0; i < selectedCities.size(); ++i){
					model.deleteCity(selectedCities.get(i));
				}// end for loop
			} // end if statement
		}
	} // end DeleteCityListener
	
	/**
	 * This class contains the actions to be performed when 
	 * the user would like to delete a place of interest. 
	 *
	 */
	private class DeletePlaceListener implements ActionListener{
		/**
		 * Method that catches the delete event from the view
		 * and then attempts to delete the selected places of interest.
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; //No model associated yet.
			
			//Get indices of selected places
			int[] indices = inputView.getPlaceOIPanel().getJlRegionList().getSelectedIndices();
			
			//Construct String of names ond array of selected places
			String places = "";
			ArrayList<PlaceOfInterest> selectedPlaces = new ArrayList<PlaceOfInterest>();
			for(int i = 0; i < indices.length ; ++i){
				places += model.getAllPlacesOfInterest().get(indices[i]).getName() + "\n";
				selectedPlaces.add(model.getAllPlacesOfInterest().get(indices[i]));
			}
			
			Object[] options = {"Yes", "No"};
			//Ask user to confirm deletion
			int n = JOptionPane.showOptionDialog(null, "Delete all information for following Place(s) of Interest:"
					+ "\n" + places, "Confirm Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			//If yes, delete places
			if(n == 0){
				for(int i = 0; i < selectedPlaces.size(); ++i){
					model.deletePlace(selectedPlaces.get(i));
				}// end for loop
			} // end if statement
		}
	} // end DeletePlaceListener
	
	/**
	 * This class contains the actions to be performed when 
	 * the user would like to delete a point of interest. 
	 *
	 */
	private class DeletePointListener implements ActionListener{
		/**
		 * Method that catches the delete event from the view
		 * and then attempts to delete the selected points of interest.
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; //No model associated yet.
			
			//Get indices of selected points
			int[] indices = inputView.getPointOIPanel().getJlRegionList().getSelectedIndices();
			
			//Construct String of names ond array of selected points
			String points = "";
			ArrayList<PointOfInterest> selectedPoints = new ArrayList<PointOfInterest>();
			for(int i = 0; i < indices.length ; ++i){
				points += model.getAllPointsOfInterest().get(indices[i]).getName() + "\n";
				selectedPoints.add(model.getAllPointsOfInterest().get(indices[i]));
			}
			
			Object[] options = {"Yes", "No"};
			//Ask user to confirm deletion
			int n = JOptionPane.showOptionDialog(null, "Delete all information for following Point(s) of Interest:"
					+ "\n" + points, "Confirm Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			//If yes, delete points
			if(n == 0){
				for(int i = 0; i < selectedPoints.size(); ++i){
					model.deletePoint(selectedPoints.get(i));
				}// end for loop
			} // end if statement
		}
	} // end DeletePointListener
	
	/**
	 * This class contains the actions to be performed
	 * when the user would like to edit a continent.
	 *
	 */
	private class EditContinentListener implements ActionListener{
		/**
		 * This method catches the edit event from the selection
		 * view and displays an edit view for each continent selected. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet. 
			
			// Get the index of the selected item(s).
			int[] indices = inputView.getContinentPanel().getJlRegionList().getSelectedIndices();
			
			for(int i = 0; i < indices.length ; ++i){
				setContinentEditView(new AddEditContinentView(model.getAllContinents().get(indices[i])));
			}
		} // end actionPerformed
	} // end EditListener class
	
	/**
	 * This class contains the actions to be performed when a user
	 * clicks 'enter' on an edit continent view. 
	 *
	 */
	private class EditContinentEnterListener implements ActionListener{
		/**
		 * Method that catches the event from the edit continent
		 * view and actually attempts to edit the selected continent. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; //No model associated yet.
			
			//Find index of corresponding edit window
			int index = -1;
			for(int i = 0; i < editContinentViews.size(); ++i){
				if(e.getSource().equals(editContinentViews.get(i).getJbtEnter())){
					index = i;
				}
			}
			
			//If index is valid, edit the continent.
			if(index != -1){
				if(editContinentViews.get(index).getJtfName().getText() != null &&
						!"".equals(editContinentViews.get(index).getJtfName().getText()) &&
						editContinentViews.get(index).getJtfArea().getText() != null &&
						!"".equals(editContinentViews.get(index).getJtfArea().getText()) &&
						editContinentViews.get(index).getJtfPopulation().getText() != null &&
						!"".equals(editContinentViews.get(index).getJtfPopulation().getText() != null)){
				
				//Find index of continent to be edited
				int i = -1;
				for(int j = 0; j < model.getAllContinents().size(); ++j){
					if(editContinentViews.get(index).getRegionName().equals(model.getAllContinents().get(j).getName())){
						i = j;
					} // end if statement
				} // end for loop
					
				//Add Continent to the model if original was found
				if(i != -1){
					model.editContinent(i, new 
							Continent(editContinentViews.get(index).getJtfName().getText(),
							new Double(editContinentViews.get(index).getJtfArea().getText()).doubleValue(),
							new Long(editContinentViews.get(index).getJtfPopulation().getText()).longValue()));
					} // end if statement
				} // end if statement
			} // end if statement
		} // end actionPerformed
	} // end EditContinentEnterListener
	
	/**
	 * This class contains the actions to be performed
	 * when the user would like to edit a country.
	 *
	 */
	private class EditCountryListener implements ActionListener{
		/**
		 * This method catches the edit event from the selection
		 * view and displays an edit view for each country selected. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet. 
			
			// Get the index of the selected item(s).
			int[] indices = inputView.getCountryPanel().getJlRegionList().getSelectedIndices();
			
			for(int i = 0; i < indices.length ; ++i){
				setCountryEditView(new AddEditCountryView(model.getAllCountries().get(indices[i]),
						model));
			}
		} // end actionPerformed
	} // end EditListener class
	
	/**
	 * This class contains the actions to be performed when a user
	 * clicks 'enter' on an edit country view. 
	 *
	 */
	private class EditCountryEnterListener implements ActionListener{
		/**
		 * Method that catches the event from the edit country
		 * view and actually attempts to edit the selected country. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; //No model associated yet.
			
			//Find index of corresponding edit window
			int index = -1;
			for(int i = 0; i < editCountryViews.size(); ++i){
				if(e.getSource().equals(editCountryViews.get(i).getJbtEnter())){
					index = i;
				}
			}
			
			if(index != -1){
				if(editCountryViews.get(index).getJtfName().getText() != null &&
						!"".equals(editCountryViews.get(index).getJtfName().getText()) &&
						editCountryViews.get(index).getJtfArea().getText() != null &&
						!"".equals(editCountryViews.get(index).getJtfArea().getText()) &&
						editCountryViews.get(index).getJtfPopulation().getText() != null &&
						!"".equals(editCountryViews.get(index).getJtfPopulation().getText() != null)){
					
					//Find index of country to be edited
					int i = -1;
					for(int j = 0; j < model.getAllCountries().size(); ++j){
						if(editCountryViews.get(index).getRegionName().equals(model.getAllCountries().get(j).getName())){
							i = j;
						} // end if statement
					} // end for loop
						
					
					//Steps to add Country to the model if original was found
					
					if(i != 1){
						Country country = new Country(editCountryViews.get(index).getJtfName().getText(),
								new Double(editCountryViews.get(index).getJtfArea().getText()).doubleValue(),
								new Long(editCountryViews.get(index).getJtfPopulation().getText()).longValue());
						//Set country to appropriate continent
						for(Continent continent: model.getAllContinents()){
							if(continent.getName().equals(editCountryViews.get(index).getJcbContinents().getSelectedItem().toString())){
								//delete old country
								continent.getCountryList().remove(model.getAllCountries().get(i));
								//add new country
								continent.addCountry(country);
								country.setContinentLocation(continent);
							}
						}
						
						//Edit country
						model.editCountry(i, country);
					} // end if statement
				} // end if statement
			} // end if statement
		} // end actionPerformed
	} // end EditContinentEnterListener
	
	/**
	 * This class contains the actions to be performed
	 * when the user would like to edit a city.
	 *
	 */
	private class EditCityListener implements ActionListener{
		/**
		 * This method catches the edit event from the selection
		 * view and displays an edit view for each city selected. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet. 
			
			// Get the index of the selected item(s).
			int[] indices = inputView.getCityPanel().getJlRegionList().getSelectedIndices();
			
			for(int i = 0; i < indices.length ; ++i){
				setCityEditView(new AddEditCityView(model.getAllCities().get(indices[i]),
						model));
			}
		} // end actionPerformed
	} // end EditListener class
	
	/**
	 * This class contains the actions to be performed when a user
	 * clicks 'enter' on an edit city view. 
	 *
	 */
	private class EditCityEnterListener implements ActionListener{
		/**
		 * Method that catches the event from the edit city
		 * view and actually attempts to edit the selected city. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; //No model associated yet.
			
			//Find index of corresponding edit window
			int index = -1;
			for(int i = 0; i < editCityViews.size(); ++i){
				if(e.getSource().equals(editCityViews.get(i).getJbtEnter())){
					index = i;
				}
			}
			
			if(index != -1){
				//Check for inputs, then create city 
				if(editCityViews.get(index).getJtfName().getText() != null &&
						!"".equals(editCityViews.get(index).getJtfName().getText()) &&
						editCityViews.get(index).getJtfArea().getText() != null &&
						!"".equals(editCityViews.get(index).getJtfArea().getText()) &&
						editCityViews.get(index).getJtfPopulation().getText() != null &&
						!"".equals(editCityViews.get(index).getJtfPopulation().getText()) &&
						editCityViews.get(index).getJtfLatitude().getText() != null &&
						!"".equals(editCityViews.get(index).getJtfLatitude().getText()) &&
						editCityViews.get(index).getJtfLongitude().getText() != null &&
						!"".equals(editCityViews.get(index).getJtfLongitude().getText()) &&
						editCityViews.get(index).getJtfElevation().getText() != null &&
						!"".equals(editCityViews.get(index).getJtfElevation().getText())
						){
					
				//Find index of original city
				int i = -1;
				for(int j = 0; j < model.getAllCities().size(); ++j){
					if(editCityViews.get(index).getRegionName().equals(model.getAllCities().get(j).getName())){
						i = j;
					} // end if statement
				} // end for loop
						
				//Add City to the model if original city is found
				if(i != -1){
					//String cityName, double area, long population, String latitude, String longitude, double elevation
					City city = new City(editCityViews.get(index).getJtfName().getText(),
							new Double(editCityViews.get(index).getJtfArea().getText()).doubleValue(),
							new Long(editCityViews.get(index).getJtfPopulation().getText()).longValue(),
							editCityViews.get(index).getJtfLatitude().getText(),
							editCityViews.get(index).getJtfLongitude().getText(),
							new Double(editCityViews.get(index).getJtfElevation().getText()).doubleValue());
					//Set city to appropriate country
					for(Country country: model.getAllCountries()){
						if(country.getName().equals(editCityViews.get(index).getJcbCountries().getSelectedItem().toString())){
							//Delete old city
							country.getCityList().remove(model.getAllCities().get(i));
							//Add new city
							country.addCity(city);
							city.setCountryLocation(country);
						}
					}
					model.editCity(i, city); // Edit the city
				}// end if statement
			}// end if statement
		  } // end index if statement
		} // end actionPerformed
	} // end EditContinentEnterListener
	
	/**
	 * This class contains the actions to be performed
	 * when the user would like to edit a place of interest.
	 *
	 */
	private class EditPlaceListener implements ActionListener{
		/**
		 * This method catches the edit event from the selection
		 * view and displays an edit view for each place of interest selected. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet. 
			// Get the index of the selected item(s).
			int[] indices = inputView.getPlaceOIPanel().getJlRegionList().getSelectedIndices();
			
			//Ask user what kind of region they would like to add place to for each one
			int[] regionChoices = new int[indices.length];
			for(int i = 0; i < indices.length; ++i){

				Object[] options = {"Continent", "City",
		                    "Country(ies)"};
				regionChoices[i] = JOptionPane.showOptionDialog(null,"What type of region "
							+ "will the place of interest \n be added to?", "Type of Region", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

			}
			
			for(int i = 0; i < indices.length ; ++i){
				setPlaceEditView(new AddEditPlaceView(model.getAllPlacesOfInterest().get(indices[i]),
						model, regionChoices[i]));
			}
		} // end actionPerformed
	} // end EditListener class
	
	/**
	 * This class contains the actions to be performed when a user
	 * clicks 'enter' on an edit place of interest view. 
	 *
	 */
	private class EditPlaceEnterListener implements ActionListener{
		/**
		 * Method that catches the event from the edit place of interest
		 * view and actually attempts to edit the selected place of interest. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			
			//Find index of corresponding edit window
			int index = -1;
			for(int i = 0; i < editPlaceViews.size(); ++i){
				if(e.getSource().equals(editPlaceViews.get(i).getJbtEnter())){
					index = i;
				}
			}
			
			if(index != -1){
				if(editPlaceViews.get(index).getJtfName().getText() != null &&
						!"".equals(editPlaceViews.get(index).getJtfName().getText()) &&
						editPlaceViews.get(index).getJtfArea().getText() != null &&
						!"".equals(editPlaceViews.get(index).getJtfArea().getText()) &&
						editPlaceViews.get(index).getJtfType().getText() != null &&
						!"".equals(editPlaceViews.get(index).getJtfType().getText())){
					//Construct new place
					PlaceOfInterest place = new PlaceOfInterest(editPlaceViews.get(index).getJtfName().getText(),
							new Double(editPlaceViews.get(index).getJtfArea().getText()).doubleValue(),
							editPlaceViews.get(index).getJtfType().getText());
					
					//Find index of original place
					int i = -1;
					for(int j = 0; j < model.getAllPlacesOfInterest().size(); ++j){
						if(editPlaceViews.get(index).getRegionName().equals(model.getAllPlacesOfInterest().get(j).getName())){
							i = j;
						} // end if statement
					} // end for loop
						
					//If original place was found, edit it
					if(i != -1){
						//Delete original place from corresponding regions
						for(PoliticalRegion region: model.getAllPlacesOfInterest().get(i).getPoliticalRegionList()){
							region.getPlacesList().remove(model.getAllPlacesOfInterest().get(i));
						}
						
						//Set place to appropriate political region
						if(editPlaceViews.get(index).getRegionChoice() == 0){
							for(Continent continent: model.getAllContinents()){
								if(continent.getName().equals(editPlaceViews.get(index).getJcbContinents().getSelectedItem().toString())){
									continent.addPlaceOfInterest(place);
									place.addPoliticalRegion(continent);
								}
							}
						}
						else if(editPlaceViews.get(index).getRegionChoice() == 1){
							for(City city: model.getAllCities()){
								if(city.getName().equals(editPlaceViews.get(index).getJcbCities().getSelectedItem().toString())){
									city.addPlaceOfInterest(place);
									place.addPoliticalRegion(city);
								}
							}
						}
						else if(editPlaceViews.get(index).getRegionChoice() == 2){
							int[] indices = editPlaceViews.get(index).getJlCountries().getSelectedIndices();
							//Add selected countries to place and vice versa
							for(int j = 0; j < indices.length; ++j){
								for(Country country: model.getAllCountries()){
									if(country.getName().equals(editPlaceViews.get(index).getCountryListModel().get(indices[j]))){
										country.addPlaceOfInterest(place);
										place.addPoliticalRegion(country);
									}
								}
							}
						}
						//Edit the place
						model.editPlaceOfInterest(i, place);
					}// end if statement
				}// end if statement
			} // end if statement

		}
	} // end EditPlaceEnterListener
	
	/**
	 * This class contains the actions to be performed
	 * when the user would like to edit a point of interest.
	 *
	 */
	private class EditPointListener implements ActionListener{
		/**
		 * This method catches the edit event from the selection
		 * view and displays an edit view for each point of interest selected. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet. 
			
			// Get the index of the selected item(s).
			int[] indices = inputView.getPointOIPanel().getJlRegionList().getSelectedIndices();
			
			//Generate edit view for each item
			for(int i = 0; i < indices.length ; ++i){
				setPointEditView(new AddEditPointView(model.getAllPointsOfInterest().get(indices[i]),
						model));
			}
		} // end actionPerformed
	} // end EditListener class

	/**
	 * This class contains the actions to be performed when a user
	 * clicks 'enter' on an edit point of interest view. 
	 *
	 */
	private class EditPointEnterListener implements ActionListener{
		/**
		 * Method that catches the event from the edit point of interest
		 * view and actually attempts to edit the selected point of interest. 
		 */
		public void actionPerformed(ActionEvent e){
			//Find index of corresponding edit window
			int index = -1;
			for(int i = 0; i < editPointViews.size(); ++i){
				if(e.getSource().equals(editPointViews.get(i).getJbtEnter())){
					index = i;
				}
			}
			
			if(index != -1){
				//Check for inputs, then create point
				if(editPointViews.get(index).getJtfName().getText() != null &&
						!"".equals(editPointViews.get(index).getJtfName().getText()) &&
						editPointViews.get(index).getJtfType().getText() != null &&
						!"".equals(editPointViews.get(index).getJtfType().getText()) &&
						editPointViews.get(index).getJtfLatitude().getText() != null &&
						!"".equals(editPointViews.get(index).getJtfLatitude().getText()) &&
						editPointViews.get(index).getJtfLongitude().getText() != null &&
						!"".equals(editPointViews.get(index).getJtfLongitude().getText()) &&
						editPointViews.get(index).getJtfElevation().getText() != null &&
						!"".equals(editPointViews.get(index).getJtfElevation().getText())
						){
					
				//Find index of original point
				int i = -1;
				for(int j = 0; j < model.getAllPointsOfInterest().size(); ++j){
					if(editPointViews.get(index).getRegionName().equals(model.getAllPointsOfInterest().get(j).getName())){
						i = j;
					} // end if statement
				} // end for loop
						
				//Add Point to the model if original was found
				if(i != -1){
				//String name, String pointType, String latitude, String longitude, double elevation
				PointOfInterest point = new PointOfInterest(editPointViews.get(index).getJtfName().getText(),
						editPointViews.get(index).getJtfType().getText(),
						editPointViews.get(index).getJtfLatitude().getText(),
						editPointViews.get(index).getJtfLongitude().getText(),
						new Double(editPointViews.get(index).getJtfElevation().getText()).doubleValue());
				
				//Delete original point from region
				model.getAllPointsOfInterest().get(i).getRegionLocation().removePointOfInterest(model.getAllPointsOfInterest().get(i).getName());
				
				//Set point to appropriate Region
				//Check Continents
				for(Continent continent: model.getAllContinents()){
					if(continent.getName().equals(editPointViews.get(index).getJcbRegions().getSelectedItem().toString())){
						continent.addPointOfInterest(point);
						point.setRegionLocation(continent);
						break;
					}
				}
				//Check Countries
				if(point.getRegionLocation() == null){
					for(Country country: model.getAllCountries()){
						if(country.getName().equals(editPointViews.get(index).getJcbRegions().getSelectedItem().toString())){
							country.addPointOfInterest(point);
							point.setRegionLocation(country);
							break;
						}
					}
				}
				//Check Cities
				if(point.getRegionLocation() == null){
					for(City city: model.getAllCities()){
						if(city.getName().equals(editPointViews.get(index).getJcbRegions().getSelectedItem().toString())){
							city.addPointOfInterest(point);
							point.setRegionLocation(city);
							break;
						}
					}
				}
				
				//Check Places
				if(point.getRegionLocation() == null){
					for(PlaceOfInterest place: model.getAllPlacesOfInterest()){
						if(place.getName().equals(editPointViews.get(index).getJcbRegions().getSelectedItem().toString())){
							place.addPointOfInterest(point);
							point.setRegionLocation(place);
							break;
						}
					}
				}
				//Check points
				if(point.getRegionLocation() == null){
					for(PointOfInterest checkPoint: model.getAllPointsOfInterest()){
						if(checkPoint.getName().equals(editPointViews.get(index).getJcbRegions().getSelectedItem().toString())){
							checkPoint.addPointOfInterest(point);
							point.setRegionLocation(checkPoint);
							break;
						}
					}
				}
				//Edit point
				model.editPointOfInterest(i, point);
			}
			} // end if statement
		  } // end index if statement
		} // end actionPerformed
	} // end EditPointEnterListener

	/**
	 * Class containing actions to be performed when the user would
	 * like to load data from a binary file.
	 *
	 */
	private class LoadListener implements ActionListener{
		/**
		 * Method that catches load action from the user
		 * and attempts to load data to the model.
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			//Check if there is any unsaved data
			if(model.getDirtyFileBit() == true && model.getAllContinents().size() >0){
				//Ask user what to do with unsaved data
				Object[] options = {"Save", "Export",
	                    "Discard"};
				int n = JOptionPane.showOptionDialog(null,"What should be done with"
						+ " unsaved data?", "Unsaved Data", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				//Chose to save data
				if(n == 0){
					saveData();
					model.clearModel();
				} // End n == 0
				else if(n == 1){
					exportData();
					model.clearModel();
				} // End n == 1
				else if(n == 2){
					model.clearModel();
				} // End n == 2
			} // end if statement (unsaved data)
			
			//Boolean to reflect if a file has been found yet
			boolean appropriateFile = false;
			//String to hold user input
			String input = "";
			
			// Get binary file name
			do{
				//Obtain filename from user
				input = (String)JOptionPane.showInputDialog( null,
	                    "Name of binary (save) file: ", "Get File",
	                    JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
				
				if(input != null){ //User input a name, check it
					try {
						model.readBinaryFile(input);
						appropriateFile = true; //Appropriate file was found
					} catch (ClassNotFoundException | IOException e1) {
						Object[] options = {"Try Again", "Cancel"};
						int n = JOptionPane.showOptionDialog(null, 
								  "The file name was not appropriate."
								  + "\n Please input a different one.",
								  "File Error", JOptionPane.YES_NO_OPTION,
								JOptionPane.ERROR_MESSAGE,null,
								options, options[1]);
						if(n == 1){
							//User would like to cancel
							break;
						} // end if statement
					} //end catch
				} // end if statement
			} while(appropriateFile == false && input != null);
		} // end actionPerformed
	} // end LoadListener class 

	/**
	 * This class contains actions to be performed when the 
	 * user would like to save data in GeoGrapher. 
	 *
	 */
	private class SaveListener implements ActionListener{
		/**
		 * This method catches the save event from the view
		 * and then attempts to save data to a binary file. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			
			saveData(); // Save the data
		} // end actionPerformed
	} // end SaveListener class

	/**
	 * This class contains the actions to be performed when
	 * the user would like to import data to GeoGrapher. 
	 *
	 */
	private class ImportListener implements ActionListener{
		/**
		 * Method that catches the import event from the selection
		 * view and then imports data from text files. 
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			
			//Check if there is any unsaved data
			if(model.getDirtyFileBit() == true && model.getAllContinents().size() > 0){
				//Ask user what to do with unsaved data
				Object[] options = {"Save", "Export",
	                    "Discard"};
				int n = JOptionPane.showOptionDialog(null,"What should be done with"
						+ " unsaved data?", "Unsaved Data", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				//Chose to save data
				if(n == 0){
					saveData();
					model.clearModel();
				} // End n == 0
				else if(n == 1){
					exportData();
					model.clearModel();
				} // End n == 1
				else if(n == 2){
					model.clearModel();
				} // End n == 2
			} // end if statement (unsaved data)
			
			// Get Continent file name
			//Boolean to determine if a file has been found
			boolean appropriateFile = false;
			//String array to hold continent information from text file
			String[] continentInfo = null;
			//String to hold user input
			String input = "";
			do{
				input = (String)JOptionPane.showInputDialog( null,
	                    "Name of Continent file: ", "Get File",
	                    JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
					
				if(input != null){
					try {
					   continentInfo = model.readTextFile(input);
					   appropriateFile = true;
					} catch (IOException e1) {
						Object[] options = {"Try Again", "Cancel"};
						int n = JOptionPane.showOptionDialog(null, 
								  "The file name was not appropriate."
								  + "\n Please input a different one.",
								  "File Error", JOptionPane.YES_NO_OPTION,
								JOptionPane.ERROR_MESSAGE,null,
								options, options[1]);
						if(n == 1){
							//User would like to cancel
							break;
						}
					}
				} // end if statement
			}while(appropriateFile == false && input != null);
			//Generate continents
			if (appropriateFile != false){
				model.generateContinents(continentInfo);
;			}
			
			
			// Reset appropriateFile and input
			appropriateFile = false;
			input = "";
			//String array to hold country information
			String[] countryInfo = null;
			
			if(model.getAllContinents().size() > 0){ // only show when there are continents
				do{
					input = (String)JOptionPane.showInputDialog( null,
		                    "Name of Country file: ", "Get File",
		                    JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
						if(input != null){ //If user has input
							try {
							   countryInfo = model.readTextFile(input);
							   appropriateFile = true;
							} catch (IOException e1) {
								Object[] options = {"Try Again", "Cancel"};
								int n = JOptionPane.showOptionDialog(null, 
										  "The file name was not appropriate."
										  + "\n Please input a different one.",
										  "File Error", JOptionPane.YES_NO_OPTION,
										JOptionPane.ERROR_MESSAGE,null,
										options, options[1]);
								if(n == 1){
									//User would like to cancel
									break;
								}
							} // end catch
						}// end if statement
					}while(appropriateFile != true && input != null);
			
				//Generate countries if file was found
				if(appropriateFile == true){
					model.generateCountries(countryInfo);
				} // end if statement
			} // end country file name
			
			
			// Reset appropriateFile and input
			appropriateFile = false;
			input = "";
			//String array to hold city information
			String[] cityInfo = null;
			
			// Get City file name
			if(model.getAllCountries().size() >0){ // only show when there are countries 
				do{
					//Get filename from user
					input = (String)JOptionPane.showInputDialog( null,
		                    "Name of City file: ", "Get File",
		                    JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
					
					//If the user input a name, check the file
					if(input != null){
						try {
						   cityInfo = model.readTextFile(input);
						   appropriateFile = true; //No exceptions, found file
						} catch (IOException e1) {
							//Ask user to either cancel or enter other file
							Object[] options = {"Try Again", "Cancel"};
							int n = JOptionPane.showOptionDialog(null, 
									  "The file name was not appropriate."
									  + "\n Please input a different one.",
									  "File Error", JOptionPane.YES_NO_OPTION,
									JOptionPane.ERROR_MESSAGE,null,
									options, options[1]);
							if(n == 1){
								//User would like to cancel
								break;
							} // end if
						} // end catch
					}// end if statement
				}while(appropriateFile != true && input != null);
				
				//Generate cities if file was given
				if(appropriateFile == true){
					model.generateCities(cityInfo);
				}
			} // end get city file name
			
			// Reset appropriateFile and input
			appropriateFile = false;
			input = "";
			//String array to hold places information
			String[] placesInfo = null;
			
			// Get Places file name
			if(model.getAllContinents().size() > 0){ // only show if there are continetns
				do{
					//Get filename input from user
					input = (String)JOptionPane.showInputDialog( null,
		                    "Name of Places of Interest file: ", "Get File",
		                    JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
					
					if(input != null){ //If user input a filename, try it
						try {
						   placesInfo = model.readTextFile(input);
						   appropriateFile =true; //Found a working filename
						} catch (IOException e1) {
							//Ask user to either cancel or enter other file
							Object[] options = {"Try Again", "Cancel"};
							int n = JOptionPane.showOptionDialog(null, 
									  "The file name was not appropriate."
									  + "\n Please input a different one.",
									  "File Error", JOptionPane.YES_NO_OPTION,
									JOptionPane.ERROR_MESSAGE,null,
									options, options[1]);
							if(n == 1){
								//User would like to cancel
								break;
							} // end if
						}// end catch
					} // end if statement
				}while(appropriateFile != true && input != null);
				
				//Generate places if appropriate file was found
				if(appropriateFile == true){
					model.generatePlacesOfInterest(placesInfo);
				}
			} // end get places file name
			
			// Reset appropriateFile and input
			appropriateFile = false;
			input = "";
			//String array to hold points of interest info
			String[] pointsInfo = null;
			
			// Get points file name
			if(model.getAllContinents().size() >0){ // only ask if there are continents
				do{
					//Get filename from user
					input= (String)JOptionPane.showInputDialog( null,
		                    "Name of Points of Interest file: ", "Get File",
		                    JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
					
					if(input != null){ // if user input something, check it
						try {
						   pointsInfo = model.readTextFile(input);
						   appropriateFile = true; //appropriate file was found
						} catch (IOException e1) {
							//Ask user to either cancel or enter other file
							Object[] options = {"Try Again", "Cancel"};
							int n = JOptionPane.showOptionDialog(null, 
									  "The file name was not appropriate."
									  + "\n Please input a different one.",
									  "File Error", JOptionPane.YES_NO_OPTION,
									JOptionPane.ERROR_MESSAGE,null,
									options, options[1]);
							if(n == 1){
								//User would like to cancel
								break;
							} // end if
						} // end catch
					} // end if statement
				}while(appropriateFile == false && input != null);
				
				//Generate points if file was found
				if(appropriateFile == true){
					model.generatePointsOfInterest(pointsInfo);
				}
			} // end get points file name
		} // end actionPerformed
	} // end ImportListener class

	/**
	 * Class that contains actions to be performed when the user
	 * would like to export data to text files. 
	 *
	 */
	private class ExportListener implements ActionListener{
		/**
		 * Method that catches the export data event
		 * and then calls the export data method.
		 */
		public void actionPerformed(ActionEvent e){
			if(model == null) return; // No model associated yet.
			
			exportData();

		} // end actionPerformed
	} // end ExportListener class

	// Create Action Listeners for single Bar menu objects
	private class JmiAreaBarContinentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (model != null) {
				ListSelectPopSimple view = new ListSelectPopSimple(
						model.getAllContinents(), "ar");
				view.setModel(model);
			}// end if
		}// end actionPerformed
	}// end Listener

	private class JmiAreaBarCountryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model != null) {
				ListSelectPopSimple view = new ListSelectPopSimple(
						model.getAllCountries(), "ar");
				view.setModel(model);
			}// end if
		}// end actionPreformed
	}// end Listener

	private class JmiAreaBarCityListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model != null) {
				ListSelectPopSimple view = new ListSelectPopSimple(
						model.getAllCities(), "ar");
				view.setModel(model);
			}// end if
		}// end actionPreformed
	}// end Listener

	private class JmiAreaBarPlacesOfInterestListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model != null) {
				ListSelectPopSimple view = new ListSelectPopSimple(
						model.getAllPlacesOfInterest(), "ar");
				view.setModel(model);
			}// end if
		}// end actionPreformed
	}// end Listener

	private class JmiPopBarContinentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model != null) {
				ListSelectPopSimple view = new ListSelectPopSimple(
						model.getAllContinents(), "po");
				view.setModel(model);
			}// end if
		}// end actionPreformed
	}// end Listener

	private class JmiPopBarCountryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model != null) {
				ListSelectPopSimple view = new ListSelectPopSimple(
						model.getAllCountries(), "po");
				view.setModel(model);
			}// end if
		}// end actionPreformed
	}// end Listener

	private class JmiPopBarCityListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model != null) {
				ListSelectPopSimple view = new ListSelectPopSimple(
						model.getAllCities(), "po");
				view.setModel(model);
			}// end if
		}// end actionPreformed
	}// end Listener
	
	//TODO: end gary's action listener classes
	
	/**
	 *Class that contains the actions to be performed when the user
	 *clicks the 'Geographic Neighborhood List' button. It displays the
	 *geographic neighborhood list selection view. 
	 *
	 */
	private class JmiNeighborhoodListListener implements ActionListener {
		/**
		 * Displays the geographic neighborhood list selection view.
		 */
		public void actionPerformed(ActionEvent e) {
			if (model != null) {
				new NeighborhoodListSelectView(model, "normal");
			}// end if
		}// end actionPreformed
	}// end Listener
	
	/**
	 *Class that contains the actions to be performed when the user
	 *clicks the 'Geographic Neighborhood Check' button. It displays the
	 *geographic neighborhood check selection view. 
	 *
	 */
	private class JmiNeighborhoodCheckListener implements ActionListener {
		/**
		 * Displays the geographic neighborhood check selection view.
		 */
		public void actionPerformed(ActionEvent e) {
			if(model != null) {
				new NeighborhoodCheckSelectView(model, "normal");
			} // end if
		} // end actionPerformed
	} // end JmiNeighborhoodCheckListener class
	
	/**
	 *Class that contains the actions to be performed when the user
	 *clicks the 'Geographic Neighborhood' button. It displays the
	 *geographic neighborhood map selection view. 
	 *
	 */
	private class JmiNeighborhoodMapListener implements ActionListener {
		/**
		 * Displays the geographic neighborhood map selection view.
		 */
		public void actionPerformed(ActionEvent e) {
			if(model != null) {
				new NeighborhoodMapSelectView(model, "normal");
			} // end if
		} // end actionPerformed
	} // end JmiNeighborhoodCheckListener class
	
	/**
	 * This class contains the action to be performed when the user
	 * clicks the 'Recursive Geographic List' button in the
	 * Neighborhood menu. It displays a selection view of potential
	 * regions to be listed.
	 */
	private class JmiNeighborhoodRecListListener implements ActionListener {
		/**
		 * Displays the recursive list selection view for coordinate regions.
		 */
		public void actionPerformed(ActionEvent e){
			if(model != null) {
				new NeighborhoodListSelectView(model, "recursive");
			} // end if statement
		} // end actionPerformed
	} // end JmiNeighborhoodRecListListener
	
	/**
	 * This class contains the action to be performed when the user
	 * clicks the 'Recursive Geographic Check' button in the
	 * Neighborhood menu. It displays a selection view of potential
	 * regions to be checked.
	 */
	private class JmiNeighborhoodRecCheckListener implements ActionListener {
		/**
		 * Displays the recursive check selection view for coordinate regions.
		 */
		public void actionPerformed(ActionEvent e){
			if(model != null) {
				new NeighborhoodCheckSelectView(model, "recursive");
			} // end if statement
		} // end actionPerformed
	} // end JmiNeighborhoodRecListListener
	
	/**
	 * This class contains the action to be performed when the user
	 * clicks the 'Recursive Geographic Neighborhood' button in the
	 * graph bar. It displays a selection view of potential
	 * regions to be mapped.
	 */
	private class JmiNeighborhoodRecMapListener implements ActionListener {
		/**
		 * Displays the recursive map selection view for coordinate regions.
		 */
		public void actionPerformed(ActionEvent e){
			if(model != null) {
				new NeighborhoodMapSelectView(model, "recursive");
			} // end if statement
		} // end actionPerformed
	} // end JmiNeighborhoodRecListListener

	
	/**
	 * A mutator method for the model of RegionController. 
	 * @param model to set as the model
	 */
	public void setModel(RegionModel model) {
		this.model = model;
	} // end setModel

	/**
	 * An accessor method for the model of RegionController.
	 * @return RegionModel the model
	 */
	public RegionModel getModel() { 
		return model;
	} // end getModel

	/**
	 * A mutator method for the input view of RegionController. 
	 * @param selectionView on which the listeners should be set
	 */
	public void setInputView(SelectionView selectionView) {
		this.inputView = selectionView;
		
		//Register the listeners for the continent panel
		inputView.getContinentPanel().getJBTNPanel().getJBTAdd()
			.addActionListener(new AddContinentListener());
		inputView.getContinentPanel().getJBTNPanel().getJBTDelete()
			.addActionListener(new DeleteContinentListener());
		inputView.getContinentPanel().getJBTNPanel().getJBTEdit()
			.addActionListener(new EditContinentListener());
		
		//Register the listeners for the country panel
		inputView.getCountryPanel().getJBTNPanel().getJBTAdd()
			.addActionListener(new AddCountryListener());
		inputView.getCountryPanel().getJBTNPanel().getJBTDelete()
			.addActionListener(new DeleteCountryListener());
		inputView.getCountryPanel().getJBTNPanel().getJBTEdit()
			.addActionListener(new EditCountryListener());
		
		//Register the listeners for the city panel
		inputView.getCityPanel().getJBTNPanel().getJBTAdd()
			.addActionListener(new AddCityListener());
		inputView.getCityPanel().getJBTNPanel().getJBTDelete()
			.addActionListener(new DeleteCityListener());
		inputView.getCityPanel().getJBTNPanel().getJBTEdit()
			.addActionListener(new EditCityListener());
		
		//Register the listeners for the places panel
		inputView.getPlaceOIPanel().getJBTNPanel().getJBTAdd()
			.addActionListener(new AddPlaceListener());
		inputView.getPlaceOIPanel().getJBTNPanel().getJBTDelete()
			.addActionListener(new DeletePlaceListener());
		inputView.getPlaceOIPanel().getJBTNPanel().getJBTEdit()
			.addActionListener(new EditPlaceListener());
		
		//Register the listeners for the points panel
		inputView.getPointOIPanel().getJBTNPanel().getJBTAdd()
			.addActionListener(new AddPointListener());
		inputView.getPointOIPanel().getJBTNPanel().getJBTDelete()
			.addActionListener(new DeletePointListener());
		inputView.getPointOIPanel().getJBTNPanel().getJBTEdit()
			.addActionListener(new EditPointListener());
		
		
		//Register the listeners for load, save, import, and export
		inputView.getMenu().getJMILoad().addActionListener(new LoadListener());
		inputView.getMenu().getJMISave().addActionListener(new SaveListener());
		inputView.getMenu().getJMIImport().addActionListener(new ImportListener());
		inputView.getMenu().getJMIExport().addActionListener(new ExportListener());

		
		//Gary's listeners are here
		inputView.getMenu().getjmiAreaBarContinent().addActionListener(new JmiAreaBarContinentListener());
		inputView.getMenu().getjmiAreaBarCountry().addActionListener(new JmiAreaBarCountryListener());
		inputView.getMenu().getjmiAreaBarCity().addActionListener(new JmiAreaBarCityListener());
		inputView.getMenu().getjmiAreaBarPlacesOfInterest().addActionListener(new JmiAreaBarPlacesOfInterestListener());
		
		inputView.getMenu().getjmiPopBarContinent().addActionListener(new JmiPopBarContinentListener());
		inputView.getMenu().getjmiPopBarCountry().addActionListener(new JmiPopBarCountryListener());
		inputView.getMenu().getjmiPopBarCity().addActionListener(new JmiPopBarCityListener());
	
		//Register the listeners for geographic neighborhood buttons
		inputView.getMenu().getjmiNeighborhoodList().addActionListener(new JmiNeighborhoodListListener());
		inputView.getMenu().getjmiNeighborhoodCheck().addActionListener(new JmiNeighborhoodCheckListener());
		inputView.getMenu().getjmiNeighborhoodMap().addActionListener(new JmiNeighborhoodMapListener());
		
		//Register the listeners for recursive geographic neighborhood buttons
		inputView.getMenu().getjmiRecNeighborhoodList().addActionListener(new JmiNeighborhoodRecListListener());
		inputView.getMenu().getjmiRecNeighborhoodCheck().addActionListener(new JmiNeighborhoodRecCheckListener());
		inputView.getMenu().getjmiRecNeighborhoodMap().addActionListener(new JmiNeighborhoodRecMapListener());
	} // end setInputView

	/**
	 * A mutator method for the add continent of RegionController. 
	 * @param continentView to which the listeners should be set
	 */
	public void setContinentAddView(AddEditContinentView continentView){
		continentView.getJbtEnter().addActionListener(new AddContinentEnterListener());
	} // end setContinentAddView
	
	/**
	 * A mutator method for the edit continent views of RegionController. 
	 * @param editContinent views on which the listeners should be set
	 */
	public void setContinentEditView(AddEditContinentView editContinent){
		editContinent.getJbtEnter().addActionListener(new EditContinentEnterListener());
		editContinentViews.add(editContinent);
	} // end setContinentEditView
	
	/**
	 * A mutator method for the add country view of RegionController. 
	 * @param countryView on which the listeners should be set
	 */
	public void setCountryAddView(AddEditCountryView countryView){
		addCountryView.getJbtEnter().addActionListener(new AddCountryEnterListener());
	}
	
	/**
	 * A mutator method for the edit country views of RegionController. 
	 * @param editCountry Views on which the listeners should be set
	 */
	public void setCountryEditView(AddEditCountryView editCountry){
		editCountry.getJbtEnter().addActionListener(new EditCountryEnterListener());
		editCountryViews.add(editCountry);
	} // end setContinentEditView
	
	/**
	 * A mutator method for the add city view of RegionController. 
	 * @param cityView on which the listeners should be set
	 */
	public void setCityAddView(AddEditCityView cityView){
		addCityView.getJbtEnter().addActionListener(new AddCityEnterListener());
	}
	
	/**
	 * A mutator method for the edit city view of RegionController. 
	 * @param editCity Views on which the listeners should be set
	 */
	public void setCityEditView(AddEditCityView editCity){
		editCity.getJbtEnter().addActionListener(new EditCityEnterListener());
		editCityViews.add(editCity);
	} // end setCityEditView
	
	/**
	 * A mutator method for the add place view of RegionController. 
	 * @param placeView on which the listeners should be set
	 */
	public void setPlaceAddView(AddEditPlaceView placeView){
		addPlaceView.getJbtEnter().addActionListener(new AddPlaceEnterListener());
	}
	
	/**
	 * A mutator method for the edit place views of RegionController. 
	 * @param editPlace View on which the listeners should be set
	 */
	public void setPlaceEditView(AddEditPlaceView editPlace){
		editPlace.getJbtEnter().addActionListener(new EditPlaceEnterListener());
		editPlaceViews.add(editPlace);
	} // end setPlaceEditView
	
	/**
	 * A mutator method for the add point view of RegionController. 
	 * @param pointView on which the listeners should be set
	 */
	public void setPointAddView(AddEditPointView pointView){
		addPointView.getJbtEnter().addActionListener(new AddPointEnterListener());
	}
	
	/**
	 * A mutator method for the edit point view of RegionController. 
	 * @param editPoint View to which the listeners should be set
	 */
	public void setPointEditView(AddEditPointView editPoint){
		editPoint.getJbtEnter().addActionListener(new EditPointEnterListener());
		editPointViews.add(editPoint);
	} // end setContinentEditView
	
	/**
	 * An accessor method for the input view of RegionController. 
	 * @return the window 
	 */
	public SelectionView getInputView() {
		return inputView;
	} // end getInputView
	
	/**
	 * This method takes in the names of text files and then writes
	 * corresponding region data to the files in .csv format.
	 */
	public void exportData(){
		//Variable to determine if an appropriate file has been found
		boolean appropriateFile = false;
		//String to hold user's filename input
		String input = "";
		
		// Get Continent file name
		do{
			//Get filename input from user
			input = (String)JOptionPane.showInputDialog( null,
	                "Name of Continent file for export: ", "Get File",
	                JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
			
			if(input != null){ //User entered a file name, check it. 
				try {
					model.writeTextFile(model.getAllContinents(), input);
					appropriateFile = true; //File was found
				} catch (IOException e1) {
					//Ask user to either cancel or enter other file
					Object[] options = {"Try Again", "Cancel"};
					int n = JOptionPane.showOptionDialog(null, 
							  "The file name was not appropriate."
							  + "\n Please input a different one.",
							  "File Error", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE,null,
							options, options[1]);
					if(n == 1){
						//User would like to cancel
						break;
					} // end if
				} // end catch
			} // end if statement
		}while(appropriateFile == false && input != null);
		
		//Reset input and appropriate file variables
		appropriateFile = false;
		input = "";
		
		// Get Country file name
		do{
			//Get filename input from user
			input = (String)JOptionPane.showInputDialog( null,
	                "Name of Country file for export: ", "Get File",
	                JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
			
			if(input != null){ //User entered a file name, check it. 
				try {
					model.writeTextFile(model.getAllContinents(), input);
					appropriateFile = true; //File was found
				} catch (IOException e1) {
					//Ask user to either cancel or enter other file
					Object[] options = {"Try Again", "Cancel"};
					int n = JOptionPane.showOptionDialog(null, 
							  "The file name was not appropriate."
							  + "\n Please input a different one.",
							  "File Error", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE,null,
							options, options[1]);
					if(n == 1){
						//User would like to cancel
						break;
					} // end if
				} // end catch
			} // end if statement
		}while(appropriateFile == false && input != null);
		
		//Reset input and appropriate file variables
		appropriateFile = false;
		input = "";
		
		// Get City file name
		do{
			//Get filename input from user
			input = (String)JOptionPane.showInputDialog( null,
	                "Name of City file for export: ", "Get File",
	                JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
			
			if(input != null){ //User entered a file name, check it. 
				try {
					model.writeTextFile(model.getAllContinents(), input);
					appropriateFile = true; //File was found
				} catch (IOException e1) {
					//Ask user to either cancel or enter other file
					Object[] options = {"Try Again", "Cancel"};
					int n = JOptionPane.showOptionDialog(null, 
							  "The file name was not appropriate."
							  + "\n Please input a different one.",
							  "File Error", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE,null,
							options, options[1]);
					if(n == 1){
						//User would like to cancel
						break;
					} // end if
				} // end catch
			} // end if statement
		}while(appropriateFile == false && input != null);
		
		//Reset input and appropriate file variables
		appropriateFile = false;
		input = "";
		
		// Get Places file name
		do{
			//Get filename input from user
			input = (String)JOptionPane.showInputDialog( null,
	                "Name of Places of Interest file for export: ", "Get File",
	                JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
			
			if(input != null){ //User entered a file name, check it. 
				try {
					model.writeTextFile(model.getAllContinents(), input);
					appropriateFile = true; //File was found
				} catch (IOException e1) {
					//Ask user to either cancel or enter other file
					Object[] options = {"Try Again", "Cancel"};
					int n = JOptionPane.showOptionDialog(null, 
							  "The file name was not appropriate."
							  + "\n Please input a different one.",
							  "File Error", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE,null,
							options, options[1]);
					if(n == 1){
						//User would like to cancel
						break;
					} // end if
				} // end catch
			} // end if statement
		}while(appropriateFile == false && input != null);
		
		//Reset input and appropriate file variables
		appropriateFile = false;
		input = "";
		
		// Get points file name
		do{
			//Get filename input from user
			input = (String)JOptionPane.showInputDialog( null,
	                "Name of Points of Interest file for export: ", "Get File",
	                JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
			
			if(input != null){ //User entered a file name, check it. 
				try {
					model.writeTextFile(model.getAllContinents(), input);
					appropriateFile = true; //File was found
				} catch (IOException e1) {
					//Ask user to either cancel or enter other file
					Object[] options = {"Try Again", "Cancel"};
					int n = JOptionPane.showOptionDialog(null, 
							  "The file name was not appropriate."
							  + "\n Please input a different one.",
							  "File Error", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE,null,
							options, options[1]);
					if(n == 1){
						//User would like to cancel
						break;
					} // end if
				} // end catch
			} // end if statement
		}while(appropriateFile == false && input != null);
	}// end exportData
	
	/**
	 * This method obtains a file name from the user
	 * then saves all the model data to the file
	 * in a binary format. 
	 */
	public void saveData(){
		//Boolean to reflect if appropriate file has been found yet
		boolean appropriateFile = false;
		//String to hold user input
		String input = "";
		
		// Get binary file name
		do{
			//Receive user input for name of file
			input = (String)JOptionPane.showInputDialog( null,
	                "Name of binary (save) file: ", "Get Filename",
	                JOptionPane.PLAIN_MESSAGE,  null, null, "filename");
			
			if(input != null){ // If the user input a name, check it
				try {
					model.writeBinaryFile(input);
					appropriateFile = true; // Try was successful, file found
					
				} catch (ClassNotFoundException | IOException e1) {
					//Ask user to either cancel or enter other file
					Object[] options = {"Try Again", "Cancel"};
					int n = JOptionPane.showOptionDialog(null, 
							  "The file name was not appropriate."
							  + "\n Please input a different one.",
							  "File Error", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE,null,
							options, options[1]);
					if(n == 1){
						//User would like to cancel
						break;
					} // end if
				} // end catch
			} // end if statement
		}while(appropriateFile == false && input != null);
	} // end saveData
} // end RegionController
