package Game;
import javax.swing.ImageIcon;


public class Map {
	// Variables
	int speedLimit;
	int laneNumber;
	boolean oneWay;
	String mapName;
	int x;
	int y;
	int roadx1;
	int roadx2;
	ImageIcon background;
	
	// constructor
	public Map(ImageIcon background, int speedLimit, int laneNumber, boolean oneWay, String mapName, int roadx1, int roadx2) {
		// initialization of variables
		this.background = background;
		this.speedLimit = speedLimit;
		this.laneNumber = laneNumber;
		this.oneWay = oneWay;
		this.mapName = mapName;
		this.x = 0;
		this.y = 0;
		this.roadx1 = roadx1;
		this.roadx2 = roadx2;
		
	}
	
	// Getters and Setters

	public ImageIcon getBackground() {
		return background;
	}

	public void setBackground(ImageIcon background) {
		this.background = background;
	}

	public int getSpeedLimit() {
		return speedLimit;
	}
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}
	public int getLaneNumber() {
		return laneNumber;
	}
	public void setLaneNumber(int laneNumber) {
		this.laneNumber = laneNumber;
	}
	public boolean isOneWay() {
		return oneWay;
	}
	public void setOneWay(boolean oneWay) {
		this.oneWay = oneWay;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getRoadx1() {
		return roadx1;
	}

	public void setRoadx1(int roadx1) {
		this.roadx1 = roadx1;
	}

	public int getRoadx2() {
		return roadx2;
	}

	public void setRoadx2(int roadx2) {
		this.roadx2 = roadx2;
	}
	public boolean getOneWay(){
		return this.oneWay;
	}
	public String toString() {
		String oneway;
		if (oneWay)
			oneway = "One Way";
		else
			oneway = "Two Ways";
		return "\n\nName: " + mapName + "\n\nSpeed limit: " + speedLimit + "\n\n Number of ways: " + oneway;
	}
	
	
	
}
