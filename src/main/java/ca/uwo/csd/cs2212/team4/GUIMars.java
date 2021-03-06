package ca.uwo.csd.cs2212.team4;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;

import org.json.JSONException;
/**
 * GUIMars is a JFrame that displays the weather information on Mars.
 * It includes labels for both the titles and contents of max/min temperature, humidity, windspeed, wind direction.
 * The data generated is from marsweather.ingenology.com.
 * This window is opened when the button "Take me to Mars!" is pressed on {@link ca.uwo.csd.cs2212.team4.GUIFullWindow}.
 * 
 * @author Team 4
 * */
public class GUIMars extends JFrame {
	private JPanel contentPane;
	private Date date;
	private DateFormat dateFormat;
	private String strMin, strMax, strPressure, strHumidity, strWdSpd, strWdDir, strLocation, strCondition;
	private CustomLabel lblLocation, lblMin, lblMax, lblPressure_1, lblHumidity_1, lblWdSpd_1, lblWddir_1, lblDate, lblCondition;
	private CustomLabel lblWdSpd_1_1;
	private Mars weather;
	private ImageIcon img;
	private JLabel lblIcon;

	/**
	 * Constructor to create the frame.
	 * 
	 * @throws java.io.IOException
	 * @throws org.json.JSONException
	 */
	public GUIMars() throws JSONException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(BorderFactory.createRaisedBevelBorder());
		setContentPane(contentPane);

		/**********************************************
		 * Initialize local weather object
		 * *********************************************/
		weather = new Mars();
	
        /***********************************************************************************************
         * initialize all strings and labels
         * ************************************************************************************************/
		
		strMin = strMax = strPressure = strHumidity = strWdSpd = strWdDir = strLocation = strCondition = "NA";
		try {
			strMin = weather.getMinTemp();
			strMax = weather.getMaxTemp();
			strPressure = weather.getPressure();
			strHumidity = weather.getHumidity();
			strWdSpd = weather.getWindSpeed();
			strWdDir = weather.getWindDirection();
			strCondition = weather.getSkyCondition();
			strLocation = "Mars";
		}
		catch(Exception e) {
			System.out.println("Error getting info");
		}
		
		CustomLabel lblPressure = new CustomLabel("Pressure");
		lblPressure.setForeground(Color.gray);
		CustomLabel lblHumidity = new CustomLabel("Humidity");	
		lblHumidity.setForeground(Color.gray);
		CustomLabel lblWdspd = new CustomLabel("Wind Speed");	
		lblWdspd.setForeground(Color.gray);
		CustomLabel lblWinddirection = new CustomLabel("Wind Direction");
		lblWinddirection.setForeground(Color.gray);

        lblMax = new CustomLabel("Max: " + strMax);
        lblMin = new CustomLabel("Min: " + strMin);
        lblCondition = new CustomLabel(strCondition.toUpperCase());
        lblCondition.setFont(new Font("Gotham Light", Font.PLAIN, 22));
        lblPressure_1 = new CustomLabel(strPressure);
        lblWdSpd_1 = new CustomLabel(strWdSpd);
        lblWddir_1 = new CustomLabel(strWdDir);
        lblHumidity_1 = new CustomLabel(strHumidity);
        lblWdSpd_1_1 = new CustomLabel(strWdSpd);

