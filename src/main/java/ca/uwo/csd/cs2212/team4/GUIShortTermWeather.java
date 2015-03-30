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

public class GUIShortTermWeather extends JFrame implements ActionListener {
	private JPanel contentPane;
	private String city,country;
	private ShortTerm weather;
	private Boolean nextDay;
	private JLabel[] temp,skyCondition,icon,date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIShortTermWeather frame = new GUIShortTermWeather();
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
	public GUIShortTermWeather() {
		city = GUIApp.pref.getCity();
		country = GUIApp.pref.getCountry();
		nextDay=false;

		setTitle("Short-Term Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setMinimumSize(new Dimension(650, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CustomLabel lbl3 = new CustomLabel("3");
		lbl3.setBounds(40, 25, 80, 20);
		contentPane.add(lbl3);

		CustomLabel lbl6 = new CustomLabel("6");
		lbl6.setBounds(140, 25, 80, 20);
		contentPane.add(lbl6);

		CustomLabel lbl9 = new CustomLabel("9");
		lbl9.setBounds(240, 25, 80, 20);
		contentPane.add(lbl9);

		CustomLabel lbl12 = new CustomLabel("12");
		lbl12.setBounds(340, 25, 80, 20);
		contentPane.add(lbl12);

		CustomLabel lbl15 = new CustomLabel("15");
		lbl15.setBounds(40, 210, 80, 20);
		contentPane.add(lbl15);

		CustomLabel lbl18 = new CustomLabel("18");
		lbl18.setBounds(140,210, 80, 20);
		contentPane.add(lbl18);

		CustomLabel lbl21 = new CustomLabel("21");
		lbl21.setBounds(240, 210, 80, 20);
		contentPane.add(lbl21);

		CustomLabel lbl24 = new CustomLabel("24");
		lbl24.setBounds(340, 210, 80, 20);
		contentPane.add(lbl24);

		CustomLabel temp3 = new CustomLabel("temp");
		temp3.setBounds(50, 165, 80, 14);
		contentPane.add(temp3);
		temp3.setText("temp");

		CustomLabel temp6 = new CustomLabel("temp");
		temp6.setBounds(150, 165, 80, 14);
		contentPane.add(temp6);
		temp6.setText("temp");

		CustomLabel temp9 = new CustomLabel("temp");
		temp9.setBounds(250, 165, 80, 14);
		contentPane.add(temp9);
		temp9.setText("temp");

		CustomLabel temp12 = new CustomLabel("temp");
		temp12.setBounds(350, 165, 80, 14);
		contentPane.add(temp12);
		temp12.setText("temp");

		CustomLabel temp15 = new CustomLabel("temp");
		temp15.setBounds(50, 350, 80, 14);
		contentPane.add(temp15);
		temp15.setText("temp");

		CustomLabel temp18 = new CustomLabel("temp");
		temp18.setBounds(150, 350, 80, 14);
		contentPane.add(temp18);
		temp18.setText("temp");

		CustomLabel temp21 = new CustomLabel("temp");
		temp21.setBounds(250, 350, 80, 14);
		contentPane.add(temp21);
		temp21.setText("temp");

		CustomLabel temp24 = new CustomLabel("temp");
		temp24.setBounds(350, 350, 80, 14);
		contentPane.add(temp24);

		//skycondition
		CustomLabel skyCon3 = new CustomLabel("temp");
		skyCon3.setBounds(50, 150, 80, 14);
		contentPane.add(skyCon3);
		skyCon3.setText("skyCon");

		CustomLabel skyCon6 = new CustomLabel("temp");
		skyCon6.setBounds(150, 150, 80, 14);
		contentPane.add(skyCon6);
		skyCon6.setText("skyCon");

		CustomLabel skyCon9 = new CustomLabel("temp");
		skyCon9.setBounds(250, 150, 80, 14);
		contentPane.add(skyCon9);
		skyCon9.setText("skyCon");

		CustomLabel skyCon12 = new CustomLabel("temp");
		skyCon12.setBounds(350, 150, 80, 14);
		contentPane.add(skyCon12);
		skyCon12.setText("skyCon");

		CustomLabel skyCon15 = new CustomLabel("temp");
		skyCon15.setBounds(50, 335, 80, 14);
		contentPane.add(skyCon15);
		skyCon15.setText("skyCon");

		CustomLabel skyCon18 = new CustomLabel("temp");
		skyCon18.setBounds(150, 335, 80, 14);
		contentPane.add(skyCon18);
		skyCon18.setText("skyCon");

		CustomLabel skyCon21 = new CustomLabel("temp");
		skyCon21.setBounds(250, 335, 80, 14);
		contentPane.add(skyCon21);
		skyCon21.setText("skyCon");

		CustomLabel skyCon24 = new CustomLabel("temp");
		skyCon24.setBounds(350, 335, 80, 14);
		contentPane.add(skyCon24);
		skyCon24.setText("skyCon");

		//sky condition icons
		JLabel sImg3 = new JLabel(new ImageIcon("snow.png"));
		sImg3.setBounds(25,45,100,100);
		contentPane.add(sImg3);

		JLabel sImg6 = new JLabel(new ImageIcon("snow.png"));
		sImg6.setBounds(125,45,100,100);
		contentPane.add(sImg6);

		JLabel sImg9 = new JLabel(new ImageIcon("snow.png"));
		sImg9.setBounds(225,45,100,100);
		contentPane.add(sImg9);

		JLabel sImg12 = new JLabel(new ImageIcon("snow.png"));
		sImg12.setBounds(325,45,100,100);
		contentPane.add(sImg12);

		JLabel sImg15 = new JLabel(new ImageIcon("snow.png"));
		sImg15.setBounds(25,230,100,100);
		contentPane.add(sImg15);

		JLabel sImg18 = new JLabel(new ImageIcon("snow.png"));
		sImg18.setBounds(125,230,100,100);
		contentPane.add(sImg18);

		JLabel sImg21 = new JLabel(new ImageIcon("snow.png"));
		sImg21.setBounds(225,230,100,100);
		contentPane.add(sImg21);

		JLabel sImg24 = new JLabel(new ImageIcon("snow.png"));
		sImg24.setBounds(325,230,100,100);
		contentPane.add(sImg24);

		temp = new CustomLabel[8];
		temp[0] = temp3;
		temp[1] = temp6;
		temp[2] = temp9;
		temp[3] = temp12;
		temp[4] = temp15;
		temp[5] = temp18;
		temp[6] = temp21;
		temp[7] = temp24;

		skyCondition = new CustomLabel[8];
		skyCondition[0] = skyCon3;
		skyCondition[1] = skyCon6;
		skyCondition[2] = skyCon9;
		skyCondition[3] = skyCon12;
		skyCondition[4] = skyCon15;
		skyCondition[5] = skyCon18;
		skyCondition[6] = skyCon21;
		skyCondition[7] = skyCon24;

		icon = new JLabel[8];
		icon[0] = sImg3;
		icon[1] = sImg6;
		icon[2] = sImg9;
		icon[3] = sImg12;
		icon[4] = sImg15;
		icon[5] = sImg18;
		icon[6] = sImg21;
		icon[7] = sImg24;

		date = new CustomLabel[8];
		date[0] = lbl3;
		date[1] = lbl6;
		date[2] = lbl9;
		date[3] = lbl12;
		date[4] = lbl15;
		date[5] = lbl18;
		date[6] = lbl21;
		date[7] = lbl24;
		
		
		try{
		refresh(city,country);
		}
		catch(Exception e){
			System.out.println("IO exception, short term refresh method");
		}
	}

	public void actionPerformed(ActionEvent ae) {
		refresh(city);
	}

	public void refresh(String city){
		try {
            weather = new ShortTerm(city);

            for(int i = 0; i < 8; i++) {
            	date[i].setText(dateParse(weather.getDate(i+1)));
            	temp[i].setText(weather.getTemperature(i+1));
            	skyCondition[i].setText(weather.getSkyCondition(i+1));
                icon[i].setIcon(new ImageIcon(weather.getSkyCondition(i).toLowerCase()+ ".png" ));
            }
		}
        catch(Exception e) {
			System.out.println("IO exception, short term refresh method");
		}
	}

	public void refresh(String city, String country) throws IOException, JSONException{
		weather = new ShortTerm(city, country);

		for(int i = 0; i < 8; i++) {
			date[i].setText(dateParse(weather.getDate(i+1)));
			temp[i].setText(weather.getTemperature(i+1));
			skyCondition[i].setText(weather.getSkyCondition(i+1));
			icon[i].setIcon(new ImageIcon(weather.getSkyCondition(i).toLowerCase()+ ".png" ));
		}
	}

	private String dateParse(String date) {
		StringTokenizer st = new StringTokenizer(date);
		String day = st.nextToken();
		st.nextToken();
		st.nextToken();
		String time = st.nextToken().substring(0, 3)+ "00";		
		return (day + " " + time);
	}

	public JPanel getPanel(){
		return contentPane;
	}
}
