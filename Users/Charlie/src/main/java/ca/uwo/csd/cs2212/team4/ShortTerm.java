/**Class for short-term weather forecast.
 * -> Still need to implement something to build the url, but i think it should be on the webgetter class.
 * -> I think it is also a good thing to put the getDate/getCity/getCountry method on the current weather class; but we might need to change some little details on these methods.
 * ->
 * @author team4
 */

package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;

public class ShortTerm {
	
	private String city;
	private String country;
	private WebInterface data;
	private JSONObject object;
	private JSONArray list;
	private JSONObject cityJSON;
	private JSONObject internObject;
	private JSONObject main;
	private JSONArray weatherArray;
	private JSONObject weather;

	//constructor 1
	public ShortTerm(String cityName) throws JSONException, IOException {
		city = cityName;
		data = new WebInterface(city);
        object = data.createJSONObject(data.getShortTermURL());	
        list = object.getJSONArray("list");
	}

	//constructor 2
	public ShortTerm(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		this.data = new WebInterface(city);
        object = data.createJSONObject(data.getShortTermURL());	
        list = object.getJSONArray("list"); 
	}
	
	///////////////////////////HELPER METHODS///////////////////////
    private JSONObject getCity() throws JSONException, IOException {
        cityJSON = object.getJSONObject("city");
        return cityJSON;
    }
	
    private JSONObject getMain(int i) throws JSONException, IOException {
        internObject = list.getJSONObject(i);
        main = internObject.getJSONObject("main");
        return main;
    }

    private JSONObject getWeather(int i) throws JSONException, IOException {
        internObject = list.getJSONObject(i);
        weatherArray = internObject.getJSONArray("weather");
        weather = weatherArray.getJSONObject(0);
        return weather;
    }
    ///////////////////////////HELPER METHODS///////////////////////
    
    //GETTERS
    public String getDate(int i) throws JSONException, IOException {
        internObject = list.getJSONObject(i);
        Date date = new Date(internObject.getInt("dt") * 1000L); // It has to be in miliseconds, thus the * 1000.
        return date.toString();
    }
    
    public String getCityName() throws  JSONException, IOException {
        return getCity().optString("name");
    }

    public String getCountry() throws  JSONException, IOException {
        return getCity().optString("country");
    }

    public String getTemperature(int i) throws  JSONException, IOException {
        return getMain(i).optString("temp");
    }

    public String getTempMin(int i) throws  JSONException, IOException {
        return getMain(i).optString("temp_min");
    }

    public String getTempMax(int i) throws  JSONException, IOException {
        return getMain(i).optString("temp_max");
    }

    public String getSkyCondition(int i) throws  JSONException, IOException {
        return getWeather(i).optString("main");
    }

    public String getIcon(int i) throws  JSONException, IOException {
        return getWeather(i).optString("icon");
    }

    /* TODO take out this main. */
    public static void main(String[] args) throws JSONException, IOException {
		ShortTerm test = new ShortTerm("mississauga");

        System.out.println("City name: \t\t" + test.getCityName());
        System.out.println("Country: \t\t" + test.getCountry());
        
        for(int i = 0; i < 8; i++) {
            System.out.println(test.getDate(i));
            System.out.println("Temperature " + i * 3 + ":\t\t" + test.getTemperature(i) + "/\t\t" + test.getTempMin(i) + "/\t" + test.getTempMax(i));
            System.out.println("Sky cond/icon " + i * 3 + ":\t" + test.getSkyCondition(i) + "/\t" + test.getIcon(i));
    }
     
    }
}
