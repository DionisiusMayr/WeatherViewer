package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * This class is for Local Weather View
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

	//constructor 1
	public LocalWeatherView(String cityName) throws JSONException, IOException {
		city = cityName;
		data = new WebInterface(city);
		object = data.createJSONObject(data.getLocalWeatherURL());
	}

	//constructor 2
	public LocalWeatherView(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		data = new WebInterface(city, country);
		object = data.createJSONObject(data.getLocalWeatherURL());
	}

	///////////////////////////HELPER METHODS///////////////////////
    private JSONObject getMain() throws JSONException, IOException {
		main = object.optJSONObject("main");
		return main;
    }

    private JSONObject getSys() throws JSONException, IOException {
		sys = object.optJSONObject("sys");
		return sys;
    }

    private JSONObject getWind() throws JSONException, IOException {
		wind = object.optJSONObject("wind");
		return wind;
    }

    private JSONObject getWeather() throws JSONException, IOException {
    	weatherArray = object.optJSONArray("weather");
		weather = weatherArray.getJSONObject(0);
		return weather;
    }
    ///////////////////////////HELPER METHODS///////////////////////

    //GETTERS
    public String getDate() throws JSONException, IOException {
    	int dt = object.optInt("dt");
    	Date date = new Date(dt * 1000L);
        return date.toString();
	}

    public String getCityName() throws JSONException, IOException {
		return object.optString("name");
	}

    public String getCountry() throws JSONException, IOException {
		return getSys().optString("country", null);
	}

	/**
	 * getTemperature method to return temperature
	 * @return string temperature
	 */
	public String getTemperature() throws JSONException, IOException {
	    DecimalFormat f = new DecimalFormat("0");
	    double temp = getMain().optDouble("temp");
	    if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
	}

	/** getPressure method to return pressure
	 *  @return strong pressure
	 */
	public String getPressure() throws JSONException, IOException {
		return getMain().optString("pressure", null) + " hpa";
	}

	/** getHumidity method to return humidity
	 *  @return humidity value
	 */
	public String getHumidity() throws JSONException, IOException {
		return getMain().optString("humidity", null) + " %";
	}

	/** getTempMin method to return min Temp
	 *  @return TempMin
	 */
	public String getTempMin() throws JSONException, IOException {
	    DecimalFormat f = new DecimalFormat("0");
	    double temp_min = getMain().optDouble("temp_min");
		if(GUIApp.pref.getUnit().equals("metric"))
			return f.format(temp_min) + " \u00b0C";
    	else
    		return f.format(temp_min) + " \u00b0F";
	}

	/** getTempMax method to return Max temp
	 *  @return GetTempMax
	 */
	public String getTempMax() throws JSONException, IOException {
	    DecimalFormat f = new DecimalFormat("0");
	    double temp_max = getMain().optDouble("temp_max");
		if(GUIApp.pref.getUnit().equals("metric"))
			return f.format(temp_max) + " \u00b0C";
    	else
    		return f.format(temp_max) + " \u00b0F";
	}

	/** getWindSpeed method to return wind speed
	 *  @return the value of windspeed
	 */
	public String getWindSpeed() throws JSONException, IOException {
		return getWind().optString("speed", null) + " m/s";
	}

	/** getWindDirection method to return wind direction
	 *  @return the value of wind direction
	 */
	public String getWindDirection() throws JSONException, IOException {
		double degree = getWind().getDouble("deg");
		String letters[] = { "N", "NE", "E", "SE", "S", "SW", "W", "NW", "N" };
		return letters[(int)Math.round((degree / 45))];
	}

	/** getSunrise method to return sunrise time
	 *  @return the value of sunrise
	 */
	public String getSunrise() throws JSONException, IOException {
		Date date = new Date(getSys().optInt("sunrise") * 1000L);
        return dateParse(date.toString());
	}

	/** getSunset method to return sunset time
	 *  @return the string sunset
	 */
	public String getSunset() throws JSONException, IOException {
		Date date = new Date(getSys().optInt("sunset") * 1000L);
        return dateParse(date.toString());
	}

	/** getSkyCondition method to return Sky condition
	 *  @return the string sky condition
	 */
	public String getSkyCondition() throws JSONException, IOException {
		return getWeather().optString("main", null);
	}
	
	private String dateParse(String date) {
		StringTokenizer st = new StringTokenizer(date);
		st.nextToken();
		st.nextToken();
		st.nextToken();
		String time = st.nextToken().substring(0, 5);		
		return (time);
	}
}
