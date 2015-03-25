package ca.uwo.csd.cs2212.team4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUICurrentWeather extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUIModel2 frame = new GUIModel2();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	

	/**
	 * Create the frame.
	 */
	public GUICurrentWeather() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(8,194,184));
		
		contentPane.setBorder(BorderFactory.createRaisedBevelBorder());
		setContentPane(contentPane);
		
		
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
		
		JPanel MiscWeatherPanel = new JPanel();
		MiscWeatherPanel.setOpaque(false);
		GroupLayout gl_BottomPanel = new GroupLayout(BottomPanel);
		gl_BottomPanel.setHorizontalGroup(
			gl_BottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BottomPanel.createSequentialGroup()
					.addComponent(AllTempPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(125))
				.addGroup(gl_BottomPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(MiscWeatherPanel, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_BottomPanel.setVerticalGroup(
			gl_BottomPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_BottomPanel.createSequentialGroup()
					.addComponent(AllTempPanel, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(MiscWeatherPanel)
					.addContainerGap())
		);
		
		CustomLabel lblPressure = new CustomLabel("Pressure");
		
		CustomLabel lblHumidity = new CustomLabel("Humidity");
		
		CustomLabel lblSunrise = new CustomLabel("Sunrise");
		
		CustomLabel lblSunset = new CustomLabel("Sunset");
		
		CustomLabel lblPrecipitation = new CustomLabel("Windspeed");
		
		CustomLabel lblWinddirection = new CustomLabel("Winddirection");
		
		CustomLabel lblPrecipitation_1 = new CustomLabel("Precipitation:");
		GroupLayout gl_MiscWeatherPanel = new GroupLayout(MiscWeatherPanel);
		gl_MiscWeatherPanel.setHorizontalGroup(
			gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPressure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHumidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(29)
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSunrise, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSunset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblWinddirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrecipitation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblPrecipitation_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_MiscWeatherPanel.setVerticalGroup(
			gl_MiscWeatherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MiscWeatherPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPressure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrecipitation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSunrise, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_MiscWeatherPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHumidity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWinddirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSunset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPrecipitation_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		MiscWeatherPanel.setLayout(gl_MiscWeatherPanel);
		
		JPanel CurrentTempPanel = new JPanel();
		CurrentTempPanel.setOpaque(false);
		
		JPanel OtherTempPanel = new JPanel();
		OtherTempPanel.setOpaque(false);
		
		CustomLabel lblMax = new CustomLabel("Max");
		
		CustomLabel lblMin = new CustomLabel("Min");
		
		CustomLabel lblCondition = new CustomLabel("Condition");
		GroupLayout gl_OtherTempPanel = new GroupLayout(OtherTempPanel);
		gl_OtherTempPanel.setHorizontalGroup(
			gl_OtherTempPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_OtherTempPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_OtherTempPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMin)
						.addComponent(lblMax)
						.addComponent(lblCondition))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_OtherTempPanel.setVerticalGroup(
			gl_OtherTempPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_OtherTempPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblCondition)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMax)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMin)
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
		
		String strTemp = "DEFAULT";
		try{
			strTemp = LocalWeatherView.getTemperature();
		}
		catch(Exception e){
			System.out.println("Error getting temperature");
		}
		CustomLabel lblTemp = new CustomLabel(strTemp);
		lblTemp.setFont(new Font("Gotham Light", Font.PLAIN, 40));
		CurrentTempPanel.add(lblTemp);
		AllTempPanel.setLayout(gl_AllTempPanel);
		BottomPanel.setLayout(gl_BottomPanel);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		topPanel.add(infoPanel, BorderLayout.NORTH);
		
		CustomLabel lblLocation = new CustomLabel("LOCATION");
		
		CustomLabel lblLastUpdate = new CustomLabel("Last Update:");
		
		CustomLabel lblDate = new CustomLabel("Date");
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
		
		
		//Image import
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File("Cloudy.png"));
		}
		catch(IOException e){
			System.out.println("Can't open image file");
		}
		JLabel lblIcon = new JLabel(new ImageIcon(img));
		topPanel.add(lblIcon, BorderLayout.CENTER);

	}
	
	public JPanel getPanel(){
		return contentPane;
	}
}
