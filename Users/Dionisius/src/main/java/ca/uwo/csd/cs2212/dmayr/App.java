package ca.uwo.csd.cs2212.dmayr;

import java.util.Scanner;   // To read.

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Have no idea why, but add this:
import java.io.IOException;
import java.net.MalformedURLException;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

public class App {
    static Logger logger = LogManager.getLogger(App.class.getName());
    public static void main(String [] args) throws IOException, MalformedURLException, JSONException {
        Scanner in = new Scanner(System.in);

        System.out.print("Please insert the city name: ");
        String cityName = in.next();
        System.out.print("Please insert the country abbreviation (Like CA for Canada): ");
        String countryAbbreviation = in.next();

        OpenWeatherMap owm = new OpenWeatherMap("");

        CurrentWeather cwd = owm.currentWeatherByCityName(cityName, countryAbbreviation);

        if(cwd.isValid()) {
            if(cwd.hasCityName()) {
                System.out.println("City: " + cwd.getCityName());
            }

            if(cwd.getMainInstance().hasMaxTemperature() && cwd.getMainInstance().hasMinTemperature()) {
                System.out.println("Temperature: " + cwd.getMainInstance().getMaxTemperature() +
                        "/" + cwd.getMainInstance().getMinTemperature() + "\'F");
            }

            if(cwd.getMainInstance().hasHumidity()) {
                System.out.println("Humidity: " + cwd.getMainInstance().getHumidity() + " Humidity units");
            }

            if(cwd.getMainInstance().hasPressure()) {
                System.out.println("Pressure: " + cwd.getMainInstance().getPressure() + " Pressure units");
            }
        }

        /*logger.trace("Entering main");
        logger.warn("Hello Maven/log4j World");
        logger.info("My username is dmayr.");
        logger.trace("Exiting main"); */
    }
}