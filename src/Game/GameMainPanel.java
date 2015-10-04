package Game;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import Menu.Entrance;
import Menu.MainFrame;


public class GameMainPanel extends JPanel implements IGameView{
	
	//Properties
	
	GameModel model;
	GameTopPanelView tpView;
	GameBottomPanelView bpView;
	public GameCenterPanelView cpView;
		
	//Constructor
	public GameMainPanel(GameModel model,Entrance enter) throws FileNotFoundException{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
		setBackground(Color.green);
		
		this.model = model;
		tpView = new GameTopPanelView(model);
		bpView = new GameBottomPanelView(model);
		cpView = new GameCenterPanelView(model,enter);
		
		
		
		add(tpView, BorderLayout.NORTH);
		add(bpView, BorderLayout.SOUTH);
		add(cpView, BorderLayout.CENTER);
		
		
		model.addView(tpView);
		model.addView(bpView);
		model.addView(cpView);
		
	}

	@Override
	public void updateView(GameModel model) {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	} 
	

}
