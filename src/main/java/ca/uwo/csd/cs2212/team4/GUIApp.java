package ca.uwo.csd.cs2212.team4;
import org.json.JSONException;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 * This class contains the main method to run the GUI window of the weather viewer.
 * This is the class accessed to start the app.
 * 
 * @author Team 4
 */

public class GUIApp {
    static Preferences pref; // pref has package scope.

    /**
     * Loads preferences from local storage file and creates the GUI window.
     * 
     * @throws Exception if no preference file exists or data from website is invalid
     * */
	public static void main(String[]args) throws Exception {
        /* Loads the preferences from the file. */
        pref = new Preferences("", "", "metric");
        pref = pref.loadPreferences();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIFullWindow window = null;

				try {
					window = new GUIFullWindow();
				}
                catch (JSONException e) {
					e.printStackTrace();
				}
                catch (IOException e) {
					e.printStackTrace();
				}

				window.setVisible(true);
			}
		});
	}
}
