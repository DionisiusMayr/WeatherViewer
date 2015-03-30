package ca.uwo.csd.cs2212.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
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
		data = new WebInterface(city,country);
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
    	DecimalFormat f = new DecimalFormat("0");
	    double temp = getTemperature(i).optDouble("day");
    	if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
    }

    public String getTempMin(int i) throws  JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
	    double temp = getTemperature(i).optDouble("min");
        if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
    }

    public String getTempMax(int i) throws  JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
	    double temp = getTemperature(i).optDouble("max");
    	if(GUIApp.pref.getUnit().equals("metric"))
	    	return f.format(temp) + " \u00b0C";
    	else
    		return f.format(temp) + " \u00b0F";
    }

    public String getTempNight(int i) throws  JSONException, IOException {
        if(GUIApp.pref.getUnit().equals("metric"))
        	return getTemperature(i).optString("night") + " \u00b0C";
    	else
    		return getTemperature(i).optString("night") + " \u00b0F";
    }

    public String getTempEve(int i) throws  JSONException, IOException {
        if(GUIApp.pref.getUnit().equals("metric"))
        	return getTemperature(i).optString("eve") + " \u00b0C";
    	else
    		return getTemperature(i).optString("eve") + " \u00b0F";
    }

    public String getTempMorn(int i) throws  JSONException, IOException {
        if(GUIApp.pref.getUnit().equals("metric"))
        	return getTemperature(i).optString("morn") + " \u00b0C";
    	else
    		return getTemperature(i).optString("morn") + " \u00b0F";
    }

    public String getSkyCondition(int i) throws  JSONException, IOException {
        return getWeather(i).optString("main");
    }

    public String getIcon(int i) throws  JSONException, IOException {
        return getWeather(i).optString("icon");
    }
}

