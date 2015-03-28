package ca.uwo.csd.cs2212.team4;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class Preferences implements Serializable {
    private String cityName;
    private String countryName;
    private String unit;
    private static final long serialVersionUID = 2L;

    Preferences(String city, String country, String unitp) {
        cityName = new String(city);
        countryName = new String(country);
        if(unitp == "metric" || unitp == "imperial")
            unit = new String(unitp);
    }

    public void storePreferences() throws Exception {
        /* Serialize the object: */
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("preferences.dat"));
        out.writeObject(this);
        out.close();
    }

    public static Preferences loadPreferences() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("preferences.dat"));

        Preferences objectRead = (Preferences) in.readObject();
        in.close();
        
        return objectRead;
    }

    public String getCity() {
        return cityName;
    }

    public String getCountry() {
        return countryName;
    }    

    public String getUnit() {
        return unit;
    }

    public void setCity(String city) {
        cityName = city;
    }

    public void setCountry(String country) {
        countryName = country;
    }

    public void setUnit(String unitp) {
        if(unitp.equals("metric") || unitp.equals("imperial"))
            unit = unitp;
    }

    public void setPreferences(String city, String country, String unitp) {
        setCity(city);
        setCountry(country);
        setUnit(unitp);
    }

    // TODO remove this function
    public void printValues() {
        System.out.println("City: " + cityName);
        System.out.println("Country: " + countryName);
        System.out.println("Units: " + unit);
    }

    // TODO remove this main function
    public static void main(String[] args) throws Exception {
        System.out.println("Start");
        Preferences pref = new Preferences("London", "Canada", "metric");
        pref.storePreferences();
        //Preferences pref = Preferences.loadPreferences();
        pref.printValues();
        
    }
}
