package team.csca.view.set;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;
import team.csca.view.image.ImgOthers;

public class JPanelSetting extends JPanel{
	
	private LayerBackground background;
	
	private Image volumeBack = ImgOthers.SOUNDS_VOLUME[10];
	
	private Image volumeGame = ImgOthers.SOUNDS_VOLUME[10];
	
	public JPanelSetting() {
		
		this.setLayout(null);
		this.background = new LayerBackground(0, 0, 1250, 700, ImgBackground.SETTING_PANEL_BACKGROUND);
		
		this.add(new JButtonBackSound());
		this.add(new JButtonGameSound());
		this.add(new JButtonExitSet(this));
		
		//添加一系列的音量控制按钮。
		for(int i = 0 ; i < 11 ; i++) {
			
		}
		
	}
	
	public void paint(Graphics g){
		
		this.background.createWindow(g);
		g.drawImage(volumeBack, 375, 323, null);
		g.drawImage(volumeGame, 375, 440, null);
		super.paintComponents(g);
		
	}
	
	public void setBackVol(Image volumeBack){
		this.volumeBack = volumeBack;
	}
	
	public void setGameVol(Image volumeGame){
		this.volumeGame = volumeGame;
	}
	
}
