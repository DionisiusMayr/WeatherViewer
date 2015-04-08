package ca.uwo.csd.cs2212.team4;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * This is the class responsible for storing the preferences (city, country and unit).
 * @author team4
 */
public class Preferences implements Serializable {
    private String cityName;
    private String countryName;
    private String unit;
    private static final long serialVersionUID = 2L;

    /** Constructor to build the preferences from a city, country and unit.
     * @param city String with the name of the city.
     * @param country String with the name of the country.
     * @param unitp String unit, should be metric or imperial.
     * */
    Preferences(String city, String country, String unitp) {
        cityName = new String(city);
        countryName = new String(country);
        if(unitp.equals("metric") || unitp.equals("imperial"))
            unit = new String(unitp);
    }

    /** Method used to save the preferences on the file preferences.dat
     * @throws java.lang.Exception if it can't find the file.
     */
    public void storePreferences() throws Exception {
        /* Serialize the object: */
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("preferences.dat"));
        out.writeObject(this);
        out.close();
    }

    /** Method used to retrieve the preferences object data from the file preferences.dat
     * @throws java.lang.Exception if it can't find the file.
     */
    public Preferences loadPreferences() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("preferences.dat"));

        Preferences objectRead = (Preferences) in.readObject();
        in.close();
        
        return objectRead;
    }

    /* GETTERS */

    /** Method that gets the city name stored on the preferences.
     * @return String with the name of the stored city.
     */
    public String getCity() {
        return cityName;
    }

    /** Method that gets the country name stored on the preferences.
     * @return String with the name of the stored country.
     */
    public String getCountry() {
        return countryName;
    }

    /** Method that gets the unit stored on the preferences.
     * @return String with the unit stored (metric or imperial).
     */
    public String getUnit() {
        return unit;
    }

    /* END OF GETTERS */

    /* SETTERS */

    /** Setter of city
     * @param city String with the name of the city.
     */
    public void setCity(String city) {
        cityName = city;
    }

    /** Setter of country
     * @param country String with the name of the country.
     */
    public void setCountry(String country) {
        countryName = country;
    }

    /** Setter of unit
     * @param unitp String unit, should be metric or imperial.
     */
    public void setUnit(String unitp) {
        if(unitp.equals("metric") || unitp.equals("imperial"))
            unit = unitp;
    }

    /** Setter for all the three attributes.
     * @param city String with the name of the city.
     * @param country String with the name of the country.
     * @param unitp String unit, should be metric or imperial.
     */
    public void setPreferences(String city, String country, String unitp) {
        setCity(city);
        setCountry(country);
        setUnit(unitp);
    }

    /* END OF SETTERS */
}
