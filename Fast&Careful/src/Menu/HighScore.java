package Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class HighScore extends JPanel {
	
	File myFile;
	Scanner scan;
	ButtonListener buttonListener;
	JLabel[] labels;
	final int PLAYERS = 10;
	JPanel p;
	JPanel p2;
	JPanel bottom;
	JButton back,help;
	String str;
	Border border;
	
	
	Entrance enter;
	
	public HighScore(Entrance enter) throws FileNotFoundException {
		this.enter = enter;
		myFile = new File("HighScore.txt");
		scan = new Scanner(myFile);
		border = LineBorder.createGrayLineBorder();
		
		setLayout(new BorderLayout());
		setOpaque(false);
		
		p = new JPanel();
		p.setLayout(new GridLayout(PLAYERS/2,2));
		p.setOpaque(false);
		
		labels = new JLabel[PLAYERS];
		for(int i = 0; i < labels.length; i++){
			str = scan.nextLine();
			String[] s = str.split("/");
			labels[i] = new JLabel((i+1) + ". Player have " + s[0] + " points. Player name is : " + s[1]);
			labels[i].setBorder(border);
			p2 = new JPanel();
			p2.add(labels[i]);
			p.add(p2);
			p2.setOpaque(false);
		}
		scan.close();
		add(p,BorderLayout.CENTER);
		
		buttonListener = new ButtonListener();
		bottom = new JPanel();
		back = new JButton("Back");
		back.addActionListener(buttonListener);
		help = new JButton("Help");
		help.addActionListener(buttonListener);
		bottom.setLayout(new BorderLayout());
		bottom.add(back, BorderLayout.WEST);
		bottom.add(help, BorderLayout.EAST);
		bottom.setBackground(Color.white);
		
		add(bottom,BorderLayout.SOUTH);
		
	}
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("menu/high.jpg"), 0, 0, null);
		repaint();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if( e.getSource() == back){
				enter.back();
			}
			else if(e.getSource() == help){
				
			}
		}
	}
	
}
