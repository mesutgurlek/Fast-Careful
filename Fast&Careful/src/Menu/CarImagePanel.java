package Menu;



import java.awt.*;

import javax.swing.*;


public class CarImagePanel extends JPanel {
	// Variables
	ImageIcon[][] carImageMenu;
	JLabel carImage;
	JTextArea infoField;
	String info;
	
	// Constructor
	public CarImagePanel () {
		GridLayout gl = new GridLayout(2, 1);
		gl.setVgap(20);
		setLayout(gl);
		// Arrays to store panel image of the cars
		carImageMenu = new ImageIcon[4][6];
	
		// initialization of these images
		for (int i = 0; i < 4; i++) {
			carImageMenu[i][0] = new ImageIcon(("models/model" + (i + 1) + "_red.png"));
			carImageMenu[i][1] = new ImageIcon(("models/model" + (i + 1) + "_yellow.png"));
			carImageMenu[i][2] = new ImageIcon(("models/model" + (i + 1) + "_white.png"));
			carImageMenu[i][3] = new ImageIcon(("models/model" + (i + 1) + "_black.png"));
			carImageMenu[i][4] = new ImageIcon(("models/model" + (i + 1) + "_blue.png"));
			carImageMenu[i][5] = new ImageIcon(("models/model" + (i + 1) + "_pink.png"));
		}
		//default image
		carImage = new JLabel(carImageMenu[0][0]);
		
		// arranging text area which shows the info about the models
		infoField = new JTextArea(info);
		infoField.setEditable(false);
		infoField.setBackground(new Color(117, 71, 255));
		infoField.setForeground(Color.red);
		infoField.setFont(new Font(getName(), Font.BOLD, 20)); 
		
		add(carImage);
		add(infoField);
	}
	// To arrange car info when the model is changed
	public void setText(String str) {
		info = str;
		remove(infoField);
		infoField.setText(info);
		add(infoField, 1);
	}
	// To change car image 
	public void changeCar(int model, int color) {
		remove(carImage);
		carImage = new JLabel(carImageMenu[model][color]);
		add(carImage, 0);	
	}
}
