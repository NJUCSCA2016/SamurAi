package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgOthers;

public class JButtonGameSoundLine extends StaticButton implements ActionListener{
	
	private JPanelSetting fatherPanel ;
	private int sound_SeriesNumber;
	
	
	public JButtonGameSoundLine(int x, int y, int w, int h , int sound_SeriesNumber ,JPanelSetting fatherPanel) {
		super(x, y, w, h, null);
		
		this.sound_SeriesNumber = sound_SeriesNumber;
		this.fatherPanel = fatherPanel;
		
		this.addActionListener(this);
//		this.setOpaque(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.fatherPanel.setGameVol(ImgOthers.SOUNDS_VOLUME[sound_SeriesNumber]);
		Player.MUSiC_PLAYER.setGameVol(sound_SeriesNumber);
		repaint();
	}
	
}
