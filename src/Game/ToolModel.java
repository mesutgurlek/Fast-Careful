package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class ToolModel implements ActionListener {
	
	//properties
	GameModel model;
	String name;
	double sizeX, sizeY, x, y, speedX, speedY, acc, topSpeed, durability;
	boolean gas, brake, active4points;
	ImageIcon img;
	Timer t;
	int lifeTime;
	int counter = 0;
	
	//constructor
	public ToolModel(GameModel model, String name, double sizeX, double sizeY, double x, double y, double speedX, double speedY, double acc, double topSpeed, double durability, ImageIcon i){
		this.model = model;
		this.name = name;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.acc = acc;
		this.topSpeed = topSpeed;
		this.durability = durability;
		this.img = i;	
		this.gas = false;
		this.brake = false;
		this.active4points = true;
		
	}
	//Setter and getter methods for all properties
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public double getSizeX(){
		return this.sizeX;
	}
	public double getSizeY(){
		return this.sizeY;
		
	}
	public double getSpeedX(){
		return this.speedX;
	}
	public double getSpeedY(){
		return this.speedY;
	}
	public void setSpeedX(double x){
		this.speedX = x;
	}
	public void setX(double x){
		this.x = x;
	}
	public ImageIcon getImage(){
		return this.img;
	}
	public String getName(){
		return this.name;
	}
	public void setTopSpeed(double topSpeed){
		this.topSpeed = topSpeed;
	}
	public GameModel getModel() {
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}

	public double getAcc() {
		return acc;
	}

	public void setAcc(double acc) {
		this.acc = acc;
	}

	public double getDurability() {
		return durability;
	}

	public void setDurability(double durability) {
		this.durability = durability;
	}

	public boolean isGas() {
		return gas;
	}

	public void setGas(boolean gas) {
		this.gas = gas;
	}

	public boolean isBrake() {
		return brake;
	}

	public void setBrake(boolean brake) {
		this.brake = brake;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSizeX(double sizeX) {
		this.sizeX = sizeX;
	}

	public void setSizeY(double sizeY) {
		this.sizeY = sizeY;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	public void startTimer(int delay, ActionListener listener ){
		this.t = new Timer(delay, listener);
		t.start();
	}
	
	public void stopTimer(){
		if (t != null)
			t.stop();
	}
	
	public void continueTimer(){
		t.start();
	}
	
	public Timer getTimer(){
		return this.t;
	}
	
	public int getLifeTime() {
		return lifeTime;
	}
	
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}
	
	public boolean isDead(){
		return (lifeTime <= 0);
	}
	public boolean isActive4points() {
		return active4points;
	}
	public void setActive4points(boolean active4points) {
		this.active4points = active4points;
	}
	public String toString() {
		String str = "\n\nModel: " + name + "\n\nTopSpeed: " + topSpeed + "\n\nAcceliration Coefficient: " + (acc * 10) + "\n\nDurability: " + durability;
		return str;	
	}
	public void update(){
		//moving left
		if (this.getX() > model.getRoadx1() && this.getSpeedX() < 0 )
			this.x += this.getSpeedX();
		
		//moving right
		else if (this.getX() < model.getRoadx2() - this.getSizeX() && this.getSpeedX() > 0)
			this.x += this.getSpeedX();
		
		//moving forward and making an stable speed
		if (this.getName() != "myCar"){
			this.speedY = model.getFlowSpeed();
			this.y += (this.getSpeedY() - this.topSpeed);
		}
		
		//friction for cars
		if ( this.speedY > model.getMIN_VEL() && !this.gas)
			this.speedY -= model.getFriction();
		
		//brake control
		if ( this.brake && this.speedY > 0)
			this.speedY -= this.acc;
		
		//gas control
		if (this.gas){
			if(Math.round(speedY) == 0){
				SoundCreator carStart = new SoundCreator("carStarting.wav", 7);
				carStart.start();
			}
			else{
				counter++;
				if(counter%8 == 0){
					SoundCreator gas = new SoundCreator("car.wav", 5);
					gas.start();
					counter = 0;
				}
					
				
			}
				
			this.speedY += Math.pow(this.acc, this.speedY / model.getMIN_VEL()) ;
			
		}
			
		//updating the flow speed of the objects
		if ( this.getName() != "car") 
				model.setFlowSpeed( (int)(model.getMyCar().getSpeedY()) );
		
				
		if ( Math.round(model.getFlowSpeed()) == 0 ){
			this.setSpeedY(0);
			
			if (this.getName() == "car"){
				if (topSpeed >= 0)
					this.setTopSpeed(Math.random()*5);
				else
					this.setTopSpeed(-Math.random()*5);
			}
				
		}
		
	}
	
	//actionPerfomed for timer object of ToolModel objects
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<ToolModel> deadPickedBoxes = new ArrayList<ToolModel>();
		
		if (this.isDead()){
			deadPickedBoxes.add(this);
		}else{
			this.setLifeTime(this.getLifeTime() - 1);
		}
		
		for (ToolModel v : deadPickedBoxes)
			model.getPickedBoxes().remove(v);
		
	}

}
