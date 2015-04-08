package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * This is the class responsible for getting the JSON objects from the web server.
 * @author team4
 */
public class WebInterface {
	private static final String LOCAL_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String SHORT_TERM_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
	private static final String LONG_TERM_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
	private static final String APPID = "&appId=f74197d83bc45827574fcf77670f8a63";
	private static final String MARS_URL = "http://marsweather.ingenology.com/v1/latest/?format=json";
	private String cityName;
	private String countryCode;

    /** Constructor to build the web interface from just a city
     * @param cityName Name of the city to be used on the query.
     */
	public WebInterface(String cityName) {
		this.cityName = cityName;
	}

    /** Constructor to build the web interface from a city and the respective country.
     * @param cityName Name of the city to be used on the query.
     * @param countryCode Name of the country of the city to be used on the query.
     */
	public WebInterface(String cityName, String countryCode) {
		this.cityName = cityName;
		this.countryCode = countryCode;
	}

	public WebInterface() {
	
	}

    /** Method to generate the URL for Current Weather data.
     * @return String the URL containing the Current Weather data.
     * @throws UnsupportedEncodingException if it fails to encode in UTF-8 the city and country
     */
	public String buildLocalWeatherURL() throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();

        url.append(LOCAL_WEATHER_URL);

        if(this.countryCode != null)
            url.append(URLEncoder.encode(this.cityName + "," + this.countryCode, "UTF-8"));
        else
            url.append(URLEncoder.encode(this.cityName, "UTF-8"));

        if(GUIApp.pref.getUnit().equals("metric"))
            url.append("&units=metric");
        else if(GUIApp.pref.getUnit().equals("imperial"))
            url.append("&units=imperial");
        else
            System.out.println("Invalid unit.");

        url.append(APPID);

        return url.toString();
	}

    /** Method to get the JSON object for the current weather in a String format
     * @return The JSON object in String format
     * @throws IOException
     */
	public String getLocalWeatherURL() throws IOException{
		return getJSON(buildLocalWeatherURL());
	}

    /** Method to generate the URL for Short Term Forecast data.
     * @return String the URL containing the Short Term data.
     * @throws UnsupportedEncodingException if it fails to encode in UTF-8 the city and country
     */
	public String buildShortTermURL() throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();

        url.append(SHORT_TERM_URL);

        if(this.countryCode != null)
            url.append(URLEncoder.encode(this.cityName + "," + this.countryCode, "UTF-8"));
        else
            url.append(URLEncoder.encode(this.cityName, "UTF-8"));

        if(GUIApp.pref.getUnit().equals("metric"))
            url.append("&units=metric");
        else if(GUIApp.pref.getUnit().equals("imperial"))
            url.append("&units=imperial");
        else
            System.out.println("Invalid unit.");

        url.append(APPID);

        return url.toString();
	}

    /** Method to get the JSON object for the short term forecast in a String format
     * @return The JSON object in String format
     * @throws IOException
     */
	public String getShortTermURL() throws IOException{
		return getJSON(buildShortTermURL());
	}

    /** Method to generate the URL for Long Term Forecast data.
     * @return String the URL containing the Long Term data.
     * @throws UnsupportedEncodingException if it fails to encode in UTF-8 the city and country
     */
    public String buildLongTermURL() throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();

        url.append(LONG_TERM_URL);

        if(this.countryCode != null)
            url.append(URLEncoder.encode(this.cityName + "," + this.countryCode, "UTF-8"));
        else
            url.append(URLEncoder.encode(this.cityName, "UTF-8"));

        url.append("&cnt=10").append("&mode=json").append("&units=");

        if(GUIApp.pref.getUnit().equals("metric"))
            url.append("metric");
        else if(GUIApp.pref.getUnit().equals("imperial"))
            url.append("imperial");
        else
            System.out.println("Invalid unit.");

        url.append(APPID);

        return url.toString();
	}

    /** Method to get the JSON object for the long term forecast in a String format
     * @return The JSON object in String format
     * @throws IOException
     */
	public String getLongTermURL() throws IOException{
		return getJSON(buildLongTermURL());
	}

    /** Method to generate the URL for mars' data.
     * @return String the URL containing the Mars data.
     * @throws UnsupportedEncodingException if it fails to encode in UTF-8 the city and country
     */
	public String buildMarsURL() throws UnsupportedEncodingException {
		return new StringBuilder().append(MARS_URL).toString();
	}

    /** Method to get the JSON object for the mars forecast in a String format
     * @return The JSON object in String format
     * @throws IOException
     */
	public String getMarsURL() throws IOException{
		return getJSON(buildMarsURL());
	}

    /** Method to translate a JSONObject in a String format to a JSONObject.
     * @param stringNotParsed JSONObject in a String format
     * @return the converted JSONObject
     * @throws JSONException if it fails to build the object (invalid string).
     */
	public JSONObject createJSONObject(String stringNotParsed) throws JSONException {
		JSONObject jsonObject;
		if(stringNotParsed != null)
			jsonObject = new JSONObject(stringNotParsed);
		else
			jsonObject = null;
		return jsonObject;
	}

    /** Method used to get the content of an URL.
     * @param urlToRead URL to be read and get the content
     * @return the content of the URL in a String format
     * @throws IOException
     */
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