package ca.uwo.csd.cs2212.team4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

public class GUIFullWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private GUICurrentWeather currentWeatherWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFullWindow frame = new GUIFullWindow();
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
	public GUIFullWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(this);
		contentPane.add(btnRefresh);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		GUIShortTermWeather shortForecastWindow = new GUIShortTermWeather("London Canada");
		GUILongTermWeather longForecastWindow = new GUILongTermWeather();
		
		tabbedPane.addTab("Short Term Forecast", null, shortForecastWindow.getPanel(), null);
		tabbedPane.addTab("Long Term Forecast", null, longForecastWindow.getPanel(), null);

		
		currentWeatherWindow = new GUICurrentWeather();
		splitPane.setLeftComponent(currentWeatherWindow.getPanel());
	}
	
	
	public void actionPerformed(ActionEvent ae){
		
		currentWeatherWindow.refresh();
	
	}

}
