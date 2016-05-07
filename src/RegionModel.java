import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Project #5
 * CS 2334, Section 10
 * April 22, 2014 
 * This class houses the data for all types of geographic regions.
 * It contains the methods to work with this data as well as 
 * ActionListeners in order to notify any connected views of 
 * updates to the data. 
 * @version 1.0
 */
public class RegionModel {
	/** A list of all continents stored in RegionModel. */
	private ArrayList<Continent> allContinents = new ArrayList<Continent>();
	/** A list of all countries stored in RegionModel. */
	private ArrayList<Country> allCountries = new ArrayList<Country>();
	/** A list of all cities stored in regionModel. */
	private ArrayList<City> allCities = new ArrayList<City>();
	/** A list of all places of interest stored in RegionModel. */
	private ArrayList<PlaceOfInterest> allPlacesOfInterest = new ArrayList<PlaceOfInterest>();
	/** A list of all points of interest stored in RegionModel. */
	private ArrayList<PointOfInterest> allPointsOfInterest = new ArrayList<PointOfInterest>();
	/**
	 * List to keep track of who is registered to listen for events from the RegionModel.
	 */
	private ArrayList<ActionListener> actionListenerList;
	/** Boolean tracking whether data has been modified. */
	private boolean dirtyFileBit;
	
	
	/**
	 * A Constructor of RegionModel objects
	 */
	public RegionModel() {
		dirtyFileBit = false; //Default - no modifications 
	} // end RegionModel
	
	/**
	 * An accessor method for the dirty file bit
	 * @return True if there is unsaved data, false otherwise
	 */
	public boolean getDirtyFileBit(){
		return dirtyFileBit;
	}// end getDirtyFileBit
	
	/**
	 * An accessor method for the list of all continents.
	 * @return ArrayList of all continents in the model.
	 */
	public ArrayList<Continent> getAllContinents(){
		return allContinents;
	} // end getAllContinents
	
	/**
	 * An accessor method for the list of all countries.
	 * @return ArrayList of all countries in the model.
	 */
	public ArrayList<Country> getAllCountries(){
		return allCountries;
	} // end getAllCountries
	
	/**
	 * An accessor method for the list of all cities.
	 * @return ArrayList of all cities in the model.
	 */
	public ArrayList<City> getAllCities(){
		return allCities;
	} // end getAllCities
	
	/**
	 * An accessor method for the list of all places of interest.
	 * @return ArrayList of all places of interest in the model.
	 */
	public ArrayList<PlaceOfInterest> getAllPlacesOfInterest(){
		return allPlacesOfInterest;
	} // end getAllPlacesOfInterest
	
	/**
	 * An accessor method for the list of all points of interest.
	 * @return ArrayList of all points of interest in the model.
	 */
	public ArrayList<PointOfInterest> getAllPointsOfInterest(){
		return allPointsOfInterest;
	} // end getAllPointsOfInterest
	
	/**
	 * A mutator method for the list of all continents.
	 * @param allContinents New list of all continents.
	 */
	public void setAllContinents(ArrayList<Continent> allContinents){
		this.allContinents = allContinents;
		
		//Notify the listener for the change on all continents
		processEvent(new ActionEvent(this, 
				ActionEvent.ACTION_PERFORMED, "all continents"));
		dirtyFileBit = true;
	} // end setAllContinents
	
