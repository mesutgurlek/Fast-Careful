package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TrafficRuleController implements IGameView {

	ArrayList<ToolModel> deadTrafficObjects, newObjects;
	GameModel model;
	
	//constructor
	public TrafficRuleController(GameModel model){
		this.model = model;
	}
	
	
	public void updateView(GameModel model) {
		
		if(model.isStarted()){
			
			//declaring and arrayList to update traffic objects
			deadTrafficObjects = new ArrayList<ToolModel>();
			newObjects = new	ArrayList<ToolModel>();
			
						
			//generating traffic signs for the trafficObjects
			if ( Math.round( model.getFlowSpeed() ) != 0 && model.getMap().getMapName() != "highway" ){
				// deciding the coordinates of the random generated trafficObjects
				int x = model.getRoadx2();
				if ( /*((int)(Math.random() * 100000) % (model.getBoxDensity()) == 0) &&*/ model.isStarted() ){
					int objectNumber = 1 + (int)(Math.random()*3);
					String str = "trafficSigns/" + objectNumber + ".png";
					ToolModel o = new ToolModel(model,"trafficSign",40,80,x,-100,0,10,.01,5,3,new ImageIcon((str)));
					o.setTopSpeed(0);
					o.setLifeTime(5);
									
					// if there is no collide, it could be added
					if ( !isCollide(o, model.getTrafficObjects()) && model.getTrafficObjects().size() < 1 ){
						model.getTrafficObjects().add(o);
					}
				}
			}
			
			//generating speedLimiters for the trafficObjects
			if ( Math.round( model.getFlowSpeed() ) != 0 ){
				// deciding the coordinates of the random generated speedLimiter
				int x = model.getRoadx2();
				if ( ((int)(Math.random() * 100000) % (model.getBoxDensity()) == 0) && model.isStarted() ){
					int objectNumber;
					if (model.getMap().getMapName() == "city")
						objectNumber = 1;
					else if(model.getMap().getMapName() == "rural")
						objectNumber = 2;
					else
						objectNumber = 3;
					String str = "speedSigns/" + objectNumber + ".png";
					ToolModel o = new ToolModel(model,"trafficSign",40,80,x,-100,0,10,.01,5,3,new ImageIcon((str)));
					o.setTopSpeed(0);
					o.setLifeTime(5);
									
					// if there is no collide, it could be added
					if ( !isCollide(o, model.getTrafficObjects()) && model.getTrafficObjects().size() < 1 ){
						model.getTrafficObjects().add(o);
					}
				}
			}
			
			
			
			//generating the successive traffic signs
			for (ToolModel o : model.getTrafficObjects()){
				
				//generating trafficLambs for the trafficObjects according to traffic signs
				if ( o.getImage().getDescription().contains("trafficSigns/1.png") && o.isActive4points()){
					
					// deactivating the object which causes lamp
					o.setActive4points(false);
					
					// deciding the coordinates of the random generated traffic lamp
					int x = model.getRoadx2();
					
					int objectNumber = 1 + (int)(Math.random()*3);
					String str = "trafficLambs/" + objectNumber + ".png";
					
					//generating the lamp after 500 pixels from the sign
					ToolModel l = new ToolModel(model,"trafficLamb",40,80,x,o.getY()-500,0,10,.01,5,3,new ImageIcon((str)));
					l.setTopSpeed(0);
					//if yellow set life time 2 seconds, else 10 seconds
					if (l.getImage().toString().contains("2.png"))
						l.setLifeTime(2);
					else
						l.setLifeTime(5);
					l.startTimer(1000, l);
					
					//adding traffic lamp to the arrayList 
					newObjects.add(l);
					
				}
				//generating pedestrian roads for the trafficObjects according to traffic signs
				else if(o.getImage().getDescription().contains("trafficSigns/2.png") && o.isActive4points()){
					
					// deactivating the object which causes pedestrian
					o.setActive4points(false);
					
					// deciding the coordinates of the random generated pedestrian road
					int x = model.getRoadx1();
					
					String str = "pedestrians/yaya1.gif";
					
					//generating the pedestrian road after 500 pixels from the sign
					ToolModel p = new ToolModel(model,"pedestrian",40,80,328,o.getY()-500,0,10,.01,5,3,new ImageIcon((str)));
					p.setTopSpeed(0);
					p.setLifeTime(3);
					p.startTimer(1000, p);				
					
					//adding pedestrian to the arrayList
					newObjects.add(p);	
					
				}
				
				//generating cross road for the trafficObjects according to traffic signs
				else if (o.getImage().getDescription().contains("trafficSigns/3.png") && o.isActive4points()){
					
					// deactivating the object which causes pedestrian
					o.setActive4points(false);
					
					// deciding the coordinates of the cross road
					int x = 0;
					ToolModel c = new ToolModel(model,"road",40,80,0,o.getY()-500,0,10,.01,5,3,new ImageIcon("horizontal_road.png"));
					c.setTopSpeed(0);
					c.setLifeTime(3);
					
					//adding cross road to the arrayList
					newObjects.add(c);		
				}
				
				/*// horizontal car for the cross road
				if (o.isActive4points() && o.getImage().getDescription().contains("horizontal_road")){
					
					String str = "vehicles/horizontal_car.png";
											
					ToolModel c = new ToolModel(model,"car",80,40,o.getX(),o.getY(),5,0,.01,5,3,new ImageIcon((str)));
					c.setTopSpeed(0);	
					c.setLifeTime(5);
					model.getVehicles().add(c);
					System.out.println("cross road car added: " +  c.isDead() + "coordinates:" + model.getWidth() + "-" + o.getY());
					o.setActive4points(false);
					
				}*/
			}
			
											
			//updating trafficObjects
			for (ToolModel o : this.model.getTrafficObjects()){
				o.update();
			}
						
			// filling dead trafficObjects array 
			for (ToolModel v : model.getTrafficObjects()){
				if (v.getY() < -1000 || v.getY() > 2000 || v.getX() < -1000 || v.getX() > 2000 )
					deadTrafficObjects.add(v);
				if (v.isDead()){
										
					//if dead light is red, generate an yellow Light
					if (v.getImage().getDescription().contains("trafficLambs/1.png") ){
						String str = "trafficLambs/2.png";
						ToolModel o = new ToolModel(model,"trafficLamb",40,80,v.getX(),v.getY(),0,10,.01,5,3,new ImageIcon((str)));
						o.setTopSpeed(0);
						//set life time 2 second for yellow light
						o.setLifeTime(2);
						o.startTimer(1000, o);
						newObjects.add(o);
						System.out.println("red light dead");
					}//if dead light is yellow, generate a green light
					else if (v.getImage().getDescription().contains("trafficLambs/2.png")){
						String str = "trafficLambs/3.png";
						ToolModel o = new ToolModel(model,"trafficLamb",40,80,v.getX(),v.getY(),0,10,.01,5,3,new ImageIcon((str)));
						o.setTopSpeed(0);
						//set life time 5 second for green light
						o.setLifeTime(5);
						o.startTimer(1000, o);
						newObjects.add(o);
						System.out.println("yellow light dead");
					}//if dead light is green generate red light
					else if(v.getImage().getDescription().contains("trafficLambs/3.png")){
						String str = "trafficLambs/1.png";
						ToolModel o = new ToolModel(model,"trafficLamb",40,80,v.getX(),v.getY(),0,10,.01,5,3,new ImageIcon((str)));
						o.setTopSpeed(0);
						//set life time 5 second for red light
						o.setLifeTime(5);
						o.startTimer(1000, o);
						newObjects.add(o);
						System.out.println("green light dead");
					}
					
					
					//if there is pedestrians on the road generate new pedestrian road without people
					if(v.getImage().getDescription().contains("yaya1")){
						String str = "pedestrians/yaya2.gif";
						ToolModel o = new ToolModel(model,"pedestrian",40,80,v.getX(),v.getY(),0,10,.01,5,3,new ImageIcon((str)));
						o.setTopSpeed(0);
						o.setLifeTime(2);
						
						newObjects.add(o);
					}
					deadTrafficObjects.add(v);
				}
			}
			
			//adding new generated objects to the objects
			for (ToolModel o : newObjects)
				model.getTrafficObjects().add(o);
			
			
			
			//removing dead traffic objects
			for (ToolModel o : deadTrafficObjects)
				model.getTrafficObjects().remove(o);
		}
		
	}
	
	public boolean isCollide(ToolModel v, ArrayList<ToolModel> list){
		for (ToolModel v1 : list)
			if (model.collide(v, v1) && v != v1 )
				return true;
		return false;
	}

}
