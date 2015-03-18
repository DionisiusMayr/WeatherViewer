package ca.uwo.csd.cs2212.team4;

/**Class for short-term weather forecast.
 * -> Still need to implement something to build the url, but i think it should be on the webgetter class.
 * -> I think it is also a good thing to put the getDate/getCity/getCountry method on the current weather class; but we might need to change some little details on these methods.
 * ->
 * @author team4
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;

public class ShortTerm {
    public ShortTerm() {
    }

    /** URL of the short-term forecast. Just for testing purposes.
     *
     * */
    private static final String URL = "http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139";

    static WebInterface data = new WebInterface("london");

    public static String getCityName() throws  JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONObject city = object.getJSONObject("city");
        return city.optString("name");
    }

    public static String getCountry() throws  JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONObject city = object.getJSONObject("city");
        return city.optString("country");
    }

    public static String getTemperature(int i) throws  JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        JSONObject main = internObject.getJSONObject("main");
        return main.optString("temp");
    }

    public static String getTempMin(int i) throws  JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        JSONObject main = internObject.getJSONObject("main");
        return main.optString("temp_min");
    }

    public static String getTempMax(int i) throws  JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        JSONObject main = internObject.getJSONObject("main");
        return main.optString("temp_max");
    }

    public static String getSkyCondition(int i) throws  JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        JSONArray weatherArray = internObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        return weatherObject.optString("main");
    }

		public static String getIcon(int i) throws  JSONException, IOException {
				JSONObject object = data.createJsonObject(data.getJSON(URL));
				JSONArray list = object.getJSONArray("list");
				JSONObject internObject = list.getJSONObject(i);
				JSONArray weatherArray = internObject.getJSONArray("weather");
				JSONObject weatherObject = weatherArray.getJSONObject(0);
				return weatherObject.optString("icon");
		}

    public static String getDate(int i) throws JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        Date date = new Date(internObject.getInt("dt") * 1000); // It has to be in miliseconds, thus the * 1000.
        return date.toString();
    }

    /* TODO take out this main. */
    public static void main(String[] args) throws JSONException, IOException {
        System.out.println("Starting execution.");

        JSONObject json = data.createJsonObject(data.getJSON("http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139"));

        System.out.println("City name: \t\t" + ShortTerm.getCityName());
        System.out.println("Country: \t\t" + ShortTerm.getCountry());
        for(int i = 0; i < 8; i++)
            System.out.println("Temperature " + i * 3 + ":\t\t" + ShortTerm.getTemperature(i) + "/\t\t" + ShortTerm.getTempMin(i) + "/\t" + ShortTerm.getTempMax(i));
        for(int i = 0; i < 8; i++)
            System.out.println("Sky cond/icon " + i * 3 + ":\t" + ShortTerm.getSkyCondition(i) + "/\t" + ShortTerm.getIcon(i));
        for(int i = 0; i < 8; i++)
          System.out.println(getDate(i));
        System.out.println("End of execution.");
    }
}
