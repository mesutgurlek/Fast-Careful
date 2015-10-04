package Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import Menu.Entrance;
import Menu.MainFrame;

public class GameCenterPanelView extends JPanel implements IGameView, ActionListener {

	//properties to manage the center panel
	//Writing file
	File myFile; 
	PrintWriter out;
	ArrayList<String> highScoreNameList; 
	ArrayList<Integer> highScorePointsList; 
	// Variables
	Entrance enter;
	GameModel model;
	VehicleControl vehicleManager;
	KeyController myCarManager;	
	BoxController boxManager;
	TrafficRuleController trafficManager;
	Timer countdown;
	
	int count = 3, timer = 0;
	//fonts to show things when the game stops
	Font bigFont, smallFont;
	//Images after questions
	Image correct, incorrect;
	Image speedometer;
	int y = 0;
	String tree1,tree2,tree3,tree4;
	int x1,x2,x3,x4,treex1,treex2,treex3,treex4,objectx1,objectx2,objectx3,objectx4;
	//JLabel pedestrian;
	//to show screen texts
	String exScreenText = "";
	// Static variables
	public static JButton menuButton, resumeButton, resetGame;
	public static JButton exitButton;
	public static JTextField highScore;
	public static JLabel score;
	public static JPanel buttonPanel;
	public static JPanel highscorePanel;
	