	/**
	 * A mutator method for the list of all countries.
	 * @param allCountries New list of all countries. 
	 */
	public void setAllCountries(ArrayList<Country> allCountries){
		this.allCountries = allCountries;
		
		//Notify the listener for the change on all countries
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "all countries"));
		dirtyFileBit = true;
	} // end setAllCountries
	
	/**
	 * A mutator method for the list of all cities.
	 * @param allCities New list of all cities.
	 */
	public void setAllCities(ArrayList<City> allCities){
		this.allCities = allCities;
		
		//Notify the listener for the change on allCities
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "all cities"));
		dirtyFileBit = true;
	} // end setAllCities
	
	/**
	 * A mutator method for the list of all places of interest.
	 * @param allPlacesOfInterest New list of all places of interest.
	 */
	public void setAllPlacesOfInterest(ArrayList<PlaceOfInterest> allPlacesOfInterest){
		this.allPlacesOfInterest = allPlacesOfInterest;
		
		//Notify the listener for the change on all places of interest
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "all places"));
		dirtyFileBit = true;
	} // end setAllPlacesOfInterest
	
	/**
	 * A mutator method for the list of all points of interest.
	 * @param allPointsOfInterest New list of all points of interest.
	 */
	public void setAllPointsOfInterest(ArrayList<PointOfInterest> allPointsOfInterest){
		this.allPointsOfInterest = allPointsOfInterest;
		
		//Notify the listener for the change on all points of interest
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "all points"));
		dirtyFileBit = true;
	} // end setAllPointsOfInterest
	
	/**
	 * A mutator method for the dirty file bit
	 * @param value New boolean value of dirty file bit
	 */
	public void setDirtyFileBit(boolean value){
		this.dirtyFileBit = value;
	}
	
	/**
	 * A method that discards all data currently in model.
	 */
	public void clearModel(){
		allContinents.clear(); // clear all continents
		allCountries.clear(); // clear all countries
		allCities.clear(); // Clear all cities
		allPlacesOfInterest.clear(); // Clear all places
		allPointsOfInterest.clear(); //Clear all points
		
		//Notify the listener for the change on all continents
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "clear model"));
		dirtyFileBit = false;
	} // end clearModel
	
	/**
	 * Method that adds a continent to the model
	 * @param continent Continent to be added. 
	 */
	public void addContinent(Continent continent){
		// Construct allContinents if not there
		if(allContinents == null){
			allContinents = new ArrayList<Continent>();
		}
		
		//Make sure continent name is not already there
		for(Continent continent2: allContinents){
			if(continent2.getName().equalsIgnoreCase(continent.getName())){
				JOptionPane.showMessageDialog (null, "There is already a Continent with that name."
						+ "\n Duplicate Continent was not added.", "Error",		
						JOptionPane.WARNING_MESSAGE);
				return; // break out of method without adding Continent
			}
		} // end for-loop
		
		//Add continent to list
		allContinents.add(continent);
		
		//Notify the listener for the change on all continents
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "add continent"));
		dirtyFileBit = true;
	} // end addContinent
	
	/**
	 * Method that adds a country to the model
	 * @param country Country to be added
	 */
	public void addCountry(Country country){
		//Find population sum and area sum of continentLocation countries
		long popSum = 0;
		double areaSum = 0;
		for(int i = 0; i < country.getContinentLocation().getCountryList().size(); ++i){
			popSum += country.getContinentLocation().getCountryList().get(i).getPopulation();
			areaSum += country.getContinentLocation().getCountryList().get(i).getArea();
		}
		
		//Add country to list if sanity checks are met
		if(areaSum > country.getContinentLocation().getArea()){
			JOptionPane.showMessageDialog (null, "Adding the Country " + country.getName()
					+" will exceed the area of the Continent " + country.getContinentLocation().getName() + "."
					+"\n Country was not added.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			country.getContinentLocation().removeCountry(country.getName());
			return; // break out of method without adding Country
		}
		else if(popSum > country.getContinentLocation().getPopulation()){
			JOptionPane.showMessageDialog (null, "Adding the Country " + country.getName()
					+" will exceed the population of the Continent " + country.getContinentLocation().getName() + "."
					+"\n Country was not added.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			country.getContinentLocation().removeCountry(country.getName());
			return; // break out of method without adding Country
		}
		else{
			allCountries.add(country); // Add country
		}
		
		//Notify the listener for the change on all countries
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "add country"));
		dirtyFileBit = true;
	} // end addCountry
	
	/**
	 * A method that adds a city to the model
	 * @param city City to be added.
	 */
	public void addCity(City city){
		//Find population sum and area sum of continentLocation countries
		//Assumes constructed city already has continent location
		long popSum = 0;
		double areaSum = 0;
		for(int i = 0; i < city.getCountryLocation().getCityList().size(); ++i){
			popSum += city.getCountryLocation().getCityList().get(i).getPopulation();
			areaSum += city.getCountryLocation().getCityList().get(i).getArea();
		}
		
		//Add city to list if sanity checks are met
		if(areaSum > city.getCountryLocation().getArea()){
			JOptionPane.showMessageDialog (null, "Adding the City " + city.getName()
					+" will exceed the area of the Country " + city.getCountryLocation().getName() + "."
					+"\n City was not added.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			city.getCountryLocation().removeCity(city.getName());
			return; // break out of method without adding Country
		}
		else if(popSum > city.getCountryLocation().getPopulation()){
			JOptionPane.showMessageDialog (null, "Adding the City " + city.getName()
					+" will exceed the population of the Country " + city.getCountryLocation().getName() + "."
					+"\n City was not added.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			city.getCountryLocation().removeCity(city.getName());
			return; // break out of method without adding Country
		}
		else{
			allCities.add(city); // Add city
		}
		
		//Notify the listener for the change on all cities
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "add city"));
		dirtyFileBit = true;
	} // end addCity
	
	/**
	 * Method that adds a place of interest to the model.
	 * @param place Place to be added. 
	 */
	public void addPlaceOfInterest(PlaceOfInterest place){
		//Find area sum of regionLocation
		double areaSum = 0;
		for(int i = 0; i < place.getPoliticalRegionList().size(); ++i){
			areaSum += place.getPoliticalRegionList().get(i).getArea();
		}
		
		//Add place to list if sanity checks are met
		if(place.getArea() > areaSum){
			JOptionPane.showMessageDialog (null, "The place " + place.getName()
					+ " has an area larger than associated region(s)."
					+"\n Place of interest was not added.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			//Remove place from associated regions
			for(int i = 0; i < place.getPoliticalRegionList().size(); ++i){
				place.getPoliticalRegionList().get(i).removePlaceOfInterest(place.getName());
			}
			return; // break out of method without adding place
		}
		else{
			allPlacesOfInterest.add(place); // Add place of interest
		}
		
		//Notify the listener to the change on all places
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "add place"));
		dirtyFileBit = true;
	} // end addPlaceOfInterest
	
	/**
	 * Method that adds a point of interest to the model.
	 * @param point Point of Interest to be added. 
	 */
	public void addPointOfInterest(PointOfInterest point){
		// Checks values against city location
		if(allCities != null){
		for(City city: allCities){
			if(city.getName().equals(point.getRegionLocation().getName())){
				//Had corresponding city, check latitude
				if(city.getLatitude() != null && !point.getLatitude().equalsIgnoreCase(city.getLatitude())){
					JOptionPane.showMessageDialog (null, "The point " + point.getName()
							+ " has a diferent latitude than the city " + city.getName() + "."
							+"\n Point of interest was not added.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					city.removePointOfInterest(point.getName());
					return; //leave method without adding point
				}
				//Check longitude
				if(city.getLongitude() != null && !point.getLongitude().equalsIgnoreCase(city.getLongitude())){
					JOptionPane.showMessageDialog (null, "The point " + point.getName()
							+ " has a diferent longitude than the city " + city.getName() + "."
							+"\n Point of interest was not added.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					city.removePointOfInterest(point.getName());
					return; //leave method without adding point
				}
				//Check elevation
				if(city.getElevation() != 0.0 && point.getElevation() != city.getElevation()){
					JOptionPane.showMessageDialog (null, "The point " + point.getName()
							+ " has a diferent elevation than the city " + city.getName() + "."
							+"\n Point of interest was not added.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					city.removePointOfInterest(point.getName());
					return; //leave method without adding point
				}
			}
		}
		}// end if-statement
		// Checks values against point location
		if(allPointsOfInterest != null){
		for(PointOfInterest checkPoint: allPointsOfInterest){
			if(checkPoint.getName().equals(point.getRegionLocation().getName())){
				//Had corresponding point, check latitude, longitude, and elevation
				//Check latitude
				if(checkPoint.getLatitude() != null && !point.getLatitude().equalsIgnoreCase(checkPoint.getLatitude())){
					JOptionPane.showMessageDialog (null, "The point " + point.getName()
							+ " has a diferent latitude than the parent point " + checkPoint.getName() + "."
							+"\n Point of interest was not added.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					checkPoint.removePointOfInterest(point.getName());
					return; //leave method without adding point
				}
				//Check longitude
				if(checkPoint.getLongitude() != null && !point.getLongitude().equalsIgnoreCase(checkPoint.getLongitude())){
					JOptionPane.showMessageDialog (null, "The point " + point.getName()
							+ " has a diferent longitude than the parent point " + checkPoint.getName() + "."
							+"\n Point of interest was not added.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					checkPoint.removePointOfInterest(point.getName());
					return; //leave method without adding point
				}
				//Check elevation
				if(checkPoint.getElevation() != 0.0 && point.getElevation() != checkPoint.getElevation()){
					JOptionPane.showMessageDialog (null, "The point " + point.getName()
							+ " has a diferent elevation than the parent point " + checkPoint.getName() + "."
							+"\n Point of interest was not added.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					checkPoint.removePointOfInterest(point.getName());
					return; //leave method without adding point
				}
			} // end if statement
		} // end for loop
		} // end if statement
		
		//Add point to list
		allPointsOfInterest.add(point);
		
		//Notify the listener to the change on all points
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "add point"));
		dirtyFileBit = true;
	} // end addPointOfInterest
	
	/**
	 * Method that edits a continent at a specified index.
	 * @param index Index of continent
	 * @param editedContinent Continent containing edits. 
	 */
	public void editContinent(int index, Continent editedContinent){
		//Save countryList and pointsList
		ArrayList<Country> tempCList = allContinents.get(index).getCountryList();
		ArrayList<PointOfInterest> tempPointOIList = allContinents.get(index).getPointOfInterestList();
		ArrayList<PlaceOfInterest> tempPlaceOIList = allContinents.get(index).getPlacesList();
		
		//Do continent sanity
		//Make sure continent name is not already there
		for(Continent continent2: allContinents){
			if(continent2.getName().equalsIgnoreCase(editedContinent.getName())){
				JOptionPane.showMessageDialog (null, "There is already a Continent with that name."
					 + "\n Duplicate Continent was not edited.", "Error",		
					 JOptionPane.WARNING_MESSAGE);
					return; // break out of method without adding Continent
				}
		} // end for-loop
		
		//Replace with edited version
		allContinents.set(index, editedContinent);
		
		//Reset lists
		allContinents.get(index).setCountryList(tempCList);
		allContinents.get(index).setPointOfInterestList(tempPointOIList);
		allContinents.get(index).setPlacesList(tempPlaceOIList);
		
		//Notify the listener for the change on all continents
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "edit continent"));
		dirtyFileBit = true;
	} // end editContinent
	
	/**
	 * Method that edits a country at a specified index.
	 * @param index Index of country.
	 * @param editCountry Country containing edits.
	 */
	public void editCountry(int index, Country editCountry){
		//Save cityList and pointsList and places list
		ArrayList<City> tempCList = allCountries.get(index).getCityList();
		ArrayList<PointOfInterest> tempPointOIList = allCountries.get(index).getPointOfInterestList();
		ArrayList<PlaceOfInterest> tempPlaceOIList = allCountries.get(index).getPlacesList();
		
		//Find population sum and area sum of continentLocation countries
		long popSum = 0;
		double areaSum = 0;
		for(int i = 0; i < editCountry.getContinentLocation().getCountryList().size(); ++i){
			popSum += editCountry.getContinentLocation().getCountryList().get(i).getPopulation();
			areaSum += editCountry.getContinentLocation().getCountryList().get(i).getArea();
		}
		
		//Add country to list if sanity checks are met
		if(areaSum > editCountry.getContinentLocation().getArea()){
			JOptionPane.showMessageDialog (null, "Adding the Country " + editCountry.getName()
					+" will exceed the area of the Continent " + editCountry.getContinentLocation().getName() + "."
					+"\n Country was not edited.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			editCountry.getContinentLocation().removeCountry(editCountry.getName());
			return; // break out of method without adding Country
		}
		else if(popSum > editCountry.getContinentLocation().getPopulation()){
			JOptionPane.showMessageDialog (null, "Adding the Country " + editCountry.getName()
					+" will exceed the population of the Continent " + editCountry.getContinentLocation().getName() + "."
					+"\n Country was not edited.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			editCountry.getContinentLocation().removeCountry(editCountry.getName());
			return; // break out of method without adding Country
		}

		//Replace with edited version
		allCountries.set(index, editCountry);
		
		//Reset lists
		allCountries.get(index).setCityList(tempCList);
		allCountries.get(index).setPointOfInterestList(tempPointOIList);
		allCountries.get(index).setPlacesList(tempPlaceOIList);
		
		//Notify the listener for the change on all countries
		processEvent(new ActionEvent(this, 
				ActionEvent.ACTION_PERFORMED, "edit country"));
		dirtyFileBit = true;
	} // end editCountry
	
	/**
	 * Method that edits a city at a specified index.
	 * @param index Index of city to be edited. 
	 * @param editCity City containing edits. 
	 */
	public void editCity(int index, City editCity){
		//Save pointsList and places list
		ArrayList<PointOfInterest> tempPointOIList = allCities.get(index).getPointOfInterestList();
		ArrayList<PlaceOfInterest> tempPlaceOIList = allCities.get(index).getPlacesList();
		
		//Find population sum and area sum of continentLocation countries
		//Assumes constructed city already has continent location
		long popSum = 0;
		double areaSum = 0;
		for(int i = 0; i < editCity.getCountryLocation().getCityList().size(); ++i){
			popSum += editCity.getCountryLocation().getCityList().get(i).getPopulation();
			areaSum += editCity.getCountryLocation().getCityList().get(i).getArea();
		}
				
		//Add city to list if sanity checks are met
		if(areaSum > editCity.getCountryLocation().getArea()){
			JOptionPane.showMessageDialog (null, "Adding the City " + editCity.getName()
						+" will exceed the area of the Country " + editCity.getCountryLocation().getName() + "."
						+"\n City was not edited.", "Error",		
						JOptionPane.WARNING_MESSAGE);
			editCity.getCountryLocation().removeCity(editCity.getName());
			return; // break out of method without adding Country
		}
		else if(popSum > editCity.getCountryLocation().getPopulation()){
			JOptionPane.showMessageDialog (null, "Adding the City " + editCity.getName()
					+" will exceed the population of the Country " + editCity.getCountryLocation().getName() + "."
					+"\n City was not edited.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			editCity.getCountryLocation().removeCity(editCity.getName());
				return; // break out of method without adding Country
		}
		
		//Replace with edited version
		allCities.set(index, editCity);
		
		//Reset lists
		allCities.get(index).setPointOfInterestList(tempPointOIList);
		allCities.get(index).setPlacesList(tempPlaceOIList);
		
		//Notify the listener for the change on all cities
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "edit city"));
		dirtyFileBit = true;
	} // end editCity
	
	/**
	 * Method that edits a place of interest at a specified index.
	 * @param index Index of place to be edited.
	 * @param editPlace Place containing edits.
	 */
	public void editPlaceOfInterest(int index, PlaceOfInterest editPlace){
		//Save points list
		ArrayList<PointOfInterest> tempPointOIList = allPlacesOfInterest.get(index).getPointOfInterestList();
		
		//Find area sum of regionLocation
		double areaSum = 0;
		for(int i = 0; i < editPlace.getPoliticalRegionList().size(); ++i){
			areaSum += editPlace.getPoliticalRegionList().get(i).getArea();
		}
		
		//Add place to list if sanity checks are met
		if(editPlace.getArea() > areaSum){
			JOptionPane.showMessageDialog (null, "The place " + editPlace.getName()
					+ " has an area larger than associated region(s)."
					+"\n Place of interest was not edited.", "Error",		
					JOptionPane.WARNING_MESSAGE);
			//Remove place from associated regions
			for(int i = 0; i < editPlace.getPoliticalRegionList().size(); ++i){
				editPlace.getPoliticalRegionList().get(i).removePlaceOfInterest(editPlace.getName());
			}
			return; // break out of method without adding place
		}
		
		//Replace with edited version
		allPlacesOfInterest.set(index, editPlace);
		
		//Reset lists
		allPlacesOfInterest.get(index).setPointOfInterestList(tempPointOIList);
		
		//Notify the listener for the change on all places
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "edit place"));
		dirtyFileBit = true;
	} // end editPlaceOfInterest
	
	/**
	 * Method that edits a point at a specified index.
	 * @param index Index of point to be edited. 
	 * @param editPoint Point of Interest containing edits. 
	 */
	public void editPointOfInterest(int index, PointOfInterest editPoint){
		//Save pointsList
		ArrayList<PointOfInterest> tempPointOIList = allPointsOfInterest.get(index).getPointOfInterestList();
		
		// Checks values against city location
		if(allCities != null){
			for(City city: allCities){
				if(city.getName().equals(editPoint.getRegionLocation().getName())){
						//Had corresponding city, check latitude
					if(city.getLatitude() != null && !editPoint.getLatitude().equalsIgnoreCase(city.getLatitude())){
						JOptionPane.showMessageDialog (null, "The Point " + editPoint.getName()
							+ " has a diferent latitude than the city " + city.getName() + "."
							+"\n Point of interest was not edited.", "Error",		
							JOptionPane.WARNING_MESSAGE);
						//Remove editPoint from associated regions
						city.removePointOfInterest(editPoint.getName());
						return; //leave method without adding point
					}
					//Check longitude
					if(city.getLongitude() != null && !editPoint.getLongitude().equalsIgnoreCase(city.getLongitude())){
							JOptionPane.showMessageDialog (null, "The point " + editPoint.getName()
									+ " has a diferent longitude than the city " + city.getName() + "."
									+"\n Point of interest was not edited.", "Error",		
									JOptionPane.WARNING_MESSAGE);
							//Remove point from associated regions
							city.removePointOfInterest(editPoint.getName());
						return; //leave method without adding point
					}
					//Check elevation
					if(city.getElevation() != 0.0 && editPoint.getElevation() != city.getElevation()){
						JOptionPane.showMessageDialog (null, "The point " + editPoint.getName()
									+ " has a diferent elevation than the city " + city.getName() + "."
									+"\n Point of interest was not edited.", "Error",		
									JOptionPane.WARNING_MESSAGE);
						//Remove point from associated regions
						city.removePointOfInterest(editPoint.getName());
						return; //leave method without adding point
					}
				}
			} // end for loop
		}// end if-statement
		// Checks values against point location
		if(allPointsOfInterest != null){
		for(PointOfInterest checkPoint: allPointsOfInterest){
			if(checkPoint.getName().equals(editPoint.getRegionLocation().getName())){
				//Had corresponding point, check latitude, longitude, and elevation
				//Check latitude
				if(checkPoint.getLatitude() != null && !editPoint.getLatitude().equalsIgnoreCase(checkPoint.getLatitude())){
					JOptionPane.showMessageDialog (null, "The point " + editPoint.getName()
							+ " has a diferent latitude than the parent point " + checkPoint.getName() + "."
							+"\n Point of interest was not edited.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					checkPoint.removePointOfInterest(editPoint.getName());
					return; //leave method without adding point
				}
				//Check longitude
				if(checkPoint.getLongitude() != null && !editPoint.getLongitude().equalsIgnoreCase(checkPoint.getLongitude())){
					JOptionPane.showMessageDialog (null, "The point " + editPoint.getName()
							+ " has a diferent longitude than the parent point " + checkPoint.getName() + "."
							+"\n Point of interest was not edited.", "Error",		
							JOptionPane.WARNING_MESSAGE);
					//Remove point from associated regions
					checkPoint.removePointOfInterest(editPoint.getName());
					return; //leave method without adding point
				}
				//Check elevation
				if(checkPoint.getElevation() != 0.0 && editPoint.getElevation() != checkPoint.getElevation()){
						JOptionPane.showMessageDialog (null, "The point " + editPoint.getName()
								+ " has a diferent elevation than the parent point " + checkPoint.getName() + "."
								+"\n Point of interest was not edited. .", "Error",		
								JOptionPane.WARNING_MESSAGE);
						//Remove point from associated regions
						checkPoint.removePointOfInterest(editPoint.getName());
						return; //leave method without adding point
					}
				} // end if statement
			} // end for loop
		} // end if statement

		//Replace with edited version
		allPointsOfInterest.set(index, editPoint);
		
		//Reset lists
		allPointsOfInterest.get(index).setPointOfInterestList(tempPointOIList);
		
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "edit point"));
		dirtyFileBit = true;
	} // end editPointOfInterest
	
	/**
	 * Method that deletes a continent.
	 * @param continent Continent to be deleted. 
	 */
	public void deleteContinent(Continent continent){
		//Check which countries are located on continent, delete them
		if(allCountries != null && continent.getCountryList() != null){
			for(int i = 0; i < continent.getCountryList().size(); ++i){
				for(int j = 0; j < allCountries.size(); ++j){
					if(allCountries.get(j).equals(continent.getCountryList().get(i))){
						deleteCountry(allCountries.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Check which points are located on continent, delete them
		if(allPointsOfInterest != null && continent.getPointOfInterestList() != null){
			for(int i = 0; i < continent.getPointOfInterestList().size(); ++i){
				for(int j = 0; j < allPointsOfInterest.size(); ++j){
					if(allPointsOfInterest.get(j).equals(continent.getPointOfInterestList().get(i))){
						deletePoint(allPointsOfInterest.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Check which places are located on continent, delete them
		if(allPlacesOfInterest != null && continent.getPlacesList() != null){
			for(int i = 0; i < continent.getPlacesList().size(); ++i){
				for(int j = 0; j < allPlacesOfInterest.size(); ++j){
					if(allPlacesOfInterest.get(j).equals(continent.getPlacesList().get(i))){
						deletePlace(allPlacesOfInterest.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		
		//Delete this continent
		allContinents.remove(continent);
		
		//Notify the listener for the change on all continents
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "delete continent"));
		dirtyFileBit = true;
	} // end deleteContinent
	
	/**
	 * Method that deletes a country.
	 * @param country Country to be deleted. 
	 */
	public void deleteCountry(Country country){
		//Check which cities are located on country, delete them
		if(allCities != null && country.getCityList() != null){
			for(int i = 0; i < country.getCityList().size(); ++i){
				for(int j = 0; j < allCities.size(); ++j){
					if(allCities.get(j).equals(country.getCityList().get(i))){
						deleteCity(allCities.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Check which points are located on country, delete them
		if(allPointsOfInterest != null && country.getPointOfInterestList() != null){
			for(int i = 0; i < country.getPointOfInterestList().size(); ++i){
				for(int j = 0; j < allPointsOfInterest.size(); ++j){
					if(allPointsOfInterest.get(j).equals(country.getPointOfInterestList().get(i))){
						deletePoint(allPointsOfInterest.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Check which places are located on continent, delete them
		if(allPlacesOfInterest != null && country.getPlacesList() != null){
			for(int i = 0; i < country.getPlacesList().size(); ++i){
				for(int j = 0; j < allPlacesOfInterest.size(); ++j){
					if(allPlacesOfInterest.get(j).equals(country.getPlacesList().get(i))){
						//Remove country from list
						allPlacesOfInterest.get(j).removePoliticalRegion(country.getName());
						//Delete place if last country was removed
						if(allPlacesOfInterest.get(j).getPoliticalRegionList().size()==0){
							deletePlace(allPlacesOfInterest.get(j));
						}
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Delete this country
		allCountries.remove(country);
		
		//Notify the listener for the change on all countries
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "delete country"));
		dirtyFileBit = true;
	} // end deleteCountry
	
	/**
	 * Method that deletes a city.
	 * @param city City to be deleted. 
	 */
	public void deleteCity(City city){
		//Check which points are located on city, delete them
		if(allPointsOfInterest != null && city.getPointOfInterestList() != null){
			for(int i = 0; i < city.getPointOfInterestList().size(); ++i){
				for(int j = 0; j < allPointsOfInterest.size(); ++j){
					if(allPointsOfInterest.get(j).equals(city.getPointOfInterestList().get(i))){
						deletePoint(allPointsOfInterest.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Check which places are located on city, delete them
		if(allPlacesOfInterest != null  && city.getPlacesList() != null){
			for(int i = 0; i < city.getPlacesList().size(); ++i){
				for(int j = 0; j < allPlacesOfInterest.size(); ++j){
					if(allPlacesOfInterest.get(j).equals(city.getPlacesList().get(i))){
						deletePlace(allPlacesOfInterest.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Delete this city
		allCities.remove(city);
		
		//Notify the listener to the change on all cities
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "delete city"));
		dirtyFileBit = true;
	} // end deleteCity
	
	/**
	 * Method that deletes a place of interest.
	 * @param place Place of interest to be deleted. 
	 */
	public void deletePlace(PlaceOfInterest place){
		//Check which points are located on place, delete them
		if(allPointsOfInterest != null && place.getPointOfInterestList() != null){
			for(int i = 0; i < place.getPointOfInterestList().size(); ++i){
				for(int j = 0; j < allPointsOfInterest.size(); ++j){
					if(allPointsOfInterest.get(j).equals(place.getPointOfInterestList().get(i))){
						deletePoint(allPointsOfInterest.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Delete place
		allPlacesOfInterest.remove(place);
		
		//Notify the listener to the change on all places
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "delete place"));
		dirtyFileBit = true;
	} // end deletePlace
	
	/**
	 * Method that deletes a point of interest.
	 * @param point Point to be deleted. 
	 */
	public void deletePoint(PointOfInterest point){
		//Check which points are located on point, delete them
		if(allPointsOfInterest != null && point.getPointOfInterestList() != null){
			for(int i = 0; i < point.getPointOfInterestList().size(); ++i){
				for(int j = 0; j < allPointsOfInterest.size(); ++j){
					if(allPointsOfInterest.get(j).equals(point.getPointOfInterestList().get(i))){
						deletePoint(allPointsOfInterest.get(j));
					} // end if statement
				} // end for loop
			} // end for loop
		}// end if statement
		
		//Delete point
		allPointsOfInterest.remove(point);
		
		//Notify the listener to the change on all points
		processEvent(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "delete point"));
		dirtyFileBit = true;
	} // end deletePoint

	/**
	 * Reads a file from keyboard input
	 * 
	 * @param fileName The name of the file to be read
	 * 
	 * @return A string array containing the file data by line
	 * @throws IOException if stream from a file cannot be read or closed.
	 */
	public String[] readTextFile(String fileName) throws IOException {
		/** String ArrayList to contain lines from the file. */
		ArrayList<String> fileLines = new ArrayList<String>();

		FileReader fr = new FileReader(fileName); // open the file
		BufferedReader br = new BufferedReader(fr); // wrap the FileReader into
													// a BufferedReader

		String line;
		// Takes each line and puts it into the String ArrayList fileLines
		while ((line = br.readLine()) != null) {
			fileLines.add(line);
		}

		// Converts ArrayList of lines to a String array
		fileLines.trimToSize();// trim to number of inputs
		fileLines.remove(null); // removes null from end of list
		String[] newFileLines = new String[fileLines.size()];
		newFileLines = fileLines.toArray(newFileLines);

		br.close(); // Closes the FileReader and BufferedReader
		
		return newFileLines;
	} // end readTextFile

	/**
	 * Writes the output to a specified textfile
	 * 
	 * @param regionList
	 *            An arrayList containing the relevant regions
	 * @param fileName
	 *            String representing desired filename for output
	 * 
	 * @throws IOException
	 *             if stream to a file cannot be written or closed.
	 */
	public <E extends Region> void writeTextFile(
			ArrayList<E> regionList, String fileName) throws IOException {
		// Makes a String to contain the regions
		String regionInfo = "";

		// Puts the names of the regions in the region information
		for (Region region : regionList) {
			regionInfo += region.toString() + "\n";
		}

		// Writes the string of region names to a specified file
		FileWriter outfile = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write(regionInfo);
		bw.newLine();
		bw.close();
		
		dirtyFileBit = false; //Data has been saved
	} // end writeTextFile

	/**
	 * Reads all information from a binary file and stores it within RegionModel as
	 * continents, countries, cities, points of interest, and places of interest.
	 * 
	 * @param fileName
	 *            Name of binary file geographic region information is in.
	 * @throws IOException
	 *             if stream to fileName cannot be written or closed.
	 * @throws ClassNotFoundException
	 *             if no definition for a specified Class is found.
	 */
	@SuppressWarnings("unchecked")
	public void readBinaryFile(String fileName) throws IOException,
			ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(fileName);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				fileInputStream);

		// Read in each collection of region data in the same order it was
		// written out
		ArrayList<Continent> continentList = (ArrayList<Continent>) objectInputStream
				.readObject(); // reads in new continents
		ArrayList<Country> countryList = (ArrayList<Country>) objectInputStream
				.readObject(); // read in all countries
		ArrayList<City> cityList = (ArrayList<City>) objectInputStream
				.readObject(); // read in all cities
		ArrayList<PlaceOfInterest> placeOfInterestList = 
			(ArrayList<PlaceOfInterest>) objectInputStream.readObject(); // read in all places
		ArrayList<PointOfInterest> pointOfInterestList =
			(ArrayList<PointOfInterest>) objectInputStream.readObject(); // read in all points
		
		// Overwrite existing data
		setAllContinents(continentList);
		setAllCountries(countryList);
		setAllCities(cityList);
		setAllPlacesOfInterest(placeOfInterestList);
		setAllPointsOfInterest(pointOfInterestList);

		objectInputStream.close(); // closes the FileInputStream and
									// ObjectInputStream
		
		//Notify view of changes to the model
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"binary load"));
		dirtyFileBit = true; 
	} // end readBinaryFile

	/**
	 * Writes the entirety of data contained within the RegionModel (Continents, Countries,
	 * etc.) as objects to a binary file.
	 * 
	 * @param fileName
	 *            Name of binary file to be written to.
	 * @throws IOException
	 *             if stream to fileName cannot be written or closed.
	 * @throws ClassNotFoundException
	 *             if no definition for a specified Class is found.
	 */
	public void writeBinaryFile(String fileName) throws IOException,
			ClassNotFoundException {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream);

		// Writes out each collection of geographic data to binary file.
		objectOutputStream.writeObject(allContinents);
		objectOutputStream.writeObject(allCountries); 
		objectOutputStream.writeObject(allCities);
		objectOutputStream.writeObject(allPlacesOfInterest);
		objectOutputStream.writeObject(allPointsOfInterest);

		objectOutputStream.close(); // closes the FileOutputStream and
									// ObjectOutputStream
		dirtyFileBit = false; //Data was saved
	} // end writeBinaryFille

	/**
	 * Generates Continent objects
	 * 
	 * @param continents
	 *            The String array containing data used to generate Continent
	 *            objects
	 */
	public void generateContinents(String[] continents) {
		//If allContinents is null, construct it
		if(allContinents == null){
			allContinents = new ArrayList<Continent>();
		}
		
		// Goes through string array of continents and splits each into
		// individual continent data
		for (int i = 0; i < continents.length; ++i) {
			String[] continentInfo = continents[i].split(", ");
			String continentName = continentInfo[0];
			long population = Long.parseLong(continentInfo[1]);
			double area = Double.parseDouble(continentInfo[2]);

			//Constructs new Continent from data and adds to array of all continents
			addContinent(new Continent(continentName, area, population));
		}
		
		//Notify listener of change on all continents
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"generate continents"));
		dirtyFileBit = true;
	} // end generateContinents

	/**
	 * Generates Country objects
	 * 
	 * @param countries
	 *            The String array containing data used to generate Country
	 *            objects
	 */
	public void generateCountries(String[] countries) {
		//If allCountries is null, construct it
		if(allCountries == null){
			allCountries = new ArrayList<Country>();
		}
		
		// Goes through String array of countries and splits each into
		// individual country data
		for (int i = 0; i < countries.length; ++i) {
			String[] countryInfo = countries[i].split(", ");
			String countryName = countryInfo[0];
			long population = Long.parseLong(countryInfo[1]);
			double area = Double.parseDouble(countryInfo[2]);
			String continentName = countryInfo[3];

			// Construct new Country from individual data
			Country createdCountry = new Country(countryName, area, population);

			// Searches through all continents to see if one matches this
			// country's Continent name
			boolean match = false;
			for (Continent continent : allContinents) {
				if (continent.getName().equalsIgnoreCase(continentName)) {
					continent.addCountry(createdCountry); // Adds country to continent
					createdCountry.setContinentLocation(continent); // Sets continent location
					match = true;
				}
			}
			
			//If continent was not found, country is not edited
			if(match != true){
				JOptionPane.showMessageDialog (null, "The Country " + createdCountry.getName()
						+ " does not have a valid Continent."
						+"\n Country was not edded.", "Error",		
						JOptionPane.WARNING_MESSAGE);
			}
			else{ //Add created country to arrayList of all countries
				addCountry(createdCountry);
			}
		}
		//Notify listener of change on all countries
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
						"generate countries"));
		dirtyFileBit = true;
	} // end generateCountries

	/**
	 * Generates City objects
	 * 
	 * @param cities The String array containing data used to generate City objects
	 */
	public void generateCities(String[] cities) {
		//If allCities is null, construct it
		if(allCities == null){
			allCities = new ArrayList<City>();
		}
		
		// Goes through String array of cities and splits each into individual
		// city data
		for (int i = 0; i < cities.length; ++i) {
			String[] cityInfo = cities[i].split(", ");
			String cityName = cityInfo[0];
			long population = Long.parseLong(cityInfo[1]);
			double area = Double.parseDouble(cityInfo[2]);
			String countryName = cityInfo[3];
			City createdCity;

			// Checks to see if this city has extra latitude, longitude,
			// elevation information
			if (cityInfo.length == 7) {
				String latitude = cityInfo[4];
				String longitude = cityInfo[5];
				int elevation = Integer.parseInt(cityInfo[6]);

				// Constructs City with extra information
				createdCity = new City(cityName, area, population, latitude,
						longitude, elevation);
			} else {
				//Constructs city from basic data
				createdCity = new City(cityName, area, population);
			}

			// Searches through all countries to see if one matches this city's
			// Country name
			boolean match = false;
			for (Country country : allCountries) {
				if (country.getName().equals(countryName)) {
					country.addCity(createdCity); // Adds City to matching Country
					createdCity.setCountryLocation(country); // sets City country Location
					match = true;
				} // ends if
			} // ends for-each
			
			//If country was not found, city is not added
			if(match != true){
				JOptionPane.showMessageDialog (null, "The City " + createdCity.getName()
						+ " does not have a valid Country."
						+"\n City was not added.", "Error",		
						JOptionPane.WARNING_MESSAGE);
			}
			else{ //Add created city to arrayList of all cities
				addCity(createdCity);
			}
		} // ends for loop
		
		//Notify listener of change on all cities
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
								"generate cities"));
		dirtyFileBit = true;
	} // end generateCities

	/**
	 * This method takes in an array of Strings and creates places of interest
	 * objects based on the String information.
	 * 
	 * @param placesOfInterest
	 *            Array of Strings containing the file data used to generate
	 *            places of interest objects
	 */
	public void generatePlacesOfInterest(String[] placesOfInterest) {
		//If allPlacesOfInterest is null, construct it
		if(allPlacesOfInterest == null){
			allPlacesOfInterest = new ArrayList<PlaceOfInterest>();
		}
		
		// Goes through string array of places of interest and splits into
		// individual data
		for (int i = 0; i < placesOfInterest.length; ++i) {
			String[] placeOfInterestInfo = placesOfInterest[i].split(", ");

			// Store data into separate variables
			String placeName = placeOfInterestInfo[0];
			int placeArea = Integer.parseInt(placeOfInterestInfo[1]);
			String placeType = placeOfInterestInfo[2];

			// Construct new place of interest with given data
			PlaceOfInterest newPlace = new PlaceOfInterest(placeName,
					placeArea, placeType);

			//Add matching PoliticalRegion(s) to newly created place
			boolean match = false;
			for(int j = 3; j < placeOfInterestInfo.length ; ++j ){
				//Searches through continents
				if(allContinents != null){
					for(Continent continent: allContinents){
						if(continent.getName().equalsIgnoreCase(placeOfInterestInfo[j])){
							newPlace.addPoliticalRegion(continent);
							continent.addPlaceOfInterest(newPlace);
							match = true;
						}
					}// end for-each loop
				}
				
				//Searches through countries
				if(allCountries != null){
					for(Country country: allCountries){
						if(country.getName().equalsIgnoreCase(placeOfInterestInfo[j])){
							newPlace.addPoliticalRegion(country);
							country.addPlaceOfInterest(newPlace);
							match = true;
						}
					}// end for-each loop
				}
				
				//Searches through Cities
				if(allCities != null){
					for(City city: allCities){
						if(city.getName().equalsIgnoreCase(placeOfInterestInfo[j])){
							newPlace.addPoliticalRegion(city);
							city.addPlaceOfInterest(newPlace);
							match = true;
						}
					}// end for-each loop
				}
			}// end for loop
			
			//If region was not found, place is not added
			if(match != true){
				JOptionPane.showMessageDialog (null, "The Place " + newPlace.getName()
						+ " does not have a valid region location."
						+"\n Place of interest was not added.", "Error",		
						JOptionPane.WARNING_MESSAGE);
			}
			else{ //Add created place to arrayList of all places
				addPlaceOfInterest(newPlace);
			}
		} // end for loop
		
		//Notify listener of change on all places of interest
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"generate places"));
		dirtyFileBit = true;
	} // end generatePlacesOfInterest
	
	/**
	 * This method takes in an array of String and creates points of interest
	 * objects from the data in the array.
	 * 
	 * @param pointsOfInterest Array of Strings containing point of interest data.
	 */
	public void generatePointsOfInterest(String[] pointsOfInterest) {
		//If allPointsOfInterest is null, construct it
		if(allPointsOfInterest == null){
			allPointsOfInterest = new ArrayList<PointOfInterest>();
		}
		
		// Goes through string array of places of interest and splits into
		// individual data
		for (int i = 0; i < pointsOfInterest.length; ++i) {
			String[] pointOfInterestInfo = pointsOfInterest[i].split(", ");

			// Store data into separate variables
			String pointName = pointOfInterestInfo[0];
			String pointType = pointOfInterestInfo[1];
			String pointLatitude = pointOfInterestInfo[2];
			String pointLongitude = pointOfInterestInfo[3];
			Double pointElevation = Double.parseDouble(pointOfInterestInfo[4]);
			String pointLocation = pointOfInterestInfo[5];

			// Construct new place of interest with given data
			PointOfInterest newPoint = new PointOfInterest(pointName,
					pointType, pointLatitude, pointLongitude, pointElevation);

			//Add matching EnclosingRegion to newly created place
			//Searches through continents
			boolean match = false;
			if(allContinents != null){
				for(Continent continent: allContinents){
					if(continent.getName().equalsIgnoreCase(pointLocation)){
						newPoint.setRegionLocation(continent);
						continent.addPointOfInterest(newPoint);
						match = true;
					}
				}// end for-each loop
			}
				
			//Searches through countries
			if(allCountries != null){
				for(Country country: allCountries){
					if(country.getName().equalsIgnoreCase(pointLocation)){
						newPoint.setRegionLocation(country);
						country.addPointOfInterest(newPoint);
						match = true;
					}
				}// end for-each loop
			}
			
			//Searches through cities
			if(allCities != null){
				for(City city: allCities){
					if(city.getName().equalsIgnoreCase(pointLocation)){
						newPoint.setRegionLocation(city);
						city.addPointOfInterest(newPoint);
						match = true;
					}
				}// end for-each loop
			}
			
			//Searches through places
			if(allPlacesOfInterest != null){
				for(PlaceOfInterest place: allPlacesOfInterest){
					if(place.getName().equalsIgnoreCase(pointLocation)){
						newPoint.setRegionLocation(place);
						place.addPointOfInterest(newPoint);
						match = true;
					}
				}// end for-each loop
			}
			
			//Searches through points
			if(allPointsOfInterest != null){
				for(PointOfInterest point: allPointsOfInterest){
					if(point.getName().equalsIgnoreCase(pointLocation)){
						newPoint.setRegionLocation(point);
						point.addPointOfInterest(newPoint);
						match = true;
					}
				}// end for-each loop
			}
			
			//If region was not found, point is not added
			if(match != true){
				JOptionPane.showMessageDialog (null, "The point " + newPoint.getName()
						+ " does not have a valid region location."
						+"\n Point of interest was not added.", "Error",		
						JOptionPane.WARNING_MESSAGE);
			}
			else{ //Add created point to arrayList of all points
				addPointOfInterest(newPoint);
			}
		} // end for loop
		
		//Notify listener of change on all points of interest
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"generate points"));
		dirtyFileBit = true;
	} // end generatePointsOfInterest
	
	/**
	 * Sorts a list of PoliticalRegion objects based on the user's sort choice.
	 * 
	 * @param choice A String containing the user's sort choice.
	 * @param regionList An ArrayList of regions to be sorted.
	 */
	public <E extends PoliticalRegion> void sortPoliticalRegionListByChoice(
			String choice, ArrayList<E> regionList) {
		if (choice.compareToIgnoreCase("AR") == 0) {
			// Sorts list of regions based on area
			Collections.sort(regionList, EnclosingRegion.areaComparator);
		} else if (choice.compareToIgnoreCase("PO") == 0) {
			// Sorts list of regions based on population
			Collections.sort(regionList, PoliticalRegion.populationComparator);
		} else if (choice.compareToIgnoreCase("LE") == 0) {
			// Sorts list of regions based on name, which is the natural
			// ordering
			Collections.sort(regionList);
		} else if (choice.compareToIgnoreCase("RA") == 0) {
			// Sorts list of regions randomly
			Collections.shuffle(regionList);
		}
	} // end sortPoliticalRegionListByChoice

	/**
	 * Sorts a list of region objects based on the user's sort choice.
	 * 
	 * @param choice
	 *            A String containing the user's sort choice.
	 * @param regionList
	 *            An ArrayList of regions to be sorted.
	 */
	public static <E extends Region> void sortRegionListByChoice(String choice,
			ArrayList<E> regionList) {
		if (choice.compareToIgnoreCase("LE") == 0) {
			// Sorts list of regions based on name, which is the natural
			// ordering
			Collections.sort(regionList);
		} else if (choice.compareToIgnoreCase("RA") == 0) {
			// Sorts list of regions randomly
			Collections.shuffle(regionList);
		}
	} // end sortRegionListByChoice


	/**
	 * This method displays a bar graph to the screen. The bar graph has
	 * multiple bars organized from greatest to least and reflects either the
	 * area or population of a list of regions.
	 * 
	 * @param regionList List of regions (arranged either by area or population) to be
	 *            graphed
	 * @param sortOption Specifies how the list of geographic regions is sorted
	 */
	public static <E extends EnclosingRegion> void generateMultiBarGraph(
			ArrayList<E> regionList,  String sortOption) {
		// reverse the regionList, assuming it was sorted from least to greatest
		Collections.reverse(regionList);

		// Create frame to hold bar graph components
		 new SimpleBarChartView(regionList, sortOption);

		
	} // end generateMultiBarGraph

	/**
	 * This method displays a graph composed of a single bar. The bar is divided
	 * into smaller segments based on the areas of geographic regions contained
	 * in a list. The area of the geographic region is shown to the left, the
	 * name to the right.
	 * 
	 * @param regionList
	 *            List of geographic regions to be graphed.
	 * @param searchOption
	 *            Specifies what type of information the geographic region list
	 *            contains.
	 */
	public static <E extends EnclosingRegion> void displayStackedBarChartView(
			ArrayList<E> regionList, int searchOption) {

		// Create frame to hold bar graph components
		JFrame frame = new StackedBarChartView(regionList);

		//TODO: Set title of frame based on information in regionList
		frame.setSize(450, 500); // frame default size
		frame.setLocationRelativeTo(null); // centers frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true); // Displays to user
	} // end generateSingleBarGraph

	/**
	 * This method takes an array of cities then plots it on a plate caree map.
	 * The map is displayed to the user.
	 * 
	 * @param cityList List of cities to be plotted
	 */
	public static void displayMapView(ArrayList<City> cityList) {
		// Creates frame to hold plate caree map components
		JFrame frame = new MapView(cityList);
		
		//TODO: make map work for cities AND points of interest
		Insets insets = frame.getInsets();
		frame.setSize(700 + insets.left + insets.right, 405 + insets.top
				+ insets.bottom);
		frame.setLocationRelativeTo(null); // Centers frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true); // Displays frame to the user
	} // end generatePlateCareeMap
	
	
	/**
	 * Register an action event listener.
	 */
	public synchronized void addActionListener(ActionListener l) {
		// If array is null, construct it
		if(actionListenerList == null){
			actionListenerList = new ArrayList<ActionListener>();
		}
		
		// Add action listener to list
		actionListenerList.add(l);
	} // end addActionListener
	
	/**
	 * Remove an action event listener.
	 */
	public synchronized void removeActionListener(ActionListener l) {
		if(actionListenerList != null && actionListenerList.contains(l)){
			actionListenerList.remove(l);
		}
	} // end removeActionListener
	
	/**
	 * Fire event.
	 */
	@SuppressWarnings("unchecked")
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;
		
		//To protect against list changing while we are going through it
		synchronized (this) {
			if (actionListenerList == null) return; // There are no action listeners.
			list = (ArrayList<ActionListener>) actionListenerList.clone();
		}
		
		for(int i = 0; i < list.size(); ++i){
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		} // informs all listeners of the action that was performed
	} // end processEvent
} // end RegionModel
