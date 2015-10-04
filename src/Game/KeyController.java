package Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Menu.MainFrame;


public class KeyController implements KeyListener,IGameView {

	//properties
	File myFile; 
	Scanner scan;
	String str;
	private boolean[] list = new boolean[9];
	private GameModel model;
	private ToolModel myCar ;
	private Scanner scanChoices;
	private Scanner scanAnswers;
	SoundCreator sc;
	ArrayList<String> highScoreNameList; 
	ArrayList<Integer> highScorePointsList; 
	int counter = 0;
	
	
	
	public KeyController(GameModel model) throws FileNotFoundException {
	
		
		this.model = model;
		int sizeX = model.getMyCarSizeX();
		int sizeY = model.getMyCarSizeY();
		double acc = model.getMyCarAcc();
		double topSpeed =model.getMyCarTopSpeed();
		double durability = model.getMyCarDurability();
		ImageIcon img = model.getMyCarImage();
		myCar = new ToolModel(model, "myCar", sizeX, sizeY, model.getWidth()/2, model.getHeight()*3/4, 0, 3, acc, topSpeed, durability, img);
		model.setMyCar(myCar);
		//reading high score list
		highScoreNameList = new ArrayList<String>();
		highScorePointsList = new ArrayList<Integer>();
		myFile = new File("HighScore.txt");
		scan = new Scanner(myFile);
		for(int i = 0; i < 10; i++){
			str = scan.nextLine();
			String[] array = str.split("/");
			highScorePointsList.add(Integer.valueOf(array[0]));
			highScoreNameList.add(array[1]);
		}
		scan.close();
	}
	
	
	
