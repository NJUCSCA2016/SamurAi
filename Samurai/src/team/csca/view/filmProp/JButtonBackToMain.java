package team.csca.view.filmProp;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgHurdle;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonBackToMain extends DynamicButton{
	
	JPanel father;
	
	public JButtonBackToMain(JPanel father) {
		super(1127, 600, 123, 93, ImgHurdle.BUTTON_RETURN_INIT, ImgHurdle.BUTTON_RETURN_ENTER, ImgHurdle.BUTTON_RETURN_CLICKED);
		this.father = father;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		frame.remove(father);
		frame.setContentPane(new JPanelStartGame());
		frame.revalidate();
	}
}
