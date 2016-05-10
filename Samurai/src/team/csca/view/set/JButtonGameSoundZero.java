package team.csca.view.set;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgOthers;
import team.csca.view.image.ImgSystem;

public class JButtonGameSoundZero extends StaticButton implements ActionListener{
	
	private JPanelSetting fatherPanel ;
	private int sound_SeriesNumber = 0;
	private JButtonGameSound gameSound;
	
	public JButtonGameSoundZero(JButtonGameSound gameSound , JPanelSetting fatherPanel) {
		super(425, 440, 30, 43, null);
		
		this.gameSound = gameSound;
		this.fatherPanel = fatherPanel;
		
		this.addActionListener(this);
//		this.setOpaque(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.fatherPanel.setGameVol(ImgOthers.SOUNDS_VOLUME[sound_SeriesNumber]);
		gameSound.setButtonImg(null);
		Player.MUSiC_PLAYER.setGameVol(sound_SeriesNumber);
		Player.MUSiC_PLAYER.turnOffGame();
		repaint();
	}
	
}
