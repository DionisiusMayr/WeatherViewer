package ca.uwo.csd.cs2212.team4;

import org.json.JSONException;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class GUIApp {
	public static void main(String[]args) throws JSONException, IOException {
		
		//System.out.println(LocalWeatherView.getTemperature());
		//System.out.println(LocalWeatherView.getPressure());

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				GUICurrentWeather window = new GUICurrentWeather();
				window.setVisible(true);
			}
		});
	}

}
