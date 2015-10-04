package Game;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public interface IGameSetup {
	public double getMyCarTopSpeed();
	public int getTrafficDensity();
	public int getBoxDensity();
	public int getQuestionDifficulty();
	public int getVolumeLevel();
	public double getFriction();
	public int getFPS();
	public Map getMap();
	public int getMyCarSizeX();
	public int getMyCarSizeY();
	public double getMyCarAcc();
	public double getMyCarDurability();
	public ImageIcon getMyCarImage();
	
}
