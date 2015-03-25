/**Class for short-term weather forecast.
 * Which temperatures need to be displayed?

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

public class LongTerm {
    public LongTerm() {
    }

    /** URL of the short-term forecast. Just for testing purposes.
     * */
    private static final String URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=London,ca&mode=json&units=metric&cnt=5";

    static WebInterface data = new WebInterface("london");

    private static JSONObject getCityJSONObject() throws JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONObject city = object.getJSONObject("city");
        return city;
    }

    private static JSONObject getTemperatureJSONObject(int i) throws JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
        JSONArray list = object.getJSONArray("list");
        JSONObject internObject = list.getJSONObject(i);
        JSONObject temperature = internObject.getJSONObject("temp");
        return temperature;
    }

    private static JSONObject getWeatherJSONObject(int i) throws JSONException, IOException {
        JSONObject object = data.createJsonObject(data.getJSON(URL));
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

    public static String getTempDay(int i) throws  JSONException, IOException {
        return getTemperatureJSONObject(i).optString("day");
    }

    public static String getTempMin(int i) throws  JSONException, IOException {
        return getTemperatureJSONObject(i).optString("min");
    }

    public static String getTempMax(int i) throws  JSONException, IOException {
        return getTemperatureJSONObject(i).optString("max");
    }

    public static String getTempNight(int i) throws  JSONException, IOException {
        return getTemperatureJSONObject(i).optString("night");
    }

    public static String getTempEve(int i) throws  JSONException, IOException {
        return getTemperatureJSONObject(i).optString("eve");
    }

    public static String getTempMorn(int i) throws  JSONException, IOException {
        return getTemperatureJSONObject(i).optString("morn");
    }

    public static String getSkyCondition(int i) throws  JSONException, IOException {
        return getWeatherJSONObject(i).optString("main");
    }

    public static String getIcon(int i) throws  JSONException, IOException {
        return getWeatherJSONObject(i).optString("icon");
    }

    /* TODO take out this main. */
    public static void main(String[] args) throws JSONException, IOException {
        System.out.println("Starting execution.");

        JSONObject json = data.createJsonObject(data.getJSON("http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139"));

        System.out.println("City name: \t\t" + LongTerm.getCityName());
        System.out.println("Country: \t\t" + LongTerm.getCountry());
        /* I think that when we get the array on the position 0 it is related to the actual day,
         * so maybe our loop would have to go from 1 to 6, instead of 0 to 5. */
        for(int i = 0; i < 5; i++) {
            System.out.println("[" + i + "] Temperature Day: \t\t" + LongTerm.getTempDay(i));
            System.out.println("[" + i + "] Temperature Min: \t\t" + LongTerm.getTempMin(i));
            System.out.println("[" + i + "] Temperature Max: \t\t" + LongTerm.getTempMax(i));
            System.out.println("[" + i + "] Temperature Night: \t\t" + LongTerm.getTempNight(i));
            System.out.println("[" + i + "] Temperature Eve: \t\t" + LongTerm.getTempEve(i));
            System.out.println("[" + i + "] Temperature Morn: \t\t" + LongTerm.getTempMorn(i));
            System.out.println("[" + i + "] Sky Condition: \t\t\t" + LongTerm.getSkyCondition(i));
            System.out.println("[" + i + "] Icon: \t\t\t\t\t" + LongTerm.getIcon(i));
            System.out.println();

        }
        System.out.println("End of execution.");
    }
}
