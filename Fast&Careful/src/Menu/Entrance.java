package Menu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

import Game.SoundCreator;



public class Entrance extends JPanel implements ActionListener{
	

	File myFile;
	Scanner scan;
	public ArrayList<JPanel> panels;
	MenuPanel menuPanel;
	SettingsPanel settings;
	HighScore highScore;
	//Selections selection;
	public CarSelectionPanel carSelection;
	public MapSelectionPanel mapSelection;
	
	int volume;
	
	Timer time;
	
	//musics
	public SoundCreator mainSound;
	
	
	int x;
	int y;
	
	public Entrance(int x, int y) throws FileNotFoundException {
		myFile = new File("settings.txt");
		scan = new Scanner(myFile);
		scan.nextLine();
		scan.nextLine();
		volume = Integer.valueOf(scan.nextLine());
		scan.close();
		//musics
		mainSound = new SoundCreator("menu.wav", volume);
		mainSound.start();
		
		
		panels = new ArrayList<JPanel>();
		setLayout(new BorderLayout());
		this.x = x;
		this.y = y;
		setPreferredSize(new Dimension(x,y));
		
		menuPanel = new MenuPanel(this);
		carSelection = new CarSelectionPanel(this);
		mapSelection = new MapSelectionPanel(this);
		
		panels.add(menuPanel);
		add(panels.get(0),BorderLayout.CENTER);
		
		time = new Timer(1000, this);
		time.start();
	}
	
	public void changePanel(JPanel panel){
		remove(panels.get(panels.size()-1));
		panels.add(panel);
		add(panels.get(panels.size()-1));
		//System.out.println(i + ". panel " + panel.requestFocusInWindow());
		validate();
		repaint();
	}
	public void back(){
		if(panels.size() > 1){
			remove(panels.get(panels.size()-1));
			panels.remove(panels.size()-1);
			add(panels.get(panels.size()-1));
			validate();
			repaint();
		}
		else{
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!mainSound.isRunning()){
			mainSound.stop();
			mainSound.changeMusic(mainSound.file);
			mainSound.start();
		}
		
	}
	

}
