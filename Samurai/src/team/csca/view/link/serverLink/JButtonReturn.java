package team.csca.view.link.serverLink;

import java.awt.Image;
import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonReturn extends DynamicButton{

	private JPanelLinkLoading fatherPanel;
	
	
	public JButtonReturn(int x, int y, int w, int h, Image initImage, Image enterImage, Image clickImage , JPanelLinkLoading fatherPanel) {
		super(x, y, w, h, initImage, enterImage, clickImage);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.frame.remove(fatherPanel);
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		Player.stopMusic();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm1");
		}

	}
	
	
	
}
