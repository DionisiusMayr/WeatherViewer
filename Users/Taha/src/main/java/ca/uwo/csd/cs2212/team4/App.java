package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner; 

public class App {
	public static void main(String [] args) throws IOException, JSONException {
		//System.out.println("Start of execution.");

		Scanner in = new Scanner(System.in);

		System.out.print("Please insert the city name: ");
		String cityName = in.next();

		WebInterface data = new WebInterface(cityName);
		JSONObject object = data.createJsonObject(data.getContentOfURL());
		JSONObject sys = object.optJSONObject("sys");
		JSONObject main = object.optJSONObject("main");
		JSONObject wind = object.optJSONObject("wind");

		/* 
        System.out.println(webGetter.httpGET(webGetter.buildURL()));
        System.out.println();
        if(jsonObject == null)
            System.out.println("Json is null");
        else {
            System.out.println("JSON is (as string): " + jsonObject.toString());
        }
		 */

		System.out.println("City: \t\t\t" + object.optString("name", null));
		System.out.println("Temperature: \t\t" + main.optString("temp", null));
		System.out.println("Wind Speed: \t\t" + wind.optString("speed", null));
		System.out.println("Wind Direction: \t" + wind.optString("deg", null));
		
		System.out.println("Air Pressure: \t\t" + main.optString("pressure", null));
		System.out.println("Humidity: \t\t" + main.optString("humidity", null));
				
		System.out.println("Minimum temperature: \t" + main.optString("temp_min", null));
		System.out.println("Maximum temperature: \t" + main.optString("temp_max", null));
		
		System.out.println("Time of sunrise: \t" + sys.optString("sunrise", null));
		System.out.println("Time of sunset: \t" + sys.optString("sunset", null));

		/* End of execution 
		System.out.println("End of execution.");
		*/
	}
}

/* JSON:
Suppose we have a "JSON string" like this one on the object named "jsonObject":
{
"id":2643743,
"dt":1425800505,
"clouds":{"all":80},
"coord":{"lon":-0.13,"lat":51.51},
"wind":{"speed":4.6,"deg":240},
"cod":200,
"sys":{"message":0.0467,"id":5091,"sunset":1425837178,"sunrise":1425796168,"type":1,"country":"GB"},
"name":"London",
"base":"cmc stations",
"weather":[{"id":701,"icon":"50d","description":"mist","main":"Mist"}],
"main":{"humidity":81,"pressure":1022,"temp_max":8.33,"temp_min":6,"temp":7.12}
}
    There is a method called "optString("xxx", null)" that returns a string (I think) that matches the entry for "xxx".
So, for instance the call jsonObject.optString("id", null) would return "2643743".
But we can only use it for "atomic json pieces" (idk the correct and formal name, so I named it this way.),
so the call jsonObject.optString("clouds", null) won't work, because it doesn't match a "atomic json piece".
    Think about it as something similar to recursion. "There is a json object inside this json object".
Thus we can create another JSON object from it. To do so, we can use the method "optJSONObject("yyy", null)".
This method will return another JSON object for us, so
JSONObject temperatureJson = jsonObject.optJSONObject("main");
would be a JSON object, named "temperatureJson", containing the following "JSON string":
{"humidity":81,"pressure":1022,"temp_max":8.33,"temp_min":6,"temp":7.12}, which can be rewritten as:
{
"humidity":81,
"pressure":1022,
"temp_max":8.33,
"temp_min":6,
"temp":7.12
}
Now it is easy, everything here is a "atomic json piece", thus we can use the method optString("humidty", null) to
get the value "81" and so on.
Pretty simple.
 */