        /**********************************************************************************************
         * Top Panel contains: infoPanel, AllTempPanel
         * Bottom Panel contains: miscPanel
         * *****************************************************************************************/
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		
		JPanel BottomPanel = new JPanel();
		BottomPanel.setOpaque(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(BottomPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(topPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(topPanel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(BottomPanel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);

		
		JPanel AllTempPanel = new JPanel();
		AllTempPanel.setOpaque(false);

        /*******************************************************************************************
         * The bottom panel contains only the MiscWeatherPanel, which contains all misc. weather data like pressure, humidity, etc.
         * **********************************************************************************************************/
		JPanel MiscWeatherPanel = new JPanel();
		MiscWeatherPanel.setOpaque(false);

		GroupLayout gl_MiscWeatherPanel = new GroupLayout(MiscWeatherPanel);
		gl_MiscWeatherPanel.setHorizontalGroup(
			gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPressure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPressure_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(74))
						.addGroup(Alignment.LEADING, gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblHumidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHumidity_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWinddirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWddir_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdspd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdSpd_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_MiscWeatherPanel.setVerticalGroup(
			gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPressure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdspd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPressure_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdSpd_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHumidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWinddirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWddir_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHumidity_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		MiscWeatherPanel.setLayout(gl_MiscWeatherPanel);
		
		//add misc weather panel to bottom panel
		GroupLayout gl_BottomPanel = new GroupLayout(BottomPanel);
		gl_BottomPanel.setHorizontalGroup(
			gl_BottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BottomPanel.createSequentialGroup()
					.addGroup(gl_BottomPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_BottomPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(MiscWeatherPanel, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addComponent(AllTempPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_BottomPanel.setVerticalGroup(
			gl_BottomPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_BottomPanel.createSequentialGroup()
					.addGap(33)
					.addComponent(AllTempPanel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MiscWeatherPanel, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		BottomPanel.setLayout(gl_BottomPanel);
		
		JPanel OtherTempPanel = new JPanel();
		OtherTempPanel.setOpaque(false);

		GroupLayout gl_OtherTempPanel = new GroupLayout(OtherTempPanel);
		gl_OtherTempPanel.setHorizontalGroup(
			gl_OtherTempPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_OtherTempPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_OtherTempPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCondition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_OtherTempPanel.setVerticalGroup(
			gl_OtherTempPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_OtherTempPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblCondition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		OtherTempPanel.setLayout(gl_OtherTempPanel);
		GroupLayout gl_AllTempPanel = new GroupLayout(AllTempPanel);
		gl_AllTempPanel.setHorizontalGroup(
			gl_AllTempPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_AllTempPanel.createSequentialGroup()
					.addGap(76)
					.addComponent(OtherTempPanel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_AllTempPanel.setVerticalGroup(
			gl_AllTempPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_AllTempPanel.createSequentialGroup()
					.addComponent(OtherTempPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		AllTempPanel.setLayout(gl_AllTempPanel);
		topPanel.setLayout(new BorderLayout(0, 0));
		
        /***************************************************************************************
         * infoPanel: Panel containing User info like Location, time of last update, etc.
		 *******************************************************************************
		 ** */
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		topPanel.add(infoPanel, BorderLayout.NORTH);
		
		lblLocation = new CustomLabel(strLocation);
		lblLocation.setFont(new Font("Gotham Light", Font.PLAIN, 16));
		
		CustomLabel lblLastUpdate = new CustomLabel("Last Update:");

		//date and time specifications
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		lblDate = new CustomLabel(dateFormat.format(date));
		
		//add user information to infoPanel
		GroupLayout gl_infoPanel = new GroupLayout(infoPanel);
		gl_infoPanel.setHorizontalGroup(
			gl_infoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_infoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLastUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43))
		);
		gl_infoPanel.setVerticalGroup(
			gl_infoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoPanel.createSequentialGroup()
					.addComponent(lblLastUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		infoPanel.setLayout(gl_infoPanel);
		
		
        /***********************************************************************************************
        * Weather condition icon
        * *************************************************************************************************/
		
		img = null;

		try {
			img = new ImageIcon(getClass().getResource("/images/" + strCondition.toLowerCase() + ".png" ));
		}
		catch(Exception e) {
			System.out.println("Can't open image file");
		}
		lblIcon = new JLabel(img);
		topPanel.add(lblIcon, BorderLayout.CENTER);
	}
	
	/**
	 * returns the panel that is in the local frame.
	 * 
	 * @return content panel of current frame
	 */
	public JPanel getPanel() {
		return contentPane;
	}

    /**
     * Refreshes the data. 
     * It re-queries Mars Weather for the new data and updates the date to reflect
     * the local computer time when the refresh button is clicked.
    */
	public void refresh() {
		date = new Date();
		lblDate.setText(dateFormat.format(date));
		try {
		    if(GUIApp.pref.getUnit().equals("metric")) {
		    	strMin = weather.getMinTemp();
		    	strMax = weather.getMaxTemp();
		    }
			else {
				strMin = weather.getMinTempF();
		    	strMax = weather.getMaxTempF();
			}
			strPressure = weather.getPressure();
			strHumidity = weather.getHumidity();
			strWdSpd = weather.getWindSpeed();
			strWdDir = weather.getWindDirection();
			strCondition = weather.getSkyCondition();
            img = new ImageIcon(getClass().getResource("/images/" + strCondition.toLowerCase() + ".png" ));
		}
		catch(Exception e) {
			System.out.println("Error getting info");
		}

		lblMax.setText("Max: "+strMax);
		lblMin.setText("Min: "+strMin);
		lblCondition.setText(strCondition.toUpperCase());
		lblPressure_1.setText(strPressure);
		lblWdSpd_1_1.setText(strWdSpd);
		lblWddir_1.setText(strWdDir);
		lblHumidity_1.setText(strHumidity);
		lblIcon.setIcon(img);
		lblLocation.setText(strLocation);
	}

    /**
     * Refreshes the data. 
     * It re-queries Mars Weather for the new data and updates the date to reflect
     * the local computer time when the refresh button is clicked.
    */
	public void refresh(String cityOrCountry) throws JSONException, IOException{
		this.refresh();
	}
	
    /**
     * Refreshes the data. 
     * It re-queries Mars Weather for the new data and updates the date to reflect
     * the local computer time when the refresh button is clicked.
    */
	public void refresh(String city, String country) throws JSONException, IOException{
		this.refresh();
	}
}
