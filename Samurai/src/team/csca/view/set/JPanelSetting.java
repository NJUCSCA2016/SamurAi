package team.csca.view.set;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;
import team.csca.view.image.ImgOthers;

public class JPanelSetting extends JPanel{
	
	private LayerBackground background;
	/**
	 * 需要绘制的两个音量条
	 * 一开始默认为10
	 * 需要设置为静态。
	 */
	private static Image VOLUME_BACK = ImgOthers.SOUNDS_VOLUME[10];
	
	private static Image VOLUME_GAME = ImgOthers.SOUNDS_VOLUME[10];
	
	public JPanelSetting() {
		
		this.setLayout(null);
		this.background = new LayerBackground(0, 0, 1250, 700, ImgBackground.SETTING_PANEL_BACKGROUND);
		
		JButtonBackSound jButtonBackSound = new JButtonBackSound();
		JButtonGameSound jButtonGameSound = new JButtonGameSound();
		this.add(jButtonBackSound);
		this.add(jButtonGameSound);
		this.add(new JButtonExitSet(this));
		
		//添加一系列的音量控制按钮。
		this.add(new JButtonBackSoundZero(jButtonBackSound, this));
		this.add(new JButtonGameSoundZero(jButtonGameSound, this));
		
		for(int i = 1 ; i < 11 ; i++) {
			this.add(new JButtonBackSoundLine(425 + 30 * i, i, jButtonBackSound, this));
			this.add(new JButtonGameSoundLine(425 + 30 * i, i, jButtonGameSound, this));
		}
		
	}
	
	public void paintComponent(Graphics g){
		
		this.background.createWindow(g);
		g.drawImage(VOLUME_BACK, 375, 323, null);
		g.drawImage(VOLUME_GAME, 375, 440, null);
		super.paintComponents(g);
		
	}
	
	public void setBackVol(Image volumeBack){
		JPanelSetting.VOLUME_BACK = volumeBack;
	}
	
	public void setGameVol(Image volumeGame){
		JPanelSetting.VOLUME_GAME = volumeGame;
	}
	
}
