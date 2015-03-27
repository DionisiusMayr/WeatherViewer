package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class WebInterface {

	private static final String LOCAL_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?units=metric&q=";
	private static final String SHORT_TERM_URL = "http://api.openweathermap.org/data/2.5/forecast?units=metric&q=";
	private static final String LONG_TERM_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?units=metric&cnt=10&mode=json&q=";
	private static final String UNITS = "&units=metric";
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

	//////////////////////////////////////////////////////////////////////
	public String buildLocalWeatherURL() throws UnsupportedEncodingException {
		if (this.countryCode != null) {
			return new StringBuilder().append(LOCAL_WEATHER_URL)
					.append(URLEncoder.encode(this.cityName, "UTF-8")).append(",").append(countryCode)
					.append("&").append(APPID).toString();
		}

		return new StringBuilder().append(LOCAL_WEATHER_URL)
				.append(URLEncoder.encode(this.cityName, "UTF-8")).append("&")
				.append(APPID).toString();
	}

	public String getLocalWeatherURL() throws IOException{
		return getJSON(buildLocalWeatherURL());
	}
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	public String buildShortTermURL() throws UnsupportedEncodingException {
		if (this.countryCode != null) {
			return new StringBuilder().append(SHORT_TERM_URL)
					.append(URLEncoder.encode(this.cityName, "UTF-8")).append(",").append(countryCode)
					.append("&").append(APPID).toString();
		}

		return new StringBuilder().append(SHORT_TERM_URL)
				.append(URLEncoder.encode(this.cityName, "UTF-8")).append("&")
				.append(APPID).toString();
	}

	public String getShortTermURL() throws IOException{
		return getJSON(buildShortTermURL());
	}
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	public String buildLongTermURL() throws UnsupportedEncodingException {
		if (this.countryCode != null) {
			return new StringBuilder().append(SHORT_TERM_URL)
					.append(URLEncoder.encode(this.cityName, "UTF-8")).append(",").append(countryCode)
					.append("&").append(APPID).toString();
		}

		return new StringBuilder().append(LONG_TERM_URL)
				.append(URLEncoder.encode(this.cityName, "UTF-8")).append("&")
				.append(APPID).toString();
	}

	public String getLongTermURL() throws IOException{
		return getJSON(buildLongTermURL());
	}
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	public String buildMarsURL() throws UnsupportedEncodingException {
		return new StringBuilder().append(MARS_URL).toString();
	}

	public String getMarsURL() throws IOException{
		return getJSON(buildMarsURL());
	}
	//////////////////////////////////////////////////////////////////////

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