package Menu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	Entrance enter;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width1 = (int)screenSize.getWidth();
	static int height1 = (int)screenSize.getHeight();
	public static int WIDTH = 1024;
	public static int HEIGHT = 768;
	
	public static JFrame f;
	public MainFrame() throws FileNotFoundException {
		super("Fast and Careful!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		enter = new Entrance(WIDTH,HEIGHT);
		add(enter);
		setBounds(0, 0, width1, height1);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) throws FileNotFoundException {
		f = new MainFrame();
	}

}
