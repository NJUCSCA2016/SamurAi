package team.csca.link.tra;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.link.modelChoose.JPanelChooseList;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgGameOver;

public class JButtonBackToChoose extends DynamicButton {
	
	public JButtonBackToChoose() {
		super(1150, 0, 71, 71, ImgGameOver.CROSS, ImgGameOver.CROSS, ImgGameOver.CROSS);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		frame.removeAll();
		
		this.frame.setContentPane(new JPanelChooseList());
		this.frame.revalidate();
		Player.stopMusic();
		Player.stopMidi();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm1");
		}

	}
}
