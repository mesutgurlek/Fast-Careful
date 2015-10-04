package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Deneme extends JPanel implements KeyListener {

	public Deneme() {
		addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("geliyiiii");

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
