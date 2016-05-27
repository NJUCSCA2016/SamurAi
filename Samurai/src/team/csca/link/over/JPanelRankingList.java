package team.csca.link.over;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;

public class JPanelRankingList extends JPanel{
	public Layer[] layers = {
			new LayerBackground(275, 125, 700, 700, ImgGameOver.RANKING_LIST)
			};
	public JPanelRankingList(){
		this.setLayout(null);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
}
