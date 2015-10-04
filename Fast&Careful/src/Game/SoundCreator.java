package Game;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


public class SoundCreator{
	//Properties
	File sound;
	AudioInputStream audioIn;
	
	
	String carSounds;
	public String menuSounds = "menu.wav";
	String horns = "horn.wav";
	String startings = "carStarting.wav";
	public String deserts = "desert.wav";
	public String citys = "city.wav";
	String cars = "car.wav";
	public String highways = "highway.wav";
	public int volume;
	public String file;
	
	//volume
	FloatControl gainControl;
	Clip clip;
	
	public SoundCreator(String file, int volume){
		this.file = file;
		this.volume = volume;
		try {
			sound  = new File(file);
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			
			gainControl = 
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-40.0f + 4*volume);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	
	}
	public void changeMusic(String file){
		this.file = file;
		try {
			sound  = new File(file);
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			gainControl = 
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-40.0f + 4*volume);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void start(){
		clip.start();
	}
	
	public void stop(){
		clip.stop();
	}	
	
	public boolean isRunning(){
		if(clip.isRunning())
			return true;
		return false;
	}	
	
	public void setVolume(int a){	
		this.volume = a;
		gainControl.setValue(-40.0f + 4*a);		
	}
	
	public String getCarSounds() {
		return carSounds;
	}

	public void setCarSounds(String carSounds) {
		this.carSounds = carSounds;
	}

	public String getMenuSounds() {
		return menuSounds;
	}

	public void setMenuSounds(String menuSounds) {
		this.menuSounds = menuSounds;
	}

	public String getHorns() {
		return horns;
	}

	public void setHorns(String horns) {
		this.horns = horns;
	}

	public String getStartings() {
		return startings;
	}

	public void setStartings(String startings) {
		this.startings = startings;
	}

	public String getDeserts() {
		return deserts;
	}

	public void setDeserts(String deserts) {
		this.deserts = deserts;
	}

	public String getCitys() {
		return citys;
	}

	public void setCitys(String citys) {
		this.citys = citys;
	}

	public String getCars() {
		return cars;
	}

	public void setCars(String cars) {
		this.cars = cars;
	}

	public String getHighways() {
		return highways;
	}

	public void setHighways(String highways) {
		this.highways = highways;
	}

}
	
	  
	

	

	
	
		
	


