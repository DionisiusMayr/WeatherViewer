/**This class contains the GUI object for the weather viewer
*@author TEAM4
*/

package ca.uwo.csd.cs2212.team4;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class GUICurrentWeather extends JFrame{

	private LocalWeatherView local;
	
	/**
	 * Constructor to build the main window
	 * @throws IOException 
	 * @throws JSONException 
	*/
	public GUICurrentWeather() throws JSONException, IOException {
		this.initUI();
	}
	
	/**
	 * Build the main frame window
	 * @throws IOException 
	 * @throws JSONException 
	*/
	private void initUI() throws JSONException, IOException{
		local = new LocalWeatherView("mississauga");

		//General window settings
		this.setTitle("Current Weather");
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Menu bar
		this.setJMenuBar(this.createMenubar());
		
		//Main panel
		JPanel panel = new JPanel();
		JPanel topPanel = this.createTopPanel();
		JPanel bottomPanel = this.createBottomPanel();
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)	
						.addComponent(topPanel)
						.addComponent(bottomPanel)
				)


		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(topPanel)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(bottomPanel)
				)
		);
		
		panel.setLayout(layout);
		this.add(panel);
	}	
		
		
	/**
	 * Build the menu bar
	 * @return manu bar
	*/
	private JMenuBar createMenubar(){
		JMenuBar menubar = new JMenuBar();
		JMenu mnuTools = new JMenu("Tools");
		mnuTools.setMnemonic(KeyEvent.VK_T);
		
		JMenuItem mniToolsLoc = new JMenuItem ("Change Locations");
		mniToolsLoc.setMnemonic(KeyEvent.VK_L);
		mniToolsLoc.setToolTipText("Change Location");
		mniToolsLoc.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("New window appears"); //PLACEHOLDER
			}
			
		});
		mnuTools.add(mniToolsLoc);
		menubar.add(mnuTools);
		return menubar;
	}
	
	/**
	 * Build the top panel, which contains the tempPanel and infoPanel
	 * @return top panel JPanel
	*/
	private JPanel createTopPanel(){
		
		JPanel panel = new JPanel();
		JPanel infoPanel = this.createInfoPanel();
		JPanel tempPanel = this.createTempPanel();
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(tempPanel)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(infoPanel)
				)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(tempPanel)
						.addComponent(infoPanel)
				)
		);
		panel.setLayout(layout);
		
		return panel;
	}
	
	/**
	 * Create the information panel which contains location and time information
	 * @return information panel JPanel
	*/
	private JPanel createInfoPanel(){
		
		JPanel panel = new JPanel();
		JLabel lblLoc = new JLabel("Location: London");
		JLabel lblTime = new JLabel("Time of last update: 01/03/2015 17:30");
		
		GroupLayout layout = new GroupLayout(panel);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lblLoc)
						.addComponent(lblTime)
				)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lblLoc)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lblTime)
				)
		);
		
		panel.setLayout(layout);
		return panel;
	}
	
	/**
	 * Create the temperature panel
	 * @return temperature JPanel
	*/
	private JPanel createTempPanel(){
		
		JPanel panel = new JPanel();
		
		String tempString = "DEFAULT"; /**LOOK HERE*/
		String maxtempString = "DEFAULT";
		String mintempString = "DEFAULT";
		try{
			tempString = local.getTemperature();
			maxtempString = local.getTempMax();
			mintempString = local.getTempMin();
		}
		catch(JSONException e){
			System.out.println("JSON Exception");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
	
		JLabel lblTemp = new JLabel("Current temperature: " + tempString);
		JLabel lblMax = new JLabel("Max Temperature: " + maxtempString);
		JLabel lblMin = new JLabel("Min Temperature: " + mintempString);
				
		String conditionString = "DEFAULT"; /**LOOK HERE*/
		try{
			conditionString = local.getSkyCondition();
		}
		catch(JSONException e){
			System.out.println("JSON Exception");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
		
		JLabel lblCondition = new JLabel("Sky condition: "+conditionString);
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblCondition);
		
		JPanel pnlCenter = new JPanel(); //placeholder for sky icon
		pnlCenter.setPreferredSize(new Dimension(25,25));
		pnlCenter.setBackground(Color.BLUE);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(lblTemp);
		pnlSouth.add(lblMax);
		pnlSouth.add(lblMin);

		
		
		panel.setLayout(new BorderLayout());
		panel.add(pnlNorth, BorderLayout.NORTH);
		panel.add(pnlCenter, BorderLayout.CENTER);
		panel.add(pnlSouth, BorderLayout.SOUTH);
		

		return panel;
	}
	
	/**
	 * Create bottom panel, which contains other weather info
	 * @return bottom panel JPanel
	*/
	private JPanel createBottomPanel(){
		
		JPanel panel = new JPanel();		
		
		String pressureString = "DEFAULT"; /**LOOK HERE*/
		String windspdString = "DEFAULT";
		String winddirString = "DEFAULT";
		String sunriseString = "DEFAULT";
		String sunsetString = "DEFAULT";
		String humidityString = "DEFAULT";
		
		try{
			pressureString = local.getPressure();
			windspdString = local.getWindSpeed();
			winddirString = local.getWindDirection();
			sunriseString = local.getSunrise();
			sunsetString = local.getSunset();
			humidityString = local.getHumidity();
		}
		catch(JSONException e){
			System.out.println("JSON Exception");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
		JLabel lblPressure = new JLabel("Pressure: "+pressureString);
		JLabel lblWindSpd = new JLabel("Wind speed: " + windspdString);
		JLabel lblWindDir = new JLabel("Wind direction: "+winddirString);
		JLabel lblSunrise = new JLabel("Sunrise time: "+sunriseString);
		JLabel lblSunset = new JLabel("Sunset time: "+sunsetString);
		JLabel lblHumidity = new JLabel("Humidity: "+ humidityString);
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lblPressure)
						.addComponent(lblWindSpd)
						.addComponent(lblWindDir)
						.addComponent(lblSunrise)
						.addComponent(lblSunset)
						.addComponent(lblHumidity)
				)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblPressure)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblWindSpd)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblWindDir)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblSunrise)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblSunset)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblHumidity)
				)
		);
		
		panel.setLayout(layout);
		return panel;
		
	}
}
