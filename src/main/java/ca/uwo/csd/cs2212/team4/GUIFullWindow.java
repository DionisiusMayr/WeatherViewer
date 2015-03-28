package ca.uwo.csd.cs2212.team4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.io.IOException;

import org.json.JSONException;

public class GUIFullWindow extends JFrame implements ActionListener {
	private JPanel contentPane;
	private GUICurrentWeather currentWeatherWindow;
	private GUIShortTermWeather shortForecastWindow;
	private GUILongTermWeather longForecastWindow;
	private CustomLabel lblCity;
	private JTextField txtCity;
	private CustomLabel lblCountry;
	private JTextField txtCountry;
	private String strCity, strCountry;
	private ButtonGroup group;

    
    public String getUnitFromButton() {
    	Enumeration<AbstractButton> buttons = group.getElements();
		AbstractButton button = buttons.nextElement();
		if(button.getText().compareTo("Metric")==0 && button.isSelected()){
			buttons = group.getElements();
			return "metric";
        }else{
        	buttons = group.getElements();
        	return "imperial";
			
        }
        
    }

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFullWindow frame = new GUIFullWindow();
					frame.setVisible(true);
				}
                catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws java.io.IOException
	 * @throws org.json.JSONException
	 */
	public GUIFullWindow() throws JSONException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(174,242,239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JPanel userPanel = new JPanel();
		lblCity = new CustomLabel("City: ");
		lblCity.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		txtCity = new JTextField(GUIApp.pref.getCity());
		txtCity.setPreferredSize(new Dimension(100,25));
		userPanel.add(lblCity);
		userPanel.add(txtCity);
		
		lblCountry = new CustomLabel("Country: ");
		lblCountry.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		txtCountry = new JTextField(GUIApp.pref.getCountry());
		txtCountry.setPreferredSize(new Dimension(100,25));
		userPanel.add(lblCountry);
		userPanel.add(txtCountry);

		JButton btnRefresh = new JButton("Refresh");
		this.getRootPane().setDefaultButton(btnRefresh);
		btnRefresh.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		btnRefresh.addActionListener(this);
		userPanel.add(btnRefresh);
		
		JRadioButton imperial = new JRadioButton("Imperial");
		imperial.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		imperial.setBackground(new Color(174,242,239));
		userPanel.add(imperial);
		
		JRadioButton metric = new JRadioButton("Metric");
		metric.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		metric.setBackground(new Color(174,242,239));
		userPanel.add(metric);
		
		group = new ButtonGroup();
		group.add(metric);
		group.add(imperial);
		
		if(GUIApp.pref.getUnit().equals("metric"))
			metric.setSelected(true);
		else
			imperial.setSelected(true);
		
		JButton btnMars = new JButton("Take me to Mars!");
		btnMars.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		btnMars.addActionListener(this);
		userPanel.add(btnMars);
		
		userPanel.setOpaque(false);
		contentPane.add(userPanel, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(300);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);

		shortForecastWindow = new GUIShortTermWeather();
		longForecastWindow = new GUILongTermWeather();

		String strShortTermForecast = "Short Term Forecast";
		tabbedPane.addTab("Short Term Forecast", null, shortForecastWindow.getPanel(), null);
		tabbedPane.addTab("Long Term Forecast", null, longForecastWindow.getPanel(), null);

		currentWeatherWindow = new GUICurrentWeather();
		splitPane.setLeftComponent(currentWeatherWindow.getPanel());
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Refresh")) {
			LocalWeatherView tester = null;
			strCity = txtCity.getText().toLowerCase();
			strCountry = txtCountry.getText().toLowerCase();
			 
			if(strCity.equals("") && strCountry.equals("")){
				// TODO Leon -I think we should remove this code, should never do a blank refresh it causes null pointer
				// TODO I added the try/catch here and also the "" on the refresh of the currentWeatherWindow.
                // TODO Now if you don't type anything it still works and update the data.
				//System.out.println(strCity + strCountry);
				//try {
              //      currentWeatherWindow.refresh("");
              //      shortForecastWindow.refresh("");
              //      longForecastWindow.refresh("");
              //  }
             //   catch(Exception e) {
                    this.showErrorWindow();
                    txtCity.setText("");
                    txtCountry.setText("");
              //  }
			}
			else if(strCity.equals("")) {
				try{
					tester = new LocalWeatherView(strCountry);
					currentWeatherWindow.refresh(strCountry);
					shortForecastWindow.refresh(strCountry);
					longForecastWindow.refresh(strCountry);
				}
				catch(Exception e) {
					this.showErrorWindow();
					txtCity.setText("");
					txtCountry.setText("");
				}
			}
			else if(strCountry.equals("")) {
				try {
                    GUIApp.pref.setPreferences(strCity, "", getUnitFromButton());      // Sets the preferences to this new city and no country.
                    tester = new LocalWeatherView(strCity);
					currentWeatherWindow.refresh(strCity);
					shortForecastWindow.refresh(strCity);
					longForecastWindow.refresh(strCity);
				}
				catch(Exception e) {
					this.showErrorWindow();
					txtCity.setText("");
					txtCountry.setText("");
				}
			}
			else {
				try{
                    GUIApp.pref.setPreferences(strCity, strCountry, getUnitFromButton());  // Sets the preferences to this new city and country.
                    tester = new LocalWeatherView(strCity, strCountry);
					currentWeatherWindow.refresh(strCity, strCountry);
					shortForecastWindow.refresh(strCity,strCountry);
					longForecastWindow.refresh(strCity,strCountry);
				}
				catch(Exception e) {
					this.showErrorWindow();
					txtCity.setText("");
					txtCountry.setText("");
				}
			}

            try {
                GUIApp.pref.storePreferences();
            }
            catch(Exception e) {
                this.showErrorWindow();
                txtCity.setText("");
                txtCountry.setText("");
            }
   
		}
		else if(ae.getActionCommand().equals("Take me to Mars!")){
			GUIMars mars = null;

			try {
				mars = new GUIMars();
			}
            catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mars.setVisible(true);
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
