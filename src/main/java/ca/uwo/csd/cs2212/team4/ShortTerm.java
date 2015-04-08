package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * This is the class responsible for getting the information about the Short Term Forecast.
 * @author team4
 */
public class ShortTerm {
	private String city;
	private String country;
	private WebInterface data;
	private JSONObject object;
	private JSONArray list;
	private JSONObject cityJSON;
	private JSONObject internObject;
	private JSONObject main;
	private JSONArray weatherArray;
	private JSONObject weather;

    /** Constructor to build the object from just a city
     * @param cityName Name of the city to be used on the query.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
	public ShortTerm(String cityName) throws JSONException, IOException {
		city = cityName;
		data = new WebInterface(city);
        object = data.createJSONObject(data.getShortTermURL());	
        list = object.getJSONArray("list");
	}

    /** Constructor to build the object from a city and the respective country.
     * @param cityName Name of the city to be used on the query.
     * @param countryCode Name of the country of the city to be used on the query.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
	public ShortTerm(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		data = new WebInterface(city, country);
        object = data.createJSONObject(data.getShortTermURL());	
        list = object.getJSONArray("list"); 
	}
	
	/* HELPER METHODS */

    /** Auxiliary method to get the main object i from the JSONArray
     * @param i int the index of the array that you want to get the object
     * @return the i'th "main" JSON object inside the JSONArray
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getMain(int i) throws JSONException, IOException {
        internObject = list.getJSONObject(i);
        main = internObject.getJSONObject("main");
        return main;
    }

    /** Auxiliary method to get the weather object i from the JSONArray
     * @param i int the index of the array that you want to get the object
     * @return the i'th "weather" JSON object inside the JSONArray
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getWeather(int i) throws JSONException, IOException {
        internObject = list.getJSONObject(i);
        weatherArray = internObject.getJSONArray("weather");
        weather = weatherArray.getJSONObject(0);
        return weather;
    }

    /* END OF HELPER METHODS */

    /* GETTERS */

    /** Method used to get the date/time of the hour i * 3.
     * @param i int offset of the forecast
     * @return String the complete date/time for the day i * 3.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getDate(int i) throws JSONException, IOException {
        internObject = list.getJSONObject(i);
        Date date = new Date(internObject.getInt("dt") * 1000L); // It has to be in miliseconds, thus the * 1000.
        return date.toString();
    }

    /** Method used to get the temperature of the hour i * 3.
     * @param i int offset of the forecast
     * @return String temperature and the correct unit for hour i * 3.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getTemperature(int i) throws  JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
	    double temp = getMain(i).optDouble("temp");
    	if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
    }

    /** Method used to get the sky condition of the hour i * 3.
     * @param i int offset of the forecast
     * @return String the sky condition, like Clear or Clouds for hour i * 3.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getSkyCondition(int i) throws  JSONException, IOException {
        return getWeather(i).optString("main");
    }

    /* END OF GETTERS */
}
