/**This class contains the main method to run the GUI window of the weather viewer*/

package ca.uwo.csd.cs2212.team4;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class GUIApp {
	public static void main(String[]args) throws JSONException, IOException {
		
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run(){
				GUIFullWindow window = new GUIFullWindow();
				window.setVisible(true);
			}
		});
		
		
		
		
	}

}
