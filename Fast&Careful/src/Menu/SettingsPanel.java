package Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsPanel extends JPanel {
	
	

	File myFile; 
	PrintWriter out;
	Scanner scan;
	//Properties
	//listeners
	ButtonListener buttonListen;
	SliderListener sliderListen;
	//Buttons
	JButton back;
	JButton help;
	JButton done;
	//Panels
	JPanel p;
	JPanel p2;
	//Sliders
	JSlider density;
	JSlider difficulty;
	JSlider sound;
	//JLabels
	JLabel[] labels;
	
	
	
	//Variables
	int densityMin = 0;
	int densityMax = 10;
	int densityLevel;
	int stableDensityLevel;
	int difficultyMin = 0;
	int difficultyMax = 10;
	int difficultyLevel;
	int stableDifficultyLevel;
	int soundMin = 0;
	int soundMax = 10;
	int soundLevel;
	int stableSoundLevel;
	Entrance enter;
	
	public SettingsPanel(Entrance enter) throws FileNotFoundException {
		this.enter = enter;
		System.err.println(requestFocusInWindow());
		myFile = new File("settings.txt");
		scan = new Scanner(myFile);
		densityLevel = Integer.parseInt(scan.nextLine());
		stableDensityLevel = densityLevel;
		difficultyLevel = Integer.parseInt(scan.nextLine());
		stableDifficultyLevel = difficultyLevel;
		soundLevel = Integer.parseInt(scan.nextLine());
		stableSoundLevel = soundLevel;
		scan.close();
		
		sliderListen = new SliderListener();
		buttonListen = new ButtonListener();
		back = new JButton("Back");
		back.addActionListener(buttonListen);
		help = new JButton("Help");
		help.addActionListener(buttonListen);
		done = new JButton("Done");
		done.addActionListener(buttonListen);
		
		setLayout(new BorderLayout());
		
		p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(back,BorderLayout.WEST);
		p.add(help,BorderLayout.CENTER);
		p.add(done,BorderLayout.EAST);
		p.setBackground(Color.orange);
		
		add(p,BorderLayout.SOUTH);
		
		labels = new JLabel[4];
		
		labels[0] = new JLabel("Density level");
		density = new JSlider(JSlider.HORIZONTAL,densityMin,densityMax,densityLevel);
		density.setMajorTickSpacing(1);
		density.setMinorTickSpacing(0);
		density.setPaintTicks(true);
		density.setPaintLabels(true);
		density.setForeground(Color.red);
		density.addChangeListener(sliderListen);
		density.setBackground(Color.orange);
		density.setOpaque(false);

		labels[1] = new JLabel("Difficulty level");
		difficulty = new JSlider(JSlider.HORIZONTAL,difficultyMin,difficultyMax,difficultyLevel);
		difficulty.setMajorTickSpacing(1);
		difficulty.setMinorTickSpacing(0);
		difficulty.setPaintTicks(true);
		difficulty.setPaintLabels(true);
		difficulty.setForeground(Color.MAGENTA);
		difficulty.addChangeListener(sliderListen);
		difficulty.setBackground(Color.orange);
		difficulty.setOpaque(false);

		labels[2] = new JLabel("Sound level");
		sound = new JSlider(JSlider.HORIZONTAL,soundMin,soundMax,soundLevel);
		sound.setMajorTickSpacing(1);
		sound.setMinorTickSpacing(0);
		sound.setPaintTicks(true);
		sound.setPaintLabels(true);
		sound.setForeground(Color.blue);
		sound.addChangeListener(sliderListen);
		sound.setBackground(Color.orange);
		sound.setOpaque(false);
		
		labels[3] = new JLabel("");
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(4,2));
		
		p2.add(labels[0]);
		p2.add(labels[1]);
		p2.add(density);
		p2.add(difficulty);
		p2.add(labels[2]);
		p2.add(labels[3]);
		p2.add(sound);
		p2.setOpaque(false);
		
		
		add(p2,BorderLayout.CENTER);
		
		labels[0].setForeground(Color.black);
		labels[1].setForeground(Color.black);
		labels[2].setForeground(Color.black);
		labels[3].setForeground(Color.black);
		
	}
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("desert.jpg"), 0, 0, null);
		repaint();
	}
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if( e.getSource() == back){
				enter.back();
			}
			else if(e.getSource() == help){
				
			}
			else if(e.getSource() == done){
				enter.mainSound.setVolume(soundLevel);
				try {
					out = new PrintWriter(myFile);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				stableDensityLevel = densityLevel;
				stableDifficultyLevel = difficultyLevel;
				stableSoundLevel = soundLevel;
				out.println(densityLevel);
				out.println(difficultyLevel);
				out.println(soundLevel);
				out.close();
				enter.back();
			}
			
		}
	}
	
	private class SliderListener implements ChangeListener{
		
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() == density){
				densityLevel = density.getValue();
			}
			else if(e.getSource() == difficulty){
				difficultyLevel = difficulty.getValue();
			}
			else if(e.getSource() == sound){
				soundLevel = sound.getValue();
			}
		}
	}
	
	

}
