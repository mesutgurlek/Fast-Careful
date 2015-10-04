package Game;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Menu.MainFrame;


public class GameModel implements ActionListener {
	
	private ArrayList<IGameView> views;
	private ArrayList<ToolModel> vehicles, boxes, pickedBoxes, trafficObjects;
	private Timer t;
	private Timer clock;
	private int FPS, time;
	private final double  MIN_VEL = 3;
	private double friction;
	private boolean started, countdown, collide, draw2Screen;
	private IGameSetup setup;
	private double flowSpeed;
	private String bottomText, screenText;
	private int second, minute, hour, points;
	private int width,height, replyToQuestion, answer;
	private ToolModel myCar;
	
		
	public GameModel(IGameSetup setup){
		this.setup = setup;
		initNewGame();
			
	}
	
	//init new game
	public void initNewGame(){
		views = new ArrayList<IGameView>();		
		started = true;
		countdown = false;
		time = 0;
		flowSpeed = 3;
		friction = this.setup.getFriction();
		FPS = this.setup.getFPS();
		bottomText = "Welcome to the Fast & CareFul";
		screenText = "";
		t = new Timer(1000/FPS, this);
		t.start();
		second = 0;
		minute = 0;
		hour = 0;
		points = 0;
		width = MainFrame.WIDTH;
		height = MainFrame.HEIGHT;
		
		vehicles = new ArrayList<ToolModel>();
		boxes = new ArrayList<ToolModel>();
		pickedBoxes = new ArrayList<ToolModel>();
		trafficObjects = new ArrayList<ToolModel>();
		
	}

	public void addView(IGameView view){
		views.add(view);
	}
	//Setup's methods and variables.
	public int getRoadx1(){
		return setup.getMap().getRoadx1();
	}
	public void setRoadx1(int i){
		setup.getMap().setRoadx1(i);
	}
	public int getRoadx2(){
		return setup.getMap().getRoadx2();
	}
	public void setRoadx2(int i){
		setup.getMap().setRoadx2(i);
	}
	public int getLaneNumber(){
		return setup.getMap().getLaneNumber();
	}
	public ImageIcon getBackground(){
		return setup.getMap().getBackground();
	}
	public int getLaneWidth(){
		return MainFrame.WIDTH/(3 * getLaneNumber());
	}
	public int getTrafficDensity(){
		return setup.getTrafficDensity();
	}
	public int getBoxDensity(){
		return setup.getBoxDensity();
	}
	public int getQuestionDifficulty(){
		return this.setup.getQuestionDifficulty();
	}
	//Collide methods
	public boolean collide(ToolModel v1, ToolModel v2){
		
		if ( Math.abs( v1.getX() + v1.getSizeX()/2 - v2.getX() - v2.getSizeX()/2 )
				<= (v1.getSizeX()/2+v2.getSizeX()/2) && Math.abs(v1.getY() + v1.getSizeY()/2 - v2.getY() - v2.getSizeY()/2) 
				<= (v1.getSizeY()/2+v2.getSizeY()/2)  )
			return true;
		else
			return false;
	}	
	
	//Update method
	public void updateViews(){
		if(  views != null && views.size() > 0 )
		{
			for (IGameView gViews : this.views)
			{
					if(gViews != null){
						gViews.updateView(this);
						
					}	
			}
		}
		
	}
		
	//Action performed Method
	public void actionPerformed(ActionEvent event){
		updateViews();
		time++;
	}
	
	//Getter and Setter Methods
	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

	public int getTime() {
		return getSecond() + getMinute()*60 + getHour() ;
	}
	
	public int getRealTime(){
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getFriction() {
		return friction;
	}

	public void setFriction(double friction) {
		this.friction = friction;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	public boolean isDraw2Screen() {
		return draw2Screen;
	}

	public void setDraw2Screen(boolean draw2Screen) {
		this.draw2Screen = draw2Screen;
	}

	public void removeView( IGameView view){
		views.remove(view);
	}
	
	
	//text için yorum
	public String getBottomText() {
		return bottomText;
	}


	public void setBottomText(String bottomText) {
		this.bottomText = bottomText;
	}
	
	public String getScreenText() {
		return screenText;
	}

	public void setScreenText(String screenText) {
		this.screenText = screenText;
	}


	public ArrayList<IGameView> getViews() {
		return views;
	}


	public void setViews(ArrayList<IGameView> views) {
		this.views = views;
	}


	public Timer getT() {
		return t;
	}


	public void setT(Timer t) {
		this.t = t;
	}


	public Timer getClock() {
		return clock;
	}


	public void setClock(Timer clock) {
		this.clock = clock;
	}


	public IGameSetup getSetup() {
		return setup;
	}


	public void setSetup(IGameSetup setup) {
		this.setup = setup;
	}


	public double getFlowSpeed() {
		return flowSpeed;
	}


	public void setFlowSpeed(double flowSpeed) {
		this.flowSpeed = flowSpeed;
	}


	public int getSecond() {
		return second;
	}


	public void setSecond(int second) {
		this.second = second;
	}


	public int getMinute() {
		return minute;
	}


	public void setMinute(int minute) {
		this.minute = minute;
	}


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	public double getMIN_VEL() {
		return MIN_VEL;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	public ToolModel getMyCar() {
		return myCar;
	}

	public void setMyCar(ToolModel myCar) {
		this.myCar = myCar;
	}
	public ArrayList<ToolModel> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<ToolModel> vehicles) {
		this.vehicles = vehicles;
	}
	public ArrayList<ToolModel> getBoxes(){
		return boxes;
	}
	public void setBoxes(ArrayList<ToolModel> boxes){
		this.boxes = boxes;
	}
	public boolean isCollide() {
		return collide;
	}
	public void setPickedBoxes(ArrayList<ToolModel> pickedBoxes){
		this.pickedBoxes = pickedBoxes;
	}
	public ArrayList<ToolModel> getPickedBoxes(){
		return this.pickedBoxes;
	}
	
	public ArrayList<ToolModel> getTrafficObjects(){
		return this.trafficObjects;
	}
	public void setTrafficObjects(ArrayList<ToolModel> trafficObjects){
		this.trafficObjects = trafficObjects;
	}

	public void setCollide(boolean collide) {
		this.collide = collide;
	}
	
	public boolean isCountdown() {
		return countdown;
	}

	public void setCountdown(boolean countdown) {
		this.countdown = countdown;
	}
	public  int getReplyToQuestion() {
		return replyToQuestion;
	}

	public void setReplyToQuestion(int replyToQuestion) {
		this.replyToQuestion = replyToQuestion;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getMyCarSizeX() {
		return this.setup.getMyCarSizeX();
	}
	public int getMyCarSizeY() {
		return this.setup.getMyCarSizeY();
	}
	public double getMyCarAcc(){
		return this.setup.getMyCarAcc();
	}

	public double getMyCarTopSpeed() {
		return this.setup.getMyCarTopSpeed();
	}

	public double getMyCarDurability() {
		return this.setup.getMyCarDurability();
	}

	public ImageIcon getMyCarImage() {
		return this.setup.getMyCarImage();
	}
	public boolean getOneWay(){
		return this.setup.getMap().getOneWay();
	}
	
	public Map getMap(){
		return setup.getMap();
	}
	

}

