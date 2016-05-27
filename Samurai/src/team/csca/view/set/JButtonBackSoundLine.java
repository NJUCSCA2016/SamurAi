package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgOthers;
import team.csca.view.image.ImgSystem;

public class JButtonBackSoundLine extends StaticButton implements ActionListener{
	
	private JPanelSetting fatherPanel ;
	private int sound_SeriesNumber;
	private JButtonBackSound backSound;
	
	public JButtonBackSoundLine(int x, int sound_SeriesNumber , JButtonBackSound backSound ,JPanelSetting fatherPanel) {
		super(x, 323, 30 , 43, null);
		
		this.sound_SeriesNumber = sound_SeriesNumber;
		this.fatherPanel = fatherPanel;
		this.backSound = backSound;
		
		this.addActionListener(this);
//		this.setOpaque(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
		fatherPanel.setBackVol(ImgOthers.SOUNDS_VOLUME[this.sound_SeriesNumber]);
		
		Player.MUSiC_PLAYER.setBackVol(sound_SeriesNumber);
		Player.MUSiC_PLAYER.pauseBack();
		Player.MUSiC_PLAYER.rePlay();
		//如果之前的是0的话需要Tick
		if(! Player.MUSiC_PLAYER.isBack_ON()){
			this.backSound.setButtonImg(ImgSystem.TICK);
			Player.MUSiC_PLAYER.changeBack_ON();
			Player.playMusic("bgm1");
		}
		repaint();
	}
	
	
}
