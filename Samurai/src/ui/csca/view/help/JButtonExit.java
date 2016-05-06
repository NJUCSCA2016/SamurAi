package ui.csca.view.help;


import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonExit extends DynamicButton{
	
	private JPanelHelp fatherPanel;
	
	public JButtonExit(JPanelHelp fatherPanel){
		super(0, 0, 1250, 700, ImgButton.BUTTON_EXIT_INIT, ImgButton.BUTTON_EXIT_ENTER, ImgButton.BUTTON_EXIT_CLICK);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
		this.frame.remove(this.fatherPanel);
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		
	}
	
}
