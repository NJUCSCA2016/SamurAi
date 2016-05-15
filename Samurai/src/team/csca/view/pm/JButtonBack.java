package team.csca.view.pm;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;
import team.csca.view.startgame.JPanelStartGame;

/**
 * 返回按钮
 * 
 * @author Water
 *
 */
public class JButtonBack extends DynamicButton {
	private JPanelPM fatherPanel;

	public JButtonBack(JPanelPM fatherPanel){
		// TODO:调整位置
		super(865, 635, 140, 50, ImgButton.BACK_INIT, ImgButton.BACK_ENTER, ImgButton.BACK_CLICKED);
		this.fatherPanel = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		this.frame.remove(this.fatherPanel);
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		Player.stopMusic();
		Player.playMusic("bgm1");

	}
}
