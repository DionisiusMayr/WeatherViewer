/**This class contains the main method to run the GUI window of the weather viewer*/

package ca.uwo.csd.cs2212.team4;
import org.json.JSONException;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.swing.SwingUtilities;

public class GUIApp {
    static Preferences pref; // pref has package scope.

    public static Image getImage(final String pathAndFileName) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
	public static void main(String[]args) throws Exception {
        /* Loads the preferences from the file. */
        pref = Preferences.loadPreferences();

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
