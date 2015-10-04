package Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import Game.*;




public class CarSelectionPanel extends JPanel {
	
	// VARIABLES
	ToolModel myCar; 
	// ToolModel array to store objects which refer to cars
	ToolModel[][] carModels;
	// ImageIcon array to store car's images which are used in the game ([model][color])
	ImageIcon[][] carIcons;
	// about cars
	CarImagePanel carAndInfo;
	// Panels to arrange 
	private JPanel leftSide;
	private JPanel rightSide;
	private JPanel upSide;
	private JPanel center;
	private JPanel bottom;
	private JPanel palette;
	
	int modelNo; // Default model no
	int colorNo; // Default color no
	
	// Color Selection
	JButton[] colorButtons;
	
	
	// General View
	private JLabel panelName;
	
	//buttons
	private JButton arrowRight;
	private JButton arrowLeft;
	private JButton selectAndStart;
	private JButton back;
	private JButton help;

	private Listener listen;
	private Entrance enter;
	private JFrame helpFrame;
	
	public CarSelectionPanel (Entrance enter) {
		
		this.enter = enter;
		listen = new Listener();
		// help Frame and text
		JTextArea helpInfo = new JTextArea();
		helpInfo.setEditable(false);
		helpInfo.setFont(new Font(getName(), Font.BOLD, 20));
		helpInfo.setText("Vehicle could be selected with using this menu. Car \ncould be changed with color buttons and also with arrows"
				+ "\n After the selection, game will start.");
		helpFrame = new JFrame("Help");
		helpFrame.getContentPane().add(helpInfo);
		helpFrame.pack();
		helpFrame.setLocation(300, 300);
		
		//Image Icon Array
		carIcons = new ImageIcon[4][6];
		for (int model = 0; model < 4; model++) {
			for (int color = 0; color < 6; color++ ) {
				// filling car Icon array with icons
				carIcons[model][color] = new ImageIcon(("vehicles/myCars/" + (model + 1) + (color + 1) + ".png"));
			}
		}
		
		// car array
		carModels = new ToolModel[4][6];
		// filling carModels with using carIcons
		for (int i = 0; i < 6; i++) {
			carModels[0][i] = new ToolModel(null, "Lexus LFA 16", 40, 80, 0, 0, 0, 0, .30, 310, 11, carIcons[0][i]);
			carModels[1][i] = new ToolModel(null, "Lamborghini LP670-4 SV", 40, 80, 0, 0, 0, 0, .32, 320, 13, carIcons[1][i]);
			carModels[2][i] = new ToolModel(null, "Audi RS 4 Avant", 40, 80, 0, 0, 0, 0, .20, 255, 10, carIcons[2][i]);
			carModels[3][i] = new ToolModel(null, "Ferrari-f355", 40, 80, 0, 0, 0, 0, .27, 290, 13, carIcons[3][i]);
			
					
		}

		
		// Center Panel which store car and info
		carAndInfo = new CarImagePanel();	
		// Default values
		int modelNo = 0; // Default model no
		int colorNo = 0; // Default color no
		carAndInfo.setText(carModels[modelNo][colorNo].toString());
		myCar = carModels[modelNo][colorNo]; // default car
		
		
		
		// about main panel
		setBackground(Color.cyan);
		setPreferredSize(new Dimension(1024, 768));
		setLayout(new BorderLayout());
		
		// panel name
		upSide = new JPanel();
		panelName = new JLabel("Car Selection");
		panelName.setFont(new Font(getName(), Font.BOLD, 20));
		panelName.setForeground(Color.BLUE);
		upSide.add(Box.createRigidArea(new Dimension(1024, 2)));
		upSide.add(panelName);
		upSide.add(Box.createRigidArea(new Dimension(1024, 40)));
		add(upSide, BorderLayout.NORTH);
		
		
		
		
		// implementing buttons and adding Listener
		
		// Color buttons
		palette = new JPanel(new GridLayout(12,1));
		//palette.setLayout(new FlowLayout());
		palette.setPreferredSize(new Dimension(50,350));
		colorButtons = new JButton[6];
		
		
		for (int i = 0; i < 6; i++) {	
			colorButtons[i] = new JButton();
			//rightSide.add(colorButtons[i]);
			colorButtons[i].addActionListener(listen);
			palette.add(colorButtons[i]);
		}
		colorButtons[0].setBackground(Color.red);
		colorButtons[1].setBackground(Color.yellow);
		colorButtons[2].setBackground(Color.white);
		colorButtons[3].setBackground(Color.black);
		colorButtons[4].setBackground(Color.blue);
		colorButtons[5].setBackground(Color.pink);
		
		
		arrowLeft = new JButton("left");
		arrowRight = new JButton("right");
		arrowLeft.addActionListener(listen);
		arrowRight.addActionListener(listen);
		
		//select and start button
		bottom = new JPanel(new BorderLayout());
		selectAndStart = new JButton("Select And Start");
		selectAndStart.addActionListener(listen);
		help = new JButton("help");
		help.addActionListener(listen);
		back = new JButton("back");
		back.addActionListener(listen);
		bottom.add(back, BorderLayout.WEST);
		bottom.add(selectAndStart, BorderLayout.CENTER);
		bottom.add(help, BorderLayout.EAST);
		add(bottom, BorderLayout.SOUTH);
		
		
		//RÝGHT
		rightSide = new JPanel();
		rightSide.add(Box.createRigidArea(new Dimension(0, 350)));
		rightSide.add(arrowRight);
		rightSide.add(Box.createRigidArea(new Dimension(0, 768)));
		add(rightSide, BorderLayout.EAST);
		
		//LEFT
		leftSide = new JPanel();
		leftSide.add(Box.createRigidArea(new Dimension(0, 350)));
		leftSide.add(arrowLeft, BorderLayout.WEST);
		leftSide.add(Box.createRigidArea(new Dimension(0, 768)));
		add(leftSide, BorderLayout.WEST);
		
		// center
		center = new JPanel(new BorderLayout());
		center.add(carAndInfo, BorderLayout.CENTER);
		center.add(palette, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
		
	
	}
	
	// getter to return myCar
	public ToolModel getMyCar() {
		return myCar;
	}
	
	public class Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == selectAndStart) {
				// changing myCar if user click sstart button
				myCar = carModels[modelNo][colorNo];
				BasicGameSetup.setMyCar(myCar);
				IGameSetup basicSetup = null;
				GameModel model;
				GameMainPanel panel = null;
				helpFrame.dispose();
				
