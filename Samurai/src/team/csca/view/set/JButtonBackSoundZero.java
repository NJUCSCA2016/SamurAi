package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgOthers;

public class JButtonBackSoundZero extends StaticButton implements ActionListener{
	
	
	private JPanelSetting fatherPanel ;
	private int sound_SeriesNumber;
	private JButtonBackSound backSound;
	
	public JButtonBackSoundZero(JButtonBackSound backSound, JPanelSetting fatherPanel) {
		super(425, 323, 30, 43, null);
		
		this.backSound = backSound;
		this.fatherPanel = fatherPanel;
		
		this.addActionListener(this);
//		this.setOpaque(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		fatherPanel.setBackVol(ImgOthers.SOUNDS_VOLUME[this.sound_SeriesNumber]);
		Player.MUSiC_PLAYER.turnOffBack();
		this.backSound.setButtonImg(null);
		Player.stopMusic();
		repaint();
	}
	
	
}
