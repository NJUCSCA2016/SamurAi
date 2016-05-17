package team.csca.view.gameOver;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;

public class JPanelGameWin extends JPanel{
	public Layer[] layers = {
			new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_WIN)
	};
	public JPanelGameWin() {
		this.setLayout(null);
		repaint();
		this.add(new JButtonGameBackToMain(this));
	}
	public void paintComponent(Graphics g) {
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
}
