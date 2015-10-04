package Game;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;

import javax.swing.JFrame;


public class Engine {/*
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width1 = (int)screenSize.getWidth();
	static int height1 = (int)screenSize.getHeight();
	public static JFrame f;
	
	public static int menuNumber = 1, WIDTH = 1024, HEIGHT = 768;
	
	public Engine() throws FileNotFoundException {
		
		f = new JFrame("Fast and Careful");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0, 0, width1, height1);
		
		if(menuNumber != -1){
			if (menuNumber == 1){
							
				IGameSetup basicSetup;
				GameModel model;
				GameMainPanel panel;
				
				basicSetup = new BasicGameSetup();
				model = new GameModel(basicSetup);
				panel = new GameMainPanel(model);
				model.addView(panel);
				
				f.add(panel);
				f.pack();
				
				System.out.println("inside gameplay");
			}
			
		}
		
		if (menuNumber == -1)
			System.exit(0);
		
		
		
	}
	
	/*public static void main(String[] args) throws FileNotFoundException {
		
		f = new JFrame("Fast and Careful");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0, 0, width1, height1);
		
		if(menuNumber != -1){
			if (menuNumber == 1){
							
				IGameSetup basicSetup;
				GameModel model;
				GameMainPanel panel;
				
				basicSetup = new BasicGameSetup();
				model = new GameModel(basicSetup);
				panel = new GameMainPanel(model);
				model.addView(panel);
				
				f.add(panel);
				f.pack();
				
				System.out.println("inside gameplay");
			}
			
		}
		
		if (menuNumber == -1)
			System.exit(0);
		
		

	}
	public static JFrame getFrame(){
		return f;
	}
	*/

}
