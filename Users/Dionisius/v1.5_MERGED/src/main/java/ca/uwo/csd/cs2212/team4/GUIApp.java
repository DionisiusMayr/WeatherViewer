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
    static Preferences pref; // pref has package scope.

	public static void main(String[]args) throws JSONException, IOException, Exception {
        /* Loads the preferences from the file. */
        pref = Preferences.loadPreferences();

		SwingUtilities.invokeLater(new Runnable(){

			public void run(){

				GUIFullWindow window = null;
				try {
					window = new GUIFullWindow();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				window.setVisible(true);
			}
		});

	}

}
