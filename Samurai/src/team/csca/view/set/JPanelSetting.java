package team.csca.view.set;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;

public class JPanelSetting extends JPanel{
	
	private LayerBackground background;
	
	public JPanelSetting() {
		
		this.setLayout(null);
		this.background = new LayerBackground(0, 0, 1250, 700, ImgBackground.SETTING_PANEL_BACKGROUND);
		//TODO ： 三个按钮的位置需要定一下。
		this.add(new JButtonBackSound());
		this.add(new JButtonGameSound());
		this.add(new JButtonExitSet(this));
		
	}
	
	public void paint(Graphics g){
		
		this.background.createWindow(g);
		super.paintComponents(g);
		
	}
	
}
