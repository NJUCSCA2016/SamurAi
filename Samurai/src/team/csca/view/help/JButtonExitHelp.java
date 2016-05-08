package team.csca.view.help;


import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonExitHelp extends DynamicButton{
	
	private JPanelHelp fatherPanel;
	
	public JButtonExitHelp(JPanelHelp fatherPanel){
		super(983, 92, 60, 60, ImgButton.RETURN_FROM_INIT, ImgButton.RETURN_FROM_ENTER, ImgButton.RETURN_FROM_CLICKED);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
		this.frame.remove(this.fatherPanel);
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		
	}
	
}
