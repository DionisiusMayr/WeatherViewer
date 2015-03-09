package ca.uwo.csd.cs2212.team4;

import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner; 

import javax.swing.SwingUtilities;

import org.json.JSONObject;

public class GUIApp {
	public static void main(String[]args) throws JSONException, IOException {
		System.out.println(LocalWeatherView.getPressure());

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				GUICurrentWeather window = new GUICurrentWeather();
				window.setVisible(true);
			}
		});
	}
	
}
