package ca.uwo.csd.cs2212.team4;

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

public class LongTermForecast extends JFrame {

	private JPanel contentPane;
	private JTextField txtLondonOnt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LongTermForecast frame = new LongTermForecast();
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
	public LongTermForecast() {
		setTitle("Short-Term Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 477);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setMinimumSize(new Dimension(650, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[107.00][17.00][-48.00][85][10.00][85][][85][][85][][85][][85][0][85.00]", "[][18.00][23.00][40][40][40][40][40][40][40]"));
		
		JLabel lblNewLabel_2 = new JLabel("Current Location:");
		contentPane.add(lblNewLabel_2, "cell 0 0,alignx trailing");
		
		txtLondonOnt = new JTextField();
		txtLondonOnt.setText("London, Ont");
		contentPane.add(txtLondonOnt, "cell 1 0 3 1,alignx center");
		txtLondonOnt.setColumns(10);
		
		JLabel lblUpdated = new JLabel("Updated:");
		contentPane.add(lblUpdated, "cell 11 0,alignx right");
		
		JLabel lblNow = new JLabel("\"time\"");
		contentPane.add(lblNow, "cell 12 0 2 1");
		
		JButton btnRefresh = new JButton("Refresh");
		contentPane.add(btnRefresh, "cell 0 1,alignx center");
		
