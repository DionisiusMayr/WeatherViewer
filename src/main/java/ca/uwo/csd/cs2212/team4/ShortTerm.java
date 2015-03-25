package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

/**
 * Class for short-term weather forecast.
 * @author team4
 */
public class ShortTerm {
	
	private String city;
	private String country;

	private WebInterface data;
	private JSONObject object;
	
	private static JSONObject main;
	private JSONArray weatherArray;
	private static  JSONObject weather;

	public ShortTerm(String cityName) throws JSONException, IOException {
		city = cityName;
		data = new WebInterface(city);
		this.object = data.createJSONObject(data.getShortTermURL());

		main = this.object.optJSONObject("main");
		weatherArray = object.optJSONArray("weather");
		weather = weatherArray.getJSONObject(0);
		
	}

	public ShortTerm(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		data = new WebInterface(city);
		this.object = data.createJSONObject(data.getShortTermURL());

		main = this.object.optJSONObject("main");
		weatherArray = object.optJSONArray("weather");
		weather = weatherArray.getJSONObject(0);
	}
	
	private static JSONObject getCityJSONObject() throws JSONException, IOException {
        JSONObject object = data.createJSONObject(data.getJSON(URL));
        JSONObject city = object.getJSONObject("city");
        return city;
    }

    private static JSONObject getMainJSONObject(int i) throws JSONException, IOException {
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        JSONObject main = internObject.getJSONObject("main");
        return main;
    }

    private static JSONObject getWeatherJSONObject(int i) throws JSONException, IOException {
        JSONObject object = data.createJSONObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        JSONArray weatherArray = internObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        return weatherObject;
    }

    public static String getCityName() throws  JSONException, IOException {
        return getCityJSONObject().optString("name");
    }

    public static String getCountry() throws  JSONException, IOException {
        return getCityJSONObject().optString("country");
    }

    public static String getTemperature(int i) throws  JSONException, IOException {
        return getMainJSONObject(i).optString("temp");
    }

    public static String getSkyCondition(int i) throws  JSONException, IOException {
        return getWeatherJSONObject(i).optString("main");
    }

    public static String getIcon(int i) throws  JSONException, IOException {
        return getWeatherJSONObject(i).optString("icon");
    }

    public static String getDate(int i) throws JSONException, IOException {
        JSONObject object = data.createJSONObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        Date date = new Date(internObject.getInt("dt") * 1000); // It has to be in miliseconds, thus the * 1000.
        return date.toString();
    }

    /* TODO take out this main. */
    public static void main(String[] args) throws JSONException, IOException {
        System.out.println("Starting execution.");

        JSONObject json = data.createJSONObject(data.getJSON("http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139"));

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
