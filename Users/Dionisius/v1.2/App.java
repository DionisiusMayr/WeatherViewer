/**
 * Created by dmayr on 07/03/15.
 */

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;   // To read.

public class App {
    public static void main(String [] args) throws IOException, JSONException {
        System.out.println("Start of execution.");

        Scanner in = new Scanner(System.in);

        System.out.print("Please insert the city name: ");
        String cityName = in.next();

        WebGetter webGetter = new WebGetter(cityName);

        //System.out.println(webGetter.httpGET(webGetter.buildURL()));
        System.out.println();

        JSONObject jsonObject = webGetter.createJsonObject(webGetter.getContentOfURL());
        JSONObject temperatureJson = jsonObject.optJSONObject("main");
        if(jsonObject == null)
            System.out.println("Json is null");
        else {
            System.out.println("JSON is (as string): " + jsonObject.toString());
        }

        urlRetriever justTrying = new urlRetriever();
        System.out.println(justTrying.getHTML(webGetter.buildURL()));

        System.out.println("Name: \t\t" + jsonObject.optString("name", null));
        System.out.println("Base: \t\t" + jsonObject.optString("base", null));
        System.out.println("Id: \t\t" + jsonObject.optString("id", null));
        System.out.println("Dt: \t\t" + jsonObject.optString("dt", null));
        System.out.println("TempMax: \t" + temperatureJson.optString("temp_max", null));
        System.out.println("TempMin: \t" + temperatureJson.optString("temp_min", null));
        System.out.println("Temp: \t\t" + temperatureJson.optString("temp", null));
        System.out.println("Pressure: \t" + temperatureJson.optString("pressure", null));
        System.out.println("Humidity: \t" + temperatureJson.optString("humidity", null));

        /* End of execution */
        System.out.println("End of execution.");
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