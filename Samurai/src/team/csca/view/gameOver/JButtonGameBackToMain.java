package team.csca.view.gameOver;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgGameOver;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonGameBackToMain extends DynamicButton{
	public JButtonGameBackToMain() {
		super(1150, 0, 71, 71, ImgGameOver.CROSS, ImgGameOver.CROSS, ImgGameOver.CROSS);
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		frame.removeAll();
		
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		Player.stopMusic();
		Player.stopMidi();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm1");
		}

	}
}
