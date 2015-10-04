package Game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;

// this class will setup game according to user's choices (Settings, map and tool(modified car)
public class BasicGameSetup implements IGameSetup {
	
	File myFile;
	Scanner scan;
	int densityLevel;
	int difficultyLevel;
	int soundLevel;
	static ToolModel myCar;
	static Map myMap;
	
	public BasicGameSetup() throws FileNotFoundException {
		myFile = new File("settings.txt");
		scan = new Scanner(myFile);
		densityLevel = Integer.parseInt(scan.nextLine());
		difficultyLevel = Integer.parseInt(scan.nextLine());
		soundLevel = Integer.parseInt(scan.nextLine());
		
	}
	@Override
	public int getTrafficDensity() {
		//return settings.getTrafficDensity();
		return densityLevel;
	}
	
	public int getBoxDensity(){
		return 100;
	}

	@Override
	public int getQuestionDifficulty() {
		//return settings.getQuestionDifficulty(); 
		return difficultyLevel;
	}
	@Override
	public int getVolumeLevel() {
		return soundLevel;
	}
	
	@Override
	public double getFriction() {
		return .001;
	}

	@Override
	public int getFPS() {
		return 60;
	}

	@Override
	public Map getMap() {
		//Map m = new Map(null, 100, 4, false, "highway", Engine.WIDTH/3, Engine.WIDTH*2/3);
		return myMap;
	}

	@Override
	public double getMyCarTopSpeed() {
		return myCar.getTopSpeed();
	}

	@Override
	public int getMyCarSizeX() {
		return (int) myCar.getSizeX();
	}

	@Override
	public int getMyCarSizeY() {
		return (int) myCar.getSizeY();
	}

	@Override
	public double getMyCarAcc() {
		return myCar.getAcc();
	}

	@Override
	public double getMyCarDurability() {
		return myCar.getDurability();
	}

	@Override
	public ImageIcon getMyCarImage() {
		return myCar.getImage();
	}
	public ToolModel getMyCar() {
		return myCar;
	}
	public static void setMyCar(ToolModel car) {
		myCar = car;
	}
	public static void setMyMap(Map map) {
		myMap = map;
	}
	


	
}
