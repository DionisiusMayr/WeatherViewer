package ca.uwo.csd.cs2212.team4;

import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * CustomLabel is the class that defines the default font appearance used in the GUI windows.
 * A custom font imported from the resource folder is used. Font size and colour are set.
 * 
 * @author Team 4
 * */
public class CustomLabel extends JLabel{
	
	/**
	 * Constructor to create a CustomLabel with the input text. 
	 * 
	 * @param text the string to be displayed as this CustomLabel
	 * */
	public CustomLabel (String text){
		super(text);
		this.setFont(new Font("Gotham Light", Font.PLAIN, 12));
		this.setForeground(Color.BLACK);
		
		//import custom font
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Gotham Light.ttf")));
		}
		catch(IOException e) {
			System.out.println("IOException with font -CZ");
		}
		catch(FontFormatException e) {
			System.out.println("FontFormatException -CZ");
		}
	}
}
