package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.ImageIcon;


public class BoxController implements IGameView {
	//properties
	GameModel model;
	
	// deadBoxes will help to remove the boxes which will not be used anymore
	ArrayList<ToolModel> deadBoxes;
	ArrayList<ToolModel> deadPickedBoxes;
	
	
	// Constructor
	public BoxController (GameModel model) {
		this.model = model;
					
	}
	
	@Override
	public void updateView(GameModel model) {
		if (model.isStarted()){
			
			//declaring and arrayList in function to updating cars
			deadBoxes = new ArrayList<ToolModel>();
			
			
			// filling dead boxes array 
			for (ToolModel b : this.model.getBoxes())
				if (b.getY() < -200 || b.getY() > 2000 )
					deadBoxes.add(b);
			
			// deciding the lane of the random generated box
			int x = model.getRoadx1() + ((int)(Math.random()*10) % model.getLaneNumber()) * model.getLaneWidth() ;
			
			//generating the box to the box arrayList
			if ( Math.round( model.getFlowSpeed() ) != 0 ){
				if ( ((int)(Math.random() * 100) % (model.getBoxDensity()) == 0) && model.isStarted() ){
					ToolModel b = new ToolModel(model,"box",60,60,x,-100,0,10,.01,0,3,new ImageIcon("box.png"));
					b.setTopSpeed(Math.random()*0);
					
					//adding to the boxes arrayList
					if (model.getBoxes().size() < 2){
						model.getBoxes().add(b);
					}
						
				}
			}
			for (ToolModel b : this.model.getBoxes()){
				b.update();
				
			}
			
			//adding pickedboxes
			for(ToolModel b : model.getBoxes()){
				
				if( model.collide(b,model.getMyCar()) && (model.getPickedBoxes().size() < 3) ){
					model.getPickedBoxes().add(b);
					model.setBottomText("You got a box!");
					b.startTimer(1000, b);
					b.setLifeTime(15);
					deadBoxes.add(b);
				}
				
			}
			
			
			
			//removing dead boxes
			for (ToolModel b : deadBoxes)
				model.getBoxes().remove(b);
		}
	}
	
	public ArrayList<ToolModel> getBoxes(){
		return this.model.getBoxes();
	}
	
	

}