		JLabel lblSunday = new JLabel("Sunday");
		lblSunday.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblSunday, "cell 3 2,alignx center");
		
		JLabel lblMonday = new JLabel("Monday");
		lblMonday.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblMonday, "cell 5 2,alignx center");
		
		JLabel lblTuesday = new JLabel("Tuesday");
		lblTuesday.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTuesday, "cell 7 2,alignx center");
		
		JLabel lblWednesday = new JLabel("Wednesday");
		lblWednesday.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblWednesday, "cell 9 2,alignx center");
		
		JLabel lblThursday = new JLabel("Thursday");
		lblThursday.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblThursday, "cell 11 2,alignx center");
		
		JLabel lblFriday = new JLabel("Friday");
		lblFriday.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblFriday, "cell 13 2,alignx center");
		
		JLabel lblSaturday = new JLabel("Saturday");
		lblSaturday.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblSaturday, "cell 15 2,alignx center");
		
		JLabel lblTemp = new JLabel("Temperature:");
		lblTemp.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTemp, "cell 0 3,growx");
		
		JLabel tempSun = new JLabel();
		contentPane.add(tempSun, "cell 3 3,alignx center,aligny center");
		tempSun.setText("temp");
		
		JLabel tempMon = new JLabel();
		contentPane.add(tempMon, "cell 5 3,alignx center,aligny center");
		tempMon.setText("temp");
		
		JLabel tempTues = new JLabel();
		contentPane.add(tempTues, "cell 7 3,alignx center,aligny center");
		tempTues.setText("temp");
		
		JLabel tempWed = new JLabel();
		contentPane.add(tempWed, "cell 9 3,alignx center,aligny center");
		tempWed.setText("temp");
		
		JLabel tempThu = new JLabel();
		contentPane.add(tempThu, "cell 11 3,alignx center,aligny center");
		tempThu.setText("temp");
		
		JLabel tempFri = new JLabel();
		contentPane.add(tempFri, "cell 13 3,alignx center,aligny center");
		tempFri.setText("temp");
		
		JLabel tempSat = new JLabel();
		contentPane.add(tempSat, "cell 15 3,alignx center,aligny center");
		tempSat.setText("temp");
		
		JSeparator separator = new JSeparator();
		separator.setSize(new Dimension(665, 20));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setMinimumSize(new Dimension(7, 350));
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		contentPane.add(separator, "cell 6 3 1 7");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(650, 20));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setMinimumSize(new Dimension(7, 350));
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		contentPane.add(separator_1, "cell 8 3 1 7");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setSize(new Dimension(650, 20));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setMinimumSize(new Dimension(7, 350));
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		contentPane.add(separator_2, "cell 10 3 1 7");
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setSize(new Dimension(650, 20));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setMinimumSize(new Dimension(7, 350));
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		contentPane.add(separator_3, "cell 12 3 1 7");
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setSize(new Dimension(650, 20));
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setMinimumSize(new Dimension(7, 350));
		separator_4.setForeground(Color.BLACK);
		separator_4.setBackground(Color.BLACK);
		contentPane.add(separator_4, "cell 14 3 1 7");
		
		JLabel lblWind = new JLabel("Wind speed:");
		lblWind.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblWind, "cell 0 4,growx");
		
		JSeparator sepVert = new JSeparator();
		sepVert.setOrientation(SwingConstants.VERTICAL);
		sepVert.setMinimumSize(new Dimension(7, 350));
		sepVert.setBackground(Color.BLACK);
		sepVert.setForeground(Color.BLACK);
		sepVert.setSize(new Dimension(650, 20));
		contentPane.add(sepVert, "cell 4 3 1 7");
		
		JLabel windSun = new JLabel();
		contentPane.add(windSun, "cell 3 4,alignx center");
		windSun.setText("wind");
		
		JLabel windMon = new JLabel();
		contentPane.add(windMon, "cell 5 4,alignx center");
		windMon.setText("wind");
		
		JLabel windTue = new JLabel();
		contentPane.add(windTue, "cell 7 4,alignx center");
		windTue.setText("wind");
		
		JLabel windWed = new JLabel();
		contentPane.add(windWed, "cell 9 4,alignx center");
		windWed.setText("wind");
		
		JLabel windThu = new JLabel();
		contentPane.add(windThu, "cell 11 4,alignx center");
		windThu.setText("wind");
		
		JLabel windFri = new JLabel();
		contentPane.add(windFri, "cell 13 4,alignx center");
		windFri.setText("wind");
		
		JLabel windSat = new JLabel();
		contentPane.add(windSat, "cell 15 4,alignx center");
		windSat.setText("wind");
		
		JLabel lblWindDirection = new JLabel("Wind direction:");
		contentPane.add(lblWindDirection, "cell 0 5,growx");
		lblWindDirection.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel windDirSun = new JLabel();
		contentPane.add(windDirSun, "cell 3 5,alignx center");
		windDirSun.setText("windDir");
		
		JLabel windDirMon = new JLabel();
		contentPane.add(windDirMon, "cell 5 5,alignx center");
		windDirMon.setText("windDir");
		
		JLabel windDirTue = new JLabel();
		contentPane.add(windDirTue, "cell 7 5,alignx center");
		windDirTue.setText("windDir");
		
		JLabel windDirWed = new JLabel();
		contentPane.add(windDirWed, "cell 9 5,alignx center");
		windDirWed.setText("windDir");
		
		JLabel windDirThu = new JLabel();
		contentPane.add(windDirThu, "cell 11 5,alignx center");
		windDirThu.setText("windDir");
		
		JLabel windDirFri = new JLabel();
		contentPane.add(windDirFri, "cell 13 5,alignx center");
		windDirFri.setText("windDir");
		
		JLabel windDirSat = new JLabel();
		contentPane.add(windDirSat, "cell 15 5,alignx center");
		windDirSat.setText("windDir");
		
		JLabel lblAirPressure = new JLabel("Air pressure:");
		contentPane.add(lblAirPressure, "cell 0 6,growx");
		lblAirPressure.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel airSun = new JLabel();
		contentPane.add(airSun, "cell 3 6,alignx center");
		airSun.setText("Air");
		
		JLabel airMon = new JLabel();
		contentPane.add(airMon, "cell 5 6,alignx center");
		airMon.setText("Air");
		
		JLabel airTue = new JLabel();
		contentPane.add(airTue, "cell 7 6,alignx center");
		airTue.setText("Air");
		
		JLabel airWed = new JLabel();
		contentPane.add(airWed, "cell 9 6,alignx center");
		airWed.setText("Air");
		
		JLabel airThu = new JLabel();
		contentPane.add(airThu, "cell 11 6,alignx center");
		airThu.setText("Air");
		
		JLabel airFri = new JLabel();
		contentPane.add(airFri, "cell 13 6,alignx center");
		airFri.setText("Air");
		
		JLabel airSat = new JLabel();
		contentPane.add(airSat, "cell 15 6,alignx center");
		airSat.setText("Air");
		
		JLabel lblHumidity = new JLabel("Humidity:");
		contentPane.add(lblHumidity, "cell 0 7,growx");
		lblHumidity.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel humSun = new JLabel();
		contentPane.add(humSun, "cell 3 7,alignx center");
		humSun.setText("hum");
		
		JLabel humMon = new JLabel();
		contentPane.add(humMon, "cell 5 7,alignx center");
		humMon.setText("hum");
		
		JLabel humTue = new JLabel();
		contentPane.add(humTue, "cell 7 7,alignx center");
		humTue.setText("hum");
		
		JLabel humWed = new JLabel();
		contentPane.add(humWed, "cell 9 7,alignx center");
		humWed.setText("hum");
		
		JLabel humThu = new JLabel();
		contentPane.add(humThu, "cell 11 7,alignx center");
		humThu.setText("hum");
		
		JLabel humFri = new JLabel();
		contentPane.add(humFri, "cell 13 7,alignx center");
		humFri.setText("hum");
		
		JLabel humSat = new JLabel();
		contentPane.add(humSat, "cell 15 7,alignx center");
		humSat.setText("hum");
		
		JLabel lblSkyCondition = new JLabel("Sky condition:");
		contentPane.add(lblSkyCondition, "cell 0 8,growx");
		lblSkyCondition.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel skyConSun = new JLabel();
		contentPane.add(skyConSun, "cell 3 8,alignx center");
		skyConSun.setText("skyCon");
		
		JLabel skyConMon = new JLabel();
		contentPane.add(skyConMon, "cell 5 8,alignx center");
		skyConMon.setText("skyCon");
		
		JLabel skyConTue = new JLabel();
		contentPane.add(skyConTue, "cell 7 8,alignx center");
		skyConTue.setText("skyCon");
		
		JLabel skyConWed = new JLabel();
		contentPane.add(skyConWed, "cell 9 8,alignx center");
		skyConWed.setText("skyCon");
		
		JLabel skyConThu = new JLabel();
		contentPane.add(skyConThu, "cell 11 8,alignx center");
		skyConThu.setText("skyCon");
		
		JLabel skyConFri = new JLabel();
		contentPane.add(skyConFri, "cell 13 8,alignx center");
		skyConFri.setText("skyCon");
		
		JLabel skyConSat = new JLabel();
		contentPane.add(skyConSat, "cell 15 8,alignx center");
		skyConSat.setText("skyCon");
		
		JLabel lblSkyImage = new JLabel("\"sky image\"");
		contentPane.add(lblSkyImage, "cell 3 9,alignx center");
		
	}

}
