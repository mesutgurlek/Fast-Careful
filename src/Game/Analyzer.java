package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;


public class Analyzer implements ActionListener {

	GameModel model;
	final int MAX_VEL_DIFFER = 2; 
	Timer t;
	
	public Analyzer(GameModel model){
		this.model = model;
		t = new Timer(1000, this);
		t.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String screenText = "", bottomText = "";	
		ToolModel me = model.getMyCar();
		
		//looking for collides
		for (ToolModel v : model.getVehicles()){
			if ( model.collide(v, me) && Math.abs(v.getSpeedY()-v.getTopSpeed()-me.getSpeedY()) < MAX_VEL_DIFFER ){
				model.setPoints(model.getPoints() - 1000);
				screenText = "-1000";
				bottomText = "Do NOT hit the other vehicles";
				System.out.println("collide");
			}else if(model.collide(v, me) && Math.abs(v.getSpeedY()-v.getTopSpeed()-me.getSpeedY()) >= MAX_VEL_DIFFER){
				screenText = "The End";
				bottomText = "The game is end";
				System.out.println("big !!!!!!! collide");
			}
			if(model.collide(v, me))
				System.out.println("collide!!!!");
		}
		model.setBottomText(bottomText);
		System.out.println(bottomText);
		//looking for 
	}

}
