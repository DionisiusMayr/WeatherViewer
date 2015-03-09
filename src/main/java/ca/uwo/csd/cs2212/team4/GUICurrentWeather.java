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
	
	public GUICurrentWeather(){
		this.initUI();
		local = new LocalWeatherView();
	}
	
	private void initUI(){
		
		//General window settings
		this.setTitle("Current Weather");
		this.setSize(500, 400);
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
		
		
	//Menu bar specs
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
	
	//Top panel specs
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
	
	//panel for location info and last time updated
	private JPanel createInfoPanel(){
		
		JPanel panel = new JPanel();
		JLabel lblLoc = new JLabel("LOCATION: London, Ontario ");
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
	
	//panel for current temperature and sky condition
	private JPanel createTempPanel(){
		
		JPanel panel = new JPanel();
		
		String tempString = "DEFAULT"; /**LOOK HERE*/
		try{
			tempString = local.getTemperature();
		}
		catch(JSONException e){
			System.out.println("JSON Exception");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
	
		JLabel lblTemp = new JLabel("Current temperature:"+tempString);
		
		JLabel lblCondition = new JLabel("Sky condition: Cloudy");
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblCondition);
		
		JPanel pnlCenter = new JPanel(); //placeholder for sky icon
		pnlCenter.setPreferredSize(new Dimension(25,25));
		pnlCenter.setBackground(Color.BLUE);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(lblTemp);
		
		
		
		panel.setLayout(new BorderLayout());
		panel.add(pnlNorth, BorderLayout.NORTH);
		panel.add(pnlCenter, BorderLayout.CENTER);
		panel.add(pnlSouth, BorderLayout.SOUTH);
		

		return panel;
	}
	
	//Bottom panel specs
	private JPanel createBottomPanel(){
		
		JPanel panel = new JPanel();
		JLabel lblPressure = new JLabel("Pressure: ");
		JLabel lblWindSpd = new JLabel("Wind speed: ");
		JLabel lblWindDir = new JLabel("Wind direction: ");
		JLabel lblSunrise = new JLabel("Sunrise time: ");
		JLabel lblSunset = new JLabel("Sunset time: ");
		
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
		);
		
		panel.setLayout(layout);
		return panel;
		
	}
}
