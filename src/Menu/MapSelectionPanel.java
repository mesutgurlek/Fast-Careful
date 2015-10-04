package Menu;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Game.BasicGameSetup;
import Game.Engine;
import Game.Map;




public class MapSelectionPanel extends JPanel {
	
	// Maps
	Map[] mapList;
	Map myMap;
	
	// about cars
	MapImagePanel mapAndInfo;

	private JPanel leftSide;
	private JPanel rightSide;
	private JPanel upSide;
	private JPanel center;
	private JPanel bottom;
	
	int mapNo = 0;
	
	// option pane for help
	JFrame helpFrame;
	
	
	
	// General View
	private JLabel panelName;
	
	//buttons
	private JButton arrowRight;
	private JButton arrowLeft;
	private JButton select;
	private JButton back;
	private JButton help;

	Entrance enter;
	
	public MapSelectionPanel (Entrance entrance) {
		this.enter = entrance;
		
		// help Frame and text
		JTextArea helpInfo = new JTextArea();
		helpInfo.setEditable(false);
		helpInfo.setFont(new Font(getName(), Font.BOLD, 20));
		helpInfo.setText("A map could be selected with using this menu when the menu \n changes, background and music will change too"
				+ "\n map could change with using left and right arrows" +  "\n After this panel car selection will open");
		helpFrame = new JFrame("Help");
		helpFrame.getContentPane().add(helpInfo);
		helpFrame.pack();
		helpFrame.setLocation(300, 300);
		// maps
		mapList = new Map[3];
		
		mapList[0] = new Map(null, 120, 4, false, "highway", MainFrame.WIDTH/3, MainFrame.WIDTH*2/3);
		mapList[1] = new Map(null, 110, 4, true, "rural", MainFrame.WIDTH/3, MainFrame.WIDTH*2/3);
		mapList[2] = new Map(null, 90, 4, true, "city", MainFrame.WIDTH/3, MainFrame.WIDTH*2/3);
		
		// about main panel
		setBackground(Color.cyan);
		setPreferredSize(new Dimension(1024, 768));
		setLayout(new BorderLayout());
		// panel name
		upSide = new JPanel();
		panelName = new JLabel("Map Selection");
		panelName.setFont(new Font(getName(), Font.BOLD, 20));
		panelName.setForeground(Color.BLUE);
		upSide.add(Box.createRigidArea(new Dimension(1024, 2)));
		upSide.add(panelName);
		upSide.add(Box.createRigidArea(new Dimension(1024, 40)));
		add(upSide, BorderLayout.NORTH);
		
		
		// Panel for map and info
		mapAndInfo = new MapImagePanel();
		mapAndInfo.setText(mapList[0].toString()); // default text
			
		
		// implementing buttons and adding Listener
		arrowLeft = new JButton("left");
		arrowRight = new JButton("right");
		arrowLeft.addActionListener(new Listener());
		arrowRight.addActionListener(new Listener());
		
		//select and start button
		bottom = new JPanel(new BorderLayout());
		select = new JButton("Select");
		help = new JButton("help");
		back = new JButton("back");
		back.addActionListener(new Listener());
		help.addActionListener(new Listener());
		select.addActionListener(new Listener());
		bottom.add(back, BorderLayout.WEST);
		bottom.add(select, BorderLayout.CENTER);
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
		center.add(mapAndInfo, BorderLayout.CENTER);
		add(center, BorderLayout.CENTER);
		
	}
			
	public class Listener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//select
			if (e.getSource() == select) {
				myMap = mapList[mapNo];
				BasicGameSetup.setMyMap(myMap);
				enter.changePanel(enter.carSelection);
				helpFrame.dispose();
			}
			// back
			if( e.getSource() == back){
				enter.back();
			}
			if (e.getSource() == help)
				helpFrame.setVisible(true);
			// remove before change panel
			center.remove(mapAndInfo);
			
			// changing mapNo
			if (e.getSource() == arrowRight) {
				if ( mapNo < 2)
					mapNo++;
				else
					mapNo = 0;
			}
			
			else if (e.getSource() == arrowLeft) {
				if ( mapNo > 0)
					mapNo--;
				else
					mapNo = 2;
			}
			// implementing these changes to screen
			mapAndInfo.changeMap(mapNo);
			mapAndInfo.setText(mapList[mapNo].toString());
			center.add(mapAndInfo, BorderLayout.CENTER);
			center.validate();	
			repaint();
		}
		
	}

}
