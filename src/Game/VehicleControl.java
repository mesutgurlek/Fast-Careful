package Game;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class VehicleControl implements IGameView {

	ArrayList<ToolModel> deadVehicles;
	GameModel model;
	
	//constructor
	public VehicleControl(GameModel model){
		this.model = model;
	}
	
	
	public void updateView(GameModel model) {
		if(model.isStarted())
		{
			//declaring and arrayList in function to updating cars
			deadVehicles = new ArrayList<ToolModel>();
			
			// filling dead vehicle array 
			for (ToolModel v : this.model.getVehicles())
				if (v.getY() < -200 || v.getY() > 2000 )
					deadVehicles.add(v);
			
			//generating the car to the vehicle array
			
			if ( Math.round( model.getFlowSpeed() ) != 0 ){
				if ( (model.getRealTime() % 100 == 0 ) && model.isStarted() ){
					// deciding the lane of the random generated vehicle
					int laneOfVehicle =  (int)(Math.random()*10) % model.getLaneNumber();
					
					//deciding the x position of the vehicle
					int x = model.getRoadx1() + model.getLaneWidth()/2 - 20  + laneOfVehicle * model.getLaneWidth() ;
					
					int vehicleNumber = 1 + (int)(Math.random()*17);
					String str = "vehicles/" + vehicleNumber + ".png";
					
					//change the image if it goes reverse
					if (!model.getOneWay() && laneOfVehicle < 2 )
						str = "vehicles/t" + vehicleNumber + ".png";
					
					ToolModel v = new ToolModel(model,"car",40,80,x,-100,0,5,.01,5,3,new ImageIcon((str)));
					
					//setting the speed of vehicle according to way type and lane number
					if (!model.getOneWay() && laneOfVehicle < 2 )
						v.setTopSpeed( -1 - Math.random()*3 );
					else if (!model.getOneWay() && laneOfVehicle >= 2)
						v.setTopSpeed(1+Math.random()*3);
					else if(model.getOneWay())
						v.setTopSpeed(1+Math.random()*3);
								
					// if there is no collide, it could be added
					if ( !isCollide(v, model.getVehicles()) && model.getVehicles().size() < 5 ){
						model.getVehicles().add(v);
					}
							
				}
			}
		
			
			for (ToolModel v : this.model.getVehicles()){
				v.update();
				
				//if there is collide with my car prevent from back collide
				if ( model.getMyCar().getY() < v.getY() && model.collide(model.getMyCar(), v) ){
					v.setTopSpeed(0);
					v.setSpeedY(0);
					v.setY(v.getY() + 100);
				}
				
				//if there is collide with each other, add it to the deadVehicles
				if( isCollide(v, model.getVehicles()) ){
					deadVehicles.add(v);
				}
				
				
					
				
				//if there is red or yellow light  stop the vehicle
				for (ToolModel o : model.getTrafficObjects()){
					if( (o.getImage().getDescription().contains("trafficLambs/1.png") || o.getImage().getDescription().contains("trafficLambs/2.png")) && Math.abs( (int)(v.getY()) -(int)(o.getY()) ) <= 20  ){
						if (v.getTopSpeed() > 0){
							v.setSpeedY(0);
							v.setTopSpeed(0);
							
						}
					}else if ( o.getImage().getDescription().contains("trafficLambs/3.png") && Math.abs( (int)(v.getY()) -(int)(o.getY()) ) <= 20 ){
						v.setSpeedY(Math.random()*5);
						v.setTopSpeed(Math.random()*2);
					}
				}
				
				//if there are pedestrians on the road stop the vehicle 
				for (ToolModel o : model.getTrafficObjects()){
					if( o.getImage().getDescription().contains("yaya1.gif") && Math.abs( (int)(v.getY()) -(int)(o.getY()) ) <= (v.getSizeY() + o.getSizeY() + 20)  ){
						if (v.getTopSpeed() > 0 ){
							v.setSpeedY(0);
							v.setTopSpeed(0);
						}
					}
				}
				
				//preventing collisions between the vehicles
				for ( ToolModel v2 : model.getVehicles() ){
					if ( Math.abs( (int)(v.getY()) - (int)(v2.getY()) ) <= (v.getSizeY() + 20) && (v.getX() == v2.getX()) ){
						if (Math.abs(v2.getSpeedY() - v2.getTopSpeed()) >= Math.abs(v.getSpeedY() - v.getTopSpeed())  ){
							v2.setTopSpeed(v.getTopSpeed());
							v2.setSpeedY(v.getSpeedY());
						}else{
							v.setTopSpeed(v2.getTopSpeed());
							v.setSpeedY(v2.getSpeedY());
						}

					}
				}
				
				//preventing collisions from back to the my vehicle
				/*if ( (v.getY() - model.getMyCar().getY() <= (v.getSizeY()/2 + model.getMyCar().getSizeY()/2 + 20)) && Math.abs(v.getX()- model.getMyCar().getX()) < model.getMyCar().getSizeX() ){
					v.setSpeedY(0);
					v.setTopSpeed(0);
				}*/
					
				
			}
			
			
			//removing dead Vehicles
			for (ToolModel v : deadVehicles)
				model.getVehicles().remove(v);
		}
	}
	
	public boolean isCollide(ToolModel v, ArrayList<ToolModel> list){
		for (ToolModel v1 : list)
			if (model.collide(v, v1) && v != v1 )
				return true;
		return false;
	}
	
	public ArrayList<ToolModel> getVehicles(){
		return this.model.getVehicles();
	}

}
