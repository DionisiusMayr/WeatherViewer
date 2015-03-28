package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

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
		data = new WebInterface(city);
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
	    DecimalFormat f = new DecimalFormat("0.0");
	    double temp = getMain().optDouble("temp");
		return f.format(temp) + " \u00b0C";
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
		return getMain().optString("temp_min", null) + " \u00b0C";
	}

	/** getTempMax method to return Max temp
	 *  @return GetTempMax
	 */
	public String getTempMax() throws JSONException, IOException {
		return getMain().optString("temp_max", null) + " \u00b0C";
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
        return date.toString();
	}

	/** getSunset method to return sunset time
	 *  @return the string sunset
	 */
	public String getSunset() throws JSONException, IOException {
		Date date = new Date(getSys().optInt("sunset") * 1000L);
        return date.toString();
	}

	/** getSkyCondition method to return Sky condition
	 *  @return the string sky condition
	 */
	public String getSkyCondition() throws JSONException, IOException {
		return getWeather().optString("main", null);
	}

	public static void main(String[] args) throws JSONException, IOException {
		LocalWeatherView test = new LocalWeatherView("mississauga");
		System.out.println("Date:\t" + test.getDate());
		System.out.println("City:\t" + test.getCityName());
		System.out.println("Country:\t" + test.getCountry());
		System.out.println("Temperature:\t" + test.getTemperature());
		System.out.println("Pressure:\t" + test.getPressure());
		System.out.println("Humidity:\t" + test.getHumidity());
		System.out.println("Sunrise:\t" + test.getSunrise());
		System.out.println("Sunset:\t\t" + test.getSunset());
		System.out.println("TempMax:\t" + test.getTempMax());
		System.out.println("TempMin:\t" + test.getTempMin());
		System.out.println("Wind Speed:\t" + test.getWindSpeed());
		System.out.println("Wind Direction:\t" + test.getWindDirection());
		System.out.println("Sky Condition:\t" + test.getSkyCondition());
	}

}
