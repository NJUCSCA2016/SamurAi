package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgOthers;
import team.csca.view.image.ImgSystem;

public class JButtonGameSoundLine extends StaticButton implements ActionListener{
	
	private JPanelSetting fatherPanel ;
	private int sound_SeriesNumber;
	private JButtonGameSound gameSound;
	
	public JButtonGameSoundLine(int x, int sound_SeriesNumber, JButtonGameSound gameSound ,JPanelSetting fatherPanel) {
		super(x, 440, 30 , 43, null);
		
		this.sound_SeriesNumber = sound_SeriesNumber;
		this.fatherPanel = fatherPanel;
		this.gameSound = gameSound;
		
		this.addActionListener(this);
//		this.setOpaque(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.fatherPanel.setGameVol(ImgOthers.SOUNDS_VOLUME[sound_SeriesNumber]);
		Player.MUSiC_PLAYER.setGameVol(sound_SeriesNumber);
		//Tick
		if(! Player.MUSiC_PLAYER.isGame_ON()){
			this.gameSound.setButtonImg(ImgSystem.TICK);
			Player.MUSiC_PLAYER.changeGame_ON();
		}
		repaint();
	}
	
}
