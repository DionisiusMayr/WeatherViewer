package ca.uwo.csd.cs2212.team4;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

import org.json.JSONException;

import java.io.IOException;

/**
 * GUILongTermWeather is a frame that shows the long term weather data (7 days). The information
 * is queried from OpenWeatherApp. It responds to user interactions with the GUI and interacts
 * with the Preferences class to generate appropriate data.
 * 
 * @author Team 4
 * 
 * */
public class GUILongTermWeather extends JFrame implements ActionListener {
	private JPanel contentPane;
	private String city,country;
	private LongTerm weather;
	private JLabel[] day,skyCondition,icon,date, max, min;

	/**
	 * Constructor to construct the frame.
	 */
	public GUILongTermWeather() {
		city = GUIApp.pref.getCity();
		country = GUIApp.pref.getCountry();

		setTitle("Long-Term Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setMinimumSize(new Dimension(650, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CustomLabel lbl1 = new CustomLabel("Day 1");
		lbl1.setBounds(55, 5, 80, 20);
		contentPane.add(lbl1);

		CustomLabel lbl2 = new CustomLabel("Day 2");
		lbl2.setBounds(155, 5, 80, 20);
		contentPane.add(lbl2);

		CustomLabel lbl3 = new CustomLabel("Day 3");
		lbl3.setBounds(255, 5, 80, 20);
		contentPane.add(lbl3);

		CustomLabel lbl4 = new CustomLabel("Day 4");
		lbl4.setBounds(355, 5, 80, 20);
		contentPane.add(lbl4);

		CustomLabel lbl5 = new CustomLabel("Day 5");
		lbl5.setBounds(105, 195, 80, 20);
		contentPane.add(lbl5);

		CustomLabel lbl6 = new CustomLabel("Day 6");
		lbl6.setBounds(205,195, 80, 20);
		contentPane.add(lbl6);

		CustomLabel lbl7 = new CustomLabel("Day 7");
		lbl7.setBounds(305, 195, 80, 20);
		contentPane.add(lbl7);

		CustomLabel tempday1 = new CustomLabel("day");
		tempday1.setBounds(50, 135, 80, 14);
		contentPane.add(tempday1);

		CustomLabel tempday2 = new CustomLabel("day");
		tempday2.setBounds(150, 135, 80, 14);
		contentPane.add(tempday2);

		CustomLabel tempday3 = new CustomLabel("day");
		tempday3.setBounds(250, 135, 80, 14);
		contentPane.add(tempday3);

		CustomLabel tempday4 = new CustomLabel("day");
		tempday4.setBounds(350, 135, 80, 14);
		contentPane.add(tempday4);

		CustomLabel tempday5 = new CustomLabel("day");
		tempday5.setBounds(100, 325, 80, 14);
		contentPane.add(tempday5);

		CustomLabel tempday6 = new CustomLabel("day");
		tempday6.setBounds(200, 325, 80, 14);
		contentPane.add(tempday6);

		CustomLabel tempday7 = new CustomLabel("day");
		tempday7.setBounds(300, 325, 80, 14);
		contentPane.add(tempday7);

		//skycondition
		CustomLabel skyCon1 = new CustomLabel("day");
		skyCon1.setBounds(50, 120, 80, 14);
		contentPane.add(skyCon1);
		skyCon1.setText("skyCon");

		CustomLabel skyCon2 = new CustomLabel("day");
		skyCon2.setBounds(150, 120, 80, 14);
		contentPane.add(skyCon2);
		skyCon2.setText("skyCon");

		CustomLabel skyCon3 = new CustomLabel("day");
		skyCon3.setBounds(250, 120, 80, 14);
		contentPane.add(skyCon3);
		skyCon3.setText("skyCon");

		CustomLabel skyCon4 = new CustomLabel("day");
		skyCon4.setBounds(350, 120, 80, 14);
		contentPane.add(skyCon4);
		skyCon4.setText("skyCon");

		CustomLabel skyCon5 = new CustomLabel("day");
		skyCon5.setBounds(100, 310, 80, 14);
		contentPane.add(skyCon5);
		skyCon5.setText("skyCon");

		CustomLabel skyCon6 = new CustomLabel("day");
		skyCon6.setBounds(200, 310, 80, 14);
		contentPane.add(skyCon6);
		skyCon6.setText("skyCon");

		CustomLabel skyCon7 = new CustomLabel("day");
		skyCon7.setBounds(300, 310, 80, 14);
		contentPane.add(skyCon7);
		skyCon7.setText("skyCon");

		//sky condition icons
		JLabel sImg1 = new JLabel(new ImageIcon("snow.png"));
		sImg1.setBounds(25,20,100,100);
		contentPane.add(sImg1);

		JLabel sImg2 = new JLabel(new ImageIcon("snow.png"));
		sImg2.setBounds(125,20,100,100);
		contentPane.add(sImg2);

		JLabel sImg3 = new JLabel(new ImageIcon("snow.png"));
		sImg3.setBounds(225,20,100,100);
		contentPane.add(sImg3);

		JLabel sImg4 = new JLabel(new ImageIcon("snow.png"));
		sImg4.setBounds(325,20,100,100);
		contentPane.add(sImg4);

		JLabel sImg5 = new JLabel(new ImageIcon("snow.png"));
		sImg5.setBounds(75,210,100,100);
		contentPane.add(sImg5);

		JLabel sImg6 = new JLabel(new ImageIcon("snow.png"));
		sImg6.setBounds(175,210,100,100);
		contentPane.add(sImg6);

		JLabel sImg7 = new JLabel(new ImageIcon("snow.png"));
		sImg7.setBounds(275,210,100,100);
		contentPane.add(sImg7);
		
		//Max temp
		CustomLabel maxday1 = new CustomLabel("max");
		maxday1.setBounds(40, 150, 90, 14);
		contentPane.add(maxday1);

		CustomLabel maxday2 = new CustomLabel("max");
		maxday2.setBounds(140, 150, 90, 14);
		contentPane.add(maxday2);

		CustomLabel maxday3 = new CustomLabel("max");
		maxday3.setBounds(240, 150, 90, 14);
		contentPane.add(maxday3);

		CustomLabel maxday4 = new CustomLabel("max");
		maxday4.setBounds(340, 150, 90, 14);
		contentPane.add(maxday4);

		CustomLabel maxday5 = new CustomLabel("max");
		maxday5.setBounds(90, 340, 90, 14);
		contentPane.add(maxday5);

		CustomLabel maxday6 = new CustomLabel("max");
		maxday6.setBounds(190, 340, 90, 14);
		contentPane.add(maxday6);

		CustomLabel maxday7 = new CustomLabel("max");
		maxday7.setBounds(290, 340, 90, 14);
		contentPane.add(maxday7);
		
		//Min temp
		CustomLabel minday1 = new CustomLabel("min");
		minday1.setBounds(40, 165, 90, 14);
		contentPane.add(minday1);

		CustomLabel minday2 = new CustomLabel("min");
		minday2.setBounds(140, 165, 90, 14);
		contentPane.add(minday2);

		CustomLabel minday3 = new CustomLabel("min");
		minday3.setBounds(240, 165, 90, 14);
		contentPane.add(minday3);

		CustomLabel minday4 = new CustomLabel("min");
		minday4.setBounds(340, 165, 90, 14);
		contentPane.add(minday4);

		CustomLabel minday5 = new CustomLabel("min");
		minday5.setBounds(90, 355, 90, 14);
		contentPane.add(minday5);

		CustomLabel minday6 = new CustomLabel("min");
		minday6.setBounds(190, 355, 90, 14);
		contentPane.add(minday6);

		CustomLabel minday7 = new CustomLabel("min");
		minday7.setBounds(290, 355, 90, 14);
		contentPane.add(minday7);
		
		day = new CustomLabel[7];
		day[0] = tempday1;
		day[1] = tempday2;
		day[2] = tempday3;
		day[3] = tempday4;
		day[4] = tempday5;
		day[5] = tempday6;
		day[6] = tempday7;

		skyCondition = new CustomLabel[7];
		skyCondition[0] = skyCon1;
		skyCondition[1] = skyCon2;
		skyCondition[2] = skyCon3;
		skyCondition[3] = skyCon4;
		skyCondition[4] = skyCon5;
		skyCondition[5] = skyCon6;
		skyCondition[6] = skyCon7;

		icon = new JLabel[7];
		icon[0] = sImg1;
		icon[1] = sImg2;
		icon[2] = sImg3;
		icon[3] = sImg4;
		icon[4] = sImg5;
		icon[5] = sImg6;
		icon[6] = sImg7;
	
		date = new CustomLabel[7];
		date[0] = lbl1;
		date[1] = lbl2;
		date[2] = lbl3;
		date[3] = lbl4;
		date[4] = lbl5;
		date[5] = lbl6;
		date[6] = lbl7;
		
		max = new CustomLabel[7];
		max[0] = maxday1;
		max[1] = maxday2;
		max[2] = maxday3;
		max[3] = maxday4;
		max[4] = maxday5;
		max[5] = maxday6;
		max[6] = maxday7;
		
		min = new CustomLabel[7];
		min[0] = minday1;
		min[1] = minday2;
		min[2] = minday3;
		min[3] = minday4;
		min[4] = minday5;
		min[5] = minday6;
		min[6] = minday7;
		
		try {
            if(country.equals(""))
		        refresh(city);
            else
                refresh(city, country);
		}
		catch(Exception e) {
			System.out.println("GUILongTermWeather(): IO exception, Long term refresh method");
		}
	}
	
	/**
	 * Calls the refresh method with the new city the user has inputted. This occurs in response
	 * to the refresh button being pressed.
	 * 
	 * @param ae Action performed by user via interaction with the GUI.
	 * 
	 * */
	public void actionPerformed(ActionEvent ae) {
		refresh(city);
	}

	/**Refreshes the data, given that the user has entered a value in the city text box.
	 * Open Weather Map is queried with the text for the new city.
	 * 
	 * @param city Text for new query
	 * */
	public void refresh(String city){
		try {
            weather = new LongTerm(city);

            for(int i = 0; i < 7; i++) {
            	date[i].setText(dateParse(weather.getDate(i)));
            	day[i].setText(weather.getTempDay(i));
                skyCondition[i].setText(weather.getSkyCondition(i));
                icon[i].setIcon(new ImageIcon(getClass().getResource("/images/" + weather.getSkyCondition(i).toLowerCase() + ".png")));
                max[i].setText("High:" + weather.getTempMax(i));
                min[i].setText("Low:" + weather.getTempMin(i));
            }
		}
        catch(Exception e) {
			System.out.println("refresh(String city): IO exception, Long term refresh method");
		}
	}

	/**Refreshes the data, given that the user has entered a value in both the country city
	 * text boxes. Open Weather Map is queried with the text for the new city and country.
	 * 
	 * @param city city text for new query
	 * @param country country text for new query
	 * @throws org.json.JSONException if new query fails
	 * @throws java.io.IOException
	 * */
	public void refresh(String city, String country) throws IOException, JSONException {
		weather = new LongTerm(city, country);

		for(int i=0;i<7;i++) {
			date[i].setText(dateParse(weather.getDate(i+1)));
			day[i].setText(weather.getTempDay(i));
			skyCondition[i].setText(weather.getSkyCondition(i));
			icon[i].setIcon(new ImageIcon(getClass().getResource("/images/" + weather.getSkyCondition(i).toLowerCase() + ".png")));
            max[i].setText("High: "+ weather.getTempMax(i));
            min[i].setText("Low: "+ weather.getTempMin(i));
		}
	}

	/**
	 * Parses the date to give the day of the week. Used to display correct labels for each
	 * day of the long term forecast.
	 * 
	 * @param date raw format of the date value.
	 * @return day of the week corresponding to input date value.
	 * */
	private String dateParse(String date) {
		StringTokenizer st = new StringTokenizer(date);
		String day = st.nextToken();
		st.nextToken();
		st.nextToken();

		return (day);
	}
	
	/**
	 * returns the panel that is in the local frame, which can be used as part of the larger frame 
	 * {@link ca.uwo.csd.cs2212.team4.GUIFullWindow}.
	 * 
	 * @return content panel of current frame
	 */
	public JPanel getPanel(){
		return contentPane;
	}
}