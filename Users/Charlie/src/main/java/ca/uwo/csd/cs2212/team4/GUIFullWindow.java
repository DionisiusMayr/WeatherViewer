package ca.uwo.csd.cs2212.team4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import org.json.JSONException;

public class GUIFullWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private GUICurrentWeather currentWeatherWindow;
	private CustomLabel lblCity;
	private JTextField txtCity;
	private CustomLabel lblCountry;
	private JTextField txtCountry;
	private String strCity, strCountry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFullWindow frame = new GUIFullWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public GUIFullWindow() throws JSONException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 600);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(13,191,182));
		contentPane.setBackground(new Color(174,242,239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblCity = new CustomLabel("City: ");
		lblCity.setFont(new Font("Gotham Light", Font.PLAIN, 20));
		txtCity = new JTextField("");
		txtCity.setPreferredSize(new Dimension(100,25));
		contentPane.add(lblCity);
		contentPane.add(txtCity);
		
		lblCountry = new CustomLabel("Country: ");
		lblCountry.setFont(new Font("Gotham Light", Font.PLAIN, 20));
		txtCountry = new JTextField("");
		txtCountry.setPreferredSize(new Dimension(100,25));
		contentPane.add(lblCountry);
		contentPane.add(txtCountry);
		
		JButton btnRefresh = new JButton("Refresh");
		this.getRootPane().setDefaultButton(btnRefresh);
		btnRefresh.setFont(new Font("Gotham Light", Font.PLAIN, 20));
		btnRefresh.addActionListener(this);
		contentPane.add(btnRefresh);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		GUIShortTermWeather shortForecastWindow = new GUIShortTermWeather("London Canada");
		GUILongTermWeather longForecastWindow = new GUILongTermWeather();
		
		tabbedPane.addTab("Short Term Forecast", null, shortForecastWindow.getPanel(), null);
		tabbedPane.addTab("Long Term Forecast", null, longForecastWindow.getPanel(), null);

		
		currentWeatherWindow = new GUICurrentWeather();
		splitPane.setLeftComponent(currentWeatherWindow.getPanel());
	}
	
	
	public void actionPerformed(ActionEvent ae){
		
		LocalWeatherView tester = null; 
		strCity = txtCity.getText().toLowerCase();
		strCountry = txtCountry.getText().toLowerCase();
		
		if(strCity.equals("") && strCountry.equals("")){
			currentWeatherWindow.refresh();
		}
		else if(strCity.equals("")){
			try{
				tester = new LocalWeatherView(strCountry);
				currentWeatherWindow.refresh(strCountry);
			}
			catch(Exception e){
				this.showErrorWindow();
				txtCity.setText("");
				txtCountry.setText("");
			}
		}
		else if(strCountry.equals("")){
			try{
				tester = new LocalWeatherView(strCity);
				currentWeatherWindow.refresh(strCity);
			}
			catch(Exception e){
				this.showErrorWindow();
				txtCity.setText("");
				txtCountry.setText("");
			}
		}
		else{
			try{
				tester = new LocalWeatherView(strCity, strCountry);
				currentWeatherWindow.refresh(strCity, strCountry);
			}
			catch(Exception e){
				this.showErrorWindow();
				txtCity.setText("");
				txtCountry.setText("");
			}
			
		}
		
		
	}
	
	public void showErrorWindow(){
		JFrame errorFrame = new JFrame();
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		errorFrame.setBounds(100, 100, 500, 150);
		JPanel errorPanel = new JPanel();
		errorPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		errorFrame.setContentPane(errorPanel);
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		errorPanel.setBackground(Color.WHITE);
		
		CustomLabel errorMessage = new CustomLabel("Sorry, your entry was invalid! Nice try, though.");
		errorMessage.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		errorMessage.setForeground(Color.BLACK);

		errorPanel.add(errorMessage);
		errorFrame.setVisible(true);
		
	}

}
