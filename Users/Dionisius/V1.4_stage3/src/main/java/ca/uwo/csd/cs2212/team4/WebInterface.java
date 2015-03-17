package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class WebInterface {

	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String APPID = "appId=f74197d83bc45827574fcf77670f8a63";
	private static final String UNITS = "units=metric";

	private String cityName;

	public WebInterface(String cityName) {
		this.cityName = cityName;
	}

	public String buildURL() throws UnsupportedEncodingException {
		return new StringBuilder()
		.append(URL).append(URLEncoder.encode(this.cityName, "UTF-8")).append("&")
		.append(UNITS).append("&").append(APPID).toString();
	}

	public String getContentOfURL() throws IOException{
		return getJSON(buildURL());
	}

	public JSONObject createJsonObject(String stringNotParsed) throws JSONException {
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
