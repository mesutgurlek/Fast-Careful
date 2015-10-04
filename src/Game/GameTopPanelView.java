package Game;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

public class GameTopPanelView extends JPanel implements IGameView, ActionListener{
	
	//properties
	private JLabel score, time, boxEnvanter;
	private Font font;
	private Timer clock;
	private GameModel model;
	
	public GameTopPanelView(GameModel model) {
		this.model = model;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.white);
		setPreferredSize(new Dimension(model.getWidth(), model.getHeight()/20));
		
		score = new JLabel("Score: " + model.getPoints());
		time = new JLabel("Time " + model.getHour() + ":" + model.getMinute() + ":" + model.getSecond());
		boxEnvanter = new JLabel("", new ImageIcon("minibox.png"), SwingConstants.CENTER);
		
		
		clock = new Timer(1000, this);
		clock.start();
		//model.getTimes().add(clock);
		model.setClock(clock);

		font = new Font("Serif", Font.PLAIN, 18);
		score.setFont(font);
		time.setFont(font);
		add(score);
		add(Box.createHorizontalGlue());
		add(boxEnvanter);
		add(Box.createHorizontalGlue());
		add(time);	
		
	}
	
	public void updateView(GameModel model) {
		if (model.isStarted()){
			
			String minute,second;
			if(model.getSecond() < 10){
				second = ":0" + model.getSecond();
			}
			else{
				second = ":" + model.getSecond();
			}
			if(model.getMinute() < 10){
				minute = ":0" + model.getMinute();
			}
			else{
				minute = ":" + model.getMinute();
			}
			
			//setting the label text
			String str = "";
			for (ToolModel b : model.getPickedBoxes()){
				str += " | " + b.getLifeTime() + " | "; 
			}
			boxEnvanter.setText(str);
			
			//increasing points
			model.setPoints((int) (model.getPoints()+model.getMyCar().getSpeedY()));
			time.setText("Time " + model.getHour() + minute + second);
			score.setText("Score: " + model.getPoints());	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		model.setSecond(model.getSecond()+1);	
		if (model.getSecond() % 60 == 0 && model.getSecond() != 0){
			model.setMinute(model.getMinute() + 1);
			model.setSecond(model.getSecond() % 60);
		}
		if(model.getMinute() % 60 == 0 && model.getMinute() != 0){
			model.setHour(model.getHour() + 1);
			model.setMinute(model.getMinute() % 60);
		}
		//starting the game after action listener
		model.setStarted(true);
		
	}
	
	public double round(double value) {
        int decimalPlace = 2;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
        return bd.doubleValue();
    } 

	
	
	

}