	public void keyTyped(KeyEvent e) {
	}

	
	public void keyPressed(KeyEvent e) {
		//Horn sound
		if(e.getKeyCode() == KeyEvent.VK_H){
			list[6] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			list[0] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			list[1] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			list[2] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			list[3] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			list[4] = true;
			stopGame();
			// to determine there is no answer
			model.setAnswer(-1);
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && model.getPickedBoxes().size() > 0 && model.isStarted()){
			list[5] = true;
			
			//deactivating the other buttons
			for (int i = 0; i < 4; i++)
				list[i] = false;
			
			stopGame();
			
			//opening required files
			try{
				this.scanChoices = new Scanner(new File("choices.txt"));
			}catch(IOException   b){
				System.out.println("error choices.txt not found");
			}
			
			try{
				this.scanAnswers = new Scanner(new File("answers.txt"));
			}catch(IOException   b){
				System.out.println("error answers.txt not found");
			}
			
			
			//choosing a random number for an question
			int questionNumber = model.getQuestionDifficulty() + (int)(Math.random() * 14) + 1;
			System.out.println("q nuber = " + questionNumber);
			
			//to scan the choices
			for (int i = 0; i < questionNumber-1; i++)
				scanChoices.nextLine();
			
			
			//to scan answers
			for (int i = 0; i < questionNumber-1; i++)
				scanAnswers.nextLine();

			
			Object[] choices = scanChoices.nextLine().split("\\|");
		
			int answer = scanAnswers.nextInt();
			model.setAnswer(answer);
			
			//to get the question .png
			String str = "questions/" + questionNumber + ".png";		
			
			int n = JOptionPane.showOptionDialog(MainFrame.f,"","Click or type the correct choice",
				    JOptionPane.DEFAULT_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    new ImageIcon((str)),
				    choices,
				    choices[0]); 
						
			model.getPickedBoxes().remove(0);
			
			model.setReplyToQuestion(n);
			
			//if the answer it correct, increment the score of the game 
			if (n == answer){
				model.setPoints( model.getPoints() + 10000);
				model.setDraw2Screen(true);
				model.setScreenText("+10000");
				model.setPoints(model.getPoints()+10000);
			}
			
			// continue the game 
			stopGame();
		}
	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			list[0] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			list[1] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			list[2] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			list[3] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_H){
			list[6] = false;
		}
	
	}

	//public void stopGame
	
	public void updateView(GameModel model) {
		
		
		if (model.isStarted()){
			if(list[0]){
				myCar.setGas(true);
			}
			else{
				myCar.setGas(false);
			}
			if(list[1]){
				myCar.setBrake(true);
			}
			else{
				myCar.setBrake(false);
			}
			if( list[2] && list[3]){
				myCar.setSpeedX(0);
			}
			else if(list[2]){
				myCar.setSpeedX(myCar.getSpeedY()/model.getMIN_VEL());
			}
			else if(list[3]){
				myCar.setSpeedX(-myCar.getSpeedY()/model.getMIN_VEL());
			}
			else{
				myCar.setSpeedX(0);
			}
			if(list[6]){
				counter++;
				if(counter%4 == 0){
					sc = new SoundCreator("horn.wav", 7);
					sc.start();
				}
					
				
			}
						
			
			//deciding the collides
			ArrayList<ToolModel> deadVehicles = new ArrayList<ToolModel>();
			
			for(ToolModel v : model.getVehicles())
				if( model.collide(myCar, v))
					if (Math.abs(v.getSpeedY()-v.getTopSpeed()-myCar.getSpeedY())< myCar.getDurability()/3 ){
						deadVehicles.add(v);
						model.setBottomText("do NOT crash other vehicles");
						model.setDraw2Screen(true);
						model.setScreenText("-1000");
						model.setPoints(model.getPoints()-1000);
					}else{
						deadVehicles.add(v);
						model.setBottomText("you crash very bad !!!");
						model.setDraw2Screen(true);
						model.setScreenText("The End");
						stopGame();
						
					}
			
			
			for (ToolModel o : model.getTrafficObjects()){
				
				// deciding the traffic lamb issues
				if( o.isActive4points() && o.getImage().getDescription().contains("trafficLambs/1.png") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= 20  ){
					model.setBottomText("When there is red lights, stop!!!");
					model.setDraw2Screen(true);
					model.setScreenText("-500");
					model.setPoints(model.getPoints()-500);
					o.setActive4points(false);
				}else if ( o.isActive4points() && o.getImage().getDescription().contains("trafficLambs/2.png") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= 20){
					model.setBottomText("When there is yellow lights, stop!!!");
					model.setDraw2Screen(true);
					model.setScreenText("-750");
					model.setPoints(model.getPoints()-750);
					o.setActive4points(false);
				}else if( o.getImage().getDescription().contains("trafficLambs/3.png") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= 20){
					model.setBottomText("Green lights, Go");
				}
				
				
				// deciding the pedestrian issues
				if( o.isActive4points() && o.getImage().getDescription().contains("yaya1.gif") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= (myCar.getSizeY()/2 + o.getSizeY() /2 + 20)  ){
					model.setBottomText("When there are pedestrians, you should stop ");
					model.setDraw2Screen(true);
					model.setScreenText("-1500");
					model.setPoints(model.getPoints()-1500);
					o.setActive4points(false);
				} else if( o.getImage().getDescription().contains("yaya2.gif") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= (myCar.getSizeY()/2 + o.getSizeY() /2 + 20)  ){
					model.setBottomText("NO pedestrians, go ");
				}
				
				//deciding the speed limit issues
				if( o.isActive4points() && o.getImage().getDescription().contains("speedSigns/1.png") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= (myCar.getSizeY()/2 + o.getSizeY() /2 + 20)  ){
					model.setBottomText("The Speed Limit is 90");
					
					if (model.getMyCar().getSpeedY() > 7.5){
						model.setDraw2Screen(true);
						model.setScreenText("-500");
						model.setPoints(model.getPoints()-500);
						o.setActive4points(false);
					}
				}else if( o.isActive4points() && o.getImage().getDescription().contains("speedSigns/2.png") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= (myCar.getSizeY()/2 + o.getSizeY() /2 + 20)  ){
					model.setBottomText("The Speed Limit is 110");
					
					if (model.getMyCar().getSpeedY() > 9.16){
						model.setDraw2Screen(true);
						model.setScreenText("-750");
						model.setPoints(model.getPoints()-750);
						o.setActive4points(false);
					}
				} else if( o.isActive4points() && o.getImage().getDescription().contains("speedSigns/3.png") && Math.abs( (int)(myCar.getY()) -(int)(o.getY()) ) <= (myCar.getSizeY()/2 + o.getSizeY() /2 + 20)  ){
					if (model.getMyCar().getSpeedY() > 10){
						model.setDraw2Screen(true);
						model.setScreenText("-750");
						model.setPoints(model.getPoints()-750);
						o.setActive4points(false);
					}
				} 
				
				
			}
			
			// reminding the box
			for (ToolModel b : model.getPickedBoxes())
				if (b.getLifeTime() < 2)
					model.setBottomText("Press spacebar to activate BOX !!!");
			
			
			//removing dead vehicles
			for (ToolModel v : deadVehicles)
				model.getVehicles().remove(v);
				
			myCar.update();
			}
	}


	//Getter and Setter
	public boolean[] getList() {
		return list;
	}


	public void setList(boolean[] list) {
		this.list = list;
	}


	public GameModel getModel() {
		return model;
	}


	public void setModel(GameModel model) {
		this.model = model;
	}

	public ToolModel getMyCar() {
		return this.myCar;
	}

	public void setMyCar(ToolModel myCar) {
		this.myCar = myCar;
	}
	public void stopGame(){
		if(model.isStarted()){
			model.getT().stop();
			model.getClock().stop();
			for(ToolModel t : model.getPickedBoxes())
				t.stopTimer();
			for (ToolModel o : model.getTrafficObjects()){
				if (o.getTimer() != null)
					o.stopTimer();
			}
				
			model.setStarted(false);
			model.setCountdown(false);
			
			if(model.getScreenText() == "The End"){
				for( int i = 0; i < 10; i++){
					if(highScorePointsList.get(i) < model.getPoints()){
						GameCenterPanelView.score.setText("Congrulations! You are the number " + (i+1) + " with " + model.getPoints() + " points.");
						GameCenterPanelView.score.setForeground(Color.red);
						GameCenterPanelView.highScore.setText("Please write your name: ");
						GameCenterPanelView.exitButton.setVisible(true);
						GameCenterPanelView.resumeButton.setVisible(false);
						GameCenterPanelView.menuButton.setVisible(true);
						GameCenterPanelView.highScore.setVisible(true);
						GameCenterPanelView.score.setVisible(true);
						GameCenterPanelView.resetGame.setVisible(true);
						GameCenterPanelView.highscorePanel.setVisible(true);
						GameCenterPanelView.buttonPanel.setVisible(true);
						break;
					}
					else{
						GameCenterPanelView.exitButton.setVisible(true);
						GameCenterPanelView.resumeButton.setVisible(false);
						GameCenterPanelView.menuButton.setVisible(true);
						GameCenterPanelView.highScore.setVisible(false);
						GameCenterPanelView.score.setVisible(false);
						GameCenterPanelView.resetGame.setVisible(true);
						GameCenterPanelView.buttonPanel.setVisible(true);
						GameCenterPanelView.highscorePanel.setVisible(false);
					}
				}
			}
			else{
				GameCenterPanelView.exitButton.setVisible(true);
				GameCenterPanelView.resumeButton.setVisible(true);
				GameCenterPanelView.menuButton.setVisible(true);
				GameCenterPanelView.highScore.setVisible(false);
				GameCenterPanelView.score.setVisible(false);
				GameCenterPanelView.resetGame.setVisible(false);
				GameCenterPanelView.buttonPanel.setVisible(true);
				GameCenterPanelView.highscorePanel.setVisible(false);
			}
			
			
		}
		else{
			if(model.getScreenText() != "The End"){
			model.getT().setInitialDelay(3000);;
			model.getT().start();
			model.getClock().setInitialDelay(3000);
			model.getClock().start();
			for(ToolModel t : model.getPickedBoxes()){
				t.getTimer().setInitialDelay(3000);
				t.continueTimer();
			}
			for(ToolModel o : model.getTrafficObjects()){
				if (o.getTimer() != null){
					o.getTimer().setInitialDelay(3000);
					o.continueTimer();
				}
			}
			model.setCountdown(true);
			GameCenterPanelView.exitButton.setVisible(false);
			GameCenterPanelView.resumeButton.setVisible(false);
			GameCenterPanelView.menuButton.setVisible(false);
			GameCenterPanelView.buttonPanel.setVisible(false);
			}
		}
	}
	
			
}
