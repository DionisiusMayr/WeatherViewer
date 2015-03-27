package ca.uwo.csd.cs2212.team4;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import java.awt.*;


public class GUILongTermWeather extends JFrame{

	private JTable table;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUILongTermWeather window = new GUILongTermWeather();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	public GUILongTermWeather() {
		this.setTitle("Long-Term Weather View"); 
		this.setBounds(100, 100, 731, 454);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(650, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		
		table = new JTable();
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
 				//7days per a Week from Monday to Sunday
				{null, "MO", "TU", "WED", "TH", "FR", "SA", "SU"},
				//Need to have a getter method to get the weather conditions from website
				{"Weather Condition", null, null, null, null, null, null, null},
				//It is the picture that symbolizes each weather conditions
				{null, getpicture(), null, null, null, null, null, null},
				//Need to have a getter method to get the weather Temperature from website
				{"Temp", null, null, null, null, null, null, null},
				//Need to have a getter method to get the weather Maximum Temperature from website
				{"Max Temp", null, null, null, null, null, null, null},
				////Need to have a getter method to get the weather Minimum Temperature from website
				{"Min Temp", null, null, null, null, null, null, null},
				//Need to have a getter method to get the weather humidity from website
				{"Humidity", null, null, null, null, null, null, null},
				//Need to have a getter method to get the wind speed from website
				{"Wind Speed", null, null, null, null, null, null, null},
				//Need to have a getter method to get the wind direction from website
				{"Wind Direction", null, null, null, null, null, null, null},
				//Need to have a getter method to get the time of sun rise from website
				{"Sun Rise", null, null, null, null, null, null, null},
				//Need to have a getter method to get the time of sun set from website
				{"Sun Set", null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(128);
		table.setBounds(10, 103, 695, 278);
		contentPane.add(table);
		
		JTextPane txtpnCurrentLocationLondon = new JTextPane();
		txtpnCurrentLocationLondon.setEditable(false);
		txtpnCurrentLocationLondon.setText("Current Location: London, ON, Canada");
		txtpnCurrentLocationLondon.setBounds(27, 11, 230, 20);
		contentPane.add(txtpnCurrentLocationLondon);
		
		JTextPane txtpnLastTimeUpgrade = new JTextPane();
		txtpnLastTimeUpgrade.setText("Last Time Update: 9:31 am");
		txtpnLastTimeUpgrade.setBounds(315, 11, 284, 20);
		contentPane.add(txtpnLastTimeUpgrade);
		
	}

	private Object getpicture() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public JPanel getPanel(){
		return contentPane;
	}
}
