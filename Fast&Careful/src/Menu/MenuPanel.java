package Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JPanel;

import sun.audio.AudioStream;
import Game.SoundCreator;


public class MenuPanel extends JPanel{
	
	Entrance enter;
	JButton[] buttons;
	AcListener actionLisen;
	JPanel panel;
	int x;
	int y;
	
	public MenuPanel(Entrance enter) {
		this.enter = enter;
		
		setFocusable(true);
		x = enter.x;
		y = enter.y;
		setPreferredSize(new Dimension(x,y));

		setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		actionLisen = new AcListener();
		buttons = new JButton[4];
		buttons[0] = new JButton("Play");
		buttons[0].setBackground(Color.gray);
		buttons[1] = new JButton("Settings");
		buttons[1].setBackground(Color.gray);
		buttons[2] = new JButton("High Score");
		buttons[2].setBackground(Color.gray);
		buttons[3] = new JButton("Exit");
		buttons[3].setBackground(Color.gray);
		setOpaque(false);
		
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(x/5,y/5));
		p.setBackground(Color.orange);
		add(p,BorderLayout.NORTH);
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(x/5,y/5));
		p1.setBackground(Color.orange);
		add(p1,BorderLayout.SOUTH);
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(2*x/5,y/5));
		p2.setBackground(Color.orange);
		add(p2,BorderLayout.EAST);
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(2*x/5,y/5));
		p3.setBackground(Color.orange);
		add(p3,BorderLayout.WEST);
		p.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		
		buttons[0].setSize(150, 50);
		buttons[0].setLocation(20, 180);
		buttons[1].setSize(150, 50);
		buttons[1].setLocation(20, 250);
		buttons[2].setSize(150, 50);
		buttons[2].setLocation(20, 320);
		buttons[3].setSize(150, 50);
		buttons[3].setLocation(20, 390);
		
		
		
		
		for( int i = 0; i < buttons.length; i++){
			buttons[i].addActionListener(actionLisen);
			panel.add(buttons[i]);
		}
		add(panel,BorderLayout.CENTER);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("menu/main.jpg"), 0, 0, null);
		repaint();
	}
	
	
	
	
	private class AcListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if( e.getSource() == buttons[3]){
				enter.back();
			}
			else if(e.getSource() == buttons[0]){
				enter.changePanel(enter.mapSelection);
			}
			else if( e.getSource() == buttons[1]){
				setFocusable(false);
				try {
					enter.changePanel(new SettingsPanel(enter));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == buttons[2]){
				try {
					enter.changePanel(new HighScore(enter));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
}
