/**Class for short-term weather forecast.
 *
 * TODO Which temperatures need to be displayed?

 "temp":
 {
     "day":        6.9,
     "min":        1.07,
     "max":        6.9,
     "night":      1.07,
     "eve":        6.9,
     "morn":       6.9
 },
 *
 * @author team4
 */

package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;

public class LongTerm {
	private String city;
	private String country;
	private WebInterface data;
	private JSONObject object;
	private JSONArray list;
	private JSONObject cityJSON;
	private JSONObject internObject;
	private JSONArray weatherArray;
	private JSONObject weather;

	public LongTerm(String cityName) throws JSONException, IOException {
		city = cityName;
		data = new WebInterface(city);
		object = data.createJSONObject(data.getLongTermURL());
        list = object.getJSONArray("list");
	}

	public LongTerm(String cityName, String countryCode) throws JSONException, IOException {
		city = cityName;
		country = countryCode;
		data = new WebInterface(city);
		object = data.createJSONObject(data.getLongTermURL());
        list = object.getJSONArray("list");
	}

    private JSONObject getCity() throws JSONException, IOException {
    	cityJSON = object.getJSONObject("city");
        return cityJSON;
    }

    private JSONObject getTemperature(int i) throws JSONException, IOException {
        JSONObject internObject = list.getJSONObject(i);
        JSONObject temperature = internObject.getJSONObject("temp");
        return temperature;
    }

    private JSONObject getWeather(int i) throws JSONException, IOException {
    	internObject = list.getJSONObject(i);
    	weatherArray = internObject.getJSONArray("weather");
    	weather = weatherArray.getJSONObject(0);
        return weather;
    }
    
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

    public String getTempDay(int i) throws  JSONException, IOException {
        return getTemperature(i).optString("day");
    }

    public String getTempMin(int i) throws  JSONException, IOException {
        return getTemperature(i).optString("min");
    }

    public String getTempMax(int i) throws  JSONException, IOException {
        return getTemperature(i).optString("max");
    }

    public String getTempNight(int i) throws  JSONException, IOException {
        return getTemperature(i).optString("night");
    }

    public String getTempEve(int i) throws  JSONException, IOException {
        return getTemperature(i).optString("eve");
    }

    public String getTempMorn(int i) throws  JSONException, IOException {
        return getTemperature(i).optString("morn");
    }

    public String getSkyCondition(int i) throws  JSONException, IOException {
        return getWeather(i).optString("main");
    }

    public String getIcon(int i) throws  JSONException, IOException {
        return getWeather(i).optString("icon");
    }

    /* TODO take out this main. */
    public static void main(String[] args) throws JSONException, IOException {
        
    	LongTerm test = new LongTerm("london", "ca");

        System.out.println("City name: \t\t" + test.getCityName());
        System.out.println("Country: \t\t" + test.getCountry());
        /* I think that when we get the array on the position 0 it is related to the actual day,
         * so maybe our loop would have to go from 1 to 6, instead of 0 to 5. */
        for(int i = 0; i < 5; i++) {
        	System.out.println(test.getDate(i));
            System.out.println("Temperature Min: \t\t" + test.getTempMin(i));
            System.out.println("Temperature Max: \t\t" + test.getTempMax(i));
            
            System.out.println("Temperature Morn: \t\t" + test.getTempMorn(i));
            System.out.println("Temperature Day: \t\t" + test.getTempDay(i));
            System.out.println("Temperature Eve: \t\t" + test.getTempEve(i));
            System.out.println("Temperature Night: \t\t" + test.getTempNight(i));
            
            System.out.println("Sky Condition: \t\t\t" + test.getSkyCondition(i));
            System.out.println("Icon: \t\t\t\t\t" + test.getIcon(i));
            System.out.println();
        }
    }
}

