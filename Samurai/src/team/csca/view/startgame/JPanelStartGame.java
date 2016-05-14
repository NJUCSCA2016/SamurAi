package team.csca.view.startgame;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;

public class JPanelStartGame extends JPanel{
	
	
	private Layer[] layers;
	
	ImageIcon iiStart = new ImageIcon("Image/Button/P2.png");

	
	public JPanelStartGame(){
		
		this.setLayout(null);
		// 添加背景
		layers = new Layer[] {
			new LayerBackground(0, 0, 1250, 700, ImgBackground.MAIN_PANEL_BACKGROUND1),	
			new LayerBackground(269, 20, 700, 150, ImgBackground.MAIN_PANEL_BACKGROUND2),
		};
		// 添加按钮
		
		this.add(new JButtonPP(this));
		this.add(new JButtonSituation(this));
		this.add(new JButtonSetting(this));
		this.add(new JButtonPM(this));
		this.add(new JButtonProps(this));
		this.add(new JButtonHelp(this));
		this.add(new JButtonExit());
//		Player.playMusic("bgm1");
	}
	public void paintComponent(Graphics g){
		for(int i = 0 ; i < this.layers.length ; i++){
			layers[i].createWindow(g);
		}
		
		super.paintComponents(g);
	}
	

}
