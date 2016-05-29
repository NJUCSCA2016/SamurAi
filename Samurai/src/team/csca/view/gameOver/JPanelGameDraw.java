package team.csca.view.gameOver;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;

public class JPanelGameDraw extends JPanel{
	
	public JPanelGameDraw() {
		setLayout(null);
		this.add(new JButtonGameBackToMain(this));
		background = new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_DRAW);
	}
	protected LayerBackground background;
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponents(g);
	}

}
