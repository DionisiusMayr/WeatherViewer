/**this class is for local weatherview
@author
 */

package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class LocalWeatherView {

	public LocalWeatherView() {

	}

	static WebInterface data = new WebInterface("london");
       
       /** getTemperature method to return temperature
 */
       
       
	public static String getTemperature() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp", null);
	}


       /** getPressure method to return pressure
 */
	public static String getPressure() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("pressure", null);
	}

	public static String getHumidity() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("humidity", null);
	}

	public static String getTempMin() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp_min", null);
	}

	public static String getTempMax() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp_max", null);
	}

	public static String getWindSpeed() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject wind = object.optJSONObject("wind");
		return wind.optString("speed", null);
	}

	public static String getWindDirection() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject wind = object.optJSONObject("wind");
		return wind.optString("deg", null);
	}

	public static String getSunrise() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject sys = object.optJSONObject("sys");
		return sys.optString("sunrise", null);
	}

	public static String getSunset() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject sys = object.optJSONObject("sys");
		return sys.optString("sunset", null);
	}

	public static String getSkyCondition() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONArray weatherArray = object.optJSONArray("weather");
		JSONObject weather = weatherArray.getJSONObject(0);
		return weather.optString("main", null);
	}

	/*
	 * public static void main(String[] args) throws JSONException, IOException {
			System.out.println(LocalWeatherView.getTemperature());
			System.out.println(LocalWeatherView.getPressure());
		}
	*/
}
