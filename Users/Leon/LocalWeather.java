import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LocalWeather extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtLondonOnt;
	//private JLabel temp3,temp6,temp9;
	private JLabel[] tempMax,tempMin,windSpeed,windDirection,airPressure,humidity,skyCondition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalWeather frame = new LocalWeather("london");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LocalWeather(String location) {
		setTitle("Short-Term Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 523);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setMinimumSize(new Dimension(650, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[107.00][17.00][-48.00][85][10.00][85][][85][][85][][85][][85][0][85.00][][85]", "[][18.00][23.00][40][40][40][40][40][40][40][40]"));
		
		JLabel lblNewLabel_2 = new JLabel("Current Location:");
		contentPane.add(lblNewLabel_2, "cell 0 0,alignx trailing");
		
		txtLondonOnt = new JTextField();
		txtLondonOnt.setText(location);
		contentPane.add(txtLondonOnt, "cell 1 0 3 1,alignx center");
		txtLondonOnt.setColumns(10);
		
		JLabel lblUpdated = new JLabel("Updated:");
		contentPane.add(lblUpdated, "cell 11 0,alignx right");
		
		JLabel lblNow = new JLabel("\"time\"");
		contentPane.add(lblNow, "cell 12 0 2 1");
		
		JLabel lblThree = new JLabel("3");
		lblThree.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblThree, "cell 3 2,alignx center");
		
		JLabel lblSix = new JLabel("6");
		lblSix.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblSix, "cell 5 2,alignx center");
		
		JLabel lblNine = new JLabel("9");
		lblNine.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNine, "cell 7 2,alignx center");
		
		JLabel lblTwelve = new JLabel("12");
		lblTwelve.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTwelve, "cell 9 2,alignx center");
		
		JLabel lblFifteen = new JLabel("15");
		lblFifteen.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblFifteen, "cell 11 2,alignx center");
		
		JLabel lblEighteen = new JLabel("18");
		lblEighteen.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblEighteen, "cell 13 2,alignx center");
		
		JLabel lblTwentyOne = new JLabel("21");
		lblTwentyOne.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTwentyOne, "cell 15 2,alignx center");
		
		JLabel lblTwentyFour = new JLabel("24");
		lblTwentyFour.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTwentyFour, "cell 17 2,alignx center");
		
		JLabel lblMaxTemp = new JLabel("Max Temp:");
		lblMaxTemp.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblMaxTemp, "cell 0 3");
		
		
		//Temperature
		JLabel lblTemp = new JLabel("Min Temp:");
		lblTemp.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTemp, "cell 0 4,growx,aligny center");
		
		JLabel temp3 = new JLabel();
		contentPane.add(temp3, "cell 3 4,alignx center,aligny center");
		temp3.setText("temp");
		
		JLabel temp6 = new JLabel();
		contentPane.add(temp6, "cell 5 4,alignx center,aligny center");
		temp6.setText("temp");
		
		JLabel temp9 = new JLabel();
		contentPane.add(temp9, "cell 7 4,alignx center,aligny center");
		temp9.setText("temp");
		
		JLabel temp12 = new JLabel();
		contentPane.add(temp12, "cell 9 4,alignx center,aligny center");
		temp12.setText("temp");
		
		JLabel temp15 = new JLabel();
		contentPane.add(temp15, "cell 11 4,alignx center,aligny center");
		temp15.setText("temp");
		
		JLabel temp18 = new JLabel();
		contentPane.add(temp18, "cell 13 4,alignx center,aligny center");
		temp18.setText("temp");
		
		JLabel temp21 = new JLabel();
		contentPane.add(temp21, "cell 15 4,alignx center,aligny center");
		temp21.setText("temp");
		
		//Separators
		JSeparator separator = new JSeparator();
		separator.setSize(new Dimension(665, 20));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setMinimumSize(new Dimension(7, 350));
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		contentPane.add(separator, "cell 6 3 1 8");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(650, 20));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setMinimumSize(new Dimension(7, 350));
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		contentPane.add(separator_1, "cell 8 3 1 8");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setSize(new Dimension(650, 20));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setMinimumSize(new Dimension(7, 350));
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		contentPane.add(separator_2, "cell 10 3 1 8");
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setSize(new Dimension(650, 20));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setMinimumSize(new Dimension(7, 350));
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		contentPane.add(separator_3, "cell 12 3 1 8");
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setSize(new Dimension(650, 20));
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setMinimumSize(new Dimension(7, 350));
		separator_4.setForeground(Color.BLACK);
		separator_4.setBackground(Color.BLACK);
		contentPane.add(separator_4, "cell 14 3 1 8");
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setSize(new Dimension(650, 20));
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setMinimumSize(new Dimension(7, 350));
		separator_5.setForeground(Color.BLACK);
		separator_5.setBackground(Color.BLACK);
		contentPane.add(separator_5, "cell 16 3 1 8");
		
		JSeparator sepVert = new JSeparator();
		sepVert.setOrientation(SwingConstants.VERTICAL);
		sepVert.setMinimumSize(new Dimension(7, 350));
		sepVert.setBackground(Color.BLACK);
		sepVert.setForeground(Color.BLACK);
		sepVert.setSize(new Dimension(650, 20));
		contentPane.add(sepVert, "cell 4 3 1 8");
		
		
		//Wind speed
		JLabel lblWind = new JLabel("Wind speed:");
		lblWind.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblWind, "cell 0 5,growx");
		
		JLabel wind3 = new JLabel();
		contentPane.add(wind3, "cell 3 5,alignx center");
		wind3.setText("wind");
		
		JLabel wind6 = new JLabel();
		contentPane.add(wind6, "cell 5 5,alignx center");
		wind6.setText("wind");
		
		JLabel windTue = new JLabel();
		contentPane.add(windTue, "cell 7 5,alignx center");
		windTue.setText("wind");
		
		JLabel wind12 = new JLabel();
		contentPane.add(wind12, "cell 9 5,alignx center");
		wind12.setText("wind");
		
		JLabel wind15 = new JLabel();
		contentPane.add(wind15, "cell 11 5,alignx center");
		wind15.setText("wind");
		
		JLabel wind18 = new JLabel();
		contentPane.add(wind18, "cell 13 5,alignx center");
		wind18.setText("wind");
		
		JLabel wind21 = new JLabel();
		contentPane.add(wind21, "cell 15 5,alignx center");
		wind21.setText("wind");
		
		
		//Wind direction
		JLabel lblWindDirection = new JLabel("Wind direction:");
		contentPane.add(lblWindDirection, "cell 0 6,growx");
		lblWindDirection.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel windDir3 = new JLabel();
		contentPane.add(windDir3, "cell 3 6,alignx center");
		windDir3.setText("windDir");
		
		JLabel windDir6 = new JLabel();
		contentPane.add(windDir6, "cell 5 6,alignx center");
		windDir6.setText("windDir");
		
		JLabel windDirTue = new JLabel();
		contentPane.add(windDirTue, "cell 7 6,alignx center");
		windDirTue.setText("windDir");
		
		JLabel windDir12 = new JLabel();
		contentPane.add(windDir12, "cell 9 6,alignx center");
		windDir12.setText("windDir");
		
		JLabel windDir15 = new JLabel();
		contentPane.add(windDir15, "cell 11 6,alignx center");
		windDir15.setText("windDir");
		
		JLabel windDir18 = new JLabel();
		contentPane.add(windDir18, "cell 13 6,alignx center");
		windDir18.setText("windDir");
		
		JLabel windDir21 = new JLabel();
		contentPane.add(windDir21, "cell 15 6,alignx center");
		windDir21.setText("windDir");
		
		//Air pressure
		JLabel lblAirPressure = new JLabel("Air pressure:");
		contentPane.add(lblAirPressure, "cell 0 7,growx");
		lblAirPressure.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel air3 = new JLabel();
		contentPane.add(air3, "cell 3 7,alignx center");
		air3.setText("Air");
		
		JLabel air6 = new JLabel();
		contentPane.add(air6, "cell 5 7,alignx center");
		air6.setText("Air");
		
		JLabel airTue = new JLabel();
		contentPane.add(airTue, "cell 7 7,alignx center");
		airTue.setText("Air");
		
		JLabel air12 = new JLabel();
		contentPane.add(air12, "cell 9 7,alignx center");
		air12.setText("Air");
		
		JLabel air15 = new JLabel();
		contentPane.add(air15, "cell 11 7,alignx center");
		air15.setText("Air");
		
		JLabel air18 = new JLabel();
		contentPane.add(air18, "cell 13 7,alignx center");
		air18.setText("Air");
		
		JLabel air21 = new JLabel();
		contentPane.add(air21, "cell 15 7,alignx center");
		air21.setText("Air");
		
		
		//Humidity
		JLabel lblHumidity = new JLabel("Humidity:");
		contentPane.add(lblHumidity, "cell 0 8,growx");
		lblHumidity.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel hum3 = new JLabel();
		contentPane.add(hum3, "cell 3 8,alignx center");
		hum3.setText("hum");
		
		JLabel hum6 = new JLabel();
		contentPane.add(hum6, "cell 5 8,alignx center");
		hum6.setText("hum");
		
		JLabel humTue = new JLabel();
		contentPane.add(humTue, "cell 7 8,alignx center");
		humTue.setText("hum");
		
		JLabel hum12 = new JLabel();
		contentPane.add(hum12, "cell 9 8,alignx center");
		hum12.setText("hum");
		
		JLabel hum15 = new JLabel();
		contentPane.add(hum15, "cell 11 8,alignx center");
		hum15.setText("hum");
		
		JLabel hum18 = new JLabel();
		contentPane.add(hum18, "cell 13 8,alignx center");
		hum18.setText("hum");
		
		JLabel hum21 = new JLabel();
		contentPane.add(hum21, "cell 15 8,alignx center");
		hum21.setText("hum");
		
		
		//Sky condition
		JLabel lblSkyCondition = new JLabel("Sky condition:");
		contentPane.add(lblSkyCondition, "cell 0 9,growx");
		lblSkyCondition.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel skyCon3 = new JLabel();
		contentPane.add(skyCon3, "cell 3 9,alignx center");
		skyCon3.setText("skyCon");
		
		JLabel skyCon6 = new JLabel();
		contentPane.add(skyCon6, "cell 5 9,alignx center");
		skyCon6.setText("skyCon");
		
		JLabel skyConTue = new JLabel();
		contentPane.add(skyConTue, "cell 7 9,alignx center");
		skyConTue.setText("skyCon");
		
		JLabel skyCon12 = new JLabel();
		contentPane.add(skyCon12, "cell 9 9,alignx center");
		skyCon12.setText("skyCon");
		
		JLabel skyCon15 = new JLabel();
		contentPane.add(skyCon15, "cell 11 9,alignx center");
		skyCon15.setText("skyCon");
		
		JLabel skyCon18 = new JLabel();
		contentPane.add(skyCon18, "cell 13 9,alignx center");
		skyCon18.setText("skyCon");
		
		JLabel skyCon21 = new JLabel();
		contentPane.add(skyCon21, "cell 15 9,alignx center");
		skyCon21.setText("skyCon");
	
		//Sky images
		JLabel lblSkyImage = new JLabel("\"sky image\"");
		contentPane.add(lblSkyImage, "cell 3 10,alignx center");
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(this);
		contentPane.add(btnRefresh, "cell 0 1,alignx center");
		
		tempMax = new JLabel[7];
		tempMax[0] = new JLabel ("test");
		tempMax[1] = temp6;
		contentPane.add(tempMax[0], "cell 5 10");
	}

	public void actionPerformed(ActionEvent ae){
	
			//temp3.setText("test");
			tempMax[0].setText("it works");
			tempMax[1].setText("abcdefgh");
		
	}
}
