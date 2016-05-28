package team.csca.view.propPattern;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;
import team.csca.view.set.JButtonBackSound;
import team.csca.view.set.JButtonGameSound;

public class JPanelSetting extends JPanel{
	
	private LayerBackground background;
	
	public JPanelSetting() {
		
		this.setLayout(null);
		this.background = new LayerBackground(0, 0, 1250, 700, ImgBackground.PM_SETTING);
		
		JButtonBackSound jButtonBackSound = new JButtonBackSound();
		JButtonGameSound jButtonGameSound = new JButtonGameSound();
		this.add(jButtonBackSound);
		this.add(jButtonGameSound);
		this.add(new JButtonExitSet(this));

		}
		
	
	
	public void paintComponent(Graphics g){
		
		this.background.createWindow(g);
		super.paintComponents(g);
		
	}
	
	
}

