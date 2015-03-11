import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by dmayr on 08/03/15.
 */
public class WebGetter {
    private String cityName;

    public WebGetter(String cityName) {
        this.cityName = cityName;
        //this.wgProxy = new WGProxy(null, Integer.MIN_VALUE, null, null);
    }

    public String buildURL() throws UnsupportedEncodingException {
        return new StringBuilder()
                .append("http://api.openweathermap.org/data/2.5/weather?q=")
                .append(URLEncoder.encode(this.cityName, "UTF-8"))
                .append("&mode=json&units=metric&lang=en&appId=08405bd2869953fd09bc556b3e7ff97d").toString();
    }

    public String getContentOfURL() throws UnsupportedEncodingException{
        return getHTML(buildURL());
    }

    public JSONObject createJsonObject(String stringNotParsed) throws JSONException {
        JSONObject jsonObject;
        if(stringNotParsed != null)
            jsonObject = new JSONObject(stringNotParsed);
        else
            jsonObject = null;

        return jsonObject;
    }

    /* TODO     again, it is a copied method (from that link), but it works and it is
     * TODO     simpler than the old code. */
    public String getHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
