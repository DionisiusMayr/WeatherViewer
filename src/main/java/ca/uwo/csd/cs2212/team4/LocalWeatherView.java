package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * This is the class responsible for getting the information about the Current Weather.
 * @author team4
 */
public class LocalWeatherView {
	private String city;
	private String country;
	private WebInterface data;
	private JSONObject object;
	private JSONObject main;
	private JSONObject wind;
	private JSONObject sys;
	private JSONArray weatherArray;
	private JSONObject weather;

	/** Constructor to build the object from just a city
	 * @param cityName Name of the city to be used on the query.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
	 * */
	public LocalWeatherView(String cityName) throws JSONException, IOException {
		city = cityName;
		data = new WebInterface(city);
		object = data.createJSONObject(data.getLocalWeatherURL());
	}

    /** Constructor to build the object from a city and the respective country.
     * @param cityName Name of the city to be used on the query.
     * @param countryCode Name of the country of the city to be used on the query.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
	public LocalWeatherView(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		data = new WebInterface(city, country);
		object = data.createJSONObject(data.getLocalWeatherURL());
	}

    /* HELPER METHODS */

	/** Auxiliary method to get the main object from the complete JSONObject
     * @return the "main" JSON object inside the full JSON object
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getMain() throws JSONException, IOException {
		main = object.optJSONObject("main");
		return main;
    }

    /** Auxiliary method to get the sys object from the complete JSONObject
     * @return the "sys" JSON object inside the full JSON object
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getSys() throws JSONException, IOException {
		sys = object.optJSONObject("sys");
		return sys;
    }

    /** Auxiliary method to get the wind object from the complete JSONObject
     * @return the "wind" JSON object inside the full JSON object
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getWind() throws JSONException, IOException {
		wind = object.optJSONObject("wind");
		return wind;
    }

    /** Auxiliary method to get the weather object from the complete JSONObject
     * @return the "weather" JSON object inside the full JSON object
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getWeather() throws JSONException, IOException {
    	weatherArray = object.optJSONArray("weather");
		weather = weatherArray.getJSONObject(0);
		return weather;
    }
    /** Auxiliary method to convert the date in a full format to just the time like this xx:xx
     * @param date String date in a complete format, with the date, time, year...
     * @return String time in the format xx:xx
     * */
    private String dateParse(String date) {
        StringTokenizer st = new StringTokenizer(date);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        String time = st.nextToken().substring(0, 5);
        return (time);
    }

    /* END OF HELPER METHODS */

    /* GETTERS */

    /** Method that gets the city name used on the query.
     * @return String with the name of the queried city.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    public String getCityName() throws JSONException, IOException {
		return object.optString("name");
	}

    /** Method that gets the country name used on the query.
     * @return String with the name of the country used on the query.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    public String getCountry() throws JSONException, IOException {
		return getSys().optString("country", null);
	}

	/** Method used to get the temperature and the unit of it.
	 * @return String temperature with the correct unit.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
	 */
	public String getTemperature() throws JSONException, IOException {
	    DecimalFormat f = new DecimalFormat("0");
	    double temp = getMain().optDouble("temp");
	    if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
	}

    /** Method used to get the pressure and the unit of it.
     * @return String pressure with the unit (hpa).
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getPressure() throws JSONException, IOException {
		return getMain().optString("pressure", null) + " hpa";
	}

    /** Method used to get the humidity value and the unit of it.
     * @return String humidity in percentage.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getHumidity() throws JSONException, IOException {
		return getMain().optString("humidity", null) + " %";
	}

    /** Method used to get the minimum temperature value and the unit of it.
     * @return String minimum temperature and the correct unit.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getTempMin() throws JSONException, IOException {
	    DecimalFormat f = new DecimalFormat("0");
	    double temp_min = getMain().optDouble("temp_min");
		if(GUIApp.pref.getUnit().equals("metric"))
			return f.format(temp_min) + " \u00b0C";
    	else
    		return f.format(temp_min) + " \u00b0F";
	}

    /** Method used to get the maximum temperature value and the unit of it.
     * @return String maximum temperature and the correct unit.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getTempMax() throws JSONException, IOException {
	    DecimalFormat f = new DecimalFormat("0");
	    double temp_max = getMain().optDouble("temp_max");
		if(GUIApp.pref.getUnit().equals("metric"))
			return f.format(temp_max) + " \u00b0C";
    	else
    		return f.format(temp_max) + " \u00b0F";
	}

    /** Method used to get the speed of the wind and the unit of it.
     * @return String speed of the wind and the unit (m/s).
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getWindSpeed() throws JSONException, IOException {
		return getWind().optString("speed", null) + " m/s";
	}

    /** Method used to get the direction of the wind.
     * @return String a letter representing the direction of the wind, like N, NE, E, SE...
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getWindDirection() throws JSONException, IOException {
		double degree = getWind().getDouble("deg");
		String letters[] = { "N", "NE", "E", "SE", "S", "SW", "W", "NW", "N" };
		return letters[(int)Math.round((degree / 45))];
	}

    /** Method used to get the time of the sunrise.
     * @return String the time of the sunrise (xx:xx).
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getSunrise() throws JSONException, IOException {
		Date date = new Date(getSys().optInt("sunrise") * 1000L);
        return dateParse(date.toString());
	}

    /** Method used to get the time of the sunset.
     * @return String the time of the sunset (xx:xx).
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getSunset() throws JSONException, IOException {
		Date date = new Date(getSys().optInt("sunset") * 1000L);
        return dateParse(date.toString());
	}

    /** Method used to get the sky condition.
     * @return String the sky condition, like Clear or Clouds.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
	public String getSkyCondition() throws JSONException, IOException {
		return getWeather().optString("main", null);
	}

    /* END OF GETTERS */
}
