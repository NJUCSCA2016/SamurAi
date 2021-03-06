package team.csca.view.gameOver;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgGameOver;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonGameBackToMain extends DynamicButton{
	private JPanel panel;
	
	public JButtonGameBackToMain(JPanel panel) {
		super(1150, 0, 71, 71, ImgGameOver.CROSS, ImgGameOver.CROSS, ImgGameOver.CROSS);
		this.panel = panel;
		
	}
	   
	

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		frame.remove(panel);
		System.out.println("Do");
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		Player.stopMusic();
		Player.stopMidi();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm1");
		}

	}
}
