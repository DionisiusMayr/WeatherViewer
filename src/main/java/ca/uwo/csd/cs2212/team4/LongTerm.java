package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * This is class responsible for getting the information about the Long Term Forecast.
 * @author team4
 */
public class LongTerm {
	private String city;
	private String country;
	private WebInterface data;
	private JSONObject object;
	private JSONArray list;
	private JSONObject internObject;
	private JSONArray weatherArray;
	private JSONObject weather;

    /** Constructor to build the object from just a city
     * @param cityName Name of the city to be used on the query.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
	public LongTerm(String cityName) throws JSONException, IOException {
        city = cityName;
        data = new WebInterface(city);
		object = data.createJSONObject(data.getLongTermURL());
        list = object.getJSONArray("list");
	}

    /** Constructor to build the object from a city and the respective country.
     * @param cityName Name of the city to be used on the query.
     * @param countryCode Name of the country of the city to be used on the query.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
	public LongTerm(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		data = new WebInterface(city,country);
		object = data.createJSONObject(data.getLongTermURL());
        list = object.getJSONArray("list");
	}

    /* HELPER METHODS */

    /** Auxiliary method to get the temperature object i from the JSONArray
     * @param i int the index of the array that you want to get the object
     * @return the i'th "temperature" JSON object inside the JSONArray
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getTemperature(int i) throws JSONException, IOException {
        JSONObject internObject = list.getJSONObject(i);
        JSONObject temperature = internObject.getJSONObject("temp");
        return temperature;
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

    /** Method used to get the date/time of the day i.
     * @param i int day offset of the forecast
     * @return String the complete date/time for the day i.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getDate(int i) throws JSONException, IOException {
        internObject = list.getJSONObject(i);
        Date date = new Date(internObject.getInt("dt") * 1000L); // It has to be in milliseconds, thus the * 1000.
        return date.toString();
    }

    /** Method used to get the temperature value and the unit of it for the day i.
     * @param i int day offset of the forecast
     * @return String temperature and the correct unit for the day i.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getTempDay(int i) throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
	    double temp = getTemperature(i).optDouble("day");
    	if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
    }

    /** Method used to get the minimum temperature value and the unit of it for the day i.
     * @param i int day offset of the forecast
     * @return String minimum temperature and the correct unit for the day i.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getTempMin(int i) throws  JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
	    double temp = getTemperature(i).optDouble("min");
        if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
    }

    /** Method used to get the maximum temperature value and the unit of it for the day i.
     * @param i int day offset of the forecast
     * @return String maximum temperature and the correct unit for the day i.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getTempMax(int i) throws  JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
	    double temp = getTemperature(i).optDouble("max");
    	if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
    }

    /** Method used to get the sky condition for the day i.
     * @param i int day offset of the forecast
     * @return String the sky condition, like Clear or Clouds for the day i.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getSkyCondition(int i) throws  JSONException, IOException {
        return getWeather(i).optString("main");
    }

    /* END OF GETTERS */
}

