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
	private CustomLabel lblCity, lblCountry;
	private JTextField txtCity, txtCountry;
	private String strCity, strCountry;
	private ButtonGroup group;

    public String getUnitFromButton() {
    	Enumeration<AbstractButton> buttons = group.getElements();
		AbstractButton button = buttons.nextElement();

		if(button.getText().compareTo("Metric") == 0 && button.isSelected()) {
			buttons = group.getElements();
			return "metric";
        }
        else {
        	buttons = group.getElements();
        	return "imperial";
        }
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
		imperial.addActionListener(this);
		userPanel.add(imperial);
		
		JRadioButton metric = new JRadioButton("Metric");
		metric.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		metric.setBackground(new Color(174,242,239));
		metric.addActionListener(this);
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
				try {
                    if(GUIApp.pref.getCountry() != null) {
                        currentWeatherWindow.refresh(GUIApp.pref.getCity(), GUIApp.pref.getCountry());
                        shortForecastWindow.refresh(GUIApp.pref.getCity(), GUIApp.pref.getCountry());
                        longForecastWindow.refresh(GUIApp.pref.getCity(), GUIApp.pref.getCountry());
                    }
                    else {
                        currentWeatherWindow.refresh(GUIApp.pref.getCity());
                        shortForecastWindow.refresh(GUIApp.pref.getCity());
                        longForecastWindow.refresh(GUIApp.pref.getCity());
                    }
                }
                catch(Exception e) {
                    this.showErrorWindow("Nothing found on city/country. Please, insert something.");
                    txtCity.setText("");
                    txtCountry.setText("");
                }
			}
			else if(strCity.equals("")) {
				try {
                    if(GUIApp.pref.getCountry() != null) {
                        tester = new LocalWeatherView(GUIApp.pref.getCity(), GUIApp.pref.getCountry());
                        currentWeatherWindow.refresh(GUIApp.pref.getCity(), GUIApp.pref.getCountry());
                        shortForecastWindow.refresh(GUIApp.pref.getCity(), GUIApp.pref.getCountry());
                        longForecastWindow.refresh(GUIApp.pref.getCity(), GUIApp.pref.getCountry());
                        txtCity.setText(GUIApp.pref.getCity());
                        txtCountry.setText(GUIApp.pref.getCountry());
                    }
                    else {
                        tester = new LocalWeatherView(GUIApp.pref.getCity());
                        currentWeatherWindow.refresh(GUIApp.pref.getCity());
                        shortForecastWindow.refresh(GUIApp.pref.getCity());
                        longForecastWindow.refresh(GUIApp.pref.getCity());
                        txtCity.setText(GUIApp.pref.getCity());
                    }
                    this.showErrorWindow("Nothing found on city. Using the previous one.");
				}
				catch(Exception e) {
					this.showErrorWindow("Nothing found on city. Please, insert something.");
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
				try {
                    GUIApp.pref.setPreferences(strCity, strCountry, getUnitFromButton());  // Sets the preferences to this new city and country.
                    tester = new LocalWeatherView(strCity, strCountry);
					currentWeatherWindow.refresh(strCity, strCountry);
					shortForecastWindow.refresh(strCity, strCountry);
					longForecastWindow.refresh(strCity, strCountry);
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
                // TODO This is just for debugging purposes, we need to get rid of it soon
                this.showErrorWindow("Couldn't store the preferences.");
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
		else if(ae.getActionCommand().equals("Metric")){
			GUIApp.pref.setUnit("metric");
			try {
                GUIApp.pref.storePreferences();
            }
            catch(Exception e) {}
		}
		else if(ae.getActionCommand().equals("Imperial")){
			GUIApp.pref.setUnit("imperial");
			try {
                GUIApp.pref.storePreferences();
            }
            catch(Exception e) {}
		}
	}

    public void showErrorWindow(String errorMsg){
        JFrame errorFrame = new JFrame();
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setBounds(100, 100, 500, 150);
        JPanel errorPanel = new JPanel();
        errorPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        errorFrame.setContentPane(errorPanel);
        errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        errorPanel.setBackground(Color.WHITE);

        CustomLabel errorMessage = new CustomLabel(errorMsg);
        errorMessage.setFont(new Font("Gotham Light", Font.PLAIN, 16));
        errorMessage.setForeground(Color.BLACK);

        errorPanel.add(errorMessage);
        errorFrame.setVisible(true);
    }

	public void showErrorWindow(){
        showErrorWindow("Sorry, your entry was invalid! Nice try, though.");
	}
}
