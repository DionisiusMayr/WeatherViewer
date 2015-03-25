/**This class contains the GUI object for the weather viewer
*@author TEAM4
*/

package ca.uwo.csd.cs2212.team4;

import javax.imageio.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;
import java.text.*;



public class GUIModel extends JFrame{

	private JPanel mainPanel;
	private JFrame mainFrame;
	
	/**
	 * Constructor to build the main window
	*/
	public GUIModel(){
		
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		
		//Main frame settings
		mainFrame.setTitle("Current Weather");
		mainFrame.setSize(400,700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		//Menu bar
		
		//Main panel properties
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new BorderLayout());
		
		//component panels
		JPanel infoPanel = createInfoPanel();
		JPanel iconPanel = createIconPanel();
		JPanel weatherPanel = createWeatherPanel();
		
		//add components to main panel
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		mainPanel.add(iconPanel, BorderLayout.CENTER);
		mainPanel.add(weatherPanel, BorderLayout.SOUTH);
		
		//add main panel to main frame
		mainFrame.add(mainPanel);
		
	}
	
	private JPanel createInfoPanel(){
		
		//info panel properties
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		
		//location label
		CustomLabel lblLoc = new CustomLabel("London     ");
		infoPanel.add(lblLoc);
		
		//date and time specifications
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		//date and time label		
		CustomLabel lblTime = new CustomLabel("Last update: "+ dateFormat.format(date));
		infoPanel.add(lblTime);
		
		//return infoPanel
		return infoPanel;
	}
	
	private JPanel createIconPanel(){
		
		//make JPanel
		JPanel iconPanel = new JPanel();
		iconPanel.setOpaque(false);
		
		//Image import
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File("Cloudy.png"));
		}
		catch(IOException e){
			System.out.println("Can't open image file");
		}
		
		//add image to panel
		JLabel picLabel = new JLabel(new ImageIcon(img));
		iconPanel.add(picLabel);
		
		//return iconPanel
		return iconPanel;
	}
	private JPanel createWeatherPanel(){
		
		//initialize all 3 panels
		JPanel weatherPanel = new JPanel();
		weatherPanel.setLayout(new BorderLayout());
		final JPanel tempPanel = new JPanel();
		JPanel miscPanel = new JPanel();
		
		//tempPanel
		JPanel leftTempPanel = new JPanel(){
			public Dimension getPreferredSize(){
				return tempPanel.getSize();
			};
		};		

		JLabel lbl1 = new JLabel ("1=one");
		JLabel lbl2 = new JLabel("2=two");
		JLabel lbl3 = new JLabel ("3=three");
		
		//leftTempPanel.add(lbl1);
		//tempPanel.add(leftTempPanel);
		tempPanel.add(lbl1);
		tempPanel.add(lbl2);
		
		
		
		//add temp and misc panels to weather panel
		weatherPanel.add(tempPanel, BorderLayout.NORTH);
		weatherPanel.add(miscPanel, BorderLayout.SOUTH);
		
		//return weatherPanel 
		return weatherPanel;
	}
	

}
