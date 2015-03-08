import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dmayr on 08/03/15.
 */

/*USAGE:
*
*       urlRetriever justTrying = new urlRetriever();
        System.out.println(justTrying.getHTML(webGetter.buildURL()));
* */
public class urlRetriever {

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

    public void main(String urlString)
    {
        urlRetriever c = new urlRetriever();
        System.out.println(c.getHTML(urlString));
    }
}
