package ca.uwo.csd.cs2212.team4;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

import org.json.JSONException;

import java.io.IOException;

public class GUILongTermWeather extends JFrame implements ActionListener {
	private JPanel contentPane;
	private String city,country;
	private LongTerm weather;
	private Boolean nextDay, addHours;
	private JLabel[] day,skyCondition,icon,date, max, min;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUILongTermWeather frame = new GUILongTermWeather();
					frame.setVisible(true);
				}
                catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUILongTermWeather() {
		//city = GUIApp.pref.getCity();
		//country = GUIApp.pref.getCountry();
		nextDay=false;

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
		lbl1.setBounds(50, 20, 80, 20);
		contentPane.add(lbl1);

		CustomLabel lbl2 = new CustomLabel("Day 2");
		lbl2.setBounds(150, 20, 80, 20);
		contentPane.add(lbl2);

		CustomLabel lbl3 = new CustomLabel("Day 3");
		lbl3.setBounds(250, 20, 80, 20);
		contentPane.add(lbl3);

		CustomLabel lbl4 = new CustomLabel("Day 4");
		lbl4.setBounds(350, 20, 80, 20);
		contentPane.add(lbl4);

		CustomLabel lbl5 = new CustomLabel("Day 5");
		lbl5.setBounds(50, 240, 80, 20);
		contentPane.add(lbl5);

		CustomLabel lbl6 = new CustomLabel("Day 6");
		lbl6.setBounds(150,240, 80, 20);
		contentPane.add(lbl6);

		CustomLabel lbl7 = new CustomLabel("Day 7");
		lbl7.setBounds(250, 240, 80, 20);
		contentPane.add(lbl7);


		CustomLabel tempday1 = new CustomLabel("day");
		tempday1.setBounds(50, 160, 80, 14);
		contentPane.add(tempday1);
		tempday1.setText("day");

		CustomLabel tempday2 = new CustomLabel("day");
		tempday2.setBounds(150, 160, 80, 14);
		contentPane.add(tempday2);
		tempday2.setText("day");

		CustomLabel tempday3 = new CustomLabel("day");
		tempday3.setBounds(250, 160, 80, 14);
		contentPane.add(tempday3);
		tempday3.setText("day");

		CustomLabel tempday4 = new CustomLabel("day");
		tempday4.setBounds(350, 160, 80, 14);
		contentPane.add(tempday4);
		tempday4.setText("day");

		CustomLabel tempday5 = new CustomLabel("day");
		tempday5.setBounds(50, 375, 80, 14);
		contentPane.add(tempday5);
		tempday5.setText("day");

		CustomLabel tempday6 = new CustomLabel("day");
		tempday6.setBounds(150, 375, 80, 14);
		contentPane.add(tempday6);
		tempday6.setText("day");

		CustomLabel tempday7 = new CustomLabel("day");
		tempday7.setBounds(250, 375, 80, 14);
		contentPane.add(tempday7);
		tempday7.setText("day");

		//skycondition
		CustomLabel skyCon1 = new CustomLabel("day");
		skyCon1.setBounds(50, 145, 80, 14);
		contentPane.add(skyCon1);
		skyCon1.setText("skyCon");

		CustomLabel skyCon2 = new CustomLabel("day");
		skyCon2.setBounds(150, 145, 80, 14);
		contentPane.add(skyCon2);
		skyCon2.setText("skyCon");

		CustomLabel skyCon3 = new CustomLabel("day");
		skyCon3.setBounds(250, 145, 80, 14);
		contentPane.add(skyCon3);
		skyCon3.setText("skyCon");

		CustomLabel skyCon4 = new CustomLabel("day");
		skyCon4.setBounds(350, 145, 80, 14);
		contentPane.add(skyCon4);
		skyCon4.setText("skyCon");

		CustomLabel skyCon5 = new CustomLabel("day");
		skyCon5.setBounds(50, 360, 80, 14);
		contentPane.add(skyCon5);
		skyCon5.setText("skyCon");

		CustomLabel skyCon6 = new CustomLabel("day");
		skyCon6.setBounds(150, 360, 80, 14);
		contentPane.add(skyCon6);
		skyCon6.setText("skyCon");

		CustomLabel skyCon7 = new CustomLabel("day");
		skyCon7.setBounds(250, 360, 80, 14);
		contentPane.add(skyCon7);
		skyCon7.setText("skyCon");


		//sky condition icons
		JLabel sImg1 = new JLabel(new ImageIcon("snow.png"));
		sImg1.setBounds(25,40,100,100);
		contentPane.add(sImg1);

		JLabel sImg2 = new JLabel(new ImageIcon("snow.png"));
		sImg2.setBounds(125,40,100,100);
		contentPane.add(sImg2);

		JLabel sImg3 = new JLabel(new ImageIcon("snow.png"));
		sImg3.setBounds(225,40,100,100);
		contentPane.add(sImg3);

		JLabel sImg4 = new JLabel(new ImageIcon("snow.png"));
		sImg4.setBounds(325,40,100,100);
		contentPane.add(sImg4);

		JLabel sImg5 = new JLabel(new ImageIcon("snow.png"));
		sImg5.setBounds(25,255,100,100);
		contentPane.add(sImg5);

		JLabel sImg6 = new JLabel(new ImageIcon("snow.png"));
		sImg6.setBounds(125,255,100,100);
		contentPane.add(sImg6);

		JLabel sImg7 = new JLabel(new ImageIcon("snow.png"));
		sImg7.setBounds(225,255,100,100);
		contentPane.add(sImg7);

		
		//Max temp
		CustomLabel maxday1 = new CustomLabel("max");
		maxday1.setBounds(50, 175, 80, 14);
		contentPane.add(maxday1);
		maxday1.setText("max");

		CustomLabel maxday2 = new CustomLabel("max");
		maxday2.setBounds(150, 175, 80, 14);
		contentPane.add(maxday2);
		maxday2.setText("max");

		CustomLabel maxday3 = new CustomLabel("max");
		maxday3.setBounds(250, 175, 80, 14);
		contentPane.add(maxday3);
		maxday3.setText("max");

		CustomLabel maxday4 = new CustomLabel("max");
		maxday4.setBounds(350, 175, 80, 14);
		contentPane.add(maxday4);
		maxday4.setText("max");

		CustomLabel maxday5 = new CustomLabel("max");
		maxday5.setBounds(50, 390, 80, 14);
		contentPane.add(maxday5);
		maxday5.setText("max");

		CustomLabel maxday6 = new CustomLabel("max");
		maxday6.setBounds(150, 390, 80, 14);
		contentPane.add(maxday6);
		maxday6.setText("max");

		CustomLabel maxday7 = new CustomLabel("max");
		maxday7.setBounds(250, 390, 80, 14);
		contentPane.add(maxday7);
		maxday7.setText("max");
		
		
		//Min temp
		CustomLabel minday1 = new CustomLabel("min");
		minday1.setBounds(50, 190, 80, 14);
		contentPane.add(minday1);
		minday1.setText("min");

		CustomLabel minday2 = new CustomLabel("min");
		minday2.setBounds(150, 190, 80, 14);
		contentPane.add(minday2);
		minday2.setText("min");

		CustomLabel minday3 = new CustomLabel("min");
		minday3.setBounds(250, 190, 80, 14);
		contentPane.add(minday3);
		minday3.setText("min");

		CustomLabel minday4 = new CustomLabel("min");
		minday4.setBounds(350, 190, 80, 14);
		contentPane.add(minday4);
		minday4.setText("min");

		CustomLabel minday5 = new CustomLabel("min");
		minday5.setBounds(50, 405, 80, 14);
		contentPane.add(minday5);
		minday5.setText("min");

		CustomLabel minday6 = new CustomLabel("min");
		minday6.setBounds(150, 405, 80, 14);
		contentPane.add(minday6);
		minday6.setText("min");

		CustomLabel minday7 = new CustomLabel("min");
		minday7.setBounds(250, 405, 80, 14);
		contentPane.add(minday7);
		minday7.setText("min");
	
		
		CustomLabel daytemp = new CustomLabel("Day");
		daytemp.setBounds(2, 156, 80, 20);
		contentPane.add(daytemp);
		
		CustomLabel daymax = new CustomLabel("Max");
		daymax.setBounds(2, 171, 80, 20);
		contentPane.add(daymax);
		
		CustomLabel daymin = new CustomLabel("Min");
		daymin.setBounds(2, 186, 80, 20);
		contentPane.add(daymin);
		
		CustomLabel daytemp1 = new CustomLabel("Day");
		daytemp1.setBounds(2, 373, 80, 20);
		contentPane.add(daytemp1);
		
		CustomLabel daymax1 = new CustomLabel("Max");
		daymax1.setBounds(2, 388, 80, 20);
		contentPane.add(daymax1);
		
		CustomLabel daymin1 = new CustomLabel("Min");
		daymin1.setBounds(2, 403, 80, 20);
		contentPane.add(daymin1);
		
		
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

		
		try{
		refresh(city,country);
		}
		catch(Exception e){
			System.out.println("IO exception, Long term refresh method");
		}
	}

	public void actionPerformed(ActionEvent ae) {
		refresh(city);
	}

	public void refresh(String city){
		try {
            weather = new LongTerm(city);

            for(int i = 0; i < 7; i++) {
            	date[i].setText(dateParse(weather.getDate(i+1)));
            	day[i].setText(weather.getTempDay(i));
                skyCondition[i].setText(weather.getSkyCondition(i));
                icon[i].setIcon(new ImageIcon(weather.getSkyCondition(i).toLowerCase() + ".png"));
                max[i].setText(weather.getTempMax(i));
                min[i].setText(weather.getTempMin(i));
               // rise[i].setText(weather.getSunrise(i));
               // set[i].setText(weather.getSunset(i));
            }
		}
        catch(Exception e) {
			System.out.println("IO exception, Long term refresh method");
		}
	}

	public void refresh(String city,String country) throws IOException, JSONException{
		weather = new LongTerm(city,country);

		for(int i=0;i<7;i++) {
			date[i].setText(dateParse(weather.getDate(i+1)));
			day[i].setText(weather.getTempDay(i));
			skyCondition[i].setText(weather.getSkyCondition(i));
			icon[i].setIcon(new ImageIcon(weather.getSkyCondition(i).toLowerCase() + ".png"));
            max[i].setText(weather.getTempMax(i));
            min[i].setText(weather.getTempMin(i));
            //rise[i].setText(weather.getSunrise(i));
            //set[i].setText(weather.getSunset(i));
		}
	}


	private String dateParse(String date) {
		StringTokenizer st = new StringTokenizer(date);
		String day = st.nextToken();
		st.nextToken();
		st.nextToken();
		//String time = st.nextToken().substring(0, 3)+ "00";		
		return (day);
	}
	
	//not needed but maybe useful for long term
    /* private String addHours(String time,int i)
     {
		//i=i-1;
		System.out.println(i);
		int temp = Integer.parseInt(time);
		if(temp>=24){
			temp = temp-24;
			nextDay=true;
		}
		if(temp>=10){
			return Integer.toString(temp);
		}
		return "0" + Integer.toString(temp);
	}*/
	
	private String nextDay(String day){
		if(day.compareTo("Mon")==0){
			return "Tue";
		}else if(day.compareTo("Tue")==0){
			return "Wed";
		}else if(day.compareTo("Wed")==0){
			return "Thu";
		}else if(day.compareTo("Thu")==0){
			return "Fri";
		}else if(day.compareTo("Fri")==0){
			return "Sat";
		}else if(day.compareTo("Sat")==0){
			return "Sun";
		}
		return "Mon";
		
	}
	public JPanel getPanel(){
		return contentPane;
	}
}
