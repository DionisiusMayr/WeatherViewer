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

	public static void main(String[] args) throws JSONException, IOException {
		Mars test = new Mars();
		System.out.println("Terrestral Date:\t" + test.getTerrestrialDate());
		System.out.println("MinTemp:\t" + test.getMinTemp());
		System.out.println("MinTempF:\t" + test.getMinTempF());
		System.out.println("MaxTemp:\t" + test.getMaxTemp());
		System.out.println("MaxTempF:\t" + test.getMaxTempF());
		System.out.println("Wind Speed:\t" + test.getWindSpeed());
		System.out.println("Wind Direction:\t" + test.getWindDirection());
		System.out.println("Pressure:\t" + test.getPressure());
		System.out.println("Humidity:\t" + test.getHumidity());
		System.out.println("Sky Condition:\t" + test.getSkyCondition());

	}

}
