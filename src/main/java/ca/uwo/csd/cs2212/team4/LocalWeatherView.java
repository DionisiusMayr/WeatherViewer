package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 * This class is for Local Weather View
 * @author team4
 */
public class LocalWeatherView {

	private String city;
	private String country;

	private WebInterface data;
	private JSONObject object;

	private static JSONObject main;
	private static JSONObject wind;
	private static JSONObject sys;

	private JSONArray weatherArray;
	private static JSONObject weather;

	public LocalWeatherView(String cityName) throws JSONException, IOException {
		city = cityName;
		data = new WebInterface(city);
		this.object = data.createJSONObject(data.getLocalWeatherURL());

		main = this.object.optJSONObject("main");
		wind = this.object.optJSONObject("wind");
		sys = this.object.optJSONObject("sys");

		weatherArray = object.optJSONArray("weather");
		weather = weatherArray.getJSONObject(0);
		
	}

	public LocalWeatherView(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		data = new WebInterface(city);
		this.object = data.createJSONObject(data.getLocalWeatherURL());

		main = this.object.optJSONObject("main");
		wind = this.object.optJSONObject("wind");
		sys = this.object.optJSONObject("sys");

		weatherArray = object.optJSONArray("weather");
		weather = weatherArray.getJSONObject(0);
	}

	/** 
	 * getTemperature method to return temperature
	 * @return string temperature
	 */
	public String getTemperature() throws JSONException, IOException {
		return main.optString("temp", null);
	}

	/** getPressure method to return pressure
	 *  @return strong pressure
	 */
	public String getPressure() throws JSONException, IOException {
		return main.optString("pressure", null);
	}

	/** getHumidity method to return humidity
	 *  @return humidity value
	 */
	public String getHumidity() throws JSONException, IOException {
		return main.optString("humidity", null);
	}

	/** getTempMin method to return min Temp
	 *  @return TempMin
	 */
	public String getTempMin() throws JSONException, IOException {
		return main.optString("temp_min", null);
	}

	/** getTempMax method to return Max temp
	 *  @return GetTempMax
	 */
	public String getTempMax() throws JSONException, IOException {
		return main.optString("temp_max", null);
	}

	/** getWindSpeed method to return wind speed
	 *  @return the value of windspeed
	 */
	public String getWindSpeed() throws JSONException, IOException {
		return wind.optString("speed", null);
	}

	/** getWindDirection method to return wind direction
	 *  @return the value of wind direction
	 */
	public String getWindDirection() throws JSONException, IOException {
		return wind.optString("deg", null);
	}

	/** getSunrise method to return sunrise time
	 *  @return the value of sunrise
	 */
	public String getSunrise() throws JSONException, IOException {
		return sys.optString("sunrise", null);
	}

	/** getSunset method to return sunset time
	 *  @return the string sunset
	 */
	public String getSunset() throws JSONException, IOException {
		return sys.optString("sunset", null);
	}

	/** getSkyCondition method to return Sky condition
	 *  @return the string sky condition
	 */
	public String getSkyCondition() throws JSONException, IOException {
		return weather.optString("main", null);
	}

	public static void main(String[] args) throws JSONException, IOException {
		LocalWeatherView test = new LocalWeatherView("mississauga");
		System.out.println(test.getTemperature());
		System.out.println(test.getPressure());
		System.out.println(test.getHumidity());
		System.out.println(test.getSunrise());
		System.out.println(test.getSunset());
		System.out.println(test.getTempMax());
		System.out.println(test.getTempMin());
		System.out.println(test.getWindDirection());
		System.out.println(test.getSkyCondition());
	}

}
