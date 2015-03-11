import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Created by dmayr on 08/03/15.
 */
public class WebGetter {
    private String cityName;
    private WGProxy wgProxy;

    public WebGetter(String cityName) {
        this.cityName = cityName;
        this.wgProxy = new WGProxy(null, Integer.MIN_VALUE, null, null);
    }

    public String buildURL() throws UnsupportedEncodingException {
        return new StringBuilder()
                .append("http://api.openweathermap.org/data/2.5/weather?q=")
                .append(URLEncoder.encode(this.cityName, "UTF-8"))
                .append("&mode=json&units=metric&lang=en&appId=08405bd2869953fd09bc556b3e7ff97d").toString();
    }

    public String getContentOfURL() throws UnsupportedEncodingException{
        return httpGET(buildURL());
    }

    public JSONObject createJsonObject(String stringNotParsed) throws JSONException {
        JSONObject jsonObject;
        if(stringNotParsed != null)
            jsonObject = new JSONObject(stringNotParsed);
        else
            jsonObject = null;

        return jsonObject;
    }

    /*COPIED CLASS, TODO*/
    public static class WGProxy {
        private String ip;
        private int port;
        private String user;
        private String pass;

        public WGProxy(String ip, int port, String user, String pass) {
            this.ip = ip;
            this.port = port;
            this.user = user;
            this.pass = pass;
        }

        public Proxy getProxy() {
            Proxy proxy = null;

            if (ip != null && (! "".equals(ip)) && port != Integer.MIN_VALUE)
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));

            if (user != null && (! "".equals(user)) && pass != null && (! "".equals(pass)))
                Authenticator.setDefault(getAuthenticatorInstance(user, pass));

            return proxy;
        }

        private Authenticator getAuthenticatorInstance(final String user, final String pass) {
            Authenticator authenticator = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return (new PasswordAuthentication(user, pass.toCharArray()));
                }
            };
            return authenticator;
        }
    }

    /* TODO This method comes from that other guy, i just made some minor changes on it, need to see if we can use it.*/
    public String httpGET(String requestAddress) {
        URL request;
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        String tmpStr;
        String response = null;

        try {
            request = new URL(requestAddress);

            if (wgProxy.getProxy() != null)
                connection = (HttpURLConnection) request.openConnection(wgProxy.getProxy());
            else
                connection = (HttpURLConnection) request.openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String encoding = connection.getContentEncoding();
                try {
                    if (encoding != null && "gzip".equalsIgnoreCase(encoding))
                        reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
                    else if (encoding != null && "deflate".equalsIgnoreCase(encoding))
                        reader = new BufferedReader(new InputStreamReader(new InflaterInputStream(connection.getInputStream(), new Inflater(true))));
                    else
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while ((tmpStr = reader.readLine()) != null)
                        response = tmpStr;
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }
                }
            } else { // if HttpURLConnection is not okay
                try {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((tmpStr = reader.readLine()) != null)
                        response = tmpStr;
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }
                }

                // if response is bad
                System.err.println("Bad Response: " + response + "\n");
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            response = null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return response;
    }
}