				try {
					basicSetup = new BasicGameSetup();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				model = new GameModel(basicSetup);
				try {
					panel = new GameMainPanel(model,enter);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				model.addView(panel);
				enter.changePanel(panel);
				//switching sound
				enter.mainSound.stop();
				if(model.getMap().getMapName().equals("city")){
					enter.mainSound.changeMusic(enter.mainSound.citys);
				}
				else if(model.getMap().getMapName().equals("highway")){
					enter.mainSound.changeMusic(enter.mainSound.deserts);
				}
				else if(model.getMap().getMapName().equals("rural")){
					enter.mainSound.changeMusic(enter.mainSound.highways);
				}
				enter.mainSound.start();
				
				
				panel.cpView.requestFocusInWindow();
			}
			else if(e.getSource() == back){
				enter.back();
			}
			else if(e.getSource() == help){
				helpFrame.setVisible(true);
			}
			center.remove(carAndInfo);
			if (e.getSource() == colorButtons[0]) {
				colorNo = 0;
			}	
			else if (e.getSource() == colorButtons[1]) {
				colorNo = 1;
			}
			else if (e.getSource() == colorButtons[2]) {
				colorNo = 2;
			}
			else if (e.getSource() == colorButtons[3]) {
				colorNo = 3;
			}
			else if (e.getSource() == colorButtons[4]) {
				colorNo = 4;	
			}
			else if (e.getSource() == colorButtons[5]) {		
				colorNo = 5;
			}
			else if (e.getSource() == arrowRight) {
				if ( modelNo < 3)
					modelNo++;
				else
					modelNo = 0;
			}
			
			else if (e.getSource() == arrowLeft) {
				if ( modelNo > 0)
					modelNo--;
				else
					modelNo = 3;
			}
			carAndInfo.changeCar(modelNo, colorNo);
			carAndInfo.setText(carModels[modelNo][colorNo].toString());;
			center.add(carAndInfo, BorderLayout.CENTER);
			center.validate();	
			repaint();
		}
		
	}

}
