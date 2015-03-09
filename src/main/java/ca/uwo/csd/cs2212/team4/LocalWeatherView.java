package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class LocalWeatherView {

	static WebInterface data = new WebInterface("mississauga");
	
	public static String getTemperature() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp", null);
	}
	
	public String getPressure() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("pressure", null);
	}
	
	public String getHumidity() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("humidity", null);
	}
	
	public String getTempMin() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp_min", null);
	}
	
	public String getTempMax() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject main = object.optJSONObject("main");
		return main.optString("temp_max", null);
	}
	
	public String getWindSpeed() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject wind = object.optJSONObject("speed");
		return wind.optString("temp_max", null);
	}
	
	public String getWindDirection() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject wind = object.optJSONObject("deg");
		return wind.optString("temp_max", null);
	}
	
	public String getSunrise() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject sys = object.optJSONObject("sys");
		return sys.optString("sunrise", null);
	}
	
	public String getSunset() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject sys = object.optJSONObject("sys");
		return sys.optString("sunset", null);
	}
	
	public String getSkyCondition() throws JSONException, IOException {
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONArray weatherArray = object.optJSONArray("weather");
		JSONObject weather = weatherArray.getJSONObject(0);
		return weather.optString("main", null);
	}
	
}
