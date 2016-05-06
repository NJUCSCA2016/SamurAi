package team.csca.view.help;


import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonExit extends DynamicButton{
	
	private JPanelHelp fatherPanel;
	
	public JButtonExit(JPanelHelp fatherPanel){
		super(928, 103, 60, 60, ImgButton.RETURN_FROM_HELP_INIT, ImgButton.RETURN_FROM_HELP_ENTER, ImgButton.RETURN_FROM_HELP_CLICKED);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
		this.frame.remove(this.fatherPanel);
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		
	}
	
}
