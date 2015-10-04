package Game;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

public class GameBottomPanelView extends JPanel implements IGameView{
	//Properties
	private JLabel notification;
	private Font font;
	private GameModel model;
	
	
	public GameBottomPanelView(GameModel model) {
		this.model = model;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.white);
		setPreferredSize(new Dimension(model.getWidth(), model.getHeight()/20));
		
		font = new Font("Serif", Font.PLAIN, 18);
		notification = new JLabel(model.getBottomText());
				
		add(notification);
	}
	
	@Override
	public void updateView(GameModel model) {
		if (model.isStarted()){
			notification.setText(model.getBottomText());
		}
	}

}
