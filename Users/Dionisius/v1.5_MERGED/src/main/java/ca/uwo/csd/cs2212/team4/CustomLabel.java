package ca.uwo.csd.cs2212.team4;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class CustomLabel extends JLabel{
	public CustomLabel (String text){
		super(text);
		this.setFont(new Font("Gotham Light", Font.PLAIN, 12));
		this.setForeground(Color.BLACK);

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
