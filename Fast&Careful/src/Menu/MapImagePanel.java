package Menu;


import javax.swing.*;

import java.awt.*;

public class MapImagePanel extends JPanel {
	//VARIABLES
	// Arrays to store images of the background and menu image
	ImageIcon[] mapImageMenu;
	JLabel mapImage;
	JTextArea infoField;
	String info;
	
	// Constructor
	public MapImagePanel () {
		GridLayout gl = new GridLayout(2, 1);
		gl.setVgap(20);
		setLayout(gl);
		mapImageMenu = new ImageIcon[3];
		for (int i = 0; i < 3; i++) {
			mapImageMenu[i] = new ImageIcon(("maps/map" + (i + 1) + ".png"));
		}	
			
		
		mapImage = new JLabel(mapImageMenu[0]);
		// impelementing and arranging text area
		infoField = new JTextArea(info);
		infoField.setEditable(false);
		infoField.setBackground(new Color(117, 71, 255));
		infoField.setForeground(Color.red);
		infoField.setFont(new Font(getName(), Font.BOLD, 20)); 
		
		// adding
		add(mapImage);
		add(infoField);
	}
	
	public ImageIcon getImage(int mapNo) {
		return mapImageMenu[mapNo];
	}
	// a method to change image
	public void changeMap(int mapNo) {
		remove(mapImage);
		mapImage = new JLabel(mapImageMenu[mapNo]);
		add(mapImage, 0);
	}
	// a method to change info
	public void setText(String str) {
		info = str;
		remove(infoField);
		infoField.setText(info);
		add(infoField, 1);
	}
}
