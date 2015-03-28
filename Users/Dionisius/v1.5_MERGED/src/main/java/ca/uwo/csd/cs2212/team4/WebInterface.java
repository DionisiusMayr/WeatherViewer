package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class WebInterface {
	private static final String LOCAL_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?";
	private static final String SHORT_TERM_URL = "http://api.openweathermap.org/data/2.5/forecast?";
	private static final String LONG_TERM_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
	private static final String APPID = "appId=f74197d83bc45827574fcf77670f8a63";
	private static final String MARS_URL = "http://marsweather.ingenology.com/v1/latest/?format=json";

	private String cityName; 
	private String countryCode;

	public WebInterface(String cityName) {
		this.cityName = cityName;
	}

	public WebInterface(String cityName, String countryCode) {
		this.cityName = cityName;
		this.countryCode = countryCode;
	}

	public WebInterface() {
	
	}

    // TODO added the url building part of the units, i believe everything is right.
	public String buildLocalWeatherURL() throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();

        url.append(LOCAL_WEATHER_URL);
        if(GUIApp.pref.getUnit().equals("metric"))
            url.append("units=metric&q=");
        else if(GUIApp.pref.getUnit().equals("imperial"))
            url.append("units=imperial&q=");
        else
            System.out.println("Invalid unit.");

        url.append(URLEncoder.encode(this.cityName, "UTF-8"));

        if (this.countryCode != null) {
            url.append(",").append(countryCode).append("&").append(APPID);
		}
        else {
            url.append("&").append(APPID);
        }

        return url.toString();
	}

	public String getLocalWeatherURL() throws IOException{
		return getJSON(buildLocalWeatherURL());
	}

	public String buildShortTermURL() throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();

        url.append(SHORT_TERM_URL);

        if(GUIApp.pref.getUnit().equals("metric"))
            url.append("units=metric&q=");
        else if(GUIApp.pref.getUnit().equals("imperial"))
            url.append("units=imperial&q=");
        else
            System.out.println("Invalid unit.");

        url.append(URLEncoder.encode(this.cityName, "UTF-8"));
		if (this.countryCode != null) {
			url.append(countryCode).append("&").append(APPID);
		}
        else {
            url.append("&").append(APPID);
        }

        return url.toString();
	}

	public String getShortTermURL() throws IOException{
		return getJSON(buildShortTermURL());
	}

	// TODO i wasn't able to test this method, we might need to take a look at it.
    public String buildLongTermURL() throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();

        url.append(LONG_TERM_URL);

        if(GUIApp.pref.getUnit().equals("metric"))
            url.append("units=metric&");
        else if(GUIApp.pref.getUnit().equals("imperial"))
            url.append("units=imperial&");
        else
            System.out.println("Invalid unit.");

        url.append("cnt=10&mode=json&q=");

        if (this.countryCode != null) {
			url.append(",").append(countryCode).append("&").append(APPID);
		}
        else {
            url.append("&").append(APPID);
        }

        return url.toString();
	}

	public String getLongTermURL() throws IOException{
		return getJSON(buildLongTermURL());
	}

	public String buildMarsURL() throws UnsupportedEncodingException {
		return new StringBuilder().append(MARS_URL).toString();
	}

	public String getMarsURL() throws IOException{
		return getJSON(buildMarsURL());
	}

	public JSONObject createJSONObject(String stringNotParsed) throws JSONException {
		JSONObject jsonObject;
		if(stringNotParsed != null)
			jsonObject = new JSONObject(stringNotParsed);
		else
			jsonObject = null;
		return jsonObject;
	}

	public String getJSON(String urlToRead) throws IOException {
		URL url = new URL(urlToRead);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		BufferedReader in;
		String line;
		String result = "";

		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((line = in.readLine()) != null) {
			result += line;
		}
		in.close();
		return result;
	}
}