package ca.uwo.csd.cs2212.team4;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;

import org.json.JSONException;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.text.*;

public class GUICurrentWeather extends JFrame {
	private JPanel contentPane;
	private Date date;
	private DateFormat dateFormat;
	private String strTemp, strMin, strMax, strPressure, strSunrise, strSunset, strHumidity, strWdSpd, strWdDir, strLocation, strCondition;
	private CustomLabel lblLocation, lblTemp, lblMin, lblMax, lblPressure_1, lblSunrise_1, lblSunset_1, lblHumidity_1, lblWdSpd_1, lblWddir_1, lblDate, lblCondition;
	private LocalWeatherView weather;
	private BufferedImage img;
	private JLabel lblIcon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICurrentWeather frame = new GUICurrentWeather();
					frame.setVisible(true);
				} catch (Exception e) {
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
	public GUICurrentWeather() throws JSONException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(8,194,184));
		contentPane.setBackground(Color.WHITE);

		contentPane.setBorder(BorderFactory.createRaisedBevelBorder());
		setContentPane(contentPane);

		/***
		 * Initialize local weather object
		 * *********************************************/
        // TODO I changed this to instead setting just "london", getting the city from the preferences.
 		weather = new LocalWeatherView(GUIApp.pref.getCity());

/***********************************************************************************************
 * initialize all strings and labels
 * ************************************************************************************************/

		strTemp=strMin=strMax=strPressure=strSunrise=strSunset=strHumidity=strWdSpd=strWdDir=strLocation=strCondition="NA";
		try{
			strTemp = weather.getTemperature();
			strMin = weather.getTempMin();
			strMax = weather.getTempMax();
			strPressure = weather.getPressure();
			strSunrise = weather.getSunrise();
			strSunset = weather.getSunset();
			strHumidity = weather.getHumidity();
			strWdSpd = weather.getWindSpeed();
			strWdDir = weather.getWindDirection();
			strCondition = weather.getSkyCondition();
			strLocation = weather.getCityName();
		}
		catch(Exception e){
			System.out.println("Error getting info");
		}
		lblTemp = new CustomLabel(strTemp);
		lblTemp.setFont(new Font("Gotham Light", Font.PLAIN, 30));

		CustomLabel lblPressure = new CustomLabel("Pressure");
		lblPressure.setForeground(Color.gray);
		CustomLabel lblHumidity = new CustomLabel("Humidity");
		lblHumidity.setForeground(Color.gray);
		CustomLabel lblSunrise = new CustomLabel("Sunrise");
		lblSunrise.setForeground(Color.gray);
		CustomLabel lblSunset = new CustomLabel("Sunset");
		lblSunset.setForeground(Color.gray);
		CustomLabel lblWdspd = new CustomLabel("Windspeed");
		lblWdspd.setForeground(Color.gray);
		CustomLabel lblWinddirection = new CustomLabel("Winddirection");
		lblWinddirection.setForeground(Color.gray);


		//CustomLabel lblPrecipitation = new CustomLabel("Precipitation:");

		 lblMax = new CustomLabel("Max: "+strMax);
		 lblMin = new CustomLabel("Min: "+strMin);
		lblCondition = new CustomLabel(strCondition.toUpperCase());
		lblCondition.setFont(new Font("Gotham Light", Font.PLAIN, 22));
		 lblPressure_1 = new CustomLabel(strPressure);
		 lblSunrise_1 = new CustomLabel(strSunrise);
		 lblWdSpd_1 = new CustomLabel(strWdSpd);
		 lblWddir_1 = new CustomLabel(strWdDir);
		 lblSunset_1 = new CustomLabel(strSunset);
		 lblHumidity_1 = new CustomLabel(strHumidity);
		 lblWdSpd_1 = new CustomLabel(strWdSpd);

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
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPressure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPressure_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSunrise_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSunrise, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHumidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHumidity_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSunset_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSunset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWinddirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWddir_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdspd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdSpd_1))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_MiscWeatherPanel.setVerticalGroup(
			gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPressure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSunrise, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdspd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPressure_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSunrise_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWdSpd_1))
					.addGap(18)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHumidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSunset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWinddirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSunset_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWddir_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHumidity_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(80, Short.MAX_VALUE))
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

/**************************************************************************************************************
 * AllTempPanel includes: CurrentTempPanel (current temperature) and OtherTempPanel (min, max and sky condition)
 * ************************************************************************************************************
 */
		JPanel CurrentTempPanel = new JPanel();
		CurrentTempPanel.setOpaque(false);

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
				.addGroup(gl_AllTempPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(CurrentTempPanel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(OtherTempPanel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_AllTempPanel.setVerticalGroup(
			gl_AllTempPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_AllTempPanel.createSequentialGroup()
					.addGroup(gl_AllTempPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(OtherTempPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CurrentTempPanel, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
					.addContainerGap())
		);


		CurrentTempPanel.add(lblTemp);



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
		try{
			img = ImageIO.read(new File(strCondition.toLowerCase() +".png"));
		}
		catch(IOException e){
			System.out.println("Can't open image file");
		}
		lblIcon = new JLabel(new ImageIcon(img));
		topPanel.add(lblIcon, BorderLayout.CENTER);

	}

	public JPanel getPanel(){
		return contentPane;
	}

/*****************************REFRESH**************************/

	public void refresh(){
		date = new Date();
		lblDate.setText(dateFormat.format(date));
		try{

			strTemp = weather.getTemperature();
			strMin = weather.getTempMin();
			strMax = weather.getTempMax();
			strPressure = weather.getPressure();
			strSunrise = weather.getSunrise();
			strSunset = weather.getSunset();
			strHumidity = weather.getHumidity();
			strWdSpd = weather.getWindSpeed();
			strWdDir = weather.getWindDirection();
			strCondition = weather.getSkyCondition();
			img = ImageIO.read(new File(strCondition.toLowerCase() +".png"));
			strLocation =  weather.getCityName();
		}
		catch(Exception e){
			System.out.println("Error getting info");
		}
		lblTemp.setText(strTemp);
		lblMax.setText("Max: "+strMax);
		lblMin.setText("Min: "+strMin);
		lblCondition.setText(strCondition.toUpperCase());
		lblPressure_1.setText(strPressure);
		lblSunrise_1.setText(strSunrise);
		lblWdSpd_1.setText(strWdSpd);
		lblWddir_1.setText(strWdDir);
		lblSunset_1.setText(strSunset);
		lblHumidity_1.setText(strHumidity);
		lblIcon.setIcon(new ImageIcon(img));
		lblLocation.setText(strLocation);
	}

	public void refresh(String cityOrCountry) throws JSONException, IOException{
		weather = new LocalWeatherView(cityOrCountry);
		this.refresh();
	}

	public void refresh(String city, String country) throws JSONException, IOException{
		weather = new LocalWeatherView(city, country);
		this.refresh();
	}
}
