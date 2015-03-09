Enter file contents hereimport java.awt.EventQueue;


public class GUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Long-Term Weather View"); 
		frame.setBounds(100, 100, 731, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "MO", "TU", "WED", "TH", "FR", "SA", "SU"},
				{"Weather Condition", null, null, null, null, null, null, null},
				{null, getpicture(), null, null, null, null, null, null},
				{"Temp", null, null, null, null, null, null, null},
				{"Max Temp", null, null, null, null, null, null, null},
				{"Min Temp", null, null, null, null, null, null, null},
				{"Humidity", null, null, null, null, null, null, null},
				{"Wind Speed", null, null, null, null, null, null, null},
				{"Wind Direction", null, null, null, null, null, null, null},
				{"Sun Rise", null, null, null, null, null, null, null},
				{"Sun Set", null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(128);
		table.setBounds(10, 103, 695, 278);
		frame.getContentPane().add(table);
		
		JTextPane txtpnCurrentLocationLondon = new JTextPane();
		txtpnCurrentLocationLondon.setEditable(false);
		txtpnCurrentLocationLondon.setText("Current Location: London, ON, Canada");
		txtpnCurrentLocationLondon.setBounds(27, 11, 230, 20);
		frame.getContentPane().add(txtpnCurrentLocationLondon);
		
		JTextPane txtpnLastTimeUpgrade = new JTextPane();
		txtpnLastTimeUpgrade.setText("Last Time Update: 9:31 am");
		txtpnLastTimeUpgrade.setBounds(315, 11, 284, 20);
		frame.getContentPane().add(txtpnLastTimeUpgrade);
	}

	private Object getpicture() {
		// TODO Auto-generated method stub
		return null;
	}
}
