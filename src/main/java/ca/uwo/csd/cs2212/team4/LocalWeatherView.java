/**this class is for local weatherview
@author team4
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

      /** getHumidity method to return humidity
 */
	public static String getHumidity() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("humidity", null);
	}

 /** getTempMin method to return min Temp
 */


	public static String getTempMin() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp_min", null);
	}
 /** getTempMax method to return Max temp
 */
	public static String getTempMax() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp_max", null);
	}

 /** getWindSpeed method to return wind speed
 */

	public static String getWindSpeed() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject wind = object.optJSONObject("wind");
		return wind.optString("speed", null);
	}
 /** getWindDirection method to return wind direction
 */
	public static String getWindDirection() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject wind = object.optJSONObject("wind");
		return wind.optString("deg", null);
	}
 /** getSunrise method to return sunrise time
 */
	public static String getSunrise() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject sys = object.optJSONObject("sys");
		return sys.optString("sunrise", null);
	}
 /** getSunset method to return sunset time
 */
	public static String getSunset() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject sys = object.optJSONObject("sys");
		return sys.optString("sunset", null);
	}
 /** getSkyCondition method to return Sky condition
 */
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
