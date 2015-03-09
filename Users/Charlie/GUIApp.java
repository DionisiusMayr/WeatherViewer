import javax.swing.SwingUtilities;

public class GUIApp {
	public static void main(String[]args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				GUICurrentWeather window = new GUICurrentWeather();
				window.setVisible(true);
			}
		});
	}
	
}
