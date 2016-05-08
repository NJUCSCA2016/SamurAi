package team.csca.view.pp;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.controller.media.Player;
import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;

/**
 * 人人对战
 * @author Water
 *
 */
public class JPanelPP extends JPanel{
	private Layer[] layers;
	
	
	public JPanelPP(){
		layers = new Layer[]{
				new LayerBackground(0, 0, 1250, 700, ImgBackground.PP_PANEL),
				
		};
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm2");
		}
	}
	
	public void paintComponent(Graphics g){
		for(int i = 0 ; i < this.layers.length ; i++){
			layers[i].createWindow(g);
		}
		
		super.paintComponents(g);
	}
}
