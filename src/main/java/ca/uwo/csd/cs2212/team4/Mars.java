package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * This is class responsible for getting the information about the Mars Forecast.
 * @author team4
 */
public class Mars {
	private WebInterface data;
	private JSONObject object;
	private JSONObject report;

    /** Constructor
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
	public Mars() throws JSONException, IOException {
		data = new WebInterface();
		object = data.createJSONObject(data.getMarsURL());
	}

    /* HELPER METHODS */

    /** Auxiliary method to get the report object JSONObject
     * @return the "report" JSON object
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     * */
    private JSONObject getReport() throws JSONException, IOException {
		report = object.optJSONObject("report");
		return report;
    }

    /* END OF HELPER METHODS */

    /* GETTERS */

    /** Method used to get the minimum temperature value in C.
     * @return String minimum temperature and the unit.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getMinTemp() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("min_temp");
	    return f.format(temp) + " \u00b0C";
	}

    /** Method used to get the minimum temperature value in F.
     * @return String minimum temperature and the unit.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getMinTempF() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("min_temp_fahrenheit");
	    return f.format(temp) + " \u00b0C";
	}

    /** Method used to get the maximum temperature value in C.
     * @return String maximum temperature and the unit.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getMaxTemp() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("max_temp");
	    return f.format(temp) + " \u00b0C";
	}

    /** Method used to get the maximum temperature value in F.
     * @return String maximum temperature and the unit.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getMaxTempF() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("max_temp_fahrenheit");
	    return f.format(temp) + " \u00b0C";
	}

    /** Method used to get the speed of the wind.
     * @return String speed of the wind.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getWindSpeed() throws JSONException, IOException {
		return getReport().optString("wind_speed");
	}

    /** Method used to get the direction of the wind.
     * @return String a letter representing the direction of the wind.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getWindDirection() throws JSONException, IOException {
		return getReport().optString("wind_direction");
	}

    /** Method used to get the pressure and the unit of it.
     * @return String pressure with the unit (hpa).
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getPressure() throws JSONException, IOException {
		return getReport().optString("pressure") + " hpa";
	}

    /** Method used to get the humidity value.
     * @return String humidity in percentage.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getHumidity() throws JSONException, IOException {
		return getReport().optString("abs_humidity");
	}

    /** Method used to get the sky condition.
     * @return String the sky condition, like Clear or Clouds.
     * @throws org.json.JSONException if it fails to build the JSON object
     * @throws java.io.IOException
     */
    public String getSkyCondition() throws JSONException, IOException {
		return getReport().optString("atmo_opacity");
	}

    /* END OF GETTERS */
}