	//constructor
	public GameCenterPanelView(GameModel model,Entrance enter) throws FileNotFoundException{
		
		//setLayout(new BorderLayout());
		this.enter = enter;
		this.model = model;
		myFile = new File("HighScore.txt");
		
		x1=1;
		x2=3;
		x3=2;
		x4=4;
		treex1=26;
		treex2=89;
		treex3=24;
		treex4=52;
		objectx1=37;
		objectx2=65;
		objectx3=98;
		objectx4=17;
		
		// arrangement of this panel
		setPreferredSize( new Dimension( model.getWidth(), model.getHeight()*9/10 ) );
		setBackground(Color.orange);
		
		myCarManager = new KeyController(model);
		addKeyListener(myCarManager);
		
		highScoreNameList = myCarManager.highScoreNameList;
		highScorePointsList = myCarManager.highScorePointsList;
				
		vehicleManager = new VehicleControl(model);
		boxManager = new BoxController(model);
		trafficManager = new TrafficRuleController(model);

		countdown = new Timer(1000, this);
		bigFont = new Font("Serif", Font.PLAIN, 100);
		smallFont = new Font("Serif", Font.PLAIN, 32);
		incorrect = Toolkit.getDefaultToolkit().getImage("incorrect.png");
		correct = Toolkit.getDefaultToolkit().getImage("correct.png");
		speedometer = new ImageIcon("speedometer.png").getImage();
		
		setFocusable(true);
		model.addView(vehicleManager);
		model.addView(myCarManager);
		model.addView(boxManager);
		model.addView(trafficManager);
		
		buttonPanel = new JPanel();
		highscorePanel = new JPanel();
		
		menuButton = new JButton("MAIN MENU");
		resumeButton = new JButton("RESUME GAME");
		exitButton = new JButton("EXIT GAME");
		resetGame = new JButton("reset");
		highScore = new JTextField(30);
		highScore.setFont(new Font(getName(), Font.BOLD, 20));
		score = new JLabel("Score: " + model.getPoints());
		score.setFont(new Font(getName(), Font.BOLD, 17));
		
		menuButton.addActionListener(this);
		resumeButton.addActionListener(this);
		exitButton.addActionListener(this);
		resetGame.addActionListener(this);
		highScore.addActionListener(this);
		
		menuButton.setVisible(false);
		resumeButton.setVisible(false);
		exitButton.setVisible(false);
		highScore.setVisible(false);
		score.setVisible(false);
		resetGame.setVisible(false);
		buttonPanel.setVisible(false);
		highscorePanel.setVisible(false);
		
		add(Box.createRigidArea(new Dimension(0, MainFrame.HEIGHT/2 + 50)));
		buttonPanel.add(menuButton);
		buttonPanel.add(resumeButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(resetGame);
		
		highscorePanel.add(score);
		highscorePanel.add(highScore);
		
		add(buttonPanel);
		add(highscorePanel);
		
		
	}
	
	@Override
	public void updateView(GameModel model) {
		
		//score.setText("Score: " + model.getPoints());
		 
		repaint();
		
		if(model.getMap().getMapName() == "highway" ){
			if ((-325+y)%1280<30){
				treex1 =(int)(Math.random()*150)+1;
				objectx1 =(int)(Math.random()*150)+1;
				x1 =(int)(Math.random()*5)+1;
				tree1 = x1+".png";
			}
			else {
				tree1 = tree1;
			}
			if ((-5+y)%1280<30){
				treex2 =(int)(Math.random()*150)+1;
				objectx2 =(int)(Math.random()*150)+1;
				x2 =(int)(Math.random()*5)+1;
				tree2 = x2+".png";
			}
			else {
				tree2 = tree2;
			}
			if ((+315+y)%1280<30){
				treex3 =(int)(Math.random()*150)+1;
				objectx3 =(int)(Math.random()*150)+1;
				x3 =(int)(Math.random()*5)+1;
				tree3 = x3+".png";
			}
			else {
				tree3 = tree3;
			}
			if ((635+y)%1280<30){
				treex4 =(int)(Math.random()*150)+1;
				objectx4 =(int)(Math.random()*150)+1;
				x4 =(int)(Math.random()*5)+1;
				tree4 = +x4+".png";
			}
			else {
				tree4 = tree4;
			}
			
			if (model.isStarted())
				y= y+(int)(myCarManager.getMyCar().getSpeedY());
		}
		
		else if(model.getMap().getMapName() == "rural" ){
			if ((-325+y)%1280<30){
				treex1 =(int)(Math.random()*150)+1;
				objectx1 =(int)(Math.random()*150)+1;
				x1 =(int)(Math.random()*3)+1;
				tree1 = x1+".png";
			}
			else {
				tree1 = tree1;
			}
			if ((-5+y)%1280<30){
				treex2 =(int)(Math.random()*150)+1;
				objectx2 =(int)(Math.random()*150)+1;
				x2 =(int)(Math.random()*3)+1;
				tree2 = x2+".png";
			}
			else {
				tree2 = tree2;
			}
			if ((+315+y)%1280<30){
				treex3 =(int)(Math.random()*150)+1;
				objectx3 =(int)(Math.random()*150)+1;
				x3 =(int)(Math.random()*3)+1;
				tree3 = x3+".png";
			}
			else {
				tree3 = tree3;
			}
			if ((635+y)%1280<30){
				treex4 =(int)(Math.random()*150)+1;
				objectx4 =(int)(Math.random()*150)+1;
				x4 =(int)(Math.random()*3)+1;
				tree4 = +x4+".png";
			}
			else {
				tree4 = tree4;
			}
			
			if (model.isStarted())
				y= y+(int)(myCarManager.getMyCar().getSpeedY());
		}
		else if(model.getMap().getMapName() == "city" ){
			if ((-481+y)%1280<30){
				treex1 =(int)(Math.random()*150)+1;
				objectx1 =(int)(Math.random()*150)+1;
				x1 =(int)(Math.random()*3)+1;
				tree1 = x1+".png";
			}
			else {
				tree1 = tree1;
			}
			if ((+y)%1280<30){
				treex2 =(int)(Math.random()*150)+1;
				objectx2 =(int)(Math.random()*150)+1;
				x2 =(int)(Math.random()*3)+1;
				tree2 = x2+".png";
			}
			else {
				tree2 = tree2;
			}
			if ((481+y)%1280<30){
				treex3 =(int)(Math.random()*150)+1;
				objectx3 =(int)(Math.random()*150)+1;
				x3 =(int)(Math.random()*3)+1;
				tree3 = x3+".png";
			}
			else {
				tree3 = tree3;
			}
			if ((635+y)%1280<30){
				treex4 =(int)(Math.random()*150)+1;
				objectx4 =(int)(Math.random()*150)+1;
				x4 =(int)(Math.random()*3)+1;
				tree4 = +x4+".png";
			}
			else {
				tree4 = tree4;
			}
			
			if (model.isStarted())
				y= y+(int)(myCarManager.getMyCar().getSpeedY());
		}else
			if (model.isStarted())
				y= y+(int)(myCarManager.getMyCar().getSpeedY());
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		

		//(Toolkit.getDefaultToolkit().getImage("background.jpg"), 0, (768+y)%1536-768, null);
		
		//drawing background objects
		if(model.getMap().getMapName() == "rural" ){
			
			
			//drawing background
			ImageIcon bgImage = new ImageIcon("background.jpg");
			bgImage.paintIcon(this,g,0, (-768+y)%1536-768);
			bgImage.paintIcon(this,g, 0, (0+y)%1536-768);
			bgImage.paintIcon(this, g, 0, (768+y)%1536-768);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree1), treex1, (-325+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree1), objectx1, (-155+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree2), treex2, (-5+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree2), objectx2, (165+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree3), treex3, (315+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree3), objectx3, (485+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree4), treex4, (635+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree4), objectx4, (805+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree1), 700+treex1, (-325+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree1), 700+objectx1, (-155+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree2), 700+treex2, (-5+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree2), 700+objectx2, (165+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree3), 700+treex3, (315+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree3), 700+objectx3, (485+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/tree"+tree4), 700+treex4, (635+y)%1280-320, null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/object"+tree4), 700+objectx4, (805+y)%1280-320, null);
			}
			
