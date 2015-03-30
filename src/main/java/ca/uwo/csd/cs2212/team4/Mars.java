package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * This class is for Local Weather View
 * @author team4
 */
public class Mars {

	private WebInterface data;
	private JSONObject object;
	private JSONObject report;

	//constructor 
	public Mars() throws JSONException, IOException {
		data = new WebInterface();
		object = data.createJSONObject(data.getMarsURL());
	}

	///////////////////////////HELPER METHODS///////////////////////
    private JSONObject getReport() throws JSONException, IOException {
		report = object.optJSONObject("report");
		return report;
    }
    ///////////////////////////HELPER METHODS///////////////////////
    
    //GETTERS    
    public String getTerrestrialDate() throws JSONException, IOException {
		return getReport().optString("terrestrial_date");
	}
    
    public String getMinTemp() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("min_temp");
	    return f.format(temp) + " \u00b0C";
	}
    
    public String getMinTempF() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("min_temp_fahrenheit");
	    return f.format(temp) + " \u00b0C";
	}
    
    public String getMaxTemp() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("max_temp");
	    return f.format(temp) + " \u00b0C";
	}
    
    public String getMaxTempF() throws JSONException, IOException {
    	DecimalFormat f = new DecimalFormat("0");
    	double temp = getReport().optDouble("max_temp_fahrenheit");
	    return f.format(temp) + " \u00b0C";
	}
    
    public String getWindSpeed() throws JSONException, IOException {
		return getReport().optString("wind_speed");
	}
    
    public String getWindDirection() throws JSONException, IOException {
		return getReport().optString("wind_direction");
	}
    
    public String getPressure() throws JSONException, IOException {
		return getReport().optString("pressure") + " hpa";
	}
    
    public String getHumidity() throws JSONException, IOException {
		return getReport().optString("abs_humidity");
	}
    
    public String getSkyCondition() throws JSONException, IOException {
		return getReport().optString("atmo_opacity");
	}
}
