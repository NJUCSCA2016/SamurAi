package team.csca.view.link.serverLink;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;

public class JButtonRetry extends DynamicButton {

	private JPanelLinkLoading fatherPanel;
	
	public JButtonRetry(JPanelLinkLoading fatherPanel) {
		super(0, 0, 0, 0, null , null , null);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		fatherPanel.restart();
		repaint();
		Player.stopMusic();
		Player.playMusic("linkserver");
	}
	
}