			else if(model.getMap().getMapName() == "city" ){
				
				
				g.drawImage(Toolkit.getDefaultToolkit().getImage("city/katman"+x1+".png"),694,(-481+y)%1280-481,null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("city/katman"+x2+".png"),694,(0+y)%1280-481,null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("city/katman"+x3+".png"),694,(481+y)%1280-481,null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("city/katman"+x1+"L.png"),-20,(-481+y)%1280-481,null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("city/katman"+x2+"L.png"),-20,(0+y)%1280-481,null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("city/katman"+x3+"L.png"),-20,(481+y)%1280-481,null);
			}
			
			else if(model.getMap().getMapName() == "highway"){
				
				//drawing background
				ImageIcon bgImage = new ImageIcon("desert.jpg");
				bgImage.paintIcon(this,g,0, (-768+y)%1536-768);
				bgImage.paintIcon(this,g, 0, (0+y)%1536-768);
				bgImage.paintIcon(this, g, 0, (768+y)%1536-768);
				
				
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+tree1), objectx1, (-155+y)%1280-320, null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+tree2), objectx2, (165+y)%1280-320, null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+tree3), objectx3, (485+y)%1280-320, null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+tree4), objectx4, (805+y)%1280-320, null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+(x1+1)+".png"), 700+treex1, (-155+y)%1280-320, null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+(x2+1)+".png"), 700+treex2, (165+y)%1280-320, null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+(x3+1)+".png"), 700+treex3, (485+y)%1280-320, null);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("objects/dobject"+(x4+1)+".png"), 700+treex4, (805+y)%1280-320, null);
				
				
				
			}
		

			
		//drawing the roads
		g.drawImage(Toolkit.getDefaultToolkit().getImage("vertical_road.jpg"), 328, (-325+y)%1280-320, null);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("vertical_road.jpg"), 328, (-5+y)%1280-320, null);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("vertical_road.jpg"), 328, (315+y)%1280-320, null);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("vertical_road.jpg"), 328, (635+y)%1280-320, null);
		
		//drawing traffic objects
		for (ToolModel o : model.getTrafficObjects()){
			o.getImage().paintIcon(this, g, (int)(o.getX()), (int)(o.getY()));
			if (o.getName() == "trafficLamb"){
				g.setColor(Color.white);
				g.setFont(smallFont);
				g.drawString(o.getLifeTime() +"", (int)(o.getX() + o.getSizeX()/2), (int)(o.getY()) );
			}
		}
		
		//drawing boxes
		for ( ToolModel b : boxManager.getBoxes() ){
			b.getImage().paintIcon(this, g, (int)(b.getX()), (int)(b.getY()) );
			
		}
		
		// drawing other vehicles
		for ( ToolModel v : vehicleManager.getVehicles() ){
			v.getImage().paintIcon(this, g, (int)(v.getX()), (int)(v.getY()) );
			
		}
		
		//drawing my car
		myCarManager.getMyCar().getImage().paintIcon(this, g, (int)(myCarManager.getMyCar().getX()), (int)(myCarManager.getMyCar().getY()) );
		ImageIcon smoke = new ImageIcon("smoke.gif");
		g.drawImage(smoke.getImage(), (int)(myCarManager.getMyCar().getX()), (int)((myCarManager.getMyCar().getY()) +
				myCarManager.getMyCar().getSizeY()), this);
		
		
		
		
		
		// Drawing Speedometer
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
    
        // Draw speedometer background
        g2.drawImage(speedometer, 700, 500, this);
        
        // Drawing needle (color will change according to speed)
        Color c = new Color((int)(myCarManager.getMyCar().getSpeedY() * 7), 0, 255 - (int)(myCarManager.getMyCar().getSpeedY() * 7));
        g2.setPaint(c);
        g2.setStroke(new BasicStroke(10));
        double d = (double)(myCarManager.getMyCar().getSpeedY()/myCarManager.getMyCar().getTopSpeed() * 12) * Math.PI;
        int x = (int)(Math.cos(d) * -100);
        int y = (int)(Math.sin(d) * -100);
        g2.draw(new Line2D.Float(speedometer.getWidth(this)/2 + 700, speedometer.getHeight(this) + 500, 150 + x + 700, 150 + y + 500));
        
        // drawing speed as a string
        g.setFont(new Font(getName(), Font.BOLD, 20));
        g.drawString("" + (int)(myCarManager.getMyCar().getSpeedY() * 12), 850, 670);
		
			
		
				
		//drawing the bits' after pausing the game
		if (!model.isStarted() && !model.isCountdown() && model.getScreenText() != "The End" ){
						
			//filling an rectangle
			g.setColor(Color.green);
			g.fillRoundRect(MainFrame.WIDTH/4,MainFrame.HEIGHT/4,MainFrame.WIDTH/2,MainFrame.HEIGHT/2,MainFrame.WIDTH/10,MainFrame.HEIGHT/10);
			
			g.setFont(bigFont);
			g.setColor(Color.black);
			g.drawString("paused", MainFrame.WIDTH/3,MainFrame.HEIGHT/2);
			
			
			countdown.stop();
			count = 3;
		}else if(!model.isStarted() && model.isCountdown()){
			
			// if timer is not running, start it
			if(!countdown.isRunning()){
				countdown.start();
			}
			
			//filling an rectangle
			g.setColor(Color.green);
			g.fillRoundRect(MainFrame.WIDTH/4,MainFrame.HEIGHT/4,MainFrame.WIDTH/2,MainFrame.HEIGHT/2,MainFrame.WIDTH/10,MainFrame.HEIGHT/10);	
			//drawing the count down from 3
			g.setFont(bigFont);
			g.setColor(Color.black);
			g.drawString("" + count , MainFrame.WIDTH/2, MainFrame.HEIGHT/2);
			
			
			g.setFont(smallFont);
			//reply to answers as images an string
			if(model.getAnswer() != -1){
				if (model.getAnswer() == model.getReplyToQuestion())
					g.drawImage(correct, MainFrame.WIDTH/2, MainFrame.HEIGHT/2, null);
				else if(model.getReplyToQuestion() == -1){
					g.drawString("question box will wait until its time is up", MainFrame.WIDTH/4, MainFrame.HEIGHT/2 + 32);
				}
				else{
					g.drawImage(incorrect, MainFrame.WIDTH/2, MainFrame.HEIGHT/2, null);
				}
			}else{
				g.drawString("ready to go", MainFrame.WIDTH/2, MainFrame.HEIGHT*2/3 );
			}
		}else if ( !model.isStarted() && model.getScreenText() == "The End" ){
			
			//filling an rectangle
			g.setColor(Color.green);
			g.fillRoundRect(MainFrame.WIDTH/4,MainFrame.HEIGHT/4,MainFrame.WIDTH/2,MainFrame.HEIGHT/2,MainFrame.WIDTH/10,MainFrame.HEIGHT/10);	
			
			g.setFont(bigFont);
			g.setColor(Color.black);
			g.drawString("The end", MainFrame.WIDTH/3,MainFrame.HEIGHT/2);
		}
		
		//drawing the screen text
		g.setFont(smallFont);
		timer++;	
		g.setColor(Color.red);
		if (model.isDraw2Screen() && timer < 90 && Math.sin(model.getRealTime()) > .5 )
			g.drawString(model.getScreenText(), MainFrame.WIDTH/2, MainFrame.HEIGHT*3/4);
		
		if (timer == 90){
			model.setDraw2Screen(false);
			timer = 0;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count--;
		if (e.getSource() == exitButton){
			System.exit(0);
		}else if(e.getSource() == resumeButton){
			myCarManager.stopGame();
		}
		else if(e.getSource() == menuButton){
			enter.back();
			enter.back();
			enter.back();
			enter.mainSound.stop();
			enter.mainSound.changeMusic(enter.mainSound.menuSounds);
			enter.mainSound.start();
		}
		else if( e.getSource() == resetGame){
			
			enter.back();
			IGameSetup basicSetup = null;
			GameMainPanel panel = null;
			
			try {
				basicSetup = new BasicGameSetup();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			this.model= new GameModel(basicSetup);
			try {
				panel = new GameMainPanel(model,enter);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			enter.changePanel(panel);
			model.addView(panel);
			panel.cpView.requestFocusInWindow();
			


			//stopping sound
			enter.mainSound.stop();
			if(model.getMap().getMapName().equals("city")){
				enter.mainSound.changeMusic(enter.mainSound.citys);
			}
			else if(model.getMap().getMapName().equals("highway")){
				enter.mainSound.changeMusic(enter.mainSound.highways);
			}
			else if(model.getMap().getMapName().equals("rural")){
				enter.mainSound.changeMusic(enter.mainSound.deserts);
			}
			
			enter.mainSound.start();
			
		}
		else if(e.getSource() == highScore){
			boolean switched = true; 
			try {
				out = new PrintWriter(myFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i = 0; i < 10; i++){
				if(model.getPoints() > highScorePointsList.get(i) && switched){
					out.println(model.getPoints() + "/" + highScore.getText());
					switched = false;
					highScorePointsList.add(i, model.getPoints());
					highScoreNameList.add(i, highScore.getText());
				}
				else{
					out.println(highScorePointsList.get(i) + "/" + highScoreNameList.get(i));
				}
			}
			out.close();
			highScore.setVisible(false);
			score.setText("Thanks for playing game. Do you want to play again?");
		}
	}
	
	

}
