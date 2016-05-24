package team.csca.link.serverLink;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgLink;

public class JButtonRetry extends DynamicButton {

	private JPanelLinkLoading fatherPanel;
	
	public JButtonRetry(JPanelLinkLoading fatherPanel) {
		
		super(266, 503, 249, 103,
				ImgLink.LOGIN_FAILED_RETRY_INIT , 
				ImgLink.LOGIN_FAILED_RETRY_ENTER ,
				ImgLink.LOGIN_FAILED_RETRY_CLICK);
		
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		fatherPanel.restart();
		repaint();
		Player.stopMusic();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("linkserver");
		}
		
	}
	
}
